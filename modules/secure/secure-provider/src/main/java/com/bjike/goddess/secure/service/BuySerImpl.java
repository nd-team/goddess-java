package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.BuyBO;
import com.bjike.goddess.secure.dto.BuyDTO;
import com.bjike.goddess.secure.entity.Buy;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.to.AddEmployeeTO;
import com.bjike.goddess.secure.to.BuyTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 购买社保人员业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:45 ]
 * @Description: [ 购买社保人员业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class BuySerImpl extends ServiceImpl<Buy, BuyDTO> implements BuySer {
    @Autowired
    private AddEmployeeSer addEmployeeSer;
    @Autowired
    private EmployeeSecureSer employeeSecureSer;

    @Override
    public List<BuyBO> find(BuyDTO dto) throws SerException {
        List<Buy> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, BuyBO.class);
    }

    @Override
    @Transactional
    public BuyBO edit(BuyTO to) throws SerException {
        Buy buy = super.findById(to.getId());
        LocalDateTime a = buy.getCreateTime();
        LocalDateTime b = buy.getModifyTime();
        buy = BeanTransform.copyProperties(to, Buy.class, true);
        buy.setCreateTime(a);
        buy.setModifyTime(b);
        super.update(buy);
        if (buy.getExamine()) {   //审批通过，添加到社保增员中
            AddEmployeeTO addEmployeeTO = new AddEmployeeTO();
            BeanUtils.copyProperties(buy, addEmployeeTO);
            addEmployeeTO.setBornLocal(buy.getBorn());
            addEmployeeTO.setSecureCity(buy.getCity());
            addEmployeeTO.setType(buy.getSecureType());
            addEmployeeSer.save(addEmployeeTO);
            EmployeeSecure employeeSecure = new EmployeeSecure();
            BeanUtils.copyProperties(buy, employeeSecure);
            employeeSecure.setStatus("购买中");
            employeeSecureSer.save(employeeSecure);
        }
        return BeanTransform.copyProperties(buy, BuyBO.class);
    }

    @Override
    @Transactional
    public BuyBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public BuyBO findByID(String id) throws SerException {
        Buy buy = super.findById(id);
        return BeanTransform.copyProperties(buy, BuyBO.class);
    }

    @Override
    @Transactional
    public BuyBO save(BuyTO to) throws SerException {
        Buy buy = BeanTransform.copyProperties(to, Buy.class, true);
        buy = super.save(buy);
        return BeanTransform.copyProperties(buy, BuyBO.class);
    }
}