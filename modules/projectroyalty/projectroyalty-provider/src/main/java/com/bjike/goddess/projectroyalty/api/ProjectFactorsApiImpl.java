package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.ProjectFactorsBO;
import com.bjike.goddess.projectroyalty.dto.ProjectFactorsDTO;
import com.bjike.goddess.projectroyalty.service.ProjectFactorsSer;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.ProjectFactorsTO;
import com.bjike.goddess.projectroyalty.to.TargetAuotaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 项目提成分配因素业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-14 11:39 ]
* @Description:	[ 项目提成分配因素业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("projectFactorsApiImpl")
public class ProjectFactorsApiImpl implements ProjectFactorsAPI  {
    @Autowired
    private ProjectFactorsSer projectFactorsSer;

    @Override
    public void save(ProjectFactorsTO to) throws SerException {
        projectFactorsSer.save(to);
    }

    @Override
    public void update(ProjectFactorsTO to) throws SerException {
        projectFactorsSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        projectFactorsSer.delete(id);
    }

    @Override
    public ProjectFactorsBO getById(String id) throws SerException {
        return projectFactorsSer.getById(id);
    }

    @Override
    public List<ProjectFactorsBO> maps(ProjectFactorsDTO dto) throws SerException {
        return projectFactorsSer.maps(dto);
    }

    @Override
    public Long getTotal(ProjectFactorsDTO dto) throws SerException {
        return projectFactorsSer.getTotal(dto);
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return projectFactorsSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return projectFactorsSer.sonPermission();
    }
}