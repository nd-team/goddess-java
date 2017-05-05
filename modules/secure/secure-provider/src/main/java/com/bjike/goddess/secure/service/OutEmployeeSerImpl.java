package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
<<<<<<< HEAD
import com.bjike.goddess.secure.bo.DismissionEmployeeBO;
=======
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.bo.DimissionInfoBO;
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
import com.bjike.goddess.secure.bo.OutEmployeeBO;
import com.bjike.goddess.secure.dto.OutEmployeeDTO;
import com.bjike.goddess.secure.entity.OutEmployee;
import com.bjike.goddess.secure.to.OutEmployeeTO;
<<<<<<< HEAD
=======
import org.springframework.beans.BeanUtils;
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 离职名单业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 04:11 ]
 * @Description: [ 离职名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class OutEmployeeSerImpl extends ServiceImpl<OutEmployee, OutEmployeeDTO> implements OutEmployeeSer {
    @Autowired
<<<<<<< HEAD
    private BeforeRemoveEmployeeSer beforeRemoveEmployeeSer;
    private boolean b = true;
=======
    private DimissionInfoAPI dimissionInfoAPI;
    private boolean b=true;
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e

    @Override
    @Transactional
    public void save() throws SerException {
<<<<<<< HEAD
        List<DismissionEmployeeBO> list = beforeRemoveEmployeeSer.all();
        for (DismissionEmployeeBO bo : list) {
            OutEmployee outEmployee = new OutEmployee();
            outEmployee.setName(bo.getName());
=======
        List<DimissionInfoBO> list=dimissionInfoAPI.all();
        for(DimissionInfoBO bo:list){
            OutEmployee outEmployee=new OutEmployee();
            BeanUtils.copyProperties(bo,outEmployee);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
            super.save(outEmployee);
        }
    }

    @Override
    @Transactional
    public OutEmployeeBO is_again(OutEmployeeTO to) throws SerException {
<<<<<<< HEAD
        OutEmployee outEmployee = super.findById(to.getId());
        outEmployee.setIsAgain(to.getIsAgain());
        outEmployee.setAdvice(to.getAdvice());
        super.update(outEmployee);
        return BeanTransform.copyProperties(outEmployee, OutEmployeeBO.class);
=======
        OutEmployee outEmployee=super.findById(to.getId());
        outEmployee.setIsAgain(to.getIsAgain());
        outEmployee.setAdvice(to.getAdvice());
        super.update(outEmployee);
        return BeanTransform.copyProperties(outEmployee,OutEmployeeBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    @Transactional
    public OutEmployeeBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public List<OutEmployeeBO> find(OutEmployeeDTO dto) throws SerException {
<<<<<<< HEAD
        if (b) {
            this.save();
            b = false;
        }
        List<OutEmployee> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, OutEmployeeBO.class);
=======
        if(b){
            this.save();
            b=false;
        }
        List<OutEmployee> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,OutEmployeeBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    public OutEmployeeBO findByID(String id) throws SerException {
<<<<<<< HEAD
        OutEmployee outEmployee = super.findById(id);
        return BeanTransform.copyProperties(outEmployee, OutEmployeeBO.class);
=======
        OutEmployee outEmployee=super.findById(id);
        return BeanTransform.copyProperties(outEmployee,OutEmployeeBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }
}