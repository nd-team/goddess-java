package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 股东变更
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:37 ]
 * @Description: [ 股东变更 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareChangeTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 变更前股东姓名
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "变更前股东姓名不能为空")
    private String changeBeforeName;

    /**
     * 变更后股东姓名
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "变更后股东姓名不能为空")
    private String changeAfterName;

    /**
     * 变更时间
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "变更时间不能为空")
    private String changeDate;

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

    public String getChangeBeforeName() {
        return changeBeforeName;
    }

    public void setChangeBeforeName(String changeBeforeName) {
        this.changeBeforeName = changeBeforeName;
    }

    public String getChangeAfterName() {
        return changeAfterName;
    }

    public void setChangeAfterName(String changeAfterName) {
        this.changeAfterName = changeAfterName;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}