package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.service.UserSer;
import com.bjike.goddess.user.to.UserTO;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 15:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("userApiImpl")
public class UserApiImpl implements UserAPI {
    @Autowired
    private UserSer userSer;


    @Override
    public String publicKey() throws SerException {
        return userSer.publicKey();

    }

    @Override
    public String privateKey() throws SerException {
        return userSer.privateKey();

    }


    @Override
    public UserBO currentUser() throws SerException {
        return userSer.currentUser();
    }


    @Override
    public UserBO currentUser(String userToken) throws SerException {
        return userSer.currentUser(userToken);
    }

    @Override
    public String currentSysNO(String userToken) throws SerException {
        return userSer.currentSysNO(userToken);
    }

    @Override
    public String currentSysNO() throws SerException {
        return userSer.currentSysNO();
    }

    @Override
    public UserBO add(TransactionContext context,UserTO userTO) throws SerException {
        return userSer.add(context,userTO);
    }

    @Override
    public void update(UserTO userTO) throws SerException {
        userSer.update(userTO);
    }

    @Override
    public void updatePassword(UserTO userTO) throws SerException {
        userSer.updatePassword( userTO );
    }

    @Override
    public UserBO findByUsername(String username) throws SerException {
        return userSer.findByUsername(username);
    }

    @Override
    public UserBO findByNickname(String nickname) throws SerException {
        return userSer.findByNickname(nickname);
    }

    @Override
    public UserBO findByPhone(String phone) throws SerException {
        return userSer.findByPhone(phone);
    }

    @Override
    public UserBO findByAccountNumber(String accountNumber) throws SerException {
        return userSer.findByAccountNumber(accountNumber);
    }

    @Override
    public List<UserBO> findByCis(UserDTO dto) throws SerException {
        return BeanTransform.copyProperties(userSer.findByCis(dto), UserBO.class);
    }

    @Override
    public UserBO findOne(UserDTO dto) throws SerException {
        return BeanTransform.copyProperties(userSer.findOne(dto), UserBO.class);
    }

    @Override
    public List<UserBO> findByGroup(String... groups) throws SerException {
        return userSer.findByGroup(groups);
    }

    @Override
    public List<UserBO> findAllUser() throws SerException {
        return userSer.findAllUser();
    }

    @Override
    public List<UserBO> findUserByPage(UserDTO dto) throws SerException {
        return userSer.findUserByPage(dto);
    }

    @Override
    public UserBO updateUser(UserTO userTO) throws SerException {
        return userSer.updateUser(userTO);
    }

    @Override
    public void deleteUser(String id) throws SerException {
        userSer.deleteUser(id);
    }

    @Override
    public String maxUserEmpNumber() throws SerException {
        return userSer.maxUserEmpNumber();
    }

    @Override
    public String findNameById(String id) throws SerException {
        return userSer.findNameById(id);
    }

    @Override
    public List<UserBO> findByDept(String... departmentId) throws SerException {
        return userSer.findByDept(departmentId);
    }
}
