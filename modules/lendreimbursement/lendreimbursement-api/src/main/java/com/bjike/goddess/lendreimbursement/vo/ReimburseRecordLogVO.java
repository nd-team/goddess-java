package com.bjike.goddess.lendreimbursement.vo;

/**
 * 报销记录日志表表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:48 ]
 * @Description: [ 报销记录日志表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimburseRecordLogVO {

    /**
     * id
     */
    private String id;
    /**
     * 修改人
     */
    private String userName;

    /**
     * 修改人编号
     */
    private String empNum;

    /**
     * 修改人职位
     */
    private String position;

    /**
     * 修改内容
     */
    private String content;

    /**
     * 报销记录Id
     */
    private String reimrecordId;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReimrecordId() {
        return reimrecordId;
    }

    public void setReimrecordId(String reimrecordId) {
        this.reimrecordId = reimrecordId;
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