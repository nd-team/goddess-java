package com.bjike.goddess.contractcommunicat.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 谈判技巧库数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:55 ]
 * @Description: [ 谈判技巧库数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillLibraryDTO extends BaseDTO {

    /**
     * 阶段
     */
    private String phase;

    /**
     * 场景类型
     */
    private String sceneType;

    /**
     * 技巧
     */
    private String skills;

    /**
     * 策略
     */
    private String strategy;

    /**
     * 来源
     */
    private String source;

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}