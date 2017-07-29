package com.bjike.goddess.contractcommunicat.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.contractcommunicat.enums.CollectSendUnit;
import com.bjike.goddess.contractcommunicat.enums.CollectType;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;

import java.util.List;
import java.util.Map;

/**
 * 项目商务洽谈邮件业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.877 ]
 * @Description: [ 项目商务洽谈邮件业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectEmailBO extends BaseBO {

    /**
     * 汇总类型
     */
    private String type;


    /**
     * 汇总条件
     */
    private String condi;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发送间隔
     */
    private Double sendNum;

    /**
     * 发送间隔和单位
     */
    private String sendNumAndUnit;

    /**
     * 发送单位
     */
    private CollectSendUnit collectSendUnit;

    /**
     * 汇总间隔
     */
    private QuartzCycleType collectUnit;

    /**
     * 发送对象
     */
    private String sendObject;

    /**
     * 发送对象数组
     */
    private List<String> sendObjectList;

    /**
     * 上次发送时间
     */
    private String lastSendTime;

    /**
     * 状态
     */
    private Status status;

    /**
     * 创建人
     */
    private String createPersion;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 汇总金额
     */
    private Double money;
    /**
     * 个数
     */
    private Integer counts;

    /**
     * 数据库枚举转换
     */
    private int enumConvert;

    /**
     * 合同外部项目名称
     */
    private String contractExtProject;

    /**
     * 合同外部编号
     */
    private String contractExtCode;

    /**
     * 内部项目名称
     */
    private String contractInProject;

    /**
     * 内部项目编号
     */
    private String contractInCode;

    /**
     * 外包项目名称
     */
    private String outsourcingProject;

    /**
     * 外包项目编号
     */
    private String outsourcingCode;

    /**
     * 项目外包信息
     */
    private String outsourcingInfo;

    /**
     * 洽谈轮次
     */
    private String communicateTimes;

    /**
     * 洽谈目的
     */
    private String communicateGoal;

    /**
     * 洽谈时间
     */
    private String communicateDate;

    /**
     * 洽谈人
     */
    private String communicateUser;

    /**
     * 洽谈对象
     */
    private String communicateObj;

    /**
     * 洽谈地址
     */
    private String communicateAddress;

    /**
     * 洽谈内容
     */
    private String communicateContent;

    /**
     * 费用预算
     */
    private Double costBudget;

    /**
     * 项目结果
     */
    private CommunicateResult projectResult;

    /**
     * 记录人
     */
    private String recordUser;

    //汇总返回字段
    private Integer totalCooperate;
    private Integer totalTrail;
    private Integer totalAbandon;

    //汇总返回字段
    private CommunicateResult cooperate;
    private CommunicateResult trail;
    private CommunicateResult abandon;


    public String getCondi() {
        return condi;
    }

    public void setCondi(String condi) {
        this.condi = condi;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getSendNum() {
        return sendNum;
    }

    public void setSendNum(Double sendNum) {
        this.sendNum = sendNum;
    }

    public String getSendNumAndUnit() {
        return sendNumAndUnit;
    }

    public void setSendNumAndUnit(String sendNumAndUnit) {
        this.sendNumAndUnit = sendNumAndUnit;
    }

    public CollectSendUnit getCollectSendUnit() {
        return collectSendUnit;
    }

    public void setCollectSendUnit(CollectSendUnit collectSendUnit) {
        this.collectSendUnit = collectSendUnit;
    }

    public QuartzCycleType getCollectUnit() {
        return collectUnit;
    }

    public void setCollectUnit(QuartzCycleType collectUnit) {
        this.collectUnit = collectUnit;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public List<String> getSendObjectList() {
        return sendObjectList;
    }

    public void setSendObjectList(List<String> sendObjectList) {
        this.sendObjectList = sendObjectList;
    }

    public String getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(String lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatePersion() {
        return createPersion;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
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

    public int getEnumConvert() {
        return enumConvert;
    }

    public void setEnumConvert(int enumConvert) {
        this.enumConvert = enumConvert;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getContractExtProject() {
        return contractExtProject;
    }

    public void setContractExtProject(String contractExtProject) {
        this.contractExtProject = contractExtProject;
    }

    public String getContractExtCode() {
        return contractExtCode;
    }

    public void setContractExtCode(String contractExtCode) {
        this.contractExtCode = contractExtCode;
    }

    public String getContractInProject() {
        return contractInProject;
    }

    public void setContractInProject(String contractInProject) {
        this.contractInProject = contractInProject;
    }

    public String getContractInCode() {
        return contractInCode;
    }

    public void setContractInCode(String contractInCode) {
        this.contractInCode = contractInCode;
    }

    public String getOutsourcingProject() {
        return outsourcingProject;
    }

    public void setOutsourcingProject(String outsourcingProject) {
        this.outsourcingProject = outsourcingProject;
    }

    public String getOutsourcingCode() {
        return outsourcingCode;
    }

    public void setOutsourcingCode(String outsourcingCode) {
        this.outsourcingCode = outsourcingCode;
    }

    public String getOutsourcingInfo() {
        return outsourcingInfo;
    }

    public void setOutsourcingInfo(String outsourcingInfo) {
        this.outsourcingInfo = outsourcingInfo;
    }

    public String getCommunicateTimes() {
        return communicateTimes;
    }

    public void setCommunicateTimes(String communicateTimes) {
        this.communicateTimes = communicateTimes;
    }

    public String getCommunicateGoal() {
        return communicateGoal;
    }

    public void setCommunicateGoal(String communicateGoal) {
        this.communicateGoal = communicateGoal;
    }

    public String getCommunicateDate() {
        return communicateDate;
    }

    public void setCommunicateDate(String communicateDate) {
        this.communicateDate = communicateDate;
    }

    public String getCommunicateUser() {
        return communicateUser;
    }

    public void setCommunicateUser(String communicateUser) {
        this.communicateUser = communicateUser;
    }

    public String getCommunicateObj() {
        return communicateObj;
    }

    public void setCommunicateObj(String communicateObj) {
        this.communicateObj = communicateObj;
    }

    public String getCommunicateAddress() {
        return communicateAddress;
    }

    public void setCommunicateAddress(String communicateAddress) {
        this.communicateAddress = communicateAddress;
    }

    public String getCommunicateContent() {
        return communicateContent;
    }

    public void setCommunicateContent(String communicateContent) {
        this.communicateContent = communicateContent;
    }

    public Double getCostBudget() {
        return costBudget;
    }

    public void setCostBudget(Double costBudget) {
        this.costBudget = costBudget;
    }

    public CommunicateResult getProjectResult() {
        return projectResult;
    }

    public void setProjectResult(CommunicateResult projectResult) {
        this.projectResult = projectResult;
    }

    public String getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
    }

    public Integer getTotalCooperate() {
        return totalCooperate;
    }

    public void setTotalCooperate(Integer totalCooperate) {
        this.totalCooperate = totalCooperate;
    }

    public Integer getTotalTrail() {
        return totalTrail;
    }

    public void setTotalTrail(Integer totalTrail) {
        this.totalTrail = totalTrail;
    }

    public Integer getTotalAbandon() {
        return totalAbandon;
    }

    public void setTotalAbandon(Integer totalAbandon) {
        this.totalAbandon = totalAbandon;
    }

    public CommunicateResult getCooperate() {
        return cooperate;
    }

    public void setCooperate(CommunicateResult cooperate) {
        this.cooperate = cooperate;
    }

    public CommunicateResult getTrail() {
        return trail;
    }

    public void setTrail(CommunicateResult trail) {
        this.trail = trail;
    }

    public CommunicateResult getAbandon() {
        return abandon;
    }

    public void setAbandon(CommunicateResult abandon) {
        this.abandon = abandon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}