package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.Set;

/**
 * 考核指标业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:26 ]
 * @Description: [ 考核指标业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CheckIndexBO extends BaseBO {

    /**
     * 考核名称
     */
    private String checkName;

    /**
     * 对赌标签
     */
    private Set<LabelOGBO> labelOGBOS;


    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public Set<LabelOGBO> getLabelOGBOS() {
        return labelOGBOS;
    }

    public void setLabelOGBOS(Set<LabelOGBO> labelOGBOS) {
        this.labelOGBOS = labelOGBOS;
    }
}