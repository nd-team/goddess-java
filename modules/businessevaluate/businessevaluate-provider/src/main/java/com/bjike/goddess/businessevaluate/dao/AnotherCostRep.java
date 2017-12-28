package com.bjike.goddess.businessevaluate.dao;

import com.bjike.goddess.businessevaluate.dto.AnotherCostDTO;
import com.bjike.goddess.businessevaluate.entity.AnotherCost;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 其它成本持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 01:46 ]
 * @Description: [ 其它成本持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnotherCostRep extends JpaRep<AnotherCost, AnotherCostDTO> {

}