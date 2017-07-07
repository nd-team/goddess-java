package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.progressmanage.bo.NodeListForHeadBO;
import com.bjike.goddess.progressmanage.bo.ProgressNodeBO;
import com.bjike.goddess.progressmanage.entity.ProgressNode;
import com.bjike.goddess.progressmanage.dto.ProgressNodeDTO;
import com.bjike.goddess.progressmanage.to.ProgressNodeTO;

import java.util.List;

/**
* 进度节点业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-09 05:21 ]
* @Description:	[ 进度节点业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProgressNodeSer extends Ser<ProgressNode, ProgressNodeDTO> {

    ProgressNodeBO insertModel(ProgressNodeTO to) throws SerException;

    ProgressNodeBO editModel(ProgressNodeTO to) throws SerException;

    void delete(String id) throws SerException;

    List<ProgressNodeBO> pageList(ProgressNodeDTO dto) throws SerException;

    List<NodeListForHeadBO> nodes(String tableId) throws SerException;
}