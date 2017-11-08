package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.StayApplyBO;
import com.bjike.goddess.checkhost.dto.StayApplyDTO;
import com.bjike.goddess.checkhost.entity.StayApply;
import com.bjike.goddess.checkhost.enums.GuideAddrStatus;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.to.StayApplyTO;
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
 * 住宿申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 03:38 ]
 * @Description: [ 住宿申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkhostSerCache")
@Service
public class StayApplySerImpl extends ServiceImpl<StayApply, StayApplyDTO> implements StayApplySer {
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
    public Long countStayApply(StayApplyDTO stayApplyDTO) throws SerException {
        Long count = super.count(stayApplyDTO);
        return count;
    }

    @Override
    public StayApplyBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        StayApply stayApply = super.findById(id);
        return BeanTransform.copyProperties(stayApply, StayApplyBO.class);
    }

    @Override
    public List<StayApplyBO> findListStayApply(StayApplyDTO stayApplyDTO) throws SerException {
        checkSeeIdentity();
        List<StayApply> stayApplies = super.findByCis(stayApplyDTO, true);
        return BeanTransform.copyProperties(stayApplies, StayApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayApplyBO insertStayApply(StayApplyTO stayApplyTO) throws SerException {
        checkAddIdentity();
        StayApply stayApply = BeanTransform.copyProperties(stayApplyTO, StayApply.class, true);
        stayApply.setCreateTime(LocalDateTime.now());
        super.save(stayApply);
        return BeanTransform.copyProperties(stayApply, StayApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayApplyBO editStayApply(StayApplyTO stayApplyTO) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(stayApplyTO.getId())) {
            StayApply stayApply = super.findById(stayApplyTO.getId());
            LocalDateTime createTime = stayApply.getCreateTime();
            stayApply = BeanTransform.copyProperties(stayApplyTO, StayApply.class, true);
            stayApply.setCreateTime(createTime);
            stayApply.setModifyTime(LocalDateTime.now());
            super.update(stayApply);
        } else {
            throw new SerException("更新ID不能为空");
        }
        return BeanTransform.copyProperties(stayApplyTO, StayApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeStayApply(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayApplyBO manageAudit(StayApplyTO to) throws SerException {
        checkAduitIdentity();
//        UserBO userBO = userAPI.currentUser();
        StayApply apply = super.findById(to.getId());
        apply.setHeadAudit(to.getHeadAudit());
        apply.setCheckStatus(to.getCheckStatus());
        apply.setModifyTime(LocalDateTime.now());
        super.update(apply);
        StayApplyBO bo = BeanTransform.copyProperties(apply, StayApplyBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayApplyBO applyHost(StayApplyTO to) throws SerException {
        checkAddIdentity();
        StayApply apply = super.findById(to.getId());
        BeanTransform.copyProperties(to,apply,true);
        apply.setModifyTime(LocalDateTime.now());
        super.update(apply);
        StayApplyBO bo = BeanTransform.copyProperties(apply, StayApplyBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayApplyBO hostAudit(StayApplyTO to) throws SerException {
        checkAduitIdentity();
        StayApply apply = super.findById(to.getId());
        apply.setModuleAudit(to.getModuleAudit());
        apply.setModuleCheckStatus(to.getModuleCheckStatus());
        apply.setModifyTime(LocalDateTime.now());
        super.update(apply);
        StayApplyBO bo = BeanTransform.copyProperties(apply, StayApplyBO.class);
        return bo;
    }
}