package com.bjike.goddess.secure.vo;

/**
 * 缴费比例明细表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 12:55 ]
 * @Description: [ 缴费比例明细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PayDetailVO {

    /**
     * id
     */
    private String id;
    /**
     * 年度
     */
    private String annual;

    /**
     * 参保地市
     */
    private String city;

    /**
     * 公司总缴费比例
     */
    private String expendScaleCompany;

    /**
     * 公司缴费比例/工伤
     */
    private String injuryCompany;

    /**
     * 公司缴费比例/失业
     */
    private String outWorkCompany;

    /**
     * 公司缴费比例/生育
     */
    private String fertilityCompany;

    /**
     * 公司缴费比例/医疗
     */
    private String medicalCompany;

    /**
     * 公司缴费比例/养老
     */
    private String oldCompany;

    /**
     * 公司缴费比例/疾病医疗补助
     */
    private String diseaseCompany;

    /**
     * 个人总缴费比例
     */
    private String expendScalePerson;

    /**
     * 个人缴费比例/失业
     */
    private String outWorkPerson;

    /**
     * 个人缴费比例/医疗
     */
    private String medicalPerson;

    /**
     * 个人缴费比例/养老
     */
    private String oldPerson;

    /**
     * 参保承担总费用
     */
    private Double total;

    /**
     * 参保承担费用/公司
     */
    private Double totalCompany;

    /**
     * 参保承担费用/个人
     */
    private Double totalPerson;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExpendScaleCompany() {
        return expendScaleCompany;
    }

    public void setExpendScaleCompany(String expendScaleCompany) {
        this.expendScaleCompany = expendScaleCompany;
    }

    public String getInjuryCompany() {
        return injuryCompany;
    }

    public void setInjuryCompany(String injuryCompany) {
        this.injuryCompany = injuryCompany;
    }

    public String getOutWorkCompany() {
        return outWorkCompany;
    }

    public void setOutWorkCompany(String outWorkCompany) {
        this.outWorkCompany = outWorkCompany;
    }

    public String getFertilityCompany() {
        return fertilityCompany;
    }

    public void setFertilityCompany(String fertilityCompany) {
        this.fertilityCompany = fertilityCompany;
    }

    public String getMedicalCompany() {
        return medicalCompany;
    }

    public void setMedicalCompany(String medicalCompany) {
        this.medicalCompany = medicalCompany;
    }

    public String getOldCompany() {
        return oldCompany;
    }

    public void setOldCompany(String oldCompany) {
        this.oldCompany = oldCompany;
    }

    public String getDiseaseCompany() {
        return diseaseCompany;
    }

    public void setDiseaseCompany(String diseaseCompany) {
        this.diseaseCompany = diseaseCompany;
    }

    public String getExpendScalePerson() {
        return expendScalePerson;
    }

    public void setExpendScalePerson(String expendScalePerson) {
        this.expendScalePerson = expendScalePerson;
    }

    public String getOutWorkPerson() {
        return outWorkPerson;
    }

    public void setOutWorkPerson(String outWorkPerson) {
        this.outWorkPerson = outWorkPerson;
    }

    public String getMedicalPerson() {
        return medicalPerson;
    }

    public void setMedicalPerson(String medicalPerson) {
        this.medicalPerson = medicalPerson;
    }

    public String getOldPerson() {
        return oldPerson;
    }

    public void setOldPerson(String oldPerson) {
        this.oldPerson = oldPerson;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalCompany() {
        return totalCompany;
    }

    public void setTotalCompany(Double totalCompany) {
        this.totalCompany = totalCompany;
    }

    public Double getTotalPerson() {
        return totalPerson;
    }

    public void setTotalPerson(Double totalPerson) {
        this.totalPerson = totalPerson;
    }
}