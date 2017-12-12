import java.io.IOException;

/**
 * Created by Administrator on 2017/12/7.
 */
public class ProtoMain {
    public static void main(String[] args) {
        String protoFile = "person-entity.proto";//
        String strCmd = "E:/gameTools/protobuf-3.5.0/src/protoc.exe -I=./proto --java_out=./src/main/java ./proto/"+ protoFile;
        try {
            Runtime.getRuntime().exec(strCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }//通过执行cmd命令调用protoc.exe程序
    }
}
