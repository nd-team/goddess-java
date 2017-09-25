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
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.dto.BeforeAddDTO;
import com.bjike.goddess.secure.entity.BeforeAdd;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.BeforeAddTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
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
 * 增员前业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class BeforeAddSerImpl extends ServiceImpl<BeforeAdd, BeforeAddDTO> implements BeforeAddSer {
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private RegularizationAPI regularizationAPI;
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
//        ScheduleJobTO scheduleJobTO = new ScheduleJobTO();
//        scheduleJobTO.setClazz("com.bjike.goddess.secure.api.BeforeAddAPI");
//        scheduleJobTO.setName("定时获取新转正员工");
//        scheduleJobTO.setMethod("send");
//        scheduleJobTO.setExpression("0 0 */12 * * ?");   //每12个小时执行一次
//        scheduleJobTO.setDescription("获取新转正员工，并通知福利模块这些员工可购买社保");
//        scheduleJobTO.setEnable(true);
//        scheduleJobTO.setAddress("localhost:51101");
//        scheduleJobTO.setScheduleJobGroupId("eb33c9b6-ed33-4596-be70-b3eb6aa81f1d");
//        scheduleJobAPI.add(scheduleJobTO);
//    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeAddBO save(BeforeAddTO to) throws SerException {
        checkAddIdentity();
        BeforeAdd canAdd = BeanTransform.copyProperties(to, BeforeAdd.class, true);
        canAdd = super.save(canAdd);
        return BeanTransform.copyProperties(canAdd, BeforeAddBO.class);
    }

    private String[] mEmails() throws SerException {
        String token = RpcTransmit.getUserToken();
        Set<String> set = new HashSet<>();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<PositionDetailBO> list1 = positionDetailAPI.findStatus();
            PositionDetailBO p = list1.stream().filter(positionDetailBO -> positionDetailBO.getPosition().contains("总经理")).findFirst().get();
            RpcTransmit.transmitUserToken(token);
            List<UserBO> users = positionDetailUserAPI.findByPosition(p.getId());
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
        String[] mails = new String[set.size()];
        mails = set.toArray(mails);
        return mails;
    }

    private String[] zhEmails() throws SerException {
        Set<String> set = new HashSet<>();
        String token = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            DepartmentDetailBO d = list.stream().filter(departmentDetailBO -> "综合资源部".equals(departmentDetailBO.getDepartment())).findFirst().get();
            if (moduleAPI.isCheck("contacts")) {
                RpcTransmit.transmitUserToken(token);
                CommonalityBO commonality = commonalityAPI.findByDepartment(d.getId());
                if (commonality != null && commonality.getEmail() != null) {
                    set.add(commonality.getEmail());
                }
            }
        }
        String[] mails = new String[set.size()];
        mails = set.toArray(mails);
        return mails;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeAddBO complete(BeforeAddTO to) throws SerException {
        checkAddIdentity();
        BeforeAdd canAdd = super.findById(to.getId());
        if (canAdd == null) {
            throw new SerException("该对象不存在");
        }
        canAdd.setName(to.getName());
        LocalDate time = null;
        try {
            time = DateUtil.parseDate(to.getAddTime());
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        canAdd.setAddTime(time);
        canAdd.setTime(to.getTime());
        canAdd.setRequest(to.getRequest());
        canAdd.setAddCity(to.getAddCity());
        canAdd.setDescription(to.getDescription());
        canAdd.setModifyTime(LocalDateTime.now());
        super.update(canAdd);
        BeforeAddBO beforeAddBO = BeanTransform.copyProperties(canAdd, BeforeAddBO.class);
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("社保增员参考信息");
        messageTO.setContent(html(beforeAddBO));
        String[] strings = mEmails();
        if (strings != null && strings.length > 0) {
            messageTO.setReceivers(strings);
            messageAPI.send(messageTO);
        }
        return beforeAddBO;
    }

    private String html(BeforeAddBO buy) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>增员参考信息:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
//            CollectEmailBO title = dispatchEmails.get(dispatchEmails.size() - 1);
        sb.append("<tr>");
        sb.append("<td>资质名称</td>");
        sb.append("<td>公司名称</td>");
        sb.append("<td>社保需增地市</td>");
        sb.append("<td>社保地市特殊说明</td>");
        sb.append("<td>需增员时间</td>");
        sb.append("<td>需参保时长</td>");
        sb.append("<td>增员参保周期特殊要求</td>");
        sb.append("<td>当前各公司参保总人数</td>");
        sb.append("<td>当前各地市总参保人员</td>");
        sb.append("<td>可参保人员姓名</td>");
        sb.append("<td>可参保人员新增的地市</td>");
        sb.append("<td>备注</td>");
        sb.append("</tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + buy.getIntelligence() + "</td>");
        sb.append("<td>" + buy.getCompany() + "</td>");
        sb.append("<td>" + buy.getArrival() + "</td>");
        sb.append("<td>" + buy.getArrivalDescrption() + "</td>");
        sb.append("<td>" + buy.getAddTime() + "</td>");
        sb.append("<td>" + buy.getTime() + "</td>");
        sb.append("<td>" + buy.getRequest() + "</td>");
        sb.append("<td>" + buy.getCompanyCount() + "</td>");
        sb.append("<td>" + buy.getCityCount() + "</td>");
        sb.append("<td>" + buy.getName() + "</td>");
        sb.append("<td>" + buy.getAddCity() + "</td>");
        sb.append("<td>" + buy.getDescription() + "</td>");
        sb.append("</tr>");
        //结束
        sb.append("</table>");
        return sb.toString();
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(BeforeAddTO to) throws SerException {
        checkAddIdentity();
        BeforeAdd canAdd = super.findById(to.getId());
        if (canAdd == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = canAdd.getCreateTime();
        canAdd = BeanTransform.copyProperties(to, BeforeAdd.class, true);
        canAdd.setCreateTime(a);
        canAdd.setModifyTime(LocalDateTime.now());
        super.update(canAdd);
    }

    @Override
    public void add(AddEmployeeDTO dto, String id) throws SerException {
        checkAddIdentity();
        String token = RpcTransmit.getUserToken();
        BeforeAdd entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setIncrease(true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        BeforeAddBO beforeAddBO = BeanTransform.copyProperties(entity, BeforeAddBO.class);
        Boolean send = dto.getSend();
        if (send) {
            String[] users = dto.getUsers();
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("社保增员参考信息");
            messageTO.setContent(html(beforeAddBO));
            if (users != null) {
                if (moduleAPI.isCheck("contacts")) {
                    RpcTransmit.transmitUserToken(token);
                    List<String> mails = internalContactsAPI.getEmails(users);
                    if (mails != null && !mails.isEmpty()) {
                        String[] emails = new String[mails.size()];
                        emails = mails.toArray(emails);
                        messageTO.setReceivers(emails);
                        messageAPI.send(messageTO);
                    }
                }
            }
            messageTO.setReceivers(zhEmails());
            if (zhEmails() != null && zhEmails().length > 0) {
                messageAPI.send(messageTO);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeAddBO delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        return null;
    }

    @Override
    public List<BeforeAddBO> find(BeforeAddDTO dto) throws SerException {
        checkSeeIdentity();
        List<BeforeAdd> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, BeforeAddBO.class);
    }

    @Override
    public BeforeAddBO findByID(String id) throws SerException {
        BeforeAdd canAdd = super.findById(id);
        return BeanTransform.copyProperties(canAdd, BeforeAddBO.class);
    }

    @Override
    //每12小时执行一次
    public void send() throws SerException {
        String token = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("regularization")) {
            RpcTransmit.transmitUserToken(token);
            List<RegularizationBO> boList = regularizationAPI.list(new RegularizationDTO());
            LocalDate now = LocalDate.now();
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("有转正员工可购买社保");
            boolean b = false;
            StringBuilder sb = new StringBuilder();
            if ((boList != null) && (!boList.isEmpty())) {
                for (RegularizationBO bo : boList) {
                    if (StringUtils.isNotBlank(bo.getPositiveDate())) {
                        if (now == DateUtil.parseDate(bo.getPositiveDate())) {
                            b = true;
                            String name = bo.getName();
                            String empNo = bo.getEmpNo();
                            sb.append("员工编号为:" + empNo + "的" + name + "，");
                        }
                    }
                }
            }
            if (b) {
                sb.append("已转正，可购买社保，请及时处理!");
                messageTO.setContent(sb.toString());
                messageTO.setReceivers(flEmails());
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");
                if (flEmails() != null && flEmails().length > 0) {
                    messageAPI.send(messageTO);
                }
            }
        }
    }

    private String[] flEmails() throws SerException {
        String token = RpcTransmit.getUserToken();
        Set<String> set = new HashSet<>();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<PositionDetailBO> list1 = positionDetailAPI.findStatus();
            PositionDetailBO p = list1.stream().filter(positionDetailBO -> "综合资源部".equals(positionDetailBO.getDepartmentName()) && "福利模块".equals(positionDetailBO.getModuleName())).findFirst().get();
            if (moduleAPI.isCheck("organize")) {
                RpcTransmit.transmitUserToken(token);
                List<UserBO> users = positionDetailUserAPI.findByPosition(p.getId());
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
        String[] mails = new String[set.size()];
        mails = set.toArray(mails);
        return mails;
    }

    @Override
    public Long count(BeforeAddDTO dto) throws SerException {
        return super.count(dto);
    }

//    private String getJobGroupId() throws SerException{
//        ScheduleJobGroupTO scheduleJobGroupTO = new ScheduleJobGroupTO();
//        scheduleJobGroupTO.setName("定时获取新转正员工工作组");
//        scheduleJobGroupTO.setEnable(true);
//        ScheduleJobGroupBO scheduleJobGroupBO = scheduleJobGroupAPI.add(null, scheduleJobGroupTO);
//        return scheduleJobGroupBO.getId();
//    }
}