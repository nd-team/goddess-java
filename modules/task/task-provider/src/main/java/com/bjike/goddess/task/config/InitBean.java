package com.bjike.goddess.task.config;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.quartz.TaskRunner;
import com.bjike.goddess.task.service.CustomizeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 初始化bean
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-26 09:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class InitBean {
    @Autowired
    private CustomizeSer customizeSer;

    @Bean
    public Object init() throws SerException {
        TaskRunner.customizeSer = customizeSer;
        return new Object();
    }
}
