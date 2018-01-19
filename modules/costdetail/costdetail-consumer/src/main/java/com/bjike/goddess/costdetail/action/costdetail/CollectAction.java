package com.bjike.goddess.costdetail.action.costdetail;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.costdetail.api.CollectAPI;
import com.bjike.goddess.costdetail.bo.CollectBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 成本明细汇总
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-03 04:10 ]
 * @Description: [ 成本明细汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collect")
public class CollectAction {

    @Autowired
    private CollectAPI collectAPI;

    /**
     * 列表
     *
     * @param date c
     * @return class CollectBO
     * @throws SerException
     * @version v1
     */

    @GetMapping("v1/collect")
    public Result getCollect(String date) throws SerException {
        System.out.println("hello");
        List<CollectBO> list = collectAPI.getCollect(date);
        return new ActResult("success", list);
    }

}