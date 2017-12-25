package com.bjike.goddess.staffwelfare.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 祝福语
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 03:21 ]
 * @Description: [ 祝福语 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WishesStatementTO extends BaseTO {

    /**
     * 填写人
     */
    private String createUser;

    /**
     * 祝福语
     */
    @NotBlank(message = "祝福语不能为空!", groups = {ADD.class, EDIT.class})
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