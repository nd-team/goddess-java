package com.bjike.goddess.assemble.service;

import com.bjike.goddess.assemble.dto.ModuleAssembleDTO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.entity.Module;
import com.bjike.goddess.assemble.entity.ModuleAssemble;
import com.bjike.goddess.assemble.to.ModuleAssembleTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 模块关联业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assembleSerCache")
@Service
public class ModuleAssembleSerImpl extends ServiceImpl<ModuleAssemble, ModuleAssembleDTO> implements ModuleAssembleSer {
    @Autowired
    private ModuleSer moduleSer;

    @Transactional
    @Override
    public void add(ModuleAssembleTO to) throws SerException {
        ModuleAssembleDTO dto = new ModuleAssembleDTO();
        dto.getConditions().add(Restrict.eq("module.id", to.getModuleId()));
        dto.getConditions().add(Restrict.eq("relation.id", to.getRelationId()));
        if (null == super.findOne(dto)) {
            ModuleDTO moduleDTO = new ModuleDTO();
            moduleDTO.getConditions().add(Restrict.eq("id", to.getModuleId()));
            Module module = moduleSer.findOne(moduleDTO);
            if (null != module) {
                moduleDTO = new ModuleDTO();
                moduleDTO.getConditions().add(Restrict.eq("id", to.getRelationId()));
                Module relation = moduleSer.findOne(moduleDTO);
                if (null != relation) {
                    ModuleAssemble moduleAssemble = new ModuleAssemble();
                    moduleAssemble.setModule(module);
                    moduleAssemble.setRelation(relation);
                    super.save(moduleAssemble);
                } else {
                    throw new SerException("关联模块数据不存在");
                }
            } else {
                throw new SerException("模块数据不存在");
            }


        }
    }

    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }
}