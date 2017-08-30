package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.entity.WorkRange;

/**
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:50]
 * @Description: [工作范围信息设置持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
public interface WorkRangeRep extends JpaRep<WorkRange, WorkRangeDTO> {

}
