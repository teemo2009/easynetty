import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Administrator on 2017/12/8.
 */
public class MyEncoder extends MessageToByteEncoder<MessageLite> {


    /**
     * 传入协议数据，产生携带包头后的数据
     * */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageLite messageLite, ByteBuf byteBuf) throws Exception {
            byte[] body=messageLite.toByteArray();
            byte[] header=encodeHeader(messageLite, (short)body.length);

    }

    /**
     * 获得一个协议头
     * @param msg
     * @param bodyLength
     * @return
     */
    private byte[] encodeHeader(MessageLite msg,short bodyLength){
        short _typeId = 0;
        if(msg instanceof MyNet.MyReq){
            _typeId = MyNet.MyREQ;
        }else if(msg instanceof MyNet.MyRes){
            _typeId = MyNet.MyRES;
        }
        //存放两个short数据
        byte[] header = new byte[4];
        //前两位放数据长度
        header[0] = (byte) (bodyLength & 0xff);
        header[1] = (byte) ((bodyLength >> 8) & 0xff);
        //后两个字段存协议id
        header[2] = (byte) (_typeId & 0xff);
        header[3] = (byte) ((_typeId >> 8) & 0xff);
        return header;
    }
}
