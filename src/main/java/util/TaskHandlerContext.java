package util;

import edu.emory.mathcs.backport.java.util.concurrent.atomic.AtomicBoolean;
import javafx.concurrent.Task;
import task.AbstractDistributeTask;

import javax.naming.CompositeName;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2017/12/11.
 */
public enum TaskHandlerContext {
    INSTANCE;

    private final int CORE_SIZE = Runtime.getRuntime().availableProcessors();

    private final List<TaskWorker> workerPool=new ArrayList<TaskWorker>();

    private ExecutorService es= Executors.newCachedThreadPool();

    private final AtomicBoolean run = new AtomicBoolean(true);

    public void initialize(){
        for(int i=1;i< CORE_SIZE;i++){
            TaskWorker taskWorker=new TaskWorker(i);
            workerPool.add(taskWorker);
            es.execute(taskWorker);
        }
    }

    public void acceptTask(AbstractDistributeTask task){
        if (task == null) {
            throw new NullPointerException("task is null");
        }
        //负载均衡的线程思维(随机分配)
        int distributeKey = task.distributeKey() % workerPool.size();
        workerPool.get(distributeKey).addTask(task);
    }


    public void shutDown() {
        run.set(false);
    }

    private class TaskWorker implements  Runnable{
        private  int workerIndex;

        /**LinkedBlockingQueue存在一种隐患，当生产者的生产速度 大于 消费者的消费速度时，系统资源可能会被耗尽！*/
        private BlockingQueue<AbstractDistributeTask> taskQueue=new LinkedBlockingQueue<AbstractDistributeTask>();

        TaskWorker(int workerIndex){
            this.workerIndex=workerIndex;
        }

        public void addTask(AbstractDistributeTask task) {
            this.taskQueue.add(task);
        }

        @Override
        public void run() {
                while (run.get()){
                    try {
                        /* 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入;*/
                        AbstractDistributeTask task = taskQueue.take();
                        task.markStartMillis();
                        task.action();
                        task.markEndMillis();
                        //if it is TimerTask and should run again, add it to queue
                     /*   if (task instanceof TimerTask) {
                            TimerTask timerTask = (TimerTask)task;
                            if (timerTask.canRunAgain()) {
                                addTask(task);
                            }
                        }*/
                    } catch (Exception e) {
                    }
                }
        }
    }
}
