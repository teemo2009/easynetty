package net;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/8.
 */
public class MyEncoder extends MessageToByteEncoder<Message> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf out) throws Exception {
        short module = message.getModule();
        short cmd = message.getCmd();
        byte[] body = wrapMessageBody(module, cmd, message);
        out.writeShort(module);
        out.writeShort(cmd);
        out.writeBytes(body);
    }

    private byte[] wrapMessageBody(short module, short cmd, Message message) {
        //写入具体消息的内容
        byte[] body = null;
        @SuppressWarnings("unchecked")
        Class<Message> msgClazz = (Class<Message>) MessageFactory.INSTANCE.getMessage(module, cmd);
        try {
            Codec<Message> codec = ProtobufProxy.create(msgClazz);
            body = codec.encode(message);
        } catch (IOException e) {
        }
        return body;
    }


}
