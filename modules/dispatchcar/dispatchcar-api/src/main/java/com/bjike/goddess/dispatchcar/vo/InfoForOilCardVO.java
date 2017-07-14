package com.bjike.goddess.dispatchcar.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.dispatchcar.enums.Acctype;
import com.bjike.goddess.dispatchcar.enums.Evaluate;
import com.bjike.goddess.dispatchcar.enums.FindType;

/**
 * 出车记录表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InfoForOilCardVO {

    /**
     * 用车人
     */
    private String carUser;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 所用油卡编号
     */
    private String oilCardNumber;

    /**
     * 加油量
     */
    private Double addOilAmount;

    /**
     * 当天油价
     */
    private Double oilPrice;

    /**
     * 加油时间
     */
    private String addOilTime;

    /**
     * 加油金额
     */
    private Double oilAmount;

    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAddOilTime() {
        return addOilTime;
    }

    public void setAddOilTime(String addOilTime) {
        this.addOilTime = addOilTime;
    }

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public Double getAddOilAmount() {
        return addOilAmount;
    }

    public void setAddOilAmount(Double addOilAmount) {
        this.addOilAmount = addOilAmount;
    }

    public Double getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(Double oilPrice) {
        this.oilPrice = oilPrice;
    }

    public Double getOilAmount() {
        return oilAmount;
    }

    public void setOilAmount(Double oilAmount) {
        this.oilAmount = oilAmount;
    }
}