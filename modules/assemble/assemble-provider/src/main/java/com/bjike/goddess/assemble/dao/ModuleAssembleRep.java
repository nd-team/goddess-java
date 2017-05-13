package com.bjike.goddess.assemble.dao;

import com.bjike.goddess.assemble.dto.ModuleAssembleDTO;
import com.bjike.goddess.assemble.entity.ModuleAssemble;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 模块关联持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ModuleAssembleRep extends JpaRep<ModuleAssemble, ModuleAssembleDTO> {

}