package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.ProjectBO;
import com.bjike.goddess.reportmanagement.dto.ProjectDTO;
import com.bjike.goddess.reportmanagement.service.ProjectSer;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProjectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 01:58 ]
 * @Description: [ 项目表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectApiImpl")
public class ProjectApiImpl implements ProjectAPI {
    @Autowired
    private ProjectSer projectSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return projectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return projectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<ProjectBO> list(ProjectDTO dto) throws SerException {
        return projectSer.list(dto);
    }

    @Override
    public void save(ProjectTO to) throws SerException {
        projectSer.add(to);
    }

    @Override
    public ProjectBO findByID(String id) throws SerException {
        return projectSer.findByID(id);
    }

    @Override
    public void edit(ProjectTO to) throws SerException {
        projectSer.edit(to);
    }

    @Override
    public Long count(ProjectDTO dto) throws SerException {
        return projectSer.findCount(dto);
    }
}