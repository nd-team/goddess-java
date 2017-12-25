package com.bjike.goddess.outcarfare.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.dispatchcar.enums.Acctype;
import com.bjike.goddess.outcarfare.entity.WaitPay;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 等待付款
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 03:11 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitPayTO extends BaseTO {
    public interface TestEdit{}
    public interface TestAdd{}
    /**
     * 司机名称
     */
    @NotBlank(message = "司机名称不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private String driverName;

    /**
     * 出车日期
     */
    @NotBlank(message = "出车日期不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private String carDate;

    /**
     * 单号
     */
    @NotBlank(message = "单号不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private String number;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private String arrival;

    /**
     * 用车人
     */
    @NotBlank(message = "用车人不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private String carUser;

    /**
     * 科目
     */
    @NotNull(message = "科目不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private Acctype acctype;

    /**
     * 租车单价
     */
    @NotNull(message = "租车单价不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private Double carPrice;

    /**
     * 加班时长
     */
    @NotNull(message = "加班时长不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private Double overtimeHour;

    /**
     * 加班单价
     */
    @NotNull(message = "加班单价不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private Double overtimePrice;

    /**
     * 加班费
     */
    @NotNull(message = "加班费不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private Double overtimeFee;

    /**
     * 餐费补贴
     */
    @NotNull(message = "餐费补贴不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private Double allowance;

    /**
     * 停车/过路费
     */
    @NotNull(message = "停车/过路费不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private Double parkFee;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private Double amount;

    /**
     * 填单人
     */
    @NotBlank(message = "填单人不能为空",groups = {WaitPayTO.TestAdd.class, WaitPayTO.TestEdit.class})
    private String single;

    /**
     * 是否付款
     */
    @NotNull(groups = {WaitPayTO.TestEdit.class},message = "是否付款不能为空")
    private boolean isPay;


    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCarDate() {
        return carDate;
    }

    public void setCarDate(String carDate) {
        this.carDate = carDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }

    public Acctype getAcctype() {
        return acctype;
    }

    public void setAcctype(Acctype acctype) {
        this.acctype = acctype;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public Double getOvertimeHour() {
        return overtimeHour;
    }

    public void setOvertimeHour(Double overtimeHour) {
        this.overtimeHour = overtimeHour;
    }

    public Double getOvertimePrice() {
        return overtimePrice;
    }

    public void setOvertimePrice(Double overtimePrice) {
        this.overtimePrice = overtimePrice;
    }

    public Double getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(Double overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public Double getParkFee() {
        return parkFee;
    }

    public void setParkFee(Double parkFee) {
        this.parkFee = parkFee;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(boolean isPay) {
        this.isPay = isPay;
    }
}