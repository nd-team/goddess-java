package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 利润增减率分析和变动分析业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-01 05:07 ]
 * @Description: [ 利润增减率分析和变动分析业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitFormulaBO extends BaseBO {

    /**
     * 分析名
     */
    private String project;

    /**
     * 说明
     */
    private String remark;

//    /**
//     * 分析类型
//     */
//    private Integer type;


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

//    public Integer getType() {
//        return type;
//    }
//
//    public void setType(Integer type) {
//        this.type = type;
//    }
}