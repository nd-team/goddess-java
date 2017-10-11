package com.bjike.goddess.task.action.custom;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.NodeAPI;
import com.bjike.goddess.task.bo.FieldBO;
import com.bjike.goddess.task.entity.Field;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 表节点
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-19 11:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("node")
public class NodeAct {
    @Autowired
    private NodeAPI nodeAPI;

    /**
     * 列表
     *
     * @param tableId 表id
     * @return {name:'data',type:'list<string>',defaultValue:'',description:'字符串列表.'}
     * @version v1
     */
    @GetMapping("v1/list/{tableId}")
    public Result node(@PathVariable String tableId) throws ActException {
        try {
            return ActResult.initialize(nodeAPI.node(tableId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 节点详情
     *
     * @param tableId 表id
     * @param node 节点
     * @return class FieldBO
     * @version v1
     */
    @GetMapping("v1/detail/{tableId}/{node}")
    public Result detail(@PathVariable String tableId, @PathVariable String node) throws ActException {
        try {
            List<FieldBO> bos = nodeAPI.detail(tableId, node);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
