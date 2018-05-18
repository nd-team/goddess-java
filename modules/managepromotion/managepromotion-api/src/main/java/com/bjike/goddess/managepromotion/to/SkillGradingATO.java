package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.managepromotion.entity.SkillGrading;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 技能定级A
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级A ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillGradingATO extends BaseTO {

    /**
     * 体系
     */
    @NotBlank(message = "体系不能为空",groups = {ADD.class, EDIT.class})
    private String system;

    /**
     * 行业
     */
    @NotBlank(message = "行业不能为空",groups = {ADD.class, EDIT.class})
    private String industry;

    /**
     * 业务方向-科目
     */
    @NotBlank(message = "业务方向-科目不能为空",groups = {ADD.class, EDIT.class})
    private String subject;

    /**
     * 技能定位-专业（业务范围内包含的技能）
     */
    @NotBlank(message = "技能定位-专业（业务范围内包含的技能）不能为空",groups = {ADD.class, EDIT.class})
    private String major;
    /**
     * 技能等级B
     */
    private List<SkillGradingBTO> skillGradingBTOS;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<SkillGradingBTO> getSkillGradingBTOS() {
        return skillGradingBTOS;
    }

    public void setSkillGradingBTOS(List<SkillGradingBTO> skillGradingBTOS) {
        this.skillGradingBTOS = skillGradingBTOS;
    }
}