package com.bjike.goddess.financeinit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.financeinit.enums.AccountingPeriod;
import com.bjike.goddess.financeinit.enums.AccountingSystem;
import com.bjike.goddess.financeinit.enums.ScaleShape;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 基本参数
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:11 ]
 * @Description: [ 基本参数 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BaseParameterTO extends BaseTO {

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空",groups = {ADD.class, EDIT.class})
    private String companyName;

    /**
     * 本位币
     */
    @NotBlank(message = "本位币不能为空",groups = {ADD.class, EDIT.class})
    private String functionalCurrency;

    /**
     * 会计制度
     */
    @NotNull(message = "会计制度不能为空",groups = {ADD.class, EDIT.class})
    private AccountingSystem accountingSystem;

    /**
     * 会计期间界定方式
     */
    @NotNull(message = "会计期间界定方式不能为空",groups = {ADD.class, EDIT.class})
    private String defWayAccountPerod;

    /**
     * 会计年度启用时间
     */
    @NotNull(message = "会计年度启用时间不能为空",groups = {ADD.class, EDIT.class})
    private String accountingYear;

    /**
     * 账套会计期间启用日期
     */
    @NotNull(message = "账套会计期间启用日期不能为空",groups = {ADD.class, EDIT.class})
    private String dateDuringPeriod;

    /**
     * 账套会计启用日期
     */
    @NotNull(message = "账套会计启用日期不能为空",groups = {ADD.class, EDIT.class})
    private String accountOpening;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFunctionalCurrency() {
        return functionalCurrency;
    }

    public void setFunctionalCurrency(String functionalCurrency) {
        this.functionalCurrency = functionalCurrency;
    }

    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    public void setAccountingSystem(AccountingSystem accountingSystem) {
        this.accountingSystem = accountingSystem;
    }

    public String getDefWayAccountPerod() {
        return defWayAccountPerod;
    }

    public void setDefWayAccountPerod(String defWayAccountPerod) {
        this.defWayAccountPerod = defWayAccountPerod;
    }

    public String getAccountingYear() {
        return accountingYear;
    }

    public void setAccountingYear(String accountingYear) {
        this.accountingYear = accountingYear;
    }

    public String getDateDuringPeriod() {
        return dateDuringPeriod;
    }

    public void setDateDuringPeriod(String dateDuringPeriod) {
        this.dateDuringPeriod = dateDuringPeriod;
    }

    public String getAccountOpening() {
        return accountOpening;
    }

    public void setAccountOpening(String accountOpening) {
        this.accountOpening = accountOpening;
    }

}