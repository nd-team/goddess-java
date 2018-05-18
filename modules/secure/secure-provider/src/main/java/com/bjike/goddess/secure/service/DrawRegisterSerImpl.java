package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.DrawRegisterBO;
import com.bjike.goddess.secure.dto.DrawRegisterDTO;
import com.bjike.goddess.secure.entity.DrawRegister;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.DrawRegisterTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
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
 * 社保卡领取登记表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 05:55 ]
 * @Description: [ 社保卡领取登记表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class DrawRegisterSerImpl extends ServiceImpl<DrawRegister, DrawRegisterDTO> implements DrawRegisterSer {

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
    private void checkAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
            if (!flag) {
                throw new SerException("您不是负责人的人员，不可以操作");
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
    private Boolean guideAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
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
        Boolean flagAudit=guideAuditIdentity();
        if (flagSee || flagAdd || flagAudit) {
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
                flag = guideAuditIdentity();
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
    public DrawRegisterBO save(DrawRegisterTO to) throws SerException {
        checkAddIdentity();
        DrawRegister drawRegister = BeanTransform.copyProperties(to, DrawRegister.class, true);
        drawRegister.setCreateTime(LocalDateTime.now());
        super.save(drawRegister);
        DrawRegisterBO bo = BeanTransform.copyProperties(drawRegister, DrawRegisterBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DrawRegisterBO edit(DrawRegisterTO to) throws SerException {
        checkAddIdentity();
        DrawRegister drawRegister = super.findById(to.getId());
        LocalDateTime createTime = drawRegister.getCreateTime();
        drawRegister = BeanTransform.copyProperties(to, DrawRegister.class, true);
        drawRegister.setCreateTime(createTime);
        drawRegister.setModifyTime(LocalDateTime.now());
        DrawRegisterBO bo = BeanTransform.copyProperties(drawRegister, DrawRegisterBO.class);
        return bo;
    }

    @Override
    public List<DrawRegisterBO> list(DrawRegisterDTO dto) throws SerException {
        checkSeeIdentity();
        List<DrawRegister> drawRegisters = super.findByCis(dto);
        List<DrawRegisterBO> drawRegisterBOS = BeanTransform.copyProperties(drawRegisters, DrawRegisterBO.class);
        return drawRegisterBOS;
    }

    @Override
    public DrawRegisterBO findByID(String id) throws SerException {
        DrawRegister drawRegister = super.findById(id);
        return BeanTransform.copyProperties(drawRegister, DrawRegisterBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        super.remove(id);

    }

    @Override
    public Long count(DrawRegisterDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public Set<String> allName() throws SerException {
        Set<String> set = new HashSet<>();
        List<DrawRegister> list = super.findAll();
        for (DrawRegister entity : list) {
            set.add(entity.getName());
        }
        return set;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DrawRegisterBO chargeAudit(DrawRegisterTO to) throws SerException {
        checkAuditIdentity();
        DrawRegister drawRegister = super.findById(to.getId());
        BeanTransform.copyProperties(to, drawRegister, true);
        drawRegister.setModifyTime(LocalDateTime.now());
        DrawRegisterBO bo = BeanTransform.copyProperties(drawRegister, DrawRegisterBO.class);
        return bo;
    }

}