package com.bjike.goddess.contractware.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractware.dto.CarRentalAgreementDTO;
import com.bjike.goddess.contractware.entity.CarRentalAgreement;

/**
 * 租车协议持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:43 ]
 * @Description: [ 租车协议持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CarRentalAgreementRep extends JpaRep<CarRentalAgreement, CarRentalAgreementDTO> {

}