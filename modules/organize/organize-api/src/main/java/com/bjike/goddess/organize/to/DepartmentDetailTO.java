package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 部门详细展示对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentDetailTO extends BaseTO {


    /**
     * 编号
     */
    @NotNull(message = "编号不能为空", groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 体系ID
     */
    @NotNull(message = "体系ID不能为空", groups = {ADD.class, EDIT.class})
    private String hierarchyId;

    /**
     * 项目组/部门
     */
    @NotNull(message = "部门不能为空", groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 所属地区
     */
    @NotNull(message = "所属地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 成本分类
     */
    @NotNull(message = "成本分类不能为空", groups = {ADD.class, EDIT.class})
    private String classify;

    /**
     * 描述
     */
    private String description;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(String hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
