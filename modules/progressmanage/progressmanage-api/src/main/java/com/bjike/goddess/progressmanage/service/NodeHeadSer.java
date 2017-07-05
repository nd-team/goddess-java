package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.progressmanage.bo.NodeHeadBO;
import com.bjike.goddess.progressmanage.entity.NodeHead;
import com.bjike.goddess.progressmanage.dto.NodeHeadDTO;
import com.bjike.goddess.progressmanage.to.NodeHeadTO;

import java.util.List;

/**
* 进度节点表头业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-09 05:43 ]
* @Description:	[ 进度节点表头业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface NodeHeadSer extends Ser<NodeHead, NodeHeadDTO> {

    NodeHeadBO insertModel(NodeHeadTO to) throws SerException;

    NodeHeadBO updateModel(NodeHeadTO to) throws SerException;

    List<NodeHeadBO> pageList(NodeHeadDTO dto) throws SerException;

    void delete(String id) throws SerException;
}