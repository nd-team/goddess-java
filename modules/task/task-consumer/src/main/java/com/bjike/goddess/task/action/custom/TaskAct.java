package com.bjike.goddess.task.action.custom;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.TaskAPI;
import com.bjike.goddess.task.to.TaskTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-19 11:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("task")
public class TaskAct {

    @Autowired
    private TaskAPI taskAPI;

    /**
     * 任务下发
     *
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/issued")
    public Result issued(@Validated(ADD.class) TaskTO to, BindingResult result) throws ActException {
        try {
            taskAPI.issued(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}
