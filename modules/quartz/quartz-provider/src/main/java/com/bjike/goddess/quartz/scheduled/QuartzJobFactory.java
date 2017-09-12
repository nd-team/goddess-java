package com.bjike.goddess.quartz.scheduled;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.quartz.entity.ScheduleJob;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.*;


@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

    private static final Logger console = LoggerFactory.getLogger(QuartzJobFactory.class);
    private static Map<String, Class<?>> maps = new HashMap<String, Class<?>>();
    private static Set<String> ADDRESS_SET = new HashSet<>(0);

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
            String url = null;//Dubbo服务暴露的ip地址&端口
            boolean exists = false;
            for (String ad : ADDRESS_SET) { // 缓存地址获取
                if (ad.indexOf(scheduleJob.getClazz()) < 0) {
                    url = ad;
                    exists = true;
                    break;
                }
            }
            if (!exists) { //远程zookeeper获取
                ZkClient zk = new ZkClient("zookeeper.host.bjike.com:2181", 5000);
                List<String> url_list = zk.getChildren("/dubbo/" + scheduleJob.getClazz() + "/providers");
                zk.close();
                if(null!=url_list && url_list.size()>0){
                    for (String ul : url_list) {
                        if (ul.indexOf(scheduleJob.getClazz()) < 0) {
                            String realUrl = StringUtils.substringBefore(ul, "?");
                            if (!ADDRESS_SET.contains(realUrl)) {
                                ADDRESS_SET.add(realUrl);
                                url = realUrl;
                                break;
                            }
                        }
                    }
                }else {
                    throw new SerException(scheduleJob.getClazz() + "未注册");
                }


            }
            if (null != url) {
                ReferenceConfig referenceConfig = new ReferenceConfig();
                referenceConfig.setTimeout(10000);
                referenceConfig.setInterface(Class.forName(scheduleJob.getClazz()));
                referenceConfig.setUrl(url);
                Object obj = referenceConfig.get();
                Method clazzMethod = obj.getClass().getMethod(scheduleJob.getMethod());
                Object o = clazzMethod.invoke(obj);
            } else {
                throw new SerException(scheduleJob.getClazz() + "未注册");
            }

        } catch (ClassNotFoundException e) {
            console.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            console.error(e.getMessage());
        } catch (IllegalAccessException e) {
            console.error(e.getMessage());
        } catch (InvocationTargetException e) {
            console.error("Exception:" + e.getTargetException().getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        String str = "dubbo%3A%2F%2F45.32.18.228%3A5580%2Fcom.bjike.goddess.user.api.UserLoginLogAPI%3Fanyhost%3Dtrue%26application%3Duser-provider%26default.service.filter%3DuserFilter%26default.timeout%3D15000000%26dubbo%3D2.5.4-SNAPSHOT%26generic%3Dfalse%26interface%3Dcom.bjike.goddess.user.api.UserLoginLogAPI%26methods%3DfindByUserId%2Csave%26pid%3D22%26revision%3D1.0%26side%3Dprovider%26timestamp%3D1504753859974";
        str = URLDecoder.decode(str, "utf-8");
        System.out.println(str);
        str = StringUtils.substringBefore(str, "?");
        List<String> strings = new ArrayList<>();
        strings.add(str);
        System.out.println(strings.indexOf("UserLoginLogAPI") < 0);
    }

}