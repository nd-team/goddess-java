package com.bjike.goddess.intromanage.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 成功案例导出
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:49 ]
 * @Description: [ 成功案例导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SuccessStoriesExport extends BaseBO {

    /**
     * 通信类
     */
    @ExcelHeader(name="通信类")
    private String communication;

    /**
     * 软件类
     */
    @ExcelHeader(name="软件类")
    private String software;

    /**
     * 系统集成类
     */
    @ExcelHeader(name="系统集成类")
    private String systemIntegration;

    /**
     * 营销策划类
     */
    @ExcelHeader(name="营销策划类")
    private String marketingPlanning;


    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getSystemIntegration() {
        return systemIntegration;
    }

    public void setSystemIntegration(String systemIntegration) {
        this.systemIntegration = systemIntegration;
    }

    public String getMarketingPlanning() {
        return marketingPlanning;
    }

    public void setMarketingPlanning(String marketingPlanning) {
        this.marketingPlanning = marketingPlanning;
    }
}