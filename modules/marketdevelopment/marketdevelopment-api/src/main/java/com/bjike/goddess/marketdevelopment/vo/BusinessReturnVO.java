package com.bjike.goddess.marketdevelopment.vo;

/**
 * 业务对象表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-28 02:54 ]
 * @Description: [ 业务对象表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessReturnVO {

    /**
     * id
     */
    private String id;
    /**
     * 业务对象编号
     */
    private String businessNum;

    /**
     * 业务对象类型
     */
    private String businessType;

    /**
     * 公司名称编号
     */
    private String companyNum;

    /**
     * 业务对象-公司名称
     */
    private String company;

    /**
     * 可发展业务方向-科目
     */
    private String subject;

    /**
     * 可发展业务-科目合计
     */
    private Integer sum;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}