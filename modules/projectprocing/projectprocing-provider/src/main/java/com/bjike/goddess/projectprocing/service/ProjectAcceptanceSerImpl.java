package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.ProjectAcceptanceBO;
import com.bjike.goddess.projectprocing.dto.ProjectAcceptanceDTO;
import com.bjike.goddess.projectprocing.entity.ProjectAcceptance;
import com.bjike.goddess.projectprocing.to.ProjectAcceptanceTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目验收情况业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:54 ]
 * @Description: [ 项目验收情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class ProjectAcceptanceSerImpl extends ServiceImpl<ProjectAcceptance, ProjectAcceptanceDTO> implements ProjectAcceptanceSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    public Long countProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        if(StringUtils.isNoneBlank(projectAcceptanceDTO.getArea())){
            projectAcceptanceDTO.getConditions().add(Restrict.like("area",projectAcceptanceDTO.getArea()));
        }
        return super.count(projectAcceptanceDTO);
    }

    @Override
    public ProjectAcceptanceBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        ProjectAcceptance projectSituation = super.findById( id );
        return BeanTransform.copyProperties( projectSituation , ProjectAcceptanceBO.class);
    }

    @Override
    public List<ProjectAcceptanceBO> listProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        //列表权限
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您的帐号没有权限");
        }

        if(StringUtils.isNoneBlank(projectAcceptanceDTO.getArea())){
            projectAcceptanceDTO.getConditions().add(Restrict.like("area",projectAcceptanceDTO.getArea()));
        }
        List<ProjectAcceptance> list = super.findByCis(projectAcceptanceDTO,true);
        return BeanTransform.copyProperties(list,ProjectAcceptanceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectAcceptanceBO addProjectAcceptance(ProjectAcceptanceTO projectAcceptanceTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行添加基本信息操作");
        }

        ProjectAcceptance projectAcceptance = BeanTransform.copyProperties(projectAcceptanceTO,ProjectAcceptance.class,true);
        projectAcceptance.setCreateTime(LocalDateTime.now());
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.save( projectAcceptance );
        return BeanTransform.copyProperties(projectAcceptance,ProjectAcceptanceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectAcceptanceBO editProjectAcceptance(ProjectAcceptanceTO projectAcceptanceTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行编辑基本信息操作");
        }

        if(StringUtils.isBlank(projectAcceptanceTO.getId()) ){
            throw  new SerException("编号不能为空");
        }
        ProjectAcceptance projectAcceptance = super.findById( projectAcceptanceTO.getId() );
        if( projectAcceptance == null ){
            throw  new SerException("编辑失败，您填写的数据可能有错");
        }
        ProjectAcceptance temp = BeanTransform.copyProperties(projectAcceptanceTO,ProjectAcceptance.class,true);
        BeanUtils.copyProperties(temp,projectAcceptance,"id","createTime","outerNameId","innerNameId","saleNumId");
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        projectAcceptance.setModifyTime(LocalDateTime.now());
        super.update( projectAcceptance );
        return BeanTransform.copyProperties(projectAcceptance,ProjectAcceptanceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteProjectAcceptance(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块删除权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除基本信息操作");
        }

        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.remove(id);
    }

    @Override
    public List<ProjectAcceptanceBO> searchListProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        List<ProjectAcceptance> list = super.findByCis(projectAcceptanceDTO,true);
        return BeanTransform.copyProperties(list,ProjectAcceptanceBO.class);
    }
}