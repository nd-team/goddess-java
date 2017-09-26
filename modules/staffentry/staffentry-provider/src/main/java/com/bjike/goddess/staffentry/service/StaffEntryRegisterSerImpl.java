package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.staffentry.bo.StaffEntryRegisterBO;
import com.bjike.goddess.staffentry.dto.StaffEntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.StaffEntryRegister;
import com.bjike.goddess.staffentry.enums.GuideAddrStatus;
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
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private EntryBasicInfoSer entryBasicInfoSer;
    @Autowired
    private EntryRegisterSer entryRegisterSer;
    @Autowired
    private SalaryConfirmRecordSer salaryConfirmRecordSer;
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


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = checkLevelIdentity("1");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = checkLevelIdentity("8");
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("staffentryregister");
        obj.setDescribesion("用户注册");
        if (flagSee || flagAdd) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        //入职基本信息
        flagSee = entryBasicInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("entrybasicinfo");
        obj.setDescribesion("入职基本信息");
        if (flagSee) {
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

        //薪资确认
        flagSee = salaryConfirmRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("salaryConfirmRecord");
        obj.setDescribesion("薪资确认");
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
                flag = checkLevelIdentity("1");
                break;
            case ADD:
                flag = checkLevelIdentity("8");
                break;
            case EDIT:
                flag = checkLevelIdentity("8");
                break;
            case DELETE:
                flag = checkLevelIdentity("8");
                break;
            case IMPORT:
                flag = checkLevelIdentity("8");
                break;
            case EXPORT:
                flag = checkLevelIdentity("8");
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
        Long count = super.count(staffEntryRegisterDTO);
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
        RpcTransmit.transmitUserToken(token);
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
        staffEntryRegister.setProjectGroup(staffEntryRegisterTO.getProjectGroup());
        staffEntryRegister.setRole(staffEntryRegisterTO.getRole());
        staffEntryRegister.setWorkEmail(staffEntryRegisterTO.getWorkEmail());
        staffEntryRegister.setWorkEmailPassword(staffEntryRegisterTO.getWorkEmailPassword());
        staffEntryRegister.setCreateTime(LocalDateTime.now());
        staffEntryRegister.setModifyTime(LocalDateTime.now());
        staffEntryRegister.setUserId(userBO.getId());
        staffEntryRegister.setTellStatus( "否");
        super.save(staffEntryRegister);
        return BeanTransform.copyProperties(staffEntryRegister, StaffEntryRegisterBO.class);
    }

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
        temp.setProjectGroup(staffEntryRegisterTO.getProjectGroup());
        temp.setRole(staffEntryRegisterTO.getRole());
        temp.setWorkEmail(staffEntryRegisterTO.getWorkEmail());
        temp.setWorkEmailPassword(staffEntryRegisterTO.getWorkEmailPassword());
        temp.setModifyTime(LocalDateTime.now());
        temp.setTellStatus( (StringUtils.isBlank(temp.getTellStatus())||"否".equals(temp.getTellStatus()))?"否":"是");

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


            staffEntryRegister.setTellStatus("是");
            staffEntryRegister.setModifyTime(LocalDateTime.now());
            super.update( staffEntryRegister );
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
}