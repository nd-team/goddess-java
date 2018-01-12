package com.bjike.goddess.rotation.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 岗位轮换详细汇总
 * @Author: [caiwenxian]
 * @Date: [2018-01-09 15:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RotationCollectTO extends BaseTO{

    /**
     * 地区
     */
    private String area;

    /**
     * 部门
     */
    private String department;

    /**
     * 公司发展需求人数
     */
    private Integer companyNeed;

    /**
     * 个人发展需求人数
     */
    private Integer personalNeed;

    /**
     * 应轮岗人数
     */
    private Integer shouldRotation;

    /**
     * 处理中
     */
    private Integer dealing;

    /**
     * 已处理
     */
    private Integer finished;

    /**
     * 通过（轮岗人数）
     */
    private Integer passRotation;

    /**
     * 未通过
     */
    private Integer notPassRotation;

    /**
     * 已通报结果数
     */
    private Integer hadNotify;

    /**
     * 员工发展成本（元）
     */
    private Double developCost;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getCompanyNeed() {
        return companyNeed;
    }

    public void setCompanyNeed(Integer companyNeed) {
        this.companyNeed = companyNeed;
    }

    public Integer getPersonalNeed() {
        return personalNeed;
    }

    public void setPersonalNeed(Integer personalNeed) {
        this.personalNeed = personalNeed;
    }

    public Integer getShouldRotation() {
        return shouldRotation;
    }

    public void setShouldRotation(Integer shouldRotation) {
        this.shouldRotation = shouldRotation;
    }

    public Integer getDealing() {
        return dealing;
    }

    public void setDealing(Integer dealing) {
        this.dealing = dealing;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public Integer getPassRotation() {
        return passRotation;
    }

    public void setPassRotation(Integer passRotation) {
        this.passRotation = passRotation;
    }

    public Integer getNotPassRotation() {
        return notPassRotation;
    }

    public void setNotPassRotation(Integer notPassRotation) {
        this.notPassRotation = notPassRotation;
    }

    public Integer getHadNotify() {
        return hadNotify;
    }

    public void setHadNotify(Integer hadNotify) {
        this.hadNotify = hadNotify;
    }

    public Double getDevelopCost() {
        return developCost;
    }

    public void setDevelopCost(Double developCost) {
        this.developCost = developCost;
    }

    @Override
    public String toString() {
        return "RotationCollectBO{" +
                "area='" + area + '\'' +
                ", department='" + department + '\'' +
                ", companyNeed=" + companyNeed +
                ", personalNeed=" + personalNeed +
                ", shouldRotation=" + shouldRotation +
                ", dealing=" + dealing +
                ", finished=" + finished +
                ", passRotation=" + passRotation +
                ", notPassRotation=" + notPassRotation +
                ", hadNotify=" + hadNotify +
                ", developCost=" + developCost +
                '}';
    }
}
