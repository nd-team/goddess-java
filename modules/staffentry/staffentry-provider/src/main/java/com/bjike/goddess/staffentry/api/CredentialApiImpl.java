package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.service.CredentialSer;
import com.bjike.goddess.staffentry.to.CredentialTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 证书情况业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 15:51]
 * @Description: [证书情况业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("credentialApiImpl")
public class CredentialApiImpl implements CredentialAPI{

    @Autowired
    private CredentialSer credentialSer;

    @Override
    public void insertCredentials(List<CredentialTO> credentialTOs) throws SerException {
         credentialSer.insertCredentials(credentialTOs);
    }
}
