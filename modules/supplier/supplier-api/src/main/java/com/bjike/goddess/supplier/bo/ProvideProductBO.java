package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * 针对拟为我公司提供产品业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:06 ]
 * @Description: [ 针对拟为我公司提供产品业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProvideProductBO extends BaseBO {

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 产品/服务内容
     */
    private String productContent;

    /**
     * 合作时间
     */
    private LocalDate cooperationDate;

    /**
     * 合作期限
     */
    private String cooperationTimeLimit;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public LocalDate getCooperationDate() {
        return cooperationDate;
    }

    public void setCooperationDate(LocalDate cooperationDate) {
        this.cooperationDate = cooperationDate;
    }

    public String getCooperationTimeLimit() {
        return cooperationTimeLimit;
    }

    public void setCooperationTimeLimit(String cooperationTimeLimit) {
        this.cooperationTimeLimit = cooperationTimeLimit;
    }
}