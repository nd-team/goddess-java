package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.bo.InternalContactsBO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.supplier.bo.CollectSendBO;
import com.bjike.goddess.supplier.bo.SupplierInfoCollectBO;
import com.bjike.goddess.supplier.bo.SupplierInfoCollectTitleBO;
import com.bjike.goddess.supplier.dto.CollectSendDTO;
import com.bjike.goddess.supplier.entity.CollectSend;
import com.bjike.goddess.supplier.enums.GuideAddrStatus;
import com.bjike.goddess.supplier.to.CollectSendTO;
import com.bjike.goddess.supplier.to.CollectTo;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 供应商汇总业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-14 11:48 ]
 * @Description: [ 供应商汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class CollectSendSerImpl extends ServiceImpl<CollectSend, CollectSendDTO> implements CollectSendSer {

    @Autowired
    private SupplierInformationSer supplierInformationSer;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private SupCusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

//    private static final String idFlag = "supplier-01";

    private static final String tableF = "<table cellspacing='0px' style='width:80%;border:2px #808080 outset;border-collapse:collapse;border-spacing:0px;'>";
    private static final String tableL = "</table>";
    private static final String trF = "<tr>";
    private static final String trL = "</tr>";
    private static final String tdF = "<td style='border:2px #808080 inset;padding:3px;'>";
    private static final String tdL = "</td>";

    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public void sendEmail() throws SerException {
        CollectSendDTO dto = new CollectSendDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<CollectSend> list = super.findByCis(dto), editList = new ArrayList<>(0);
        LocalDateTime now = LocalDateTime.now();
        for (CollectSend entity : list) {
            if (!this.getNextTime(entity).isAfter(now)) {
                entity.setLastTime(now);
                entity.setModifyTime(now);
                this.sendEmail();
                editList.add(entity);
            }
        }
        if (editList.size() != 0)
            super.update(editList);
    }

    /**
     * 发送邮件
     *
     * @param entity 供应商汇总数据
     * @throws SerException
     */
    private void sendEmail(CollectSend entity) throws SerException {
        CollectTo collectTo = new CollectTo();
        collectTo.setStart(this.getCollectTime(entity).toString());
        collectTo.setEnd(entity.getLastTime().toString());
        collectTo.setArea(entity.getArea().split(","));
        MessageTO message = new MessageTO(entity.getDescription(), this.collect(collectTo));
        message.setMsgType(MsgType.SYS);
        message.setRangeType(RangeType.SPECIFIED);
        message.setSendType(SendType.EMAIL);
        message.setReceivers(this.getEmail(entity));
        messageAPI.send(message);
    }

    /**
     * 获取需要发送的邮箱
     *
     * @param entity 供应商汇总数据
     * @return
     * @throws SerException
     */
    private String[] getEmail(CollectSend entity) throws SerException {
        if (entity.getAll()) {
            List<String> emails = new ArrayList<>(0);
            emails.addAll(internalContactsAPI.findEmailNotNull().stream()
                    .map(InternalContactsBO::getWorkEmail)
                    .collect(Collectors.toList()));
            emails.addAll(commonalityAPI.findThaw().stream()
                    .map(CommonalityBO::getEmail)
                    .collect(Collectors.toList()));
            return emails.toArray(new String[0]);
        } else {
            return entity.getEmail().split(",");
        }
    }

    /**
     * 获取下次发送时间
     *
     * @param entity 供应商汇总数据
     * @return
     * @throws SerException
     */
    private LocalDateTime getCollectTime(CollectSend entity) throws SerException {
        LocalDateTime collectTime = entity.getLastTime();
        switch (entity.getTimeInterval()) {
            case MINUTE:
                collectTime = collectTime.minusMinutes(entity.getInterval());
                break;
            case HOUR:
                collectTime = collectTime.minusHours(entity.getInterval());
                break;
            case DAY:
                collectTime = collectTime.minusDays(entity.getInterval());
                break;
            case WEEK:
                collectTime = collectTime.minusWeeks(entity.getInterval());
                break;
            case MONTH:
                collectTime = collectTime.minusMonths(entity.getInterval());
                break;
            case QUARTER:
                collectTime = collectTime.minusMonths(entity.getInterval() * 3);
                break;
            default:
                collectTime = collectTime.minusYears(entity.getInterval());
                break;
        }
        return collectTime;
    }

    /**
     * 获取下次发送时间
     *
     * @param entity 供应商汇总数据
     * @return
     * @throws SerException
     */
    private LocalDateTime getNextTime(CollectSend entity) throws SerException {
        LocalDateTime nextTime = entity.getLastTime();
        switch (entity.getTimeInterval()) {
            case MINUTE:
                nextTime = nextTime.plusMinutes(entity.getInterval());
                break;
            case HOUR:
                nextTime = nextTime.plusHours(entity.getInterval());
                break;
            case DAY:
                nextTime = nextTime.plusDays(entity.getInterval());
                break;
            case WEEK:
                nextTime = nextTime.plusWeeks(entity.getInterval());
                break;
            case MONTH:
                nextTime = nextTime.plusMonths(entity.getInterval());
                break;
            case QUARTER:
                nextTime = nextTime.plusMonths(entity.getInterval() * 3);
                break;
            default:
                nextTime = nextTime.plusYears(entity.getInterval());
                break;
        }
        return nextTime;
    }

    @Override
    public String collect(CollectTo to) throws SerException {
        List<SupplierInfoCollectBO> collectBos = supplierInformationSer.collect(to);
        StringBuilder table = new StringBuilder(tableF);
        for (int i = 0, lent = collectBos.size(); i < lent; i++) {
            SupplierInfoCollectBO collect = collectBos.get(i);
            if (null != collect) {
                if (i == 0) {
                    table.append(trF).append(tdF).append("地区").append(tdL);
                    for (SupplierInfoCollectTitleBO title : collect.getTitleBOs()) {
                        table.append(tdF).append(title.getTitle()).append(tdL);
                    }
                    table.append(trL);
                }
                table.append(trF).append(tdF).append(collect.getArea()).append(tdL);
                for (SupplierInfoCollectTitleBO title : collect.getTitleBOs()) {
                    table.append(tdF).append(title.getNumber()).append(tdL);
                }
                table.append(trL);
            }
        }
        table.append(tableL);
        return table.toString();
    }

    private CollectSendBO transform(CollectSend entity) throws SerException {
        CollectSendBO bo = BeanTransform.copyProperties(entity, CollectSendBO.class);
        bo.setEmails(entity.getEmail().split(","));
        bo.setAreas(entity.getArea().split(","));
        return bo;
    }

    private List<CollectSendBO> transform(List<CollectSend> list) throws SerException {
        List<CollectSendBO> bos = new ArrayList<>(0);
        for (CollectSend entity : list)
            bos.add(this.transform(entity));
        return bos;
    }

    @Override
    public CollectSendBO save(CollectSendTO to) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CollectSend entity = BeanTransform.copyProperties(to, CollectSend.class);
        entity.setLastTime(LocalDateTime.now());
        this.setEmail(entity, to);
        super.save(entity);
        return this.transform(entity);
    }

    private void setEmail(CollectSend entity, CollectSendTO to) throws SerException {
        StringBuilder email = new StringBuilder(0), area = new StringBuilder(0);
        if (null != to.getEmails())
            for (String s : to.getEmails())
                email.append(s).append(",");
        if (null != to.getAreas())
            for (String s : to.getAreas())
                area.append(s).append(",");
        entity.setEmail(email.toString());
        entity.setArea(area.toString());
    }

    @Override
    public CollectSendBO update(CollectSendTO to) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CollectSend entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        this.setEmail(entity, to);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transform(entity);
    }

    @Override
    public CollectSendBO delete(String id) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CollectSend entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transform(entity);
    }

    @Override
    public CollectSendBO congeal(String id) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CollectSend entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.CONGEAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transform(entity);
    }

    @Override
    public CollectSendBO thaw(String id) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CollectSend entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.THAW);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transform(entity);
    }

    @Override
    public CollectSendBO getById(String id) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CollectSend entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transform(entity);
    }

    @Override
    public List<CollectSendBO> maps(CollectSendDTO dto) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        dto.getSorts().add("status=asc");
        dto.getSorts().add("createTime=desc");
        return this.transform(super.findByPage(dto));
    }

    @Override
    public List<CollectSendBO> findThaw() throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CollectSendDTO dto = new CollectSendDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        return this.transform(super.findByCis(dto));
    }

    @Override
    public Long getTotal(CollectSendDTO dto) throws SerException {
        return super.count(dto);
    }

//    @Override
//    public Boolean sonPermission() throws SerException {
//        return supPermissionSer.getSupPermission(idFlag);
//    }



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
            flag = cusPermissionSer.getSupCusPermission("1");
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
            flag = cusPermissionSer.busSupCusPermission("2");
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
            flag = cusPermissionSer.getSupCusPermission("1");
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
            flag = cusPermissionSer.busSupCusPermission("2");
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
}