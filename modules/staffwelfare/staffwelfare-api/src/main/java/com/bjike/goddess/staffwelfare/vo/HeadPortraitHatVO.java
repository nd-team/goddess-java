package com.bjike.goddess.staffwelfare.vo;

/**
 * 头像帽表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 04:00 ]
 * @Description: [ 头像帽表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HeadPortraitHatVO {

    /**
     * id
     */
    private String id;
    /**
     * 填写人
     */
    private String createUser;

    /**
     * 头像帽名称
     */
    private String name;

    /**
     * 头像帽图片URL名称
     */
    private String url;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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