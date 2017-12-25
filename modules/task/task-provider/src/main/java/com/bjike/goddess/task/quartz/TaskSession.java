package com.bjike.goddess.task.quartz;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.entity.Customize;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**任务定时缓存
 * @Author: [liguiqin]
 * @Date: [2017-04-12 09:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public final class TaskSession {
    private static Logger logger = LoggerFactory.getLogger(TaskSession.class);

    private TaskSession() {
    }

    private static final RuntimeException ID_NOT_NULL = new RuntimeException("id不能为空");

    private static final LoadingCache<String, Customize> TASK_SESSION = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, Customize>() {
                @Override
                public void onRemoval(RemovalNotification<String, Customize> notification) {
                    logger.info("remove:" + notification.getCause().name());
                }
            })
            .build(new CacheLoader<String, Customize>() {
                @Override
                public Customize load(String key) throws Exception {
                    return null;
                }
            });


    public static void put(String id, Customize customize) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            TASK_SESSION.put(id, customize);
        } else {
            throw ID_NOT_NULL;
        }
    }


    public static void remove(String id) {
        if (StringUtils.isNotBlank(id)) {
            TASK_SESSION.invalidate(id);
        } else {

            throw ID_NOT_NULL;
        }
    }


    public static Customize get(String id) {
        try {
            if (StringUtils.isNotBlank(id)) {
                return TASK_SESSION.get(id);
            }
        } catch (Exception e) {
            return null;
        }

        throw ID_NOT_NULL;
    }
    public static LoadingCache<String, Customize> sessions() {
        if (null != TASK_SESSION && TASK_SESSION.size() > 0) {
            return TASK_SESSION;
        }
        return null;
    }

}
