package model;

import annotation.MessageMeta;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import net.Message;

/**
 * Created by Administrator on 2017/12/11.
 */
@MessageMeta(module = 1,cmd = 3)
public class User extends Message {

    @Protobuf
    private String name;
    @Protobuf
    private  int age;
    @Protobuf
    private String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
