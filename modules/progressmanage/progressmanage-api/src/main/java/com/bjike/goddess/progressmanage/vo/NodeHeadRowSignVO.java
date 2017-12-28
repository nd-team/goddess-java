package com.bjike.goddess.progressmanage.vo;

import com.bjike.goddess.progressmanage.to.NodeHeadValueTO;
import com.bjike.goddess.progressmanage.to.TableHeadValueTO;

import java.util.List;

/**
 * 节点表头对应值的行标记表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 10:01 ]
 * @Description: [ 节点表头对应值的行标记表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeHeadRowSignVO {
    /**
     * Id
     */
    private String id;

    /**
     * 节点表头信息List
     */
    private List<NodeHeadValueVO> voList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<NodeHeadValueVO> getVoList() {
        return voList;
    }

    public void setVoList(List<NodeHeadValueVO> voList) {
        this.voList = voList;
    }
}