package com.bjike.goddess.ticket.config;

import com.bjike.goddess.common.jpa.boot.JpaComponents;
import com.bjike.goddess.common.jpa.boot.EntityToScan;
import org.springframework.stereotype.Component;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [entity扫描类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Component
public class AppComponents extends JpaComponents implements EntityToScan<AppRoot> {

    @Override
    public String[] entityScan() {
        return new String[]{"com.bjike.goddess.ticket.entity"};
    }
}
