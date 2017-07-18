package com.bjike.goddess.royalty.vo;

/**
 * 部门间对赌表D表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表D表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetDVO {

    /**
     * id
     */
    private String id;
    /**
     * 未达标分配部门
     */
    private String unmetAllocationDepartment;

    /**
     * 未达标分配
     */
    private Double unmetAllocation;
    /**
     * 部门间对赌表B
     */
    private DepartmentBetCVO departmentBetCVO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnmetAllocationDepartment() {
        return unmetAllocationDepartment;
    }

    public void setUnmetAllocationDepartment(String unmetAllocationDepartment) {
        this.unmetAllocationDepartment = unmetAllocationDepartment;
    }

    public Double getUnmetAllocation() {
        return unmetAllocation;
    }

    public void setUnmetAllocation(Double unmetAllocation) {
        this.unmetAllocation = unmetAllocation;
    }

    public DepartmentBetCVO getDepartmentBetCVO() {
        return departmentBetCVO;
    }

    public void setDepartmentBetCVO(DepartmentBetCVO departmentBetCVO) {
        this.departmentBetCVO = departmentBetCVO;
    }
}