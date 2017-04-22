package com.bjike.goddess.function.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.function.dto.UserFunctionDTO;
import com.bjike.goddess.function.entity.Function;
import com.bjike.goddess.function.entity.UserFunction;
import com.bjike.goddess.function.to.UserFunctionTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 用户功能业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 01:56 ]
 * @Description: [ 用户功能业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "functionSerCache")
@Service
public class UserFunctionSerImpl extends ServiceImpl<UserFunction, UserFunctionDTO> implements UserFunctionSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private FunctionSer functionSer;

    @Override
    public void add(UserFunctionTO to) throws SerException {
        String userId = userAPI.currentUser().getId();
        UserFunctionDTO dto = new UserFunctionDTO();
        dto.getConditions().add(Restrict.eq("userId", userId));
        dto.getConditions().add(Restrict.eq("function.id", to.getFunctionId()));
        if(null!=super.findOne(dto)){
            dto = new UserFunctionDTO();
            dto.getConditions().add(Restrict.eq("userId", userId));
            Integer seq = super.count(dto).intValue() + 1;
            UserFunction userFunction = new UserFunction();
            userFunction.setUserId(userId);
            Function function = functionSer.findById(to.getFunctionId());
            userFunction.setFunction(function);
            userFunction.setSeq(seq);
            super.save(userFunction);
        }else {
            throw new SerException("该用户已拥有该功能");
        }

    }

    @Override
    public void delete(String functionId) throws SerException {
        String userId = userAPI.currentUser().getId();
        UserFunctionDTO dto = new UserFunctionDTO();
        dto.getConditions().add(Restrict.eq("userId", userId));
        dto.getConditions().add(Restrict.eq("function.id", functionId));
        UserFunction userFunction = super.findOne(dto);
        super.remove(userFunction);
    }
}