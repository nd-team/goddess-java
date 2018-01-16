package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.WanyJackson;
import com.bjike.goddess.recruit.api.EmotionOneAPI;
import com.bjike.goddess.recruit.bo.EmotionOneBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 情感标签二级
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:57 ]
 * @Description: [ 情感标签二级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("emotionone")
public class EmotionOneAction {
    @Autowired
    private EmotionOneAPI emotionOneAPI;

    /**
     * 列表
     *
     * @return class EmotionOneBO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list() throws SerException {
        List<EmotionOneBO> emotionOneBOS = emotionOneAPI.list();
        return ActResult.initialize(emotionOneBOS);
    }

    /**
     * 删除
     *
     * @param id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws SerException {
        emotionOneAPI.delete(id);
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
        emotionOneAPI.add(WanyJackson.superman(data, EmotionOneBO.class));
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
        EmotionOneBO emotionOneBO = emotionOneAPI.edit(id);
        return ActResult.initialize(emotionOneBO);
    }
}