package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;


/**
 * 弹框考核指标
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-14 02:43 ]
 * @Description: [ 弹框考核指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "recruit_alertindex")
public class AlertIndex extends BaseEntity {

    /**
     * 考核指标名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '考核指标名称'")
    private String name;

    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "alertindex_id")
    private Set<IndexContent> indexContent;

    @Column(name = "score", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分数'")
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<IndexContent> getIndexContent() {
        return indexContent;
    }

    public void setIndexContent(Set<IndexContent> indexContent) {
        this.indexContent = indexContent;
    }
}