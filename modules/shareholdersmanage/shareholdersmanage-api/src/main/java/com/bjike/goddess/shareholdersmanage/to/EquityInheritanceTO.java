package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 股权继承
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:06 ]
 * @Description: [ 股权继承 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EquityInheritanceTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 继承人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "继承人不能为空")
    private String heir;

    /**
     * 被继承人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "被继承人不能为空")
    private String beHeir;

    /**
     * 股权类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "股权类型不能为空")
    private String equityType;

    /**
     * 继承日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "继承日期不能为空")
    private String heirDate;

    /**
     * 备注
     */
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHeir() {
        return heir;
    }

    public void setHeir(String heir) {
        this.heir = heir;
    }

    public String getBeHeir() {
        return beHeir;
    }

    public void setBeHeir(String beHeir) {
        this.beHeir = beHeir;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public String getHeirDate() {
        return heirDate;
    }

    public void setHeirDate(String heirDate) {
        this.heirDate = heirDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}