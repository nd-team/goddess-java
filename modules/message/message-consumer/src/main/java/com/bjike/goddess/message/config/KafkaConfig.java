package com.bjike.goddess.message.config;

import com.bjike.goddess.message.kafka.KafkaConsumer;
import com.bjike.goddess.message.mail.MailSer;
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
    private MailSer mailSer;
    @Autowired
    private Environment env;

    @Bean
    public KafkaConfig init() {
        KafkaConsumer.mailSer = mailSer;
        KafkaConsumer.env = env;
        return new KafkaConfig();
    }

    public MailSer getMailSer() {
        return mailSer;
    }

    public void setMailSer(MailSer mailSer) {
        this.mailSer = mailSer;
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }
}
