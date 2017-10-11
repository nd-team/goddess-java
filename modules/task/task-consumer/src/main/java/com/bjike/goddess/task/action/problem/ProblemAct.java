package com.bjike.goddess.task.action.problem;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.ProblemAPI;
import com.bjike.goddess.task.bo.ProblemBO;
import com.bjike.goddess.task.dto.ProblemDTO;
import com.bjike.goddess.task.to.AcceptTO;
import com.bjike.goddess.task.to.ProblemEditTO;
import com.bjike.goddess.task.to.ProblemTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 问题受理
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 10:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("problem")
public class ProblemAct {
    @Autowired
    private ProblemAPI problemAPI;

    /**
     * 问题列表
     *
     * @param dto
     * @return class ProblemBO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProblemDTO dto) throws ActException {
        try {
            List<ProblemBO> bos = problemAPI.list(dto);

            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题列表数量
     *
     * @param dto
     * @return {name:'data',type:'int',defaultValue:'',description:'数量.'}
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProblemDTO dto) throws ActException {
        try {
            return ActResult.initialize(problemAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题修改
     *
     * @param to
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ProblemEditTO to) throws ActException {
        try {
            problemAPI.edit(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题删除
     *
     * @param id
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PutMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            problemAPI.delete(id);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题添加
     *
     * @param to 问题
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProblemTO to, BindingResult rs) throws ActException {
        try {
            problemAPI.add(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题添加
     *
     * @param to 问题
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PostMapping("v1/accept")
    public Result accept(@Validated({ADD.class}) AcceptTO to, BindingResult rs) throws ActException {
        try {
            problemAPI.accept(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
