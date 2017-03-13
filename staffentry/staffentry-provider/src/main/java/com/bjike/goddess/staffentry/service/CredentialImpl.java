package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.dto.CredentialDTO;
import com.bjike.goddess.staffentry.entity.Credential;
import com.bjike.goddess.staffentry.to.CredentialTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 证书情况业务实现
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 16:00]
 * @Description: [证书情况业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "staffentrySerCache")
@Service
public class CredentialImpl extends ServiceImpl<Credential, CredentialDTO> implements CredentialSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void insertCredentials(List<CredentialTO> credentialTOs) throws SerException {
        List<Credential> credentials = new ArrayList<>(0);

        credentialTOs.stream().forEach(family -> {
            Credential credential = BeanTransform.copyProperties(family, Credential.class, true);
            credential.setCreateTime( LocalDateTime.now());
            credentials.add(credential);
        });
        if (credentials != null && credentials.size() > 0) {
            super.save(credentials);
        }
    }
}
