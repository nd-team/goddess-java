package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.CredentialSituationBO;
import com.bjike.goddess.intromanage.dto.CredentialSituationDTO;
import com.bjike.goddess.intromanage.to.CredentialSituationTO;

import java.util.List;

/**
 * 证书情况业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:52 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CredentialSituationAPI {

    /**
     * 分页查询证书情况
     *
     * @return class CredentialSituationBO
     * @throws SerException
     */
    List<CredentialSituationBO> list(CredentialSituationDTO dto) throws SerException;

    /**
     * 保存证书情况
     *
     * @param to 证书情况to
     * @return class CredentialSituationBO
     * @throws SerException
     */
    CredentialSituationBO save(CredentialSituationTO to) throws SerException;

    /**
     * 根据id删除证书情况
     *
     * @param id 证书情况唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新证书情况
     *
     * @param to 证书情况to
     * @throws SerException
     */
    void update(CredentialSituationTO to) throws SerException;

}