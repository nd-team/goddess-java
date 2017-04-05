package com.bjike.goddess.qualifications.vo;

/**
 * 审核资料表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:44 ]
 * @Description: [ 审核资料表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AuditMaterialVO {

    /**
     * id
     */
    private String id;
    /**
     * 备案书
     */
    private String record;

    /**
     * 其他(附件)
     */
    private String other;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}