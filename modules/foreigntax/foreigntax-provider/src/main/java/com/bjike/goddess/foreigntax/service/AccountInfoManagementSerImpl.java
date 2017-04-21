package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.AccountInfoManagementBO;
import com.bjike.goddess.foreigntax.dto.AccountInfoManagementDTO;
import com.bjike.goddess.foreigntax.entity.AccountInfoManagement;
import com.bjike.goddess.foreigntax.to.AccountInfoManagementTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 外账资料管理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:52 ]
 * @Description: [ 外账资料管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class AccountInfoManagementSerImpl extends ServiceImpl<AccountInfoManagement, AccountInfoManagementDTO> implements AccountInfoManagementSer {

    @Override
    public Long countAccountInfoManagement(AccountInfoManagementDTO accountInfoManagementDTO) throws SerException {
        accountInfoManagementDTO.getSorts().add("createTime=desc");
        Long count = super.count(accountInfoManagementDTO);
        return count;
    }

    @Override
    public List<AccountInfoManagementBO> findListAccountInfoManagement(AccountInfoManagementDTO accountInfoManagementDTO) throws SerException {
        accountInfoManagementDTO.getSorts().add("createTime=desc");
        List<AccountInfoManagement> accountInfoManagements = super.findByCis(accountInfoManagementDTO, true);
        List<AccountInfoManagementBO> accountInfoManagementBOS = BeanTransform.copyProperties(accountInfoManagements, AccountInfoManagementBO.class, true);
        return accountInfoManagementBOS;
    }

    @Override
    public AccountInfoManagementBO insertAccountInfoManagement(AccountInfoManagementTO accountInfoManagementTO) throws SerException {
        AccountInfoManagement accountInfoManagement = BeanTransform.copyProperties(accountInfoManagementTO, AccountInfoManagement.class, true);
        accountInfoManagement.setCreateTime(LocalDateTime.now());
        super.save(accountInfoManagement);
        return BeanTransform.copyProperties(accountInfoManagement, AccountInfoManagementBO.class);
    }

    @Override
    public AccountInfoManagementBO editAccountInfoManagement(AccountInfoManagementTO accountInfoManagementTO) throws SerException {
        AccountInfoManagement accountInfoManagement = super.findById(accountInfoManagementTO.getId());
        BeanTransform.copyProperties(accountInfoManagementTO, accountInfoManagement, true);
        accountInfoManagement.setModifyTime(LocalDateTime.now());
        super.update(accountInfoManagement);
        return BeanTransform.copyProperties(accountInfoManagementTO, AccountInfoManagementBO.class);
    }

    @Override
    public void removeAccountInfoManagement(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
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