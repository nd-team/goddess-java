package com.bjike.goddess.business.service;

import com.bjike.goddess.business.bo.BusinessTaxChangeBO;
import com.bjike.goddess.business.to.BusinessTaxChangeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.business.dto.BusinessTaxChangeDTO;
import com.bjike.goddess.business.entity.BusinessTaxChange;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工商税务变更业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:56 ]
 * @Description: [ 工商税务变更业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessSerCache")
@Service
public class BusinessTaxChangeSerImpl extends ServiceImpl<BusinessTaxChange, BusinessTaxChangeDTO> implements BusinessTaxChangeSer {

    @Override
    public Long countBusinessTaxChange(BusinessTaxChangeDTO businessTaxChangeDTO) throws SerException {
        businessTaxChangeDTO.getSorts().add("createTime=desc");
        Long counts = super.count(businessTaxChangeDTO);
        return counts;
    }
    @Override
    public BusinessTaxChangeBO getOne(String id) throws SerException {
        BusinessTaxChange businessTaxChange = super.findById(id);
        return BeanTransform.copyProperties(businessTaxChange,BusinessTaxChangeBO.class);
    }

    @Override
    public List<BusinessTaxChangeBO> findListBusinessTaxChange(BusinessTaxChangeDTO businessTaxChangeDTO) throws SerException {
        businessTaxChangeDTO.getSorts().add("createTime=desc");
        List<BusinessTaxChange> businessTaxChanges = super.findByCis(businessTaxChangeDTO,true);
        List<BusinessTaxChangeBO> businessTaxChangeBOS = BeanTransform.copyProperties(businessTaxChanges,BusinessTaxChangeBO.class);
        return businessTaxChangeBOS;
    }

    @Override
    public BusinessTaxChangeBO insertBusinessTaxChange(BusinessTaxChangeTO businessTaxChangeTO) throws SerException {
        BusinessTaxChange businessTaxChange = BeanTransform.copyProperties(businessTaxChangeTO,BusinessTaxChange.class,true);
        businessTaxChange.setCreateTime(LocalDateTime.now());
        super.save(businessTaxChange);
        return BeanTransform.copyProperties(businessTaxChange,BusinessTaxChangeBO.class);
    }

    @Override
    public BusinessTaxChangeBO editBusinessTaxChange(BusinessTaxChangeTO businessTaxChangeTO) throws SerException {
        BusinessTaxChange businessTaxChange = super.findById(businessTaxChangeTO.getId());
        BeanTransform.copyProperties(businessTaxChangeTO,businessTaxChange,true);
        businessTaxChange.setModifyTime(LocalDateTime.now());
        super.update(businessTaxChange);
        return BeanTransform.copyProperties(businessTaxChangeTO,BusinessTaxChangeBO.class);
    }

    @Override
    public void removeBusinessTaxChange(String id) throws SerException {
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