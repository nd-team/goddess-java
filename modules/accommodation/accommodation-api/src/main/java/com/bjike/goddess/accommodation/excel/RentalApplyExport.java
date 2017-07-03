package com.bjike.goddess.accommodation.excel;


import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [租房申请   视图封装对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RentalApplyExport {
    /**
     * 姓名（用户名称）
     */
    @ExcelHeader(name = "姓名（用户名称）",notNull = true)
    private String name;
    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号",notNull = true)
    private String employeeNum;
    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;
    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String jobs;
    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组",notNull = true)
    private String projectGroup;
    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称",notNull = true)
    private String projectName;
    /**
     * 租赁人
     */
    @ExcelHeader(name = "租赁人",notNull = true)
    private String lessee;
    /**
     * 住宿人
     */
    @ExcelHeader(name = "住宿人",notNull = true)
    private String stayPeople;
    /**
     * 申请原因
     */
    @ExcelHeader(name = "申请原因",notNull = true)
    private String reason;
    /**
     * 租房用途
     */
    @ExcelHeader(name = "租房用途",notNull = true)
    private String purpose;
    /**
     * 租房地址
     */
    @ExcelHeader(name = "租房地址",notNull = true)
    private String address;
    /**
     * 房东姓名
     */
    @ExcelHeader(name = "房东姓名",notNull = true)
    private String landlord;
    /**
     * 联系方式
     */
    @ExcelHeader(name = "联系方式",notNull = true)
    private String contact;
    /**
     * 中介费
     */
    @ExcelHeader(name = "中介费",notNull = true)
    private Double agency;
    /**
     * 押金
     */
    @ExcelHeader(name = "押金",notNull = true)
    private Double deposit;
    /**
     * 房租
     */
    @ExcelHeader(name = "房租",notNull = true)
    private Double rent;
    /**
     * 房租管理费
     */
    @ExcelHeader(name = "房租管理费",notNull = true)
    private Double rentFee;
    /**
     * 卫生费
     */
    @ExcelHeader(name = "卫生费",notNull = true)
    private Double sanitation;
    /**
     * 水费计价
     */
    @ExcelHeader(name = "水费计价",notNull = true)
    private Double water;
    /**
     * 电费计价
     */
    @ExcelHeader(name = "电费计价",notNull = true)
    private Double energy;
    /**
     * 网络套餐费用
     */
    @ExcelHeader(name = "网络套餐费用",notNull = true)
    private Double network;
    /**
     * 项目经理
     */
    @ExcelHeader(name = "项目经理",notNull = true)
    private String manage;
    /**
     * 项目经理意见
     */
    @ExcelHeader(name = "项目经理意见",notNull = true)
    private String manageOpinion;
    /**
     * 项目经理是否通过(是/否)
     */
    @ExcelHeader(name = "项目经理是否通过(是/否)",notNull = true)
    private String managePass;
    /**
     * 商务发展部意见
     */
    @ExcelHeader(name = "商务发展部意见",notNull = true)
    private String commerceRemark;
    /**
     * 综合资源部意见
     */
    @ExcelHeader(name = "综合资源部意见",notNull = true)
    private String comprehensiveRemark;
    /**
     * 运营财务部意见
     */
    @ExcelHeader(name = "运营财务部意见",notNull = true)
    private String operatingRemark;
    /**
     * 备注
     */
    @ExcelHeader(name = "备注",notNull = true)
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLessee() {
        return lessee;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    public String getStayPeople() {
        return stayPeople;
    }

    public void setStayPeople(String stayPeople) {
        this.stayPeople = stayPeople;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) {
        this.landlord = landlord;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Double getAgency() {
        return agency;
    }

    public void setAgency(Double agency) {
        this.agency = agency;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getRentFee() {
        return rentFee;
    }

    public void setRentFee(Double rentFee) {
        this.rentFee = rentFee;
    }

    public Double getSanitation() {
        return sanitation;
    }

    public void setSanitation(Double sanitation) {
        this.sanitation = sanitation;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getNetwork() {
        return network;
    }

    public void setNetwork(Double network) {
        this.network = network;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getManageOpinion() {
        return manageOpinion;
    }

    public void setManageOpinion(String manageOpinion) {
        this.manageOpinion = manageOpinion;
    }

    public String getManagePass() {
        return managePass;
    }

    public void setManagePass(String managePass) {
        this.managePass = managePass;
    }

    public String getCommerceRemark() {
        return commerceRemark;
    }

    public void setCommerceRemark(String commerceRemark) {
        this.commerceRemark = commerceRemark;
    }

    public String getComprehensiveRemark() {
        return comprehensiveRemark;
    }

    public void setComprehensiveRemark(String comprehensiveRemark) {
        this.comprehensiveRemark = comprehensiveRemark;
    }

    public String getOperatingRemark() {
        return operatingRemark;
    }

    public void setOperatingRemark(String operatingRemark) {
        this.operatingRemark = operatingRemark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
