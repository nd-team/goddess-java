package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 奖励项目比例
 *
 * @Author: [sunfengtao]
 * @Date: [2017-05-27 17:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RewardProgramTO extends BaseTO {

    /**
     * 奖励项目比例
     */
    private List<RewardProgramRatiosTO> rewardProgramRatiosTOS;

    public List<RewardProgramRatiosTO> getRewardProgramRatiosTOS() {
        return rewardProgramRatiosTOS;
    }

    public void setRewardProgramRatiosTOS(List<RewardProgramRatiosTO> rewardProgramRatiosTOS) {
        this.rewardProgramRatiosTOS = rewardProgramRatiosTOS;
    }
}
