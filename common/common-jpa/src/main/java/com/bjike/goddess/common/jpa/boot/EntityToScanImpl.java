package com.bjike.goddess.common.jpa.boot;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description:[扫描Entity（实体类）实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EntityToScanImpl<T> implements EntityToScan {
    protected String packageName;

    public EntityToScanImpl(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        packageName = ((Class<T>) params[0]).getPackage().toString();
        packageName = packageName.substring(8,packageName.lastIndexOf("."));
    }

    @Override
    public String[] entityScan() {

        return new String[]{packageName+".entity"};
    }
}
