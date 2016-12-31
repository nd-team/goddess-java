package com.bjike.goddess.dbs.jpa.boot.initializer;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description:[扫描Entity（实体类）实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class EntityToScanImpl implements EntityToScan {

    @Override
    public String[] entityScan() {
        return new String[0];
    }
}
