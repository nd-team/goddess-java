package com.bjike.goddess.customer.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.customer.enums.*;

/**
 * 客户基本信息表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.047 ]
 * @Description: [ 客户基本信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerBaseInfoVO {

    /**
     * id
     */
    private String id;
    /**
     * 客户信息编号
     */
    private String customerNum;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 地区
     */
    private String area;

    /**
     * 性别
     */
    private CustomerSex customerSex;

    /**
     * 客户类别
     */
    private CustomerType customerType;

    /**
     * 客户状态
     */
    private CustomerStatus customerStatus;

    /**
     * 关系程度
     */
    private Double relation;

    /**
     * 客户级别
     */
    private CustomerLevelVO customerLevelVO;

    /**
     * 客户来源
     */
    private Origin origin;

    /**
     * 介绍人
     */
    private String introducer;

    /**
     * 邮箱
     */
    private String cusEmail;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 座机
     */
    private String phone;

    /**
     * 微信
     */
    private String weChart;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 行业
     */
    private String workProfession;

    /**
     * 组织机构名称
     */
    private String origanizion;

    /**
     * 组织机构规模
     */
    private String origanizationSize;

    /**
     * 岗位
     */
    private String workPosition;

    /**
     * 职级
     */
    private String workLevel;

    /**
     * 职权
     */
    private String workRight;

    /**
     * 生活地区
     */
    private String lifeArea;

    /**
     * 成长地区
     */
    private String grouthArea;

    /**
     * 以往工作地区
     */
    private String oldWorkPlace;

    /**
     * 最近市场接待时间
     */
    private String marketReceptTime;


    /**
     * 最近一次更新人
     */
    private String modifyPersion;

    /**
     * 客户信息完成度
     */
    private String infoComplet;

    /**
     * 客户启用状态
     */
    private Status status;
    /**
     * 最新更新时间
     */
    private String updateDate;
    /**
     * 市场信息编号
     */
    private String marketInfoNum;
    /**
     * 省份
     */
    private String provinces;
    /**
     * 接触阶段
     */
    private ContactPhace contactPhace;
    /**
     * 时效性
     */
    private Timeliness timeliness;
    /**
     * 亲密度
     */
    private Intimacy intimacy;
    /**
     * 难易度
     */
    private DifficultyLevel difficultyLevel;
    /**
     * 推荐的拜访时间
     */
    private String recommendVisitTime;
    /**
     * 拜访状态
     */
    private VisitStatus visitStatus;
    /**
     * 客户维护状态
     */
    private CustomerMainStatus customerMainStatus;
    /**
     * 最终权重
     */
    private Double finalWeight;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 业务方向-科目
     */
    private String businessWay;
    /**
     * 关联项目
     */
    private String relatedProject;
    /**
     * 合作项目区域分布
     */
    private String cooProjectAreaDistri;
    /**
     * 以前合作过的公司
     */
    private String beforeCooCompanise;
    /**
     * 现业务有关联公司
     */
    private String currentBusiness;
    /**
     * 未来发展方向
     */
    private String futureDevelopment;
    /**
     * 对客户合作满意程度
     */
    private Satisfation customerSatisfation;
    /**
     * 客户对我司合作满意程度
     */
    private Satisfation satisfationWithCompany;
    /**
     * 扣分事项
     */
    private String pointsMatters;
    /**
     * 客户保持率
     */
    private Double customerReten;
    /**
     * 商务活动记录
     */
    private String businessActivityRecord;
    /**
     * 市场招待记录汇总
     */
    private String marketReceptionRecord;
    /**
     * 市场信息记录
     */
    private String marketInfoRecord;
    /**
     * 是否需进行市场招待
     */
    private Boolean proceedMarketTreat;

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

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public CustomerSex getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(CustomerSex customerSex) {
        this.customerSex = customerSex;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public CustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Double getRelation() {
        return relation;
    }

    public void setRelation(Double relation) {
        this.relation = relation;
    }

    public CustomerLevelVO getCustomerLevelVO() {
        return customerLevelVO;
    }

    public void setCustomerLevelVO(CustomerLevelVO customerLevelVO) {
        this.customerLevelVO = customerLevelVO;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeChart() {
        return weChart;
    }

    public void setWeChart(String weChart) {
        this.weChart = weChart;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWorkProfession() {
        return workProfession;
    }

    public void setWorkProfession(String workProfession) {
        this.workProfession = workProfession;
    }

    public String getOriganizion() {
        return origanizion;
    }

    public void setOriganizion(String origanizion) {
        this.origanizion = origanizion;
    }

    public String getOriganizationSize() {
        return origanizationSize;
    }

    public void setOriganizationSize(String origanizationSize) {
        this.origanizationSize = origanizationSize;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getWorkLevel() {
        return workLevel;
    }

    public void setWorkLevel(String workLevel) {
        this.workLevel = workLevel;
    }

    public String getWorkRight() {
        return workRight;
    }

    public void setWorkRight(String workRight) {
        this.workRight = workRight;
    }

    public String getLifeArea() {
        return lifeArea;
    }

    public void setLifeArea(String lifeArea) {
        this.lifeArea = lifeArea;
    }

    public String getGrouthArea() {
        return grouthArea;
    }

    public void setGrouthArea(String grouthArea) {
        this.grouthArea = grouthArea;
    }

    public String getOldWorkPlace() {
        return oldWorkPlace;
    }

    public void setOldWorkPlace(String oldWorkPlace) {
        this.oldWorkPlace = oldWorkPlace;
    }

    public String getMarketReceptTime() {
        return marketReceptTime;
    }

    public void setMarketReceptTime(String marketReceptTime) {
        this.marketReceptTime = marketReceptTime;
    }

    public String getModifyPersion() {
        return modifyPersion;
    }

    public void setModifyPersion(String modifyPersion) {
        this.modifyPersion = modifyPersion;
    }

    public String getInfoComplet() {
        return infoComplet;
    }

    public void setInfoComplet(String infoComplet) {
        this.infoComplet = infoComplet;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getMarketInfoNum() {
        return marketInfoNum;
    }

    public void setMarketInfoNum(String marketInfoNum) {
        this.marketInfoNum = marketInfoNum;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public ContactPhace getContactPhace() {
        return contactPhace;
    }

    public void setContactPhace(ContactPhace contactPhace) {
        this.contactPhace = contactPhace;
    }

    public Timeliness getTimeliness() {
        return timeliness;
    }

    public void setTimeliness(Timeliness timeliness) {
        this.timeliness = timeliness;
    }

    public Intimacy getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(Intimacy intimacy) {
        this.intimacy = intimacy;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getRecommendVisitTime() {
        return recommendVisitTime;
    }

    public void setRecommendVisitTime(String recommendVisitTime) {
        this.recommendVisitTime = recommendVisitTime;
    }

    public VisitStatus getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(VisitStatus visitStatus) {
        this.visitStatus = visitStatus;
    }

    public CustomerMainStatus getCustomerMainStatus() {
        return customerMainStatus;
    }

    public void setCustomerMainStatus(CustomerMainStatus customerMainStatus) {
        this.customerMainStatus = customerMainStatus;
    }

    public Double getFinalWeight() {
        return finalWeight;
    }

    public void setFinalWeight(Double finalWeight) {
        this.finalWeight = finalWeight;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessWay() {
        return businessWay;
    }

    public void setBusinessWay(String businessWay) {
        this.businessWay = businessWay;
    }

    public String getRelatedProject() {
        return relatedProject;
    }

    public void setRelatedProject(String relatedProject) {
        this.relatedProject = relatedProject;
    }

    public String getCooProjectAreaDistri() {
        return cooProjectAreaDistri;
    }

    public void setCooProjectAreaDistri(String cooProjectAreaDistri) {
        this.cooProjectAreaDistri = cooProjectAreaDistri;
    }

    public String getBeforeCooCompanise() {
        return beforeCooCompanise;
    }

    public void setBeforeCooCompanise(String beforeCooCompanise) {
        this.beforeCooCompanise = beforeCooCompanise;
    }

    public String getCurrentBusiness() {
        return currentBusiness;
    }

    public void setCurrentBusiness(String currentBusiness) {
        this.currentBusiness = currentBusiness;
    }

    public String getFutureDevelopment() {
        return futureDevelopment;
    }

    public void setFutureDevelopment(String futureDevelopment) {
        this.futureDevelopment = futureDevelopment;
    }

    public Satisfation getCustomerSatisfation() {
        return customerSatisfation;
    }

    public void setCustomerSatisfation(Satisfation customerSatisfation) {
        this.customerSatisfation = customerSatisfation;
    }

    public Satisfation getSatisfationWithCompany() {
        return satisfationWithCompany;
    }

    public void setSatisfationWithCompany(Satisfation satisfationWithCompany) {
        this.satisfationWithCompany = satisfationWithCompany;
    }

    public String getPointsMatters() {
        return pointsMatters;
    }

    public void setPointsMatters(String pointsMatters) {
        this.pointsMatters = pointsMatters;
    }

    public Double getCustomerReten() {
        return customerReten;
    }

    public void setCustomerReten(Double customerReten) {
        this.customerReten = customerReten;
    }

    public String getBusinessActivityRecord() {
        return businessActivityRecord;
    }

    public void setBusinessActivityRecord(String businessActivityRecord) {
        this.businessActivityRecord = businessActivityRecord;
    }

    public String getMarketReceptionRecord() {
        return marketReceptionRecord;
    }

    public void setMarketReceptionRecord(String marketReceptionRecord) {
        this.marketReceptionRecord = marketReceptionRecord;
    }

    public String getMarketInfoRecord() {
        return marketInfoRecord;
    }

    public void setMarketInfoRecord(String marketInfoRecord) {
        this.marketInfoRecord = marketInfoRecord;
    }

    public Boolean getProceedMarketTreat() {
        return proceedMarketTreat;
    }

    public void setProceedMarketTreat(Boolean proceedMarketTreat) {
        this.proceedMarketTreat = proceedMarketTreat;
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