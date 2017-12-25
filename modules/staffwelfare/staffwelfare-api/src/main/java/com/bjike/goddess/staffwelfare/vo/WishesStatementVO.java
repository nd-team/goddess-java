package com.bjike.goddess.staffwelfare.vo;

/**
 * 祝福语表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 03:21 ]
 * @Description: [ 祝福语表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WishesStatementVO {

    /**
     * id
     */
    private String id;
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

    /**
     * 更新时间
     */
    private String updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}