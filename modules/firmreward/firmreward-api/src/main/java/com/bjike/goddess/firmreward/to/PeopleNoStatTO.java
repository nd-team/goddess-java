package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 奖励人数统计
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:45 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PeopleNoStatTO extends BaseTO {
    /**
     * 获奖明细
     */
    private List<PeopleTO> peopleTOS;

    public List<PeopleTO> getPeopleTOS() {
        return peopleTOS;
    }

    public void setPeopleTOS(List<PeopleTO> peopleTOS) {
        this.peopleTOS = peopleTOS;
    }
}