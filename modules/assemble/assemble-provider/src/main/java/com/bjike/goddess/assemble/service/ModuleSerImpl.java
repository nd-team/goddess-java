package com.bjike.goddess.assemble.service;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.entity.Module;
import com.bjike.goddess.assemble.to.ModuleTO;
import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "ModuleSerCache")
@Service
public class ModuleSerImpl extends ServiceImpl<Module, ModuleDTO> implements ModuleSer {
    @Override
    public List<ModuleBO> list(ModuleDTO moduleDTO) throws SerException {
        return BeanTransform.copyProperties(super.findByCis(moduleDTO), ModuleBO.class);
    }

    @Override
    public void add(ModuleTO moduleTO) throws SerException {
        Module module = BeanTransform.copyProperties(moduleTO, Module.class, true);
        if (null == module.getCheckType()) {
            module.setCheckType(CheckType.NONE);
        }
        ModuleDTO dto = new ModuleDTO();
        dto.getConditions().add(Restrict.eq("name", moduleTO.getName()));
        if (null == super.findOne(dto)) {
            super.save(module);
        } else {
            throw new SerException("[" + moduleTO.getName() + "]已存在");
        }
    }

    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void check(String id, CheckType checkType) throws SerException {
        ModuleDTO dto = new ModuleDTO();
        dto.getConditions().add(Restrict.eq(ID, id));
        Module module = super.findOne(dto);
        if (null != module) {
            module.setCheckType(checkType);
            super.update(module);
        } else {
            throw new SerException("该数据不存在!");
        }
    }
}
