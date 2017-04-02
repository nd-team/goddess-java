package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 资质办理管理外键id传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-01 11:51]
 * @Description: [ 资质办理管理外键传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class QualificationsHandleForeignTO extends BaseTO {

    /**
     * 人员信息资料ids
     */
    private String[] personnel_ids;

    /**
     * 设备信息资料ids
     */
    private String[] facility_ids;

    /**
     * 公司基本信息ids
     */
    private String[] company_ids;

    /**
     * 财务资料ids
     */
    private String[] finance_ids;

    /**
     * 审核资料ids
     */
    private String[] material_ids;

    public String[] getPersonnel_ids() {
        return personnel_ids;
    }

    public void setPersonnel_ids(String[] personnel_ids) {
        this.personnel_ids = personnel_ids;
    }

    public String[] getFacility_ids() {
        return facility_ids;
    }

    public void setFacility_ids(String[] facility_ids) {
        this.facility_ids = facility_ids;
    }

    public String[] getCompany_ids() {
        return company_ids;
    }

    public void setCompany_ids(String[] company_ids) {
        this.company_ids = company_ids;
    }

    public String[] getFinance_ids() {
        return finance_ids;
    }

    public void setFinance_ids(String[] finance_ids) {
        this.finance_ids = finance_ids;
    }

    public String[] getMaterial_ids() {
        return material_ids;
    }

    public void setMaterial_ids(String[] material_ids) {
        this.material_ids = material_ids;
    }
}
