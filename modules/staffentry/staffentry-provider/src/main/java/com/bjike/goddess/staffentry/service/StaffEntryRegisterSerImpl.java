package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.StaffEntryRegisterBO;
import com.bjike.goddess.staffentry.dto.StaffEntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.StaffEntryRegister;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.enums.UserType;
import com.bjike.goddess.user.to.UserTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检测模块
     * @param idFlag
     * @throws SerException
     */
    private void checkMoudleIdentity(String idFlag) throws SerException{

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        Boolean flag = false;
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.moudleCusPermission(idFlag);
            if( !flag){
                throw new SerException("你不是相应模块的人员，不能进行操作");
            }
        }

    }


    /**
     * 检测层级
     * @param idFlag
     * @throws SerException
     */
    private void checkLevelIdentity(String idFlag) throws SerException{
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        Boolean flag = false;
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(idFlag);
            if( !flag){
                throw new SerException("你不是相应层级的人员，不能进行操作");
            }
        }


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
        UserDTO userDTO  = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("id",staffEntryRegister.getUserId()));
        List<UserBO> userList = userAPI.findByCis( userDTO  );

        StaffEntryRegisterBO bo = BeanTransform.copyProperties(staffEntryRegister, StaffEntryRegisterBO.class);
        if( userList!= null && userList.size()>0 ){
            bo.setEmpNumber( userList.get(0).getEmployeeNumber());
            bo.setUserName( userList.get(0).getUsername());
            bo.setPassword( userList.get(0).getPassword());
        }
        return bo;
    }

    @Override
    public List<StaffEntryRegisterBO> listStaffEntryRegister(StaffEntryRegisterDTO staffEntryRegisterDTO) throws SerException {

        String token = RpcTransmit.getUserToken();
        checkLevelIdentity("1");
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
        checkMoudleIdentity("9");
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

        //填自己的表
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
        super.save(staffEntryRegister);
        return BeanTransform.copyProperties(staffEntryRegister, StaffEntryRegisterBO.class);
    }

    @Override
    public StaffEntryRegisterBO editStaffEntryRegister(StaffEntryRegisterTO staffEntryRegisterTO) throws SerException {
        String token = RpcTransmit.getUserToken();
        checkMoudleIdentity("9");
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
        checkMoudleIdentity("9");
        RpcTransmit.transmitUserToken(token);

        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        StaffEntryRegister staffEntryRegister = super.findById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("id", staffEntryRegister.getUserId()));
        List<UserBO> userBOList = userAPI.findByCis(userDTO);

        if (userBOList != null && userBOList.size() > 0) {
            for(UserBO userbo : userBOList){
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
    }
}