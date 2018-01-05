package com.bjike.goddess.rentcar.config;

import com.bjike.goddess.common.jpa.boot.EntityToScanImpl;
import org.springframework.stereotype.Component;

/**
 * @Author: [sunft]
 * @Date: [2017-01-11 17:15]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class AppEntityToScan extends EntityToScanImpl<AppRoot> {

    @Override
    public String[] entityScan() {
        return new String[]{"com.bjike.goddess.rentcar"};
    }
}
