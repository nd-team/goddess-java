package com.bjike.goddess.recruit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.recruit.dto.CheckIndexDTO;
import com.bjike.goddess.recruit.entity.CheckIndex;

import java.util.List;

/**
 * 考核指标持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:26 ]
 * @Description: [ 考核指标持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CheckIndexRep extends JpaRep<CheckIndex, CheckIndexDTO> {
    List<CheckIndex> findAll();

    void deleteById(String id);

}