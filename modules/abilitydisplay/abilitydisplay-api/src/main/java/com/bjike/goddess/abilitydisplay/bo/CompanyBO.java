package com.bjike.goddess.abilitydisplay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 公司业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:38 ]
 * @Description: [ 公司业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyBO extends BaseBO {

    /**
     * 用途类型String
     */
    private String type;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 公司英文名称
     */
    private String nameEn;

    /**
     * 官网
     */
    private String officialWeb;

    /**
     * 地区
     */
    private String area;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 注册地
     */
    private String placeREG;

    /**
     * 注册资金
     */
    private Double capitalREG;

    /**
     * 组成人员
     */
    private List<PersonnelBO> personnelBOS;

    /**
     * 设备
     */
    private String equipment;

    /**
     * 公司规划
     */
    private List<CompanyPlanBO> planBOS;

    /**
     * 公司认证
     */
    private List<CompanyAuthBO> authBOS;

    /**
     * 公司业务
     */
    private List<CompanyBNBO> businessBOS;

    /**
     * 公司文化
     */
    private List<ComCulturalBO> culturalBOS;

    /**
     * 公司联系人
     */
    private List<CompanyConBO> contactsBOS;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getOfficialWeb() {
        return officialWeb;
    }

    public void setOfficialWeb(String officialWeb) {
        this.officialWeb = officialWeb;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceREG() {
        return placeREG;
    }

    public void setPlaceREG(String placeREG) {
        this.placeREG = placeREG;
    }

    public Double getCapitalREG() {
        return capitalREG;
    }

    public void setCapitalREG(Double capitalREG) {
        this.capitalREG = capitalREG;
    }

    public List<PersonnelBO> getPersonnelBOS() {
        return personnelBOS;
    }

    public void setPersonnelBOS(List<PersonnelBO> personnelBOS) {
        this.personnelBOS = personnelBOS;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public List<CompanyPlanBO> getPlanBOS() {
        return planBOS;
    }

    public void setPlanBOS(List<CompanyPlanBO> planBOS) {
        this.planBOS = planBOS;
    }

    public List<CompanyAuthBO> getAuthBOS() {
        return authBOS;
    }

    public void setAuthBOS(List<CompanyAuthBO> authBOS) {
        this.authBOS = authBOS;
    }

    public List<CompanyBNBO> getBusinessBOS() {
        return businessBOS;
    }

    public void setBusinessBOS(List<CompanyBNBO> businessBOS) {
        this.businessBOS = businessBOS;
    }

    public List<ComCulturalBO> getCulturalBOS() {
        return culturalBOS;
    }

    public void setCulturalBOS(List<ComCulturalBO> culturalBOS) {
        this.culturalBOS = culturalBOS;
    }

    public List<CompanyConBO> getContactsBOS() {
        return contactsBOS;
    }

    public void setContactsBOS(List<CompanyConBO> contactsBOS) {
        this.contactsBOS = contactsBOS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}