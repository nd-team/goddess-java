package com.bjike.goddess.task.action;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.FieldAPI;
import com.bjike.goddess.task.api.TableAPI;
import com.bjike.goddess.task.dto.FieldDTO;
import com.bjike.goddess.task.dto.TableDTO;
import com.bjike.goddess.task.entity.Field;
import com.bjike.goddess.task.to.FieldTO;
import com.bjike.goddess.task.to.TableTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-16 09:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("field")
public class FieldAct {
    @Autowired
    private FieldAPI fieldAPI;

    /**
     * 列表
     *
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(String tableId, String node) throws ActException {
        try {
            return ActResult.initialize(fieldAPI.list(tableId,node));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加列
     *
     * @param to 列信息
     * @return
     * @throws ActException
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) FieldTO to, BindingResult rs) throws ActException {
        try {
            fieldAPI.add(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除列
     *
     * @param id 列id
     * @return
     * @throws ActException
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            fieldAPI.delete(id);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
