package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.NodeHeadForValueBO;
import com.bjike.goddess.progressmanage.bo.NodeHeadRowSignBO;
import com.bjike.goddess.progressmanage.dto.NodeHeadRowSignDTO;
import com.bjike.goddess.progressmanage.service.NodeHeadRowSignSer;
import com.bjike.goddess.progressmanage.to.NodeHeadRowSignTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节点表头对应值的行标记业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 10:01 ]
 * @Description: [ 节点表头对应值的行标记业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("nodeHeadRowSignApiImpl")
public class NodeHeadRowSignApiImpl implements NodeHeadRowSignAPI {

    @Autowired
    private NodeHeadRowSignSer nodeHeadRowSignSer;

    @Override
    public List<NodeHeadForValueBO> heads(String nodeId) throws SerException {
        return nodeHeadRowSignSer.heads(nodeId);
    }

    @Override
    public List<NodeHeadRowSignBO> pageList(NodeHeadRowSignDTO dto) throws SerException {
        return nodeHeadRowSignSer.pageList(dto);
    }

    @Override
    public void add(NodeHeadRowSignTO to) throws SerException {
        nodeHeadRowSignSer.insertModel(to);
    }

    @Override
    public void edit(NodeHeadRowSignTO to) throws SerException {
        nodeHeadRowSignSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        nodeHeadRowSignSer.delete(id);
    }
}