package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.bo.TaskReceiveBO;
import com.bjike.goddess.progressmanage.dto.TaskReceiveDTO;
import com.bjike.goddess.progressmanage.entity.ProgressNode;
import com.bjike.goddess.progressmanage.entity.TaskReceive;
import com.bjike.goddess.progressmanage.to.TaskReceiveTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 任务接收业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-07-03 02:33 ]
 * @Description: [ 任务接收业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "progressmanageSerCache")
@Service
public class TaskReceiveSerImpl extends ServiceImpl<TaskReceive, TaskReceiveDTO> implements TaskReceiveSer {

    @Autowired
    private ProgressNodeSer progressNodeSer;

    @Override
    public TaskReceiveBO insertModel(TaskReceiveTO to) throws SerException {
        Set<ProgressNode> progressNodeSet = getNodes(to.getNodeIds());
        TaskReceive model = BeanTransform.copyProperties(to, TaskReceive.class, true);
        model.setProgressNodeSet(progressNodeSet);
        super.save(model);
        return BeanTransform.copyProperties(model, TaskReceiveBO.class);
    }

    @Override
    public TaskReceiveBO updateModel(TaskReceiveTO to) throws SerException {
        TaskReceive model = super.findById(to.getId());
        Set<ProgressNode> progressNodeSet = getNodes(to.getNodeIds());

        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            model.setProgressNodeSet(progressNodeSet);
            super.update(model);

            return BeanTransform.copyProperties(model, TaskReceiveBO.class);
        } else {
            throw new SerException("非法Id,任务接收对象不能为空!");
        }
    }

    //查询节点信息
    public Set<ProgressNode> getNodes(List<String> nodeIds) throws SerException {
        Set<ProgressNode> progressNodeSet = new HashSet<ProgressNode>();
        for (String nodeId : nodeIds) {
            ProgressNode node = progressNodeSer.findById(nodeId);
            if (node != null) {
                progressNodeSet.add(node);
            } else {
                throw new SerException("非法nodeId,节点对象不能为空!");
            }
        }
        return progressNodeSet;
    }


    @Override
    public void delete(String id) throws SerException {
        TaskReceive model = super.findById(id);
        if (model != null) {
            super.remove(id);
        } else {
            throw new SerException("非法Id,任务接收对象不能为空!");
        }
    }

    @Override
    public List<TaskReceiveBO> pageList(TaskReceiveDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");

        return BeanTransform.copyProperties(super.findByPage(dto), TaskReceiveBO.class);
    }
}