package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.AddEmployeeBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.entity.AddEmployee;
import com.bjike.goddess.secure.to.AddEmployeeTO;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 社保增员信息名单业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 03:02 ]
 * @Description: [ 社保增员信息名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AddEmployeeSerImpl extends ServiceImpl<AddEmployee, AddEmployeeDTO> implements AddEmployeeSer {
    @Override
    public List<AddEmployeeBO> find(AddEmployeeDTO dto) throws SerException {
        List<AddEmployee> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,AddEmployeeBO.class);
    }

    @Override
    public AddEmployeeBO findByID(String id) throws SerException {
        AddEmployee addEmployee=super.findById(id);
        return BeanTransform.copyProperties(addEmployee,AddEmployeeBO.class);
    }

    @Override
    @Transactional
    public AddEmployeeBO edit(AddEmployeeTO to) throws SerException {
        AddEmployee addEmployee= super.findById(to.getId());
        addEmployee=BeanTransform.copyProperties(to,AddEmployee.class);
        super.update(addEmployee);
        return BeanTransform.copyProperties(addEmployee,AddEmployeeBO.class);
    }

    @Override
    @Transactional
    public AddEmployeeBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    @Transactional
    public AddEmployeeBO save(AddEmployeeTO to) throws SerException {
        AddEmployee addEmployee=BeanTransform.copyProperties(to,AddEmployee.class,true);
        super.save(addEmployee);
        return BeanTransform.copyProperties(addEmployee,AddEmployeeBO.class);
    }

    @Override
    public List<AddEmployeeBO> findALL() throws SerException {
        List<AddEmployee> list=super.findAll();
        return BeanTransform.copyProperties(list,AddEmployeeBO.class);
    }
}