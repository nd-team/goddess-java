package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectEvaluateResultBO;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureSummaryBO;
import com.bjike.goddess.projectmeasure.bo.ProjectOtherDemandBO;
import com.bjike.goddess.projectmeasure.dto.ProjectOtherDemandDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectOtherDemand;
import com.bjike.goddess.projectmeasure.excel.SonPermissionObject;
import com.bjike.goddess.projectmeasure.service.ProjectOtherDemandSer;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectOtherDemandTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 其他需求界面业务接口实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-06-12 04:21 ]
 * @Description: [ 其他需求界面业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectOtherDemandApiImpl")
public class ProjectOtherDemandApiImpl implements ProjectOtherDemandAPI {

    @Autowired
    private ProjectOtherDemandSer projectOtherDemandSer;

    public ProjectOtherDemandBO add(ProjectOtherDemandTO projectOtherDemandTO) throws SerException {
        return projectOtherDemandSer.add(projectOtherDemandTO);
    }

    public ProjectOtherDemandBO edit(ProjectOtherDemandTO projectOtherDemandTO) throws SerException {
        return projectOtherDemandSer.edit(projectOtherDemandTO);
    }

    public void remove(String id) throws SerException {
        projectOtherDemandSer.remove(id);
    }

    public List<ProjectEvaluateResultBO> findEvaluateResult(ProjectOtherDemandDTO demandDTO) throws SerException{
        return projectOtherDemandSer.findEvaluateResult(demandDTO);
    }

    public List<ProjectOtherDemandBO> findProjectOtherDemand(ProjectOtherDemandDTO dto) throws SerException {
        return projectOtherDemandSer.findProjectOtherDemand(dto);
    }

    public Long count(ProjectOtherDemandDTO projectOtherDemandDTO) throws SerException {
        return projectOtherDemandSer.count(projectOtherDemandDTO);
    }

    public ProjectOtherDemandBO getOne(String id) throws SerException {
        return projectOtherDemandSer.getOne(id);
    }

    public List<SonPermissionObject> sonPermission() throws SerException {
        return projectOtherDemandSer.sonPermission();
    }

    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return projectOtherDemandSer.guidePermission(guidePermissionTO);
    }

}