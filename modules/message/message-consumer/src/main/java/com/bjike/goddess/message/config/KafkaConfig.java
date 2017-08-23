package com.bjike.goddess.message.config;

import com.bjike.goddess.message.api.EmailAPI;
import com.bjike.goddess.message.kafka.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-15 11:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class KafkaConfig {
    @Autowired
    private EmailAPI emailAPI;
    @Autowired
    private Environment env;

    @Bean
    public KafkaConfig init() {
        KafkaConsumer.emailAPI = emailAPI;
        KafkaConsumer.env = env;
        return new KafkaConfig();
    }

    public EmailAPI getEmailAPI() {
        return emailAPI;
    }

    public void setEmailAPI(EmailAPI emailAPI) {
        this.emailAPI = emailAPI;
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }
}
