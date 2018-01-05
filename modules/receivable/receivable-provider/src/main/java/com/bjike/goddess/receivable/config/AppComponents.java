package com.bjike.goddess.receivable.config;

import com.bjike.goddess.common.jpa.boot.EntityToScan;
import com.bjike.goddess.common.jpa.boot.JpaComponents;
import org.springframework.stereotype.Component;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [entity扫描类]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class AppComponents extends JpaComponents implements EntityToScan<AppRoot> {
    @Override
    public String[] entityScan() {
        return new String[]{"com.bjike.goddess.receivable.entity"};
    }

}
