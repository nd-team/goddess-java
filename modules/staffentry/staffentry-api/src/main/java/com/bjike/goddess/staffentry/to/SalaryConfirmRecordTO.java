package com.bjike.goddess.staffentry.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 薪资确认业务传输数据
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 17:25]
 * @Description: [薪资确认业务传输数据]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SalaryConfirmRecordTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 姓名
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "姓名不能为空")
    private String name;
    /**
     * 员工编号
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "员工编号不能为空")
    private String employeeID;
    /**
     * 籍贯
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "籍贯不能为空")
    private String nativePlace;
    /**
     * 办理入职时间，格式是日期
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "办理入职时间不能为空,格式日期2017-01-01")
    private String entryTime;
    /**
     * 入职前居住地
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "入职前居住地不能为空")
    private String beforeResidence;
    /**
     * 是否对工作地有要求
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "是否对工作地有要求不能为空")
    private String workPlaceRequest;
    /**
     * 试用期是多长时间
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "试用期是多长时间不能为空")
    private String probationDuration;
    /**
     * 期望薪资
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "期望薪资不能为空")
    private String expectSalary;
    /**
     * 试用期薪资
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "试用期薪资不能为空")
    private String probationSalary;
    /**
     * 转正后薪资
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "转正后薪资不能为空")
    private String officialSalary;
    /**
     * 是否同意征税(true/false)
     */
    @NotNull(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "是否同意征税不能为空(true/false)")
    private Boolean whetherTax;
    /**
     * 是否需要公司安排住宿(true/false)
     */
    @NotNull(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "是否需要公司安排住宿不能为空(true/false)")
    private Boolean whetherArrangeResidence;
    /**
     * 是否购买社保(true/false)
     */
    @NotNull(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "是否购买社保不能为空(true/false)")
    private Boolean whetherSocialSecurity;
    /**
     * 紧急联络人
     */
    private String emergencyContract;
    /**
     * 紧急联络人电话
     */
    private String contractPhone;
    /**
     * 从事通信工作时长
     */
    private String communicationWorkDuration;
    /**
     * 从事非通信工作时长
     */
    private String notCommunicationWorkDuration;
    /**
     * 其他特殊要求
     */
    private String otherSpecialNeeds;
    /**
     * 协办人
     */
    private String assistant;
    /**
     * 是否有面谈确认单原件(true/false)
     */
    @NotNull(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "是否有面谈确认单原件不能为空(true/false)")
    private Boolean whetherVoucher;
    /**
     * 是否有住宿(true/false)
     */
    @NotNull(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "是否有住宿不能为空(true/false)")
    private Boolean whetherAccommodation;
    /**
     * 确认人
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "确认人不能为空")
    private String confirmPerson;
    /**
     * 存档人
     */
    @NotBlank(groups = {SalaryConfirmRecordTO.TestAdd.class}, message = "存档人不能为空")
    private String archivePerson;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getBeforeResidence() {
        return beforeResidence;
    }

    public void setBeforeResidence(String beforeResidence) {
        this.beforeResidence = beforeResidence;
    }

    public String getWorkPlaceRequest() {
        return workPlaceRequest;
    }

    public void setWorkPlaceRequest(String workPlaceRequest) {
        this.workPlaceRequest = workPlaceRequest;
    }

    public String getProbationDuration() {
        return probationDuration;
    }

    public void setProbationDuration(String probationDuration) {
        this.probationDuration = probationDuration;
    }

    public String getExpectSalary() {
        return expectSalary;
    }

    public void setExpectSalary(String expectSalary) {
        this.expectSalary = expectSalary;
    }

    public String getProbationSalary() {
        return probationSalary;
    }

    public void setProbationSalary(String probationSalary) {
        this.probationSalary = probationSalary;
    }

    public String getOfficialSalary() {
        return officialSalary;
    }

    public void setOfficialSalary(String officialSalary) {
        this.officialSalary = officialSalary;
    }

    public Boolean getWhetherTax() {
        return whetherTax;
    }

    public void setWhetherTax(Boolean whetherTax) {
        this.whetherTax = whetherTax;
    }

    public Boolean getWhetherArrangeResidence() {
        return whetherArrangeResidence;
    }

    public void setWhetherArrangeResidence(Boolean whetherArrangeResidence) {
        this.whetherArrangeResidence = whetherArrangeResidence;
    }

    public Boolean getWhetherSocialSecurity() {
        return whetherSocialSecurity;
    }

    public void setWhetherSocialSecurity(Boolean whetherSocialSecurity) {
        this.whetherSocialSecurity = whetherSocialSecurity;
    }

    public String getEmergencyContract() {
        return emergencyContract;
    }

    public void setEmergencyContract(String emergencyContract) {
        this.emergencyContract = emergencyContract;
    }

    public String getContractPhone() {
        return contractPhone;
    }

    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
    }

    public String getCommunicationWorkDuration() {
        return communicationWorkDuration;
    }

    public void setCommunicationWorkDuration(String communicationWorkDuration) {
        this.communicationWorkDuration = communicationWorkDuration;
    }

    public String getNotCommunicationWorkDuration() {
        return notCommunicationWorkDuration;
    }

    public void setNotCommunicationWorkDuration(String notCommunicationWorkDuration) {
        this.notCommunicationWorkDuration = notCommunicationWorkDuration;
    }

    public String getOtherSpecialNeeds() {
        return otherSpecialNeeds;
    }

    public void setOtherSpecialNeeds(String otherSpecialNeeds) {
        this.otherSpecialNeeds = otherSpecialNeeds;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public Boolean getWhetherVoucher() {
        return whetherVoucher;
    }

    public void setWhetherVoucher(Boolean whetherVoucher) {
        this.whetherVoucher = whetherVoucher;
    }

    public Boolean getWhetherAccommodation() {
        return whetherAccommodation;
    }

    public void setWhetherAccommodation(Boolean whetherAccommodation) {
        this.whetherAccommodation = whetherAccommodation;
    }

    public String getConfirmPerson() {
        return confirmPerson;
    }

    public void setConfirmPerson(String confirmPerson) {
        this.confirmPerson = confirmPerson;
    }

    public String getArchivePerson() {
        return archivePerson;
    }

    public void setArchivePerson(String archivePerson) {
        this.archivePerson = archivePerson;
    }

}
