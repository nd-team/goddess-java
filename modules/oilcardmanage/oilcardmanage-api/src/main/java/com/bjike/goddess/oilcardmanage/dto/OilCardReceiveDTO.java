package com.bjike.goddess.oilcardmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.oilcardmanage.enums.OilCardStatus;

/**
 * 油卡领用查询对象
 *
 * @Author: [Jason]
 * @Date: [17-3-14 下午4:37]
 * @Package:[ com.bjike.goddess.oilcardmanage.dto ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardReceiveDTO extends BaseDTO {
    /**
     * 油卡编号
     */
    private String oilCardNumber;

    /**
     * 项目组
     */
    private String department;

    /**
     * 领用状态
     */
    private OilCardStatus oilCardStatus;

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public OilCardStatus getOilCardStatus() {
        return oilCardStatus;
    }

    public void setOilCardStatus(OilCardStatus oilCardStatus) {
        this.oilCardStatus = oilCardStatus;
    }
}
