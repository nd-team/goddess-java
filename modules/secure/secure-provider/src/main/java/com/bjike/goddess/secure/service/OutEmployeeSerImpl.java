package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.bo.DimissionInfoBO;
import com.bjike.goddess.secure.bo.OutEmployeeBO;
import com.bjike.goddess.secure.dto.OutEmployeeDTO;
import com.bjike.goddess.secure.entity.OutEmployee;
import com.bjike.goddess.secure.to.OutEmployeeTO;
import org.springframework.beans.BeanUtils;
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
    private DimissionInfoAPI dimissionInfoAPI;
    private boolean b=true;

    @Override
    @Transactional
    public void save() throws SerException {
        List<DimissionInfoBO> list=dimissionInfoAPI.all();
        for(DimissionInfoBO bo:list){
            OutEmployee outEmployee=new OutEmployee();
            BeanUtils.copyProperties(bo,outEmployee);
            super.save(outEmployee);
        }
    }

    @Override
    @Transactional
    public OutEmployeeBO is_again(OutEmployeeTO to) throws SerException {
        OutEmployee outEmployee=super.findById(to.getId());
        outEmployee.setIs_again(to.getIsAgain());
        outEmployee.setAdvice(to.getAdvice());
        super.update(outEmployee);
        return BeanTransform.copyProperties(outEmployee,OutEmployeeBO.class);
    }

    @Override
    @Transactional
    public OutEmployeeBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public List<OutEmployeeBO> find(OutEmployeeDTO dto) throws SerException {
        if(b){
            this.save();
            b=false;
        }
        List<OutEmployee> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,OutEmployeeBO.class);
    }

    @Override
    public OutEmployeeBO findByID(String id) throws SerException {
        OutEmployee outEmployee=super.findById(id);
        return BeanTransform.copyProperties(outEmployee,OutEmployeeBO.class);
    }
}