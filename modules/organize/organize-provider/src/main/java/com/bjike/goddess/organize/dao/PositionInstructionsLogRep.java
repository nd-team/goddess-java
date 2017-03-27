package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.PositionInstructionsLogDTO;
import com.bjike.goddess.organize.entity.PositionInstructionsLog;

/**
 * @Author: [dengjunren]
 * @Date: [2017-03-08 14:08]
 * @Description: [岗位说明书历史记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface PositionInstructionsLogRep extends JpaRep<PositionInstructionsLog, PositionInstructionsLogDTO> {
}
