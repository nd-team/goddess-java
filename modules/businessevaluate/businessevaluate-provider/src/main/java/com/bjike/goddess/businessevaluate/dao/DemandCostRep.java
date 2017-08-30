package com.bjike.goddess.businessevaluate.dao;

import com.bjike.goddess.businessevaluate.dto.DemandCostDTO;
import com.bjike.goddess.businessevaluate.entity.DemandCost;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 需求成本持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 11:06 ]
 * @Description: [ 需求成本持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DemandCostRep extends JpaRep<DemandCost, DemandCostDTO> {

}