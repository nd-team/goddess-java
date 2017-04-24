package com.bjike.goddess.datastore.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.bo.NumSpecificationBO;
import com.bjike.goddess.datastore.dto.NumSpecificationDTO;
import com.bjike.goddess.datastore.entity.NumSpecification;
import com.bjike.goddess.datastore.to.NumSpecificationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据存储编号规范业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:35 ]
 * @Description: [ 数据存储编号规范业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "datastoreSerCache")
@Service
public class NumSpecificationSerImpl extends ServiceImpl<NumSpecification, NumSpecificationDTO> implements NumSpecificationSer {

    @Override
    public Long countNumSpecification(NumSpecificationDTO numSpecificationDTO) throws SerException {
        numSpecificationDTO.getSorts().add("createTime=desc");
        Long counts = super.count(numSpecificationDTO);
        return counts;
    }

    @Override
    public List<NumSpecificationBO> findListNumSpecification(NumSpecificationDTO numSpecificationDTO) throws SerException {
        numSpecificationDTO.getSorts().add("createTime=desc");
        List<NumSpecification> numSpecifications = super.findByCis(numSpecificationDTO,true);
        List<NumSpecificationBO> numSpecificationBOS = BeanTransform.copyProperties(numSpecifications,NumSpecificationBO.class,true);
        return numSpecificationBOS;
    }

    @Override
    public NumSpecificationBO insertNumSpecification(NumSpecificationTO numSpecificationTO) throws SerException {
        NumSpecification numSpecification = BeanTransform.copyProperties(numSpecificationTO,NumSpecification.class,true);
        numSpecification.setCreateTime(LocalDateTime.now());
        super.save(numSpecification);
        return BeanTransform.copyProperties(numSpecification,NumSpecificationBO.class);
    }

    @Override
    public NumSpecificationBO editNumSpecification(NumSpecificationTO numSpecificationTO) throws SerException {
        NumSpecification numSpecification = super.findById(numSpecificationTO.getId());
        BeanTransform.copyProperties(numSpecificationTO,numSpecification,true);
        numSpecification.setModifyTime(LocalDateTime.now());
        super.update(numSpecification);
        return BeanTransform.copyProperties(numSpecificationTO,NumSpecificationBO.class);
    }

    @Override
    public void removeNumSpecification(String id) throws SerException {
        if(StringUtils.isNotBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}