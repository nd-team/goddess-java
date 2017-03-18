package message_code;

import com.bjike.goddess.common.jpa.boot.EntityToScan;
import com.bjike.goddess.message.config.AppRoot;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [测试配置项]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.bjike.goddess.message.dao"})//jpa 所在包
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@PropertySource({"classpath:config.properties"})
@ImportResource({"classpath:application.xml"})
@ComponentScan(basePackages = {"com.bjike.goddess.redis","com.bjike.goddess.message","message_code"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class AppConfig {

}

