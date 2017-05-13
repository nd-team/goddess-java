package com.bjike.goddess.assemble.action.assemble;

import com.bjike.goddess.assemble.api.ModuleAssembleAPI;
import com.bjike.goddess.assemble.to.ModuleAssembleTO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 模块关联
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("assemble")
public class ModuleAssembleAction {
    @Autowired
    private ModuleAssembleAPI moduleAssembleAPI;

    /**
     * 添加模块
     *
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result list(ModuleAssembleTO to) throws ActException {
        ActResult actResult = new ActResult();
        try {
            moduleAssembleAPI.add(to);
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
        return actResult;
    }

    /**
     * 删除模块
     *
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result list(@PathVariable String id) throws ActException {
        ActResult actResult = new ActResult();
        try {
            moduleAssembleAPI.delete(id);
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
        return actResult;
    }

}