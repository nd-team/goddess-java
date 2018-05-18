package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 公允值变动
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-29 05:36 ]
 * @Description: [ 公允值变动 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FairChangeTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 股权类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "股权类型不能为空")
    private String equityType;

    /**
     * 变动时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "变动时间不能为空")
    private String changDate;

    /**
     * 每股价格/元
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "每股价格不能为空")
    private Double perSharePrice;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public String getChangDate() {
        return changDate;
    }

    public void setChangDate(String changDate) {
        this.changDate = changDate;
    }

    public Double getPerSharePrice() {
        return perSharePrice;
    }

    public void setPerSharePrice(Double perSharePrice) {
        this.perSharePrice = perSharePrice;
    }
}