package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 推荐类型设定
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 02:10 ]
 * @Description: [ 推荐类型设定 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "interiorrecommend_recommendtype")
public class OldRecommendType extends BaseEntity {

    /**
     * 推荐类型名称
     */
    @Column(name = "typeName", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '推荐类型名称'")
    private String typeName;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '推荐类型名称'")
    private String remark;

    /**
     * 创建人
     */
    @Column(name = "createUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String createUser;

    /**
     * 修改人
     */
    @Column(name = "updateUser", columnDefinition = "VARCHAR(255)   COMMENT '修改人'")
    private String updateUser;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}