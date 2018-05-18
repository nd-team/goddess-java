package com.bjike.goddess.customer.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.customer.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 客户基本信息导入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-03-15T16:23:28.041 ]
 * @Description: [ 客户基本信息导出模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerBaseInfoExcel extends BaseTO {

    /**
     * 客户信息编号
     */
    @ExcelHeader(name = "客户信息编号",notNull = true)
    private String customerNum;

    /**
     * 客户姓名
     */
    @ExcelHeader(name = "客户姓名",notNull = true)
    private String customerName;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 性别
     */
    @ExcelHeader(name = "性别",notNull = true)
    private CustomerSex customerSex;

    /**
     * 客户类别
     */
    @ExcelHeader(name = "客户类别")
    private CustomerType customerType;

    /**
     * 客户状态
     */
    @ExcelHeader(name = "客户状态")
    private CustomerStatus customerStatus;

    /**
     * 关系程度
     */
    @ExcelHeader(name = "关系程度")
    private Double relation;

    /**
     * 客户级别
     */
    @ExcelHeader(name = "客户级别",notNull = true)
    private String customerLevelName;

    /**
     * 客户来源
     */
    @ExcelHeader(name = "客户来源",notNull = true)
    private Origin origin;

    /**
     * 介绍人
     */
    @ExcelHeader(name = "介绍人")
    private String introducer;

    /**
     * 邮箱
     */
    @ExcelHeader(name = "邮箱")
    private String cusEmail;

    /**
     * 手机号
     */
    @ExcelHeader(name = "手机号")
    private String tel;

    /**
     * 座机
     */
    @ExcelHeader(name = "座机")
    private String phone;

    /**
     * 微信
     */
    @ExcelHeader(name = "微信")
    private String weChart;

    /**
     * QQ号
     */
    @ExcelHeader(name = "QQ号")
    private String qq;

    /**
     * 行业
     */
    @ExcelHeader(name = "行业")
    private String workProfession;

    /**
     * 组织机构名称
     */
    @ExcelHeader(name = "组织机构名称")
    private String origanizion;

    /**
     * 组织机构规模
     */
    @ExcelHeader(name = "组织机构规模")
    private String origanizationSize;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位")
    private String workPosition;

    /**
     * 职级
     */
    @ExcelHeader(name = "职级")
    private String workLevel;

    /**
     * 职权
     */
    @ExcelHeader(name = "职权")
    private WorkRight workRight;

    /**
     * 生活地区
     */
    @ExcelHeader(name = "生活地区")
    private String lifeArea;

    /**
     * 成长地区
     */
    @ExcelHeader(name = "成长地区")
    private String grouthArea;

    /**
     * 以往工作地区
     */
    @ExcelHeader(name = "以往工作地区")
    private String oldWorkPlace;

    /**
     * 最近市场接待时间
     */
    @ExcelHeader(name = "最近市场接待时间")
    private LocalDateTime marketReceptTime;

    /**
     * 客户信息完成度
     */
    @ExcelHeader(name = "客户信息完成度",notNull = true)
    private String infoComplet;
    /**
     * 市场信息编号
     */
    @ExcelHeader(name = "市场信息编号")
    private String marketInfoNum;
    /**
     * 省份
     */
    @ExcelHeader(name = "省份",notNull = true)
    private String provinces;
    /**
     * 接触阶段
     */
    @ExcelHeader(name = "接触阶段",notNull = true)
    private ContactPhace contactPhace;
    /**
     * 时效性
     */
    @ExcelHeader(name = "时效性",notNull = true)
    private Timeliness timeliness;
    /**
     * 亲密度
     */
    @ExcelHeader(name = "亲密度",notNull = true)
    private Intimacy intimacy;
    /**
     * 难易度
     */
    @ExcelHeader(name = "难易度",notNull = true)
    private DifficultyLevel difficultyLevel;
    /**
     * 拜访状态
     */
    @ExcelHeader(name = "拜访状态",notNull = true)
    private VisitStatus visitStatus;
    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型",notNull = true)
    private String businessType;
    /**
     * 业务方向-科目
     */
    @ExcelHeader(name = "业务方向-科目",notNull = true)
    private String businessWay;
    /**
     * 关联项目
     */
    @ExcelHeader(name = "关联项目")
    private String relatedProject;
    /**
     * 合作项目区域分布
     */
    @ExcelHeader(name = "合作项目区域分布")
    private String cooProjectAreaDistri;
    /**
     * 以前合作过的公司
     */
    @ExcelHeader(name = "以前合作过的公司")
    private String beforeCooCompanise;
    /**
     * 现业务有关联公司
     */
    @ExcelHeader(name = "现业务有关联公司")
    private String currentBusiness;
    /**
     * 未来发展方向
     */
    @ExcelHeader(name = "未来发展方向")
    private String futureDevelopment;
    /**
     * 对客户合作满意程度
     */
    @ExcelHeader(name = "对客户合作满意程度")
    private Satisfation customerSatisfation;
    /**
     * 客户对我司合作满意程度
     */
    @ExcelHeader(name = "客户对我司合作满意程度")
    private Satisfation satisfationWithCompany;
    /**
     * 扣分事项
     */
    @ExcelHeader(name = "扣分事项")
    private String pointsMatters;
    /**
     * 客户保持率
     */
    @ExcelHeader(name = "客户保持率")
    private Double customerReten;
    /**
     * 商务活动记录
     */
    @ExcelHeader(name = "商务活动记录")
    private String businessActivityRecord;
    /**
     * 市场招待记录汇总
     */
    @ExcelHeader(name = "市场招待记录汇总")
    private String marketReceptionRecord;
    /**
     * 市场信息记录
     */
    @ExcelHeader(name = "市场信息记录")
    private String marketInfoRecord;
    /**
     * 是否需进行市场招待
     */
    @ExcelHeader(name = "是否需进行市场招待")
    private String proceedMarketTreat;

    /**
     * 拜访周期
     */
    @ExcelHeader(name = "拜访周期",notNull = true)
    private Integer callcyle;
    /**
     * 年龄
     */
    @ExcelHeader(name = "年龄")
    private Integer age;

    /**
     * 出生年月日
     */
    @ExcelHeader(name = "出生年月日")
    private LocalDate birthday;

    /**
     * 工作经历
     */
    @ExcelHeader(name = "工作经历")
    private String workExperience;

    /**
     * 毕业学校
     */
    @ExcelHeader(name = "毕业学校")
    private String graduatedSchool;

    /**
     * 求学经历
     */
    @ExcelHeader(name = "求学经历")
    private String studyExperience;

    /**
     * 爱好
     */
    @ExcelHeader(name = "爱好")
    private String love;

    /**
     * 性格评价
     */
    @ExcelHeader(name = "性格评价")
    private String characterEvaluation;

    /**
     * 称谓
     */
    @ExcelHeader(name = "称谓")
    private String title;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名")
    private String name;

    /**
     * 联系方式
     */
    @ExcelHeader(name = "联系方式")
    private String relationWay;

    /**
     * 性格爱好
     */
    @ExcelHeader(name = "性格爱好")
    private String charactLove;


    /**
     * 工作单位
     */
    @ExcelHeader(name = "工作单位")
    private String workPlace;

    /**
     * 职位
     */
    @ExcelHeader(name = "职位")
    private String jobPost;

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

    public String getCustomerLevelName() {
        return customerLevelName;
    }

    public void setCustomerLevelName(String customerLevelName) {
        this.customerLevelName = customerLevelName;
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

    public WorkRight getWorkRight() {
        return workRight;
    }

    public void setWorkRight(WorkRight workRight) {
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

    public LocalDateTime getMarketReceptTime() {
        return marketReceptTime;
    }

    public void setMarketReceptTime(LocalDateTime marketReceptTime) {
        this.marketReceptTime = marketReceptTime;
    }

    public String getInfoComplet() {
        return infoComplet;
    }

    public void setInfoComplet(String infoComplet) {
        this.infoComplet = infoComplet;
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

    public VisitStatus getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(VisitStatus visitStatus) {
        this.visitStatus = visitStatus;
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

    public String getProceedMarketTreat() {
        return proceedMarketTreat;
    }

    public void setProceedMarketTreat(String proceedMarketTreat) {
        this.proceedMarketTreat = proceedMarketTreat;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getGraduatedSchool() {
        return graduatedSchool;
    }

    public void setGraduatedSchool(String graduatedSchool) {
        this.graduatedSchool = graduatedSchool;
    }

    public String getStudyExperience() {
        return studyExperience;
    }

    public void setStudyExperience(String studyExperience) {
        this.studyExperience = studyExperience;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getCharacterEvaluation() {
        return characterEvaluation;
    }

    public void setCharacterEvaluation(String characterEvaluation) {
        this.characterEvaluation = characterEvaluation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationWay() {
        return relationWay;
    }

    public void setRelationWay(String relationWay) {
        this.relationWay = relationWay;
    }

    public String getCharactLove() {
        return charactLove;
    }

    public void setCharactLove(String charactLove) {
        this.charactLove = charactLove;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    public Integer getCallcyle() {
        return callcyle;
    }

    public void setCallcyle(Integer callcyle) {
        this.callcyle = callcyle;
    }
}