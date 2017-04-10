package com.bjike.goddess.intromanage.vo;

/**
 * 主业介绍表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:41 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MainBusinessIntroVO {

    /**
     * id
     */
    private String id;

    /**
     * 公司名称
     */
    private String firmId;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 项目科目
     */
    private String projectSubject;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getProjectSubject() {
        return projectSubject;
    }

    public void setProjectSubject(String projectSubject) {
        this.projectSubject = projectSubject;
    }
}