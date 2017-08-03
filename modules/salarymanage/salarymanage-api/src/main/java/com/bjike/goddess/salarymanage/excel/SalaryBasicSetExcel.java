package com.bjike.goddess.salarymanage.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 薪资管理基本资料
 *
 * @Author: [jiangzaixuan]
 * @Date: [2017-08-02 10:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SalaryBasicSetExcel {
    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String  area;

    /**
     * 体系
     */
    @ExcelHeader(name = "体系",notNull = true)
    private String  system;

    /**
     * 部门/项目组
     */
    @ExcelHeader(name = "部门/项目组",notNull = true)
    private String  department;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String  position;

    /**
     * 基本工资
     */
    @ExcelHeader(name = "基本工资",notNull = true)
    private String  basePay;



    public String getArea () {
        return area;
    }
    public void setArea (String area ) {
        this.area = area ;
    }
    public String getSystem () {
        return system;
    }
    public void setSystem (String system ) {
        this.system = system ;
    }
    public String getDepartment () {
        return department;
    }
    public void setDepartment (String department ) {
        this.department = department ;
    }
    public String getPosition () {
        return position;
    }
    public void setPosition (String position ) {
        this.position = position ;
    }
    public String getBasePay () {
        return basePay;
    }
    public void setBasePay (String basePay ) {
        this.basePay = basePay ;
    }
}
