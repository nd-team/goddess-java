package com.bjike.goddess.assemble.api;

import com.bjike.goddess.assemble.bo.ModuleBO;
import com.bjike.goddess.assemble.service.ModuleAssembleSer;
import com.bjike.goddess.assemble.to.ModuleAssembleTO;
import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 模块关联业务接口实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("moduleAssembleApiImpl")
public class ModuleAssembleApiImpl implements ModuleAssembleAPI {
    @Autowired
    private ModuleAssembleSer moduleAssembleSer;

    @Override
    public void add(ModuleAssembleTO moduleAssembleTO) throws SerException {
        moduleAssembleSer.add(moduleAssembleTO);
    }

    @Override
    public void delete(String[] ids) throws SerException {
        moduleAssembleSer.delete(ids);
    }

    @Override
    public void relation(String moduleId,String[] relationIds) throws SerException {
        moduleAssembleSer.relation(moduleId, relationIds);
    }


    @Override
    public ModuleBO modulesByName(String name, CheckType checkType) throws SerException {
        return moduleAssembleSer.modulesByName(name,checkType);
    }
}