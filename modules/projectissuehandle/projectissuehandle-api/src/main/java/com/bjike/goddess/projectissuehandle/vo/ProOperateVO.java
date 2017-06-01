package com.bjike.goddess.projectissuehandle.vo;

/**
 * 问题权限配置表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 05:43 ]
 * @Description: [ 问题权限配置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProOperateVO {

    /**
     * id
     */
    private String id;



    /**
     * 操作对象
     */
    private String operator;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}