package com.bjike.goddess.staffmove.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmove.bo.StaffMoveDemandBO;
import com.bjike.goddess.staffmove.dto.StaffMoveDemandDTO;
import com.bjike.goddess.staffmove.entity.StaffMoveDemand;
import com.bjike.goddess.staffmove.entity.StaffMoveImplementA;
import com.bjike.goddess.staffmove.enums.GuideAddrStatus;
import com.bjike.goddess.staffmove.excel.SonPermissionObject;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveDemandTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 人员调动需求业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:03 ]
 * @Description: [ 人员调动需求业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmoveSerCache")
@Service
public class StaffMoveDemandSerImpl extends ServiceImpl<StaffMoveDemand, StaffMoveDemandDTO> implements StaffMoveDemandSer {
    @Autowired
    private StaffMoveImplementASer staffMoveImplementASer;
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
     * 导航栏核对添加修改删除权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("staffmove");
        obj.setDescribesion("人员调动");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        return list;
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
    public Long count(StaffMoveDemandDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public StaffMoveDemandBO getOne(String id) throws SerException {
        StaffMoveDemand staffMoveDemand = super.findById(id);
        StaffMoveDemandBO bo = BeanTransform.copyProperties(staffMoveDemand, StaffMoveDemandBO.class);
        return bo;
    }

    @Override
    public List<StaffMoveDemandBO> list(StaffMoveDemandDTO dto) throws SerException {
        checkSeeIdentity();
        List<StaffMoveDemand> staffMoveDemands = super.findByCis(dto);
        List<StaffMoveDemandBO> staffMoveDemandBOS = BeanTransform.copyProperties(staffMoveDemands, StaffMoveDemandBO.class);
        return staffMoveDemandBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StaffMoveDemandBO insert(StaffMoveDemandTO to) throws SerException {
        checkAddIdentity();
        UserBO userBO = userAPI.currentUser();
        StaffMoveDemand staffMoveDemand = BeanTransform.copyProperties(to, StaffMoveDemand.class, true);
        staffMoveDemand.setCreateTime(LocalDateTime.now());
        staffMoveDemand.setDemandPeople(userBO.getUsername());
        super.save(staffMoveDemand);
        StaffMoveImplementA staffMoveImplementA = new StaffMoveImplementA();
        BeanUtils.copyProperties(staffMoveDemand, staffMoveImplementA);
        staffMoveImplementASer.save(staffMoveImplementA);
        StaffMoveDemandBO bo = BeanTransform.copyProperties(staffMoveDemand, StaffMoveDemandBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StaffMoveDemandBO edit(StaffMoveDemandTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            UserBO userBO = userAPI.currentUser();
            StaffMoveDemand staffMoveDemand = super.findById(to.getId());
            LocalDateTime createTime = staffMoveDemand.getCreateTime();
            staffMoveDemand = BeanTransform.copyProperties(staffMoveDemand, StaffMoveDemand.class, true);
            staffMoveDemand.setDemandPeople(userBO.getUsername());
            staffMoveDemand.setCreateTime(createTime);
            staffMoveDemand.setModifyTime(LocalDateTime.now());
            super.update(staffMoveDemand);
            StaffMoveDemandBO bo = BeanTransform.copyProperties(staffMoveDemand, StaffMoveDemandBO.class);
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
}