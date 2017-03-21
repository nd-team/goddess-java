package com.bjike.goddess.headcount.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.headcount.dto.IntervalInfoDTO;
import com.bjike.goddess.headcount.entity.plancost.IntervalInfo;

/**
 * 持久化接口，继承其类可使用JPA命名查询
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-09 11:26 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IntervalInfoRep extends JpaRep<IntervalInfo,IntervalInfoDTO> {

}
