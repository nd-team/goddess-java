package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.OperateDTO;
import com.bjike.goddess.organize.entity.Operate;

/**
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:16]
 * @Description: [操作类型持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
public interface OperateRep extends JpaRep<Operate, OperateDTO> {

}
