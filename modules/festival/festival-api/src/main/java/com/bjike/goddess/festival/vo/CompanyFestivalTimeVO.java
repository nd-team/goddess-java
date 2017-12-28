package com.bjike.goddess.festival.vo;

/**
 * 公司放假时间安排表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 08:10 ]
 * @Description: [ 公司放假时间安排表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyFestivalTimeVO {

    /**
     * id
     */
    private String id;
    /**
     * 节假日名称
     */
    private String name;

    /**
     * 国家放假时长
     */
    private String countryTimeLong;

    /**
     * 公司放假时长
     */
    private String companyTimeLong;

    /**
     * 公司放假起始时间
     */
    private String companyStartTime;

    /**
     * 公司放假结束时间
     */
    private String companyEndTime;

    /**
     * 调休天数
     */
    private String offDay;

    /**
     * 补休日期
     */
    private String takeTime;

    /**
     * 调休日期
     */
    private String offTime;

    /**
     * 调休规范
     */
    private String offStandard;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryTimeLong() {
        return countryTimeLong;
    }

    public void setCountryTimeLong(String countryTimeLong) {
        this.countryTimeLong = countryTimeLong;
    }

    public String getCompanyTimeLong() {
        return companyTimeLong;
    }

    public void setCompanyTimeLong(String companyTimeLong) {
        this.companyTimeLong = companyTimeLong;
    }

    public String getCompanyStartTime() {
        return companyStartTime;
    }

    public void setCompanyStartTime(String companyStartTime) {
        this.companyStartTime = companyStartTime;
    }

    public String getCompanyEndTime() {
        return companyEndTime;
    }

    public void setCompanyEndTime(String companyEndTime) {
        this.companyEndTime = companyEndTime;
    }

    public String getOffDay() {
        return offDay;
    }

    public void setOffDay(String offDay) {
        this.offDay = offDay;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    public String getOffStandard() {
        return offStandard;
    }

    public void setOffStandard(String offStandard) {
        this.offStandard = offStandard;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }


}