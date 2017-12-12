import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2017/12/7.
 */
public class PersonServer {
    private static final int port=7000;
    public void start() throws InterruptedException{
        ServerBootstrap b=new ServerBootstrap();
        //bossGroup 用来接收进来的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup 用来处理已经被接收的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            b.group(bossGroup,workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.localAddress(new InetSocketAddress(port));
            b.childHandler(new ChannelInitializer<SocketChannel>() {//有连接到达时会创建一个channel
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();

                    // ----Protobuf处理器，这里的配置是关键----
                    pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());// 用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）
                    //配置Protobuf解码处理器，消息接收到了就会自动解码，ProtobufDecoder是netty自带的，Message是自己定义的Protobuf类
                    pipeline.addLast("protobufDecoder",
                            new ProtobufDecoder(PersonMsg.Person.getDefaultInstance()));
                    // 用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。
                    pipeline.addLast("frameEncoder",
                            new ProtobufVarint32LengthFieldPrepender());
                    //配置Protobuf编码器，发送的消息会先经过编码
                    pipeline.addLast("protobufEncoder", new ProtobufEncoder());
                    // ----Protobuf处理器END----
                    pipeline.addLast("handler", new PersonServerHandler());//自己定义的消息处理器，接收消息会在这个类处理
                    // ----Protobuf处理器END----
                    //pipeline.addLast("ackHandler", new AckServerHandler());//处理ACK
                  /*  pipeline.addLast("timeout", new IdleStateHandler(100, 0, 0,
                            TimeUnit.SECONDS));// //此两项为添加心跳机制,60秒查看一次在线的客户端channel是否空闲
                    pipeline.addLast(new HeartBeatServerHandler());// 心跳处理handler*/
                }
            }).option(ChannelOption.SO_BACKLOG, 128)//最大客户端连接数为128
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind().sync();// 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
            System.out.println(PersonServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();// 应用程序会一直等待，直到channel关闭
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully().sync();//关闭EventLoopGroup，释放掉所有资源包括创建的线程
            bossGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) {
        try {
            new PersonServer().start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
