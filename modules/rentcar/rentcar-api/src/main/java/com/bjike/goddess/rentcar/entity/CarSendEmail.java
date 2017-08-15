package com.bjike.goddess.rentcar.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 邮箱发送
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 邮箱发送 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "rentcar_carsendemail")
public class CarSendEmail extends BaseEntity {


    /**
     * 项目组id
     */
    @Column(name = "projectManageId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组Id'")
    private String projectManageId;

    /**
     * 岗位id
     */
    @Column(name = "positionNameId", nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '岗位Id'")
    private String positionNameId;




    public String getPositionNameId() {
        return positionNameId;
    }

    public void setPositionNameId(String positionNameId) {
        this.positionNameId = positionNameId;
    }

    public String getProjectManageId() {
        return projectManageId;
    }

    public void setProjectManageId(String projectManageId) {
        this.projectManageId = projectManageId;
    }


}