package com.bjike.goddess.common.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * data transfer object
 * 基础数据传输，所有dto继承该类
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public abstract class BaseDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = -3558525794931360478L;
    /**
     * 排序字段 (有排序字段默认排序) "username=desc" 不指定 username (默认使用desc)
     */
    protected List<String> sorts = new ArrayList<>(0);
    /**
     * 类搜索条件
     */
    protected List<Condition> conditions = new ArrayList<Condition>(0);

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<String> getSorts() {
        return sorts;
    }

    public void setSorts(List<String> sorts) {
        this.sorts = sorts;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
