package com.bjike.goddess.progressmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 项目下拉列表数据对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 项目下拉列表数据对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeListForHeadBO extends BaseBO {

    /**
     * 节点名称
     */
    private String nodeName;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

}