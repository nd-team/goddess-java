package com.bjike.goddess.royalty.vo;

import java.util.List;

/**
 * 部门间对赌表B表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表B表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetBVO {

    /**
     * id
     */
    private String id;
    /**
     * 体系
     */
    private String system;

    /**
     * 部门间对赌表C
     */
    private List<DepartmentBetCVO> departmentBetCBOS;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public List<DepartmentBetCVO> getDepartmentBetCBOS() {
        return departmentBetCBOS;
    }

    public void setDepartmentBetCBOS(List<DepartmentBetCVO> departmentBetCBOS) {
        this.departmentBetCBOS = departmentBetCBOS;
    }
}