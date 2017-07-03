package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.businessevaluate.enums.*;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目问题受理和处理
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 02:11 ]
 * @Description: [ 项目问题受理和处理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemDisposeTO extends BaseTO {

    public interface Qualitative{}
    public interface Ration{}

    /**
     * 来源
     */
    @NotBlank(message = "来源不能为空", groups = {ADD.class, EDIT.class})
    private String source;

    /**
     * 通知方式
     */
    @NotBlank(message = "通知方式不能为空", groups = {ADD.class, EDIT.class})
    private String informWay;

    /**
     * 影响部门
     */
    @NotBlank(message = "影响部门不能为空", groups = {ADD.class, EDIT.class})
    private String affectDepartment;

    /**
     * 时间紧急程度
     */
    @NotBlank(message = "时间紧急程度不能为空", groups = {ADD.class, EDIT.class})
    private String urgencyLevel;

    /**
     * 责任人员
     */
    @NotBlank(message = "责任人员不能为空", groups = {ADD.class, EDIT.class})
    private String dutyMan;

    /**
     * 处理人员
     */
    @NotBlank(message = "处理人员不能为空", groups = {ADD.class, EDIT.class})
    private String disposeMan;

    /**
     * 反应类型
     */
    @NotBlank(message = "反应类型不能为空", groups = {ADD.class, EDIT.class})
    private String responseType;

    /**
     * 详细情况
     */
    @NotBlank(message = "详细情况不能为空", groups = {ADD.class, EDIT.class})
    private String detail;

    /**
     * 受理开始时间
     */
    @NotBlank(message = "受理开始时间不能为空", groups = {ADD.class, EDIT.class})
    private String disposeStartTime;

    /**
     * 受理终止时间
     */
    @NotBlank(message = "受理终止时间不能为空", groups = {ADD.class, EDIT.class})
    private String disposeEndTime;

    /**
     * 经验总结
     */
    @NotBlank(message = "经验总结不能为空", groups = {ADD.class, EDIT.class})
    private String sufferSummary;

    /**
     * 项目信息Id
     */
    @NotBlank(message = "项目不能为空", groups = {ADD.class, EDIT.class})
    private String projectInfoId;

    /**
     * 影响模块
     */
    @NotBlank(message = "影响模块不能为空",groups = {ProblemDisposeTO.Qualitative.class})
    private String affectModule;

    /**
     * 规定完成时间
     */
    @NotNull(message = "影响模块不能为空",groups = {ProblemDisposeTO.Qualitative.class})
    private StipulateFinishTime stipulateFinishTime;

    /**
     * 影响部门数
     */
    @NotNull(message = "影响模块不能为空",groups = {ProblemDisposeTO.Qualitative.class})
    private AffectDeptAmount affectDeptAmount;

    /**
     * 问题受理难度
     */
    @NotNull(message = "影响模块不能为空",groups = {ProblemDisposeTO.Qualitative.class})
    private ProblemDifficulty problemDifficulty;

    /**
     * 是否按时完成
     */
    @NotNull(message = "影响模块不能为空",groups = {ProblemDisposeTO.Qualitative.class})
    private FinishOnTime finishOnTime;

    /**
     * 错误情况
     */
    @NotNull(message = "错误情况不能为空",groups = {ProblemDisposeTO.Ration.class})
    private ErrorCase errorCase;

    /**
     * 错误类型
     */
    @NotNull(message = "错误情况不能为空",groups = {ProblemDisposeTO.Ration.class})
    private ErrorType errorType;

    /**
     * 指标规定
     */
    @NotNull(message = "指标规定不能为空",groups = {ProblemDisposeTO.Ration.class})
    private KpiStipulate kpiStipulate;

    /**
     * 违约金确认单评分
     */
    @NotNull(message = "违约金确认单评分不能为空",groups = {ProblemDisposeTO.Ration.class})
    private Double grade;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getInformWay() {
        return informWay;
    }

    public void setInformWay(String informWay) {
        this.informWay = informWay;
    }

    public String getAffectDepartment() {
        return affectDepartment;
    }

    public void setAffectDepartment(String affectDepartment) {
        this.affectDepartment = affectDepartment;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public String getDutyMan() {
        return dutyMan;
    }

    public void setDutyMan(String dutyMan) {
        this.dutyMan = dutyMan;
    }

    public String getDisposeMan() {
        return disposeMan;
    }

    public void setDisposeMan(String disposeMan) {
        this.disposeMan = disposeMan;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDisposeStartTime() {
        return disposeStartTime;
    }

    public void setDisposeStartTime(String disposeStartTime) {
        this.disposeStartTime = disposeStartTime;
    }

    public String getDisposeEndTime() {
        return disposeEndTime;
    }

    public void setDisposeEndTime(String disposeEndTime) {
        this.disposeEndTime = disposeEndTime;
    }

    public String getSufferSummary() {
        return sufferSummary;
    }

    public void setSufferSummary(String sufferSummary) {
        this.sufferSummary = sufferSummary;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getAffectModule() {
        return affectModule;
    }

    public void setAffectModule(String affectModule) {
        this.affectModule = affectModule;
    }

    public StipulateFinishTime getStipulateFinishTime() {
        return stipulateFinishTime;
    }

    public void setStipulateFinishTime(StipulateFinishTime stipulateFinishTime) {
        this.stipulateFinishTime = stipulateFinishTime;
    }

    public AffectDeptAmount getAffectDeptAmount() {
        return affectDeptAmount;
    }

    public void setAffectDeptAmount(AffectDeptAmount affectDeptAmount) {
        this.affectDeptAmount = affectDeptAmount;
    }

    public ProblemDifficulty getProblemDifficulty() {
        return problemDifficulty;
    }

    public void setProblemDifficulty(ProblemDifficulty problemDifficulty) {
        this.problemDifficulty = problemDifficulty;
    }

    public FinishOnTime getFinishOnTime() {
        return finishOnTime;
    }

    public void setFinishOnTime(FinishOnTime finishOnTime) {
        this.finishOnTime = finishOnTime;
    }

    public ErrorCase getErrorCase() {
        return errorCase;
    }

    public void setErrorCase(ErrorCase errorCase) {
        this.errorCase = errorCase;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public KpiStipulate getKpiStipulate() {
        return kpiStipulate;
    }

    public void setKpiStipulate(KpiStipulate kpiStipulate) {
        this.kpiStipulate = kpiStipulate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}