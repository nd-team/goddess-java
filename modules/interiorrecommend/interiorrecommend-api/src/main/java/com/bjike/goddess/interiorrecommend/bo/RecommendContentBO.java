package com.bjike.goddess.interiorrecommend.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 推荐内容业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:51 ]
 * @Description: [ 推荐内容业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendContentBO extends BaseBO {

    /**
     * 推荐信息id
     */
    private String infoId;
    /**
     * 推荐内容
     */
    private String content;

    /**
     * 内容明细
     */
    private String detail;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}