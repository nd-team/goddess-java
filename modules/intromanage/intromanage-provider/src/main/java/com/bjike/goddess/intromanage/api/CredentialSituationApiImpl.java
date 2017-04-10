package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.CredentialSituationBO;
import com.bjike.goddess.intromanage.dto.CredentialSituationDTO;
import com.bjike.goddess.intromanage.service.CredentialSituationSer;
import com.bjike.goddess.intromanage.to.CredentialSituationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 证书情况业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:52 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("credentialSituationApiImpl")
public class CredentialSituationApiImpl implements CredentialSituationAPI {

    @Autowired
    private CredentialSituationSer credentialSituationSer;

    /**
     * 分页查询证书情况
     *
     * @return class CredentialSituationBO
     * @throws SerException
     */
    @Override
    public List<CredentialSituationBO> list(CredentialSituationDTO dto) throws SerException {
        return credentialSituationSer.list(dto);
    }

    /**
     * 保存证书情况
     *
     * @param to 证书情况to
     * @return class CredentialSituationBO
     * @throws SerException
     */
    @Override
    public CredentialSituationBO save(CredentialSituationTO to) throws SerException {
        return credentialSituationSer.save(to);
    }

    /**
     * 根据id删除证书情况
     *
     * @param id 证书情况唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        credentialSituationSer.remove(id);
    }

    /**
     * 更新证书情况
     *
     * @param to 证书情况to
     * @throws SerException
     */
    @Override
    public void update(CredentialSituationTO to) throws SerException {
        credentialSituationSer.update(to);
    }
}