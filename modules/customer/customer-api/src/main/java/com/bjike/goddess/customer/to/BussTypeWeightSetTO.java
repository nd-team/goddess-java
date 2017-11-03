package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 业务类型权重设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:24 ]
 * @Description: [ 业务类型权重设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BussTypeWeightSetTO extends BaseTO {

    /**
     * 业务类型
     */
    @NotBlank(message = "业务类型不能为空",groups = {ADD.class,EDIT.class})
    private String businessType;

    /**
     * 业务类型权重
     */
    @NotNull(message = "业务类型权重不能为空",groups = {ADD.class,EDIT.class})
    private Double businessTypeWeight;

    /**
     * 业务方向
     */
    @NotBlank(message = "业务方向不能为空",groups = {ADD.class,EDIT.class})
    private String businessWay;

    /**
     * 业务方向权重
     */
    @NotNull(message = "业务方向权重不能为空",groups = {ADD.class,EDIT.class})
    private Double businessWayWeight;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Double getBusinessTypeWeight() {
        return businessTypeWeight;
    }

    public void setBusinessTypeWeight(Double businessTypeWeight) {
        this.businessTypeWeight = businessTypeWeight;
    }

    public String getBusinessWay() {
        return businessWay;
    }

    public void setBusinessWay(String businessWay) {
        this.businessWay = businessWay;
    }

    public Double getBusinessWayWeight() {
        return businessWayWeight;
    }

    public void setBusinessWayWeight(Double businessWayWeight) {
        this.businessWayWeight = businessWayWeight;
    }
}