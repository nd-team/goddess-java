package com.bjike.goddess.balancecard.vo;

/**
 * 考核方式设置表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 考核方式设置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExamWaySetVO {

    /**
     * id
     */
    private String id;
    /**
     * 考核方式名称
     */
    private String typeName;

    /**
     * 描述
     */
    private String describtion;

    /**
     * 考核方式添加人
     */
    private String person;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}