package com.bjike.goddess.staffwelfare.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 头像帽业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 04:00 ]
 * @Description: [ 头像帽业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HeadPortraitHatBO extends BaseBO {

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
}