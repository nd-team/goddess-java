package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Set;

/**
 * 弹框考核指标业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-14 02:43 ]
 * @Description: [ 弹框考核指标业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AlertIndexBO extends BaseBO {

    /**
     * 考核指标名称
     */
    private String name;

    private Set<IndexContentBO> indexContentBOS;

    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Set<IndexContentBO> getIndexContentBOS() {
        return indexContentBOS;
    }

    public void setIndexContentBOS(Set<IndexContentBO> indexContentBOS) {
        this.indexContentBOS = indexContentBOS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}