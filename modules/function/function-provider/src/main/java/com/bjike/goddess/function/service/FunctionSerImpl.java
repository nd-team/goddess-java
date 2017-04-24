package com.bjike.goddess.function.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.function.bo.FunctionBO;
import com.bjike.goddess.function.dto.FunctionDTO;
import com.bjike.goddess.function.dto.UserFunctionDTO;
import com.bjike.goddess.function.entity.Function;
import com.bjike.goddess.function.entity.UserFunction;
import com.bjike.goddess.function.enums.FunctionType;
import com.bjike.goddess.function.to.FunctionTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 模块功能业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 11:07 ]
 * @Description: [ 模块功能业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "functionSerCache")
@Service
public class FunctionSerImpl extends ServiceImpl<Function, FunctionDTO> implements FunctionSer {
    @Autowired
    private UserFunctionSer userFunctionSer;
    @Autowired
    private UserAPI userAPI;

    @Override
    public FunctionBO add(FunctionTO functionTO) throws SerException {
        Function function = BeanTransform.copyProperties(functionTO, Function.class);
        Integer seq = super.count(new FunctionDTO()).intValue()+1;
        function.setSeq(seq);
        super.save(function);
        return BeanTransform.copyProperties(function, FunctionBO.class);
    }

    @Override
    public void edit(FunctionTO functionTO) throws SerException {
        Function function = super.findById(functionTO.getId());
        BeanTransform.copyProperties(functionTO, function, new String[]{"id"});
        function.setModifyTime(LocalDateTime.now());
        super.update(function);
    }

    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<FunctionBO> list(FunctionType type) throws SerException {
        FunctionDTO dto = new FunctionDTO();
        if (null != type) {
            dto.getConditions().add(Restrict.eq("type", type.getCode()));
        }
        return BeanTransform.copyProperties(findByCis(dto), FunctionBO.class);
    }

    @Override
    public List<FunctionBO> userFunctions() throws SerException {
        UserFunctionDTO dto = new UserFunctionDTO();
        String userId = userAPI.currentUser().getId();
        dto.getConditions().add(Restrict.eq("userId", userId));
        List<UserFunction> userFunctions = userFunctionSer.findByCis(dto);
        List<FunctionBO> functionBOS = new ArrayList<>();
        if (null != userFunctions) {
            for (UserFunction userFunction : userFunctions) {
                functionBOS.add(BeanTransform.copyProperties(userFunction.getFunction(), FunctionBO.class));
            }
        }
        return functionBOS;
    }
}