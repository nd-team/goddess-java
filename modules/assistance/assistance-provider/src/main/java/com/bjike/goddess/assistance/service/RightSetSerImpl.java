package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.RightSetBO;
import com.bjike.goddess.assistance.dto.RightSetDTO;
import com.bjike.goddess.assistance.entity.RightSet;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.RightSetTO;
import com.bjike.goddess.common.api.dto.Restrict;
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
 * 权限设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:14 ]
 * @Description: [ 权限设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class RightSetSerImpl extends ServiceImpl<RightSet, RightSetDTO> implements RightSetSer {
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
            flag = cusPermissionSer.getCusPermission("planAuditPermission");
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
            flag = cusPermissionSer.busCusPermission("planAuditPermission");
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
            flag = cusPermissionSer.getCusPermission("planAuditPermission");
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
            flag = cusPermissionSer.busCusPermission("planAuditPermission");
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
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public RightSetBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        RightSetBO rightSetBO = BeanTransform.copyProperties(super.findById( id ),RightSetBO.class);
        return null;
    }

    @Override
    public Long countRightSet(RightSetDTO rightSetDTO) throws SerException {
        if (StringUtils.isNotBlank(rightSetDTO.getEmpName())) {
            rightSetDTO.getConditions().add(Restrict.like("empName", rightSetDTO.getEmpName()));
        }
        rightSetDTO.getSorts().add("empRight=desc");
        Long count = super.count(rightSetDTO);
        return count;
    }

    @Override
    public List<RightSetBO> listRightSet(RightSetDTO rightSetDTO) throws SerException {
        checkSeeIdentity();
        if (StringUtils.isNotBlank(rightSetDTO.getEmpName())) {
            rightSetDTO.getConditions().add(Restrict.like("empName", rightSetDTO.getEmpName()));
        }
        rightSetDTO.getSorts().add("empRight=desc");
        List<RightSet> list = super.findByCis(rightSetDTO, true);

        return BeanTransform.copyProperties(list, RightSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RightSetBO addRightSet(RightSetTO rightSetTO) throws SerException {
        checkAddIdentity();
        RightSet rightSet = BeanTransform.copyProperties(rightSetTO, RightSet.class, true);
        rightSet.setCreateTime(LocalDateTime.now());
        super.save(rightSet);
        return BeanTransform.copyProperties(rightSet, RightSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RightSetBO editRightSet(RightSetTO rightSetTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank( rightSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        RightSet rightSet = BeanTransform.copyProperties(rightSetTO, RightSet.class, true);
        RightSet rs = super.findById(rightSetTO.getId());

        rs.setEmpName(rightSet.getEmpName());
        rs.setRemark(rightSet.getRemark());
        rs.setEmpRight(rightSet.getEmpRight());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, RightSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteRightSet(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

}