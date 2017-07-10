package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.NodeHeadBO;
import com.bjike.goddess.progressmanage.dto.NodeHeadDTO;
import com.bjike.goddess.progressmanage.service.NodeHeadSer;
import com.bjike.goddess.progressmanage.to.NodeHeadTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进度节点表头业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:43 ]
 * @Description: [ 进度节点表头业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("nodeHeadApiImpl")
public class NodeHeadApiImpl implements NodeHeadAPI {

    @Autowired
    private NodeHeadSer nodeHeadSer;

    @Override
    public NodeHeadBO add(NodeHeadTO to) throws SerException {
        return nodeHeadSer.insertModel(to);
    }

    @Override
    public NodeHeadBO edit(NodeHeadTO to) throws SerException {
        return nodeHeadSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        nodeHeadSer.delete(id);
    }

    @Override
    public List<NodeHeadBO> pageList(NodeHeadDTO dto) throws SerException {
        return nodeHeadSer.pageList(dto);
    }
}