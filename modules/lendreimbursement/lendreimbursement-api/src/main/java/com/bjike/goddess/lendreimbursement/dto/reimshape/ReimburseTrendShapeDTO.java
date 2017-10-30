package com.bjike.goddess.lendreimbursement.dto.reimshape;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.lendreimbursement.enums.ReimburseShapeStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 报销图形数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 报销图形数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimburseTrendShapeDTO extends BaseDTO {

    public interface TESTCON{}

    /**
     * 用户名
     */
    @NotBlank(groups = {ReimburseTrendShapeDTO.TESTCON.class} , message = "用户名不能为空")
    private String userName;


    /**
     * 汇总的开始时间，格式为2017-09
     */
    @NotBlank(groups = {ReimburseTrendShapeDTO.TESTCON.class} , message = "汇总的开始时间不能为空,格式为2017-09")
    private String endTime;

    /**
     * 汇总的结束时间，格式为2017-10
     */
    @NotBlank(groups = {ReimburseTrendShapeDTO.TESTCON.class} , message = "汇总的结束时间不能为空,格式为2017-10")
    private String startTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}