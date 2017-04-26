package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 岗位详细展示对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PositionDetailTO extends BaseTO {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空", groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 部门id
     */
    @NotNull(message = "部门id不能为空", groups = {ADD.class, EDIT.class})
    private String departmentId;

    /**
     * 层级ID
     */
    @NotNull(message = "层级ID不能为空", groups = {ADD.class, EDIT.class})
    private String arrangementId;

    /**
     * 资源池
     */
    @NotNull(message = "资源池不能为空", groups = {ADD.class, EDIT.class})
    private String pool;

    /**
     * 模块id
     */
    @NotNull(message = "模块id不能为空", groups = {ADD.class, EDIT.class})
    private String moduleId;

    /**
     * 岗位
     */
    @NotNull(message = "岗位不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 人员编制数
     */
    @NotNull(message = "人员编制数不能为空", groups = {ADD.class, EDIT.class})
    private Integer staff;

    /**
     * 当前人数
     */
    private String current;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(String arrangementId) {
        this.arrangementId = arrangementId;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
