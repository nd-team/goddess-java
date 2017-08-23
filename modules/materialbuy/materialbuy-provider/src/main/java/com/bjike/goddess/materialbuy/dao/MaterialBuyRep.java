package com.bjike.goddess.materialbuy.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.materialbuy.entity.MaterialBuy;

/**
 * 物资购买持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:47 ]
 * @Description: [ 物资购买持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialBuyRep extends JpaRep<MaterialBuy, MaterialBuyDTO> {

}