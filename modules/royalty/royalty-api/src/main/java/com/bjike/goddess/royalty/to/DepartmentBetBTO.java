package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 部门间对赌表B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetBTO extends BaseTO {

    /**
     * 体系
     */
    private String system;

    /**
     * 部门间对赌表C
     */
    private List<DepartmentBetCTO> departmentBetCTOS;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public List<DepartmentBetCTO> getDepartmentBetCTOS() {
        return departmentBetCTOS;
    }

    public void setDepartmentBetCTOS(List<DepartmentBetCTO> departmentBetCTOS) {
        this.departmentBetCTOS = departmentBetCTOS;
    }
}