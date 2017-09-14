package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.bo.ProjectFactorsBO;
import com.bjike.goddess.projectroyalty.dto.ProjectFactorsDTO;
import com.bjike.goddess.projectroyalty.entity.ProjectFactors;
import com.bjike.goddess.projectroyalty.enums.GuideAddrStatus;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.ProjectFactorsTO;
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
 * 项目提成分配因素业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 11:39 ]
 * @Description: [ 项目提成分配因素业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class ProjectFactorsSerImpl extends ServiceImpl<ProjectFactors, ProjectFactorsDTO> implements ProjectFactorsSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;


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

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
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
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Transactional
    @Override
    public void save(ProjectFactorsTO to) throws SerException {
        ProjectFactors entity = BeanTransform.copyProperties(to, ProjectFactors.class, "name");
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        entity.setName(userBO.getUsername());
        super.save(entity);
    }

    @Transactional
    @Override
    public void update(ProjectFactorsTO to) throws SerException {
        ProjectFactors entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        BeanTransform.copyProperties(to, entity, "name");
        if (StringUtils.isBlank(to.getRemark())) {
            entity.setRemark("");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        entity.setName(userBO.getUsername());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Transactional
    @Override
    public void delete(String id) throws SerException {
        ProjectFactors entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        super.remove(entity);
    }

    @Override
    public ProjectFactorsBO getById(String id) throws SerException {
        ProjectFactors entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        ProjectFactorsBO projectFactorsBO = BeanTransform.copyProperties(entity, ProjectFactorsBO.class, false);
        return projectFactorsBO;
    }

    @Override
    public List<ProjectFactorsBO> maps(ProjectFactorsDTO dto) throws SerException {
        searchCondition(dto);
        List<ProjectFactors> projectFactorses = super.findByPage(dto);
        if (null != projectFactorses && projectFactorses.size() > 0) {
            List<ProjectFactorsBO> projectFactorsBOs = BeanTransform.copyProperties(projectFactorses, ProjectFactorsBO.class, false);
            return projectFactorsBOs;
        }
        return null;
    }

    @Override
    public Long getTotal(ProjectFactorsDTO dto) throws SerException {
        searchCondition(dto);
        return super.count(dto);
    }


    private void searchCondition(ProjectFactorsDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getModifyTime())) {
            dto.getConditions().add(Restrict.eq("modifyTime", dto.getModifyTime()));
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            dto.getConditions().add(Restrict.eq("name", dto.getName()));
        }
        if (StringUtils.isNotBlank(dto.getCode())) {
            dto.getConditions().add(Restrict.eq("code", dto.getCode()));
        }
    }
}