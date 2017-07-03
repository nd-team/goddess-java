package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.ProjectCarryAuditBO;
import com.bjike.goddess.projectprocing.dto.ProjectCarryAuditDTO;
import com.bjike.goddess.projectprocing.dto.ProjectCarryAuditDTO;
import com.bjike.goddess.projectprocing.entity.ProjectCarryAudit;
import com.bjike.goddess.projectprocing.entity.ProjectCarryAudit;
import com.bjike.goddess.projectprocing.to.ProjectCarryAuditTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目实施审核(针对没派工单情况)业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 06:41 ]
 * @Description: [ 项目实施审核(针对没派工单情况)业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class ProjectCarryAuditSerImpl extends ServiceImpl<ProjectCarryAudit, ProjectCarryAuditDTO> implements ProjectCarryAuditSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    public Long countProjectCarryAudit(ProjectCarryAuditDTO projectCarryAuditDTO) throws SerException {
        if(StringUtils.isNoneBlank(projectCarryAuditDTO.getSaleNum())){
            projectCarryAuditDTO.getConditions().add(Restrict.like("saleNum",projectCarryAuditDTO.getSaleNum()));
        }if(StringUtils.isNoneBlank(projectCarryAuditDTO.getSignProjectCondition())){
            projectCarryAuditDTO.getConditions().add(Restrict.like("signProjectCondition",projectCarryAuditDTO.getSignProjectCondition()));
        }
        return super.count(projectCarryAuditDTO);
    }

    @Override
    public ProjectCarryAuditBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        ProjectCarryAudit projectSituation = super.findById( id );
        return BeanTransform.copyProperties( projectSituation , ProjectCarryAuditBO.class);
    }

    @Override
    public List<ProjectCarryAuditBO> listProjectCarryAudit(ProjectCarryAuditDTO projectCarryAuditDTO) throws SerException {
        //列表权限
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您的帐号没有权限");
        }

        if(StringUtils.isNoneBlank(projectCarryAuditDTO.getSaleNum())){
            projectCarryAuditDTO.getConditions().add(Restrict.like("saleNum",projectCarryAuditDTO.getSaleNum()));
        }if(StringUtils.isNoneBlank(projectCarryAuditDTO.getSignProjectCondition())){
            projectCarryAuditDTO.getConditions().add(Restrict.like("signProjectCondition",projectCarryAuditDTO.getSignProjectCondition()));
        }
        List<ProjectCarryAudit> list = super.findByCis(projectCarryAuditDTO,true);
        return BeanTransform.copyProperties(list,ProjectCarryAuditBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectCarryAuditBO addProjectCarryAudit(ProjectCarryAuditTO projectCarryAuditTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行添加基本信息操作");
        }

        ProjectCarryAudit projectCarryAudit = BeanTransform.copyProperties(projectCarryAuditTO,ProjectCarryAudit.class,true);
        projectCarryAudit.setCreateTime(LocalDateTime.now());
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.save( projectCarryAudit );
        return BeanTransform.copyProperties(projectCarryAudit,ProjectCarryAuditBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectCarryAuditBO editProjectCarryAudit(ProjectCarryAuditTO projectCarryAuditTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行编辑基本信息操作");
        }

        if(StringUtils.isBlank(projectCarryAuditTO.getId()) ){
            throw  new SerException("编号不能为空");
        }
        ProjectCarryAudit projectCarryAudit = super.findById( projectCarryAuditTO.getId() );
        if( projectCarryAudit == null ){
            throw  new SerException("编辑失败，您填写的数据可能有错");
        }
        ProjectCarryAudit temp = BeanTransform.copyProperties(projectCarryAuditTO,ProjectCarryAudit.class,true);
        BeanUtils.copyProperties(temp,projectCarryAudit,"id","createTime","outerNameId","innerNameId","saleNumId");
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        projectCarryAudit.setModifyTime(LocalDateTime.now());
        super.update( projectCarryAudit );
        return BeanTransform.copyProperties(projectCarryAudit,ProjectCarryAuditBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteProjectCarryAudit(String id) throws SerException {
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
    public List<ProjectCarryAuditBO> searchListProjectCarryAudit(ProjectCarryAuditDTO projectCarryAuditDTO) throws SerException {
        List<ProjectCarryAudit> list = super.findByCis(projectCarryAuditDTO,true);
        return BeanTransform.copyProperties(list,ProjectCarryAuditBO.class);
    }
}