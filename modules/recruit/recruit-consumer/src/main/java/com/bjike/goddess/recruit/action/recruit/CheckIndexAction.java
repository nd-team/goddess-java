package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.WanyJackson;
import com.bjike.goddess.recruit.api.CheckIndexAPI;
import com.bjike.goddess.recruit.bo.CheckIndexBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 考核指标
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:26 ]
 * @Description: [ 考核指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("checkindex")
public class CheckIndexAction {
    @Autowired
    private CheckIndexAPI checkIndexAPI;

    /**
     * 列表
     *
     * @return class CheckIndexBO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list() throws SerException {
        List<CheckIndexBO> checkIndexBOS = checkIndexAPI.list();
        return ActResult.initialize(checkIndexBOS);
    }

    /**
     * 删除
     *
     * @param id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws SerException {
        checkIndexAPI.delete(id);
        return new ActResult("delete success");
    }

    /**
     * 添加
     *
     * @param data data
     * @version v1
     */
    @PutMapping("v1/add")
    public Result add(String data) throws SerException, IOException {
        checkIndexAPI.add(WanyJackson.superman(data, CheckIndexBO.class));
        return new ActResult("insert success");
    }

    /**
     * 编辑
     *
     * @param id
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(String id) throws SerException {
        CheckIndexBO checkIndexBO = checkIndexAPI.edit(id);
        return ActResult.initialize(checkIndexBO);
    }
}