package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.CommunicationTempleBO;
import com.bjike.goddess.projectprocing.dto.CommunicationTempleDTO;
import com.bjike.goddess.projectprocing.entity.CommunicationTemple;
import com.bjike.goddess.projectprocing.enums.GuideAddrStatus;
import com.bjike.goddess.projectprocing.to.CommunicationTempleTO;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 各类沟通交流模板业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-25 05:41 ]
 * @Description: [ 各类沟通交流模板业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class CommunicationTempleSerImpl extends ServiceImpl<CommunicationTemple, CommunicationTempleDTO> implements CommunicationTempleSer {


    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public Long countCommuni(CommunicationTempleDTO communicationTempleDTO) throws SerException {
        Long count = super.count(communicationTempleDTO);
        return count;
    }

    @Override
    public CommunicationTempleBO getOneById(String id) throws SerException {
        CommunicationTemple communicationTemple = super.findById(id);
        return BeanTransform.copyProperties(communicationTemple, CommunicationTempleBO.class);
    }

    @Override
    public List<CommunicationTempleBO> listCommuni(CommunicationTempleDTO communicationTempleDTO) throws SerException {
        checkPermission();
        List<CommunicationTemple> communicationTempleList = super.findByCis(communicationTempleDTO, true);
        return BeanTransform.copyProperties(communicationTempleList, CommunicationTempleBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommunicationTempleBO addCommuni(CommunicationTempleTO communicationTempleTO) throws SerException {
        checkPermission();
        CommunicationTemple communicationTemple = BeanTransform.copyProperties(communicationTempleTO, CommunicationTemple.class, true);
        communicationTemple.setCreateTime(LocalDateTime.now());
        super.save(communicationTemple);
        return BeanTransform.copyProperties(communicationTemple, CommunicationTempleBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommunicationTempleBO editCommuni(CommunicationTempleTO communicationTempleTO) throws SerException {
        checkPermission();
        CommunicationTemple communicationTemple = super.findById(communicationTempleTO.getId());
        LocalDateTime dateTime = communicationTemple.getCreateTime();
        communicationTemple = BeanTransform.copyProperties(communicationTempleTO, CommunicationTemple.class, true);
        communicationTemple.setCreateTime(dateTime);
        communicationTemple.setModifyTime(LocalDateTime.now());
        super.update(communicationTemple);
        return BeanTransform.copyProperties(communicationTemple, CommunicationTempleBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteNode(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }
}