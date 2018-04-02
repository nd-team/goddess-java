package com.bjike.goddess.abilitydisplay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;


/**
 * 公司
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:38 ]
 * @Description: [ 公司 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "abilitydisplay_company")
public class Company extends BaseEntity {

    /**
     * 用途类型
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '用途类型String'")
    private String type;

    /**
     * 公司名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String name;

    /**
     * 公司英文名称
     */
    @Column(name = "nameEn", columnDefinition = "VARCHAR(255)   COMMENT '公司英文名称'")
    private String nameEn;

    /**
     * 官网
     */
    @Column(name = "officialWeb", columnDefinition = "VARCHAR(255)   COMMENT '官网'")
    private String officialWeb;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 公司地址
     */
    @Column(name = "address", columnDefinition = "VARCHAR(255)   COMMENT '公司地址'")
    private String address;

    /**
     * 注册地
     */
    @Column(name = "placeREG", columnDefinition = "VARCHAR(255)   COMMENT '注册地'")
    private String placeREG;

    /**
     * 注册资金
     */
    @Column(name = "capitalREG", columnDefinition = "DECIMAL(10,2)   COMMENT '注册资金'")
    private Double capitalREG;

    /**
     * 组成人员
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
    private List<Personnel> personnels;

    /**
     * 设备
     */
    @Column(name = "equipment", columnDefinition = "VARCHAR(255)   COMMENT '设备'")
    private String equipment;

    /**
     * 公司规划
     */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private List<CompanyPlan> plans;

    /**
     * 公司认证
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private List<CompanyAuth> auths;

    /**
     * 公司业务
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private List<CompanyBN> business;

    /**
     * 公司文化
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private List<ComCultural> culturals;

    /**
     * 公司联系人
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private List<CompanyCon> contacts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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


    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }


    public List<CompanyBN> getBusiness() {
        return business;
    }

    public void setBusiness(List<CompanyBN> business) {
        this.business = business;
    }

    public List<CompanyCon> getContacts() {
        return contacts;
    }

    public void setContacts(List<CompanyCon> contacts) {
        this.contacts = contacts;
    }

    public List<Personnel> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(List<Personnel> personnels) {
        this.personnels = personnels;
    }

    public List<CompanyPlan> getPlans() {
        return plans;
    }

    public void setPlans(List<CompanyPlan> plans) {
        this.plans = plans;
    }

    public List<CompanyAuth> getAuths() {
        return auths;
    }

    public void setAuths(List<CompanyAuth> auths) {
        this.auths = auths;
    }

    public List<ComCultural> getCulturals() {
        return culturals;
    }

    public void setCulturals(List<ComCultural> culturals) {
        this.culturals = culturals;
    }

    @Override
    public String toString() {
        return "Company{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", officialWeb='" + officialWeb + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", placeREG='" + placeREG + '\'' +
                ", capitalREG=" + capitalREG +
                ", personnels=" + personnels +
                ", equipment='" + equipment + '\'' +
                ", plans=" + plans +
                ", auths=" + auths +
                ", business=" + business +
                ", culturals=" + culturals +
                ", contacts=" + contacts +
                '}';
    }
}