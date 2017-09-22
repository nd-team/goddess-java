package com.bjike.goddess.salarymanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.salarymanage.enums.Probation;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
* 招聘面谈薪资确认记录业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-15 02:05 ]
* @Description:	[ 招聘面谈薪资确认记录业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryConfirmRecordTO extends BaseTO {

    /**
     * 技能
     */
    @NotBlank(message = "技能不能为空!", groups = {ADD.class, EDIT.class})
    private String  skill;

    /**
     * 入职前居住地
     */
    @NotBlank(message = "入职前居住地不能为空!", groups = {ADD.class, EDIT.class})
    private String  beforePlace;

    /**
     * 是否对工作地有要求
     */
    @NotNull(message = "是否对工作地有要求不能为空!", groups = {ADD.class, EDIT.class})
    private Boolean  ifAreaRequire;

    /**
     * 是否服从调动安排
     */
    @NotNull(message = "是否服从调动安排不能为空!", groups = {ADD.class, EDIT.class})
    private Boolean  ifAcceptRemove;

    /**
     * 试用期是多长时间
     */
    @NotNull(message = "试用期时长不能为空!", groups = {ADD.class, EDIT.class})
    private Probation  probation;

    /**
     * 入职前期望薪资是多少
     */
    @NotNull(message = "入职前期望薪资是多少不能为空!", groups = {ADD.class, EDIT.class})
    private Integer  expectSalary;

    /**
     * 面谈后同意确认试用期薪水是多少
     */
    @NotNull(message = "面谈后同意确认试用期薪水是多少不能为空!", groups = {ADD.class, EDIT.class})
    private Integer  probationSalary;

    /**
     * 面谈后同意确认转正后薪水是多少
     */
    @NotNull(message = "面谈后同意确认转正后薪水是多少不能为空!", groups = {ADD.class, EDIT.class})
    private Integer  officialSalary;

    /**
     * 是否同意超出个税起征点3500以上的金额，需提供相对应金额发票
     */
    @NotNull(message = "是否同意超出个税起征点3500以上的金额，需提供相对应金额发票不能为空!", groups = {ADD.class, EDIT.class})
    private Boolean  ifHandInvoice;

    /**
     * 是否需要公司安排住宿
     */
    @NotNull(message = "是否需要公司安排住宿不能为空！", groups = {ADD.class, EDIT.class})
    private Boolean  ifNeedStay;

    /**
     * 是否同意工作满三个月并且转正之后从第四个月开始购买社保
     */
    @NotNull(message = "是否同意工作满三个月并且转正之后从第四个月开始购买社保不能为空!", groups = {ADD.class, EDIT.class})
    private Boolean  ifAgreeSocial;

    /**
     * 紧急联络人
     */
    @NotBlank(message = "紧急联络人不能为空!", groups = {ADD.class, EDIT.class})
    private String  emergencyContact;

    /**
     * 紧急联系人电话
     */
    @NotBlank(message = "紧急联系人电话不能为空!", groups = {ADD.class, EDIT.class})
    private String  emergencyContactPhone;

    /**
     * 入职前从事的通信专业/工作时间
     */
    @NotNull(message = "入职前从事的通信专业/工作时间不能为空!", groups = {ADD.class, EDIT.class})
    private Integer  communicationDate;

    /**
     * 入职前从事的非通信专业/工作时间
     */
    @NotNull(message = "入职前从事的非通信不能为空!", groups = {ADD.class, EDIT.class})
    private Integer  noCommunicationDate;

    /**
     * 其他特殊约定
     */
    @NotBlank(message = "其他特殊约定不能为空!", groups = {ADD.class, EDIT.class})
    private String  speciaTerms;

    /**
     * 协办人
     */
    @NotBlank(message = "协办人不能为空!", groups = {ADD.class, EDIT.class})
    private String  coOrganiser;

    /**
     * 是否有面谈确认单原件
     */
    @NotNull(message = "是否有面谈确认单原件不能为空!", groups = {ADD.class, EDIT.class})
    private Boolean  ifConfirmationSlip;

    /**
     * 是否有住宿
     */
    @NotNull(message = "是否有住宿不能为空!", groups = {ADD.class, EDIT.class})
    private Boolean  ifStay;

    /**
     * 确认人
     */
    @NotBlank(message = "确认人不能为空!", groups = {ADD.class, EDIT.class})
    private String  confirmPeople;


    /**
     * 存档人
     */
    @NotBlank(message = "存档人不能为空!", groups = {ADD.class, EDIT.class})
    private String  archivePeople;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空!", groups = {ADD.class, EDIT.class})
    private String  status;




    public String getSkill () {
        return skill;
    }
    public void setSkill (String skill ) {
        this.skill = skill ;
    }
    public String getBeforePlace () {
        return beforePlace;
    }
    public void setBeforePlace (String beforePlace ) {
        this.beforePlace = beforePlace ;
    }
    public Boolean getIfAreaRequire () {
        return ifAreaRequire;
    }
    public void setIfAreaRequire (Boolean ifAreaRequire ) {
        this.ifAreaRequire = ifAreaRequire ;
    }
    public Boolean getIfAcceptRemove () {
        return ifAcceptRemove;
    }
    public void setIfAcceptRemove (Boolean ifAcceptRemove ) {
        this.ifAcceptRemove = ifAcceptRemove ;
    }
    public Probation getProbation () {
        return probation;
    }
    public void setProbation (Probation probation ) {
        this.probation = probation ;
    }
    public Integer getExpectSalary () {
        return expectSalary;
    }
    public void setExpectSalary (Integer expectSalary ) {
        this.expectSalary = expectSalary ;
    }
    public Integer getProbationSalary () {
        return probationSalary;
    }
    public void setProbationSalary (Integer probationSalary ) {
        this.probationSalary = probationSalary ;
    }
    public Integer getOfficialSalary () {
        return officialSalary;
    }
    public void setOfficialSalary (Integer officialSalary ) {
        this.officialSalary = officialSalary ;
    }
    public Boolean getIfHandInvoice () {
        return ifHandInvoice;
    }
    public void setIfHandInvoice (Boolean ifHandInvoice ) {
        this.ifHandInvoice = ifHandInvoice ;
    }
    public Boolean getIfNeedStay () {
        return ifNeedStay;
    }
    public void setIfNeedStay (Boolean ifNeedStay ) {
        this.ifNeedStay = ifNeedStay ;
    }
    public Boolean getIfAgreeSocial () {
        return ifAgreeSocial;
    }
    public void setIfAgreeSocial (Boolean ifAgreeSocial ) {
        this.ifAgreeSocial = ifAgreeSocial ;
    }
    public String getEmergencyContact () {
        return emergencyContact;
    }
    public void setEmergencyContact (String emergencyContact ) {
        this.emergencyContact = emergencyContact ;
    }
    public String getEmergencyContactPhone () {
        return emergencyContactPhone;
    }
    public void setEmergencyContactPhone (String emergencyContactPhone ) {
        this.emergencyContactPhone = emergencyContactPhone ;
    }
    public Integer getCommunicationDate () {
        return communicationDate;
    }
    public void setCommunicationDate (Integer communicationDate ) {
        this.communicationDate = communicationDate ;
    }
    public Integer getNoCommunicationDate () {
        return noCommunicationDate;
    }
    public void setNoCommunicationDate (Integer noCommunicationDate ) {
        this.noCommunicationDate = noCommunicationDate ;
    }
    public String getSpeciaTerms () {
        return speciaTerms;
    }
    public void setSpeciaTerms (String speciaTerms ) {
        this.speciaTerms = speciaTerms ;
    }
    public String getCoOrganiser () {
        return coOrganiser;
    }
    public void setCoOrganiser (String coOrganiser ) {
        this.coOrganiser = coOrganiser ;
    }
    public Boolean getIfConfirmationSlip () {
        return ifConfirmationSlip;
    }
    public void setIfConfirmationSlip (Boolean ifConfirmationSlip ) {
        this.ifConfirmationSlip = ifConfirmationSlip ;
    }
    public Boolean getIfStay () {
        return ifStay;
    }
    public void setIfStay (Boolean ifStay ) {
        this.ifStay = ifStay ;
    }
    public String getConfirmPeople () {
        return confirmPeople;
    }
    public void setConfirmPeople (String confirmPeople ) {
        this.confirmPeople = confirmPeople ;
    }
    public String getArchivePeople () {
        return archivePeople;
    }
    public void setArchivePeople (String archivePeople ) {
        this.archivePeople = archivePeople ;
    }
    public String getStatus () {
        return status;
    }
    public void setStatus (String status ) {
        this.status = status ;
    }
}