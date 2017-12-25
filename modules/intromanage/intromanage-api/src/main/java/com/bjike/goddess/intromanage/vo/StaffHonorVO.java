package com.bjike.goddess.intromanage.vo;

/**
 * 员工荣誉表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:34 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffHonorVO {

    /**
     * id
     */
    private String id;

    /**
     * 姓名
     */
    private String staffId;

    /**
     * 荣誉名称
     */
    private String honorName;

    /**
     * 荣誉等级
     */
    private String honorGrade;

    /**
     * 公司补助
     */
    private String firmSubsidy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public String getHonorGrade() {
        return honorGrade;
    }

    public void setHonorGrade(String honorGrade) {
        this.honorGrade = honorGrade;
    }

    public String getFirmSubsidy() {
        return firmSubsidy;
    }

    public void setFirmSubsidy(String firmSubsidy) {
        this.firmSubsidy = firmSubsidy;
    }
}