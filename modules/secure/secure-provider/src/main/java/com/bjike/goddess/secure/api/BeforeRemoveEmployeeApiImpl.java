package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.BeforeRemoveEmployeeBO;
import com.bjike.goddess.secure.bo.DismissionEmployeeBO;
import com.bjike.goddess.secure.dto.BeforeRemoveEmployeeDTO;
import com.bjike.goddess.secure.service.BeforeRemoveEmployeeSer;
import com.bjike.goddess.secure.to.BeforeRemoveEmployeeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 减员前业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:45 ]
 * @Description: [ 减员前业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("beforeRemoveEmployeeApiImpl")
public class BeforeRemoveEmployeeApiImpl implements BeforeRemoveEmployeeAPI {
    @Autowired
    private BeforeRemoveEmployeeSer beforeRemoveEmployeeSer;

    @Override
    public BeforeRemoveEmployeeBO save(BeforeRemoveEmployeeTO to) throws SerException {
        return beforeRemoveEmployeeSer.save(to);
    }

    @Override
    public BeforeRemoveEmployeeBO exam(BeforeRemoveEmployeeTO to) throws SerException {
        return beforeRemoveEmployeeSer.exam(to);
    }

    @Override
    public List<BeforeRemoveEmployeeBO> find(BeforeRemoveEmployeeDTO dto) throws SerException {
        return beforeRemoveEmployeeSer.find(dto);
    }

    @Override
    public BeforeRemoveEmployeeBO findByID(String id) throws SerException {
        return beforeRemoveEmployeeSer.findByID(id);
    }

    @Override
    public BeforeRemoveEmployeeBO delete(String id) throws SerException {
        return beforeRemoveEmployeeSer.delete(id);
    }

    @Override
    public List<DismissionEmployeeBO> all() throws SerException {
        return beforeRemoveEmployeeSer.all();
    }
}