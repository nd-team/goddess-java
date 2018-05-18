package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.PositionDutyBO;
import com.bjike.goddess.recruit.dto.PositionDutyDTO;
import com.bjike.goddess.recruit.entity.PositionDuty;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.PositionDutyTO;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
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
 * 公司岗位分类岗位职责业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 09:31 ]
 * @Description: [ 公司岗位分类岗位职责业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class PositionDutySerImpl extends ServiceImpl<PositionDuty, PositionDutyDTO> implements PositionDutySer {
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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.busCusPermission("2",null);
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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.busCusPermission("2",null);
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
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
    public Long count(PositionDutyDTO dto) throws SerException {
        search(dto);
        Long count = super.count(dto);
        return count;
    }

    @Override
    public PositionDutyBO getId(String id) throws SerException {
        PositionDuty positionDuty = super.findById(id);
        PositionDutyBO bo = BeanTransform.copyProperties(positionDuty, PositionDutyBO.class);
        return bo;
    }

    @Override
    public List<PositionDutyBO> list(PositionDutyDTO dto) throws SerException {
        checkSeeIdentity();
        search(dto);
        List<PositionDuty> positionDuties = super.findByCis(dto);
        List<PositionDutyBO> positionDutyBOS = BeanTransform.copyProperties(positionDuties, PositionDutyBO.class);
        return positionDutyBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionDutyBO save(PositionDutyTO to) throws SerException {
        checkAddIdentity();
        PositionDuty positionDuty = BeanTransform.copyProperties(to, PositionDuty.class);
        positionDuty.setCreateTime(LocalDateTime.now());
        super.save(positionDuty);
        PositionDutyBO bo = BeanTransform.copyProperties(positionDuty, PositionDutyBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionDutyBO update(PositionDutyTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            PositionDuty positionDuty = super.findById(to.getId());
            LocalDateTime createTime = positionDuty.getCreateTime();
            positionDuty = BeanTransform.copyProperties(to, PositionDuty.class, true);
            positionDuty.setCreateTime(createTime);
            positionDuty.setModifyTime(LocalDateTime.now());
            PositionDutyBO bo = BeanTransform.copyProperties(positionDuty, PositionDutyBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thaw(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            PositionDuty positionDuty = super.findById(id);
            positionDuty.setStatus(Status.THAW);
            super.update(positionDuty);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congeal(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            PositionDuty positionDuty = super.findById(id);
            positionDuty.setStatus(Status.CONGEAL);
            super.update(positionDuty);
        } else {
            throw new SerException("id不能为空");
        }
    }

    private List<PositionDutyBO> search(PositionDutyDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getPosition())) {
            dto.getConditions().add(Restrict.like("position", dto.getPosition()));
        }
        List<PositionDuty> list = super.findByCis(dto);
        List<PositionDutyBO> positionDutyBOS = BeanTransform.copyProperties(list, PositionDutyBO.class);
        return positionDutyBOS;
    }
}