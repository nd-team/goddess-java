package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.ProjectSituationBO;
import com.bjike.goddess.projectprocing.dto.ProjectSituationDTO;
import com.bjike.goddess.projectprocing.entity.ProjectSituation;
import com.bjike.goddess.projectprocing.to.ProjectSituationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目情况业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:16 ]
 * @Description: [ 项目情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class ProjectSituationSerImpl extends ServiceImpl<ProjectSituation, ProjectSituationDTO> implements ProjectSituationSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;


    @Override
    public Long countProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {
        if (StringUtils.isNotBlank(projectSituationDTO.getEnginPlace())) {
            projectSituationDTO.getConditions().add(Restrict.like("enginPlace", projectSituationDTO.getEnginPlace()));
        }
        if (StringUtils.isNotBlank(projectSituationDTO.getCompleteCondition())) {
            projectSituationDTO.getConditions().add(Restrict.like("completeCondition", projectSituationDTO.getCompleteCondition()));
        }
        return super.count(projectSituationDTO);
    }

    @Override
    public ProjectSituationBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        ProjectSituation projectSituation = super.findById( id );
        return BeanTransform.copyProperties( projectSituation , ProjectSituationBO.class);
    }

    @Override
    public List<ProjectSituationBO> listProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {

        //列表权限
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您的帐号没有权限");
        }


        if (StringUtils.isNotBlank(projectSituationDTO.getEnginPlace())) {
            projectSituationDTO.getConditions().add(Restrict.like("enginPlace", projectSituationDTO.getEnginPlace()));
        }
        if (StringUtils.isNotBlank(projectSituationDTO.getCompleteCondition())) {
            projectSituationDTO.getConditions().add(Restrict.like("completeCondition", projectSituationDTO.getCompleteCondition()));
        }
        List<ProjectSituation> list = super.findByCis(projectSituationDTO,true);
        return BeanTransform.copyProperties(list,ProjectSituationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectSituationBO addProjectSituation(ProjectSituationTO projectSituationTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行添加基本信息操作");
        }

        ProjectSituation projectSituation = BeanTransform.copyProperties(projectSituationTO,ProjectSituation.class,true);
        projectSituation.setCreateTime(LocalDateTime.now());
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.save( projectSituation );
        return BeanTransform.copyProperties(projectSituation,ProjectSituationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectSituationBO editProjectSituation(ProjectSituationTO projectSituationTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行编辑基本信息操作");
        }


        if(StringUtils.isBlank(projectSituationTO.getId()) || projectSituationTO.getId()==null){
            throw  new SerException("编号不能为空");
        }
        ProjectSituation projectSituation = super.findById( projectSituationTO.getId() );
        if( projectSituation == null ){
            throw  new SerException("编辑失败，您填写的数据可能有错");
        }
        ProjectSituation temp = BeanTransform.copyProperties(projectSituationTO,ProjectSituation.class,true);
        BeanUtils.copyProperties(temp,projectSituation,"id","createTime","outerNameId","innerNameId","saleNumId");
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        projectSituation.setModifyTime(LocalDateTime.now());
        super.update( projectSituation );
        return BeanTransform.copyProperties(projectSituation,ProjectSituationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteProjectSituation(String id) throws SerException {
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
        ProjectSituation projectSituation = super.findById(id);
        if( projectSituation == null ){
            throw new SerException("删除失败，可能您传过来的数据有误");
        }
        super.remove(id);
    }

    @Override
    public List<ProjectSituationBO> searchListProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {
        List<ProjectSituation> list = super.findByCis(projectSituationDTO,true);
        return BeanTransform.copyProperties(list,ProjectSituationBO.class);
    }
}