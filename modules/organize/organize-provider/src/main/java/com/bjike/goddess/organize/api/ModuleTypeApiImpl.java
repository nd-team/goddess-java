package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.organize.bo.ModuleTypeBO;
import com.bjike.goddess.organize.dto.ModuleTypeDTO;
import com.bjike.goddess.organize.service.ModuleTypeSer;
import com.bjike.goddess.organize.to.ModuleTypeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模块类型业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:19 ]
 * @Description: [ 模块类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("moduleTypeApiImpl")
public class ModuleTypeApiImpl implements ModuleTypeAPI {

    @Autowired
    private ModuleTypeSer moduleTypeSer;

    @Override
    public ModuleTypeBO save(ModuleTypeTO to) throws SerException {
        return moduleTypeSer.save(to);
    }

    @Override
    public ModuleTypeBO update(ModuleTypeTO to) throws SerException {
        return moduleTypeSer.update(to);
    }

    @Override
    public ModuleTypeBO delete(String id) throws SerException {
        return moduleTypeSer.delete(id);
    }

    @Override
    public ModuleTypeBO congeal(String id) throws SerException {
        return moduleTypeSer.congeal(id);
    }

    @Override
    public ModuleTypeBO thaw(String id) throws SerException {
        return moduleTypeSer.thaw(id);
    }

    @Override
    public List<ModuleTypeBO> findByStatus(Status status) throws SerException {
        return moduleTypeSer.findByStatus(status);
    }

    @Override
    public List<ModuleTypeBO> maps(ModuleTypeDTO dto) throws SerException {
        return moduleTypeSer.maps(dto);
    }
}