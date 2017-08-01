package com.bjike.goddess.secure.service;

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
import com.bjike.goddess.secure.bo.AddEmployeeBO;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.bo.BuyBO;
import com.bjike.goddess.secure.bo.EmployeeSecureBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.dto.BuyDTO;
import com.bjike.goddess.secure.entity.*;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.AddEmployeeTO;
import com.bjike.goddess.secure.to.BuyTO;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.vo.SonPermissionObject;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 社保增员信息名单业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 03:02 ]
 * @Description: [ 社保增员信息名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AddEmployeeSerImpl extends ServiceImpl<AddEmployee, AddEmployeeDTO> implements AddEmployeeSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private AbandonSer abandonSer;
    @Autowired
    private AttachedSer attachedSer;
    @Autowired
    private AttachedEndSer attachedEndSer;
    @Autowired
    private BeforeAddSer beforeAddSer;
    @Autowired
    private BeforeRemoveEmployeeSer beforeRemoveEmployeeSer;
    @Autowired
    private BuySer buySer;
    @Autowired
    private EmployeeSecureSer employeeSecureSer;
    @Autowired
    private OutEmployeeSer outEmployeeSer;
    @Autowired
    private PayDetailSer payDetailSer;
    @Autowired
    private RemoveEmployeeSer removeEmployeeSer;
    @Autowired
    private SecureCartSer secureCartSer;

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
                throw new SerException("您不是相应部门的人员，不可以查看");
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

    //运营商务部审核
    private void checkYYIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是运营商务部的人员，不可以操作");
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

    //社保管理负责人
    private void checkSBIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
            if (!flag) {
                throw new SerException("您不是社保管理负责人，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

//    /**
//     * 审批权限
//     */
//    private void checkAuditIdentity() throws SerException {
//        Boolean flag = false;
//        String userToken = RpcTransmit.getUserToken();
//        UserBO userBO = userAPI.currentUser();
//        RpcTransmit.transmitUserToken(userToken);
//        String userName = userBO.getUsername();
//        List<PositionDetailBO> boList = positionDetailAPI.findStatus();
//        List<UserBO> list = null;
//        if ((boList != null) && (!boList.isEmpty())) {
//            for (PositionDetailBO bo : boList) {
//                if ("管理层".equals(bo.getArrangementName()) && "资金模块".equals(bo.getModuleName()) && "资金模块负责人".equals(bo.getPosition())) {
//                    list = positionDetailUserAPI.findByPosition(bo.getId());
//                }
//            }
//        }
//        boolean b = false;
//        if ((list != null) && (!list.isEmpty())) {
//            for (UserBO bo : list) {
//                if (userName.equals(bo.getUsername())) {
//                    b = true;
//                }
//            }
//        }
//        if (!b) {
//            RpcTransmit.transmitUserToken(userToken);
//            flag = cusPermissionSer.getCusPermission("3");
//            if (!flag) {
//                throw new SerException("您不是资金模块负责人，不可以操作");
//            }
//        }
//        RpcTransmit.transmitUserToken(userToken);
//    }

    /**
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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

    //运营商务部审核
    private Boolean guideYYIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
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

    //社保管理负责人
    private Boolean guideSBIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagYYSign = guideYYIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagMSign = guideMIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSBSign = guideSBIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("addemployee");
        obj.setDescribesion("社保增员信息名单");
        if (flagSeeSign || flagAddSign||flagYYSign||flagMSign||flagSBSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = abandonSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("abandon");
        obj.setDescribesion("放弃购买名单");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = attachedSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("attached");
        obj.setDescribesion("挂靠");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = attachedEndSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("attachedend");
        obj.setDescribesion("挂靠到期");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail1 = beforeAddSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("beforeadd");
        obj.setDescribesion("增员前");
        if (flagSeeEmail1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail2 = beforeRemoveEmployeeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("beforeremoveemployee");
        obj.setDescribesion("减员前");
        if (flagSeeEmail2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail3 = buySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("buy");
        obj.setDescribesion("购买社保人员");
        if (flagSeeEmail3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail4 = employeeSecureSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("employeesecure");
        obj.setDescribesion("员工社保基本信息");
        if (flagSeeEmail4) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail5 = outEmployeeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("outemployee");
        obj.setDescribesion("离职名单");
        if (flagSeeEmail5) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail6 = payDetailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("paydetail");
        obj.setDescribesion("缴费比例明细");
        if (flagSeeEmail6) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail7 = removeEmployeeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("removeemployee");
        obj.setDescribesion("减员名单");
        if (flagSeeEmail7) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail8 = secureCartSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("securecart");
        obj.setDescribesion("社保卡基本信息");
        if (flagSeeEmail8) {
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
            case BUINESS:
                flag = guideYYIdentity();
                break;
            case BOSS:
                flag = guideMIdentity();
                break;
            case CHARGE:
                flag = guideSBIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEARCH:
                flag = guideSBIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public List<AddEmployeeBO> find(AddEmployeeDTO dto) throws SerException {
        checkSeeIdentity();
        List<AddEmployee> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AddEmployeeBO.class);
    }

    @Override
    public AddEmployeeBO findByID(String id) throws SerException {
        AddEmployee addEmployee = super.findById(id);
        return BeanTransform.copyProperties(addEmployee, AddEmployeeBO.class);
    }

    private Set<String> emails() throws SerException {
        Set<String> set = new HashSet<>();
        List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
        List<PositionDetailBO> list1 = positionDetailAPI.findStatus();
        for (DepartmentDetailBO departmentDetailBO : list) {
            if ("运营商务部".equals(departmentDetailBO.getDepartment())) {
                CommonalityBO commonality = commonalityAPI.findByDepartment(departmentDetailBO.getId());
                if (commonality != null) {
                    set.add(commonality.getEmail());
                }
            }
        }
        for (PositionDetailBO positionDetailBO : list1) {
            if ("总经理".equals(positionDetailBO.getPosition())) {
                List<UserBO> users = positionDetailUserAPI.findByPosition(positionDetailBO.getId());
                for (UserBO userBO : users) {
                    String mail = internalContactsAPI.getEmail(userBO.getUsername());
                    if (mail != null) {
                        set.add(mail);
                    }
                }
            }
        }
        return set;
    }

    private Set<String> yyEmails() throws SerException {
        Set<String> set = new HashSet<>();
        List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
        for (DepartmentDetailBO departmentDetailBO : list) {
            if ("运营商务部".equals(departmentDetailBO.getDepartment())) {
                CommonalityBO commonality = commonalityAPI.findByDepartment(departmentDetailBO.getId());
                if (commonality != null) {
                    set.add(commonality.getEmail());
                }
            }
        }
        return set;
    }

    private Set<String> mEmails() throws SerException {
        Set<String> set = new HashSet<>();
        List<PositionDetailBO> list1 = positionDetailAPI.findStatus();
        for (PositionDetailBO positionDetailBO : list1) {
            if ("总经理".equals(positionDetailBO.getPosition())) {
                List<UserBO> users = positionDetailUserAPI.findByPosition(positionDetailBO.getId());
                for (UserBO userBO : users) {
                    String mail = internalContactsAPI.getEmail(userBO.getUsername());
                    if (mail != null) {
                        set.add(mail);
                    }
                }
            }
        }
        return set;
    }


    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(AddEmployeeTO to) throws SerException {
        checkAddIdentity();
        AddEmployee addEmployee = super.findById(to.getId());
        if (addEmployee == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = addEmployee.getCreateTime();
        addEmployee = BeanTransform.copyProperties(to, AddEmployee.class, true);
        addEmployee.setCreateTime(a);
        LocalDate time = DateUtil.parseDate(to.getSecureTime());
        addEmployee.setSecureTime(time);
        addEmployee.setMonth(time.getMonthValue());
        addEmployee.setYear(time.getYear());
        addEmployee.setModifyTime(LocalDateTime.now());
        super.update(addEmployee);
        BeforeAdd beforeAdd = findByName(addEmployee.getName());
        AddEmployeeBO addEmployeeBO = BeanTransform.copyProperties(addEmployee, AddEmployeeBO.class);
        if (beforeAdd != null) {
            Set<String> set = emails();
            String[] emalis = new String[set.size()];
            emalis = set.toArray(emalis);
            BeforeAddBO beforeAddBO = beforeAddSer.findByID(beforeAdd.getId());
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("社保增员信息");
            messageTO.setContent(html(beforeAddBO, addEmployeeBO));
            messageTO.setReceivers(emalis);
            messageAPI.send(messageTO);
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AddEmployeeBO delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        return null;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AddEmployeeBO save(AddEmployeeTO to) throws SerException {
        checkAddIdentity();
        AddEmployee addEmployee = BeanTransform.copyProperties(to, AddEmployee.class, true);
        if (to.getSecureTime() == null) {
            addEmployee.setSecureTime(LocalDate.now());
        }
        addEmployee.setMonth(addEmployee.getSecureTime().getMonthValue());
        addEmployee.setYear(addEmployee.getSecureTime().getYear());
        super.save(addEmployee);
        BeforeAdd beforeAdd = findByName(addEmployee.getName());
        AddEmployeeBO addEmployeeBO = BeanTransform.copyProperties(addEmployee, AddEmployeeBO.class);
        if (beforeAdd != null) {
            Set<String> set = emails();
            String[] emalis = new String[set.size()];
            emalis = set.toArray(emalis);
            BeforeAddBO beforeAddBO = beforeAddSer.findByID(beforeAdd.getId());
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("社保增员信息");
            messageTO.setContent(html(beforeAddBO, addEmployeeBO));
            messageTO.setReceivers(emalis);
            messageAPI.send(messageTO);
        }
        return addEmployeeBO;
    }

    private String html(BeforeAddBO beforeAddBO, AddEmployeeBO addEmployeeBO) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>增员前参考信息:</h4>");
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
        sb.append("<td>" + beforeAddBO.getIntelligence() + "</td>");
        sb.append("<td>" + beforeAddBO.getCompany() + "</td>");
        sb.append("<td>" + beforeAddBO.getArrival() + "</td>");
        sb.append("<td>" + beforeAddBO.getArrivalDescrption() + "</td>");
        sb.append("<td>" + beforeAddBO.getAddTime() + "</td>");
        sb.append("<td>" + beforeAddBO.getTime() + "</td>");
        sb.append("<td>" + beforeAddBO.getRequest() + "</td>");
        sb.append("<td>" + beforeAddBO.getCompanyCount() + "</td>");
        sb.append("<td>" + beforeAddBO.getCityCount() + "</td>");
        sb.append("<td>" + beforeAddBO.getName() + "</td>");
        sb.append("<td>" + beforeAddBO.getAddCity() + "</td>");
        sb.append("</tr>");
        //结束
        sb.append("</table>");

        sb.append("<h4>增员信息基本内容:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
//            CollectEmailBO title = dispatchEmails.get(dispatchEmails.size() - 1);
        sb.append("<tr>");
        sb.append("<td>姓名</td>");
        sb.append("<td>员工编号</td>");
        sb.append("<td>地区</td>");
        sb.append("<td>项目组</td>");
        sb.append("<td>岗位</td>");
        sb.append("<td>岗位层级</td>");
        sb.append("<td>身份证号码</td>");
        sb.append("<td>身份证籍贯</td>");
        sb.append("<td>联系方式</td>");
        sb.append("<td>入职时间</td>");
        sb.append("<td>转正时间</td>");
        sb.append("<td>参保公司</td>");
        sb.append("<td>参保地市</td>");
        sb.append("<td>参保户口</td>");
        sb.append("<td>参保类型</td>");
        sb.append("<td>购买方式</td>");
        sb.append("<td>前参保公司</td>");
        sb.append("<td>前参保地市</td>");
        sb.append("<td>已参保年限</td>");
        sb.append("<td>运营商务部意见</td>");
        sb.append("<td>增员时间</td>");
        sb.append("</tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + addEmployeeBO.getName() + "</td>");
        sb.append("<td>" + addEmployeeBO.getEmployeeNum() + "</td>");
        sb.append("<td>" + addEmployeeBO.getCity() + "</td>");
        sb.append("<td>" + addEmployeeBO.getTeam() + "</td>");
        sb.append("<td>" + addEmployeeBO.getJob() + "</td>");
        sb.append("<td>" + addEmployeeBO.getJobLevel() + "</td>");
        sb.append("<td>" + addEmployeeBO.getIdCart() + "</td>");
        sb.append("<td>" + addEmployeeBO.getBorn() + "</td>");
        sb.append("<td>" + addEmployeeBO.getTel() + "</td>");
        sb.append("<td>" + addEmployeeBO.getStartTime() + "</td>");
        sb.append("<td>" + addEmployeeBO.getOfficialTime() + "</td>");
        sb.append("<td>" + addEmployeeBO.getCompany() + "</td>");
        sb.append("<td>" + addEmployeeBO.getSecureCity() + "</td>");
        sb.append("<td>" + addEmployeeBO.getBornLocal() + "</td>");
        sb.append("<td>" + addEmployeeBO.getType() + "</td>");
        sb.append("<td>" + addEmployeeBO.getPayType() + "</td>");
        sb.append("<td>" + addEmployeeBO.getBeforeCompany() + "</td>");
        sb.append("<td>" + addEmployeeBO.getBeforeCity() + "</td>");
        sb.append("<td>" + addEmployeeBO.getInsuredYear() + "</td>");
        sb.append("<td>" + addEmployeeBO.getBusinessAdvice() + "</td>");
        sb.append("<td>" + addEmployeeBO.getSecureTime() + "</td>");
        sb.append("</tr>");
        //结束
        sb.append("</table>");
        sb.append("<hr/>");
        return sb.toString();
    }

    @Override
    public List<AddEmployeeBO> findALL() throws SerException {
        List<AddEmployee> list = super.findAll();
        return BeanTransform.copyProperties(list, AddEmployeeBO.class);
    }

    @Override
    public Long count(AddEmployeeDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //运营商务部审核
    public void commerceAudit(AddEmployeeTO to) throws SerException {
        checkYYIdentity();
        AddEmployee entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setBusinessAdvice(to.getBusinessAdvice());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        Set<String> set = mEmails();
        String[] emails = new String[set.size()];
        emails = set.toArray(emails);
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("运营商务部审核社保增员");
        messageTO.setContent("运营商务部对员工编号为" + entity.getEmployeeNum() + "的" + entity.getName() + "的社保增员审核意见为：" + entity.getBusinessAdvice());
        messageTO.setReceivers(emails);
        messageAPI.send(messageTO);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //总经办确认新增
    public void managerConfirmAdd(AddEmployeeDTO dto, String id) throws SerException {
        checkMIdentity();
        String userToken = RpcTransmit.getUserToken();
        AddEmployee entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        String name = entity.getName();
        Boolean send = dto.getSend();
        if (send) {
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("可新增人员名单");
            messageTO.setContent(name + "可新增社保名单");
            String[] users = dto.getUsers();
            if (users != null) {
                List<String> mails = internalContactsAPI.getEmails(users);
                String[] emails = new String[mails.size()];
                emails = mails.toArray(emails);
                messageTO.setReceivers(emails);
                messageAPI.send(messageTO);
            }
        }
        EmployeeSecureTO employeeSecureTO = new EmployeeSecureTO();
        BeanUtils.copyProperties(entity, employeeSecureTO);
        LocalDate beforeTime = entity.getBeforeTime();
        LocalDate officialTime = entity.getOfficialTime();
        LocalDate secureTime = entity.getSecureTime();
        LocalDate startTime = entity.getStartTime();
        try {
            if (beforeTime != null) {
                employeeSecureTO.setBeforeTime(DateUtil.dateToString(beforeTime));
            }
            employeeSecureTO.setOfficialTime(DateUtil.dateToString(officialTime));
            employeeSecureTO.setSecureTime(DateUtil.dateToString(secureTime));
            employeeSecureTO.setStartTime(DateUtil.dateToString(startTime));
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        employeeSecureTO.setManagerAdvice("通过");
        RpcTransmit.transmitUserToken(userToken);
        employeeSecureSer.save(employeeSecureTO);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //社保管理负责人确认增员
    public void confirmAdd(String id) throws SerException {
        checkSBIdentity();
        String userToken = RpcTransmit.getUserToken();
        AddEmployee entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        BuyTO buyTO = new BuyTO();
        BeanUtils.copyProperties(entity, buyTO);
        if ((entity.getName() != null) && (entity.getEmployeeNum() != null) && (StringUtils.isNotBlank(entity.getEmployeeNum()))) {
            AddEmployee addEmployee = findByNameAndNum(entity.getName(), entity.getEmployeeNum());
            if (addEmployee != null) {
                buyTO.setCity(addEmployee.getSecureCity());
            }
        } else if ((entity.getEmployeeNum() == null) || (StringUtils.isBlank(entity.getEmployeeNum()))) {
            Attached attached = findByAttachedName(entity.getName());
            if (attached != null) {
                buyTO.setCity(attached.getAttachedArrival());
            }
        }
        RpcTransmit.transmitUserToken(userToken);
        buySer.save(buyTO);
        EmployeeSecureBO employeeSecureBO = employeeSecureSer.findBySql(entity.getName(), entity.getEmployeeNum());
        if (employeeSecureBO != null) {
            EmployeeSecureBO bo = employeeSecureSer.findByID(employeeSecureBO.getId());
            EmployeeSecureTO employeeSecureTO = new EmployeeSecureTO();
            BeanUtils.copyProperties(bo, employeeSecureTO);
            employeeSecureTO.setStatus("已增员成功");
            RpcTransmit.transmitUserToken(userToken);
            employeeSecureSer.edit(employeeSecureTO);
        }
        AddEmployeeDTO addEmployeeDTO = new AddEmployeeDTO();
        addEmployeeDTO.getConditions().add(Restrict.eq("year", LocalDate.now().getYear()));
        addEmployeeDTO.getConditions().add(Restrict.eq("month", LocalDate.now().getMonthValue()));
        List<AddEmployee> list = super.findByCis(addEmployeeDTO);
        List<BuyBO> boList = new ArrayList<BuyBO>();
        for (AddEmployee a : list) {
            BuyDTO dto = new BuyDTO();
            dto.getConditions().add(Restrict.eq("name", a.getName()));
            List<BuyBO> buyBOs = buySer.findByDTO(dto);
            if (buyBOs != null && !buyBOs.isEmpty()) {
                boList.add(buyBOs.get(0));
            }
        }
        Set<String> set = yyEmails();
        String[] emails = new String[set.size()];
        emails = set.toArray(emails);
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("本月社保所需表-购买社保人员名单");
        messageTO.setContent(buyHtml(boList));
        messageTO.setReceivers(emails);
        messageAPI.send(messageTO);
    }

    private String buyHtml(List<BuyBO> list) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>本月社保所需表-购买社保人员名单:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
//            CollectEmailBO title = dispatchEmails.get(dispatchEmails.size() - 1);
        sb.append("<tr>");
        sb.append("<td>姓名</td>");
        sb.append("<td>参保公司</td>");
        sb.append("<td>参保地市</td>");
        sb.append("<td>参保户口</td>");
        sb.append("<td>参保类型</td>");
        sb.append("<td>购买方式</td>");
        sb.append("<td>社保状态</td>");
        sb.append("<td>审批状态</td>");
        sb.append("</tr>");

        for (BuyBO buy : list) {
            //拼body部分
            sb.append("<tr>");
            sb.append("<td>" + buy.getName() + "</td>");
            sb.append("<td>" + buy.getCompany() + "</td>");
            sb.append("<td>" + buy.getCity() + "</td>");
            sb.append("<td>" + buy.getBornLocal() + "</td>");
            sb.append("<td>" + buy.getType() + "</td>");
            sb.append("<td>" + buy.getPayType() + "</td>");
            sb.append("<td>" + buy.getStatus() + "</td>");
            sb.append("<td>" + buy.isExamine() + "</td>");
            sb.append("</tr>");
        }
        //结束
        sb.append("</table>");
        return sb.toString();
    }

    /**
     * 通过姓名查找增员前信息
     *
     * @param name 姓名
     * @return
     * @throws SerException
     */
    private BeforeAdd findByName(String name) throws SerException {
        String[] names = new String[]{name};
        List<BeforeAdd> list = null;
        for (String s : names) {
            String sql = "SELECT id FROM " +
                    "secure_beforeadd " +
                    "WHERE name='" + s + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, BeforeAdd.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过姓名和员工编号查找增员信息
     *
     * @param name 姓名
     * @param num  员工编号
     * @return
     * @throws SerException
     */
    private AddEmployee findByNameAndNum(String name, String num) throws SerException {
        String[] names = new String[]{name};
        String[] nums = new String[]{num};
        List<AddEmployee> list = null;
        for (int i = 0; i < names.length; i++) {
            String sql = "SELECT secureCity FROM secure_addemployee " +
                    "WHERE name='" + names[i] + "' AND employeeNum='" + nums[i] + "'";
            String[] fileds = new String[]{"secureCity"};
            list = super.findBySql(sql, AddEmployee.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过挂靠姓名查找挂靠信息
     *
     * @param name 挂靠姓名
     * @return
     * @throws SerException
     */
    private Attached findByAttachedName(String name) throws SerException {
        String[] names = new String[]{name};
        List<Attached> list = null;
        for (String s : names) {
            String sql = "SELECT attachedArrival from secure_attached\n" +
                    "WHERE attachedName='" + s + "'";
            String[] fileds = new String[]{"attachedArrival"};
            list = super.findBySql(sql, Attached.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }
}