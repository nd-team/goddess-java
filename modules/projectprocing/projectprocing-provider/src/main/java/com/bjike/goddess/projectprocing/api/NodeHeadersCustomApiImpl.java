package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.NodeHeadersCustomBO;
import com.bjike.goddess.projectprocing.dto.NodeHeadersCustomDTO;
import com.bjike.goddess.projectprocing.service.NodeHeadersCustomSer;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
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
    private NodeHeadersCustomSer nodeHeadersCustomSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return nodeHeadersCustomSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return nodeHeadersCustomSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countNode(NodeHeadersCustomDTO nodeHeadersCustomDTO) throws SerException {
        return nodeHeadersCustomSer.countNode(nodeHeadersCustomDTO);
    }

    @Override
    public NodeHeadersCustomBO getOneById(String id) throws SerException {
        return nodeHeadersCustomSer.getOneById(id);
    }

    @Override
    public List<NodeHeadersCustomBO> listNode(NodeHeadersCustomDTO nodeHeadersCustomDTO) throws SerException {
        return nodeHeadersCustomSer.listNode(nodeHeadersCustomDTO);
    }

    @Override
    public NodeHeadersCustomBO addNode(NodeHeadersCustomTO nodeHeadersCustomTO) throws SerException {
        return nodeHeadersCustomSer.addNode(nodeHeadersCustomTO);
    }

    @Override
    public NodeHeadersCustomBO editNode(NodeHeadersCustomTO nodeHeadersCustomTO) throws SerException {
        return nodeHeadersCustomSer.editNode(nodeHeadersCustomTO);
    }

    @Override
    public void deleteNode(String id) throws SerException {
        nodeHeadersCustomSer.deleteNode(id);
    }

    @Override
    public List<NodeHeadersCustomBO> getNodeByOutUnit(String outUnit) throws SerException {
        return nodeHeadersCustomSer.getNodeByOutUnit(outUnit);
    }

    @Override
    public List<NodeHeadersCustomBO> getByManageId(String passManageId) throws SerException {
        return nodeHeadersCustomSer.getByManageId(passManageId);
    }

    @Override
    public void removeByManageId(String passManageId) throws SerException {
        nodeHeadersCustomSer.removeByManageId(passManageId);
    }

    @Override
    public void checkNodeCutoff() throws SerException {
        nodeHeadersCustomSer.checkNodeCutoff();
    }
}