package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.SettleProgressRecordBO;
import com.bjike.goddess.projectprocing.dto.SettleProgressRecordDTO;
import com.bjike.goddess.projectprocing.entity.SettleProgressRecord;
import com.bjike.goddess.projectprocing.enums.GuideAddrStatus;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.SettleProgressRecordTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 结算进度调整记录&结算问题汇总业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 结算进度调整记录&结算问题汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class SettleProgressRecordSerImpl extends ServiceImpl<SettleProgressRecord, SettleProgressRecordDTO> implements SettleProgressRecordSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
     * 资金模块意见(模块)
     *
     * @throws SerException
     */
    private void checkMoneyPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busModulPermission("8");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是资金模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 总经理审批(岗位)
     *
     * @throws SerException
     */
    private void checkPositionPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("9");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是该职位人员,没有该操作权限");
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

    /**
     * 资金模块意见(模块)
     */
    private Boolean guideModuleIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busModulPermission("8");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 总经理审批(岗位)
     */
    private Boolean guidePositionIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("9");
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
        Boolean flagModule = guideModuleIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPosition = guidePositionIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagModule || flagPosition) {
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
            case CAPITALOPINION:
                flag = guideModuleIdentity();
                break;
            case GENERALAPPROVAL:
                flag = guidePositionIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countManage(SettleProgressRecordDTO settleProgressRecordDTO) throws SerException {
        Long count = super.count(settleProgressRecordDTO);
        return count;
    }

    @Override
    public SettleProgressRecordBO getOneById(String id) throws SerException {
        SettleProgressRecord settleProgressRecord = super.findById(id);
        return BeanTransform.copyProperties(settleProgressRecord, SettleProgressRecordBO.class);
    }

    @Override
    public List<SettleProgressRecordBO> listManage(SettleProgressRecordDTO settleProgressRecordDTO) throws SerException {
        checkPermission();
        List<SettleProgressRecord> settleProgressRecordList = super.findByCis(settleProgressRecordDTO, true);
        return BeanTransform.copyProperties(settleProgressRecordList, SettleProgressRecordBO.class);
    }

    @Override
    public SettleProgressRecordBO addManage(SettleProgressRecordTO settleProgressRecordTO) throws SerException {
        checkPermission();
        SettleProgressRecord settleProgressRecord = BeanTransform.copyProperties(settleProgressRecordTO, SettleProgressRecord.class, true);
        settleProgressRecord.setCreateTime(LocalDateTime.now());
        super.save(settleProgressRecord);
        return BeanTransform.copyProperties(settleProgressRecord, SettleProgressRecordBO.class);
    }

    @Override
    public SettleProgressRecordBO editManage(SettleProgressRecordTO settleProgressRecordTO) throws SerException {
        checkPermission();
        SettleProgressRecord settleProgressRecord = super.findById(settleProgressRecordTO.getId());
        LocalDateTime dateTime = settleProgressRecord.getCreateTime();
        settleProgressRecord = BeanTransform.copyProperties(settleProgressRecordTO, SettleProgressRecord.class, true);
        settleProgressRecord.setCreateTime(dateTime);
        settleProgressRecord.setModifyTime(LocalDateTime.now());
        super.update(settleProgressRecord);
        return BeanTransform.copyProperties(settleProgressRecord, SettleProgressRecordBO.class);
    }

    @Override
    public void deleteManage(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    @Override
    public void checkAnalysis(String id, String moneyModule, String moneyModuleOpinion) throws SerException {
        checkMoneyPermission();
        SettleProgressRecord settleProgressRecord = super.findById(id);
        settleProgressRecord.setMoneyModule(moneyModule);
        settleProgressRecord.setMoneyModuleOpinion(moneyModuleOpinion);
        settleProgressRecord.setModifyTime(LocalDateTime.now());
        super.update(settleProgressRecord);
    }

    @Override
    public void confirm(String id, String generalManager, String approvalExam, Boolean confirm) throws SerException {
        checkPositionPermission();
        SettleProgressRecord settleProgressRecord = super.findById(id);
        settleProgressRecord.setGeneralManager(generalManager);
        settleProgressRecord.setApprovalExam(approvalExam);
        settleProgressRecord.setConfirm(confirm);
        settleProgressRecord.setModifyTime(LocalDateTime.now());
        super.update(settleProgressRecord);
    }
}