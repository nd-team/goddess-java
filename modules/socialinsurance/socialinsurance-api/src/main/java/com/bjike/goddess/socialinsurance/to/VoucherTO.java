package com.bjike.goddess.socialinsurance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.socialinsurance.enums.AuditStatus;
import com.bjike.goddess.socialinsurance.enums.CheckStatus;
import com.bjike.goddess.socialinsurance.enums.TransferStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 记账凭证生成
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-26 05:33 ]
 * @Description: [ 记账凭证生成 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VoucherTO extends BaseTO {
    public interface TestAdd {
    }
    public interface TestPost {
    }

    /**
     * id数组
     */
    @NotNull(groups = {VoucherTO.TestPost.class} , message = "ids数组不能为空")
    private String[] ids;

    /**
     * 凭证字(付/转/记/收)
     */
    @NotBlank(groups = {VoucherTO.TestAdd.class}, message = "凭证字不能为空")
    private String voucherWord;

    /**
     * 凭证字号
     */
    private Double voucherNum;

    /**
     * 凭证日期
     */
    @NotBlank(groups = {VoucherTO.TestAdd.class}, message = "凭证日期不能为空")
    private String voucherDate;

    /**
     * 一级科目
     */
    @NotNull(groups = {VoucherTO.TestAdd.class}, message = "一级科目不能为空")
    private List<String> firstSubjects;

    /**
     * 二级科目
     */
    private List<String> secondSubjects;

    /**
     * 三级科目
     */
    private List<String> thirdSubjects;

    /**
     * 借方金额且数据与贷方金额相反填如1-10
     */
//    @NotNull(groups = {VoucherTO.TestAdd.class}, message = "借方金额不能为空")
    private List<Double> borrowMoneys;

    /**
     * 贷方金额且数据与借方金额相反填如0-1
     */
//    @NotNull(groups = {VoucherTO.TestAdd.class}, message = "贷方金额不能为空")
    private List<Double> loanMoneys;

    /**
     * 摘要
     */
    @NotBlank(groups = {VoucherTO.TestAdd.class}, message = "摘要不能为空")
    private String sumary;

    /**
     * 来源
     */
    @NotBlank(groups = {VoucherTO.TestAdd.class}, message = "来源不能为空")
    private String source;

    /**
     * 地区
     */
    @NotBlank(groups = {VoucherTO.TestAdd.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目名称
     */
    @NotBlank(groups = {VoucherTO.TestAdd.class}, message = "项目名称不能为空")
    private String projectName;

    /**
     * 项目组
     */
    @NotBlank(groups = {VoucherTO.TestAdd.class}, message = "项目组不能为空")
    private String projectGroup;

    /**
     * 制单人
     */
    private String ticketer;

    /**
     * 票据数量
     */
    @NotNull(groups = {VoucherTO.TestAdd.class}, message = "票据数量不能为空")
    private Double ticketNum;

    /**
     * 附件
     */
    private String extraFile;

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 审核状态
     */
    private AuditStatus auditStatus;

    /**
     * 过帐状态
     */
    private TransferStatus transferStatus;

    /**
     * 结帐状态
     */
    private CheckStatus checkStatus;

    /**
     * 借贷金额合计
     */
    private Double moneyTotal;

    /**
     * 合计id
     */
    private String totalId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getVoucherWord() {
        return voucherWord;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public void setVoucherWord(String voucherWord) {
        this.voucherWord = voucherWord;
    }

    public Double getVoucherNum() {
        return voucherNum;
    }

    public void setVoucherNum(Double voucherNum) {
        this.voucherNum = voucherNum;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public List<String> getFirstSubjects() {
        return firstSubjects;
    }

    public void setFirstSubjects(List<String> firstSubjects) {
        this.firstSubjects = firstSubjects;
    }

    public List<String> getSecondSubjects() {
        return secondSubjects;
    }

    public void setSecondSubjects(List<String> secondSubjects) {
        this.secondSubjects = secondSubjects;
    }

    public List<String> getThirdSubjects() {
        return thirdSubjects;
    }

    public void setThirdSubjects(List<String> thirdSubjects) {
        this.thirdSubjects = thirdSubjects;
    }

    public List<Double> getBorrowMoneys() {
        return borrowMoneys;
    }

    public void setBorrowMoneys(List<Double> borrowMoneys) {
        this.borrowMoneys = borrowMoneys;
    }

    public List<Double> getLoanMoneys() {
        return loanMoneys;
    }

    public void setLoanMoneys(List<Double> loanMoneys) {
        this.loanMoneys = loanMoneys;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getTicketer() {
        return ticketer;
    }

    public void setTicketer(String ticketer) {
        this.ticketer = ticketer;
    }

    public Double getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Double ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getExtraFile() {
        return extraFile;
    }

    public void setExtraFile(String extraFile) {
        this.extraFile = extraFile;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Double getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(Double moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public String getTotalId() {
        return totalId;
    }

    public void setTotalId(String totalId) {
        this.totalId = totalId;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}