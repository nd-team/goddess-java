package com.bjike.goddess.materialcheck.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialcheck.dto.MaterialInventoryDTO;
import com.bjike.goddess.materialcheck.entity.MaterialInventory;

/**
 * 物资盘点持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:00 ]
 * @Description: [ 物资盘点持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialInventoryRep extends JpaRep<MaterialInventory, MaterialInventoryDTO> {

}