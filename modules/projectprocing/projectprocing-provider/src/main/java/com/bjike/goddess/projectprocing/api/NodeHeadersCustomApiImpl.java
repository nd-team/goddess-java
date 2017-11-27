package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.NodeHeadersCustomBO;
import com.bjike.goddess.projectprocing.dto.NodeHeadersCustomDTO;
import com.bjike.goddess.projectprocing.to.NodeHeadersCustomTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节点表头定制业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:47 ]
 * @Description: [ 节点表头定制业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("nodeHeadersCustomApiImpl")
public class NodeHeadersCustomApiImpl implements NodeHeadersCustomAPI {
    @Autowired
    private NodeHeadersCustomAPI nodeHeadersCustomAPI;
    @Override
    public Long countNode(NodeHeadersCustomDTO nodeHeadersCustomDTO) throws SerException {
        return nodeHeadersCustomAPI.countNode(nodeHeadersCustomDTO);
    }

    @Override
    public NodeHeadersCustomBO getOneById(String id) throws SerException {
        return nodeHeadersCustomAPI.getOneById(id);
    }

    @Override
    public List<NodeHeadersCustomBO> listNode(NodeHeadersCustomDTO nodeHeadersCustomDTO) throws SerException {
        return nodeHeadersCustomAPI.listNode(nodeHeadersCustomDTO);
    }

    @Override
    public NodeHeadersCustomBO addNode(NodeHeadersCustomTO nodeHeadersCustomTO) throws SerException {
        return nodeHeadersCustomAPI.addNode(nodeHeadersCustomTO);
    }

    @Override
    public NodeHeadersCustomBO editNode(NodeHeadersCustomTO nodeHeadersCustomTO) throws SerException {
        return nodeHeadersCustomAPI.editNode(nodeHeadersCustomTO);
    }

    @Override
    public void deleteNode(String id) throws SerException {
        nodeHeadersCustomAPI.deleteNode(id);
    }

    @Override
    public List<NodeHeadersCustomBO> getNodeByOutUnit(String outUnit) throws SerException {
        return nodeHeadersCustomAPI.getNodeByOutUnit(outUnit);
    }

    @Override
    public List<NodeHeadersCustomBO> getByManageId(String passManageId) throws SerException {
        return nodeHeadersCustomAPI.getByManageId(passManageId);
    }

    @Override
    public void removeByManageId(String passManageId) throws SerException {
        nodeHeadersCustomAPI.removeByManageId(passManageId);
    }

    @Override
    public void checkNodeCutoff() throws SerException {
        nodeHeadersCustomAPI.checkNodeCutoff();
    }
}