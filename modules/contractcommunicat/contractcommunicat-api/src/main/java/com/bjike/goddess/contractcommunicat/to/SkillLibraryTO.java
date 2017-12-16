package com.bjike.goddess.contractcommunicat.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.message.entity.Message;
import org.hibernate.validator.constraints.NotBlank;

import javax.swing.*;

/**
 * 谈判技巧库
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:55 ]
 * @Description: [ 谈判技巧库 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillLibraryTO extends BaseTO {
    public interface APPRAISE{}

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
     * 例子
     */
    private String example;

    /**
     * 评价
     */
    @NotBlank(message = "评价不能为空",groups = {SkillLibraryTO.APPRAISE.class})
    private String appraise;

    /**
     * 好评率
     */
    private String rate;

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

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}