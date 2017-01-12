package com.bjike.goddess.common.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [基础数据传输，所有dto继承该类]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BaseDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = -3558525794931360478L;
    private Map<String,String> sorts = new HashMap<>(0); //排序字段 (有排序字段默认排序) "username" "desc"
    private List<Condition> conditions = new ArrayList<Condition>(0);// 类搜索条件

    public Map<String, String> getSorts() {
        return sorts;
    }

    public void setSorts(Map<String, String> sorts) {
        this.sorts = sorts;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
