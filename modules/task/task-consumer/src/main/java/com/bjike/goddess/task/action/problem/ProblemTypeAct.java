package com.bjike.goddess.task.action.problem;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.ProblemTypeAPI;
import com.bjike.goddess.task.bo.ProblemTypeBO;
import com.bjike.goddess.task.dto.ProblemTypeDTO;
import com.bjike.goddess.task.to.ProblemTypeTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 问题类型
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 10:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("problem/type")
public class ProblemTypeAct {
    @Autowired
    private ProblemTypeAPI problemTypeAPI;

    /**
     * 添加
     *
     * @param dto 问题
     * @return class ProblemTypeBO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProblemTypeDTO dto) throws ActException {
        try {
            List<ProblemTypeBO> bos = problemTypeAPI.list(dto);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 问题类型
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProblemTypeTO to, BindingResult rs) throws ActException {
        try {
            problemTypeAPI.add(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 问题id
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            problemTypeAPI.delete(id);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * @param to 问题
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     * 编辑类型名
     */
    @PutMapping("v1/edit")
    public Result enable(@Validated({ADD.class}) ProblemTypeTO to, BindingResult rs) throws ActException {
        try {
            problemTypeAPI.edit(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 开启关闭
     *
     * @param id 问题id
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PutMapping("v1/{id}/{enable}")
    public Result enable(@PathVariable String id, @PathVariable Boolean enable) throws ActException {
        try {
            problemTypeAPI.enable(id, enable);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
