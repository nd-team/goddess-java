package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectBasicInfoBO;
import com.bjike.goddess.projectmeasure.dto.ProjectBasicInfoDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectBasicInfo;
import com.bjike.goddess.projectmeasure.service.ProjectBasicInfoSer;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectBasicInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目基本信息业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:07 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectBasicInfoApiImpl")
public class ProjectBasicInfoApiImpl implements ProjectBasicInfoAPI {

    @Autowired
    private ProjectBasicInfoSer projectBasicInfoSer;

    /**
     * 根据id查询项目基本信息
     *
     * @param id 项目基本信息唯一标识
     * @return class ProjectBasicInfoBO
     * @throws SerException
     */
    @Override
    public ProjectBasicInfoBO getOne(String id) throws SerException {
        return projectBasicInfoSer.getOne(id);
    }

    /**
     * 计算总条数
     *
     * @param dto 项目基本信息dto
     * @throws SerException
     */
    @Override
    public Long count(ProjectBasicInfoDTO dto) throws SerException {
        return projectBasicInfoSer.count(dto);
    }

    /**
     * 分页查询项目基本信息
     *
     * @return class ProjectBasicInfoBO
     * @throws SerException
     */
    @Override
    public List<ProjectBasicInfoBO> list(ProjectBasicInfoDTO dto) throws SerException {
        return projectBasicInfoSer.list(dto);
    }

    /**
     * 保存项目基本信息
     *
     * @param to 项目基本信息to
     * @return
     * @throws SerException
     */
    @Override
    public ProjectBasicInfoBO save(ProjectBasicInfoTO to) throws SerException {
        return projectBasicInfoSer.save(to);
    }

    /**
     * 根据id删除项目基本信息
     *
     * @param id 项目基本信息唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        projectBasicInfoSer.remove(id);
    }

    /**
     * 更新项目基本信息
     *
     * @param to 项目基本信息to
     * @throws SerException
     */
    @Override
    public void update(ProjectBasicInfoTO to) throws SerException {
        projectBasicInfoSer.update(to);
    }

    /**
     * 查询所有项目名称
     *
     * @return
     * @throws SerException
     */
    @Override
    public List<String> findAllProjectNames() throws SerException {
        return projectBasicInfoSer.findAllProjectNames();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return projectBasicInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return projectBasicInfoSer.guidePermission(guidePermissionTO);
    }

    /**
     * 查询所有地址
     *
     * @return
     * @throws SerException
     */
    @Override
    public List<String> findAllAreas() throws SerException {
        return projectBasicInfoSer.findAllAreas();
    }
}