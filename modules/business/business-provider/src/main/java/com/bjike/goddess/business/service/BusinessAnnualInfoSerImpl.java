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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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

    @Override
    public Long countBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws SerException {
        businessAnnualInfoDTO.getSorts().add("createTime=desc");
        Long counts = super.count(businessAnnualInfoDTO);
        return counts;
    }
    @Override
    public BusinessAnnualInfoBO getOne(String id) throws SerException {
        BusinessAnnualInfo businessAnnualInfo = super.findById(id);
        return BeanTransform.copyProperties(businessAnnualInfo,BusinessAnnualInfoBO.class,true);
    }

    @Override
    public List<BusinessAnnualInfoBO> findListBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws SerException {
        businessAnnualInfoDTO.getSorts().add("createTime=desc");
        List<BusinessAnnualInfo> businessAnnualInfos = super.findByCis(businessAnnualInfoDTO,true);
        List<BusinessAnnualInfoBO> businessAnnualInfoBOS = BeanTransform.copyProperties(businessAnnualInfos, BusinessRegisterBO.class,true);
        return businessAnnualInfoBOS;
    }

    @Override
    public BusinessAnnualInfoBO insertBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws SerException {
        BusinessAnnualInfo businessAnnualInfo = BeanTransform.copyProperties(businessAnnualInfoTO,BusinessRegister.class,true);
        businessAnnualInfo.setCreateTime(LocalDateTime.now());
        super.save(businessAnnualInfo);
        return BeanTransform.copyProperties(businessAnnualInfo,BusinessRegisterBO.class);
    }

    @Override
    public BusinessAnnualInfoBO editBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws SerException {
        BusinessAnnualInfo businessAnnualInfo = super.findById(businessAnnualInfoTO.getId());
        BeanTransform.copyProperties(businessAnnualInfoTO,businessAnnualInfo,true);
        businessAnnualInfo.setModifyTime(LocalDateTime.now());
        super.update(businessAnnualInfo);
        return BeanTransform.copyProperties(businessAnnualInfoTO,BusinessRegisterBO.class);
    }

    @Override
    public void removeBusinessAnnualInfo(String id) throws SerException {
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