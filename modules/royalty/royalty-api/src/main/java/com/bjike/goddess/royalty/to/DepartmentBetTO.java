package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 部门间对赌表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:16 ]
 * @Description: [ 部门间对赌表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetTO extends BaseTO {

    /**
     * 部门间对赌表A
     */
    private DepartmentBetATO departmentBetATO;

    /**
     * 部门间对赌表B
     */
    private List<DepartmentBetBTO> departmentBetBTOS;

    public DepartmentBetATO getDepartmentBetATO() {
        return departmentBetATO;
    }

    public void setDepartmentBetATO(DepartmentBetATO departmentBetATO) {
        this.departmentBetATO = departmentBetATO;
    }

    public List<DepartmentBetBTO> getDepartmentBetBTOS() {
        return departmentBetBTOS;
    }

    public void setDepartmentBetBTOS(List<DepartmentBetBTO> departmentBetBTOS) {
        this.departmentBetBTOS = departmentBetBTOS;
    }
}