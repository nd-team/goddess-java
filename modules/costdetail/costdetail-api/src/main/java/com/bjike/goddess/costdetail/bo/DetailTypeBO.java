package com.bjike.goddess.costdetail.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 明细分类业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-07 09:43 ]
 * @Description: [ 明细分类业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DetailTypeBO extends BaseBO {

    /**
     * 主科目
     */
    private String parNode;

    /**
     * 二级科目
     */
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