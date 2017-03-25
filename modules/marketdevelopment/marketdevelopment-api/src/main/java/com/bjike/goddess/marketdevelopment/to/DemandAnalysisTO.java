package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 市场需求分析
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:10 ]
 * @Description: [ 市场需求分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DemandAnalysisTO extends BaseTO {

    /**
     * 业务类型
     */
    @NotNull(message = "业务类型不能为空",groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 业务方向科目
     */
    @NotNull(message = "业务方向科目不能为空",groups = {ADD.class, EDIT.class})
    private String course;

    /**
     * 目标人群
     */
    @NotNull(message = "目标人群不能为空",groups = {ADD.class, EDIT.class})
    private String target;

    /**
     * 目标定位
     */
    @NotNull(message = "目标定位不能为空",groups = {ADD.class, EDIT.class})
    private String orientation;

    /**
     * 备注
     */
    private String remark;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}