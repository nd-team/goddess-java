package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 部门工作范围展示对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-09 11:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentWorkRangeTO extends BaseTO {

    /**
     * 项目组/部门id
     */
    private String department_id;

    /**
     * 工作范围ID数组
     */
    private String[] range_ids;

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String[] getRange_ids() {
        return range_ids;
    }

    public void setRange_ids(String[] range_ids) {
        this.range_ids = range_ids;
    }
}
