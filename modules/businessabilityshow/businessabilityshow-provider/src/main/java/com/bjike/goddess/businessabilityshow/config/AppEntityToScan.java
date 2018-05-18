package com.bjike.goddess.businessabilityshow.config;

import com.bjike.goddess.common.jpa.boot.EntityToScanImpl;
import org.springframework.stereotype.Component;

/**
 * 扫描entity
 *
 * @Author: [caiwenxian]
 * @Date: [2017-12-15 17:15]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class AppEntityToScan extends EntityToScanImpl<AppRoot> {

    @Override
    public String[] entityScan() {
        return new String[]{"com.bjike.goddess.businessabilityshow"};
    }
}
