package com.bjike.goddess.staffentry.service;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.CommunicationFormworkBO;
import com.bjike.goddess.staffentry.dto.CommunicationFormworkDTO;
import com.bjike.goddess.staffentry.entity.CommunicationFormwork;
import com.bjike.goddess.staffentry.enums.GuideAddrStatus;
import com.bjike.goddess.staffentry.to.CommunicationFormworkTO;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 各类交流沟通模块业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-25 09:48 ]
 * @Description: [ 各类交流沟通模块业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffentrySerCache")
@Service
public class CommunicationFormworkSerImpl extends ServiceImpl<CommunicationFormwork, CommunicationFormworkDTO> implements CommunicationFormworkSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检测部门
     *
     * @param idFlag
     * @throws SerException
     */
    private Boolean checkDepartIdentity(String idFlag) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        Boolean flag = true;
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(idFlag);
//            if( !flag){
//                throw new SerException("你不是相应部门的人员，不能进行操作");
//            }
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = checkDepartIdentity("3");
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
                flag = checkDepartIdentity("3");
                break;
            case ADD:
                flag = checkDepartIdentity("3");
                break;
            case EDIT:
                flag = checkDepartIdentity("3");
                break;
            case DELETE:
                flag = checkDepartIdentity("3");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countComm(CommunicationFormworkDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public CommunicationFormworkBO getOne(String id) throws SerException {
        CommunicationFormwork com = super.findById(id);
        return BeanTransform.copyProperties(com, CommunicationFormworkBO.class);
    }

    @Override
    public List<CommunicationFormworkBO> list(CommunicationFormworkDTO dto) throws SerException {
        List<CommunicationFormwork> communicationFormworks = super.findByCis(dto);
        return BeanTransform.copyProperties(communicationFormworks, CommunicationFormworkBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommunicationFormworkBO save(CommunicationFormworkTO to) throws SerException {
//        checkPermission();
        CommunicationFormwork comm = BeanTransform.copyProperties(to, CommunicationFormwork.class, true);
        comm.setCreateTime(LocalDateTime.now());
        super.save(comm);
        return BeanTransform.copyProperties(comm, CommunicationFormworkBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(CommunicationFormworkTO to) throws SerException {
//        checkPermission();
        CommunicationFormwork comm = super.findById(to.getId());
        LocalDateTime date = comm.getCreateTime();
        comm = BeanTransform.copyProperties(to, CommunicationFormwork.class, true);
        comm.setModifyTime(LocalDateTime.now());
        comm.setCreateTime(date);
        super.update(comm);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
//        checkPermission();
        super.remove(id);
    }
}