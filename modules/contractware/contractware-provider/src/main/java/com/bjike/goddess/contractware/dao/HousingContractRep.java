package com.bjike.goddess.contractware.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractware.dto.HousingContractDTO;
import com.bjike.goddess.contractware.entity.HousingContract;

/**
 * 房屋合同持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:42 ]
 * @Description: [ 房屋合同持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HousingContractRep extends JpaRep<HousingContract, HousingContractDTO> {

}