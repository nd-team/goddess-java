package com.bjike.goddess.courier.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 快递公司信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:29 ]
 * @Description: [ 快递公司信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CourierCompanyTO extends BaseTO {

    /**
     * 快递公司
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "快递公司不能为空")
    private String courierCompany;

    /**
     * 联系电话
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "联系电话不能为空")
    private String courierTel;


    public String getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(String courierCompany) {
        this.courierCompany = courierCompany;
    }

    public String getCourierTel() {
        return courierTel;
    }

    public void setCourierTel(String courierTel) {
        this.courierTel = courierTel;
    }
}