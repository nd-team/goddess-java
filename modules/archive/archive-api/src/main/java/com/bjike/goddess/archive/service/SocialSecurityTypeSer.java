package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.SocialSecurityTypeBO;
import com.bjike.goddess.archive.dto.SocialSecurityTypeDTO;
import com.bjike.goddess.archive.entity.SocialSecurityType;
import com.bjike.goddess.archive.to.SocialSecurityTypeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;

import java.util.List;

/**
 * 公司社保购买类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:08 ]
 * @Description: [ 公司社保购买类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SocialSecurityTypeSer extends Ser<SocialSecurityType, SocialSecurityTypeDTO> {

    default SocialSecurityTypeBO save(SocialSecurityTypeTO to) throws SerException {
        return null;
    }

    default SocialSecurityTypeBO update(SocialSecurityTypeTO to) throws SerException {
        return null;
    }

    default SocialSecurityTypeBO delete(String id) throws SerException {
        return null;
    }

    default SocialSecurityTypeBO congeal(String id) throws SerException {
        return null;
    }

    default SocialSecurityTypeBO thaw(String id) throws SerException {
        return null;
    }

    default List<SocialSecurityTypeBO> findByStatus(Status status) throws SerException {
        return null;
    }

    default List<SocialSecurityTypeBO> maps(SocialSecurityTypeDTO dto) throws SerException {
        return null;
    }
}