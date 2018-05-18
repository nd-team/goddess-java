package com.bjike.goddess.secure.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
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
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.entity.Attached;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.AddEmployeeTO;
import com.bjike.goddess.secure.to.AttachedTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.NameTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 挂靠业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AttachedSerImpl extends ServiceImpl<Attached, AttachedDTO> implements AttachedSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
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

    //总经办
    private void checkMIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
            if (!flag) {
                throw new SerException("您不是总经理，不可以操作");
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

    //总经办
    private Boolean guideMIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
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
            case BOSS:
                flag = guideMIdentity();
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

    private String[] yyEmails() throws SerException {
        Set<String> set = new HashSet<>();
        String token = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            List<DepartmentDetailBO> d = list.stream().filter(departmentDetailBO -> "综合资源部".equals(departmentDetailBO.getDepartment())).collect(Collectors.toList());
            if (moduleAPI.isCheck("contacts")) {
                RpcTransmit.transmitUserToken(token);
                if (!d.isEmpty()) {
                    CommonalityBO commonality = commonalityAPI.findByDepartment(d.get(0).getId());
                    if (commonality != null && commonality.getEmail() != null) {
                        set.add(commonality.getEmail());
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
            List<DepartmentDetailBO> d = list.stream().filter(departmentDetailBO -> "综合资源部".equals(departmentDetailBO.getDepartment())).collect(Collectors.toList());
            if (moduleAPI.isCheck("contacts")) {
                RpcTransmit.transmitUserToken(token);
                if (!d.isEmpty()) {
                    CommonalityBO commonality = commonalityAPI.findByDepartment(d.get(0).getId());
                    if (commonality != null && commonality.getEmail() != null) {
                        set.add(commonality.getEmail());
                    }
                }
            }
        }

        String[] mails = new String[set.size()];
        mails = set.toArray(mails);
        return mails;
    }

    private String[] mEmails() throws SerException {
        Set<String> set = new HashSet<>();
        String token = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<PositionDetailBO> list1 = positionDetailAPI.findStatus();
            List<PositionDetailBO> p = list1.stream().filter(positionDetailBO -> positionDetailBO.getPosition().contains("总经理")).collect(Collectors.toList());
            RpcTransmit.transmitUserToken(token);
            if (!p.isEmpty()) {
                List<UserBO> users = positionDetailUserAPI.findByPosition(p.get(0).getId());
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
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO save(AttachedTO to) throws SerException {
        checkAddIdentity();
        Attached attached = BeanTransform.copyProperties(to, Attached.class, true);
        attached = super.save(attached);
        AttachedBO attachedBO = BeanTransform.copyProperties(attached, AttachedBO.class);
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("社保挂靠信息");
        messageTO.setContent(html(attachedBO));
        String[] strings = mEmails();
        if (strings != null && strings.length > 0) {
            messageTO.setReceivers(strings);
            messageAPI.send(messageTO);
        }
        return attachedBO;
    }
    private String html(AttachedBO buy) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>社保挂靠信息:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
//            CollectEmailBO title = dispatchEmails.get(dispatchEmails.size() - 1);
        sb.append("<tr>");
        sb.append("<td>挂靠人姓名</td>");
        sb.append("<td>挂靠原因</td>");
        sb.append("<td>挂靠城市</td>");
        sb.append("<td>挂靠人目前居住地</td>");
        sb.append("<td>挂靠时长</td>");
        sb.append("<td>担保人姓名</td>");
        sb.append("<td>挂靠人与担保人关系</td>");
        sb.append("<td>担保人在司岗位</td>");
        sb.append("<td>担保人所属地区</td>");
        sb.append("<td>担保人联系电话</td>");
        sb.append("<td>挂靠开始时间</td>");
        sb.append("<td>挂靠结束时间</td>");
        sb.append("<td>挂靠的公司</td>");
        sb.append("<td>审批意见</td>");
        sb.append("<td>挂靠的缴费金</td>");
        sb.append("<td>金额的支付类型</td>");
        sb.append("<td>前参保地市</td>");
        sb.append("<td>前参保时间</td>");
        sb.append("<td>已参保年限</td>");
        sb.append("<td>备注</td>");
        sb.append("</tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + buy.getAttachedName() + "</td>");
        sb.append("<td>" + buy.getReason() + "</td>");
        sb.append("<td>" + buy.getCity() + "</td>");
        sb.append("<td>" + buy.getAttachedArrival() + "</td>");
        sb.append("<td>" + buy.getTime() + "</td>");
        sb.append("<td>" + buy.getAssureName() + "</td>");
        sb.append("<td>" + buy.getReverse() + "</td>");
        sb.append("<td>" + buy.getAssureJob() + "</td>");
        sb.append("<td>" + buy.getAssureArrival() + "</td>");
        sb.append("<td>" + buy.getAssureTel() + "</td>");
        sb.append("<td>" + buy.getStartTime() + "</td>");
        sb.append("<td>" + buy.getEndTime() + "</td>");
        sb.append("<td>" + buy.getCompany() + "</td>");
        sb.append("<td>" + buy.getAdvice() + "</td>");
        sb.append("<td>" + buy.getMoney() + "</td>");
        sb.append("<td>" + buy.getPay() + "</td>");
        sb.append("<td>" + buy.getBeforeCity() + "</td>");
        sb.append("<td>" + buy.getBeforeTime() + "</td>");
        sb.append("<td>" + buy.getInsuredYear() + "</td>");
        sb.append("<td>" + buy.getDecription() + "</td>");
        sb.append("</tr>");
        //结束
        sb.append("</table>");
        return sb.toString();
    }

    @Override
    public void pass(AddEmployeeDTO dto, String id) throws SerException {
        checkMIdentity();
        Attached attached = super.findById(id);
        if (attached == null) {
            throw new SerException("该对象不存在");
        }
        attached.setAdvice("审批通过");
        attached.setModifyTime(LocalDateTime.now());
        super.update(attached);
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("挂靠社保信息审批意见");
        messageTO.setContent("挂靠人：" + attached.getAttachedName() + "的挂靠信息" + attached.getAdvice());
        Boolean send = dto.getSend();
        if (send) {
            String[] users = dto.getUsers();
            if (users != null) {
                String token = RpcTransmit.getUserToken();
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
    public void notPass(AddEmployeeDTO dto, String id) throws SerException {
        checkMIdentity();
        Attached attached = super.findById(id);
        if (attached == null) {
            throw new SerException("该对象不存在");
        }
        attached.setAdvice("审批不通过");
        attached.setModifyTime(LocalDateTime.now());
        super.update(attached);
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("挂靠社保信息审批意见");
        messageTO.setContent("挂靠人：" + attached.getAttachedName() + "的挂靠信息" + attached.getAdvice());
        Boolean send = dto.getSend();
        if (send) {
            String[] users = dto.getUsers();
            if (users != null) {
                String token = RpcTransmit.getUserToken();
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
    public AttachedBO complete(AttachedTO to) throws SerException {
        checkAddIdentity();
        Attached attached = super.findById(to.getId());
        attached.setAssureJob(to.getAssureJob());
        attached.setAssureArrival(to.getAssureArrival());
        attached.setAssureTel(to.getAssureTel());
        attached.setStartTime(DateUtil.parseDate(to.getStartTime()));
        attached.setEndTime(DateUtil.parseDate(to.getEndTime()));
        attached.setCompany(to.getCompany());
        attached.setMoney(to.getMoney());
        if ((to.getBeforeCity() != null) && (StringUtils.isNotBlank(to.getBeforeCity()))) {
            attached.setBeforeCity(to.getBeforeCity());
        }
        if ((to.getBeforeTime() != null) && (StringUtils.isNotBlank(to.getBeforeTime()))) {
            attached.setBeforeTime(DateUtil.parseDate(to.getBeforeTime()));
        }
        if ((to.getInsuredYear() != null) && (StringUtils.isNotBlank(to.getInsuredYear()))) {
            attached.setInsuredYear(to.getInsuredYear());
        }
        attached.setModifyTime(LocalDateTime.now());
        super.update(attached);
        AttachedBO attachedBO = BeanTransform.copyProperties(attached, AttachedBO.class);
        MessageTO messageTO = new MessageTO();
        messageTO.setContent(html(attachedBO));
        messageTO.setTitle("以下是社保挂靠基本信息，请给予意见");
        String[] strings = yyEmails();
        if (strings != null && strings.length > 0) {
            messageTO.setReceivers(strings);
            messageAPI.send(messageTO);
        }
        String[] strings1 = mEmails();
        if (strings1 != null && strings1.length > 0) {
            messageTO.setReceivers(strings1);
            messageAPI.send(messageTO);
        }
        AddEmployeeTO addEmployeeTO = new AddEmployeeTO();
        BeanUtils.copyProperties(attached, addEmployeeTO);
        addEmployeeTO.setName(attached.getAttachedName());
        addEmployeeTO.setSecureTime(DateUtil.dateToString(attached.getStartTime()));
        return attachedBO;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        return null;
    }

    @Override
    public List<AttachedBO> find(AttachedDTO dto) throws SerException {
        checkSeeIdentity();
        search(dto);
        List<Attached> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AttachedBO.class);
    }

    @Override
    public AttachedBO findByID(String id) throws SerException {
        Attached attached = super.findById(id);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
    }

    @Override
    public List<AttachedBO> findALL() throws SerException {
        List<Attached> list = super.findAll();
        return BeanTransform.copyProperties(list, AttachedBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO edit(AttachedTO to) throws SerException {
        checkAddIdentity();
        Attached attached = super.findById(to.getId());
        LocalDateTime a = attached.getCreateTime();
        attached = BeanTransform.copyProperties(to, Attached.class, true);
        attached.setCreateTime(a);
        attached.setModifyTime(LocalDateTime.now());
        super.update(attached);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
    }

    @Override
    public Long count(AttachedDTO dto) throws SerException {
        search(dto);
        return super.count(dto);
    }

    @Override
    public AttachedBO findAttached(String name) throws SerException {
        AttachedDTO dto = new AttachedDTO();
        dto.getConditions().add(Restrict.eq("attachedName", name));
        Attached attached = super.findOne(dto);
        AttachedBO bo = BeanTransform.copyProperties(attached, AttachedBO.class, false);
        return bo;
    }

    private void search(AttachedDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getAttachedName())) {
            dto.getConditions().add(Restrict.like("attachedName", dto.getAttachedName()));
        }
        if (StringUtils.isNotBlank(dto.getCity())) {
            dto.getConditions().add(Restrict.like("city", dto.getCity()));
        }
        if (dto.getAffiliated() != null) {
            dto.getConditions().add(Restrict.like("affiliated", dto.getAffiliated()));
        }
    }

    @Override
    public List<AttachedBO> byName(NameTO to) throws SerException {
        List<Attached> attacheds = new ArrayList<>();
        if (to.getNames() != null) {
            AttachedDTO dto = new AttachedDTO();
            dto.getConditions().add(Restrict.eq("attachedName", to.getNames()));
            attacheds = super.findByCis(dto);
        }
        List<AttachedBO> boList = BeanTransform.copyProperties(attacheds, AttachedBO.class);
        return boList;
    }

    @Override
    public Set<String> allName() throws SerException {
        Set<String> set = new HashSet<>();
        List<Attached> list = super.findAll();
        for (Attached attached : list) {
            set.add(attached.getAttachedName());
        }
        return set;
    }
}