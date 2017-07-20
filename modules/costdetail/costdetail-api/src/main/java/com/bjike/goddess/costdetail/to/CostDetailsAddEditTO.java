package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 查看成本明细详情
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 查看成本明细详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostDetailsAddEditTO extends BaseTO {

    /**
     * 日期
     */
    @NotBlank(groups = {ADD.class}, message = "日期不能为空")
    private String costTime;

    /**
     * 部门
     */
    @NotBlank(groups = {ADD.class}, message = "部门不能为空")
    private String department;

    /**
     * 劳务成本
     */
    private CostLaborMoneyTO costLaborMoneyTO;

    /**
     * 劳务成本明细list
     */
    private List<LaborCostDetailTO> laborCostDetailTOList;

    /**
     * 应交税金合计
     */
    private CostPayeMoneyTO costPayeMoneyTO;

    /**
     * 公司借入合计
     */
    private CostBorrowMoneyTO costBorrowMoneyTO;

    /**
     * 公司借入明细list
     */
    private List<CompanyBorrowedDetailTO> companyBorrowedDetailTOList;

    /**
     * 实收资本合计
     */
    private CostPaidMoneyTO costPaidMoneyTO;

    /**
     * 实收资本明细list
     */
    private List<PaidCapitalDetailTO> paidCapitalDetailTOList;

    /**
     * 公司借出合计
     */
    private CostLendMoneyTO costLendMoneyTO;

    /**
     * 公司借出明细list
     */
    private List<CompanyLendDetailTO> companyLendDetailTOList;

    /**
     * 主营业务收入
     */
    private CostIncomeMoneyTO costIncomeMoneyTO;

    /**
     * 主营业务收入明细list
     */
    private List<BusinessIncomeDetailTO> businessIncomeDetailTOList;

    /**
     * 初期余额
     */
    private CostBeginMoneyTO costBeginMoneyTO;

    /**
     * 营业成本
     */
    private CostBusinessMoneyTO costBusinessMoneyTO;

    /**
     * 预估应收账款
     */
    private CostForeMoneyTO costForeMoneyTO;

    /**
     * 实际资金缺口
     */
    private CostActualMoneyTO costActualMoneyTO;

    /**
     * 按时回款预估结余资金
     */
    private CostBalanceMoneyTO costBalanceMoneyTO;


    public CostBusinessMoneyTO getCostBusinessMoneyTO() {
        return costBusinessMoneyTO;
    }

    public void setCostBusinessMoneyTO(CostBusinessMoneyTO costBusinessMoneyTO) {
        this.costBusinessMoneyTO = costBusinessMoneyTO;
    }

    public CostLaborMoneyTO getCostLaborMoneyTO() {
        return costLaborMoneyTO;
    }

    public void setCostLaborMoneyTO(CostLaborMoneyTO costLaborMoneyTO) {
        this.costLaborMoneyTO = costLaborMoneyTO;
    }

    public CostPayeMoneyTO getCostPayeMoneyTO() {
        return costPayeMoneyTO;
    }

    public void setCostPayeMoneyTO(CostPayeMoneyTO costPayeMoneyTO) {
        this.costPayeMoneyTO = costPayeMoneyTO;
    }

    public CostBorrowMoneyTO getCostBorrowMoneyTO() {
        return costBorrowMoneyTO;
    }

    public void setCostBorrowMoneyTO(CostBorrowMoneyTO costBorrowMoneyTO) {
        this.costBorrowMoneyTO = costBorrowMoneyTO;
    }

    public CostPaidMoneyTO getCostPaidMoneyTO() {
        return costPaidMoneyTO;
    }

    public void setCostPaidMoneyTO(CostPaidMoneyTO costPaidMoneyTO) {
        this.costPaidMoneyTO = costPaidMoneyTO;
    }

    public CostLendMoneyTO getCostLendMoneyTO() {
        return costLendMoneyTO;
    }

    public void setCostLendMoneyTO(CostLendMoneyTO costLendMoneyTO) {
        this.costLendMoneyTO = costLendMoneyTO;
    }

    public CostIncomeMoneyTO getCostIncomeMoneyTO() {
        return costIncomeMoneyTO;
    }

    public void setCostIncomeMoneyTO(CostIncomeMoneyTO costIncomeMoneyTO) {
        this.costIncomeMoneyTO = costIncomeMoneyTO;
    }

    public CostForeMoneyTO getCostForeMoneyTO() {
        return costForeMoneyTO;
    }

    public void setCostForeMoneyTO(CostForeMoneyTO costForeMoneyTO) {
        this.costForeMoneyTO = costForeMoneyTO;
    }

    public CostActualMoneyTO getCostActualMoneyTO() {
        return costActualMoneyTO;
    }

    public void setCostActualMoneyTO(CostActualMoneyTO costActualMoneyTO) {
        this.costActualMoneyTO = costActualMoneyTO;
    }

    public CostBalanceMoneyTO getCostBalanceMoneyTO() {
        return costBalanceMoneyTO;
    }

    public void setCostBalanceMoneyTO(CostBalanceMoneyTO costBalanceMoneyTO) {
        this.costBalanceMoneyTO = costBalanceMoneyTO;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public CostBeginMoneyTO getCostBeginMoneyTO() {
        return costBeginMoneyTO;
    }

    public void setCostBeginMoneyTO(CostBeginMoneyTO costBeginMoneyTO) {
        this.costBeginMoneyTO = costBeginMoneyTO;
    }

    public List<LaborCostDetailTO> getLaborCostDetailTOList() {
        return laborCostDetailTOList;
    }

    public void setLaborCostDetailTOList(List<LaborCostDetailTO> laborCostDetailTOList) {
        this.laborCostDetailTOList = laborCostDetailTOList;
    }

    public List<CompanyBorrowedDetailTO> getCompanyBorrowedDetailTOList() {
        return companyBorrowedDetailTOList;
    }

    public void setCompanyBorrowedDetailTOList(List<CompanyBorrowedDetailTO> companyBorrowedDetailTOList) {
        this.companyBorrowedDetailTOList = companyBorrowedDetailTOList;
    }

    public List<PaidCapitalDetailTO> getPaidCapitalDetailTOList() {
        return paidCapitalDetailTOList;
    }

    public void setPaidCapitalDetailTOList(List<PaidCapitalDetailTO> paidCapitalDetailTOList) {
        this.paidCapitalDetailTOList = paidCapitalDetailTOList;
    }

    public List<CompanyLendDetailTO> getCompanyLendDetailTOList() {
        return companyLendDetailTOList;
    }

    public void setCompanyLendDetailTOList(List<CompanyLendDetailTO> companyLendDetailTOList) {
        this.companyLendDetailTOList = companyLendDetailTOList;
    }

    public List<BusinessIncomeDetailTO> getBusinessIncomeDetailTOList() {
        return businessIncomeDetailTOList;
    }

    public void setBusinessIncomeDetailTOList(List<BusinessIncomeDetailTO> businessIncomeDetailTOList) {
        this.businessIncomeDetailTOList = businessIncomeDetailTOList;
    }
}