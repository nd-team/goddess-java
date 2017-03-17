package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.DesignNumberInfoDTO;
import com.bjike.goddess.organize.entity.DesignNumberInfo;

/**
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:26]
 * @Description: [编号设计信息持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */

public interface DesignNumberInfoRep extends JpaRep<DesignNumberInfo, DesignNumberInfoDTO> {

}
