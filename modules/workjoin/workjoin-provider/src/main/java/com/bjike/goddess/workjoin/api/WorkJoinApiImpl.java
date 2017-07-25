package com.bjike.goddess.workjoin.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.WorkJoinBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDTO;
import com.bjike.goddess.workjoin.entity.WorkJoin;
import com.bjike.goddess.workjoin.excel.SonPermissionObject;
import com.bjike.goddess.workjoin.service.WorkJoinSer;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.WorkJoinTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作交接业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:14 ]
 * @Description: [ 工作交接业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("workJoinApiImpl")
public class WorkJoinApiImpl implements WorkJoinAPI {
    @Autowired
    private WorkJoinSer workJoinSer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return workJoinSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return workJoinSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countWorkJoin(WorkJoinDTO workJoinDTO) throws SerException {
        return workJoinSer.countWorkJoin(workJoinDTO);
    }

    @Override
    public WorkJoinBO getOne(String id) throws SerException {
        return workJoinSer.getOne(id);
    }

    @Override
    public List<WorkJoinBO> findListWorkJoin(WorkJoinDTO workJoinDTO) throws SerException {
        return workJoinSer.findListWorkJoin(workJoinDTO);
    }

    @Override
    public WorkJoinBO insertWorkJoin(WorkJoinTO workJoinTO) throws SerException {
        return workJoinSer.insertWorkJoin(workJoinTO);
    }

    @Override
    public WorkJoinBO editWorkJoin(WorkJoinTO workJoinTO) throws SerException {
        return workJoinSer.editWorkJoin(workJoinTO);
    }

    @Override
    public void removeWorkJoin(String id) throws SerException {
        workJoinSer.removeWorkJoin(id);
    }
    @Override
    public List<String> getNum() throws SerException {
        return workJoinSer.getNum();
    }
    @Override
    public WorkJoinBO audit(WorkJoinTO to) throws SerException {
        return workJoinSer.audit(to);
    }

}