package com.bjike.goddess.analysis.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Created by ike on 17-7-6.
 */
public class DepartmentTO extends BaseTO{
    public interface Collect {
    }
    /**
     * 项目组
     */
    private String[] department;

    public String[] getDepartment() {
        return department;
    }

    public void setDepartment(String[] department) {
        this.department = department;
    }
}
