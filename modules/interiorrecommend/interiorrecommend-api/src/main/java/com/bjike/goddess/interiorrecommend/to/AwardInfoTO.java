package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 推荐奖励信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 04:07 ]
 * @Description: [ 推荐奖励信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AwardInfoTO extends BaseTO {

    /**
     * 推荐信息id
     */
    @NotBlank(message = "推荐信息id不能为空", groups = {ADD.class, EDIT.class})
    private String infoId;

    /**
     * 奖励时间
     */
    @NotBlank(message = "奖励时间不能为空", groups = {ADD.class, EDIT.class})
    private String awardTime;

    /**
     * 是否获得奖励
     */
    @NotNull(message = "是否获得奖励不能为空", groups = {ADD.class, EDIT.class})
    private Boolean getAward;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }

    public Boolean getGetAward() {
        return getAward;
    }

    public void setGetAward(Boolean getAward) {
        this.getAward = getAward;
    }
}