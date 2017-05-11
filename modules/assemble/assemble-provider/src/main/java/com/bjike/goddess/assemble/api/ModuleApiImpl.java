package com.bjike.goddess.assemble.api;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.service.ModuleSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lake on 17-5-8.
 */
@Service("moduleApiImpl")
public class ModuleApiImpl implements ModuleAPI {
    @Autowired
    private ModuleSer moduleSer;

    @Override
    public List<ModuleBO> list(ModuleDTO moduleDTO) throws SerException{
        moduleSer.list(moduleDTO);
         return  null;
    }
}
