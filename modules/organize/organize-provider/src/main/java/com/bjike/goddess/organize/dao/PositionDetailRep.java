package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.entity.PositionDetail;

/**
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:08]
 * @Description: [岗位详细持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
public interface PositionDetailRep extends JpaRep<PositionDetail, PositionDetailDTO> {

}
