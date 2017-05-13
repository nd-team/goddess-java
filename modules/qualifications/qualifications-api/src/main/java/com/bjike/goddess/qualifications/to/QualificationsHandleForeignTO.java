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
    private String[] personnelIds;

    /**
     * 设备信息资料ids
     */
    private String[] facilityIds;

    /**
     * 公司基本信息ids
     */
    private String[] companyIds;

    /**
     * 财务资料ids
     */
    private String[] financeIds;

    /**
     * 审核资料ids
     */
    private String[] materialIds;

    public String[] getPersonnelIds() {
        return personnelIds;
    }

    public void setPersonnelIds(String[] personnelIds) {
        this.personnelIds = personnelIds;
    }

    public String[] getFacilityIds() {
        return facilityIds;
    }

    public void setFacilityIds(String[] facilityIds) {
        this.facilityIds = facilityIds;
    }

    public String[] getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(String[] companyIds) {
        this.companyIds = companyIds;
    }

    public String[] getFinanceIds() {
        return financeIds;
    }

    public void setFinanceIds(String[] financeIds) {
        this.financeIds = financeIds;
    }

    public String[] getMaterialIds() {
        return materialIds;
    }

    public void setMaterialIds(String[] materialIds) {
        this.materialIds = materialIds;
    }
}
