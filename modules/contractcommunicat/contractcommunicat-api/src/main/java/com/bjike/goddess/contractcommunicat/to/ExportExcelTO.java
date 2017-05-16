package com.bjike.goddess.contractcommunicat.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Excel导出查询条件封装
 *
 * @Author: [Jason]
 * @Date: [17-5-12 下午2:09]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExportExcelTO extends BaseTO {

    /**
     * 内部项目名称
     */
    private String contractInProject;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 开始日期
     */
    private String endDate;

    public String getContractInProject() {
        return contractInProject;
    }

    public void setContractInProject(String contractInProject) {
        this.contractInProject = contractInProject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
