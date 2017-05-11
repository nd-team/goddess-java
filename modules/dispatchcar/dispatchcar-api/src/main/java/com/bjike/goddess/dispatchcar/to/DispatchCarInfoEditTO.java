package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.dispatchcar.enums.Acctype;
import com.bjike.goddess.dispatchcar.enums.Evaluate;
import com.bjike.goddess.dispatchcar.enums.FindType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 出车记录新增或编辑
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DispatchCarInfoEditTO extends BaseTO {

    /**
     * 司机名称
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String driver;

    /**
     * 是否公司人员出车
     */
    @NotNull(message = "'是否公司人员出车'不能为空", groups = {ADD.class, EDIT.class})
    private Boolean companyDispatch;

    /**
     * 用车人
     */
    @NotBlank(message = "'用车人'不能为空", groups = {ADD.class, EDIT.class})
    private String carUser;

    /**
     * 所属地区
     */
    @NotBlank(message = "'地区'不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 所属项目组
     */
    @NotBlank(message = "'项目组'不能为空", groups = {ADD.class, EDIT.class})
    private String group;

    /**
     * 是否立项
     */
    @NotNull(message = "'是否立项'不能为空", groups = {ADD.class, EDIT.class})
    private Boolean projectApproval;

    /**
     * 项目名称
     */
    @NotBlank(message = "'项目名称'不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 科目类型
     */
    @NotNull(message = "'科目类型'不能为空", groups = {ADD.class, EDIT.class})
    private Acctype acctype;

    /**
     * 出车日期
     */
    @NotBlank(message = "'出车日期'不能为空", groups = {ADD.class, EDIT.class})
    private String dispatchDate;

    /**
     * 出车开始时间
     */
    @NotBlank(message = "'出车开始时间'不能为空", groups = {ADD.class, EDIT.class})
    private String startTime;

    /**
     * 出车结束时间
     */
    @NotBlank(message = "'出车结束时间'不能为空", groups = {ADD.class, EDIT.class})
    private String endTime;

    /**
     * 是否午休
     */
    @NotNull(message = "'是否午休'不能为空", groups = {ADD.class, EDIT.class})
    private Boolean siesta;

    /**
     * 加班时长
     */
    @NotNull(message = "'加班时长'不能为空", groups = {ADD.class, EDIT.class})
    private Integer overWorkTime;

    /**
     * 用车事由
     */
    @NotBlank(message = "'用车事由'不能为空", groups = {ADD.class, EDIT.class})
    private String dispatchReason;

    /**
     * 随同人员
     */
    @NotBlank(message = "'随同人员'不能为空", groups = {ADD.class, EDIT.class})
    private String accompanyUser;

    /**
     * 车牌号码
     */
    @NotBlank(message = "'车牌号码'不能为空", groups = {ADD.class, EDIT.class})
    private String carNumber;

    /**
     * 所用油卡编号
     */
    @NotBlank(message = "'所用油卡编号'不能为空", groups = {ADD.class, EDIT.class})
    private String oilCardNumber;

    /**
     * 是否开空调
     */
    @NotNull(message = "'是否开空调'不能为空", groups = {ADD.class, EDIT.class})
    private Boolean aircondition;

    /**
     * 是否市内
     */
    @NotNull(message = "'是否室内'不能为空", groups = {ADD.class, EDIT.class})
    private Boolean downtown;

    /**
     * 当天是否加油
     */
    @NotNull(message = "'当天是否加油'不能为空", groups = {ADD.class, EDIT.class})
    private Boolean addOil;

    /**
     * 补加油说明
     */
    private String addOilExplain;

    /**
     * 欠油说明
     */
    private String oweOilExplain;

    /**
     * 加油时间
     */
    private String addOilTime;

    /**
     * 油卡余额
     */
    @NotNull(message = "'油卡余额'不能为空", groups = {ADD.class, EDIT.class})
    private Double oilCardBalance;

    /**
     * 当天油价
     */
    private Double oilPrice;

    /**
     * 任务下达人
     */
    @NotBlank(message = "'任务下达人'不能为空", groups = {ADD.class, EDIT.class})
    private String principal;

    /**
     * 计划任务数量
     */
    @NotNull(message = "'计划任务数量'不能为空", groups = {ADD.class, EDIT.class})
    private Integer planTaskAmount;

    /**
     * 完成任务数量
     */
    @NotNull(message = "'完成任务数量'不能为空", groups = {ADD.class, EDIT.class})
    private Integer finishTaskAmount;

    /**
     * 出车开始里程数
     */
    @NotNull(message = "'出车开始里程数'不能为空", groups = {ADD.class, EDIT.class})
    private Double startMileage;

    /**
     * 出车结束里程数
     */
    @NotNull(message = "'出车结束里程数'不能为空", groups = {ADD.class, EDIT.class})
    private Double endMileage;

    /**
     * GPS轨迹总里程数
     */
    @NotNull(message = "'GPS轨迹总里程数'不能为空", groups = {ADD.class, EDIT.class})
    private Double mileageOfGPS;

    /**
     * 停车费
     */
    @NotNull(message = "'停车费'不能为空", groups = {ADD.class, EDIT.class})
    private Double parkCost;

    /**
     * 过路费
     */
    @NotNull(message = "'过路费'不能为空", groups = {ADD.class, EDIT.class})
    private Double roadCost;

    /**
     * 对司机的评价
     */
    @NotNull(message = "'对司机的评价'不能为空", groups = {ADD.class, EDIT.class})
    private Evaluate evaluatedriver;

    /**
     * 处罚汇总
     */
    private Double punishCost;

    /**
     * 异常分析
     */
    private String exceptionAnalyze;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Boolean getCompanyDispatch() {
        return companyDispatch;
    }

    public void setCompanyDispatch(Boolean companyDispatch) {
        this.companyDispatch = companyDispatch;
    }

    public String getCarUser() {
        return carUser;
    }

    public void setCarUser(String carUser) {
        this.carUser = carUser;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getProjectApproval() {
        return projectApproval;
    }

    public void setProjectApproval(Boolean projectApproval) {
        this.projectApproval = projectApproval;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Acctype getAcctype() {
        return acctype;
    }

    public void setAcctype(Acctype acctype) {
        this.acctype = acctype;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
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

    public Boolean getSiesta() {
        return siesta;
    }

    public void setSiesta(Boolean siesta) {
        this.siesta = siesta;
    }

    public Integer getOverWorkTime() {
        return overWorkTime;
    }

    public void setOverWorkTime(Integer overWorkTime) {
        this.overWorkTime = overWorkTime;
    }

    public String getDispatchReason() {
        return dispatchReason;
    }

    public void setDispatchReason(String dispatchReason) {
        this.dispatchReason = dispatchReason;
    }

    public String getAccompanyUser() {
        return accompanyUser;
    }

    public void setAccompanyUser(String accompanyUser) {
        this.accompanyUser = accompanyUser;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public Boolean getAircondition() {
        return aircondition;
    }

    public void setAircondition(Boolean aircondition) {
        this.aircondition = aircondition;
    }

    public Boolean getDowntown() {
        return downtown;
    }

    public void setDowntown(Boolean downtown) {
        this.downtown = downtown;
    }

    public Boolean getAddOil() {
        return addOil;
    }

    public void setAddOil(Boolean addOil) {
        this.addOil = addOil;
    }

    public String getAddOilExplain() {
        return addOilExplain;
    }

    public void setAddOilExplain(String addOilExplain) {
        this.addOilExplain = addOilExplain;
    }

    public String getOweOilExplain() {
        return oweOilExplain;
    }

    public void setOweOilExplain(String oweOilExplain) {
        this.oweOilExplain = oweOilExplain;
    }

    public String getAddOilTime() {
        return addOilTime;
    }

    public void setAddOilTime(String addOilTime) {
        this.addOilTime = addOilTime;
    }

    public Double getOilCardBalance() {
        return oilCardBalance;
    }

    public void setOilCardBalance(Double oilCardBalance) {
        this.oilCardBalance = oilCardBalance;
    }

    public Double getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(Double oilPrice) {
        this.oilPrice = oilPrice;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Integer getPlanTaskAmount() {
        return planTaskAmount;
    }

    public void setPlanTaskAmount(Integer planTaskAmount) {
        this.planTaskAmount = planTaskAmount;
    }

    public Integer getFinishTaskAmount() {
        return finishTaskAmount;
    }

    public void setFinishTaskAmount(Integer finishTaskAmount) {
        this.finishTaskAmount = finishTaskAmount;
    }

    public Double getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(Double startMileage) {
        this.startMileage = startMileage;
    }

    public Double getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(Double endMileage) {
        this.endMileage = endMileage;
    }

    public Double getMileageOfGPS() {
        return mileageOfGPS;
    }

    public void setMileageOfGPS(Double mileageOfGPS) {
        this.mileageOfGPS = mileageOfGPS;
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

    public Evaluate getEvaluatedriver() {
        return evaluatedriver;
    }

    public void setEvaluatedriver(Evaluate evaluatedriver) {
        this.evaluatedriver = evaluatedriver;
    }

    public Double getPunishCost() {
        return punishCost;
    }

    public void setPunishCost(Double punishCost) {
        this.punishCost = punishCost;
    }

    public String getExceptionAnalyze() {
        return exceptionAnalyze;
    }

    public void setExceptionAnalyze(String exceptionAnalyze) {
        this.exceptionAnalyze = exceptionAnalyze;
    }
}