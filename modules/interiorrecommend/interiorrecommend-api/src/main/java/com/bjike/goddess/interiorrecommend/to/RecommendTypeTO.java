package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

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
    @NotBlank(message = "推荐类型名称不能为空",groups = {ADD.class, EDIT.class})
    private String typeName;

    /**
     * 备注
     */
    private String remark;


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

}