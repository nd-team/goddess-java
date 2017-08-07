package com.bjike.goddess.socialfee.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 社会缴费数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-19 03:25 ]
 * @Description: [ 社会缴费数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SocialFeeDTO extends BaseDTO {

    /**
     * 缴费所属时期
     */
    private String payTime;

    /**
     * 纳税人名称/缴费公司
     */
    private String payFeer;

    /**
     * 单位社保号
     */
    private String workSocalNum;

    /**
     * 姓名/缴费个人
     */
    private String empName;

    /**
     * 开始日期
     */
    private String startTime;
    /**
     * 结束日期
     */
    private String endTime;



    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayFeer() {
        return payFeer;
    }

    public void setPayFeer(String payFeer) {
        this.payFeer = payFeer;
    }

    public String getWorkSocalNum() {
        return workSocalNum;
    }

    public void setWorkSocalNum(String workSocalNum) {
        this.workSocalNum = workSocalNum;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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