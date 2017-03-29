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
    private String standard_id;

    /**
     * 岗位层级id
     */
    @NotNull(message = "岗位层级id不能为空", groups = {EDIT.class, ADD.class})
    private String arrangement_id;

    /**
     * 年假天数
     */
    @NotNull(message = "年假天数不能为空", groups = {EDIT.class, ADD.class})
    private Integer days;


    public String getStandard_id() {
        return standard_id;
    }

    public void setStandard_id(String standard_id) {
        this.standard_id = standard_id;
    }

    public String getArrangement_id() {
        return arrangement_id;
    }

    public void setArrangement_id(String arrangement_id) {
        this.arrangement_id = arrangement_id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}