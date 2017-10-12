package com.bjike.goddess.task.quartz;

import com.bjike.goddess.task.service.CustomizeSer;

/**
 * 定时任务执行线程
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-26 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TaskRunner extends Thread {
    public static CustomizeSer customizeSer;
    private String name;//线程名
    private Thread t;
    private boolean suspend = false; //暂停

    public TaskRunner(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                if (suspend) {
                    try {
                        wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    customizeSer.executeTask();
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }

    }

    /**
     * 开始
     */
    public void start() {
        System.out.println("Starting Thread " + name);
        if (t == null) {
            t = new Thread(this, name);
            t.start();
        }
    }

    /**
     * 暂停
     */
    public void suspends() {
        suspend = true;
    }

    /**
     * 继续
     */
    public synchronized void resumes() {
        suspend = false;
        notify();
    }


}
