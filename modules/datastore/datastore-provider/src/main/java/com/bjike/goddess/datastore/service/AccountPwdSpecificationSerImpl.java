package com.bjike.goddess.datastore.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.bo.AccountPwdSpecificationBO;
import com.bjike.goddess.datastore.dto.AccountPwdSpecificationDTO;
import com.bjike.goddess.datastore.entity.AccountPwdSpecification;
import com.bjike.goddess.datastore.enums.GuideAddrStatus;
import com.bjike.goddess.datastore.to.AccountPwdSpecificationTO;
import com.bjike.goddess.datastore.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据存储账号密码规范业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 06:14 ]
 * @Description: [ 数据存储账号密码规范业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "datastoreSerCache")
@Service
public class AccountPwdSpecificationSerImpl extends ServiceImpl<AccountPwdSpecification, AccountPwdSpecificationDTO> implements AccountPwdSpecificationSer {
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
    public Long countAccountPwdSpecification(AccountPwdSpecificationDTO accountPwdSpecificationDTO) throws SerException {
        accountPwdSpecificationDTO.getSorts().add("createTime=desc");
        Long count = super.count(accountPwdSpecificationDTO);
        return count;
    }

    @Override
    public AccountPwdSpecificationBO getOne(String id) throws SerException {
        AccountPwdSpecification accountPwdSpecification = super.findById(id);
        return BeanTransform.copyProperties(accountPwdSpecification, AccountPwdSpecificationBO.class);
    }

    @Override
    public List<AccountPwdSpecificationBO> findListAccountPwdSpecification(AccountPwdSpecificationDTO accountPwdSpecificationDTO) throws SerException {
        checkSeeIdentity();
        accountPwdSpecificationDTO.getSorts().add("createTime=desc");
        List<AccountPwdSpecification> accountPwdSpecifications = super.findByCis(accountPwdSpecificationDTO, true);
        List<AccountPwdSpecificationBO> accountPwdSpecificationBOS = BeanTransform.copyProperties(accountPwdSpecifications, AccountPwdSpecificationBO.class);
        return accountPwdSpecificationBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountPwdSpecificationBO insertAccountPwdSpecification(AccountPwdSpecificationTO accountPwdSpecificationTO) throws SerException {
        checkAddIdentity();
        AccountPwdSpecification accountPwdSpecification = BeanTransform.copyProperties(accountPwdSpecificationTO, AccountPwdSpecification.class, true);
        accountPwdSpecification.setCreateTime(LocalDateTime.now());
        super.save(accountPwdSpecification);
        return BeanTransform.copyProperties(accountPwdSpecification, AccountPwdSpecificationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountPwdSpecificationBO editAccountPwdSpecification(AccountPwdSpecificationTO accountPwdSpecificationTO) throws SerException {
        checkAddIdentity();
        AccountPwdSpecification accountPwdSpecification = super.findById(accountPwdSpecificationTO.getId());
        LocalDateTime createTime = accountPwdSpecification.getCreateTime();
        accountPwdSpecification = BeanTransform.copyProperties(accountPwdSpecificationTO, AccountPwdSpecification.class, true);
        accountPwdSpecification.setCreateTime(createTime);
        accountPwdSpecification.setModifyTime(LocalDateTime.now());
        super.update(accountPwdSpecification);
        return BeanTransform.copyProperties(accountPwdSpecificationTO, AccountPwdSpecificationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeAccountPwdSpecification(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }
}