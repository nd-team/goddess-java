package com.bjike.goddess.materialbuy.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialbuy.dto.TempMatterDemandDTO;
import com.bjike.goddess.materialbuy.entity.TempMatterDemand;

/**
 * 临时物质需求持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:08 ]
 * @Description: [ 临时物质需求持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TempMatterDemandRep extends JpaRep<TempMatterDemand, TempMatterDemandDTO> {

}