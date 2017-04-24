package com.bjike.goddess.function.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.function.service.UserFunctionSer;
import com.bjike.goddess.function.to.UserFunctionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户功能业务接口实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 01:56 ]
 * @Description: [ 用户功能业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("userFunctionApiImpl")
public class UserFunctionApiImpl implements UserFunctionAPI {
    @Autowired
    private UserFunctionSer userFunctionSer;

    @Override
    public void add(UserFunctionTO userFunctionTO) throws SerException {
        userFunctionSer.add(userFunctionTO);
    }

    @Override
    public void delete(String functionId) throws SerException {
        userFunctionSer.delete(functionId);
    }
}