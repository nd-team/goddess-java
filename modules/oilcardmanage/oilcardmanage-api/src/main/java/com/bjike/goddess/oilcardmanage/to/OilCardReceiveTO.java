package com.bjike.goddess.oilcardmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 油卡领用传输对象
 *
 * @Author: [Jason]
 * @Date: [17-3-14下午4:37]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardReceiveTO extends BaseTO {

    /**
     * 油卡信息Id
     */
    @NotBlank(message = "油卡id不能为空!", groups = {ADD.class, EDIT.class})
    private String oilCardBasicId;

    /**
     * 领用日期
     */
    @NotBlank(message = "领用日期不能为空!", groups = {ADD.class, EDIT.class})
    private String receiveDate;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空!", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 原因
     */
    @NotBlank(message = "原因不能为空!", groups = {ADD.class, EDIT.class})
    private String receiveReason;

    /**
     * 审核人
     */
    @NotBlank(message = "审核人不能为空!", groups = {ADD.class, EDIT.class})
    private String auditUser;

    /**
     * 备注
     */
    private String remark;


    public String getOilCardBasicId() {
        return oilCardBasicId;
    }

    public void setOilCardBasicId(String oilCardBasicId) {
        this.oilCardBasicId = oilCardBasicId;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getReceiveReason() {
        return receiveReason;
    }

    public void setReceiveReason(String receiveReason) {
        this.receiveReason = receiveReason;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
