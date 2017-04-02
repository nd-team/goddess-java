package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.HandlePlanImplementBO;
import com.bjike.goddess.qualifications.service.HandlePlanImplementSer;
import com.bjike.goddess.qualifications.to.HandlePlanImplementTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资质办理计划阶段实施工作记录业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 05:00 ]
 * @Description: [ 资质办理计划阶段实施工作记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("handlePlanImplementApiImpl")
public class HandlePlanImplementApiImpl implements HandlePlanImplementAPI {

    @Autowired
    private HandlePlanImplementSer handlePlanImplementSer;

    @Override
    public HandlePlanImplementBO save(HandlePlanImplementTO to) throws SerException {
        return handlePlanImplementSer.save(to);
    }

    @Override
    public HandlePlanImplementBO update(HandlePlanImplementTO to) throws SerException {
        return handlePlanImplementSer.update(to);
    }

    @Override
    public HandlePlanImplementBO delete(String id) throws SerException {
        return handlePlanImplementSer.delete(id);
    }

    @Override
    public List<HandlePlanImplementBO> findByStageIds(String[] stage_ids) throws SerException {
        return handlePlanImplementSer.findByStageIds(stage_ids);
    }

    @Override
    public List<HandlePlanImplementBO> findByStage(String stage_id) throws SerException {
        return handlePlanImplementSer.findByStage(stage_id);
    }

    @Override
    public List<HandlePlanImplementBO> findByHandle(String handle_id) throws SerException {
        return handlePlanImplementSer.findByHandle(handle_id);
    }
}