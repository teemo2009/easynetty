package task;

import net.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/12/12.
 */
public class MessageTask extends  AbstractDistributeTask {

    private Message message;

    private Object handler;

    private Method method;

    private Object[]  params;

    public static MessageTask valueof(int distributeKey,Object handler,Method method,Object[] params){
        MessageTask messageTask=new MessageTask();
        messageTask.distributeKey=distributeKey;
        messageTask.handler=handler;
        messageTask.method=method;
        messageTask.params=params;
        return  messageTask;
    }


    /**执行方法**/
    @Override
    public void action() {
        try {
            method.invoke(handler,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message getMessage() {
        return message;
    }

    public Object getHandler() {
        return handler;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getParams() {
        return params;
    }
}
