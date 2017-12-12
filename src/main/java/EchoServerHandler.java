import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

/**
 * Created by Administrator on 2017/12/7.
 * 业务逻辑处理  集成ChannelInboundHandlerAdapter
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        String clientMsg=in.toString(CharsetUtil.UTF_8);
        //释放掉连接资源
        in.release();
        System.out.println("来自客户端的消息"+clientMsg);
        // 向客户端发送消息
        String response = "来自服务端的相应："+clientMsg;
        // 在当前场景下，发送的数据必须转换成ByteBuf数组
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
        encoded.writeBytes(response.getBytes());
        ctx.write(encoded);
        System.out.println("channelRead这里执行了一次");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /**将未决消息冲刷到远程节点，并且关闭该 Channel*/
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
        System.out.printf("这里是最后一条信息了~");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();//捕捉异常信息
        ctx.close();//出现异常时关闭channel
    }
}
