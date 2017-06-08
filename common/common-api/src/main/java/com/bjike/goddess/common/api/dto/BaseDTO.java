package com.bjike.goddess.common.api.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
    /** js传递前encodeURI(url)
     * 类搜索条件Json {"field":"name","restrict":"EQ","value":"liguiqin"}
     * 多条件Json [{"field":"name","restrict":"EQ","value":"liguiqin"},{"field":"name","restrict":"IN","value":["liguiqin1","liguiqin2"]}]
     */
    protected String conditionsJson;

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

    public String getConditionsJson() {
        return conditionsJson;
    }

    public void setConditionsJson(String conditionsJson) {
        if (null != conditionsJson) {
            try {
                if (conditionsJson.indexOf("[{") != -1 && conditionsJson.lastIndexOf("}]") != -1) {
                    conditions = JSON.parseArray(conditionsJson, Condition.class);
                } else {
                    conditions = Arrays.asList(JSON.parseObject(conditionsJson, Condition.class));
                }
            } catch (Exception e) {
                throw new RuntimeException("条件json转换错误");
            }
        }
    }

}
