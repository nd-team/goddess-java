package com.bjike.goddess.staffwelfare.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

/**
 * 头像帽
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 04:00 ]
 * @Description: [ 头像帽 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HeadPortraitHatTO extends BaseTO {

    /**
     * 填写人
     */
    private String createUser;

    /**
     * 头像帽名称
     */
    @NotBlank(message = "头像帽名称不能为空!", groups = {ADD.class, EDIT.class})
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
     * 文件转存
     */
    private Map<String, byte[]> map;

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

    public Map<String, byte[]> getMap() {
        return map;
    }

    public void setMap(Map<String, byte[]> map) {
        this.map = map;
    }
}