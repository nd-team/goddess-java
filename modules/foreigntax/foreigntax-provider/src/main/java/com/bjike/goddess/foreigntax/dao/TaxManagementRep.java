package com.bjike.goddess.foreigntax.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.entity.TaxManagement;

/**
 * 税金管理持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TaxManagementRep extends JpaRep<TaxManagement, TaxManagementDTO> {

}