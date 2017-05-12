package com.bjike.goddess.assemble.api;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ModuleAPI {

    List<ModuleBO> list(ModuleDTO moduleDTO) throws SerException;
}
