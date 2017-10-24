package com.bjike.goddess.foreigntax.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 进项发票
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:45 ]
 * @Description: [ 进项发票 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IncomeInvoiceTO extends BaseTO {

    /**
     * 开票时间
     */
    @NotBlank(message = "开票时间不能为空",groups = {ADD.class, EDIT.class})
    private String invoicingTime;

    /**
     * 公司
     */
    @NotBlank(message = "公司不能为空",groups = {ADD.class, EDIT.class})
    private String company;

    /**
     * 税号
     */
    @NotBlank(message = "税号不能为空",groups = {ADD.class, EDIT.class})
    private String taxCode;
    /**
     * 主要商品名称
     */
    @NotBlank(message = "主要商品名称不能为空",groups = {ADD.class, EDIT.class})
    private String mainName;
    /**
     * 税率(%)
     */
    @NotNull(message = "税率(%)不能为空",groups = {ADD.class, EDIT.class})
    private Double rate;

    /**
     * 不含税金额
     */
    @NotNull(message = "不含税金额不能为空",groups = {ADD.class, EDIT.class})
    private Double notTax;

    /**
     * 税金
     */
    @NotNull(message = "税金不能为空",groups = {ADD.class, EDIT.class})
    private Double tax;

    /**
     * 签收人
     */
    @NotBlank(message = "签收人不能为空",groups = {ADD.class, EDIT.class})
    private String signer;

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getInvoicingTime() {
        return invoicingTime;
    }

    public void setInvoicingTime(String invoicingTime) {
        this.invoicingTime = invoicingTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Double getNotTax() {
        return notTax;
    }

    public void setNotTax(Double notTax) {
        this.notTax = notTax;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getSigner() {
        return signer;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }
}