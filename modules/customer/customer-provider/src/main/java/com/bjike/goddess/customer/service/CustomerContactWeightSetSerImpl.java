package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CustomerContactWeightSetBO;
import com.bjike.goddess.customer.dto.CustomerContactWeightSetDTO;
import com.bjike.goddess.customer.entity.CustomerContactWeightSet;
import com.bjike.goddess.customer.enums.GuideAddrStatus;
import com.bjike.goddess.customer.to.CustomerContactWeightSetTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 客户接触阶段权重设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:46 ]
 * @Description: [ 客户接触阶段权重设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CustomerContactWeightSetSerImpl extends ServiceImpl<CustomerContactWeightSet, CustomerContactWeightSetDTO> implements CustomerContactWeightSetSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;


    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity("1");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity("3");
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
                flag = guideSeeIdentity("1");
                break;
            case ADD:
                flag = guideAddIdentity("3");
                break;
            case EDIT:
                flag = guideAddIdentity("3");
                break;
            case DELETE:
                flag = guideAddIdentity("3");
                break;
            case UPLOAD:
                flag = guideAddIdentity("3");
                break;
            case SEEFILE:
                flag = guideAddIdentity("3");
                break;
            case DOWNLOAD:
                flag = guideAddIdentity("3");
                break;
            case CONGEL:
                flag = guideAddIdentity("3");
                break;
            case THAW:
                flag = guideAddIdentity("3");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countContactWeight(CustomerContactWeightSetDTO customerContactWeightSetDTO) throws SerException {
        Long count = super.count(customerContactWeightSetDTO);
        return count;
    }

    @Override
    public CustomerContactWeightSetBO getOneContactWeight(String id) throws SerException {
        CustomerContactWeightSet customerContactWeightSet = super.findById(id);
        return BeanTransform.copyProperties(customerContactWeightSet, CustomerContactWeightSet.class);
    }

    @Override
    public List<CustomerContactWeightSetBO> listContactWeight(CustomerContactWeightSetDTO customerContactWeightSetDTO) throws SerException {
        List<CustomerContactWeightSet> customerContactWeightSetList = super.findByCis(customerContactWeightSetDTO, true);
        return BeanTransform.copyProperties(customerContactWeightSetList, CustomerContactWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerContactWeightSetBO addContactWeight(CustomerContactWeightSetTO customerContactWeightSetTO) throws SerException {
        CustomerContactWeightSet customerContactWeightSet = BeanTransform.copyProperties(customerContactWeightSetTO, CustomerContactWeightSet.class, true);
        customerContactWeightSet.setCreateTime(LocalDateTime.now());
        super.save(customerContactWeightSet);
        return BeanTransform.copyProperties(customerContactWeightSet, CustomerContactWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerContactWeightSetBO editContactWeight(CustomerContactWeightSetTO customerContactWeightSetTO) throws SerException {
        CustomerContactWeightSet customerContactWeightSet = super.findById(customerContactWeightSetTO.getId());
        LocalDateTime dateTime = customerContactWeightSet.getCreateTime();
        customerContactWeightSet = BeanTransform.copyProperties(customerContactWeightSetTO, CustomerContactWeightSet.class, true);
        customerContactWeightSet.setCreateTime(dateTime);
        super.update(customerContactWeightSet);
        return BeanTransform.copyProperties(customerContactWeightSet, CustomerContactWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteContactWeight(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public CustomerContactWeightSetBO findByCustomerType(String customerContactType) throws SerException {
        CustomerContactWeightSetDTO customerContactWeightSetDTO = new CustomerContactWeightSetDTO();
        customerContactWeightSetDTO.getConditions().add(Restrict.eq("customerContactType",customerContactType));
        CustomerContactWeightSet customerContactWeightSet = super.findOne(customerContactWeightSetDTO);
        return BeanTransform.copyProperties(customerContactWeightSet,CustomerContactWeightSet.class);
    }
}