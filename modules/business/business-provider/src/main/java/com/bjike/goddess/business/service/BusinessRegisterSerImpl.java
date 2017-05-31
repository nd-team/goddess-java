package com.bjike.goddess.business.service;

import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.to.BusinessRegisterTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.business.dto.BusinessRegisterDTO;
import com.bjike.goddess.business.entity.BusinessRegister;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Override
    public Long countBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        businessRegisterDTO.getSorts().add("createTime=desc");
        Long counts = super.count(businessRegisterDTO);
        return counts;
    }
    @Override
    public BusinessRegisterBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        BusinessRegister businessRegister = super.findById(id);
        return BeanTransform.copyProperties(businessRegister,BusinessRegisterBO.class);
    }
    @Override
    public List<BusinessRegisterBO> findListBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您的帐号没有权限");
        }
        List<BusinessRegister> businessRegisters = super.findByCis(businessRegisterDTO,true);
        List<BusinessRegisterBO> businessRegisterBOS = BeanTransform.copyProperties(businessRegisters,BusinessRegisterBO.class,true);
        return businessRegisterBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessRegisterBO insertBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是财务人员，没有权限");
        }
        BusinessRegister businessRegister = BeanTransform.copyProperties(businessRegisterTO,BusinessRegister.class,true);
        businessRegister.setCreateTime(LocalDateTime.now());
        super.save(businessRegister);
        return BeanTransform.copyProperties(businessRegister,BusinessRegisterBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessRegisterBO editBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        if(StringUtils.isBlank(businessRegisterTO.getId())){
            throw new SerException("id不能为空");
        }
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是财务人员，没有权限");
        }
        BusinessRegister businessRegister = super.findById(businessRegisterTO.getId());
        BeanTransform.copyProperties(businessRegisterTO,businessRegister,true);
        businessRegister.setModifyTime(LocalDateTime.now());
        super.update(businessRegister);
        return BeanTransform.copyProperties(businessRegisterTO,BusinessRegisterBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBusinessRegister(String id) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是财务人员，没有权限");
        }
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