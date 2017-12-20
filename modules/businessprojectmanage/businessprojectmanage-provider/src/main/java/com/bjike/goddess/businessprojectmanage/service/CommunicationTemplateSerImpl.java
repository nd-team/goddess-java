package com.bjike.goddess.businessprojectmanage.service;

import com.bjike.goddess.businessprojectmanage.bo.CommunicationTemplateBO;
import com.bjike.goddess.businessprojectmanage.enums.GuideAddrStatus;
import com.bjike.goddess.businessprojectmanage.to.CommunicationTemplateTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businessprojectmanage.dto.CommunicationTemplateDTO;
import com.bjike.goddess.businessprojectmanage.entity.CommunicationTemplate;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 各类沟通交流模板业务实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-09 02:31 ]
 * @Description: [ 各类沟通交流模板业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectmanageSerCache")
@Service
public class CommunicationTemplateSerImpl extends ServiceImpl<CommunicationTemplate, CommunicationTemplateDTO> implements CommunicationTemplateSer {

    @Autowired
    UserAPI userAPI;
    @Autowired
    CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1", null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（部门级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1", null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guidePlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.modCusPermission("4", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideBudgetIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.modCusPermission("5", null);
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
            case MANAGER:
                flag = guideManageIdentity();
                break;
            case PLAN:
                flag = guidePlanIdentity();
                break;
            case BUDGET:
                flag = guideBudgetIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public Long countCommuni(CommunicationTemplateDTO CommunicationTemplateDTO) throws SerException {
        Long count = super.count(CommunicationTemplateDTO);
        return count;
    }

    @Override
    public CommunicationTemplateBO getOneById(String id) throws SerException {
        CommunicationTemplate CommunicationTemplate = super.findById(id);
        return BeanTransform.copyProperties(CommunicationTemplate, CommunicationTemplateBO.class);
    }

    @Override
    public List<CommunicationTemplateBO> listCommuni(CommunicationTemplateDTO CommunicationTemplateDTO) throws SerException {
        List<CommunicationTemplate> CommunicationTemplateList = super.findByCis(CommunicationTemplateDTO, true);
        return BeanTransform.copyProperties(CommunicationTemplateList, CommunicationTemplateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommunicationTemplateBO addCommuni(CommunicationTemplateTO CommunicationTemplateTO) throws SerException {
        CommunicationTemplate CommunicationTemplate = BeanTransform.copyProperties(CommunicationTemplateTO, CommunicationTemplate.class, true);
        CommunicationTemplate.setCreateTime(LocalDateTime.now());
        super.save(CommunicationTemplate);
        return BeanTransform.copyProperties(CommunicationTemplate, CommunicationTemplateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommunicationTemplateBO editCommuni(CommunicationTemplateTO CommunicationTemplateTO) throws SerException {
        CommunicationTemplate CommunicationTemplate = super.findById(CommunicationTemplateTO.getId());
        if (CommunicationTemplate == null) {
            throw new SerException("指定ＩＤ实体不存在");
        }
        LocalDateTime dateTime = CommunicationTemplate.getCreateTime();
        CommunicationTemplate = BeanTransform.copyProperties(CommunicationTemplateTO, CommunicationTemplate.class, true);
        CommunicationTemplate.setCreateTime(dateTime);
        CommunicationTemplate.setModifyTime(LocalDateTime.now());
        super.update(CommunicationTemplate);
        return BeanTransform.copyProperties(CommunicationTemplate, CommunicationTemplateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteNode(String id) throws SerException {
        super.remove(id);
    }
}