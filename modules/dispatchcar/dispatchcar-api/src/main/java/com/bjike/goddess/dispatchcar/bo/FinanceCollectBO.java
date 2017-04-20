package com.bjike.goddess.dispatchcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.dispatchcar.enums.Acctype;

/**
 * 财务出车汇总
 *
 * @Author: [Jason]
 * @Date: [17-4-17 上午9:13]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FinanceCollectBO extends BaseBO {

    /**
     * 出车日期
     */
    private String date;

    /**
     * 单号
     */
    private String number;

    /**
     * 司机名称
     */
    private String driver;

    /**
     * 地区
     */
    private String area;

    /**
     * 用车人
     */
    private String carUser;

    /**
     * 项目
     */
    private String project;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 科目
     */
    private Acctype acctype;

    /**
     * 油费
     */
    private Double oilCost;

    /**
     * 加班时长
     */
    private Integer overWorkTime;

    /**
     * 加班费
     */
    private Double overWorkCost;

    /**
     * 餐费补贴
     */
    private Double mealCost;

    /**
     * 停车费
     */
    private Double parkCost;

    /**
     * 过路费
     */
    private Double roadCost;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Acctype getAcctype() {
        return acctype;
    }

    public void setAcctype(Acctype acctype) {
        this.acctype = acctype;
    }

    public Double getOilCost() {
        return oilCost;
    }

    public void setOilCost(Double oilCost) {
        this.oilCost = oilCost;
    }

    public Integer getOverWorkTime() {
        return overWorkTime;
    }

    public void setOverWorkTime(Integer overWorkTime) {
        this.overWorkTime = overWorkTime;
    }

    public Double getOverWorkCost() {
        return overWorkCost;
    }

    public void setOverWorkCost(Double overWorkCost) {
        this.overWorkCost = overWorkCost;
    }

    public Double getMealCost() {
        return mealCost;
    }

    public void setMealCost(Double mealCost) {
        this.mealCost = mealCost;
    }

    public Double getParkCost() {
        return parkCost;
    }

    public void setParkCost(Double parkCost) {
        this.parkCost = parkCost;
    }

    public Double getRoadCost() {
        return roadCost;
    }

    public void setRoadCost(Double roadCost) {
        this.roadCost = roadCost;
    }

    public FinanceCollectBO() {
    }

    public FinanceCollectBO(String date, String number, String driver, String area,
                            String carUser, String project, String projectGroup, Acctype acctype,
                            Double oilCost, Integer overWorkTime, Double overWorkCost,
                            Double mealCost, Double parkCost, Double roadCost ,String id) {
        this.date = date;
        this.number = number;
        this.driver = driver;
        this.area = area;
        this.carUser = carUser;
        this.project = project;
        this.projectGroup = projectGroup;
        this.acctype = acctype;
        this.oilCost = oilCost;
        this.overWorkTime = overWorkTime;
        this.overWorkCost = overWorkCost;
        this.mealCost = mealCost;
        this.parkCost = parkCost;
        this.roadCost = roadCost;
        this.id=id;
    }
}
