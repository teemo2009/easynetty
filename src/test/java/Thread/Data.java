package Thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/12/11.
 */
public class Data {

    private  int data;
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();


    public void setData(int data) {
        readWriteLock.writeLock().lock();
        try {
            Thread.sleep(20);
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);
        }catch (Exception ex){
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void getData(){
        readWriteLock.readLock().lock();
        try {
            Thread.sleep(20);
            System.out.println(Thread.currentThread().getName()+"读取"+this.data);
        }catch (Exception e){
        }finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
       final Data myData=new Data();
        //写入
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        myData.setData(j);
                    }
                }
            });
            t.setName("Thread-W" + i);
            t.start();
        }
        //读取
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        myData.getData();
                    }
                }
            });
            t.setName("Thread-R" + i);
            t.start();
        }
    }
}
