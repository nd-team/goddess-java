package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.AccountInfoManagementBO;
import com.bjike.goddess.foreigntax.dto.AccountInfoManagementDTO;
import com.bjike.goddess.foreigntax.entity.AccountInfoManagement;
import com.bjike.goddess.foreigntax.enums.GuideAddrStatus;
import com.bjike.goddess.foreigntax.excel.SonPermissionObject;
import com.bjike.goddess.foreigntax.to.AccountInfoManagementTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 外账资料管理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:52 ]
 * @Description: [ 外账资料管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class AccountInfoManagementSerImpl extends ServiceImpl<AccountInfoManagement, AccountInfoManagementDTO> implements AccountInfoManagementSer {

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


    @Override
    public Long countAccountInfoManagement(AccountInfoManagementDTO accountInfoManagementDTO) throws SerException {
        Long count = super.count(accountInfoManagementDTO);
        return count;
    }

    @Override
    public AccountInfoManagementBO getOne(String id) throws SerException {
        AccountInfoManagement accountInfoManagement = super.findById(id);
        return BeanTransform.copyProperties(accountInfoManagement, AccountInfoManagementBO.class);
    }

    @Override
    public List<AccountInfoManagementBO> findListAccountInfoManagement(AccountInfoManagementDTO accountInfoManagementDTO) throws SerException {
        checkSeeIdentity();
        List<AccountInfoManagement> accountInfoManagements = super.findByCis(accountInfoManagementDTO, true);
        List<AccountInfoManagementBO> accountInfoManagementBOS = BeanTransform.copyProperties(accountInfoManagements, AccountInfoManagementBO.class, true);
        return accountInfoManagementBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountInfoManagementBO insertAccountInfoManagement(AccountInfoManagementTO accountInfoManagementTO) throws SerException {
        checkAddIdentity();
        AccountInfoManagement accountInfoManagement = BeanTransform.copyProperties(accountInfoManagementTO, AccountInfoManagement.class, true);
        accountInfoManagement.setCreateTime(LocalDateTime.now());
        super.save(accountInfoManagement);
        return BeanTransform.copyProperties(accountInfoManagement, AccountInfoManagementBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountInfoManagementBO editAccountInfoManagement(AccountInfoManagementTO accountInfoManagementTO) throws SerException {
        checkAddIdentity();
        AccountInfoManagement accountInfoManagement = super.findById(accountInfoManagementTO.getId());
        BeanTransform.copyProperties(accountInfoManagementTO, accountInfoManagement, true);
        accountInfoManagement.setModifyTime(LocalDateTime.now());
        super.update(accountInfoManagement);
        return BeanTransform.copyProperties(accountInfoManagementTO, AccountInfoManagementBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeAccountInfoManagement(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

}