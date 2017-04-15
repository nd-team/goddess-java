package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 用户职位
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionDetailUserTO extends BaseTO {

    /**
     * 用户信息
     */
    private String user_id;

    /**
     * 职位详细id
     */
    private String[] position_ids;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String[] getPosition_ids() {
        return position_ids;
    }

    public void setPosition_ids(String[] position_ids) {
        this.position_ids = position_ids;
    }
}