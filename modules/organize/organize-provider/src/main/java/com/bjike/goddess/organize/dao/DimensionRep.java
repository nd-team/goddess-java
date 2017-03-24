package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.DimensionDTO;
import com.bjike.goddess.organize.entity.Dimension;

/**
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:24]
 * @Description: [维度持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */

public interface DimensionRep extends JpaRep<Dimension, DimensionDTO> {


}
