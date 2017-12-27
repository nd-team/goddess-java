package com.bjike.goddess.socialinsurance.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 16:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SocialInsuranceImportExcel {

    /**
     * 税费所属时期
     */
    @ExcelHeader(name = "税费所属时期")
    private String taxDate;

    /**
     * 纳税人名称
     */
    @ExcelHeader(name = "纳税人名称")
    private String taxpayer;

    /**
     * 单位社保号
     */
    @ExcelHeader(name = "单位社保号")
    private String companySSNumber;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名")
    private String name;

    /**
     * 身份证明号码
     */
    @ExcelHeader(name = "身份证明号码")
    private String idCard;

    /**
     * 证件名称
     */
    @ExcelHeader(name = "证件名称")
    private String cardName;

    /**
     * 个人社保号
     */
    @ExcelHeader(name = "个人社保号")
    private String personalSSNumber;

//    /**
//     * 基本养老保险
//     */
//    @ExcelHeader(name = "")
//    private String parent1;

    /**
     * 基本养老保险/计费工资
     */
    @ExcelHeader(name = "基本养老保险/计费工资")
    private Double pensionInsuranceWage;

    /**
     * 基本养老保险/单位
     */
    @ExcelHeader(name = "基本养老保险/单位")
    private Double pensionInsuranceCompany;

    /**
     * 基本养老保险/个人
     */
    @ExcelHeader(name = "基本养老保险/个人")
    private Double pensionInsurancePerson;
//
//    /**
//     * 工伤保险
//     */
//    @ExcelHeader(name = "")
//    private String parent2;

    /**
     * 工伤保险/计费工资
     */
    @ExcelHeader(name = "工伤保险/计费工资")
    private Double injuryInsuranceWage;

    /**
     * 工伤保险/单位
     */
    @ExcelHeader(name = "工伤保险/单位")
    private Double injuryInsuranceCompany;

    /**
     * 工伤保险/个人
     */
    @ExcelHeader(name = "工伤保险/个人")
    private Double injuryInsurancePerson;

    /**
     * 失业保险
     */
//    @ExcelHeader(name = "失业保险")
//    private String parent3;

    /**
     * 失业保险/计费工资
     */
    @ExcelHeader(name = "失业保险/计费工资")
    private Double unemploymentInsuranceWage;

    /**
     * 失业保险/单位
     */
    @ExcelHeader(name = "失业保险/单位")
    private Double unemploymentInsuranceCompany;

    /**
     * 失业保险/个人
     */
    @ExcelHeader(name = "失业保险/个人")
    private Double unemploymentInsurancePerson;

    /**
     * 职工社会医疗保险
     */
//    @ExcelHeader(name = "职工社会医疗保险")
//    private String parent4;

    /**
     * 职工社会医疗保险/计费工资
     */
    @ExcelHeader(name = "职工社会医疗保险/计费工资")
    private Double medicalInsuranceWage;

    /**
     * 职工社会医疗保险/单位
     */
    @ExcelHeader(name = "职工社会医疗保险/单位")
    private Double medicalInsuranceCompany;

    /**
     * 职工社会医疗保险/个人
     */
    @ExcelHeader(name = "职工社会医疗保险/个人")
    private Double medicalInsurancePerson;

    /**
     * 职工重大疾病医疗补助
     */
//    @ExcelHeader(name = "职工重大疾病医疗补助")
//    private String parent5;

    /**
     * 职工重大疾病医疗补助/计费工资
     */
    @ExcelHeader(name = "职工重大疾病医疗补助/计费工资")
    private Double diseaseInsuranceWage;

    /**
     * 职工重大疾病医疗补助/单位
     */
    @ExcelHeader(name = "职工重大疾病医疗补助/单位")
    private Double diseaseInsuranceCompany;

    /**
     * 职工重大疾病医疗补助/个人
     */
    @ExcelHeader(name = "职工重大疾病医疗补助/个人")
    private Double diseaseInsurancePerson;

    /**
     * 生育保险
     */
//    @ExcelHeader(name = "生育保险")
//    private String parent6;

    /**
     * 生育保险/计费工资
     */
    @ExcelHeader(name = "生育保险/计费工资")
    private Double fertilityInsuranceWage;

    /**
     * 生育保险/单位
     */
    @ExcelHeader(name = "生育保险/单位")
    private Double fertilityInsuranceCompany;

    /**
     * 生育保险/个人
     */
    @ExcelHeader(name = "生育保险/个人")
    private Double fertilityInsurancePerson;

    /**
     * 单位合计
     */
    @ExcelHeader(name = "单位合计")
    private Double companyTotal;

    /**
     * 个人合计
     */
    @ExcelHeader(name = "个人合计")
    private Double personalTotal;

    /**
     * 应缴金额
     */
    @ExcelHeader(name = "应缴金额")
    private Double amountDue;



    public String getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(String taxDate) {
        this.taxDate = taxDate;
    }

    public String getTaxpayer() {
        return taxpayer;
    }

    public void setTaxpayer(String taxpayer) {
        this.taxpayer = taxpayer;
    }

    public String getCompanySSNumber() {
        return companySSNumber;
    }

    public void setCompanySSNumber(String companySSNumber) {
        this.companySSNumber = companySSNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getPersonalSSNumber() {
        return personalSSNumber;
    }

    public void setPersonalSSNumber(String personalSSNumber) {
        this.personalSSNumber = personalSSNumber;
    }

    public Double getPensionInsuranceWage() {
        return pensionInsuranceWage;
    }

    public void setPensionInsuranceWage(Double pensionInsuranceWage) {
        this.pensionInsuranceWage = pensionInsuranceWage;
    }

    public Double getPensionInsuranceCompany() {
        return pensionInsuranceCompany;
    }

    public void setPensionInsuranceCompany(Double pensionInsuranceCompany) {
        this.pensionInsuranceCompany = pensionInsuranceCompany;
    }

    public Double getPensionInsurancePerson() {
        return pensionInsurancePerson;
    }

    public void setPensionInsurancePerson(Double pensionInsurancePerson) {
        this.pensionInsurancePerson = pensionInsurancePerson;
    }

    public Double getInjuryInsuranceWage() {
        return injuryInsuranceWage;
    }

    public void setInjuryInsuranceWage(Double injuryInsuranceWage) {
        this.injuryInsuranceWage = injuryInsuranceWage;
    }

    public Double getInjuryInsuranceCompany() {
        return injuryInsuranceCompany;
    }

    public void setInjuryInsuranceCompany(Double injuryInsuranceCompany) {
        this.injuryInsuranceCompany = injuryInsuranceCompany;
    }

    public Double getInjuryInsurancePerson() {
        return injuryInsurancePerson;
    }

    public void setInjuryInsurancePerson(Double injuryInsurancePerson) {
        this.injuryInsurancePerson = injuryInsurancePerson;
    }

    public Double getUnemploymentInsuranceWage() {
        return unemploymentInsuranceWage;
    }

    public void setUnemploymentInsuranceWage(Double unemploymentInsuranceWage) {
        this.unemploymentInsuranceWage = unemploymentInsuranceWage;
    }

    public Double getUnemploymentInsuranceCompany() {
        return unemploymentInsuranceCompany;
    }

    public void setUnemploymentInsuranceCompany(Double unemploymentInsuranceCompany) {
        this.unemploymentInsuranceCompany = unemploymentInsuranceCompany;
    }

    public Double getUnemploymentInsurancePerson() {
        return unemploymentInsurancePerson;
    }

    public void setUnemploymentInsurancePerson(Double unemploymentInsurancePerson) {
        this.unemploymentInsurancePerson = unemploymentInsurancePerson;
    }

    public Double getMedicalInsuranceWage() {
        return medicalInsuranceWage;
    }

    public void setMedicalInsuranceWage(Double medicalInsuranceWage) {
        this.medicalInsuranceWage = medicalInsuranceWage;
    }

    public Double getMedicalInsuranceCompany() {
        return medicalInsuranceCompany;
    }

    public void setMedicalInsuranceCompany(Double medicalInsuranceCompany) {
        this.medicalInsuranceCompany = medicalInsuranceCompany;
    }

    public Double getMedicalInsurancePerson() {
        return medicalInsurancePerson;
    }

    public void setMedicalInsurancePerson(Double medicalInsurancePerson) {
        this.medicalInsurancePerson = medicalInsurancePerson;
    }

    public Double getDiseaseInsuranceWage() {
        return diseaseInsuranceWage;
    }

    public void setDiseaseInsuranceWage(Double diseaseInsuranceWage) {
        this.diseaseInsuranceWage = diseaseInsuranceWage;
    }

    public Double getDiseaseInsuranceCompany() {
        return diseaseInsuranceCompany;
    }

    public void setDiseaseInsuranceCompany(Double diseaseInsuranceCompany) {
        this.diseaseInsuranceCompany = diseaseInsuranceCompany;
    }

    public Double getDiseaseInsurancePerson() {
        return diseaseInsurancePerson;
    }

    public void setDiseaseInsurancePerson(Double diseaseInsurancePerson) {
        this.diseaseInsurancePerson = diseaseInsurancePerson;
    }

    public Double getFertilityInsuranceWage() {
        return fertilityInsuranceWage;
    }

    public void setFertilityInsuranceWage(Double fertilityInsuranceWage) {
        this.fertilityInsuranceWage = fertilityInsuranceWage;
    }

    public Double getFertilityInsuranceCompany() {
        return fertilityInsuranceCompany;
    }

    public void setFertilityInsuranceCompany(Double fertilityInsuranceCompany) {
        this.fertilityInsuranceCompany = fertilityInsuranceCompany;
    }

    public Double getFertilityInsurancePerson() {
        return fertilityInsurancePerson;
    }

    public void setFertilityInsurancePerson(Double fertilityInsurancePerson) {
        this.fertilityInsurancePerson = fertilityInsurancePerson;
    }

    public Double getCompanyTotal() {
        return companyTotal;
    }

    public void setCompanyTotal(Double companyTotal) {
        this.companyTotal = companyTotal;
    }

    public Double getPersonalTotal() {
        return personalTotal;
    }

    public void setPersonalTotal(Double personalTotal) {
        this.personalTotal = personalTotal;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }

//    public String getArea() {
//        return area;
//    }
//
//    public void setArea(String area) {
//        this.area = area;
//    }
//
//    public String getParent1() {
//        return parent1;
//    }
//
//    public void setParent1(String parent1) {
//        this.parent1 = parent1;
//    }
//
//    public String getParent2() {
//        return parent2;
//    }
//
//    public void setParent2(String parent2) {
//        this.parent2 = parent2;
//    }
//
//    public String getParent3() {
//        return parent3;
//    }
//
//    public void setParent3(String parent3) {
//        this.parent3 = parent3;
//    }
//
//    public String getParent4() {
//        return parent4;
//    }
//
//    public void setParent4(String parent4) {
//        this.parent4 = parent4;
//    }
//
//    public String getParent5() {
//        return parent5;
//    }
//
//    public void setParent5(String parent5) {
//        this.parent5 = parent5;
//    }
//
//    public String getParent6() {
//        return parent6;
//    }
//
//    public void setParent6(String parent6) {
//        this.parent6 = parent6;
//    }
}

