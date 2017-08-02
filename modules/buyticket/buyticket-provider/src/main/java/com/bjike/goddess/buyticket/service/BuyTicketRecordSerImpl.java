package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.BuyTicketRecordBO;
import com.bjike.goddess.buyticket.dto.BuyTicketRecordDTO;
import com.bjike.goddess.buyticket.entity.BuyTicketRecord;
import com.bjike.goddess.buyticket.enums.GuideAddrStatus;
import com.bjike.goddess.buyticket.to.BuyTicketRecordTO;
import com.bjike.goddess.buyticket.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 车票购买记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:33 ]
 * @Description: [ 车票购买记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class BuyTicketRecordSerImpl extends ServiceImpl<BuyTicketRecord, BuyTicketRecordDTO> implements BuyTicketRecordSer {

    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private MessageAPI messageAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;
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

    /**
     * 权限
     */
    private Boolean guideAllTrueIdentity() throws SerException {

        return true;
    }
    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagGuideMod = guideMondIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidePosi = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAllTrue = guideAllTrueIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagGuideMod || flagGuidePosi || flagAllTrue) {
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
    public Long countBuyTicketRecord(BuyTicketRecordDTO buyTicketRecordDTO) throws SerException {
        buyTicketRecordDTO.getSorts().add("createTime=desc");
        Long count = super.count(buyTicketRecordDTO);
        return count;
    }
    @Override
    public BuyTicketRecordBO getOne(String id) throws SerException {
        BuyTicketRecord buyTicketRecord = super.findById(id);
        return BeanTransform.copyProperties(buyTicketRecord,BuyTicketRecordBO.class,true);
    }


    @Override
    public List<BuyTicketRecordBO> findListBuyTicketRecord(BuyTicketRecordDTO buyTicketRecordDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flag = positCusPermission( "list");
        RpcTransmit.transmitUserToken( userToken );
        Boolean flag1 = checkAppSeePermission();
        RpcTransmit.transmitUserToken( userToken );
        if( flag || flag1 ){
            //可以查看所有数据
            buyTicketRecordDTO.getSorts().add("createTime=desc");
        }else{
            //查看自己的
            UserBO userBO = userAPI.currentUser();
            String userName = userBO.getUsername();
            buyTicketRecordDTO.getConditions().add(Restrict.eq("passenger",userName));
        }
        List<BuyTicketRecord> basicInfos = super.findByCis(buyTicketRecordDTO, true);
        List<BuyTicketRecordBO> buyTicketRecordBOS = BeanTransform.copyProperties(basicInfos, BuyTicketRecordBO.class, true);
        return buyTicketRecordBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BuyTicketRecordBO editBuyTicketRecord(BuyTicketRecordTO buyTicketRecordTO) throws SerException {
        guideMondIdentity();
        BuyTicketRecord buyTicketRecord = super.findById(buyTicketRecordTO.getId());
        BeanTransform.copyProperties(buyTicketRecordTO,buyTicketRecord,true);
        buyTicketRecord.setModifyTime(LocalDateTime.now());
        super.update(buyTicketRecord);
        String passName = buyTicketRecord.getPassenger();
        String ticketCause = buyTicketRecord.getTicketCause();//购票原因
        String origin = buyTicketRecord.getOrigin();//出发地
        String destination = buyTicketRecord.getDestination();//目的地
        String departureTime = buyTicketRecord.getDepartureTime().toString();//出发时间
        String arrivalTime = buyTicketRecord.getArrivalTime().toString();//到达时间
        String shift = buyTicketRecord.getArrivalTime().toString();//班次/航班
        String seatInfo = buyTicketRecord.getArrivalTime().toString();//座位信息
        String orderNum = buyTicketRecord.getArrivalTime().toString();//订单号

        StringBuffer content = new StringBuffer();
        content.append("您好!您因 "+ticketCause+" 需要从"+origin+"前往"+destination+"以下为乘车信息:  "+shift+" ,"+departureTime+"-"+arrivalTime+" ");
        content.append(seatInfo+"  "+orderNum+"。请按时带上身份证/护照乘车。请悉知和确认乘车信息,谢谢合作!(邮件和系统发送,系统看消息时有个确认键,如有疑问选择不确认,并填写");
        content.append("问题,点击确认键后,记录列表中乘车人是否有确认乘车信息显示是,确认信息时间-自动生成;如不确认的,系统通知福利模块进行处理,可以到记录查看确认问题,并按要求做修改后,系统自动确认)");
        MessageTO messageTO = new MessageTO();
        messageTO.setContent(content.toString());
        messageTO.setTitle("购票成功提醒");
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setSendType(SendType.EMAIL);
        messageTO.setRangeType(RangeType.SPECIFIED);

        String email = internalContactsAPI.getEmail(passName);


        messageTO.setReceivers(new String[]{email});
        messageAPI.send(messageTO);
        return BeanTransform.copyProperties(buyTicketRecord,BuyTicketRecordBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeBuyTicketRecord(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flag = positCusPermission("delete");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flag1 = checkAppSeePermission();
        RpcTransmit.transmitUserToken(userToken);
        if(flag || flag1){
            super.remove(id);
        }else{
            throw new SerException("您不是相关人员,没有该操作权限");
        }
    }

}