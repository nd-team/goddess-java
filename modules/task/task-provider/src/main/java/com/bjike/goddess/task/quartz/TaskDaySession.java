package com.bjike.goddess.task.quartz;

import com.bjike.goddess.common.api.exception.SerException;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 每天指定某时间执行任务缓存
 *
 * @Author: [liguiqin]
 * @Date: [2017-10-12 14:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TaskDaySession {
    private static Logger logger = LoggerFactory.getLogger(TaskDaySession.class);

    private static final RuntimeException ID_NOT_NULL = new RuntimeException("id不能为空");

    private static final LoadingCache<String, String> TASK_SESSION = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(2, TimeUnit.MINUTES) //2分钟销毁,保证一分钟内不被调用第二次
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    logger.info("remove:" + notification.getCause().name());
                }
            })
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return null;
                }
            });


    public static void put(String id, String time) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            TASK_SESSION.put(id, time);
        } else {
            throw ID_NOT_NULL;
        }
    }


    public static String get(String id) {
        try {
            if (StringUtils.isNotBlank(id)) {
                return TASK_SESSION.get(id);
            }
        } catch (Exception e) {
            return null;
        }

        throw ID_NOT_NULL;
    }
}
