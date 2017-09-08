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
     * 用户信息
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用户信息'", unique = true)
    private String userId;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "organize_position_detail_user_table", joinColumns = {@JoinColumn(name = "user_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '用户id'")},
            inverseJoinColumns = {@JoinColumn(name = "position_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '岗位详细id'")})
    private Set<PositionDetail> positionSet = new HashSet<>(0);

    /**
     * 人员状态
     */
    @Column(columnDefinition = "TINYINT(2) comment '人员状态' ",nullable = false)
    private StaffStatus staffStatus;

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<PositionDetail> getPositionSet() {
        return positionSet;
    }

    public void setPositionSet(Set<PositionDetail> positionSet) {
        this.positionSet = positionSet;
    }
}