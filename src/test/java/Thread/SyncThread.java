package Thread;

/**
 * Created by Administrator on 2017/12/11.
 */
public class SyncThread implements  Runnable{
    private  int count;

    public SyncThread(){
        count=0;
    }


    @Override
    public void run() {
        synchronized (this){
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
      /*  SyncThread syncThread=new SyncThread();
        Thread thread1=new Thread(syncThread,"sync1");
        Thread thread2=new Thread(syncThread,"sync2");
        thread1.start();
        thread2.start();*/
        long distributeKey=System.currentTimeMillis();
        System.out.println("distributeKey==="+distributeKey/1000);
    }

}

