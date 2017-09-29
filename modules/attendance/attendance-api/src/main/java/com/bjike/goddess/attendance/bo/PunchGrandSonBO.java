package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.attendance.enums.PunchStatus;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 打卡孙子表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 05:13 ]
 * @Description: [ 打卡孙子表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PunchGrandSonBO extends BaseBO {

    /**
     * 打卡状态
     */
    private PunchStatus punchStatus;

    /**
     * 父id
     */
    private String punchSonId;


    public PunchStatus getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(PunchStatus punchStatus) {
        this.punchStatus = punchStatus;
    }

    public String getPunchSonId() {
        return punchSonId;
    }

    public void setPunchSonId(String punchSonId) {
        this.punchSonId = punchSonId;
    }
}