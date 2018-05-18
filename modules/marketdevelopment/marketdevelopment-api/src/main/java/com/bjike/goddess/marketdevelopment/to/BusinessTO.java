package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 业务对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-28 02:54 ]
 * @Description: [ 业务对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessTO extends BaseTO {

//    /**
//     * 业务对象编号
//     */
//    private String businessNum;

    /**
     * 业务对象类型
     */
    @NotBlank(message = "业务对象类型不能为空", groups = {ADD.class, EDIT.class})
    private String businessType;

//    /**
//     * 公司名称编号
//     */
//    private String companyNum;

    /**
     * 业务对象-公司名称
     */
    @NotBlank(message = "业务对象-公司名称不能为空", groups = {ADD.class, EDIT.class})
    private String company;

    /**
     * 可发展业务方向-科目
     */
    @NotBlank(message = "可发展业务方向-科目不能为空", groups = {ADD.class, EDIT.class})
    private String subject;
//
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