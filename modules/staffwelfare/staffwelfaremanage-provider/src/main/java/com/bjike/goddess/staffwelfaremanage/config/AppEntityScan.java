package com.bjike.goddess.staffwelfaremanage.config;

import com.bjike.goddess.common.jpa.boot.EntityToScanImpl;
import org.springframework.stereotype.Component;

/**
 * Created by huanghuanlai on 2017/1/13.
 */
@Component
public class AppEntityScan extends EntityToScanImpl<AppRoot>{
    @Override
    public String[] entityScan() {
        return new String[]{"com.bjike.goddess.staffwelfaremanage"};
    }
}
