package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectCostStatusBO;
import com.bjike.goddess.projectmeasure.dto.ProjectCostStatusDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectCostStatus;
import com.bjike.goddess.projectmeasure.to.ProjectCostStatusTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目费用情况业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class ProjectCostStatusSerImpl extends ServiceImpl<ProjectCostStatus, ProjectCostStatusDTO> implements ProjectCostStatusSer {

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
     * 分页查询项目费用情况
     *
     * @return class ProjectCostStatusBO
     * @throws SerException
     */
    @Override
    public List<ProjectCostStatusBO> list(ProjectCostStatusDTO dto) throws SerException {
//        checkPermission();
        dto.getSorts().add("projectName=asc");
        List<ProjectCostStatus> list = super.findByPage(dto);
        List<ProjectCostStatusBO> listBO = BeanTransform.copyProperties(list, ProjectCostStatusBO.class);
        return listBO;
    }

    /**
     * 保存项目费用情况
     *
     * @param to 项目费用情况to
     * @return class ProjectCostStatusBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectCostStatusBO save(ProjectCostStatusTO to) throws SerException {
//        checkPermission();
        ProjectCostStatus entity = BeanTransform.copyProperties(to, ProjectCostStatus.class, true);
        entity = super.save(entity);
        ProjectCostStatusBO bo = BeanTransform.copyProperties(entity, ProjectCostStatusBO.class);
        return bo;
    }

    /**
     * 更新项目费用情况
     *
     * @param to 项目费用情况to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ProjectCostStatusTO to) throws SerException {
//        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            ProjectCostStatus model = super.findById(to.getId());
            if (model != null) {
                updateProjectCostStatus(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新项目费用情况
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateProjectCostStatus(ProjectCostStatusTO to, ProjectCostStatus model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除项目费用情况
     *
     * @param id 项目费用情况唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
//        checkPermission();
        super.remove(id);
    }
}