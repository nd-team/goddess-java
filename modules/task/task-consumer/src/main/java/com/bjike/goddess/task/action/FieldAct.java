package com.bjike.goddess.task.action;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.FieldAPI;
import com.bjike.goddess.task.to.FieldTO;
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
            return ActResult.initialize(fieldAPI.list(tableId, node));
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
     * @des 设置任务节点：任务名称，执行人（选项），计划执行时间，任务类型（选项），类型（根据任务的类型选择获取类型：1.当选择行政任务时：内部项目名称；2.工程/培训任务：功能流程名称），任务内容，计划任务量，所需时长，备注(除了备注,其他默认必填)
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
