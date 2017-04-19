package com.bjike.goddess.business.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.business.dto.BusinessTaxChangeDTO;
import com.bjike.goddess.business.entity.BusinessTaxChange;

/**
 * 工商税务变更持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:56 ]
 * @Description: [ 工商税务变更持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessTaxChangeRep extends JpaRep<BusinessTaxChange, BusinessTaxChangeDTO> {

}