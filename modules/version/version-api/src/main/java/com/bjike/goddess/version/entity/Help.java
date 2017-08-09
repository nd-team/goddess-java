package com.bjike.goddess.version.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 帮助与解答
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:07 ]
 * @Description: [ 帮助与解答 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "version_help")
public class Help extends BaseEntity {

    /**
     * 问题提出人
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题提出人'")
    private String name;

    /**
     * 问题描述
     */
    @Column(name = "rate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题描述'")
    private String rate;

    /**
     * 版本信息
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "version_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '版本信息'")
    private Version version;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}