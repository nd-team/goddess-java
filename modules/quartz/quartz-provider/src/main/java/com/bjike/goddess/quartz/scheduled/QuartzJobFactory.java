package com.bjike.goddess.quartz.scheduled;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.quartz.entity.ScheduleJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

    private static final Logger console = LoggerFactory.getLogger(QuartzJobFactory.class);
    private static Map<String, Class<?>> maps = new HashMap<String, Class<?>>();


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduledJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        console.info("执行任务：" + scheduledJob.getName());
        try {
            String className = scheduledJob.getClazz();

            Class<?> exec = null;
            if (maps.containsKey(className)) {
                exec = maps.get(className);
            } else {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                if (null == classLoader) {
                    classLoader = QuartzJobFactory.class.getClassLoader();
                }
                exec = classLoader.loadClass(className);
                maps.put(className, exec);
            }
            try {
                invokeDubbo(scheduledJob);

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void invokeDubbo(ScheduleJob scheduleJob) throws SerException {
        try {
            String url = "dubbo://" + scheduleJob.getAddress() + "/" + scheduleJob.getClazz();//Dubbo服务暴露的ip地址&端口
            ReferenceConfig referenceConfig = new ReferenceConfig();
            referenceConfig.setTimeout(10000);
            referenceConfig.setInterface(Class.forName(scheduleJob.getClazz()));
            referenceConfig.setUrl(url);
            Object obj = referenceConfig.get();
            Method clazzMethod = obj.getClass().getMethod(scheduleJob.getMethod());
            Object o = clazzMethod.invoke(obj);
        } catch (ClassNotFoundException e) {
            console.error(e.getMessage());
        }catch (NoSuchMethodException e){
            console.error(e.getMessage());
        }catch (IllegalAccessException e){
            console.error(e.getMessage());
        }catch (InvocationTargetException e){
            console.error("Exception:"+e.getTargetException().getMessage());
        }
    }
}