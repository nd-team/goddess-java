package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.BuyBO;
import com.bjike.goddess.secure.dto.BuyDTO;
import com.bjike.goddess.secure.entity.Buy;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.BuyTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 购买社保人员业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:45 ]
 * @Description: [ 购买社保人员业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class BuySerImpl extends ServiceImpl<Buy, BuyDTO> implements BuySer {
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

    @Override
    public List<BuyBO> find(BuyDTO dto) throws SerException {
        checkSeeIdentity();
        List<Buy> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, BuyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BuyBO edit(BuyTO to) throws SerException {
        checkAddIdentity();
        Buy buy = super.findById(to.getId());
        LocalDateTime a = buy.getCreateTime();
        buy = BeanTransform.copyProperties(to, Buy.class, true);
        buy.setCreateTime(a);
        buy.setModifyTime(LocalDateTime.now());
        super.update(buy);
//        if (buy.getExamine()) {   //审批通过，添加到社保增员中
//            AddEmployeeTO addEmployeeTO = new AddEmployeeTO();
//            BeanUtils.copyProperties(buy, addEmployeeTO);
//            addEmployeeTO.setBornLocal(buy.getBorn());
//            addEmployeeTO.setSecureCity(buy.getCity());
//            addEmployeeTO.setType(buy.getSecureType());
//            addEmployeeSer.save(addEmployeeTO);
//            EmployeeSecure employeeSecure = new EmployeeSecure();
//            BeanUtils.copyProperties(buy, employeeSecure);
//            employeeSecure.setStatus("购买中");
//            employeeSecureSer.save(employeeSecure);
//        }
        return BeanTransform.copyProperties(buy, BuyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BuyBO delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        return null;
    }

    @Override
    public BuyBO findByID(String id) throws SerException {
        Buy buy = super.findById(id);
        return BeanTransform.copyProperties(buy, BuyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BuyBO save(BuyTO to) throws SerException {
        checkAddIdentity();
        Buy buy = BeanTransform.copyProperties(to, Buy.class, true);
        buy = super.save(buy);
        return BeanTransform.copyProperties(buy, BuyBO.class);
    }

    @Override
    public List<BuyBO> findByDTO(BuyDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByCis(dto), BuyBO.class);
    }

    @Override
    public Long count(BuyDTO dto) throws SerException {
        return super.count(dto);
    }
}