package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.bo.PerBO;
import com.bjike.goddess.buyticket.bo.BuyTicketApplyBO;
import com.bjike.goddess.buyticket.dto.BuyTicketApplyDTO;
import com.bjike.goddess.buyticket.entity.BuyTicketApply;
import com.bjike.goddess.buyticket.entity.BuyTicketRecord;
import com.bjike.goddess.buyticket.enums.AuditType;
import com.bjike.goddess.buyticket.enums.GuideAddrStatus;
import com.bjike.goddess.buyticket.excel.SonPermissionObject;
import com.bjike.goddess.buyticket.to.BuyTicketApplyTO;
import com.bjike.goddess.buyticket.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 车票购买申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:32 ]
 * @Description: [ 车票购买申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class BuyTicketApplySerImpl extends ServiceImpl<BuyTicketApply, BuyTicketApplyDTO> implements BuyTicketApplySer {
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private StaffRecordsAPI staffRecordsAPI;
    @Autowired
    private BuyTicketRecordSer buyTicketRecordSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private BasicInfoSer basicInfoSer;
    @Autowired
    private BuyTicketCollectSer buyTicketCollectSer;
    @Autowired
    private BuyTicketStandardSer buyTicketStandardSer;
    @Autowired
    private TicketInfoRecordSer ticketInfoRecordSer;


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
     * 核对模块审核权限（模块级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {

        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagAppMod = guideMondIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidePosi = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAllTrue = guideAllTrueIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("buyTicketapply");
        obj.setDescribesion("车票购买申请");
        if (flagAppMod || flagGuidePosi || flagAllTrue) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagBasinfoMon = basicInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("basicinfo");
        obj.setDescribesion("基本信息设置");
        if (flagBasinfoMon) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidRecord = buyTicketRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("buyTicketrecord");
        obj.setDescribesion("车票购买记录");
        if (flagGuidRecord) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidStand = buyTicketStandardSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("buyTicketstandard");
        obj.setDescribesion("购票标准");
        if (flagGuidStand) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidTicket = ticketInfoRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("ticketinforecord");
        obj.setDescribesion("车票信息记录");
        if (flagGuidTicket) {
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
    public Long countBuyTicketApply(BuyTicketApplyDTO buyTicketApplyDTO) throws SerException {
        buyTicketApplyDTO.getSorts().add("createTime=desc");
        Long count = super.count(buyTicketApplyDTO);
        return count;
    }

    @Override
    public BuyTicketApplyBO getOne(String id) throws SerException {
        BuyTicketApply buyTicketApply = super.findById(id);
        return BeanTransform.copyProperties(buyTicketApply, BuyTicketApplyBO.class);
    }

    @Override
    public List<BuyTicketApplyBO> findListBuyTicketApply(BuyTicketApplyDTO buyTicketApplyDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flag = positCusPermission("list");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flag1 = checkAppSeePermission();
        RpcTransmit.transmitUserToken(userToken);
        String[] filed = {"id","applicant", "area", "department", "passenger", "passengerNum", "ticketCause", "ticketType", "buyPattern",
                "tripType", "origin", "destination", "planDepartureTime", "planArrivalTime", "remark", "planAuditor", "planAuditOpinion", "welfAuditor", "welfAuditOpinion"};
        String sql = "";
        if (flag || flag1) {
            sql = "SELECT " +
                    "  id                AS id,"+
                    "  applicant         AS applicant," +
                    "  area              AS area," +
                    "  department        AS department," +
                    "  passenger         AS passenger," +
                    "  passengerNum      AS passengerNum," +
                    "  ticketCause       AS ticketCause," +
                    "  ticketType        AS ticketType," +
                    "  buyPattern        AS buyPattern," +
                    "  tripType          AS tripType," +
                    "  origin            AS origin," +
                    "  destination       AS destination," +
                    "  planDepartureTime AS planDepartureTime," +
                    "  planArrivalTime   AS planArrivalTime," +
                    "  remark            AS remark," +
                    "  planAuditor       AS planAuditor," +
                    "  planAuditOpinion  AS planAuditOpinion," +
                    "  welfAuditor       AS welfAuditor," +
                    "  welfAuditOpinion  AS welfAuditOpinion" +
                    "  FROM buyticket_buyticketapply" +
                    "  WHERE id NOT IN (" +
                    "  SELECT id" +
                    "  FROM buyticket_buyticketapply" +
                    "  WHERE planAuditOpinion = 1 AND welfAuditOpinion = 1) limit " + buyTicketApplyDTO.getPage() + "," + buyTicketApplyDTO.getLimit();
        } else {
            UserBO user = userAPI.currentUser();
            sql = "SELECT " +
                    "  id                AS id,"+
                    "  applicant         AS applicant," +
                    "  area              AS area," +
                    "  department        AS department," +
                    "  passenger         AS passenger," +
                    "  passengerNum      AS passengerNum," +
                    "  ticketCause       AS ticketCause," +
                    "  ticketType        AS ticketType," +
                    "  buyPattern        AS buyPattern," +
                    "  tripType          AS tripType," +
                    "  origin            AS origin," +
                    "  destination       AS destination," +
                    "  planDepartureTime AS planDepartureTime," +
                    "  planArrivalTime   AS planArrivalTime," +
                    "  remark            AS remark," +
                    "  planAuditor       AS planAuditor," +
                    "  planAuditOpinion  AS planAuditOpinion," +
                    "  welfAuditor       AS welfAuditor," +
                    "  welfAuditOpinion  AS welfAuditOpinion" +
                    "  FROM buyticket_buyticketapply" +
                    "  WHERE id NOT IN (" +
                    "  SELECT id" +
                    "  FROM buyticket_buyticketapply" +
                    "  WHERE planAuditOpinion = 1 AND welfAuditOpinion = 1) where applicant =" + user.getUsername() + " limit " + buyTicketApplyDTO.getPage() + "," + buyTicketApplyDTO.getLimit();
        }
        List<BuyTicketApplyBO> buyTicketApplyBOS = super.findBySql(sql, BuyTicketApplyBO.class, filed);
        return buyTicketApplyBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BuyTicketApplyBO insertBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        BuyTicketApply buyTicketApply = BeanTransform.copyProperties(buyTicketApplyTO, BuyTicketApply.class, true, "passenger");
        buyTicketApply.setPassenger(StringUtils.join(buyTicketApplyTO.getPassenger(), ","));
        buyTicketApply.setCreateTime(LocalDateTime.now());
        super.save(buyTicketApply);
        if (buyTicketApplyTO.getSendEmail() && buyTicketApplyTO.getSendObject() != null && buyTicketApplyTO.getSendObject().length > 0) {
            String passName = buyTicketApply.getPassenger();
            String ticketCause = buyTicketApply.getTicketCause();
            String origin = buyTicketApply.getOrigin();
            String destination = buyTicketApply.getDestination();
            String planDepartureTime = buyTicketApply.getPlanDepartureTime().toString();
            //校验邮箱书写
            String[] sendObject = buyTicketApplyTO.getSendObject();
            if (sendObject != null && sendObject.length > 0) {
                for (String emailStr : sendObject) {
                    if (!Validator.isEmail(emailStr)) {
                        throw new SerException("邮箱书写不正确");
                    }
                }
            }
            StringBuffer content = new StringBuffer();
            content.append("福利模块、规划模块负责人:你们好! " + passName + " 因" + ticketCause + "从" + origin + "前往" + destination + "," + planDepartureTime + "需要出发,请尽快审核跟进处理");

            MessageTO messageTO = new MessageTO();
            messageTO.setContent(content.toString());
            messageTO.setTitle("审核提醒");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);


            messageTO.setReceivers(buyTicketApplyTO.getSendObject());
            messageAPI.send(messageTO);
        }

        return BeanTransform.copyProperties(buyTicketApply, BuyTicketApplyBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BuyTicketApplyBO editBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        BuyTicketApply buyTicketApply = super.findById(buyTicketApplyTO.getId());
        BeanTransform.copyProperties(buyTicketApplyTO, buyTicketApply, true, "passenger");
        buyTicketApply.setPassenger(StringUtils.join(buyTicketApplyTO.getPassenger(), ","));
        buyTicketApply.setModifyTime(LocalDateTime.now());
        super.update(buyTicketApply);
        return BeanTransform.copyProperties(buyTicketApply, BuyTicketApplyBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeBuyTicketApply(String id) throws SerException {
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void planAuditBuyTicketApply(String id, AuditType planAuditOpinion) throws SerException {
        guidePosinIdentity();
        BuyTicketApply buyTicketApply = super.findById(id);
        if(buyTicketApply.getPlanAuditOpinion()!=null){
            throw new SerException("您已经审核过了,就不能再审核了");
        }
        buyTicketApply.setPlanAuditOpinion(planAuditOpinion);
        buyTicketApply.setModifyTime(LocalDateTime.now());
        super.update(buyTicketApply);
        BuyTicketApplyBO buyTicketApplyBO = BeanTransform.copyProperties(buyTicketApply, BuyTicketApplyBO.class);
        String[] passenger = buyTicketApply.getPassenger().split(",");
        if (buyTicketApply.getPlanAuditOpinion().equals(AuditType.ALLOWED) && buyTicketApply.getWelfAuditOpinion()!=null && buyTicketApply.getWelfAuditOpinion().equals(AuditType.ALLOWED)) {
            for (String pass : passenger) {
                String position = positionDetailUserAPI.getPosition(pass);
                List<PerBO> perBOS = staffRecordsAPI.getPerBO(pass);
                PerBO perBO = new PerBO();
                if (perBOS != null && perBOS.size() > 0) {
                    perBO = perBOS.get(0);
                }
                BuyTicketRecord buyTicketRecord = BeanTransform.copyProperties(buyTicketApplyBO, BuyTicketRecord.class, true, "passenger");
                buyTicketRecord.setPassenger(pass);
                buyTicketRecord.setPassengerPostName(position);
                buyTicketRecord.setIdCardNumber(perBO.getPerid());
                buyTicketRecord.setRelationTel(perBO.getPhone());
                buyTicketRecord.setCreateTime(LocalDateTime.now());
                buyTicketRecordSer.save(buyTicketRecord);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void welfAuditBuyTicketApply(String id, AuditType welfAuditOpinion) throws SerException {
        guidePosinIdentity();
        BuyTicketApply buyTicketApply = super.findById(id);
        if(buyTicketApply.getWelfAuditOpinion()!=null){
            throw new SerException("您已经审核过了,就不能再审核了");
        }
        buyTicketApply.setWelfAuditOpinion(welfAuditOpinion);
        buyTicketApply.setModifyTime(LocalDateTime.now());
        super.update(buyTicketApply);
        BuyTicketApplyBO buyTicketApplyBO = BeanTransform.copyProperties(buyTicketApply, BuyTicketApplyBO.class);
        String[] passenger = buyTicketApply.getPassenger().split(",");
        if (buyTicketApply.getPlanAuditOpinion()!=null && buyTicketApply.getPlanAuditOpinion().equals(AuditType.ALLOWED) && buyTicketApply.getWelfAuditOpinion().equals(AuditType.ALLOWED)) {
            for (String pass : passenger) {
                String position = positionDetailUserAPI.getPosition(pass);
                List<PerBO> perBOS = staffRecordsAPI.getPerBO(pass);
                PerBO perBO = new PerBO();
                if (perBOS != null && perBOS.size() > 0) {
                    perBO = perBOS.get(0);
                }
                BuyTicketRecord buyTicketRecord = BeanTransform.copyProperties(buyTicketApplyBO, BuyTicketRecord.class, true, "passenger");
                buyTicketRecord.setPassenger(pass);
                buyTicketRecord.setPassengerPostName(position);
                buyTicketRecord.setIdCardNumber(perBO.getPerid());
                buyTicketRecord.setRelationTel(perBO.getPhone());
                buyTicketRecord.setCreateTime(LocalDateTime.now());
                buyTicketRecordSer.save(buyTicketRecord);
            }
        }
    }


    @Override
    public List<String> findAddAllDetails() throws SerException {
        List<DepartmentDetailBO> departmentDetailBOS = departmentDetailAPI.findStatus();
        if (CollectionUtils.isEmpty(departmentDetailBOS)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DepartmentDetailBO departmentDetailBO : departmentDetailBOS) {
            String details = departmentDetailBO.getDepartment();
            if (StringUtils.isNotBlank(departmentDetailBO.getDepartment())) {
                set.add(details);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findallMonUser() throws SerException {
        List<UserBO> userBOS = positionDetailUserAPI.findUserList();
        if (CollectionUtils.isEmpty(userBOS)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (UserBO userBO : userBOS) {
            String userName = userBO.getUsername();
            if (StringUtils.isNotBlank(userBO.getUsername())) {
                set.add(userName);
            }
        }
        return new ArrayList<>(set);
    }
}