package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
<<<<<<< HEAD
=======
import com.bjike.goddess.secure.api.EmployeeSecureAPI;
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
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

<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
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
<<<<<<< HEAD
    private boolean b = true;
=======
    private boolean b=true;
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e

    @Override
    @Transactional
    public void save() throws SerException {
<<<<<<< HEAD
        List<EmployeeSecure> list = employeeSecureSer.findAll();
        for (EmployeeSecure employeeSecure : list) {
            if (employeeSecure.getStatus() != null && !employeeSecure.getStatus().equals("已减员成功")) {
                SecureCart secureCart = new SecureCart();
                BeanUtils.copyProperties(employeeSecure, secureCart);
=======
        List<EmployeeSecure> list=employeeSecureSer.findAll();
        for(EmployeeSecure employeeSecure:list){
            if (employeeSecure.getStatus()!=null&&!employeeSecure.getStatus().equals("已减员成功")){
                SecureCart secureCart=new SecureCart();
                BeanUtils.copyProperties(employeeSecure,secureCart);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
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
<<<<<<< HEAD
        SecureCart secureCart = super.findById(to.getId());
        LocalDateTime a = secureCart.getCreateTime();
        LocalDateTime b = secureCart.getModifyTime();
        secureCart = BeanTransform.copyProperties(to, SecureCart.class);
        secureCart.setCreateTime(a);
        secureCart.setModifyTime(b);
=======
        SecureCart secureCart=super.findById(to.getId());
        secureCart=BeanTransform.copyProperties(to,SecureCart.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
        super.update(secureCart);
    }

    @Override
    public List<SecureCartBO> list(SecureCartDTO dto) throws SerException {
<<<<<<< HEAD
        if (b) {
            save();
            b = false;
        }
        List<SecureCart> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, SecureCartBO.class);
=======
        if(b){
            save();
            b=false;
        }
        List<SecureCart> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,SecureCartBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    public SecureCartBO findByID(String id) throws SerException {
<<<<<<< HEAD
        SecureCart secureCart = super.findById(id);
        return BeanTransform.copyProperties(secureCart, SecureCartBO.class);
=======
        SecureCart secureCart=super.findById(id);
        return BeanTransform.copyProperties(secureCart,SecureCartBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    @Transactional
    public void delete(String id) throws SerException {
        super.remove(id);
    }
}