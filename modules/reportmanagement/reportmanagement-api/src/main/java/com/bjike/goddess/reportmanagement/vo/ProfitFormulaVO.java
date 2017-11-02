package com.bjike.goddess.reportmanagement.vo;

/**
 * 利润增减率分析和变动分析表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-01 05:07 ]
 * @Description: [ 利润增减率分析和变动分析表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitFormulaVO {

    /**
     * id
     */
    private String id;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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