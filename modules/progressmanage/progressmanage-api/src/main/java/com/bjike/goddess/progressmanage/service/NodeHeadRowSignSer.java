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

    /**
     * 根据节点Id查询所有表头
     * @param nodeId
     * @return
     * @throws SerException
     */
    List<NodeHeadForValueBO> heads(String nodeId) throws SerException;

    /**
     * 分页查询
     * @param dto 分页条件
     */
    List<NodeHeadRowSignBO> pageList(NodeHeadRowSignDTO dto) throws SerException;

    /**
     * 保存行记录
     * @param to 行信息
     */
    void insertModel(NodeHeadRowSignTO to) throws SerException;

    /**
     * 更新
     * @param to 行信息
     */
    void updateModel(NodeHeadRowSignTO to) throws SerException;

    /**
     * 删除
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;
}