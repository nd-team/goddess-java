package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.EmotionOneBO;

import java.util.List;

/**
 * 情感标签二级业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:57 ]
 * @Description: [ 情感标签二级业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EmotionOneAPI {
    /**
     * 情感标签添加
     *
     * @param bo bo
     * @throws SerException
     */
    void add(EmotionOneBO bo) throws SerException;

    /**
     * 情感标签列表
     *
     * @return
     * @throws SerException
     */
    List<EmotionOneBO> list() throws SerException;

    /**
     * 情感标签删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 情感标签编辑
     *
     * @param id
     * @return
     * @throws SerException
     */
    EmotionOneBO edit(String id) throws SerException;

}