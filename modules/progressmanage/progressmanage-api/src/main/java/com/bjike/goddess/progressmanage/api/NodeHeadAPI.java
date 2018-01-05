package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.NodeHeadBO;
import com.bjike.goddess.progressmanage.dto.NodeHeadDTO;
import com.bjike.goddess.progressmanage.to.NodeHeadTO;

import java.util.List;

/**
 * 进度节点表头业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:43 ]
 * @Description: [ 进度节点表头业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface NodeHeadAPI {

    NodeHeadBO add(NodeHeadTO to) throws SerException;

    NodeHeadBO edit(NodeHeadTO to) throws SerException;

    void delete(String id) throws SerException;

    List<NodeHeadBO> pageList(NodeHeadDTO dto) throws SerException;
}