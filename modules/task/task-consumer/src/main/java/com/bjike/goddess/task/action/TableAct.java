package com.bjike.goddess.task.action;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.TableAPI;
import com.bjike.goddess.task.dto.TableDTO;
import com.bjike.goddess.task.to.TableTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-16 09:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("table")
public class TableAct {
    @Autowired
    private TableAPI tableAPI;

    /**
     * 列表
     *
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TableDTO dto) throws ActException {
        try {
            return ActResult.initialize(tableAPI.list(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 表信息
     * @return
     * @throws ActException
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) TableTO to, BindingResult rs) throws ActException {
        try {
            tableAPI.add(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 表删除
     *
     * @param id 表id
     * @return
     * @throws ActException
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            tableAPI.delete(id);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
