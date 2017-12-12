package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import model.MyMsg;
import net.Message;

/**
 * Created by Administrator on 2017/12/7.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message person) throws Exception {
        System.out.println("client!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyMsg msg=new MyMsg();
        msg.setValue("我来自客户端的Proto");
        ctx.writeAndFlush(msg);
        System.out.println("已经发送了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
