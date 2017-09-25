package com.bjike.goddess.secure.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.bo.AttachedEndBO;
import com.bjike.goddess.secure.dto.AttachedEndDTO;
import com.bjike.goddess.secure.entity.AttachedEnd;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.AttachedEndTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 挂靠到期业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:04 ]
 * @Description: [ 挂靠到期业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AttachedEndSerImpl extends ServiceImpl<AttachedEnd, AttachedEndDTO> implements AttachedEndSer {
    @Autowired
    private AttachedSer attachedSer;
    @Autowired
    private RemoveEmployeeSer removeEmployeeSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private ModuleAPI moduleAPI;

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
            flag = cusPermissionSer.busCusPermission("2");
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
            flag = cusPermissionSer.getCusPermission("1");
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
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

//    @Override
//    @Transactional(rollbackFor = {SerException.class})
//    public void quartz() throws SerException {
////        ScheduleJobGroupTO scheduleJobGroupTO = new ScheduleJobGroupTO();
////        scheduleJobGroupTO.setName("定时查找挂靠即将到期人员工作组");
////        scheduleJobGroupTO.setEnable(true);
////        ScheduleJobGroupBO scheduleJobGroupBO = scheduleJobGroupAPI.add(null, scheduleJobGroupTO);
//        ScheduleJobTO scheduleJobTO = new ScheduleJobTO();
//        scheduleJobTO.setClazz("com.bjike.goddess.secure.api.AttachedEndAPI");
//        scheduleJobTO.setName("定时查找挂靠即将到期人员");
//        scheduleJobTO.setMethod("send");
//        scheduleJobTO.setExpression("0 0/1440 * * * ?");   //每24个小时执行一次
//        scheduleJobTO.setDescription("查找挂靠即将到期人员，并通知综合资源部");
//        scheduleJobTO.setEnable(true);
//        scheduleJobTO.setAddress("localhost:51101");
//        scheduleJobTO.setScheduleJobGroupId("eb33c9b6-ed33-4596-be70-b3eb6aa81f1d");
//        scheduleJobAPI.add(scheduleJobTO);
//    }


    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedEndBO save(AttachedEndTO to) throws SerException {
        checkAddIdentity();
        AttachedEnd entity = BeanTransform.copyProperties(to, AttachedEnd.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, AttachedEndBO.class);
//        List<AttachedBO> list = attachedSer.findALL();
//        if (list.size() > 0) {
//            for (AttachedBO bo : list) {
//                if (bo.getAdvice()) {
//                    String endTime = bo.getEndTime();
//                    LocalDateTime localDateTime = DateUtil.parseDateTime(endTime);
//                    localDateTime = localDateTime.minusMonths(1);
//                    int year = localDateTime.getYear();
//                    int month = localDateTime.getMonthValue();
//                    int day = localDateTime.getDayOfMonth();
//                    LocalDateTime localDateTime1 = LocalDateTime.now();
//                    int year1 = localDateTime1.getYear();
//                    int month1 = localDateTime1.getMonthValue();
//                    int day1 = localDateTime1.getDayOfMonth();
//                    if (year1 == year && month == month1 && day == day1) {
//                        AttachedEnd attachedEnd = new AttachedEnd();
//                        BeanUtils.copyProperties(bo, attachedEnd);
//                        super.save(attachedEnd);
//                    }
//                }
//            }
//        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedEndBO is_Again(AttachedEndTO to) throws SerException {
        checkAddIdentity();
        String userToken = RpcTransmit.getUserToken();
        AttachedEnd attachedEnd = super.findById(to.getId());
        if (attachedEnd == null) {
            throw new SerException("该对象不存在");
        }
//        LocalDateTime a = attachedEnd.getCreateTime();
//        attachedEnd = BeanTransform.copyProperties(to, AttachedEnd.class, true);
//        attachedEnd.setCreateTime(a);
        attachedEnd.setIsAttachedAgin(to.getIsAttachedAgin());
        attachedEnd.setModifyTime(LocalDateTime.now());
        super.update(attachedEnd);
        if (to.getIsAttachedAgin()) {
//            AttachedTO attachedTO = new AttachedTO();
//            BeanUtils.copyProperties(to, attachedTO);
//            attachedSer.update(attachedTO);
        } else {
            RemoveEmployeeTO removeEmployeeTO = new RemoveEmployeeTO();
            removeEmployeeTO.setRemoveName(attachedEnd.getAttachedName());
            removeEmployeeTO.setRemoveCity(attachedEnd.getAttachedArrival());
            RpcTransmit.transmitUserToken(userToken);
            removeEmployeeSer.save(removeEmployeeTO);
        }
        return BeanTransform.copyProperties(attachedEnd, AttachedEndBO.class);
    }

    @Override
    public List<AttachedEndBO> find(AttachedEndDTO dto) throws SerException {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        int year = localDateTime.getYear();
//        int month = localDateTime.getMonthValue();
//        int day = localDateTime.getDayOfMonth();
//        if ((year1 != year) || (month1 != month) || (day1 != day)) {
//            b = true;
//        }
//        if (b) {
//            this.save();
//            year1 = year;
//            month1 = month;
//            day1 = day;
//            b = false;
//        }
        checkSeeIdentity();
        List<AttachedEnd> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AttachedEndBO.class);
    }

    @Override
    public AttachedEndBO findByID(String id) throws SerException {
        AttachedEnd attachedEnd = super.findById(id);
        return BeanTransform.copyProperties(attachedEnd, AttachedEndBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedEndBO delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        return null;
    }

    private Set<String> zhEmails() throws SerException {
        Set<String> set = new HashSet<>();
        String token=RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            for (DepartmentDetailBO departmentDetailBO : list) {
                if ("综合资源部".equals(departmentDetailBO.getDepartment())) {
                    if (moduleAPI.isCheck("contacts")) {
                        RpcTransmit.transmitUserToken(token);
                        CommonalityBO commonality = commonalityAPI.findByDepartment(departmentDetailBO.getId());
                        if (commonality != null&&commonality.getEmail()!=null) {
                            set.add(commonality.getEmail());
                        }
                    }
                }
            }
        }
        return set;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //定时发送，每天12点执行
    public void send() throws SerException {
        List<AttachedBO> boList = attachedSer.findALL();
        LocalDate now = LocalDate.now();
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("有社保挂靠即将到期的人员");
        StringBuilder sb = new StringBuilder();
        boolean b = false;
        for (AttachedBO bo : boList) {
            if (StringUtils.isNotBlank(bo.getEndTime())) {
                LocalDate endTime = DateUtil.parseDate(bo.getEndTime());
                if (now == (endTime.minusMonths(1))) {
                    b = true;
                    String name = bo.getAttachedName();
                    sb.append(name + "，");
                    AttachedEndTO attachedEndTO = new AttachedEndTO();
                    BeanUtils.copyProperties(bo, attachedEndTO);
                    save(attachedEndTO);
                }
            }
        }
        if (b) {
            sb.append("社保挂靠即将到期，请及时处理!");
            Set<String> set = zhEmails();
            String[] emails = new String[set.size()];
            messageTO.setContent(sb.toString());
            messageTO.setReceivers(emails);
            messageTO.setSenderId("SYSTEM");
            messageTO.setSenderName("SYSTEM");
            if (emails != null && emails.length > 0) {
                messageAPI.send(messageTO);
            }
        }
    }

    @Override
    public Long count(AttachedEndDTO dto) throws SerException {
        return super.count(dto);
    }
}