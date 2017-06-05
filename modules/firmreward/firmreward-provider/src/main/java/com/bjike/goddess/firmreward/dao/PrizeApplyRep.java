package com.bjike.goddess.firmreward.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.firmreward.dto.PrizeApplyDTO;
import com.bjike.goddess.firmreward.entity.PrizeApply;

/**
 * 奖品申请持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:04 ]
 * @Description: [ 奖品申请持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PrizeApplyRep extends JpaRep<PrizeApply, PrizeApplyDTO> {

}