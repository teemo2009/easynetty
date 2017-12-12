package net;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import model.MyMsg;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8.
 */
public class MyDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        short module = byteBuf.readShort();
        short cmd = byteBuf.readShort();
        int b= byteBuf.readableBytes();
        byte[] body=new byte[b];
        byteBuf.readBytes(body);
        Message message = readMessage(module, cmd, body);
        out.add(message);
    }

    private Message readMessage(short module, short cmd, byte[] body) {
        Class<?> msgClazz = MessageFactory.INSTANCE.getMessage(module, cmd);
        try {
            Codec<?> codec = ProtobufProxy.create(msgClazz);
            Message message = (Message) codec.decode(body);
            return message;
        } catch (IOException e) {
        }
        return null;
    }
}
