package com.bjike.goddess.businesscommission.dao;

import com.bjike.goddess.businesscommission.dto.WeightAllotDTO;
import com.bjike.goddess.businesscommission.entity.WeightAllot;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 业务提成分配比例表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:34 ]
 * @Description: [ 业务提成分配比例表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WeightAllotRep extends JpaRep<WeightAllot, WeightAllotDTO> {

}