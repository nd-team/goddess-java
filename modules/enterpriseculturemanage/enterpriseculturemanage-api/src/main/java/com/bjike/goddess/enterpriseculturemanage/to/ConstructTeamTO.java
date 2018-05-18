package com.bjike.goddess.enterpriseculturemanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 建设小组
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 03:33 ]
 * @Description: [ 建设小组 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ConstructTeamTO extends BaseTO {

    /**
     * 员工Id
     */
    @NotBlank(message = "员工Id不能为空",groups = {EDIT.class})
    private String userId;

    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空",groups = {ADD.class, EDIT.class})
    private String userNumber;


    /**
     * 员工姓名
     */
    @NotBlank(message = "员工姓名不能为空",groups = {ADD.class, EDIT.class})
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}