package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.EmployeeSecureAPI;
import com.bjike.goddess.secure.bo.SecureCartBO;
import com.bjike.goddess.secure.dto.SecureCartDTO;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.entity.SecureCart;
import com.bjike.goddess.secure.to.SecureCartTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 社保卡基本信息业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 01:44 ]
 * @Description: [ 社保卡基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class SecureCartSerImpl extends ServiceImpl<SecureCart, SecureCartDTO> implements SecureCartSer {
    @Autowired
    private EmployeeSecureSer employeeSecureSer;
    private boolean b=true;

    @Override
    @Transactional
    public void save() throws SerException {
        List<EmployeeSecure> list=employeeSecureSer.findAll();
        for(EmployeeSecure employeeSecure:list){
            if (employeeSecure.getStatus()!=null&&!employeeSecure.getStatus().equals("已减员成功")){
                SecureCart secureCart=new SecureCart();
                BeanUtils.copyProperties(employeeSecure,secureCart);
                secureCart.setEmployeeId(employeeSecure.getEmployeeNum());
                secureCart.setArrival(employeeSecure.getArrival());
                secureCart.setStatus("");
                super.save(secureCart);
            }
        }
    }

    @Override
    @Transactional
    public void edit(SecureCartTO to) throws SerException {
        SecureCart secureCart=super.findById(to.getId());
        secureCart=BeanTransform.copyProperties(to,SecureCart.class);
        super.update(secureCart);
    }

    @Override
    public List<SecureCartBO> list(SecureCartDTO dto) throws SerException {
        if(b){
            save();
            b=false;
        }
        List<SecureCart> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,SecureCartBO.class);
    }

    @Override
    public SecureCartBO findByID(String id) throws SerException {
        SecureCart secureCart=super.findById(id);
        return BeanTransform.copyProperties(secureCart,SecureCartBO.class);
    }

    @Override
    @Transactional
    public void delete(String id) throws SerException {
        super.remove(id);
    }
}