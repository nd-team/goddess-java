package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 技能定级B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillGradingBTO extends BaseTO {
    /**
     * 技能等级
     */
    @NotBlank(message = "技能等级不能为空",groups = {ADD.class, EDIT.class})
    private String skillLevel;
    /**
     * 转正后间隔时间
     */
    @NotNull(message = "转正后间隔时间不能为空",groups = {ADD.class, EDIT.class})
    private Integer intervalAfterTransfer;

    /**
     * 技能等级C
     */
    private List<SkillGradingCTO> skillGradingCTOS;

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Integer getIntervalAfterTransfer() {
        return intervalAfterTransfer;
    }

    public void setIntervalAfterTransfer(Integer intervalAfterTransfer) {
        this.intervalAfterTransfer = intervalAfterTransfer;
    }

    public List<SkillGradingCTO> getSkillGradingCTOS() {
        return skillGradingCTOS;
    }

    public void setSkillGradingCTOS(List<SkillGradingCTO> skillGradingCTOS) {
        this.skillGradingCTOS = skillGradingCTOS;
    }
}