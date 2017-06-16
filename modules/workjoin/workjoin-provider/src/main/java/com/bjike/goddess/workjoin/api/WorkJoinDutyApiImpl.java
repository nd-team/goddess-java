package com.bjike.goddess.workjoin.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.workjoin.bo.WorkJoinDutyBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDutyDTO;
import com.bjike.goddess.workjoin.service.WorkJoinDutySer;
import com.bjike.goddess.workjoin.to.WorkJoinDutyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作交接责任义务业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:20 ]
 * @Description: [ 工作交接责任义务业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("workJoinDutyApiImpl")
public class WorkJoinDutyApiImpl implements WorkJoinDutyAPI {

    @Autowired
    private WorkJoinDutySer workJoinDutySer;

    @Override
    public Long countWorkJoinDuty(WorkJoinDutyDTO workJoinDutyDTO) throws SerException {
        return workJoinDutySer.countWorkJoinDuty(workJoinDutyDTO);
    }

    @Override
    public WorkJoinDutyBO getOne(String id) throws SerException {
        return workJoinDutySer.getOne(id);
    }

    @Override
    public List<WorkJoinDutyBO> findListWorkJoinDuty(WorkJoinDutyDTO workJoinDutyDTO) throws SerException {
        return workJoinDutySer.findListWorkJoinDuty(workJoinDutyDTO);
    }

    @Override
    public WorkJoinDutyBO insertWorkJoinDuty(WorkJoinDutyTO workJoinDutyTO) throws SerException {
        return workJoinDutySer.insertWorkJoinDuty(workJoinDutyTO);
    }

    @Override
    public WorkJoinDutyBO editWorkJoinDuty(WorkJoinDutyTO workJoinDutyTO) throws SerException {
        return workJoinDutySer.editWorkJoinDuty(workJoinDutyTO);

    }

    @Override
    public void removeWorkJoinDuty(String id) throws SerException {
        workJoinDutySer.removeWorkJoinDuty(id);
    }
}