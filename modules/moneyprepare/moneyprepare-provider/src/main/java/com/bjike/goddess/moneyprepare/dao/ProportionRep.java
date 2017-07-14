package com.bjike.goddess.moneyprepare.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.moneyprepare.dto.ProportionDTO;
import com.bjike.goddess.moneyprepare.entity.Proportion;

/**
 * 比例分配持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProportionRep extends JpaRep<Proportion, ProportionDTO> {

}