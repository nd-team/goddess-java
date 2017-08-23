package com.bjike.goddess.assemble.dao;

import com.bjike.goddess.assemble.dto.ModuleApplyDTO;
import com.bjike.goddess.assemble.entity.ModuleApply;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 模块应用持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-17 05:41 ]
 * @Description: [ 模块应用持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ModuleApplyRep extends JpaRep<ModuleApply, ModuleApplyDTO> {

}