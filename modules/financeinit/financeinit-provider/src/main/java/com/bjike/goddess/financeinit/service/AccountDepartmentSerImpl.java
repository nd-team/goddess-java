package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.AccountDepartmentBO;
import com.bjike.goddess.financeinit.dto.AccountDepartmentDTO;
import com.bjike.goddess.financeinit.entity.AccountDepartment;
import com.bjike.goddess.financeinit.enums.GuideAddrStatus;
import com.bjike.goddess.financeinit.to.AccountDepartmentTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
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
 * 核算部门业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:43 ]
 * @Description: [ 核算部门业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class AccountDepartmentSerImpl extends ServiceImpl<AccountDepartment, AccountDepartmentDTO> implements AccountDepartmentSer {
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
                throw new SerException("您不是相应财务部门的人员，不可以查看");
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
                throw new SerException("您不是相应财务部门的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("1");
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
            case DELETE:
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
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countDepart(AccountDepartmentDTO accountDepartmentDTO) throws SerException {
        Long count = super.count(accountDepartmentDTO);
        return count;
    }

    @Override
    public AccountDepartmentBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        AccountDepartment accountDepartment = super.findById(id);
        return BeanTransform.copyProperties(accountDepartment, AccountDepartmentBO.class);
    }

    @Override
    public List<AccountDepartmentBO> listDepart(AccountDepartmentDTO accountDepartmentDTO) throws SerException {
        checkSeeIdentity();
        List<AccountDepartment> list = super.findByCis(accountDepartmentDTO, true);
        return BeanTransform.copyProperties(list, AccountDepartmentBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public AccountDepartmentBO addDepart(AccountDepartmentTO accountDepartmentTO) throws SerException {
       checkAddIdentity();
        AccountDepartment accountDepartment = BeanTransform.copyProperties(accountDepartmentTO, AccountDepartment.class, true);
        accountDepartment.setCreateTime(LocalDateTime.now());
        super.save(accountDepartment);
        return BeanTransform.copyProperties(accountDepartment, AccountDepartmentBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public AccountDepartmentBO editDepart(AccountDepartmentTO accountDepartmentTO) throws SerException {
        checkAddIdentity();
        AccountDepartment accountDepartment = super.findById(accountDepartmentTO.getId());
        LocalDateTime date = accountDepartment.getCreateTime();
        accountDepartment = BeanTransform.copyProperties(accountDepartmentTO, AccountDepartment.class);
        accountDepartment.setCreateTime(date);
        accountDepartment.setModifyTime(LocalDateTime.now());
        super.update(accountDepartment);
        return BeanTransform.copyProperties(accountDepartment, AccountDepartmentBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void deleteDepart(String id) throws SerException {
        checkAddIdentity();
        List<AccountDepartment> accountDepartmentList = super.findAll();
        if(accountDepartmentList.size()<=1){
            throw new SerException("这是最后一条数据了,是不能被删除的");
        }
        super.remove(id);
    }
}