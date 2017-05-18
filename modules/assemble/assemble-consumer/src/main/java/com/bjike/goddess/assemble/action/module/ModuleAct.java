package com.bjike.goddess.assemble.action.module;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.to.ModuleTO;
import com.bjike.goddess.assemble.vo.ModuleVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 模块功能
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("module")
public class ModuleAct {

    @Autowired
    private ModuleAPI moduleAPI;

    /**
     * 模块获取列表
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ModuleDTO moduleDTO, HttpServletRequest request) throws ActException {
        ActResult actResult = new ActResult();
        try {
            actResult.setData(BeanTransform.copyProperties(moduleAPI.list(moduleDTO), ModuleVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
        return actResult;
    }


    /**
     * 添加模块
     *
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ModuleTO to, BindingResult result) throws ActException {
        try {
            moduleAPI.add(to);
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
        return new ActResult("add success");
    }

    /**
     * 删除模块
     *
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            moduleAPI.delete(id);
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
        return new ActResult("delete success");
    }

    /**
     * 勾选模块
     *
     * @param ids 模块id
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/check")
    public Result check(String[] ids) throws ActException {
        try {
            moduleAPI.check(ids);
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
        return new ActResult("check success");
    }

    /**
     * 勾选模块
     *
     * @param id 模块id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/isCheck/{id}")
    public Result check(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(moduleAPI.isCheck(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage(), e.getCause());
        }
    }

}
