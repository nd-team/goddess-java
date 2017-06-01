package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectBasicInfoBO;
import com.bjike.goddess.projectmeasure.dto.ProjectBasicInfoDTO;
import com.bjike.goddess.projectmeasure.dto.ProjectCostStatusDTO;
import com.bjike.goddess.projectmeasure.dto.ProjectPersonnelDemandDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectBasicInfo;
import com.bjike.goddess.projectmeasure.entity.ProjectCostStatus;
import com.bjike.goddess.projectmeasure.entity.ProjectPersonnelDemand;
import com.bjike.goddess.projectmeasure.to.ProjectBasicInfoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目基本信息业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:07 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class ProjectBasicInfoSerImpl extends ServiceImpl<ProjectBasicInfo, ProjectBasicInfoDTO> implements ProjectBasicInfoSer {

    @Autowired
    private ProjectCostStatusSer projectCostStatusSer;//项目费用情况

    @Autowired
    private ProjectPersonnelDemandSer projectPersonnelDemandSer;//项目人员需求

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块权限
        Boolean permissionLevel = cusPermissionSer.busCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken( userToken );

    }

    /**
     * 分页查询项目基本信息
     *
     * @return class ProjectBasicInfoBO
     * @throws SerException
     */
    @Override
    public List<ProjectBasicInfoBO> list(ProjectBasicInfoDTO dto) throws SerException {
//        checkPermission();
        List<ProjectBasicInfo> list = super.findByPage(dto);
        List<ProjectBasicInfoBO> listBO = BeanTransform.copyProperties(list, ProjectBasicInfoBO.class);
        return listBO;
    }

    /**
     * 保存项目基本信息
     *
     * @param to 项目基本信息to
     * @return class ProjectBasicInfoBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectBasicInfoBO save(ProjectBasicInfoTO to) throws SerException {
//        checkPermission();
        ProjectBasicInfo entity = BeanTransform.copyProperties(to, ProjectBasicInfo.class, true);
        entity = super.save(entity);
        ProjectBasicInfoBO bo = BeanTransform.copyProperties(entity, ProjectBasicInfoBO.class);
        return bo;
    }

    /**
     * 更新项目基本信息
     *
     * @param to 项目基本信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ProjectBasicInfoTO to) throws SerException {
//        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            ProjectBasicInfo model = super.findById(to.getId());
            if (model != null) {
                updateProjectBasicInfo(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新项目基本信息
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateProjectBasicInfo(ProjectBasicInfoTO to, ProjectBasicInfo model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除项目基本信息
     *
     * @param id 项目基本信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
//        checkPermission();
        ProjectBasicInfo entity = super.findById(id);
        String projectName = entity.getProjectName();//获取项目名称
        deleteProjectCostByName(projectName);//删除项目费用情况
        deleteProjectPersonnelDemandByName(projectName);//删除项目人员需求管理
        // TODO: 17-3-23
        //删除单项目单个界面,单项目多个界面,项目基本信息,多项目多个界面

        super.remove(id);
    }

    /**
     * 根据项目名称删除项目人员需求
     *　
     * @param projectName 项目名称
     * @throws SerException
     */
    private void deleteProjectPersonnelDemandByName(String projectName) throws SerException {
        ProjectPersonnelDemandDTO dto = new ProjectPersonnelDemandDTO();
        dto.getConditions().add(Restrict.eq("projectName", projectName));
        List<ProjectPersonnelDemand> list = projectPersonnelDemandSer.findByCis(dto);
        projectPersonnelDemandSer.remove(list);
    }

    /**
     * 根据项目名称删除项目费用情况
     *
     * @param projectName 项目名称
     * @throws SerException
     */
    private void deleteProjectCostByName(String projectName) throws SerException {
        ProjectCostStatusDTO dto = new ProjectCostStatusDTO();
        dto.getConditions().add(Restrict.eq("projectName", projectName));
        List<ProjectCostStatus> list = projectCostStatusSer.findByCis(dto);
        projectCostStatusSer.remove(list);//批量删除项目费用情况
    }
}