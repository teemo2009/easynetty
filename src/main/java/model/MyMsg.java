package model;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import net.Message;
import annotation.MessageMeta;

/**
 * Created by Administrator on 2017/12/8.
 */
@MessageMeta(module = 1,cmd =2 )
public class MyMsg extends Message {
    @Protobuf(order = 1,required = false)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
