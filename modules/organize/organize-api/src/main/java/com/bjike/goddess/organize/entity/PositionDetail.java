package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

/**
 * 岗位详细
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "organize_position_detail")
public class PositionDetail extends BaseEntity {

    /**
     * 编号
     */
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(50) COMMENT '编号'")
    private String serialNumber;

    /**
     * 部门
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "department_id", columnDefinition = "VARCHAR(36) COMMENT '部门'")
    private DepartmentDetail department;

    /**
     * 层级
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "arrangement_id", columnDefinition = "VARCHAR(36) COMMENT '层级'")
    private Arrangement arrangement;

    /**
     * 资源池
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '资源池'")
    private String pool;

    /**
     * 模块
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id", columnDefinition = "VARCHAR(36) COMMENT '模块'")
    private ModuleType module;

    /**
     * 岗位名称
     */
    @JoinColumn(nullable = false, columnDefinition = "VARCHAR(36) COMMENT '岗位名称'", unique = true)
    private String position;

    /**
     * 人员编制数
     */
    @Range(min = 0)
    @Column(columnDefinition = "INT COMMENT '人员编制数'")
    private Integer staff;

    /**
     * 描述
     */
    @Column(columnDefinition = "VARCHAR(255) comment '描述' ")
    private String description;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '岗位状态' ", nullable = false, insertable = false)
    private Status status;
    /**
     * 是否有岗位说明书
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否有岗位说明书' ", nullable = false)
    private Boolean book;

    public Boolean getBook() {
        return book;
    }

    public void setBook(Boolean book) {
        this.book = book;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DepartmentDetail getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDetail department) {
        this.department = department;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public ModuleType getModule() {
        return module;
    }

    public void setModule(ModuleType module) {
        this.module = module;
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

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
