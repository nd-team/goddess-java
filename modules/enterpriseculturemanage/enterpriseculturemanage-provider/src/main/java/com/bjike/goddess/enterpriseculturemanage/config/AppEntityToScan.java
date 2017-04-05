package com.bjike.goddess.enterpriseculturemanage.config;

import com.bjike.goddess.common.jpa.boot.EntityToScanImpl;
import org.springframework.stereotype.Component;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Component
public class AppEntityToScan extends EntityToScanImpl<AppRoot> {
    @Override
    public String[] entityScan() {
        return new String[]{"com.bjike.goddess.enterpriseculturemanage", "com.bjike.goddess.customer"};
    }
}
