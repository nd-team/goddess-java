package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.NodeListForHeadBO;
import com.bjike.goddess.progressmanage.bo.ProgressNodeBO;
import com.bjike.goddess.progressmanage.dto.ProgressNodeDTO;
import com.bjike.goddess.progressmanage.service.ProgressNodeSer;
import com.bjike.goddess.progressmanage.to.ProgressNodeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进度节点业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:21 ]
 * @Description: [ 进度节点业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("progressNodeApiImpl")
public class ProgressNodeApiImpl implements ProgressNodeAPI {

    @Autowired
    private ProgressNodeSer progressNodeSer;

    @Override
    public ProgressNodeBO add(ProgressNodeTO to) throws SerException {
        return progressNodeSer.insertModel(to);
    }

    @Override
    public ProgressNodeBO edit(ProgressNodeTO to) throws SerException {
        return progressNodeSer.editModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        progressNodeSer.delete(id);
    }

    @Override
    public List<ProgressNodeBO> pageList(ProgressNodeDTO dto) throws SerException {
        return progressNodeSer.pageList(dto);
    }

    @Override
    public List<NodeListForHeadBO> nodes(String projectId) throws SerException {
        return progressNodeSer.nodes(projectId);
    }
}