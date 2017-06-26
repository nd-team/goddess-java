package com.bjike.goddess.democraticmeet.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.util.List;

/**
 * 会议纪要业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:22 ]
 * @Description: [ 会议纪要业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummaryBO extends BaseBO {

    /**
     * 会议类型
     */
    private String typeName;

    /**
     * 会议编号
     */
    private String meetNumber;

    /**
     * 实际会议时间
     */
    private String actualTime;

    /**
     * 实际参会人员
     */
    private String actualPerson;

    /**
     * 新增参会人员
     */
    private String newPerson;

    /**
     * 未参会人员
     */
    private String nonePerson;

    /**
     * 会议记录人
     */
    private String recorder;

    /**
     * 参会人数
     */
    private Double personNum;

    /**
     * 自我批评的内容
     */
    private String selfCritic;

    /**
     * 自我总结
     */
    private String selfsummery;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 民主生活会议组织内容
     */
    private DemocraticContentBO democraticContentBO;

    /**
     * 建议数据数组
     */
    private List<AdviceTableBO> adviceTableBOList;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMeetNumber() {
        return meetNumber;
    }

    public void setMeetNumber(String meetNumber) {
        this.meetNumber = meetNumber;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getActualPerson() {
        return actualPerson;
    }

    public void setActualPerson(String actualPerson) {
        this.actualPerson = actualPerson;
    }

    public String getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(String newPerson) {
        this.newPerson = newPerson;
    }

    public String getNonePerson() {
        return nonePerson;
    }

    public void setNonePerson(String nonePerson) {
        this.nonePerson = nonePerson;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public Double getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Double personNum) {
        this.personNum = personNum;
    }

    public String getSelfCritic() {
        return selfCritic;
    }

    public void setSelfCritic(String selfCritic) {
        this.selfCritic = selfCritic;
    }

    public String getSelfsummery() {
        return selfsummery;
    }

    public void setSelfsummery(String selfsummery) {
        this.selfsummery = selfsummery;
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

    public DemocraticContentBO getDemocraticContentBO() {
        return democraticContentBO;
    }

    public void setDemocraticContentBO(DemocraticContentBO democraticContentBO) {
        this.democraticContentBO = democraticContentBO;
    }

    public List<AdviceTableBO> getAdviceTableBOList() {
        return adviceTableBOList;
    }

    public void setAdviceTableBOList(List<AdviceTableBO> adviceTableBOList) {
        this.adviceTableBOList = adviceTableBOList;
    }
}