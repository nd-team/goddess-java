package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 总经办审批to
 *
 * @Author: [sunfengtao]
 * @Date: [2017-06-02 11:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ZjbApprovalTO extends BaseTO {

    public interface ZjbApproval{}

    /**
     * 转正类型
     */
    @NotBlank(groups = {ZjbApprovalTO.ZjbApproval.class}, message = "转正类型不能为空")
    private String positiveType;

    /**
     * 总经办评价
     */
    @NotBlank(groups = {ZjbApprovalTO.ZjbApproval.class}, message = "总经办评价不能为空")
    private String zjbAppraise;

    /**
     * 转正日期
     */
    @NotBlank(groups = {ZjbApprovalTO.ZjbApproval.class}, message = "转正时间不能为空")
    private String positiveDate;

    public String getPositiveType() {
        return positiveType;
    }

    public void setPositiveType(String positiveType) {
        this.positiveType = positiveType;
    }

    public String getZjbAppraise() {
        return zjbAppraise;
    }

    public void setZjbAppraise(String zjbAppraise) {
        this.zjbAppraise = zjbAppraise;
    }

    public String getPositiveDate() {
        return positiveDate;
    }

    public void setPositiveDate(String positiveDate) {
        this.positiveDate = positiveDate;
    }
}
