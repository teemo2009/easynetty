package net;

import annotation.Controller;
import annotation.MessageMeta;
import annotation.RequestMapping;
import io.netty.channel.ChannelHandlerContext;
import task.MessageTask;
import util.TaskHandlerContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/12/11.
 */
public class MessageDispatcher {
    private volatile static MessageDispatcher instance;
    private static final Map<String, CmdExecutor> MODULE_CMD_HANDLERS = new HashMap<String, CmdExecutor>();

    public static MessageDispatcher getInstance() {
        //double check
        if (instance == null) {
            synchronized (MessageDispatcher.class) {
                if (instance == null) {
                    instance = new MessageDispatcher();
                }
            }
        }
        return instance;
    }

    private MessageDispatcher() {
        initialize();
    }

    public void initialize() {
        Set<Class<?>> controllers = ClassScanner.listClassesWithAnnotation("controller", Controller.class);
        for (Class<?> controller: controllers) {
            try {
                Object handler = controller.newInstance();
                Method[] methods = controller.getDeclaredMethods();
                for (Method method:methods) {
                    RequestMapping mapperAnnotation = method.getAnnotation(RequestMapping.class);
                    if (mapperAnnotation != null) {
                        short[] meta = getMessageMeta(method);
                        if (meta == null) {
                            throw new RuntimeException(String.format("controller[%s] method[%s] lack of RequestMapping annotation",
                                    controller.getName(), method.getName()));
                        }
                        short module = meta[0];
                        short cmd    = meta[1];
                        String key = buildKey(module, cmd);
                        CmdExecutor cmdExecutor = MODULE_CMD_HANDLERS.get(key);
                        if (cmdExecutor != null) {
                            throw new RuntimeException(String.format("module[%d] cmd[%d] duplicated", module, cmd));
                        }

                        cmdExecutor = CmdExecutor.valueOf(method, method.getParameterTypes(), handler);
                        MODULE_CMD_HANDLERS.put(key, cmdExecutor);
                    }
                }
            }catch(Exception e) {
            }
        }
    }

    /**
     * 返回方法所带Message参数的元信息
     * @param method
     * @return
     */
    private short[] getMessageMeta(Method method) {
        for (Class<?> paramClazz: method.getParameterTypes()) {
            if (Message.class.isAssignableFrom(paramClazz)) {
                MessageMeta protocol = paramClazz.getAnnotation(MessageMeta.class);
                if (protocol != null) {
                    short[] meta = {protocol.module(), protocol.cmd()};
                    return meta;
                }
            }
        }
        return null;
    }

    public void dispatch(ChannelHandlerContext ctx,Message message){
        short module=message.getModule();
        short cmd=message.getCmd();
        CmdExecutor cmdExecutor = MODULE_CMD_HANDLERS.get(buildKey(module, cmd));
        if (cmdExecutor == null) {
            System.out.println("message executor missed");
            return;
        }
        Object[] params = convertToMethodParams(cmdExecutor.getParams(), message);
        Object controller = cmdExecutor.getHandler();
        long distributeKey=System.currentTimeMillis()/1000;
        TaskHandlerContext.INSTANCE.acceptTask(MessageTask.valueof(Integer.valueOf(distributeKey+""),controller,cmdExecutor.getMethod(),params));
        //简单的执行——需要利用线程去解决
       /* try {
            cmdExecutor.getMethod().invoke(controller, params);
        }catch (Exception e){

        }*/
    }

    /**
     * 将各种参数转为被RequestMapper注解的方法的实参
     * @param
     * @param methodParams
     * @param message
     * @return
     */
    private Object[] convertToMethodParams(Class<?>[] methodParams, Message message) {
        Object[] result = new Object[methodParams==null?0:methodParams.length];
        for (int i=0;i<result.length;i++) {
            Class<?> param = methodParams[i];
            if(Message.class.isAssignableFrom(param)) {
                result[i] = message;
            }
        }

        return result;
    }


    private String buildKey(short module, short cmd) {
        return module + "_" + cmd;
    }
}
