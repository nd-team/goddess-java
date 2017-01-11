package com.bjike.goddess.user.register.boot;

import com.bjike.goddess.dbs.jpa.boot.initializer.Components;
import com.bjike.goddess.dbs.jpa.boot.initializer.EntityToScan;
import org.springframework.stereotype.Component;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 扫描依赖jpa com.bjike.goddess.card.entity]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Component
public class MyComponents extends Components implements EntityToScan {

    @Override
    public String[] entityScan() {
        return new String[]{"com.bjike.goddess.user.entity"};
    }
}
