package com.bjike.goddess.materialreceive.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialreceive.bo.MaterialReceiveBO;
import com.bjike.goddess.materialreceive.bo.ProjectGroupReceiveBO;
import com.bjike.goddess.materialreceive.dto.ProjectGroupReceiveDTO;
import com.bjike.goddess.materialreceive.entity.MaterialReceive;
import com.bjike.goddess.materialreceive.entity.ProjectGroupReceive;
import com.bjike.goddess.materialreceive.to.ProjectGroupReceiveTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目组领用归还业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:52 ]
 * @Description: [ 项目组领用归还业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialreceiveSerCache")
@Service
public class ProjectGroupReceiveSerImpl extends ServiceImpl<ProjectGroupReceive, ProjectGroupReceiveDTO> implements ProjectGroupReceiveSer {

    /**
     * 分页查询项目组领用归还
     *
     * @return class ProjectGroupReceiveBO
     * @throws SerException
     */
    @Override
    public List<ProjectGroupReceiveBO> list(ProjectGroupReceiveDTO dto) throws SerException {
        List<ProjectGroupReceive> list = super.findByPage(dto);
        List<ProjectGroupReceiveBO> listBO = BeanTransform.copyProperties(list, ProjectGroupReceiveBO.class);
        return listBO;
    }

    /**
     * 保存项目组领用归还
     *
     * @param to 项目组领用归还to
     * @return class ProjectGroupReceiveBO
     * @throws SerException
     */
    @Override
    public ProjectGroupReceiveBO save(ProjectGroupReceiveTO to) throws SerException {
        ProjectGroupReceive entity = BeanTransform.copyProperties(to, ProjectGroupReceive.class, true);
        entity = super.save(entity);
        ProjectGroupReceiveBO bo = BeanTransform.copyProperties(entity, ProjectGroupReceiveBO.class);
        return bo;
    }

    /**
     * 根据id删除项目组领用归还
     *
     * @param id 项目组领用归还唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 归还物资
     *
     * @param to 项目组领用归还to
     * @throws SerException
     */
    @Override
    public void returnMaterial(ProjectGroupReceiveTO to) throws SerException {
        update(to);
    }

    /**
     * 更新更新项目组领用归还
     *
     * @param to 项目组领用归还to
     * @throws SerException
     */
    @Override
    public void update(ProjectGroupReceiveTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            ProjectGroupReceive model = super.findById(to.getId());
            if (model != null) {
                updateProjectGroupReceive(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新项目组领用归还
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateProjectGroupReceive(ProjectGroupReceiveTO to, ProjectGroupReceive model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}