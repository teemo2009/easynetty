import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/12/7.
 */
public class EchoServer {
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
                    // ----Protobuf处理器END----
                    pipeline.addLast("myhandler", new EchoServerHandler());//自己定义的消息处理器，接收消息会在这个类处理
                }
            }).option(ChannelOption.SO_BACKLOG, 128)//最大客户端连接数为128
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind().sync();// 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
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
            new EchoServer().start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
