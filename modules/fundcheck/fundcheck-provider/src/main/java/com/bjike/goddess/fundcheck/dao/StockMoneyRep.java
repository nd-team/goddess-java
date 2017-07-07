package com.bjike.goddess.fundcheck.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.fundcheck.dto.StockMoneyDTO;
import com.bjike.goddess.fundcheck.entity.StockMoney;

/**
 * 收到股东款持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:51 ]
 * @Description: [ 收到股东款持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StockMoneyRep extends JpaRep<StockMoney, StockMoneyDTO> {

}