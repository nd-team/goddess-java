package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * @Author: [zhangzhiguang]
 * @Date: [2018-03-23 16:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.gitgeek]
 */
public class ConfirmTO extends BaseTO{

    private List<TaskNodeTO> tos;

    public List<TaskNodeTO> getTos() {
        return tos;
    }

    public void setTos(List<TaskNodeTO> tos) {
        this.tos = tos;
    }
}
