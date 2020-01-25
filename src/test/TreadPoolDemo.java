package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TreadPoolDemo {
    public static void main(String[] args) {
        /**
         *
         corePoolSize 核心线程池大小
         maximumPoolSize 线程池最大容量大小
         keepAliveTime 线程池空闲时，线程存活的时间
         TimeUnit 时间单位
         ThreadFactory 线程工厂
         BlockingQueue任务队列
         RejectedExecutionHandler 线程拒绝策略

         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 3000, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(50));

        for (int i = 0; i < 3000; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}


class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + taskNum);
//        for (int j = step*i; j < step*(i + 1); j++){
//
//        }
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + "执行完毕");
    }
}
