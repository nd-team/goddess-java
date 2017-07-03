package com.bjike.goddess.staffwelfaremanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 祝福语业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 03:21 ]
 * @Description: [ 祝福语业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WishesStatementBO extends BaseBO {

    /**
     * 填写人
     */
    private String createUser;

    /**
     * 祝福语
     */
    private String wishesStatement;

    /**
     * 更新人
     */
    private String updateUser;


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getWishesStatement() {
        return wishesStatement;
    }

    public void setWishesStatement(String wishesStatement) {
        this.wishesStatement = wishesStatement;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}