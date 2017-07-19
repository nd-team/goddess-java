package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.HostApplyBO;
import com.bjike.goddess.checkhost.dto.HostApplyDTO;
import com.bjike.goddess.checkhost.entity.HostApply;
import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.checkhost.enums.GuideAddrStatus;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.to.HostApplyTO;
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
 * 离宿申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 04:51 ]
 * @Description: [ 离宿申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkhostSerCache")
@Service
public class HostApplySerImpl extends ServiceImpl<HostApply, HostApplyDTO> implements HostApplySer {
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
     * 模块责任人审核
     */
    private void checkAduitIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是福利模块负责人，不可以操作");
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
     * 模块责任人审核
     */
    private Boolean guideAduitIdentity() throws SerException {
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

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAduit = guideAduitIdentity();
        if (flagSee || flagAdd || flagAduit) {
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
            case DEPARTADUIT:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case HEADADUIT:
                flag = guideAduitIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countHostApply(HostApplyDTO hostApplyDTO) throws SerException {
        Long count = super.count(hostApplyDTO);
        return count;
    }

    @Override
    public HostApplyBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        HostApply hostApply = super.findById(id);
        return BeanTransform.copyProperties(hostApply, HostApplyBO.class);
    }

    @Override
    public List<HostApplyBO> findListHostApply(HostApplyDTO hostApplyDTO) throws SerException {
        checkSeeIdentity();
        List<HostApply> hostApplies = super.findByCis(hostApplyDTO, true);
        return BeanTransform.copyProperties(hostApplies, HostApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HostApplyBO insertHostApply(HostApplyTO hostApplyTO) throws SerException {
        checkAddIdentity();
        HostApply hostApply = BeanTransform.copyProperties(hostApplyTO, HostApply.class, true);
        hostApply.setCreateTime(LocalDateTime.now());
        super.save(hostApply);
        return BeanTransform.copyProperties(hostApply, HostApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HostApplyBO editHostApply(HostApplyTO hostApplyTO) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(hostApplyTO.getId())) {
            HostApply hostApply = super.findById(hostApplyTO.getId());
            BeanTransform.copyProperties(hostApplyTO, hostApply, true);
            hostApply.setModifyTime(LocalDateTime.now());
            super.update(hostApply);
        } else {
            throw new SerException("更新ID不能为空");
        }
        return BeanTransform.copyProperties(hostApplyTO, HostApply.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeHostApply(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public HostApplyBO auditHostApply(String id, CheckStatus checkStatus) throws SerException {
        checkAduitIdentity();
//        hostApplyTO.setHeadAudit(userAPI.currentUser().getUsername());
//        HostApply hostApply = BeanTransform.copyProperties(hostApplyTO, HostApply.class, true);
        HostApply hostApply=super.findById(id);
        hostApply.setCheckStatus(checkStatus);
        hostApply.setModifyTime(LocalDateTime.now());
        super.update(hostApply);

        HostApplyBO hostApplyBO = BeanTransform.copyProperties(hostApply, HostApplyBO.class);
        return hostApplyBO;
    }
}