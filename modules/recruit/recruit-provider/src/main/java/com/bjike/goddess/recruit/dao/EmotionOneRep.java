package com.bjike.goddess.recruit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.recruit.dto.EmotionOneDTO;
import com.bjike.goddess.recruit.entity.EmotionOne;

import java.util.List;

/**
 * 情感标签二级持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:57 ]
 * @Description: [ 情感标签二级持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EmotionOneRep extends JpaRep<EmotionOne, EmotionOneDTO> {
    List<EmotionOne> findAll();

    void deleteById(String id);

}