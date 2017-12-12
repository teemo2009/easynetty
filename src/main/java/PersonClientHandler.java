import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by Administrator on 2017/12/7.
 */
public class PersonClientHandler extends SimpleChannelInboundHandler<PersonMsg.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PersonMsg.Person person) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        PersonMsg.Person.Builder builder=PersonMsg.Person.newBuilder();
        builder.setQuery("111111");
        PersonMsg.Person person= builder.build();
        ctx.writeAndFlush(person);
        System.out.println("已经发送了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
