package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.AccountDepartmentBO;
import com.bjike.goddess.financeinit.dto.AccountDepartmentDTO;
import com.bjike.goddess.financeinit.entity.AccountDepartment;
import com.bjike.goddess.financeinit.to.AccountDepartmentTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 核算部门业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:43 ]
 * @Description: [ 核算部门业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class AccountDepartmentSerImpl extends ServiceImpl<AccountDepartment, AccountDepartmentDTO> implements AccountDepartmentSer {

    @Override
    public Long countDepart(AccountDepartmentDTO accountDepartmentDTO) throws SerException {
        Long count = super.count(accountDepartmentDTO);
        return count;
    }

    @Override
    public AccountDepartmentBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        AccountDepartment accountDepartment = super.findById(id);
        return BeanTransform.copyProperties(accountDepartment, AccountDepartmentBO.class);
    }

    @Override
    public List<AccountDepartmentBO> listDepart(AccountDepartmentDTO accountDepartmentDTO) throws SerException {
        List<AccountDepartment> list = super.findByCis(accountDepartmentDTO, true);
        return BeanTransform.copyProperties(list, AccountDepartmentBO.class);
    }

    @Override
    public AccountDepartmentBO addDepart(AccountDepartmentTO accountDepartmentTO) throws SerException {
        AccountDepartment accountDepartment = BeanTransform.copyProperties(accountDepartmentTO, AccountDepartment.class, true);
        accountDepartment.setCreateTime(LocalDateTime.now());
        super.save(accountDepartment);
        return BeanTransform.copyProperties(accountDepartment, AccountDepartmentBO.class);
    }

    @Override
    public AccountDepartmentBO editDepart(AccountDepartmentTO accountDepartmentTO) throws SerException {
        AccountDepartment accountDepartment = super.findById(accountDepartmentTO.getId());
        LocalDateTime date = accountDepartment.getCreateTime();
        accountDepartment = BeanTransform.copyProperties(accountDepartmentTO, AccountDepartment.class);
        accountDepartment.setCreateTime(date);
        accountDepartment.setModifyTime(LocalDateTime.now());
        super.update(accountDepartment);
        return BeanTransform.copyProperties(accountDepartment, AccountDepartmentBO.class);
    }

    @Override
    public void deleteDepart(String id) throws SerException {
        super.remove(id);
    }
}