package com.bjike.goddess.task.action;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.ProblemAPI;
import com.bjike.goddess.task.to.AcceptTO;
import com.bjike.goddess.task.to.ProblemTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 问题受理
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
     * 问题添加
     *
     * @param to 问题
     * @return
     * @throws ActException
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
     * @return
     * @throws ActException
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
