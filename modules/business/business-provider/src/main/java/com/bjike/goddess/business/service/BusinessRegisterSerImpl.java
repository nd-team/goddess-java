package com.bjike.goddess.business.service;

import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.to.BusinessRegisterTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.business.dto.BusinessRegisterDTO;
import com.bjike.goddess.business.entity.BusinessRegister;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工商注册业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessSerCache")
@Service
public class BusinessRegisterSerImpl extends ServiceImpl<BusinessRegister, BusinessRegisterDTO> implements BusinessRegisterSer {

    @Override
    public Long countBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        businessRegisterDTO.getSorts().add("createTime=desc");
        Long counts = super.count(businessRegisterDTO);
        return counts;
    }
    @Override
    public BusinessRegisterBO getOne(String id) throws SerException {
        BusinessRegister businessRegister = super.findById(id);
        return BeanTransform.copyProperties(businessRegister,BusinessRegisterBO.class,true);
    }
    @Override
    public List<BusinessRegisterBO> findListBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        businessRegisterDTO.getSorts().add("createTime=desc");
        List<BusinessRegister> businessRegisters = super.findByCis(businessRegisterDTO,true);
        List<BusinessRegisterBO> businessRegisterBOS = BeanTransform.copyProperties(businessRegisters,BusinessRegisterBO.class,true);
        return businessRegisterBOS;
    }

    @Override
    public BusinessRegisterBO insertBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        BusinessRegister businessRegister = BeanTransform.copyProperties(businessRegisterTO,BusinessRegister.class,true);
        businessRegister.setCreateTime(LocalDateTime.now());
        super.save(businessRegister);
        return BeanTransform.copyProperties(businessRegister,BusinessRegisterBO.class);
    }

    @Override
    public BusinessRegisterBO editBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        BusinessRegister businessRegister = super.findById(businessRegisterTO.getId());
        BeanTransform.copyProperties(businessRegisterTO,businessRegister,true);
        businessRegister.setModifyTime(LocalDateTime.now());
        super.update(businessRegister);
        return BeanTransform.copyProperties(businessRegisterTO,BusinessRegisterBO.class);
    }

    @Override
    public void removeBusinessRegister(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        super.remove(id);
    }
    @Override
    public void upload() throws SerException {
        //todo 未做上传
        return;

    }
    @Override
    public void download() throws SerException {
        //todo 未做下载
        return;

    }

}