package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.AccrualAllotBO;
import com.bjike.goddess.moneyside.dto.AccrualAllotDTO;
import com.bjike.goddess.moneyside.entity.AccrualAllot;
import com.bjike.goddess.moneyside.enums.GuideAddrStatus;
import com.bjike.goddess.moneyside.to.AccrualAllotTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
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
 * 权责分配业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:13 ]
 * @Description: [ 权责分配业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class AccrualAllotSerImpl extends ServiceImpl<AccrualAllot, AccrualAllotDTO> implements AccrualAllotSer {
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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
        return flag;
    }

    @Override
    public Long countAccrualAllot(AccrualAllotDTO accrualAllotDTO) throws SerException {
        Long count = super.count(accrualAllotDTO);
        return count;
    }

    @Override
    public AccrualAllotBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        AccrualAllot accrualAllot = super.findById(id);
        return BeanTransform.copyProperties(accrualAllot, AccrualAllotBO.class);
    }

    @Override
    public List<AccrualAllotBO> findListAccrualAllot(AccrualAllotDTO accrualAllotDTO) throws SerException {
        List<AccrualAllot> accrualAllots = super.findByPage(accrualAllotDTO);
        List<AccrualAllotBO> accrualAllotBOS = BeanTransform.copyProperties(accrualAllots, AccrualAllotBO.class);
        return accrualAllotBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccrualAllotBO insertAccrualAllot(AccrualAllotTO accrualAllotTO) throws SerException {
        AccrualAllot accrualAllot = BeanTransform.copyProperties(accrualAllotTO, AccrualAllot.class, true);
        accrualAllot.setCreateTime(LocalDateTime.now());
        super.save(accrualAllot);
        return BeanTransform.copyProperties(accrualAllot, AccrualAllotBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccrualAllotBO editAccrualAllot(AccrualAllotTO accrualAllotTO) throws SerException {
        if (StringUtils.isBlank(accrualAllotTO.getId())) {
            throw new SerException("id不能为空");
        }
        AccrualAllot accrualAllot = super.findById(accrualAllotTO.getId());
        LocalDateTime createTime = accrualAllot.getCreateTime();
        accrualAllot = BeanTransform.copyProperties(accrualAllotTO, AccrualAllot.class, true);
        accrualAllot.setCreateTime(createTime);
        accrualAllot.setModifyTime(LocalDateTime.now());
        super.update(accrualAllot);
        return BeanTransform.copyProperties(accrualAllot, AccrualAllotBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeAccrualAllot(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }


}