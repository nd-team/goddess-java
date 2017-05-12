package com.bjike.goddess.assemble.service;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dao.ModuleRep;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.entity.Module;
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
public class ModuleSerImpl extends ServiceImpl<Module,ModuleDTO> implements ModuleSer {
    @Override
    public List<ModuleBO> list(ModuleDTO moduleDTO) throws SerException {
        return BeanTransform.copyProperties(super.findByCis(moduleDTO),ModuleBO.class);
    }
}
