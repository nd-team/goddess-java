package com.bjike.goddess.assemble.api;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.entity.Module;
import com.bjike.goddess.assemble.service.ModuleSer;
import com.bjike.goddess.assemble.to.ModuleTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("moduleApiImpl")
public class ModuleApiImpl implements ModuleAPI {
    @Autowired
    private ModuleSer moduleSer;

    @Override
    public List<ModuleBO> list(ModuleDTO moduleDTO) throws SerException {
        return moduleSer.list(moduleDTO);
    }


    @Override
    public void add(ModuleTO moduleTO) throws SerException {
        moduleSer.add(moduleTO);
    }

    @Override
    public void delete(String id) throws SerException {
        moduleSer.delete(id);
    }

    @Override
    public void check(String[] ids) throws SerException {
        moduleSer.check(ids);
    }

    @Override
    public Boolean isCheck(String name) throws SerException {
        return moduleSer.isCheck(name);
    }

    @Override
    public Module getIdByName(String name) throws SerException {
        return moduleSer.getIdByName(name);
    }
}
