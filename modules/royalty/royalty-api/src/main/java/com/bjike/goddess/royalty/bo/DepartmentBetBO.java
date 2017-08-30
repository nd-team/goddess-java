package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 部门间对赌表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:16 ]
 * @Description: [ 部门间对赌表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetBO extends BaseBO {

    /**
     * 部门间对赌表A
     */
    private DepartmentBetABO departmentBetABO;

    /**
     * 部门间对赌表B
     */
    private List<DepartmentBetBBO> departmentBetBBOS;


    public DepartmentBetABO getDepartmentBetABO() {
        return departmentBetABO;
    }

    public void setDepartmentBetABO(DepartmentBetABO departmentBetABO) {
        this.departmentBetABO = departmentBetABO;
    }

    public List<DepartmentBetBBO> getDepartmentBetBBOS() {
        return departmentBetBBOS;
    }

    public void setDepartmentBetBBOS(List<DepartmentBetBBO> departmentBetBBOS) {
        this.departmentBetBBOS = departmentBetBBOS;
    }
}