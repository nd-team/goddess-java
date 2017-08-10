package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 明细分类
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-07 09:43 ]
 * @Description: [ 明细分类 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DetailTypeTO extends BaseTO {

    /**
     * 主科目
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "主科目不能为空")
    private String parNode;

    /**
     * 二级科目
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "二级科目不能为空")
    private String typeName;

    /**
     * 描述
     */
    private String descs;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getParNode() {
        return parNode;
    }

    public void setParNode(String parNode) {
        this.parNode = parNode;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }
}