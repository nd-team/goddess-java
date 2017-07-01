package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.progressmanage.bo.NodeHeadForValueBO;
import com.bjike.goddess.progressmanage.bo.NodeHeadRowSignBO;
import com.bjike.goddess.progressmanage.entity.NodeHeadRowSign;
import com.bjike.goddess.progressmanage.dto.NodeHeadRowSignDTO;
import com.bjike.goddess.progressmanage.to.NodeHeadRowSignTO;

import java.util.List;

/**
* 节点表头对应值的行标记业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-26 10:01 ]
* @Description:	[ 节点表头对应值的行标记业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface NodeHeadRowSignSer extends Ser<NodeHeadRowSign, NodeHeadRowSignDTO> {

    List<NodeHeadForValueBO> heads(String nodeId) throws SerException;

    List<NodeHeadRowSignBO> pageList(NodeHeadRowSignDTO dto) throws SerException;

    void insertModel(NodeHeadRowSignTO to) throws SerException;

    void updateModel(NodeHeadRowSignTO to) throws SerException;

    void delete(String id) throws SerException;
}