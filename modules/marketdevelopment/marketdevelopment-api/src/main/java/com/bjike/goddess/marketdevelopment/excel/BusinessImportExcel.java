package com.bjike.goddess.marketdevelopment.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 业务对象业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-28 02:54 ]
 * @Description: [ 业务对象业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessImportExcel extends BaseBO {

//    /**
//     * 业务对象编号
//     */
//    private String businessNum;

    /**
     * 业务对象类型
     */
    @ExcelHeader(name = "业务对象类型", notNull = true)
    private String businessType;

//    /**
//     * 公司名称编号
//     */
//    private String companyNum;

    /**
     * 业务对象-公司名称
     */
    @ExcelHeader(name = "业务对象-公司名称", notNull = true)
    private String company;

    /**
     * 可发展业务方向-科目
     */
    @ExcelHeader(name = "可发展业务方向-科目", notNull = true)
    private String subject;

//    /**
//     * 可发展业务-科目合计
//     */
//    private Integer sum;


    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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
}