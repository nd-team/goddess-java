package com.bjike.goddess.task.action;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.CustomizeAPI;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.to.CustomizeTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("customize")
public class CustomizeAct {

    @Autowired
    private CustomizeAPI customizeAPI;

    /**
     * 列表
     *
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CustomizeDTO dto) throws ActException {
        try {
            return ActResult.initialize(customizeAPI.list(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数量
     *
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CustomizeDTO dto) throws ActException {
        try {
            return ActResult.initialize(customizeAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数量
     *
     * @version v1
     */
    @PostMapping("v1/add")
    public Result count(@Validated({ADD.class}) CustomizeTO to, BindingResult rs) throws ActException {
        try {
            customizeAPI.add(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数量
     *
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result count(@PathVariable String id) throws ActException {
        try {
            customizeAPI.delete(id);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 打开关闭
     *
     * @version v1
     */
    @PutMapping("v1/edit/{enable}/{id}")
    public Result count(@PathVariable Boolean enable, @PathVariable String id) throws ActException {
        try {
            customizeAPI.enable(id, enable);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
