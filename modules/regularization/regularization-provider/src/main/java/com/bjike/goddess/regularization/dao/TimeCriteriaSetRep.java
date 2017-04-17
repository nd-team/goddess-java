package com.bjike.goddess.regularization.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.regularization.dto.TimeCriteriaSetDTO;
import com.bjike.goddess.regularization.entity.TimeCriteriaSet;

/**
 * 时间条件设置持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:21 ]
 * @Description: [ 时间条件设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TimeCriteriaSetRep extends JpaRep<TimeCriteriaSet, TimeCriteriaSetDTO> {

}