package com.bjike.goddess.assemble.service;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.dao.ModuleRep;
import com.bjike.goddess.assemble.dto.ModuleAssembleDTO;
import com.bjike.goddess.assemble.dto.ModuleDTO;
import com.bjike.goddess.assemble.entity.Module;
import com.bjike.goddess.assemble.entity.ModuleAssemble;
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

import java.util.Arrays;
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
    private ModuleRep moduleRep;
    @Autowired
    private ModuleAssembleSer moduleAssembleSer;

    @Override
    public List<ModuleBO> list(ModuleDTO moduleDTO) throws SerException {
        moduleDTO.setSorts(Arrays.asList("createTime=asc"));
        return BeanTransform.copyProperties(super.findByCis(moduleDTO), ModuleBO.class);
    }

    @Override
    public void add(ModuleTO moduleTO) throws SerException {
        Module module = BeanTransform.copyProperties(moduleTO, Module.class, true);
        ModuleDTO dto = new ModuleDTO();
        dto.getConditions().add(Restrict.eq("name", moduleTO.getName()));
        if (null == super.findOne(dto)) {
            super.save(module);
        } else {
            throw new SerException("[" + moduleTO.getName() + "]已存在");
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
        }else {
            sb.append(" and b.checkType in(0,1)");
        }
        sb.append(" )b where b.relation_id=a.id order by a.createTime asc");
        String sql = sb.toString();
        List<ModuleBO> relations = super.findBySql(sql, ModuleBO.class, new String[]{"id","relationId", "checkType", "name"});
        ModuleBO moduleBO = BeanTransform.copyProperties(module, ModuleBO.class);
        moduleBO.setRelations(relations);
        return moduleBO;
    }


    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Transactional
    @Override
    public void check(String moduleId, String[] relationIds) throws SerException {
        String sql = "update module_assemble set checkType=1 where module_id='"+moduleId+"'";
        super.executeSql(sql);
        if (null != relationIds && relationIds.length > 0) {
            String[] tmp = new String[relationIds.length];
            for(int i=0;i<relationIds.length;i++){
                tmp[i]="'"+relationIds[i]+"'";
            }
            String relations = StringUtils.join(tmp,",");
            sql ="update module_assemble set checkType=0 where module_id='"+moduleId+"' and relation_id in("+relations+")";
            super.executeSql(sql);
        } else {
            throw new SerException("关联数据不存在!");
        }

    }
}
