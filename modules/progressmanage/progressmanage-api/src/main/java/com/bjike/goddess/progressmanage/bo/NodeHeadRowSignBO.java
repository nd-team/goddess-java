package com.bjike.goddess.progressmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 节点表头对应值的行标记业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 10:01 ]
 * @Description: [ 节点表头对应值的行标记业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeHeadRowSignBO extends BaseBO {
    /**
     * 进度表表头信息List
     */
    private List<NodeHeadValueBO> boList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<NodeHeadValueBO> getBoList() {
        return boList;
    }

    public void setBoList(List<NodeHeadValueBO> boList) {
        this.boList = boList;
    }
}