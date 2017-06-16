package com.bjike.goddess.projectissuehandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectissuehandle.bo.InvolvedProcessingTaskBO;
import com.bjike.goddess.projectissuehandle.dto.InvolvedProcessingTaskDTO;
import com.bjike.goddess.projectissuehandle.service.InvolvedProcessingTaskSer;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.InvolvedProcessingTaskTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Boolean sonPermission() throws SerException {
        return involvedProcessingTaskSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return involvedProcessingTaskSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long countInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        return involvedProcessingTaskSer.countInvolvedProcessingTask(involvedProcessingTaskDTO);
    }

    @Override
    public InvolvedProcessingTaskBO getOne(String id) throws SerException {
        return involvedProcessingTaskSer.getOne(id);
    }

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
    public List<InvolvedProcessingTaskBO> searchInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        return involvedProcessingTaskSer.searchInvolvedProcessingTask(involvedProcessingTaskDTO);
    }
    @Override
    public byte[] exportExcel(InvolvedProcessingTaskDTO dto) throws SerException {
        return involvedProcessingTaskSer.exportExcel(dto);
    }

}