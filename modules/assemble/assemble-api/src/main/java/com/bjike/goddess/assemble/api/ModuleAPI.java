package com.bjike.goddess.assemble.api;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.to.ModuleTO;
import com.bjike.goddess.assemble.vo.ModuleVO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * Created by lake on 17-5-9.
 */
public interface ModuleAPI {

    List<ModuleBO> list(ModuleDTO moduleDTO) throws SerException;
}
