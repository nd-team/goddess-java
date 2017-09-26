package com.bjike.goddess.secure.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.secure.bo.SecureCartBO;
import com.bjike.goddess.secure.dto.SecureCartDTO;
import com.bjike.goddess.secure.entity.SecureCart;
import com.bjike.goddess.secure.entity.SecureCase;
import com.bjike.goddess.secure.enums.GuideAddrStatus;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.SecureCartTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.util.parsing.combinator.testing.Str;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 社保卡基本信息业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 01:44 ]
 * @Description: [ 社保卡基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class SecureCartSerImpl extends ServiceImpl<SecureCart, SecureCartDTO> implements SecureCartSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
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

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public SecureCartBO save(SecureCartTO to) throws SerException {
        checkAddIdentity();
        SecureCart secureCart = BeanTransform.copyProperties(to, SecureCart.class, true);
        super.save(secureCart);
        return BeanTransform.copyProperties(secureCart, SecureCartBO.class);
    }

//    @Override
//    @Transactional(rollbackFor = {SerException.class})
//    public void quartz() throws SerException {
////        ScheduleJobGroupTO scheduleJobGroupTO = new ScheduleJobGroupTO();
////        scheduleJobGroupTO.setName("定时查看今天是否为16号工作组");
////        scheduleJobGroupTO.setEnable(true);
////        ScheduleJobGroupBO scheduleJobGroupBO = scheduleJobGroupAPI.add(null, scheduleJobGroupTO);
//        ScheduleJobTO scheduleJobTO = new ScheduleJobTO();
//        scheduleJobTO.setClazz("com.bjike.goddess.secure.api.SecureCartAPI");
//        scheduleJobTO.setName("定时查看今天是否为16号");
//        scheduleJobTO.setMethod("send");
//        scheduleJobTO.setExpression("0 0 */12 * * ?");   //每12个小时执行一次
//        scheduleJobTO.setDescription("查看今天是否为16号，并通知综合资源部福利模块");
//        scheduleJobTO.setEnable(true);
//        scheduleJobTO.setAddress("localhost:51101");
//        scheduleJobTO.setScheduleJobGroupId("eb33c9b6-ed33-4596-be70-b3eb6aa81f1d");
//        scheduleJobAPI.add(scheduleJobTO);
//    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(SecureCartTO to) throws SerException {
        checkAddIdentity();
        SecureCart secureCart = super.findById(to.getId());
        LocalDateTime a = secureCart.getCreateTime();
        secureCart = BeanTransform.copyProperties(to, SecureCart.class, true);
        secureCart.setCreateTime(a);
        secureCart.setModifyTime(LocalDateTime.now());
        super.update(secureCart);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<SecureCartBO> list(SecureCartDTO dto) throws SerException {
//        Set<EmployeeSecure> set = allEmployeeSecures();
//        List<EmployeeSecure> list = employeeSecureSer.findAll();
//        for (EmployeeSecure employeeSecure : list) {
//            if (set.size() != 0) {
//                for (EmployeeSecure s : set) {
//                    if ((employeeSecure.getStatus() != null) && (!employeeSecure.getStatus().equals("")) && (!employeeSecure.getStatus().equals("已减员成功"))) {
//                        if (!(s.getId().equals(employeeSecure.getId()))) {
//                            SecureCart secureCart = new SecureCart();
//                            secureCart.setEmployeeSecure(employeeSecure);
//                            super.save(secureCart);
//                        }
//                    }
//                }
//            } else {
//                if ((employeeSecure.getStatus() != null) && (!employeeSecure.getStatus().equals("")) && (!employeeSecure.getStatus().equals("已减员成功"))) {
//                    SecureCart secureCart = new SecureCart();
//                    secureCart.setEmployeeSecure(employeeSecure);
//                    super.save(secureCart);
//                }
//            }
//        }
//        Set<String> ss = allEmployeeSecureIds();
//        List<EmployeeSecure> l = null;
//        for (String s : ss) {
//            String sql = "SELECT id,name,employeeNum,arrival from secure_employeesecure\n" +
//                    "where id='" + s + "'";
//            String[] fields = new String[]{"id", "name", "employeeNum", "arrival"};
//            l = super.findBySql(sql, EmployeeSecure.class, fields);
//        }
//        if (l != null) {
//            for (EmployeeSecure e : l) {
//                for (String s : ss) {
//                    if (e.getId().equals(s)) {
//                        SecureCart secureCart = super.findById(find(new String[]{s}).getId());
//                        secureCart.setName(e.getName());
//                        secureCart.setEmployeeId(e.getEmployeeNum());
//                        secureCart.setArrival(e.getArrival());
//                        super.update(secureCart);
//                    }
//                }
//            }
//        }
        checkSeeIdentity();
        List<SecureCart> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, SecureCartBO.class);
    }

    @Override
    public SecureCartBO findByID(String id) throws SerException {
        SecureCart secureCart = super.findById(id);
        if (secureCart == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(secureCart, SecureCartBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    //每12小时执行一次
    public void send() throws SerException {
        int day = LocalDate.now().getDayOfMonth();
        if (day == 16) {
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("请跟进社保卡管理");
            messageTO.setContent("今天为16号，请及时跟进社保卡管理");
            messageTO.setReceivers(flEmails());
            messageTO.setSenderId("SYSTEM");
            messageTO.setSenderName("SYSTEM");
            if (flEmails() != null && flEmails().length > 0) {
                messageAPI.send(messageTO);
            }
        }
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
    public Long count(SecureCartDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public Set<String> allName() throws SerException {
        Set<String> set = new HashSet<>();
        List<SecureCart> list = super.findAll();
        for(SecureCart entity:list){
            set.add(entity.getName());
        }
        return set;
    }
    //    /**
//     * 查找所有员工社保信息id
//     *
//     * @return class String
//     * @throws SerException
//     */
//    private Set<String> allEmployeeSecureIds() throws SerException {
//        Set<EmployeeSecure> s = allEmployeeSecures();
//        Set<String> set = new HashSet<String>();
//        for (EmployeeSecure e : s) {
//            set.add(e.getId());
//        }
//        return set;
//    }
//
//    /**
//     * 查找所有员工社保信息
//     *
//     * @return class String
//     * @throws SerException
//     */
//    private Set<EmployeeSecure> allEmployeeSecures() throws SerException {
//        List<SecureCart> list = super.findAll();
//        Set<EmployeeSecure> set = new HashSet<EmployeeSecure>();
//        for (SecureCart secureCart : list) {
//            set.add(secureCart.getEmployeeSecure());
//        }
//        return set;
//    }
//
//    /**
//     * 通过员工社保信息id查找社保卡信息
//     *
//     * @param employeeSecureIds 员工社保信息id数组
//     * @return class SecureCart
//     * @throws SerException
//     */
//    private SecureCart find(String[] employeeSecureIds) throws SerException {
//        List<SecureCart> list = null;
//        for (String id : employeeSecureIds) {
//            String sql = "SELECT id,name from secure_securecart\n" +
//                    "where employeeSecure_id='" + id + "'";
//            list = super.findBySql(sql, SecureCart.class, new String[]{"id", "name"});
//        }
//        if ((list != null) && (list.size() != 0)) {
//            return list.get(0);
//        }
//        return null;
//    }
}