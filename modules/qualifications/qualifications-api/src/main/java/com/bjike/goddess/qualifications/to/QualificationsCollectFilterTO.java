package com.bjike.goddess.qualifications.to;

import java.io.Serializable;

/**
 * 资质办理进度汇总查询过滤条件
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-20 11:19]
 * @Description: [ 资质办理进度汇总查询过滤条件 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class QualificationsCollectFilterTO implements Serializable {

    /**
     * 资质名称
     */
    private String qualifications;

    /**
     * 公司名称
     */
    private String company;

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
