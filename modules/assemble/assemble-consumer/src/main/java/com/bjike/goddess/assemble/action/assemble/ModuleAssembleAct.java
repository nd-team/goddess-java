package com.bjike.goddess.assemble.action.assemble;

import com.bjike.goddess.assemble.api.ModuleAssembleAPI;
import com.bjike.goddess.assemble.to.ModuleAssembleTO;
import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.assemble.vo.ModuleVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
public class ModuleAssembleAct {
    @Autowired
    private ModuleAssembleAPI moduleAssembleAPI;

    /**
     * 勾选模块关联
     *
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/relation")
    public Result check(String moduleId, String[] relationIds) throws ActException {
        try {
            moduleAssembleAPI.relation(moduleId, relationIds);
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
        return new ActResult("check success");
    }

    /**
     * 获取模块关联的列表
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list/{name}")
    public Result modulesByName(@PathVariable String name, CheckType checkType) throws ActException {
        ActResult actResult = new ActResult();
        try {
            actResult.setData(BeanTransform.copyProperties(moduleAssembleAPI.modulesByName(name, checkType), ModuleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
        return actResult;
    }



    /**
     * 添加关联模块
     *
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(ModuleAssembleTO to) throws ActException {
        ActResult actResult = new ActResult();
        try {
            moduleAssembleAPI.add(to);
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
        return actResult;
    }

    /**
     * 删除关联模块
     *
     * @throws ActException
     * @des  @DeleteMapping只能接收表单,多个id url超出长度
     * @version v1
     */

    @PutMapping("v1/delete")
    public Result delete(String[] ids) throws ActException {
        ActResult actResult = new ActResult();
        try {
            moduleAssembleAPI.delete(ids);
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
        return actResult;
    }

}