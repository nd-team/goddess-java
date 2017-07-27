package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.DismissionEmployeeBO;
import com.bjike.goddess.secure.bo.OutEmployeeBO;
import com.bjike.goddess.secure.dto.OutEmployeeDTO;
import com.bjike.goddess.secure.entity.OutEmployee;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.OutEmployeeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 离职名单业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 04:11 ]
 * @Description: [ 离职名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class OutEmployeeSerImpl extends ServiceImpl<OutEmployee, OutEmployeeDTO> implements OutEmployeeSer {
    @Autowired
    private BeforeRemoveEmployeeSer beforeRemoveEmployeeSer;
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
    @Transactional(rollbackFor = {SerException.class})
    public OutEmployeeBO is_again(OutEmployeeTO to) throws SerException {
        checkAddIdentity();
        OutEmployee outEmployee = super.findById(to.getId());
        outEmployee.setIsAgain(to.getIsAgain());
        outEmployee.setAdvice(to.getAdvice());
        outEmployee.setModifyTime(LocalDateTime.now());
        super.update(outEmployee);
        return BeanTransform.copyProperties(outEmployee, OutEmployeeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public OutEmployeeBO delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        return null;
    }

//    @Override
//    @Transactional(rollbackFor = {SerException.class})
//    public List<OutEmployeeBO> find(OutEmployeeDTO dto) throws SerException {
//        List<DismissionEmployeeBO> list1 = beforeRemoveEmployeeSer.all();
//        List<OutEmployee> all = findAll();
//        for (DismissionEmployeeBO d : list1) {
//            if (all.size() != 0) {
//                for (OutEmployee o : all) {
//                    if (!(o.getDimissionId().equals(d.getDimissionId()))) {
//                        OutEmployee outEmployee = new OutEmployee();
//                        outEmployee.setDimissionId(d.getDimissionId());
//                        outEmployee.setName(d.getName());
//                        outEmployee.setEndTime(d.getEndTime());
//                        super.save(outEmployee);
//                    }
//                }
//            } else {
//                OutEmployee outEmployee = new OutEmployee();
//                outEmployee.setDimissionId(d.getDimissionId());
//                outEmployee.setName(d.getName());
//                outEmployee.setEndTime(d.getEndTime());
//                super.save(outEmployee);
//            }
//        }
//        List<OutEmployee> list = super.findByCis(dto, true);
//        return BeanTransform.copyProperties(list, OutEmployeeBO.class);
//    }

    @Override
    public OutEmployeeBO findByID(String id) throws SerException {
        OutEmployee outEmployee = super.findById(id);
        return BeanTransform.copyProperties(outEmployee, OutEmployeeBO.class);
    }
}