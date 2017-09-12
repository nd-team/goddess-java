package com.bjike.goddess.quartz.session;

import com.bjike.goddess.common.api.exception.SerException;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-12 09:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public final class APISession {
    private static Logger logger = LoggerFactory.getLogger(APISession.class);

    private APISession() {
    }

    private static final RuntimeException API_NOT_NULL = new RuntimeException("api不能为空");

    private static final LoadingCache<String, String> API_SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.MINUTES)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    logger.info("remove:" + notification.getCause().name());
                }
            })
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String clazz) throws Exception {
                    return null;
                }
            });


    public static void put(String clazz, String url) throws SerException {
        if (StringUtils.isNotBlank(clazz)) {
            API_SESSION.put(clazz, url);
        } else {
            throw API_NOT_NULL;
        }
    }


    public static void remove(String clazz) {
        if (StringUtils.isNotBlank(clazz)) {
            API_SESSION.invalidate(clazz);
        } else {

            throw API_NOT_NULL;
        }
    }


    public static String get(String clazz) {
        try {
            if (StringUtils.isNotBlank(clazz)) {
                return API_SESSION.get(clazz);
            }
        } catch (Exception e) {
            return null;
        }

        throw API_NOT_NULL;
    }


}
