package com.bjike.goddess.dimission.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dimission.bo.WorkHandoverBO;
import com.bjike.goddess.dimission.dto.WorkHandoverDTO;
import com.bjike.goddess.dimission.service.WorkHandoverSer;
import com.bjike.goddess.dimission.to.HandoverSuccessTO;
import com.bjike.goddess.dimission.to.WorkHandoverTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作交接业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:24 ]
 * @Description: [ 工作交接业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("workHandoverApiImpl")
public class WorkHandoverApiImpl implements WorkHandoverAPI {

    @Autowired
    private WorkHandoverSer workHandoverSer;

    @Override
    public WorkHandoverBO save(WorkHandoverTO to) throws SerException {
        return workHandoverSer.save(to);
    }

    @Override
    public WorkHandoverBO update(WorkHandoverTO to) throws SerException {
        return workHandoverSer.update(to);
    }

    @Override
    public WorkHandoverBO delete(String id) throws SerException {
        return workHandoverSer.delete(id);
    }

    @Override
    public WorkHandoverBO success(HandoverSuccessTO to) throws SerException {
        return workHandoverSer.success(to);
    }

    @Override
    public List<WorkHandoverBO> maps(WorkHandoverDTO dto) throws SerException {
        return workHandoverSer.maps(dto);
    }

    @Override
    public WorkHandoverBO getById(String id) throws SerException {
        return workHandoverSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return workHandoverSer.getTotal();
    }
}