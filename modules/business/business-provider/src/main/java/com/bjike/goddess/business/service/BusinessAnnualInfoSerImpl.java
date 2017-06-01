package com.bjike.goddess.business.service;

import com.bjike.goddess.business.bo.BusinessAnnualInfoBO;
import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.entity.BusinessRegister;
import com.bjike.goddess.business.to.BusinessAnnualInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.business.dto.BusinessAnnualInfoDTO;
import com.bjike.goddess.business.entity.BusinessAnnualInfo;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工商年检信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:48 ]
 * @Description: [ 工商年检信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessSerCache")
@Service
public class BusinessAnnualInfoSerImpl extends ServiceImpl<BusinessAnnualInfo, BusinessAnnualInfoDTO> implements BusinessAnnualInfoSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Override
    public Long countBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws SerException {
        businessAnnualInfoDTO.getSorts().add("createTime=desc");
        Long counts = super.count(businessAnnualInfoDTO);
        return counts;
    }
    @Override
    public BusinessAnnualInfoBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        BusinessAnnualInfo businessAnnualInfo = super.findById(id);
        return BeanTransform.copyProperties(businessAnnualInfo,BusinessAnnualInfoBO.class);
    }

    @Override
    public List<BusinessAnnualInfoBO> findListBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您的帐号没有权限");
        }
        List<BusinessAnnualInfo> businessAnnualInfos = super.findByCis(businessAnnualInfoDTO,true);
        List<BusinessAnnualInfoBO> businessAnnualInfoBOS = BeanTransform.copyProperties(businessAnnualInfos, BusinessAnnualInfoBO.class);
        return businessAnnualInfoBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessAnnualInfoBO insertBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是财务人员，没有权限");
        }
        BusinessAnnualInfo businessAnnualInfo = BeanTransform.copyProperties(businessAnnualInfoTO,BusinessAnnualInfo.class,true);
        businessAnnualInfo.setCreateTime(LocalDateTime.now());
        super.save(businessAnnualInfo);
        return BeanTransform.copyProperties(businessAnnualInfo,BusinessAnnualInfoBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessAnnualInfoBO editBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws SerException {
        if(StringUtils.isBlank(businessAnnualInfoTO.getId())){
            throw new SerException("id不能为空");
        }
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是财务人员，没有权限");
        }
        BusinessAnnualInfo businessAnnualInfo = super.findById(businessAnnualInfoTO.getId());
        BeanTransform.copyProperties(businessAnnualInfoTO,businessAnnualInfo,true);
        businessAnnualInfo.setModifyTime(LocalDateTime.now());
        super.update(businessAnnualInfo);
        return BeanTransform.copyProperties(businessAnnualInfoTO,BusinessAnnualInfoBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBusinessAnnualInfo(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是财务人员，没有权限");
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