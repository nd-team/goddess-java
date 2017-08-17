package com.bjike.goddess.salarymanage.to;

import java.io.Serializable;

/**
 * 基本信息导出条件
 * Created by haikuang on 17-8-2.
 */
public class ExportSalaryBasicTO implements Serializable{
    /**
     * 地区
     */
    private String  area;


    /**
     * 部门/项目组
     */
    private String  department;


    public String getArea () {
        return area;
    }
    public void setArea (String area ) {
        this.area = area ;
    }
    public String getDepartment () {
        return department;
    }
    public void setDepartment (String department ) {
        this.department = department ;
    }
}
