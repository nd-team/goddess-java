package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.dto.StaffEntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.staffentry.entity.StaffEntryRegister;
import com.bjike.goddess.staffentry.enums.GuideAddrStatus;
import com.bjike.goddess.staffentry.excel.StaffEntryRegisterExpTemplate;
import com.bjike.goddess.staffentry.excel.StaffEntryRegisterExport;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterEmailTO;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterTO;
import com.bjike.goddess.staffentry.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.enums.UserType;
import com.bjike.goddess.user.to.UserTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

/**
 * 员工入职注册业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffentrySerCache")
@Service
public class StaffEntryRegisterSerImpl extends ServiceImpl<StaffEntryRegister, StaffEntryRegisterDTO> implements StaffEntryRegisterSer {

    @Autowired
    private EntryRegisterSer entryRegisterSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private CommunicationFormworkSer communicationFormworkSer;

    /**
     * 添加等权限 检测模块
     *
     * @param idFlag
     * @throws SerException
     */
    private Boolean checkMoudleIdentity(String idFlag) throws SerException {

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        Boolean flag = true;
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.moudleCusPermission(idFlag);
//            if (!flag) {
//                throw new SerException("你不是相应模块的人员，不能进行操作");
//            }
        }
        return flag;
    }



    /**
     * 查看权限 检测层级
     *
     * @param idFlag
     * @throws SerException
     */
    private Boolean checkLevelIdentity(String idFlag) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        Boolean flag = true;
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(idFlag);
//            if( !flag){
//                throw new SerException("你不是相应层级的人员，不能进行操作");
//            }
        }
        return flag;

    }

    //所有人都有权限
    private Boolean checkAllTrue() throws SerException {
        return true;

    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = checkLevelIdentity("1");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = checkMoudleIdentity("8");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAllTrue = checkAllTrue();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("staffentryregister");
        obj.setDescribesion("用户注册");
        if (flagSee || flagAdd || flagAllTrue) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //入职登记信息
        flagSee = entryRegisterSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("entryregister");
        obj.setDescribesion("入职登记");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //各类模块沟通交流
        flagSee = communicationFormworkSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("communicationformwork");
        obj.setDescribesion("各类模块沟通交流");
        if (flagSee) {
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
                flag = true;
                break;
            case ADD:
                flag = true;
                break;
            case EDIT:
                flag = true;
                break;
            case DELETE:
                flag = checkMoudleIdentity("8");
                break;
            case IMPORT:
                flag = checkMoudleIdentity("8");
                break;
            case EXPORT:
                flag = checkMoudleIdentity("8");
                break;
            case SEE:
                flag = checkLevelIdentity("1");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countStaffEntryRegister(StaffEntryRegisterDTO staffEntryRegisterDTO) throws SerException {
        seachCondi(staffEntryRegisterDTO);
        Long count = null;
        String token = RpcTransmit.getUserToken();
        if (!checkLevelIdentity("1")) {
            RpcTransmit.transmitUserToken(token);
            UserBO userBO = userAPI.currentUser();
            String userId = userBO.getId();
            staffEntryRegisterDTO.getConditions().add(Restrict.eq("userId", userId));
            RpcTransmit.transmitUserToken(token);
            count = super.count(staffEntryRegisterDTO);
        }else{
            count = super.count(staffEntryRegisterDTO);
        }
        return count;
    }

    @Override
    public StaffEntryRegisterBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        StaffEntryRegister staffEntryRegister = super.findById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("id", staffEntryRegister.getUserId()));
        List<UserBO> userList = userAPI.findByCis(userDTO);

        StaffEntryRegisterBO bo = BeanTransform.copyProperties(staffEntryRegister, StaffEntryRegisterBO.class);
        if (userList != null && userList.size() > 0) {
            bo.setEmpNumber(userList.get(0).getEmployeeNumber());
            bo.setUserName(userList.get(0).getUsername());
            bo.setPassword(userList.get(0).getPassword());
        }
        return bo;
    }

    @Override
    public List<StaffEntryRegisterBO> listStaffEntryRegister(StaffEntryRegisterDTO staffEntryRegisterDTO) throws SerException {
        String token = RpcTransmit.getUserToken();
        if (!checkLevelIdentity("1")) {
            RpcTransmit.transmitUserToken(token);
            UserBO userBO = userAPI.currentUser();
            String userId = userBO.getId();
            staffEntryRegisterDTO.getConditions().add(Restrict.eq("userId", userId));
            RpcTransmit.transmitUserToken(token);
        }
        seachCondi(staffEntryRegisterDTO);
        staffEntryRegisterDTO.getSorts().add("createTime=desc");
        List<StaffEntryRegister> list = super.findByCis(staffEntryRegisterDTO, true);
        List<StaffEntryRegisterBO> boList = BeanTransform.copyProperties(list, StaffEntryRegisterBO.class);
        if (list != null && list.size() > 0) {
            for (StaffEntryRegisterBO temp : boList) {
                UserDTO userDTO = new UserDTO();
                userDTO.getConditions().add(Restrict.eq("id", temp.getUserId()));
                RpcTransmit.transmitUserToken(token);
                List<UserBO> userBOList = userAPI.findByCis(userDTO);
                RpcTransmit.transmitUserToken(token);
                if (userBOList != null && userBOList.size() > 0) {
                    UserBO userBO = userBOList.get(0);
                    temp.setEmpNumber(userBO.getEmployeeNumber());
                    temp.setUserName(userBO.getUsername());
                }
            }
        }
        return boList;
    }

    public void seachCondi(StaffEntryRegisterDTO staffEntryRegisterDTO) throws SerException {
        UserDTO userDTO = new UserDTO();
        if (StringUtils.isNotBlank(staffEntryRegisterDTO.getName())) {
            userDTO.getConditions().add(Restrict.eq("username", staffEntryRegisterDTO.getName()));
            List<UserBO> userBOs = userAPI.findByCis(userDTO);
            if (userBOs != null && userBOs.size() > 0) {
                staffEntryRegisterDTO.getConditions().add(Restrict.eq("userId", userBOs.get(0).getId()));
            }
        }
    }

    @Override
    public StaffEntryRegisterBO addStaffEntryRegister(StaffEntryRegisterTO staffEntryRegisterTO) throws SerException {
        String token = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(token);

        if (StringUtils.isBlank(staffEntryRegisterTO.getEmpNumber())) {
            throw new SerException("员工编号不能为空");
        }
        if (StringUtils.isBlank(staffEntryRegisterTO.getUserName())) {
            throw new SerException("员工名不能为空");
        }
        //填用户表
        UserTO userTO = new UserTO();
        userTO.setEmployeeNumber(staffEntryRegisterTO.getEmpNumber());
        userTO.setUsername(staffEntryRegisterTO.getUserName());
        userTO.setPassword(staffEntryRegisterTO.getPassword());
        userTO.setStatus(Status.THAW);
        userTO.setUserType(UserType.EMPLOYEE);
        UserBO userBO = userAPI.add(null, userTO);

        //保存员工入职注册表
        StaffEntryRegister staffEntryRegister = new StaffEntryRegister();
        staffEntryRegister.setDepartment(staffEntryRegisterTO.getDepartment());
        staffEntryRegister.setPosition(staffEntryRegisterTO.getPosition());
        staffEntryRegister.setContactNum(staffEntryRegisterTO.getContactNum());
        staffEntryRegister.setEntryDate(DateUtil.parseDate(staffEntryRegisterTO.getEntryDate()));
        staffEntryRegister.setLodge(staffEntryRegisterTO.getLodge());
        staffEntryRegister.setUseCompanyComputer(staffEntryRegisterTO.getUseCompanyComputer());
        staffEntryRegister.setEntryAddress(staffEntryRegisterTO.getEntryAddress());
        staffEntryRegister.setArea(staffEntryRegisterTO.getArea());
        staffEntryRegister.setRegisterUseNum(staffEntryRegisterTO.getRegisterUseNum());
        staffEntryRegister.setEntry(staffEntryRegisterTO.getEntry());
        staffEntryRegister.setNoEntryCause(staffEntryRegisterTO.getNoEntryCause());
        staffEntryRegister.setWorkEmail(staffEntryRegisterTO.getWorkEmail());
        staffEntryRegister.setWorkEmailPassword(staffEntryRegisterTO.getWorkEmailPassword());
        staffEntryRegister.setCreateTime(LocalDateTime.now());
        staffEntryRegister.setModifyTime(LocalDateTime.now());
        staffEntryRegister.setUserId(userBO.getId());
        super.save(staffEntryRegister);
        return BeanTransform.copyProperties(staffEntryRegister, StaffEntryRegisterBO.class);

    }

    //TODO 当通讯录中的邮箱密码被修改时读取通讯录中的更改密码信息到此表未做 lijuntao

    //TODO 链接数据 由于招聘模块还没有改所有获取根据是否同意入职字段为是的入职信息数据链接未做 lijuntao


    @Override
    public StaffEntryRegisterBO editStaffEntryRegister(StaffEntryRegisterTO staffEntryRegisterTO) throws SerException {
        String token = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(token);

        if (StringUtils.isBlank(staffEntryRegisterTO.getEmpNumber())) {
            throw new SerException("员工编号不能为空");
        }
        if (StringUtils.isBlank(staffEntryRegisterTO.getUserName())) {
            throw new SerException("员工名不能为空");
        }
        if (StringUtils.isBlank(staffEntryRegisterTO.getId())) {
            throw new SerException("id不能为空");
        }


        StaffEntryRegister temp = super.findById(staffEntryRegisterTO.getId());
        temp.setDepartment(staffEntryRegisterTO.getDepartment());
        temp.setPosition(staffEntryRegisterTO.getPosition());
        temp.setContactNum(staffEntryRegisterTO.getContactNum());
        temp.setEntryDate(DateUtil.parseDate(staffEntryRegisterTO.getEntryDate()));
        temp.setLodge(staffEntryRegisterTO.getLodge());
        temp.setUseCompanyComputer(staffEntryRegisterTO.getUseCompanyComputer());
        temp.setEntryAddress(staffEntryRegisterTO.getEntryAddress());
        temp.setEntry(staffEntryRegisterTO.getEntry());
        temp.setNoEntryCause(staffEntryRegisterTO.getNoEntryCause());
        temp.setWorkEmail(staffEntryRegisterTO.getWorkEmail());
        temp.setWorkEmailPassword(staffEntryRegisterTO.getWorkEmailPassword());
        temp.setWorkEmail(staffEntryRegisterTO.getWorkEmail());
        temp.setWorkEmailPassword(staffEntryRegisterTO.getWorkEmailPassword());
        temp.setModifyTime(LocalDateTime.now());

        super.update(temp);

        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("id", temp.getUserId()));
        List<UserBO> userBOList = userAPI.findByCis(userDTO);

        if (userBOList != null && userBOList.size() > 0) {
            RpcTransmit.transmitUserToken(token);
            UserTO userTO = BeanTransform.copyProperties(userBOList.get(0), UserTO.class);
            userTO.setUsername(staffEntryRegisterTO.getUserName());
            userAPI.update(userTO);
        }
        return BeanTransform.copyProperties(temp, StaffEntryRegisterBO.class);
    }


    @Override
    public void delete(String id) throws SerException {
        String token = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(token);

        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        StaffEntryRegister staffEntryRegister = super.findById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("id", staffEntryRegister.getUserId()));
        List<UserBO> userBOList = userAPI.findByCis(userDTO);

        if (userBOList != null && userBOList.size() > 0) {
            for (UserBO userbo : userBOList) {
                RpcTransmit.transmitUserToken(token);
                userAPI.deleteUser(userbo.getId());
            }
        }
        super.remove(id);
    }

    @Override
    public String maxEmpNumber() throws SerException {
        String empNumber = userAPI.maxUserEmpNumber();
        return empNumber;
//        if(moduleAPI.isCheck("organize")){
//            List<UserBO> userBOList = positionDetailUserAPI.findUserListInOrgan();
//
//        }
    }

    @Override
    public void sendAccountToEmp(StaffEntryRegisterEmailTO staffEntryRegisterEmailTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isBlank(staffEntryRegisterEmailTO.getEmpId())) {
            throw new SerException("id不能为空");
        }
        if (StringUtils.isNotBlank(staffEntryRegisterEmailTO.getEmailAccount()) && !Validator.isEmail(staffEntryRegisterEmailTO.getEmailAccount().trim())) {
            throw new SerException("邮箱书写不正确");
        }
        StaffEntryRegister staffEntryRegister = super.findById(staffEntryRegisterEmailTO.getEmpId());
        String userId = staffEntryRegister.getUserId();
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("id", userId));
        List<UserBO> userBOList = userAPI.findByCis(userDTO);

        RpcTransmit.transmitUserToken(userToken);
        if (userBOList != null && userBOList.size() > 0) {
            String userAccount = userBOList.get(0).getEmployeeNumber();
            String password = userBOList.get(0).getPassword();

            //发送员工帐号（编号） 和密码给员工
            String messageContent = "您的入职帐号和密码为: 帐号：" + userAccount + " 密码：密码请让相关人员告知 ,请保管好您的帐号密码! ";
            MessageTO messageTO = new MessageTO();
            messageTO.setContent(messageContent);
            messageTO.setTitle("帐号密码告知");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);
            //定时发送必须写
            messageTO.setSenderId("SYSTEM");
            messageTO.setSenderName("SYSTEM");

            messageTO.setReceivers(new String[]{staffEntryRegisterEmailTO.getEmailAccount()});
            messageAPI.send(messageTO);
        }


    }

    @Override
    public String getMaxEmpNumberByName(String name) throws SerException {
//        if (moduleAPI.isCheck("organize")) {
//            List<UserBO> userBOs = positionDetailUserAPI.findUserListInOrgan();
//            if(!CollectionUtils.isEmpty(userBOs)){}
//        }

        return null;
    }

    @Override
    public byte[] exportExcel() throws SerException {
        checkMoudleIdentity("8");
        List<StaffEntryRegister> list = super.findAll();
        List<StaffEntryRegisterExport> staffEntryRegisterExports = new ArrayList<>();
        for (StaffEntryRegister str : list) {
            StaffEntryRegisterExport excel = BeanTransform.copyProperties(str, StaffEntryRegisterExport.class, "lodge", "useCompanyComputer", "entry", "notice");
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.eq("id", str.getUserId()));
            UserBO userBO = userAPI.findOne(userDTO);
            excel.setLodge(checkBool(str.getLodge()));
            excel.setUseCompanyComputer(checkBool(str.getUseCompanyComputer()));
            excel.setEntry(checkBool(str.getEntry()));
            excel.setNotice(checkBool(str.getNotice()));
            if(userBO!=null){
                excel.setPassword(userBO.getPassword());
                excel.setUserName(userBO.getUsername());
                excel.setEmpNumber(userBO.getEmployeeNumber());
            }
            staffEntryRegisterExports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(staffEntryRegisterExports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<StaffEntryRegisterExpTemplate> staffEntryRegisterExpTemplates = new ArrayList<>();
        StaffEntryRegisterExpTemplate excel = new StaffEntryRegisterExpTemplate();
        excel.setDepartment("研发部");
        excel.setUserName("张三");
        excel.setEmpNumber("IKE100004");
        excel.setContactNum("13698659846");
        excel.setEntryDate("2017-09-12");
        excel.setLodge("是");
        excel.setUseCompanyComputer("是");
        excel.setEntryAddress("广州天河棠下");
        excel.setEntry("是");
        excel.setNoEntryCause("");
        excel.setArea("广州");
        excel.setNotice("否");
        excel.setRegisterUseNum("13698659846");
        excel.setPosition("工程师");
        excel.setWorkEmail("zhansan_aj@163.com");
        excel.setTellStatus("是");
        excel.setWorkEmailPassword("abc123");
        excel.setPassword("abc123");
        staffEntryRegisterExpTemplates.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(staffEntryRegisterExpTemplates, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<StaffEntryRegisterTO> staffEntryRegisterTOS) throws SerException {
        checkMoudleIdentity("8");
        for (StaffEntryRegisterTO staffEntryRegisterTO : staffEntryRegisterTOS) {
            addStaffEntryRegister(staffEntryRegisterTO);
        }
    }

    @Override
    public String findNotisDate(String[] ids) throws SerException {
        StringBuffer sb = new StringBuffer();
        for (String id : ids) {
            StaffEntryRegister staffEntryRegister = super.findById(id);
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.eq("id", staffEntryRegister.getUserId()));
            UserBO userBO = userAPI.findOne(userDTO);
            sb.append((userBO.getUsername() != null ? userBO.getUsername() : " ") + " " + (staffEntryRegister.getContactNum() != null ? staffEntryRegister.getContactNum() : " ") + " " + (staffEntryRegister.getEntryDate() != null ? staffEntryRegister.getEntryDate() : " ") + " " + checkBool(staffEntryRegister.getLodge()) + "住宿 ");
            sb.append(checkBool(staffEntryRegister.getUseCompanyComputer()) + "使用公司电脑 " + (staffEntryRegister.getEntryAddress() != null ? staffEntryRegister.getEntryAddress() : " ") + " " + (staffEntryRegister.getDepartment() != null ? staffEntryRegister.getDepartment() : " ") + " ");
            sb.append((userBO.getEmployeeNumber() != null ? userBO.getEmployeeNumber() : " ") + " " + (staffEntryRegister.getPosition() != null ? staffEntryRegister.getPosition() : " ") + " " + (staffEntryRegister.getWorkEmail() != null ? staffEntryRegister.getWorkEmail() : " ") + ";  ");
        }
        return sb.toString();
    }

    public String checkBool(Boolean bool) throws SerException {
        String name = "否";
        if (bool != null) {
            if (bool) {
                name = "是";
            }
        }
        return name;
    }

    @Override
    public void notis(String content, String[] emails, String[] ids) throws SerException {
        MessageTO messageTO = new MessageTO();
        messageTO.setContent(content);
        messageTO.setTitle("确认提醒");
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setSendType(SendType.EMAIL);
        messageTO.setRangeType(RangeType.SPECIFIED);

        messageTO.setReceivers(emails);
        messageAPI.send(messageTO);

        for (String id : ids) {
            StaffEntryRegister staffEntryRegister = super.findById(id);
            staffEntryRegister.setNotice(true);
            super.update(staffEntryRegister);
        }
    }

    @Override
    public List<String> findEmpNum() throws SerException {
        List<StaffEntryRegister> staffEntryRegisters = super.findAll();
        List<String> empNums = new ArrayList<>();
        if (staffEntryRegisters != null) {
            for (StaffEntryRegister staffEntryRegister : staffEntryRegisters) {
                UserDTO userDTO = new UserDTO();
                userDTO.getConditions().add(Restrict.eq("id", staffEntryRegister.getUserId()));
                UserBO userBO = userAPI.findOne(userDTO);
                empNums.add(userBO.getEmployeeNumber());
            }
        }
        return empNums;
    }

    @Override
    public LinkDateStaffEntryBO findByEmpNum(String empNum) throws SerException {
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("employeeNumber", empNum));
        UserBO userBO = userAPI.findOne(userDTO);
        StaffEntryRegisterDTO staffEntryRegisterDTO = new StaffEntryRegisterDTO();
        staffEntryRegisterDTO.getConditions().add(Restrict.eq("userId", userBO.getId()));
        StaffEntryRegister staffEntryRegister = super.findOne(staffEntryRegisterDTO);
        LinkDateStaffEntryBO linkDateStaffEntryBO = new LinkDateStaffEntryBO();
        linkDateStaffEntryBO.setUserName(userBO.getUsername());
        linkDateStaffEntryBO.setEmpNumber(empNum);
        linkDateStaffEntryBO.setDepartment(staffEntryRegister.getDepartment());
        linkDateStaffEntryBO.setEntryDate(staffEntryRegister.getEntryDate().toString());
        linkDateStaffEntryBO.setArea(staffEntryRegister.getArea());
        linkDateStaffEntryBO.setPosition(staffEntryRegister.getPosition());
        return linkDateStaffEntryBO;
    }

    @Override
    public List<String> findAllArea() throws SerException {
        List<StaffEntryRegister> list = super.findAll();
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (StaffEntryRegister model : list) {
            String projectName = model.getArea();
            if (StringUtils.isNotBlank(model.getArea())) {
                set.add(projectName);
            }
        }
        return new ArrayList<>(set);
    }


    @Override
    public List<String> findDepByArea(String area) throws SerException {
        StaffEntryRegisterDTO staffEntryRegisterDTO = new StaffEntryRegisterDTO();
        staffEntryRegisterDTO.getConditions().add(Restrict.eq("area", area));
        List<StaffEntryRegister> list = super.findByCis(staffEntryRegisterDTO);
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (StaffEntryRegister model : list) {
            String projectName = model.getDepartment();
            if (StringUtils.isNotBlank(model.getDepartment())) {
                set.add(projectName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findDepartment() throws SerException {
        List<StaffEntryRegister> list = super.findAll();
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (StaffEntryRegister model : list) {
            String department = model.getDepartment();
            if (StringUtils.isNotBlank(model.getDepartment())) {
                set.add(department);
            }
        }
        return new ArrayList<>(set);
    }

    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        String endTime = e.toString();
        String[] time = new String[]{start, endTime};
        return time;
    }

    @Override
    public List<EntrySummaryBO> summaDay(String summationDate) throws SerException {
        if (StringUtils.isBlank(summationDate)) {
            summationDate = LocalDate.now().toString();
        }
        String[] date = new String[]{summationDate, summationDate};
        return totalMethod(date);
    }

    @Override
    public List<EntrySummaryBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);

        return totalMethod(date);
    }

    @Override
    public List<EntrySummaryBO> summaMonth(Integer year, Integer month) throws SerException {
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String[] date = new String[]{startDate, endDate};
        return totalMethod(date);
    }

    @Override
    public List<EntrySummaryBO> summaTotal(String endDate) throws SerException {
        endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        String sql = "select min(entryDate) as entryDate from  " + getTableName(StaffEntryRegister.class);
        List<Object> objects = super.findBySql(sql);
        //获取用户注册中的最小入职时间
        String startDate = "";
        if (objects != null && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
        }
        String sql2 = "select min(updateTime) as updateTime from  " + getTableName(EntryRegister.class);
        List<Object> objects2 = super.findBySql(sql);
        //获取入职登记中的最小更新时间
        String startDate2 = "";
        if (objects != null && objects.size() > 0) {
            startDate2 = String.valueOf(objects.get(0));
        }
        String[] date1 = new String[]{startDate, endDate};
        String[] date2 = new String[]{startDate2, endDate};

        List<EntrySummaryBO> entrySummaryBOS = new ArrayList<>();
        List<String> areas = findAllArea();
        if (areas != null && areas.size() > 0) {
            for (String area : areas) {
                List<String> departments = findDepByArea(area);
                if (departments != null && departments.size() > 0) {
                    for (String department : departments) {
                        EntrySummaryBO entrySummaryBO = new EntrySummaryBO();
                        entrySummaryBO.setArea(area);
                        entrySummaryBO.setDepartment(department);
                        //TODO 由于招聘需求未明确修改所有本汇总字段计划入职人数未拿
                        entrySummaryBO.setPlanInductionNum(0);
                        entrySummaryBO.setRegistrationNum(registrationNum(date1, area, department));//注册用户数
                        entrySummaryBO.setNoticeNum(noticeNum(date1, area, department));//通告数
                        entrySummaryBO.setEntryRegistrationNum(entryRegistrationNum(date2, area, department));//新增入职登记信息数
                        entrySummaryBOS.add(entrySummaryBO);
                    }
                }
            }
        }
        return entrySummaryBOS;
    }

    public List<EntrySummaryBO> totalMethod(String[] date) throws SerException {
        List<EntrySummaryBO> entrySummaryBOS = new ArrayList<>();
        List<String> areas = findAllArea();
        if (areas != null && areas.size() > 0) {
            for (String area : areas) {
                List<String> departments = findDepByArea(area);
                if (departments != null && departments.size() > 0) {
                    for (String department : departments) {
                        EntrySummaryBO entrySummaryBO = new EntrySummaryBO();
                        entrySummaryBO.setArea(area);
                        entrySummaryBO.setDepartment(department);
                        //TODO 由于招聘需求未明确修改所有本汇总字段计划入职人数未拿
                        entrySummaryBO.setPlanInductionNum(0);
                        entrySummaryBO.setRegistrationNum(registrationNum(date, area, department));//注册用户数
                        entrySummaryBO.setNoticeNum(noticeNum(date, area, department));//通告数
                        entrySummaryBO.setEntryRegistrationNum(entryRegistrationNum(date, area, department));//新增入职登记信息数
                        entrySummaryBOS.add(entrySummaryBO);
                    }
                }
            }
        }
        return entrySummaryBOS;
    }

    //注册用户数（入职人数）
    public Integer registrationNum(String[] date, String area, String dep) throws SerException {
        Integer num = 0;
        StaffEntryRegisterDTO staffEntryRegisterDTO = new StaffEntryRegisterDTO();
        staffEntryRegisterDTO.getConditions().add(Restrict.between("entryDate", date));
        staffEntryRegisterDTO.getConditions().add(Restrict.eq("area", area));
        staffEntryRegisterDTO.getConditions().add(Restrict.eq("department", dep));
        staffEntryRegisterDTO.getConditions().add(Restrict.eq("entry", true));
        List<StaffEntryRegister> staffEntryRegisters = super.findByCis(staffEntryRegisterDTO);
        if (staffEntryRegisters != null && staffEntryRegisters.size() > 0) {
            num = staffEntryRegisters.size();
        }
        return num;
    }

    //通告数
    public Integer noticeNum(String[] date, String area, String dep) throws SerException {
        Integer num = 0;
        StaffEntryRegisterDTO staffEntryRegisterDTO = new StaffEntryRegisterDTO();
        staffEntryRegisterDTO.getConditions().add(Restrict.between("entryDate", date));
        staffEntryRegisterDTO.getConditions().add(Restrict.eq("area", area));
        staffEntryRegisterDTO.getConditions().add(Restrict.eq("department", dep));
        staffEntryRegisterDTO.getConditions().add(Restrict.eq("notice", true));
        List<StaffEntryRegister> staffEntryRegisters = super.findByCis(staffEntryRegisterDTO);
        if (staffEntryRegisters != null && staffEntryRegisters.size() > 0) {
            num = staffEntryRegisters.size();
        }
        return num;
    }

    //新增入职登记信息数
    public Integer entryRegistrationNum(String[] date, String area, String dep) throws SerException {
        Integer num = 0;
        EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
        entryRegisterDTO.getConditions().add(Restrict.between("updateTime", date));
        entryRegisterDTO.getConditions().add(Restrict.eq("area", area));
        entryRegisterDTO.getConditions().add(Restrict.eq("department", dep));
        List<EntryRegister> entryBasicInfos = entryRegisterSer.findByCis(entryRegisterDTO);
        if (entryBasicInfos != null && entryBasicInfos.size() > 0) {
            num = entryBasicInfos.size();
        }
        return num;
    }

    @Override
    public OptionBO figureShowDay(String summationDate) throws SerException {
        if (StringUtils.isBlank(summationDate)) {
            summationDate = LocalDate.now().toString();
        }
        String text_1 = "当天入职情况统计(" + summationDate + ")";
        String[] date = new String[]{summationDate, summationDate};
        return figureTotalMethod(date, text_1);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String text_1 = "入职情况周统计" + "(" + date[0] + "-" + date[1] + ")";
        return figureTotalMethod(date, text_1);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
//        checkPermission();
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String[] date = new String[]{startDate, endDate};
        String text_1 = "入职情况月统计" + "(" + year + "-" + month + ")";
        return figureTotalMethod(date, text_1);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        String sql = "select min(entryDate) as entryDate from  " + getTableName(StaffEntryRegister.class);
        List<Object> objects = super.findBySql(sql);
        //获取用户注册中的最小入职时间
        String startDate = "";
        if (objects != null && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
        }
        String[] date = new String[]{startDate,endDate};
        String text_1 = "入职情况累计统计" + "(累计)";
        return figureTotalMethod(date, text_1);
    }


    public OptionBO figureTotalMethod(String[] date, String text_1) throws SerException {
        List<FigureShowDateBO> figureShowDateBOS = new ArrayList<>();
        List<String> departments = findDepartment();
        if (departments != null && departments.size() > 0) {
            for (String department : departments) {
                FigureShowDateBO figureShowDateBO = new FigureShowDateBO();
                figureShowDateBO.setDepartment(department);
                //TODO 由于招聘需求未明确修改所有本汇总字段计划入职人数未拿
                figureShowDateBO.setPlanInductionNum(0);//计划入职人数
                Integer num = 0;
                StaffEntryRegisterDTO staffEntryRegisterDTO = new StaffEntryRegisterDTO();
                staffEntryRegisterDTO.getConditions().add(Restrict.between("entryDate", date));
                staffEntryRegisterDTO.getConditions().add(Restrict.eq("department", department));
                staffEntryRegisterDTO.getConditions().add(Restrict.eq("entry", true));
                List<StaffEntryRegister> staffEntryRegisters = super.findByCis(staffEntryRegisterDTO);
                if (staffEntryRegisters != null && staffEntryRegisters.size() > 0) {
                    num = staffEntryRegisters.size();
                }
                figureShowDateBO.setRegistrationNum(num);//注册用户数
                figureShowDateBOS.add(figureShowDateBO);
            }
            FigureShowDateBO figureShowDateBO1 = new FigureShowDateBO();
            Integer planInductionNum = figureShowDateBOS.stream().mapToInt(FigureShowDateBO::getPlanInductionNum).sum();//计划入职人数总和;
            Integer registrationNum = figureShowDateBOS.stream().mapToInt(FigureShowDateBO::getRegistrationNum).sum();//注册人数总和;
            figureShowDateBO1.setDepartment("合计");
            figureShowDateBO1.setPlanInductionNum(planInductionNum);
            figureShowDateBO1.setRegistrationNum(registrationNum);
            figureShowDateBOS.add(figureShowDateBO1);
        }
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"计划入职人数", "注册用户数（入职人数）"};
        legendBO.setData(text_2);
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();//1

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
//        tooltipBO.setTrigger("axis");

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (figureShowDateBOS != null && figureShowDateBOS.size() > 0) {
            List<Integer> planInductionNums = new ArrayList<>();
            List<Integer> registrationNums = new ArrayList<>();
            for (FigureShowDateBO figureShowDateBO : figureShowDateBOS) {
                text_list_3.add(figureShowDateBO.getDepartment());

                //柱状图数据
                planInductionNums.add(figureShowDateBO.getPlanInductionNum());
                registrationNums.add(figureShowDateBO.getRegistrationNum());
            }
            Integer[] planInductionNum_str = new Integer[planInductionNums.size()];
            planInductionNum_str = planInductionNums.toArray(planInductionNum_str);
            Integer[] registrationNums_str = new Integer[registrationNums.size()];
            registrationNums_str = registrationNums.toArray(registrationNums_str);
            SeriesBO seriesBO1 = new SeriesBO();
            seriesBO1.setName("计划入职人数");
            seriesBO1.setType("bar");
            seriesBO1.setData(planInductionNum_str);
            seriesBOList.add(seriesBO1);
            SeriesBO seriesBO2 = new SeriesBO();
            seriesBO2.setName("注册用户数（入职人数）");
            seriesBO2.setType("bar");
            seriesBO2.setData(registrationNums_str);
            seriesBOList.add(seriesBO2);
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    @Override
    public BrokenOptionBO brokenShowYear(Integer year) throws SerException {
        year = year ==null?(LocalDate.now().getYear()):year;
        List<BrokenLineDateBO> brokenLineDateBOS = new ArrayList<>();
        for (int i=1;i<=12;i++){
            String startDate = DateUtil.dateToString(LocalDate.of(year,i,1));
            String endDate = DateUtil.dateToString(LocalDate.of(year,i,DateUtil.getDayByDate(year,i)));
            String[] date = new String[]{startDate,endDate};

            StaffEntryRegisterDTO staffEntryRegisterDTO = new StaffEntryRegisterDTO();
            staffEntryRegisterDTO.getConditions().add(Restrict.between("entryDate", date));
            staffEntryRegisterDTO.getConditions().add(Restrict.eq("entry", true));
            List<StaffEntryRegister> staffEntryRegisters = super.findByCis(staffEntryRegisterDTO);
            Integer registrationNum = 0;//注册人数
            Integer planInductionNum = 0;//计划入职人数
            if(staffEntryRegisters!=null && staffEntryRegisters.size()>0){
                registrationNum = staffEntryRegisters.size();
            }
            BrokenLineDateBO brokenLineDateBO = new BrokenLineDateBO();
            brokenLineDateBO.setYearMonth(year+"年"+i+"月");
            //TODO 由于招聘需求未明确修改所有本汇总字段计划入职人数未拿
            brokenLineDateBO.setPlanInductionNum(0);
            brokenLineDateBO.setRegistrationNum(registrationNum);
            brokenLineDateBOS.add(brokenLineDateBO);
        }
        BrokenLineDateBO brokenLineDateBO1 = new BrokenLineDateBO();
        Integer totalRegistrationNum = brokenLineDateBOS.stream().mapToInt(BrokenLineDateBO::getRegistrationNum).sum();
        Integer totalPlanInductionNum = brokenLineDateBOS.stream().mapToInt(BrokenLineDateBO::getPlanInductionNum).sum();
        brokenLineDateBO1.setYearMonth("合计");
        brokenLineDateBO1.setPlanInductionNum(totalPlanInductionNum);
        brokenLineDateBO1.setRegistrationNum(totalRegistrationNum);
        brokenLineDateBOS.add(brokenLineDateBO1);


        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(year+"年度入职人数波动情况");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"计划入职人数", "注册用户数（入职人数）"};
        legendBO.setData(text_2);
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(false);
        List<String> text_list_3 = new ArrayList<>();//1

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);



        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (brokenLineDateBOS != null && brokenLineDateBOS.size() > 0) {
            List<Integer> planInductionNums = new ArrayList<>();
            List<Integer> registrationNums = new ArrayList<>();
            for (BrokenLineDateBO brokenLineDateBO : brokenLineDateBOS) {
                text_list_3.add(brokenLineDateBO.getYearMonth());

                //柱状图数据
                planInductionNums.add(brokenLineDateBO.getPlanInductionNum());
                registrationNums.add(brokenLineDateBO.getRegistrationNum());
            }
            Integer[] planInductionNum_str = new Integer[planInductionNums.size()];
            planInductionNum_str = planInductionNums.toArray(planInductionNum_str);
            Integer[] registrationNums_str = new Integer[registrationNums.size()];
            registrationNums_str = registrationNums.toArray(registrationNums_str);
            SeriesBO seriesBO1 = new SeriesBO();
            seriesBO1.setName("计划入职人数");
            seriesBO1.setType("line");
            seriesBO1.setData(planInductionNum_str);
            seriesBOList.add(seriesBO1);
            SeriesBO seriesBO2 = new SeriesBO();
            seriesBO2.setName("注册用户数（入职人数）");
            seriesBO2.setType("line");
            seriesBO2.setData(registrationNums_str);
            seriesBOList.add(seriesBO2);
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        BrokenOptionBO brokenOptionBO = new BrokenOptionBO();
        brokenOptionBO.setTitle(titleBO);
        brokenOptionBO.setTooltip(tooltipBO);
        brokenOptionBO.setLegend(legendBO);
        brokenOptionBO.setxAxis(xAxisBO);
        brokenOptionBO.setyAxis(yAxisBO);

        brokenOptionBO.setSeries(text_4);
        return brokenOptionBO;
    }
}