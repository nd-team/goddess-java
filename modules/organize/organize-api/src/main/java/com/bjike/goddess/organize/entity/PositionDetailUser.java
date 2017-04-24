package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "organize_position_detail_user_table", joinColumns = {@JoinColumn(name = "user_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "position_id", nullable = false)})
    private Set<PositionDetail> positionSet;


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