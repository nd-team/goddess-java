package com.bjike.goddess.abilitydisplay.action.abilitydisplay;

import com.bjike.goddess.abilitydisplay.api.SummaryAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 能力展示汇总
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-08 02:04 ]
 * @Description: [ 能力展示汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("summary")
public class SummaryAction {

    @Autowired
    private SummaryAPI summaryAPI;

    @GetMapping("v1/sum")
    public Result getSum(String date) throws SerException {
        System.out.println(date);
        return new ActResult("success", summaryAPI.getSum(date));
    }
}