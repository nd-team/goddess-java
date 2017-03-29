package com.bjike.goddess.projectissuehandle.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.bo.InvolvedProcessingTaskBO;
import com.bjike.goddess.projectissuehandle.dto.InvolvedProcessingTaskDTO;
import com.bjike.goddess.projectissuehandle.entity.InvolvedProcessingTask;
import com.bjike.goddess.projectissuehandle.service.InvolvedProcessingTaskSer;
import com.bjike.goddess.projectissuehandle.to.InvolvedProcessingTaskTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 参与处理人员的任务分配业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 05:02 ]
 * @Description: [ 参与处理人员的任务分配业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("involvedProcessingTaskApiImpl")
public class InvolvedProcessingTaskApiImpl implements InvolvedProcessingTaskAPI {
    @Autowired
    private InvolvedProcessingTaskSer involvedProcessingTaskSer;

    @Override
    public List<InvolvedProcessingTaskBO> findListInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        return involvedProcessingTaskSer.findListInvolvedProcessingTask(involvedProcessingTaskDTO);
    }

    @Override
    public InvolvedProcessingTaskBO insertInvolvedProcessingTask(InvolvedProcessingTaskTO involvedProcessingTaskTO) throws SerException {
        return involvedProcessingTaskSer.insertInvolvedProcessingTask(involvedProcessingTaskTO);
    }

    @Override
    public InvolvedProcessingTaskBO editInvolvedProcessingTask(InvolvedProcessingTaskTO involvedProcessingTaskTO) throws SerException {
        return involvedProcessingTaskSer.editInvolvedProcessingTask(involvedProcessingTaskTO);
    }

    @Override
    public void removeInvolvedProcessingTask(String id) throws SerException {
        involvedProcessingTaskSer.removeInvolvedProcessingTask(id);
    }

    @Override
    public String exportExcel(String internalProjectName, String handler) throws SerException {
        return involvedProcessingTaskSer.exportExcel(internalProjectName, handler);
    }

    @Override
    public InvolvedProcessingTaskBO searchInvolvedProcessingTask(String internalProjectName, String handler) throws SerException {
        return involvedProcessingTaskSer.searchInvolvedProcessingTask(internalProjectName, handler);
    }

    @Override
    public void upload() throws SerException {
        involvedProcessingTaskSer.upload();

    }

}