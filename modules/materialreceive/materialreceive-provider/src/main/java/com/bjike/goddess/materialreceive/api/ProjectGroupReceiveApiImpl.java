package com.bjike.goddess.materialreceive.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialreceive.bo.ProjectGroupReceiveBO;
import com.bjike.goddess.materialreceive.dto.ProjectGroupReceiveDTO;
import com.bjike.goddess.materialreceive.entity.ProjectGroupReceive;
import com.bjike.goddess.materialreceive.service.ProjectGroupReceiveSer;
import com.bjike.goddess.materialreceive.to.ProjectGroupReceiveTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目组领用归还业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:52 ]
 * @Description: [ 项目组领用归还业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectGroupReceiveApiImpl")
public class ProjectGroupReceiveApiImpl implements ProjectGroupReceiveAPI {

    @Autowired
    private ProjectGroupReceiveSer projectGroupReceiveSer;

    /**
     * 根据id查询项目组领用归还
     *
     * @param id 项目组领用归还唯一标识
     * @return class ProjectGroupReceiveBO
     * @throws SerException
     */
    @Override
    public ProjectGroupReceiveBO findById(String id) throws SerException {
        ProjectGroupReceive model = projectGroupReceiveSer.findById(id);
        return BeanTransform.copyProperties(model, ProjectGroupReceiveBO.class);
    }

    /**
     * 分页查询项目组领用归还
     *
     * @return class ProjectGroupReceiveBO
     * @throws SerException
     */
    @Override
    public List<ProjectGroupReceiveBO> list(ProjectGroupReceiveDTO dto) throws SerException {
        return projectGroupReceiveSer.list(dto);
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
        return projectGroupReceiveSer.save(to);
    }

    /**
     * 根据id删除项目组领用归还
     *
     * @param id 项目组领用归还唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        projectGroupReceiveSer.remove(id);
    }

    /**
     * 归还物资
     *
     * @param to 项目组领用归还to
     * @throws SerException
     */
    @Override
    public void returnMaterial(ProjectGroupReceiveTO to) throws SerException {
        projectGroupReceiveSer.returnMaterial(to);
    }

    /**
     * 更新更新项目组领用归还
     *
     * @param to 项目组领用归还to
     * @throws SerException
     */
    @Override
    public void update(ProjectGroupReceiveTO to) throws SerException {
        projectGroupReceiveSer.update(to);
    }

}