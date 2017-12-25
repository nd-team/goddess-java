package com.bjike.goddess.regularization.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.entity.Regularization;

/**
 * 员工转正持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [ 员工转正持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RegularizationRep extends JpaRep<Regularization, RegularizationDTO> {

}