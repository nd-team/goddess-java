package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.RestrictionType;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CustomerLevelDTO;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.to.CustomerLevelTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户级别业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T17:00:40.879 ]
 * @Description: [ 客户级别业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CustomerLevelSerImpl extends ServiceImpl<CustomerLevel, CustomerLevelDTO> implements CustomerLevelSer {

    @Override
    public Long countCustomerLevel(CustomerLevelDTO customerLevelDTO) throws SerException {
        Long count = super.count(customerLevelDTO);
        return count;
    }

    @Override
    public List<CustomerLevelBO> listCustomerLevel(CustomerLevelDTO customerLevelDTO) throws SerException {
        customerLevelDTO.getSorts().add("name=asc");
        List<CustomerLevel> list = super.findByCis(customerLevelDTO,true);

        return BeanTransform.copyProperties(list, CustomerLevelBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerLevelBO addCustomerLevel(CustomerLevelTO customerLevelTO) throws SerException {
        CustomerLevel customerLevel = BeanTransform.copyProperties(customerLevelTO,CustomerLevel.class,true);
        customerLevel.setCreateTime(LocalDateTime.now());
        super.save( customerLevel );
        return BeanTransform.copyProperties(customerLevel, CustomerLevelBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerLevelBO editCustomerLevel(CustomerLevelTO customerLevelTO) throws SerException {
        CustomerLevel customerLevel = BeanTransform.copyProperties(customerLevelTO,CustomerLevel.class,true);
        CustomerLevel cusLevel = super.findById( customerLevelTO.getId() );

        cusLevel.setName( customerLevel.getName() );
        cusLevel.setRemark( customerLevel.getRemark() );
        cusLevel.setName( customerLevel.getName() );
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(customerLevel, CustomerLevelBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCustomerLevel(String id) throws SerException {

        super.remove( id );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCustomerLevel(String id) throws SerException {
        CustomerLevel customerLevel = super.findById( id );
        customerLevel.setStatus(Status.CONGEAL);
        customerLevel.setModifyTime(LocalDateTime.now());
        super.update( customerLevel );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCustomerLevel(String id) throws SerException {
        CustomerLevel customerLevel = super.findById( id );
        customerLevel.setStatus(Status.THAW);
        customerLevel.setModifyTime(LocalDateTime.now());
        super.update( customerLevel );
    }

    
    @Override
    public CustomerLevelBO getCustomerLevelByName(String name) throws SerException {
        CustomerLevelDTO dto = new CustomerLevelDTO();
        dto.getConditions().add(Restrict.eq("name",name));

        CustomerLevel customerLevel = super.findOne( dto );
        return BeanTransform.copyProperties(customerLevel ,CustomerLevelBO.class);
    }

    @Override
    public List<String> getAllLevel() throws SerException {
        String[] fields = new String[]{"name"};
        List<CustomerLevelBO> customerLevelBOS =super.findBySql("select name,1 from customer_customerlevel order by name asc ", CustomerLevelBO.class, fields);

        List<String> levelList  = customerLevelBOS.stream().map(CustomerLevelBO::getName).distinct().collect(Collectors.toList());


        return levelList;
    }
}