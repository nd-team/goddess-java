package com.bjike.goddess.assemble.service;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dao.ModuleRep;
import com.bjike.goddess.assemble.dto.ModuleAssembleDTO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.entity.Module;
import com.bjike.goddess.assemble.entity.ModuleAssemble;
import com.bjike.goddess.assemble.to.ModuleAssembleTO;
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

import java.util.List;

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
    @Autowired
    private ModuleRep moduleRep;

    @Transactional
    @Override
    public void add(ModuleAssembleTO to) throws SerException {
        ModuleAssembleDTO dto = new ModuleAssembleDTO();
        dto.getConditions().add(Restrict.eq("module.name", to.getModuleName()));
        dto.getConditions().add(Restrict.eq("relation.name", to.getRelationName()));
        ModuleAssemble assemble = super.findOne(dto);
        if (null == assemble) {
            ModuleDTO moduleDTO = new ModuleDTO();
            moduleDTO.getConditions().add(Restrict.eq("name", to.getModuleName()));
            Module module = moduleSer.findOne(moduleDTO);
            if (null != module) {
                moduleDTO = new ModuleDTO();
                moduleDTO.getConditions().add(Restrict.eq("name", to.getRelationName()));
                Module relation = moduleSer.findOne(moduleDTO);
                if (null != relation) {
                    ModuleAssemble moduleAssemble = new ModuleAssemble();
                    moduleAssemble.setModule(module);
                    moduleAssemble.setRelation(relation);
                    moduleAssemble.setCheckType(to.getCheckType());
                    if (null == to.getCheckType()) {
                        moduleAssemble.setCheckType(CheckType.NONE);
                    }
                    super.save(moduleAssemble);
                } else {
                    throw new SerException("关联模块数据不存在");
                }
            } else {
                throw new SerException("模块数据不存在");
            }
        } else {
            assemble.setCheckType(CheckType.NONE);
            super.update(assemble);
        }
    }

    @Transactional
    @Override
    public void delete(String[] ids) throws SerException {
        if (null != ids && ids.length > 0) {
            String[] tmp = new String[ids.length];
            for (int i = 0; i < ids.length; i++) {
                tmp[i] = "'" + ids[i] + "'";
            }
            String relations = StringUtils.join(tmp, ",");
            String sql = "update module_assemble set checkType=2 where id  in(" + relations + ")";
            super.executeSql(sql);
        }
    }


    @Transactional
    @Override
    public void relation(String moduleId, String[] relationIds) throws SerException {
        String sql = "update module_assemble set checkType=1 where checkType<>2 and module_id='" + moduleId + "'";
        super.executeSql(sql);
        if (null != relationIds && relationIds.length > 0) {
            String[] tmp = new String[relationIds.length];
            for (int i = 0; i < relationIds.length; i++) {
                tmp[i] = "'" + relationIds[i] + "'";
            }
            String relations = StringUtils.join(tmp, ",");
            sql = "update module_assemble set checkType=0 where checkType<>2 and module_id='" + moduleId + "' and relation_id in(" + relations + ")";
            super.executeSql(sql);
        }
    }

    @Override
    public ModuleBO modulesByName(String name, CheckType checkType) throws SerException {
        Module module = moduleRep.findByName(name);
        StringBuilder sb = new StringBuilder();
        sb.append("select b.id,b.relation_id as relationId, b.checkType,a.name from module_table a,(");
        sb.append(" select b.id,b.relation_id  ,b.checkType from module_table a ,");
        sb.append(" module_assemble b where a.id = b.module_id and a.name='");
        sb.append(name);
        sb.append("' ");
        if (null != checkType) {
            sb.append(" and b.checkType=" + checkType.getCode());
        } else {
            sb.append(" and b.checkType in(0,1)");
        }
        sb.append(" )b where b.relation_id=a.id order by a.createTime asc");
        String sql = sb.toString();
        List<ModuleBO> relations = super.findBySql(sql, ModuleBO.class, new String[]{"id", "relationId", "checkType", "name"});
        ModuleBO moduleBO = BeanTransform.copyProperties(module, ModuleBO.class);
        moduleBO.setRelations(relations);
        return moduleBO;
    }

    @Override
    public Boolean checkByName(String[] moduleNames) throws SerException {
        Boolean bool = false;
        Module module = moduleSer.getIdByName(moduleNames[0]);
        Module relation = moduleSer.getIdByName(moduleNames[1]);
        ModuleAssembleDTO moduleAssembleDTO = new ModuleAssembleDTO();
        moduleAssembleDTO.getConditions().add(Restrict.eq("module.id", module.getId()));
        moduleAssembleDTO.getConditions().add(Restrict.eq("relation.id", relation.getId()));
        ModuleAssemble moduleAssemble = super.findOne(moduleAssembleDTO);
        if(moduleAssemble != null && moduleAssemble.getCheckType()==CheckType.CHECK){
            bool = true;
        }
        return bool;
    }
}