package proto;

import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;
import com.baidu.bjf.remoting.protobuf.ProtobufIDLProxy;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import model.User;

import java.io.File;

/**
 * Created by Administrator on 2017/12/12.
 */
public class ProtoBufMain {

    public static void main(String[] args) {
        //返回的内容即为 Protobuf的IDL描述文件
        String code = ProtobufIDLGenerator.getIDL(User.class);
        System.out.println(code);
        //ProtobufIDLProxy.create("", false,new File("D:/MyProto"));
    }
}
