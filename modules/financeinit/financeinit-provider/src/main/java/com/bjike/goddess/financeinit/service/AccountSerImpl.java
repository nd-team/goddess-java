package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.AccountBO;
import com.bjike.goddess.financeinit.bo.FirstSubjectBO;
import com.bjike.goddess.financeinit.dto.AccountDTO;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.financeinit.entity.Account;
import com.bjike.goddess.financeinit.entity.Category;
import com.bjike.goddess.financeinit.entity.FirstSubject;
import com.bjike.goddess.financeinit.to.AccountTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 账户来源业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:25 ]
 * @Description: [ 账户来源业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class AccountSerImpl extends ServiceImpl<Account, AccountDTO> implements AccountSer {

    @Autowired
    private CategorySer categorySer;

    @Override
    public List<AccountBO> listAccount(AccountDTO accountDTO) throws SerException {
        List<Account> list = super.findByCis(accountDTO, true);

        List<AccountBO> cb = BeanTransform.copyProperties(list, AccountBO.class);

        return cb;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountBO addAccount(AccountTO accountTO) throws SerException {
        Account account = BeanTransform.copyProperties(accountTO, Account.class, true);
        account.setCreateTime(LocalDateTime.now());
        super.save(account);
        return BeanTransform.copyProperties(account, AccountBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountBO editAccount(AccountTO accountTO) throws SerException {
        if (StringUtils.isBlank(accountTO.getId())) {
            throw new SerException("id为空，输入不正确");
        }
        Account account = super.findById(accountTO.getId());
        Account temp = BeanTransform.copyProperties(accountTO, Account.class, true);
        BeanUtils.copyProperties(temp, account, "id", "createTime");
        super.update(account);
        return BeanTransform.copyProperties(account, AccountBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAccount(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id为空，输入不正确");
        }
        super.remove(id);
    }

    @Override
    public List<String> getSecondSubject(AccountDTO accountDTO) throws SerException {
        if (StringUtils.isBlank(accountDTO.getFirstSubject())) {
            throw new SerException("一级级别名不能为空");
        }
        CategoryDTO cdto = new CategoryDTO();
        cdto.setSecondSubject(accountDTO.getFirstSubject());
        List<String> list = categorySer.getSecondSubject(cdto);
        return list;
    }

    @Override
    public List<String> getThirdSubject(AccountDTO accountDTO) throws SerException {
        if (StringUtils.isBlank(accountDTO.getFirstSubject())) {
            throw new SerException("一级级别名不能为空");
        }
        CategoryDTO cdto = new CategoryDTO();
        cdto.setSecondSubject(accountDTO.getFirstSubject());
        cdto.setSecondSubject(accountDTO.getSecondSubject());
        List<String> list = categorySer.getThirdSubject(cdto);
        return list;
    }
}