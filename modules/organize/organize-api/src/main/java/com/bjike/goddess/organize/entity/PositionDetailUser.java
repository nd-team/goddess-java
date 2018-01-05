package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.organize.enums.StaffStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * 用户职位
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "organize_position_detail_user")
public class PositionDetailUser extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'", unique = true)
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "number", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'", unique = true)
    private String number;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "organize_position_detail_user_table", joinColumns = {@JoinColumn(name = "user_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '用户id'")},
            inverseJoinColumns = {@JoinColumn(name = "position_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '岗位详细id'")})
    private Set<PositionDetail> positionSet = new HashSet<>(0);

    /**
     * 人员状态
     */
    @Column(columnDefinition = "TINYINT(2) comment '人员状态' ", nullable = false)
    private StaffStatus staffStatus;

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<PositionDetail> getPositionSet() {
        return positionSet;
    }

    public void setPositionSet(Set<PositionDetail> positionSet) {
        this.positionSet = positionSet;
    }
}