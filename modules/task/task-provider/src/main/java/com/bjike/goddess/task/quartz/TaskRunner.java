package com.bjike.goddess.task.quartz;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.service.CustomizeSer;

/**
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
    private boolean suspend=false; //暂停
    private boolean update; //更新缓存

    public TaskRunner(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (true) {
                    if(suspend){
                        wait();
                    }
                    customizeSer.executeTask();
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + name + " interrupted.");
            e.printStackTrace();
        }catch (SerException e){
            e.printStackTrace();
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
