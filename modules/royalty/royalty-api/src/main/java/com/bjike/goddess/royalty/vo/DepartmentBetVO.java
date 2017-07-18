package com.bjike.goddess.royalty.vo;

import java.util.List;

/**
 * 部门间对赌表表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:16 ]
 * @Description: [ 部门间对赌表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetVO {

    /**
     * id
     */
    private String id;
    /**
     * 部门间对赌表A
     */
    private DepartmentBetAVO departmentBetAVO;

    /**
     * 部门间对赌表B
     */
    private List<DepartmentBetBVO> departmentBetBVOS;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DepartmentBetAVO getDepartmentBetAVO() {
        return departmentBetAVO;
    }

    public void setDepartmentBetAVO(DepartmentBetAVO departmentBetAVO) {
        this.departmentBetAVO = departmentBetAVO;
    }

    public List<DepartmentBetBVO> getDepartmentBetBVOS() {
        return departmentBetBVOS;
    }

    public void setDepartmentBetBVOS(List<DepartmentBetBVO> departmentBetBVOS) {
        this.departmentBetBVOS = departmentBetBVOS;
    }
}