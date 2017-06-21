package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.ProjectInfoBO;
import com.bjike.goddess.progressmanage.bo.ProjectListForNodeBO;
import com.bjike.goddess.progressmanage.dto.ProjectInfoDTO;
import com.bjike.goddess.progressmanage.service.ProjectInfoSer;
import com.bjike.goddess.progressmanage.to.ProjectInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:41 ]
 * @Description: [ 项目信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectInfoApiImpl")
public class ProjectInfoApiImpl implements ProjectInfoAPI {

    @Autowired
    private ProjectInfoSer projectInfoSer;

    @Override
    public ProjectInfoBO add(ProjectInfoTO to) throws SerException {
        return projectInfoSer.insertModel(to);
    }

    @Override
    public ProjectInfoBO edit(ProjectInfoTO to) throws SerException {
        return projectInfoSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        projectInfoSer.delete(id);
    }

    @Override
    public List<ProjectInfoBO> pageList(ProjectInfoDTO dto) throws SerException {
        return projectInfoSer.pageList(dto);
    }

    @Override
    public List<ProjectListForNodeBO> projects() throws SerException {
        return projectInfoSer.projects();
    }
}