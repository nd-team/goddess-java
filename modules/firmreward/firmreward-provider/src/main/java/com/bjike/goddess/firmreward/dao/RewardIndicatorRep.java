package com.bjike.goddess.firmreward.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.firmreward.dto.RewardIndicatorDTO;
import com.bjike.goddess.firmreward.entity.RewardIndicator;

/**
 * 奖励指标持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:56 ]
 * @Description: [ 奖励指标持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RewardIndicatorRep extends JpaRep<RewardIndicator, RewardIndicatorDTO> {

}