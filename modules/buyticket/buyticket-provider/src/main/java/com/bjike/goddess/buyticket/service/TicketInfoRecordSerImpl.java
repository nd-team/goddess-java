package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.TicketInfoRecordBO;
import com.bjike.goddess.buyticket.dto.TicketInfoRecordDTO;
import com.bjike.goddess.buyticket.entity.TicketInfoRecord;
import com.bjike.goddess.buyticket.enums.GuideAddrStatus;
import com.bjike.goddess.buyticket.to.BuyGuidePermissionTO;
import com.bjike.goddess.buyticket.to.TicketInfoRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 车票信息记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:48 ]
 * @Description: [ 车票信息记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class TicketInfoRecordSerImpl extends ServiceImpl<TicketInfoRecord, TicketInfoRecordDTO> implements TicketInfoRecordSer {

    @Autowired
    private BuyCusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    /**
     * 设置权限表中岗位权限
     *
     * @throws SerException
     */
    private Boolean positCusPermission(String idFlag) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission(idFlag);
        } else {
            flag = true;
        }
        RpcTransmit.transmitUserToken(userToken);
        return flag;

    }

    /**
     * 检查权限(模块)
     *
     * @throws SerException
     */
    private void checkModPermission() throws SerException {
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
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查申请查看权限(模块)
     *
     * @throws SerException
     */
    private Boolean checkAppSeePermission() throws SerException {
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
        RpcTransmit.transmitUserToken(userToken);

        return flag;
    }

    /**
     * 检查权限(岗位)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对模块权限（模块级别）
     */
    private Boolean guideMondIdentity() throws SerException {
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
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagGuideMod = guideMondIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidePosi = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagGuideMod || flagGuidePosi) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 权限
     */
    private Boolean guideAllTrueIdentity() throws SerException {

        return true;
    }
    @Override
    public Boolean guidePermission(BuyGuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case APPLIST:
                flag = guideAllTrueIdentity();
                break;
            case APPADD:
                flag = guideAllTrueIdentity();
                break;
            case APPEDIT:
                flag = guideAllTrueIdentity();
                break;
            case LIST:
                flag = guideMondIdentity();
                break;
            case ADD:
                flag = guideMondIdentity();
                break;
            case EDIT:
                flag = guideMondIdentity();
                break;
            case DELETE:
                flag = guideMondIdentity();
                break;
            case PLANAUDIT:
                flag = guideMondIdentity();
                break;
            case WELFAUDIT:
                flag = guideMondIdentity();
                break;
            case CONGEL:
                flag = guideMondIdentity();
                break;
            case THAW:
                flag = guideMondIdentity();
                break;
            case RECORDLIST:
                flag = guideAllTrueIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countTicketInfoRecord(TicketInfoRecordDTO ticketInfoRecordDTO) throws SerException {
        ticketInfoRecordDTO.getSorts().add("createTime=desc");
        Long count = super.count(ticketInfoRecordDTO);
        return count;
    }
    @Override
    public TicketInfoRecordBO getOne(String id) throws SerException {
        TicketInfoRecord ticketInfoRecord = super.findById(id);
        return BeanTransform.copyProperties(ticketInfoRecord,TicketInfoRecordBO.class,true);
    }


    @Override
    public List<TicketInfoRecordBO> findListTicketInfoRecord(TicketInfoRecordDTO ticketInfoRecordDTO) throws SerException {
        checkModPermission();
        List<TicketInfoRecord> ticketInfoRecords = super.findByCis(ticketInfoRecordDTO,true);
        List<TicketInfoRecordBO> ticketInfoRecordBOS = BeanTransform.copyProperties(ticketInfoRecords,TicketInfoRecordBO.class,true);
        return ticketInfoRecordBOS;
    }

    @Override
    public TicketInfoRecordBO insertTicketInfoRecord(TicketInfoRecordTO ticketInfoRecordTO) throws SerException {
        checkModPermission();
        TicketInfoRecord ticketInfoRecord = BeanTransform.copyProperties(ticketInfoRecordTO,TicketInfoRecord.class,true);
        ticketInfoRecord.setCreateTime(LocalDateTime.now());
        super.save(ticketInfoRecord);
        return BeanTransform.copyProperties(ticketInfoRecord,TicketInfoRecordBO.class);
    }

    @Override
    public TicketInfoRecordBO editTicketInfoRecord(TicketInfoRecordTO ticketInfoRecordTO) throws SerException {
        checkModPermission();
        TicketInfoRecord ticketInfoRecord = super.findById(ticketInfoRecordTO.getId());
        BeanTransform.copyProperties(ticketInfoRecordTO,ticketInfoRecord,true);
        ticketInfoRecord.setModifyTime(LocalDateTime.now());
        super.update(ticketInfoRecord);
        return BeanTransform.copyProperties(ticketInfoRecord,TicketInfoRecordBO.class);
    }

    @Override
    public void removeTicketInfoRecord(String id) throws SerException {
        checkModPermission();
        super.remove(id);
    }
    @Override
    public void congealTicketInfoRecord(String id) throws SerException{
        checkModPermission();
        try {
            TicketInfoRecord ticketInfoRecord = super.findById(id);
            ticketInfoRecord.setModifyTime(LocalDateTime.now());
            ticketInfoRecord.setStatus(Status.CONGEAL);

            super.update(ticketInfoRecord);
        } catch (SerException e) {
            throw new SerException("冻结出现错误，冻结失败"+e.getMessage());
        }
    }

    @Override
    public void thawTicketInfoRecord(String id) throws SerException{
        checkModPermission();
        try {
            TicketInfoRecord ticketInfoRecord = super.findById(id);
            ticketInfoRecord.setModifyTime(LocalDateTime.now());
            ticketInfoRecord.setStatus(Status.THAW);

            super.update(ticketInfoRecord);
        } catch (SerException e) {
            throw new SerException("解冻出现错误，解冻失败"+e.getMessage());
        }
    }
}