package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.ProjectSettlementFollowBO;
import com.bjike.goddess.projectprocing.dto.ProjectSettlementFollowDTO;
import com.bjike.goddess.projectprocing.service.ProjectSettlementFollowSer;
import com.bjike.goddess.projectprocing.to.ProjectSettlementFollowTO;
import com.bjike.goddess.projectprocing.utils.CollectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目结算跟进业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:49 ]
 * @Description: [ 项目结算跟进业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectSettlementFollowApiImpl")
public class ProjectSettlementFollowApiImpl implements ProjectSettlementFollowAPI {

    @Autowired
    private ProjectSettlementFollowSer projectSettlementFollowSer;

    @Override
    public Long countProjectSettlementFollow(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {
        return projectSettlementFollowSer.countProjectSettlementFollow(projectSettlementFollowDTO);
    }

    @Override
    public List<ProjectSettlementFollowBO> listProjectSettlementFollow(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {
        return projectSettlementFollowSer.listProjectSettlementFollow(projectSettlementFollowDTO);
    }

    @Override
    public ProjectSettlementFollowBO addProjectSettlementFollow(ProjectSettlementFollowTO projectSettlementFollowTO) throws SerException {
        return projectSettlementFollowSer.addProjectSettlementFollow(projectSettlementFollowTO);
    }

    @Override
    public ProjectSettlementFollowBO editProjectSettlementFollow(ProjectSettlementFollowTO projectSettlementFollowTO) throws SerException {
        return projectSettlementFollowSer.editProjectSettlementFollow(projectSettlementFollowTO);
    }

    @Override
    public void deleteProjectSettlementFollow(String id) throws SerException {
        projectSettlementFollowSer.deleteProjectSettlementFollow(id);
    }

    @Override
    public List<ProjectSettlementFollowBO> searchListProjectSettlementFollow(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {
        return projectSettlementFollowSer.searchListProjectSettlementFollow(projectSettlementFollowDTO);
    }


    @Override
    public List<CollectData> collect(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {
        return projectSettlementFollowSer.collect(projectSettlementFollowDTO);
    }
}