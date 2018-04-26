package com.bjike.goddess.materialbuy.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 银行流水
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-04 09:39 ]
 * @Description: [ 银行流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankOfTheWaterTO extends BaseTO {
    /**
     * 交易下标
     */

    private Integer tradingDay;

    /**
     * 交易下标 ；LocalDate
     */

    private Integer theBankAccount;
    /**
     * 起息日下标 ；LocalDate
     */

    private Integer whereItIs;
    /**
     * 交易类型下标 ；LocalDate
     */
    private Integer transactionType;
    /**
     * 余额下标
     */

    private Integer theBalanceOf;

    /**
     * 摘要下标
     */

    private Integer abstracts;
    /**
     * 借方金额下标
     */

    private Integer debitAmount;
    /**
     * 贷方金额下标
     */
    private Integer theSum;

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @NotBlank(message = "开始时间不能空")
    private LocalDateTime theStartTime;//开始时间

    @NotBlank(message = "结束时间不能空")
    private LocalDateTime theEndOfrThe;//结束时间

    private LocalDateTime accountingDate;//账套会计启动时间
    /**
     * 银行账户信息
     */
    private String accountInformation;
    /**
     * 银行属性
     */
    @NotBlank(message = "银行属性不能空")
     private String bankProperty;

}