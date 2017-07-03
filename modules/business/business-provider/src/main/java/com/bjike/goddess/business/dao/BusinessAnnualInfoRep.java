package com.bjike.goddess.business.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.business.dto.BusinessAnnualInfoDTO;
import com.bjike.goddess.business.entity.BusinessAnnualInfo;

/**
 * 工商年检信息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:48 ]
 * @Description: [ 工商年检信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessAnnualInfoRep extends JpaRep<BusinessAnnualInfo, BusinessAnnualInfoDTO> {

}