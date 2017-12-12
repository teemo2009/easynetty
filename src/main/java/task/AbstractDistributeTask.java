package task;

/**
 * Created by Administrator on 2017/12/12.
 */
public abstract class AbstractDistributeTask implements  IDistributeTask {

    /** thread distribute key  一个任务的key值  具体用于 负载均衡*/
    protected int distributeKey;
    /** startTime when task begin */
    private long startMillis;
    /** end time when task finish */
    private long endMillis;

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public int distributeKey() {
        return distributeKey;
    }

    public long getStartMillis() {
        return startMillis;
    }

    public void markStartMillis() {
        this.startMillis = System.currentTimeMillis();
    }

    public long getEndMillis() {
        return endMillis;
    }

    public void markEndMillis() {
        this.endMillis = System.currentTimeMillis();
    }

}
