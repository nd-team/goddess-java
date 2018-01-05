package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectPersonnelDemandBO;
import com.bjike.goddess.projectmeasure.dto.ProjectPersonnelDemandDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectPersonnelDemand;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectPersonnelDemandTO;
import com.bjike.goddess.projectmeasure.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目人员需求业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class ProjectPersonnelDemandSerImpl extends ServiceImpl<ProjectPersonnelDemand, ProjectPersonnelDemandDTO> implements ProjectPersonnelDemandSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserAPI userAPI;

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        //商务模块权限
        if( !"admin".equals( userName.toLowerCase())){
            flag = cusPermissionSer.busCusPermission("1");
        }else{
            flag = true;
        }
        if ( !flag) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken( userToken );

    }

    /**
     * 导航权限（部门级别）
     */
    private Boolean guildPermission() throws SerException{
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( !"admin".equals( userName.toLowerCase())){
            flag = cusPermissionSer.busCusPermission("1");
        }else{
            flag = true;
        }
        return flag;
    }

    /**
     * 分页查询项目人员需求
     *
     * @return class ProjectPersonnelDemandBO
     * @throws SerException
     */
    @Override
    public List<ProjectPersonnelDemandBO> list(ProjectPersonnelDemandDTO dto) throws SerException {
        checkPermission();
        List<ProjectPersonnelDemand> list = super.findByPage(dto);
        List<ProjectPersonnelDemandBO> listBO = BeanTransform.copyProperties(list, ProjectPersonnelDemandBO.class);
        return listBO;
    }

    /**
     * 保存项目人员需求
     *
     * @param to 项目人员需求to
     * @return class ProjectPersonnelDemandBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectPersonnelDemandBO save(ProjectPersonnelDemandTO to) throws SerException {
        checkPermission();
        ProjectPersonnelDemand entity = BeanTransform.copyProperties(to, ProjectPersonnelDemand.class, true);
        entity = super.save(entity);
        ProjectPersonnelDemandBO bo = BeanTransform.copyProperties(entity, ProjectPersonnelDemandBO.class);
        return bo;
    }

    /**
     * 更新项目人员需求
     *
     * @param to 项目人员需求to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ProjectPersonnelDemandTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            ProjectPersonnelDemand model = super.findById(to.getId());
            if (model != null) {
                updateProjectPersonnelDemand(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新项目人员需求
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateProjectPersonnelDemand(ProjectPersonnelDemandTO to, ProjectPersonnelDemand model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除项目人员需求
     *
     * @param id 项目人员需求唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guildPermission();
        RpcTransmit.transmitUserToken( userToken );
        if( flagSee  ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guildPermission();
                break;
            case ADD:
                flag = guildPermission();
                break;
            case EDIT:
                flag = guildPermission();
                break;
            case DELETE:
                flag = guildPermission();
                break;
            case CONGEL:
                flag = guildPermission();
                break;
            case THAW:
                flag = guildPermission();
                break;
            case COLLECT:
                flag = guildPermission();
                break;
            case SEE:
                flag = guildPermission();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public ProjectPersonnelDemandBO getOne(String id) throws SerException{
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        ProjectPersonnelDemand projectBasicInfo = super.findById(id);
        return BeanTransform.copyProperties(projectBasicInfo, ProjectPersonnelDemandBO.class);
    }
}