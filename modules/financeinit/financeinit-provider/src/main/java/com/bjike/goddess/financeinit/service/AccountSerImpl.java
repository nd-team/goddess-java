package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.AccountBO;
import com.bjike.goddess.financeinit.dto.AccountDTO;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.financeinit.entity.Account;
import com.bjike.goddess.financeinit.to.AccountTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Long countAccount(AccountDTO accountDTO) throws SerException {
        Long count = super.count(accountDTO);
        return count;
    }

    @Override
    public AccountBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        Account account = super.findById(id);
        return BeanTransform.copyProperties(account, AccountBO.class);
    }

    @Override
    public List<AccountBO> listAccount(AccountDTO accountDTO) throws SerException {
        List<Account> list = super.findByCis(accountDTO, true);

        List<AccountBO> cb = BeanTransform.copyProperties(list, AccountBO.class);

        return cb;
    }

    @Override
    //cjh
    public Set<String> allNames(AccountDTO accountDTO) throws SerException {
        List<Account> list = super.findByCis(accountDTO);
        List<AccountBO> cb = BeanTransform.copyProperties(list, AccountBO.class);
        Set<String> set = new HashSet<String>();
        for (AccountBO a : cb) {
            set.add(a.getName());
        }
        return set;
    }

    @Override
    //cjh
    public String findByName(String name) throws SerException {
        AccountDTO dto = new AccountDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        List<Account> list = super.findByCis(dto);
        for (Account a : list) {
            return list.get(0).getAccount();
        }
        return null;
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
        account.setModifyTime(LocalDateTime.now());
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
        cdto.setFirstSubjectName(accountDTO.getFirstSubject());
        List<String> list = categorySer.getSecondSubject(cdto);
        return list;
    }

    @Override
    public List<String> getThirdSubject(AccountDTO accountDTO) throws SerException {
        if (StringUtils.isBlank(accountDTO.getFirstSubject())) {
            throw new SerException("一级级别名不能为空");
        }
        CategoryDTO cdto = new CategoryDTO();
        cdto.setFirstSubjectName(accountDTO.getFirstSubject());
        cdto.setSecondSubject(accountDTO.getSecondSubject());
        List<String> list = categorySer.getThirdSubject(cdto);
        return list;
    }

    @Override
    public List<String> listAccountOrigin() throws SerException {
        AccountDTO accountDTO = new AccountDTO();
        List<Account> list = super.findByCis(accountDTO);

        List<String> accountOrigin = list.stream().map(Account::getName).collect(Collectors.toList());
        return accountOrigin;
    }
}