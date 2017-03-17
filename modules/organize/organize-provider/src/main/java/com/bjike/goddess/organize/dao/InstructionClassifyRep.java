package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.InstructionClassifyDTO;
import com.bjike.goddess.organize.entity.InstructionClassify;

/**
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:25]
 * @Description: [岗位说明书 - 分类持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
public interface InstructionClassifyRep extends JpaRep<InstructionClassify, InstructionClassifyDTO> {
}
