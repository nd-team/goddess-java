package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 推荐类型设定
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 02:10 ]
 * @Description: [ 推荐类型设定 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendTypeTO extends BaseTO {

    /**
     * 推荐类型名称
     */
    private String typeName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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