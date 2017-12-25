package com.bjike.goddess.businsurance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 意外险记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 05:43 ]
 * @Description: [ 续保意外险记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InsureRecordNextTO extends BaseTO {

    public interface TestAdd {
    }


    /**
     * 保单生效日
     */
    @NotBlank(groups = {InsureRecordNextTO.TestAdd.class}, message = "保单生效日不能为空，格式为年月日")
    private String startDate;

    /**
     * 保单期满日
     */
    @NotBlank(groups = {InsureRecordNextTO.TestAdd.class}, message = "保单期满日不能为空，格式为年月日")
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}