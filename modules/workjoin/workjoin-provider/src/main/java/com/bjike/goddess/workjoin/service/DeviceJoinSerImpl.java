package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.workjoin.bo.DeviceJoinBO;
import com.bjike.goddess.workjoin.dto.DeviceJoinDTO;
import com.bjike.goddess.workjoin.entity.DeviceJoin;
import com.bjike.goddess.workjoin.enums.GuideAddrStatus;
import com.bjike.goddess.workjoin.to.DeviceJoinTO;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备交接业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:57 ]
 * @Description: [ 设备交接业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class DeviceJoinSerImpl extends ServiceImpl<DeviceJoin, DeviceJoinDTO> implements DeviceJoinSer {

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
    public Long countDeviceJoin(DeviceJoinDTO deviceJoinDTO) throws SerException {
        Long count = super.count(deviceJoinDTO);
        return count;
    }

    @Override
    public DeviceJoinBO getOne(String id) throws SerException {
        DeviceJoin deviceJoin = super.findById(id);
        return BeanTransform.copyProperties(deviceJoin,DeviceJoinBO.class);
    }

    @Override
    public List<DeviceJoinBO> findListDeviceJoin(DeviceJoinDTO deviceJoinDTO) throws SerException {
        checkSeeIdentity();
        deviceJoinDTO.getSorts().add("createTime=desc");
        List<DeviceJoin> deviceJoins = super.findByPage(deviceJoinDTO);
        List<DeviceJoinBO> deviceJoinBOS = BeanTransform.copyProperties(deviceJoins,DeviceJoinBO.class);
        return deviceJoinBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public DeviceJoinBO insertDeviceJoin(DeviceJoinTO deviceJoinTO) throws SerException {
        checkAddIdentity();
        DeviceJoin deviceJoin = BeanTransform.copyProperties(deviceJoinTO,DeviceJoin.class,true);
        deviceJoin.setCreateTime(LocalDateTime.now());
        super.save(deviceJoin);
        return BeanTransform.copyProperties(deviceJoin,DeviceJoinBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public DeviceJoinBO editDeviceJoin(DeviceJoinTO deviceJoinTO) throws SerException {
        checkAddIdentity();
        DeviceJoin deviceJoin = super.findById(deviceJoinTO.getId());
        BeanTransform.copyProperties(deviceJoinTO,deviceJoin,true);
        deviceJoin.setModifyTime(LocalDateTime.now());
        super.update(deviceJoin);
        return BeanTransform.copyProperties(deviceJoin,DeviceJoinBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeDeviceJoin(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);

    }

}