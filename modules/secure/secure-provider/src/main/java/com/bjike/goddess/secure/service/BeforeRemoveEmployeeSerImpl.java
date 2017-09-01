package com.bjike.goddess.secure.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.bo.DimissionInfoBO;
import com.bjike.goddess.dimission.enums.DimissionType;
import com.bjike.goddess.dimission.enums.HandleStatus;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.secure.bo.BeforeRemoveEmployeeBO;
import com.bjike.goddess.secure.dto.BeforeRemoveEmployeeDTO;
import com.bjike.goddess.secure.entity.BeforeRemoveEmployee;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.BeforeRemoveEmployeeTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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
 * 减员前业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:45 ]
 * @Description: [ 减员前业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class BeforeRemoveEmployeeSerImpl extends ServiceImpl<BeforeRemoveEmployee, BeforeRemoveEmployeeDTO> implements BeforeRemoveEmployeeSer {
    @Autowired
    private RemoveEmployeeSer removeEmployeeSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private DimissionInfoAPI dimissionInfoAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
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
////        scheduleJobGroupTO.setName("定时查找离职员工工作组");
////        scheduleJobGroupTO.setEnable(true);
////        ScheduleJobGroupBO scheduleJobGroupBO = scheduleJobGroupAPI.add(null, scheduleJobGroupTO);
//        ScheduleJobTO scheduleJobTO = new ScheduleJobTO();
//        scheduleJobTO.setClazz("com.bjike.goddess.secure.api.BeforeRemoveEmployeeAPI");
//        scheduleJobTO.setName("定时查找离职员工");
//        scheduleJobTO.setMethod("send");
//        scheduleJobTO.setExpression("0 0 */12 * * ?");   //每12个小时执行一次
//        scheduleJobTO.setDescription("查找离职员工，并通知综合资源部");
//        scheduleJobTO.setEnable(true);
//        scheduleJobTO.setAddress("localhost:51101");
//        scheduleJobTO.setScheduleJobGroupId("eb33c9b6-ed33-4596-be70-b3eb6aa81f1d");
//        scheduleJobAPI.add(scheduleJobTO);
//    }

    private String[] mEmails() throws SerException {
        String token=RpcTransmit.getUserToken();
        Set<String> set = new HashSet<>();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<PositionDetailBO> list1 = positionDetailAPI.findStatus();
            for (PositionDetailBO positionDetailBO : list1) {
                if ("总经理".equals(positionDetailBO.getPosition())) {
                    RpcTransmit.transmitUserToken(token);
                    List<UserBO> users = positionDetailUserAPI.findByPosition(positionDetailBO.getId());
                    for (UserBO userBO : users) {
                        if (moduleAPI.isCheck("contacts")) {
                            RpcTransmit.transmitUserToken(token);
                            String mail = internalContactsAPI.getEmail(userBO.getUsername());
                            if (mail != null) {
                                set.add(mail);
                            }
                        }
                    }
                }
            }
        }
        String[] mails = new String[set.size()];
        mails = set.toArray(mails);
        return mails;
    }

    private String[] zhEmails() throws SerException {
        String token=RpcTransmit.getUserToken();
        Set<String> set = new HashSet<>();
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
        String[] mails = new String[set.size()];
        mails = set.toArray(mails);
        return mails;
    }

    private String[] flEmails() throws SerException {
        Set<String> set = new HashSet<>();
        String token=RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<PositionDetailBO> list1 = positionDetailAPI.findStatus();
            for (PositionDetailBO positionDetailBO : list1) {
                if ("综合资源部".equals(positionDetailBO.getDepartmentName()) && "福利模块".equals(positionDetailBO.getModuleName())) {
                    RpcTransmit.transmitUserToken(token);
                    List<UserBO> users = positionDetailUserAPI.findByPosition(positionDetailBO.getId());
                    for (UserBO userBO : users) {
                        if (moduleAPI.isCheck("contacts")) {
                            RpcTransmit.transmitUserToken(token);
                            String mail = internalContactsAPI.getEmail(userBO.getUsername());
                            if (mail != null) {
                                set.add(mail);
                            }
                        }
                    }
                }
            }
        }
        String[] mails = new String[set.size()];
        mails = set.toArray(mails);
        return mails;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeRemoveEmployeeBO save(BeforeRemoveEmployeeTO to) throws SerException {
        checkAddIdentity();
        BeforeRemoveEmployee beforeRemoveEmployee = BeanTransform.copyProperties(to, BeforeRemoveEmployee.class, true);
        super.save(beforeRemoveEmployee);
        BeforeRemoveEmployeeBO beforeRemoveEmployeeBO = BeanTransform.copyProperties(beforeRemoveEmployee, BeforeRemoveEmployeeBO.class);
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("社保减员前参考信息,请审阅");
        messageTO.setContent(html(beforeRemoveEmployeeBO));
        messageTO.setReceivers(mEmails());
        if (mEmails() != null && mEmails().length > 0) {
            messageAPI.send(messageTO);
        }
        return beforeRemoveEmployeeBO;
    }

    private String html(BeforeRemoveEmployeeBO buy) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>社保减员前参考信息:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
//            CollectEmailBO title = dispatchEmails.get(dispatchEmails.size() - 1);
        sb.append("<tr>");
        sb.append("<td>当前各公司参保总人数</td>");
        sb.append("<td>当前各地市总参保人员</td>");
        sb.append("<td>减员类型</td>");
        sb.append("<td>公司名称</td>");
        sb.append("<td>减员的人员姓名</td>");
        sb.append("<td>减员的人员当前参保地市</td>");
        sb.append("<td>资质名称</td>");
        sb.append("<td>需参保时长</td>");
        sb.append("<td>需减员总人数</td>");
        sb.append("<td>备注</td>");
        sb.append("</tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + buy.getCountCompany() + "</td>");
        sb.append("<td>" + buy.getCountCity() + "</td>");
        sb.append("<td>" + buy.getRemoveType() + "</td>");
        sb.append("<td>" + buy.getCompany() + "</td>");
        sb.append("<td>" + buy.getRemoveName() + "</td>");
        sb.append("<td>" + buy.getRemoveCity() + "</td>");
        sb.append("<td>" + buy.getQuantityName() + "</td>");
        sb.append("<td>" + buy.getSecureTime() + "</td>");
        sb.append("<td>" + buy.getRemoveCount() + "</td>");
        sb.append("<td>" + buy.getDescription() + "</td>");
        sb.append("<td>" + buy.getEmployeeId() + "</td>");
        sb.append("</tr>");
        //结束
        sb.append("</table>");
        return sb.toString();
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        checkAddIdentity();
        String userToken = RpcTransmit.getUserToken();
        BeforeRemoveEmployee entity = super.findById(id);
        entity.setIs_remove(true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        RemoveEmployeeTO removeEmployeeTO = new RemoveEmployeeTO();
        BeanUtils.copyProperties(entity, removeEmployeeTO);
        RpcTransmit.transmitUserToken(userToken);
        removeEmployeeSer.save(removeEmployeeTO);
    }

    @Override
    public List<BeforeRemoveEmployeeBO> find(BeforeRemoveEmployeeDTO dto) throws SerException {
        checkSeeIdentity();
        List<BeforeRemoveEmployee> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, BeforeRemoveEmployeeBO.class);
    }

    @Override
    public BeforeRemoveEmployeeBO findByID(String id) throws SerException {
        BeforeRemoveEmployee beforeRemoveEmployee = super.findById(id);
        return BeanTransform.copyProperties(beforeRemoveEmployee, BeforeRemoveEmployeeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeRemoveEmployeeBO delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        return null;
    }

    @Override
    //每12小时执行一次
    public void send() throws SerException {
        String token=RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("dimission")) {
            RpcTransmit.transmitUserToken(token);
            List<DimissionInfoBO> list = dimissionInfoAPI.all();
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("今天的离职员工");
            LocalDate now = LocalDate.now();
            boolean b1 = false;
            StringBuilder sb1 = new StringBuilder();
            boolean b2 = false;
            StringBuilder sb2 = new StringBuilder();
            String s = "今天离职，请及时处理社保减员！";
            for (DimissionInfoBO bo : list) {
                if (DimissionType.NORMAL.equals(bo.getType()) && HandleStatus.AFFIRM.equals(bo.getHandle())) {
                    LocalDate date = DateUtil.parseDate(bo.getDimissionDate());
                    if (now == date) {
                        b1 = true;
                        String employeeNumber = bo.getEmployeeNumber();
                        String name = bo.getUsername();
                        sb1.append("员工编号为：" + employeeNumber + "的" + name + "，");
                    }
                }
                if ((!DimissionType.NORMAL.equals(bo.getType())) && HandleStatus.AFFIRM.equals(bo.getHandle())) {
                    LocalDate date = DateUtil.parseDate(bo.getAdvanceDate());
                    if (now == date) {
                        b2 = true;
                        String employeeNumber = bo.getEmployeeNumber();
                        String name = bo.getUsername();
                        sb2.append("员工编号为：" + employeeNumber + "的" + name + "，");
                    }
                }
            }
            if (b1) {
                sb1.append(s);
                messageTO.setContent(sb1.toString());
                messageTO.setReceivers(flEmails());
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");
                if (flEmails() != null && flEmails().length > 0) {
                    messageAPI.send(messageTO);
                }
                messageTO.setReceivers(zhEmails());
                if (zhEmails() != null && zhEmails().length > 0) {
                    messageAPI.send(messageTO);
                }
            }
            if (b2) {
                sb2.append(s);
                messageTO.setContent(sb2.toString());
                messageTO.setReceivers(flEmails());
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");
                if (flEmails() != null && flEmails().length > 0) {
                    messageAPI.send(messageTO);
                }
                messageTO.setReceivers(zhEmails());
                if (zhEmails() != null && zhEmails().length > 0) {
                    messageAPI.send(messageTO);
                }
            }
        }
    }

    @Override
    public Long count(BeforeRemoveEmployeeDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(BeforeRemoveEmployeeTO to) throws SerException {
        checkAddIdentity();
        BeforeRemoveEmployee entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, BeforeRemoveEmployee.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }
}