package com.bjike.goddess.task.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.TaskAPI;
import com.bjike.goddess.task.entity.Task;
import com.bjike.goddess.task.to.TaskTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 任务下发
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
     * 一个项目所有详情
     *
     * @return
     * @throws ActException
     */
    @PostMapping("v1/issued")
    public Result issued(TaskTO to) throws ActException {
        try {
            taskAPI.issued(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


}
