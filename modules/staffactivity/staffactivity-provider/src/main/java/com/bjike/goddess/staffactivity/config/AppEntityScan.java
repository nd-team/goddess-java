package com.bjike.goddess.staffactivity.config;

import com.bjike.goddess.common.jpa.boot.EntityToScanImpl;
import org.springframework.stereotype.Component;

/**
 * Created by sunfengtao on 2017/1/13.
 */
@Component
public class AppEntityScan extends EntityToScanImpl<AppRoot> {
    @Override
    public String[] entityScan() {
        return new String[]{"com.bjike.goddess.staffactivity.entity","com.bjike.goddess.user.entity","com.bjike.goddess.storage.entity"};
    }
}
