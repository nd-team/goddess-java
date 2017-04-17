package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 推荐内容
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:51 ]
 * @Description: [ 推荐内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "interiorrecommend_recommendcontent")
public class RecommendContent extends BaseEntity {

    /**
     * 推荐信息id
     */
    @Column(name = "infoId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '推荐信息id'")
    private String infoId;

    /**
     * 推荐内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '推荐内容'")
    private String content;

    /**
     * 内容明细
     */
    @Column(name = "detail", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内容明细'")
    private String detail;

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


    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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