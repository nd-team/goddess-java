package com.bjike.goddess.managefee.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 管理费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectAreaTO extends BaseTO {

    public interface TestAdd{}
    /**
     * 地区数组
     */
    @NotNull(groups = {CollectAreaTO.TestAdd.class} , message = "地区数组不能为空")
    private String[] areas;


    /**
     * 开始时间
     */
    @NotBlank(groups = {CollectAreaTO.TestAdd.class} , message = "开始时间不能为空")
    private String startTime;

    /**
     * 结束时间
     */
    @NotBlank(groups = {CollectAreaTO.TestAdd.class} , message = "结束时间不能为空")
    private String endTime;

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}