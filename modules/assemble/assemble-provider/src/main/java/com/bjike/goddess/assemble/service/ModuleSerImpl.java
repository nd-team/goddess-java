package com.bjike.goddess.assemble.service;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dto.ModuleApplyDTO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.entity.Module;
import com.bjike.goddess.assemble.entity.ModuleApply;
import com.bjike.goddess.assemble.to.ModuleTO;
import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private ModuleApplySer moduleApplySer;

    @Override
    public List<ModuleBO> list(ModuleDTO moduleDTO) throws SerException {
        moduleDTO.getSorts().add("seq=asc");
        List<ModuleBO> modules = BeanTransform.copyProperties(super.findByCis(moduleDTO), ModuleBO.class);
        ModuleApplyDTO applyDTO = new ModuleApplyDTO();
        applyDTO.getConditions().add(Restrict.eq("company", "北京艾佳"));
        List<ModuleApply> moduleApplies = moduleApplySer.findByCis(applyDTO);
        for (ModuleApply apply : moduleApplies) {
            for (ModuleBO module : modules) {
                if (apply.getModule().getName().equals(module.getName())) {
                    module.setCheckType(CheckType.CHECK);
                    break;
                }
            }
        }
        return modules;
    }

    @Transactional
    @Override
    public void add(ModuleTO moduleTO) throws SerException {
        Module module = BeanTransform.copyProperties(moduleTO, Module.class, true);
        ModuleDTO dto = new ModuleDTO();
        dto.getConditions().add(Restrict.or("name", moduleTO.getName()));
        dto.getConditions().add(Restrict.or("moduleName", moduleTO.getModuleName()));
        if (null == super.findOne(dto)) {
            String result = super.findByMaxField("seq", Module.class);
            Integer seq = Integer.parseInt(StringUtils.isNotBlank(result) ? result : "0");
            module.setSeq(seq);
            super.save(module);
        } else {
            throw new SerException("[" + moduleTO.getName() + "]已存在");
        }
    }

    @Transactional
    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Transactional
    @Override
    public void check(String[] ids) throws SerException {
        List<Module> modules = new ArrayList<>();
        if (null != ids && ids.length > 0) {
            ModuleDTO dto = new ModuleDTO();
            dto.getConditions().add(Restrict.in(ID, ids));
            modules = super.findByCis(dto);
        }
        List<ModuleApply> moduleApplies = new ArrayList<>();
        String sql = "delete from module_apply where company=" + "'北京艾佳'";
        super.executeSql(sql);
        for (Module module : modules) {
            ModuleApply apply = new ModuleApply();
            apply.setModule(module);
            apply.setCompany("北京艾佳");
            moduleApplies.add(apply);
        }
        if (moduleApplies.size() > 0) {
            moduleApplySer.save(moduleApplies);
        }
    }

    @Override
    public Boolean isCheck(String name) throws SerException {
        ModuleApplyDTO dto = new ModuleApplyDTO();
        dto.getConditions().add(Restrict.eq("company", "北京艾佳"));
        dto.getConditions().add(Restrict.or("module.name", name));
        dto.getConditions().add(Restrict.or("module.moduleName", name));
        return null != moduleApplySer.findOne(dto);
    }
}
