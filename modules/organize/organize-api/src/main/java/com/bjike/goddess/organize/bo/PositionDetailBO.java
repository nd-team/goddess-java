package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 岗位详细传输对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PositionDetailBO extends BaseBO {

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 体系
     */
    private String hierarchyName;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 部门id
     */
    private String departmentId;

    /**
     * 部门
     */
    private String departmentName;

    /**
     * 层级ID
     */
    private String arrangementId;

    /**
     * 层级
     */
    private String arrangementName;

    /**
     * 资源池
     */
    private String pool;

    /**
     * 模块id
     */
    private String moduleId;

    /**
     * 模块
     */
    private String moduleName;

    /**
     * 岗位名称
     */
    private String position;

    /**
     * 人员编制数
     */
    private Integer staff;

    /**
     * 当前人数
     */
    private String current;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 显示编号
     */
    private String showNumber;

    /**
     * 职位状态
     */
    private Status status;
    /**
     * 是否有岗位说明书
     */
    private Boolean book;

    public Boolean getBook() {
        return book;
    }

    public void setBook(Boolean book) {
        this.book = book;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(String arrangementId) {
        this.arrangementId = arrangementId;
    }

    public String getArrangementName() {
        return arrangementName;
    }

    public void setArrangementName(String arrangementName) {
        this.arrangementName = arrangementName;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getStaff() {
        return staff;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(String showNumber) {
        this.showNumber = showNumber;
    }
}
