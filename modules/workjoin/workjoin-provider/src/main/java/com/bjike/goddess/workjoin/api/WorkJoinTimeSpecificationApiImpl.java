package com.bjike.goddess.workjoin.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.WorkJoinTimeSpecificationBO;
import com.bjike.goddess.workjoin.dto.WorkJoinTimeSpecificationDTO;
import com.bjike.goddess.workjoin.entity.WorkJoinTimeSpecification;
import com.bjike.goddess.workjoin.service.WorkJoinTimeSpecificationSer;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.WorkJoinTimeSpecificationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作交接时间规范业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:18 ]
 * @Description: [ 工作交接时间规范业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("workJoinTimeSpecificationApiImpl")
public class WorkJoinTimeSpecificationApiImpl implements WorkJoinTimeSpecificationAPI {

    @Autowired
    private WorkJoinTimeSpecificationSer workJoinTimeSpecificationSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return workJoinTimeSpecificationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return workJoinTimeSpecificationSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countWorkJoinTimeSpecification(WorkJoinTimeSpecificationDTO workJoinTimeSpecificationDTO) throws SerException {
        return workJoinTimeSpecificationSer.countWorkJoinTimeSpecification(workJoinTimeSpecificationDTO);
    }

    @Override
    public WorkJoinTimeSpecificationBO getOne(String id) throws SerException {
        return workJoinTimeSpecificationSer.getOne(id);
    }

    @Override
    public List<WorkJoinTimeSpecificationBO> findListWorkJoinTimeSpecification(WorkJoinTimeSpecificationDTO workJoinTimeSpecificationDTO) throws SerException {
        return workJoinTimeSpecificationSer.findListWorkJoinTimeSpecification(workJoinTimeSpecificationDTO);
    }

    @Override
    public WorkJoinTimeSpecificationBO insertWorkJoinTimeSpecification(WorkJoinTimeSpecificationTO workJoinTimeSpecificationTO) throws SerException {
        return workJoinTimeSpecificationSer.insertWorkJoinTimeSpecification(workJoinTimeSpecificationTO);
    }

    @Override
    public WorkJoinTimeSpecificationBO editWorkJoinTimeSpecification(WorkJoinTimeSpecificationTO workJoinTimeSpecificationTO) throws SerException {
        return workJoinTimeSpecificationSer.editWorkJoinTimeSpecification(workJoinTimeSpecificationTO);
    }

    @Override
    public void removeWorkJoinTimeSpecification(String id) throws SerException {
        workJoinTimeSpecificationSer.removeWorkJoinTimeSpecification(id);
    }
}