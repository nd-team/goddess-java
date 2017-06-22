package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.bo.NodeHeadBO;
import com.bjike.goddess.progressmanage.bo.NodeHeadBO;
import com.bjike.goddess.progressmanage.dto.NodeHeadDTO;
import com.bjike.goddess.progressmanage.dto.NodeHeadDTO;
import com.bjike.goddess.progressmanage.entity.NodeHead;
import com.bjike.goddess.progressmanage.entity.ProgressNode;
import com.bjike.goddess.progressmanage.entity.ProgressTable;
import com.bjike.goddess.progressmanage.entity.NodeHead;
import com.bjike.goddess.progressmanage.to.NodeHeadTO;
import com.bjike.goddess.progressmanage.to.NodeHeadTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 进度节点表头业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:43 ]
 * @Description: [ 进度节点表头业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "progressmanageSerCache")
@Service
public class NodeHeadSerImpl extends ServiceImpl<NodeHead, NodeHeadDTO> implements NodeHeadSer {

    @Autowired
    private ProgressNodeSer progressNodeSer;

    @Override
    public NodeHeadBO insertModel(NodeHeadTO to) throws SerException {
        ProgressNode node = progressNodeSer.findById(to.getNodeId());
        if (node != null) {

            if ((isExistHeadName(to) != null)) {
                throw new SerException("该节点已经存在该表头名字段，请检查数据!");
            }
            if ((isExistSortIndex(to) != null)) {
                throw new SerException("该节点已经存在该顺序字段，请检查数据!");
            }
            NodeHead model = BeanTransform.copyProperties(to, NodeHead.class, true);
            model.setProgressNode(node);
            super.save(model);
            return BeanTransform.copyProperties(model, NodeHeadBO.class);
        } else {
            throw new SerException("非法tableId, 进度表对象不存在!");
        }
    }

    public NodeHead isExistHeadName(NodeHeadTO to) throws SerException {
        NodeHeadDTO dto = new NodeHeadDTO();
        dto.getConditions().add(Restrict.eq("progressNode.id", to.getNodeId()));
        dto.getConditions().add(Restrict.eq("headName", to.getHeadName()));
        dto.setLimit(1);
        return super.findOne(dto);
    }

    public NodeHead isExistSortIndex(NodeHeadTO to) throws SerException {
        NodeHeadDTO dto = new NodeHeadDTO();
        dto.getConditions().add(Restrict.eq("progressNode.id", to.getNodeId()));
        dto.getConditions().add(Restrict.eq("sortIndex", to.getSortIndex()));
        dto.setLimit(1);
        return super.findOne(dto);
    }

    @Override
    public NodeHeadBO updateModel(NodeHeadTO to) throws SerException {
        NodeHead model = super.findById(to.getId());
        if (model != null) {
            NodeHead judgeName = isExistHeadName(to);
            NodeHead judgeIndex = isExistSortIndex(to);
            if (judgeName == null || (judgeName != null && to.getId().equals(judgeName.getId()))) {

            } else {
                throw new SerException("该节点已经存在该表头名字段，请检查数据!");
            }
            if (judgeIndex == null || (judgeIndex != null && to.getId().equals(judgeIndex.getId()))) {

            } else {
                throw new SerException("该节点已经存在该顺序字段，请检查数据!");
            }

            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
            return BeanTransform.copyProperties(model, NodeHeadBO.class);

        } else {
            throw new SerException("非法Id,编辑对象不存在!");
        }
    }

    @Override
    public List<NodeHeadBO> pageList(NodeHeadDTO dto) throws SerException {
        if(!StringUtils.isEmpty(dto.getNodeId())){
            dto.getSorts().add("sortIndex=asc");
            dto.getConditions().add(Restrict.eq("progressNode.id",dto.getNodeId()));
            return BeanTransform.copyProperties(super.findByPage(dto), NodeHeadBO.class);
        }else{
            throw new SerException("节点Id不能为空!");
        }
    }
}