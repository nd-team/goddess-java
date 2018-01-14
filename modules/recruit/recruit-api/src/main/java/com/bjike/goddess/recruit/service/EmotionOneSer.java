package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.CheckIndexBO;
import com.bjike.goddess.recruit.bo.EmotionOneBO;
import com.bjike.goddess.recruit.dto.EmotionOneDTO;
import com.bjike.goddess.recruit.entity.EmotionOne;
import com.bjike.goddess.recruit.to.EmotionOneTO;

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
public interface EmotionOneSer extends Ser<EmotionOne, EmotionOneDTO> {
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