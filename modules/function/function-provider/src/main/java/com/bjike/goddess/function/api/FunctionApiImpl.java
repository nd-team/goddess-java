package com.bjike.goddess.function.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.function.bo.FunctionBO;
import com.bjike.goddess.function.entity.Function;
import com.bjike.goddess.function.enums.FunctionType;
import com.bjike.goddess.function.service.FunctionSer;
import com.bjike.goddess.function.to.FunctionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模块功能业务接口实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 11:07 ]
 * @Description: [ 模块功能业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("functionApiImpl")
public class FunctionApiImpl implements FunctionAPI {
    @Autowired
    private FunctionSer functionSer;

    @Override
    public FunctionBO add(FunctionTO functionTO) throws SerException {
        return functionSer.add(functionTO);
    }

    @Override
    public void edit(FunctionTO functionTO) throws SerException {
        functionSer.edit(functionTO);
    }

    @Override
    public void delete(String id) throws SerException {
        functionSer.delete(id);
    }

    @Override
    public List<FunctionBO> list(FunctionType type) throws SerException {
        return functionSer.list(type);
    }

    @Override
    public List<FunctionBO> userFunctions() throws SerException {
        return functionSer.userFunctions();
    }
}