package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.ArrangementDTO;
import com.bjike.goddess.organize.entity.Arrangement;

/**
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:49]
 * @Description: [岗位层级持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ArrangementRep extends JpaRep<Arrangement, ArrangementDTO> {
}
