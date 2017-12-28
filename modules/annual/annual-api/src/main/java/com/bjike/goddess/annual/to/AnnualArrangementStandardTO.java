package com.bjike.goddess.annual.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 年假层级标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:33 ]
 * @Description: [ 年假层级标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnualArrangementStandardTO extends BaseTO {

    /**
     * 标准ID
     */
    @NotNull(message = "标准ID不能为空", groups = {EDIT.class, ADD.class})
    private String standardId;

    /**
     * 岗位层级id
     */
    @NotNull(message = "岗位层级id不能为空", groups = {EDIT.class, ADD.class})
    private String arrangementId;

    /**
     * 年假天数
     */
    @NotNull(message = "年假天数不能为空", groups = {EDIT.class, ADD.class})
    private Integer days;


    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(String arrangementId) {
        this.arrangementId = arrangementId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}