package com.bjike.goddess.datastore.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.bo.AccountPwdSpecificationBO;
import com.bjike.goddess.datastore.dto.AccountPwdSpecificationDTO;
import com.bjike.goddess.datastore.entity.AccountPwdSpecification;
import com.bjike.goddess.datastore.to.AccountPwdSpecificationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据存储账号密码规范业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 06:14 ]
 * @Description: [ 数据存储账号密码规范业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "datastoreSerCache")
@Service
public class AccountPwdSpecificationSerImpl extends ServiceImpl<AccountPwdSpecification, AccountPwdSpecificationDTO> implements AccountPwdSpecificationSer {

    @Override
    public Long countAccountPwdSpecification(AccountPwdSpecificationDTO accountPwdSpecificationDTO) throws SerException {
        accountPwdSpecificationDTO.getSorts().add("createTime=desc");
        Long count = super.count(accountPwdSpecificationDTO);
        return count;
    }
    @Override
    public AccountPwdSpecificationBO getOne(String id) throws SerException {
        AccountPwdSpecification accountPwdSpecification = super.findById(id);
        return BeanTransform.copyProperties(accountPwdSpecification, AccountPwdSpecificationBO.class);
    }

    @Override
    public List<AccountPwdSpecificationBO> findListAccountPwdSpecification(AccountPwdSpecificationDTO accountPwdSpecificationDTO) throws SerException {
        accountPwdSpecificationDTO.getSorts().add("createTime=desc");
        List<AccountPwdSpecification> accountPwdSpecifications = super.findByCis(accountPwdSpecificationDTO, true);
        List<AccountPwdSpecificationBO> accountPwdSpecificationBOS = BeanTransform.copyProperties(accountPwdSpecifications, AccountPwdSpecificationBO.class);
        return accountPwdSpecificationBOS;
    }

    @Override
    public AccountPwdSpecificationBO insertAccountPwdSpecification(AccountPwdSpecificationTO accountPwdSpecificationTO) throws SerException {
        AccountPwdSpecification accountPwdSpecification = BeanTransform.copyProperties(accountPwdSpecificationTO, AccountPwdSpecification.class, true);
        accountPwdSpecification.setCreateTime(LocalDateTime.now());
        super.save(accountPwdSpecification);
        return BeanTransform.copyProperties(accountPwdSpecification, AccountPwdSpecificationBO.class);
    }

    @Override
    public AccountPwdSpecificationBO editAccountPwdSpecification(AccountPwdSpecificationTO accountPwdSpecificationTO) throws SerException {
        AccountPwdSpecification accountPwdSpecification = super.findById(accountPwdSpecificationTO.getId());
        BeanTransform.copyProperties(accountPwdSpecificationTO, accountPwdSpecification, true);
        accountPwdSpecification.setModifyTime(LocalDateTime.now());
        super.update(accountPwdSpecification);
        return BeanTransform.copyProperties(accountPwdSpecificationTO, AccountPwdSpecificationBO.class);
    }

    @Override
    public void removeAccountPwdSpecification(String id) throws SerException {
        super.remove(id);
    }
}