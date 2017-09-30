package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.SubsidySchemeBO;
import com.bjike.goddess.assistance.dto.SubsidySchemeDTO;
import com.bjike.goddess.assistance.entity.SubsidyScheme;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.SubsidySchemeTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
 * 公司补助方案业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 10:30 ]
 * @Description: [ 公司补助方案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class SubsidySchemeSerImpl extends ServiceImpl<SubsidyScheme, SubsidySchemeDTO> implements SubsidySchemeSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对审核权限（岗位级别）
     */
    private void checkAduitIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对审核权限（岗位级别）
     */
    private Boolean guideAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    private Boolean allTrueIdentity() throws SerException {
        return true;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAuditIdentity();
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case MANAGEAUDIT:
                flag = guideAuditIdentity();
                break;
            case CONFIRM:
                flag = allTrueIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countSubsidy(SubsidySchemeDTO subsidySchemeDTO) throws SerException {
        Long count = super.count(subsidySchemeDTO);
        return count;
    }

    @Override
    public SubsidySchemeBO getOneById(String id) throws SerException {
        SubsidyScheme subsidyScheme = super.findById(id);
        return BeanTransform.copyProperties(subsidyScheme, SubsidySchemeBO.class);
    }

    @Override
    public List<SubsidySchemeBO> listSubsidy(SubsidySchemeDTO subsidySchemeDTO) throws SerException {
        checkSeeIdentity();
        search(subsidySchemeDTO);
        subsidySchemeDTO.getSorts().add("schemeSeq=asc");
        List<SubsidyScheme> subsidySchemes = super.findByPage(subsidySchemeDTO);
        return BeanTransform.copyProperties(subsidySchemes, SubsidySchemeBO.class);
    }

    public void search(SubsidySchemeDTO subsidySchemeDTO) throws SerException {
        if (StringUtils.isNotBlank(subsidySchemeDTO.getSchemeName())) {
            subsidySchemeDTO.getConditions().add(Restrict.eq("schemeName", subsidySchemeDTO.getSchemeName()));
        }
        if (StringUtils.isNotBlank(subsidySchemeDTO.getSubsidyName())) {
            subsidySchemeDTO.getConditions().add(Restrict.eq("subsidyName", subsidySchemeDTO.getSubsidyName()));
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubsidySchemeBO addSubsidy(SubsidySchemeTO subsidySchemeTO) throws SerException {
        checkSeeIdentity();
        SubsidyScheme subsidyScheme = BeanTransform.copyProperties(subsidySchemeTO, SubsidyScheme.class, true);
        subsidyScheme.setCreateTime(LocalDateTime.now());
        super.save(subsidyScheme);
        return BeanTransform.copyProperties(subsidyScheme, SubsidySchemeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubsidySchemeBO editSubsidy(SubsidySchemeTO subsidySchemeTO) throws SerException {
        checkSeeIdentity();
        SubsidyScheme subsidyScheme = super.findById(subsidySchemeTO.getId());
        if (!subsidyScheme.getImplement()) {
            LocalDateTime date = subsidyScheme.getCreateTime();
            subsidyScheme = BeanTransform.copyProperties(subsidySchemeTO, SubsidyScheme.class, true);
            subsidyScheme.setCreateTime(date);
            subsidyScheme.setModifyTime(LocalDateTime.now());
            super.update(subsidyScheme);
        } else {
            throw new SerException("该方案已被确定可以被实施,不能被修改");
        }
        return BeanTransform.copyProperties(subsidyScheme, SubsidySchemeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSubsidy(String id) throws SerException {
        checkSeeIdentity();
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void manageAudit(SubsidySchemeTO subsidySchemeTO) throws SerException {
        checkAduitIdentity();
        SubsidyScheme subsidyScheme = super.findById(subsidySchemeTO.getId());
        if (!subsidyScheme.getImplement()) {
            subsidyScheme.setManageOpinion(subsidySchemeTO.getManageOpinion());
            subsidyScheme.setImplement(subsidySchemeTO.getImplement());
            super.update(subsidyScheme);
        } else {
            throw new SerException("该方案已被确定可以被实施,审核过了");
        }
    }
}