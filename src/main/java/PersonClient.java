import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2017/12/7.
 */
public class PersonClient {

    private final String host;
    private final int port;

    public PersonClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.remoteAddress(new InetSocketAddress(host, port));
            b.handler(new ChannelInitializer<SocketChannel>() {

                public void initChannel(SocketChannel ch) throws Exception {
                    // ----Protobuf处理器，这里的配置是关键----
                    ch.pipeline().addLast("frameDecoder", new ProtobufVarint32FrameDecoder());// 用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）
                    //配置Protobuf解码处理器，消息接收到了就会自动解码，ProtobufDecoder是netty自带的，Message是自己定义的Protobuf类
                    ch.pipeline().addLast("protobufDecoder",
                            new ProtobufDecoder(PersonMsg.Person.getDefaultInstance()));
                    // 用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。
                    ch.pipeline().addLast("frameEncoder",
                            new ProtobufVarint32LengthFieldPrepender());
                    //配置Protobuf编码器，发送的消息会先经过编码
                    ch.pipeline().addLast("protobufEncoder", new ProtobufEncoder());
                    // ----Protobuf处理器END----
                    ch.pipeline().addLast("handler",new PersonClientHandler());
                }
            });
            ChannelFuture f = b.connect().sync();
            f.addListener(new ChannelFutureListener() {

                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        System.out.println("client connected");
                    }else{
                        System.out.println("server attemp failed");
                        future.cause().printStackTrace();
                    }

                }
            });
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new PersonClient("127.0.0.1", 7000).start();
    }
}
