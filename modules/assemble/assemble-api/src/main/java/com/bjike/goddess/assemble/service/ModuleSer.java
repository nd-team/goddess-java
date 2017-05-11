package com.bjike.goddess.assemble.service;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.entity.Module;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * Created by lake on 17-5-8.
 */
public interface ModuleSer extends Ser<Module,ModuleDTO> {

    List<ModuleBO> list(ModuleDTO moduleDTO) throws SerException;
}
