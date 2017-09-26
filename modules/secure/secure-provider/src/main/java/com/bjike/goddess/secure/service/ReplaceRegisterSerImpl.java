package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.ReplaceRegisterBO;
import com.bjike.goddess.secure.dto.ReplaceRegisterDTO;
import com.bjike.goddess.secure.entity.ReplaceRegister;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.ReplaceRegisterTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 社保卡补办登记表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 06:09 ]
 * @Description: [ 社保卡补办登记表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class ReplaceRegisterSerImpl extends ServiceImpl<ReplaceRegister, ReplaceRegisterDTO> implements ReplaceRegisterSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkResourcesIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
            if (!flag) {
                throw new SerException("您不是相应综合资源部的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkFinanceIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是相应运营财务部的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkGeneralIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
            if (!flag) {
                throw new SerException("您不是总经办的人员，不可以操作");
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

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideResourcesIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideFinanceIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideGeneralIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
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
        Boolean flagResources = guideResourcesIdentity();
        Boolean flagFinance = guideFinanceIdentity();
        Boolean flagGeneral = guideGeneralIdentity();
        if (flagSee || flagAdd || flagResources || flagFinance || flagGeneral) {
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
            case RESOURCES:
                flag = guideResourcesIdentity();
                break;
            case BUINESS:
                flag = guideFinanceIdentity();
                break;
            case BOSS:
                flag = guideGeneralIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReplaceRegisterBO save(ReplaceRegisterTO to) throws SerException {
        checkAddIdentity();
        ReplaceRegister replaceRegister = BeanTransform.copyProperties(to, ReplaceRegister.class, true);
        replaceRegister.setCreateTime(LocalDateTime.now());
        super.save(replaceRegister);
        ReplaceRegisterBO bo = BeanTransform.copyProperties(replaceRegister, ReplaceRegisterBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReplaceRegisterBO edit(ReplaceRegisterTO to) throws SerException {
        checkAddIdentity();
        ReplaceRegister replaceRegister = super.findById(to.getId());
        LocalDateTime createTime = replaceRegister.getCreateTime();
        replaceRegister = BeanTransform.copyProperties(to, ReplaceRegister.class, true);
        replaceRegister.setCreateTime(createTime);
        replaceRegister.setModifyTime(LocalDateTime.now());
        ReplaceRegisterBO bo = BeanTransform.copyProperties(replaceRegister, ReplaceRegisterBO.class);
        return bo;
    }

    @Override
    public List<ReplaceRegisterBO> list(ReplaceRegisterDTO dto) throws SerException {
        checkSeeIdentity();
        List<ReplaceRegister> replaceRegisters = super.findByCis(dto);
        List<ReplaceRegisterBO> replaceRegisterBOS = BeanTransform.copyProperties(replaceRegisters, ReplaceRegisterBO.class);
        return replaceRegisterBOS;
    }

    @Override
    public ReplaceRegisterBO findByID(String id) throws SerException {
        ReplaceRegister replaceRegister = super.findById(id);
        return BeanTransform.copyProperties(replaceRegister, ReplaceRegisterBO.class);
    }

    @Override
    public Long count(ReplaceRegisterDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public Set<String> allName() throws SerException {
        Set<String> set = new HashSet<>();
        List<ReplaceRegister> list = super.findAll();
        for (ReplaceRegister entity : list) {
            set.add(entity.getName());
        }
        return set;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReplaceRegisterBO resourcesAudit(ReplaceRegisterTO to) throws SerException {
        checkResourcesIdentity();
        ReplaceRegister replaceRegister = super.findById(to.getId());
        BeanTransform.copyProperties(to, replaceRegister, true);
        replaceRegister.setModifyTime(LocalDateTime.now());
        ReplaceRegisterBO bo = BeanTransform.copyProperties(replaceRegister, ReplaceRegisterBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReplaceRegisterBO financeAudit(ReplaceRegisterTO to) throws SerException {
        checkFinanceIdentity();
        ReplaceRegister replaceRegister = super.findById(to.getId());
        BeanTransform.copyProperties(to, replaceRegister, true);
        replaceRegister.setModifyTime(LocalDateTime.now());
        ReplaceRegisterBO bo = BeanTransform.copyProperties(replaceRegister, ReplaceRegisterBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReplaceRegisterBO generalAudit(ReplaceRegisterTO to) throws SerException {
        checkGeneralIdentity();
        ReplaceRegister replaceRegister = super.findById(to.getId());
        BeanTransform.copyProperties(to, replaceRegister, true);
        replaceRegister.setModifyTime(LocalDateTime.now());
        ReplaceRegisterBO bo = BeanTransform.copyProperties(replaceRegister, ReplaceRegisterBO.class);
        return bo;
    }
}