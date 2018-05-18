package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 推荐内容
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:51 ]
 * @Description: [ 推荐内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OldRecommendContentTO extends BaseTO {

    /**
     * 推荐信息id
     */
    @NotBlank(message = "推荐信息设定id不能为空", groups = {ADD.class, EDIT.class})
    private String infoId;

    /**
     * 推荐内容
     */
    @NotBlank(message = "推荐内容不能为空", groups = {ADD.class, EDIT.class})
    private String content;

    /**
     * 内容明细
     */
    @NotBlank(message = "内容明细不能为空", groups = {ADD.class, EDIT.class})
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