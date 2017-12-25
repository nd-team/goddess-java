package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 部门间对赌表B业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表B业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetBBO extends BaseBO {
    /**
     * 体系
     */
    private String system;

    /**
     * 部门间对赌表C
     */
    private List<DepartmentBetCBO> departmentBetCBOS;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public List<DepartmentBetCBO> getDepartmentBetCBOS() {
        return departmentBetCBOS;
    }

    public void setDepartmentBetCBOS(List<DepartmentBetCBO> departmentBetCBOS) {
        this.departmentBetCBOS = departmentBetCBOS;
    }
}