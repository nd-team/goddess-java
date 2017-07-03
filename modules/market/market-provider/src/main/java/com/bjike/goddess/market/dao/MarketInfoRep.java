package com.bjike.goddess.market.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.market.dto.MarketInfoDTO;
import com.bjike.goddess.market.entity.MarketInfo;

/**
 * 市场信息管理持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.567 ]
 * @Description: [ 市场信息管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketInfoRep extends JpaRep<MarketInfo, MarketInfoDTO> {

}