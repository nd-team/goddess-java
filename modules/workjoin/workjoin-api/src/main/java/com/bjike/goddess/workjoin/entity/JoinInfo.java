package com.bjike.goddess.workjoin.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 交接资料
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:34 ]
 * @Description: [ 交接资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "workjoin_joininfo")
public class JoinInfo extends BaseEntity {

    /**
     * 制度文件夹编号
     */
    @Column(name = "systemFolderNum", columnDefinition = "VARCHAR(255)   COMMENT '制度文件夹编号'")
    private String systemFolderNum;

    /**
     * 制度文件夹存储路径
     */
    @Column(name = "systemFolderPath", columnDefinition = "VARCHAR(255)   COMMENT '制度文件夹存储路径'")
    private String systemFolderPath;

    /**
     * 经验总结编号
     */
    @Column(name = "experienceNum", columnDefinition = "VARCHAR(255)   COMMENT '经验总结编号'")
    private String experienceNum;

    /**
     * 经验总结存储路径
     */
    @Column(name = "experiencePath", columnDefinition = "VARCHAR(255)   COMMENT '经验总结存储路径'")
    private String experiencePath;

    /**
     * 工作设计账号，密码文件名称
     */
    @Column(name = "jobDesignName", columnDefinition = "VARCHAR(255)   COMMENT '工作设计账号，密码文件名称'")
    private String jobDesignName;

    /**
     * 账号，密码存储路径
     */
    @Column(name = "accountPath", columnDefinition = "VARCHAR(255)   COMMENT '账号，密码存储路径'")
    private String accountPath;


    public String getSystemFolderNum() {
        return systemFolderNum;
    }

    public void setSystemFolderNum(String systemFolderNum) {
        this.systemFolderNum = systemFolderNum;
    }

    public String getSystemFolderPath() {
        return systemFolderPath;
    }

    public void setSystemFolderPath(String systemFolderPath) {
        this.systemFolderPath = systemFolderPath;
    }

    public String getExperienceNum() {
        return experienceNum;
    }

    public void setExperienceNum(String experienceNum) {
        this.experienceNum = experienceNum;
    }

    public String getExperiencePath() {
        return experiencePath;
    }

    public void setExperiencePath(String experiencePath) {
        this.experiencePath = experiencePath;
    }

    public String getJobDesignName() {
        return jobDesignName;
    }

    public void setJobDesignName(String jobDesignName) {
        this.jobDesignName = jobDesignName;
    }

    public String getAccountPath() {
        return accountPath;
    }

    public void setAccountPath(String accountPath) {
        this.accountPath = accountPath;
    }
}