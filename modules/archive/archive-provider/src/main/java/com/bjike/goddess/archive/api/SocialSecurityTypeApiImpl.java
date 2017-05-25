package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.SocialSecurityTypeBO;
import com.bjike.goddess.archive.dto.SocialSecurityTypeDTO;
import com.bjike.goddess.archive.service.SocialSecurityTypeSer;
import com.bjike.goddess.archive.to.SocialSecurityTypeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司社保购买类型业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:08 ]
 * @Description: [ 公司社保购买类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("socialSecurityTypeApiImpl")
public class SocialSecurityTypeApiImpl implements SocialSecurityTypeAPI {

    @Autowired
    private SocialSecurityTypeSer socialSecurityTypeSer;

    @Override
    public SocialSecurityTypeBO save(SocialSecurityTypeTO to) throws SerException {
        return socialSecurityTypeSer.save(to);
    }

    @Override
    public SocialSecurityTypeBO update(SocialSecurityTypeTO to) throws SerException {
        return socialSecurityTypeSer.update(to);
    }

    @Override
    public SocialSecurityTypeBO delete(String id) throws SerException {
        return socialSecurityTypeSer.delete(id);
    }

    @Override
    public SocialSecurityTypeBO congeal(String id) throws SerException {
        return socialSecurityTypeSer.congeal(id);
    }

    @Override
    public SocialSecurityTypeBO thaw(String id) throws SerException {
        return socialSecurityTypeSer.thaw(id);
    }

    @Override
    public List<SocialSecurityTypeBO> findByStatus(Status status) throws SerException {
        return socialSecurityTypeSer.findByStatus(status);
    }

    @Override
    public List<SocialSecurityTypeBO> maps(SocialSecurityTypeDTO dto) throws SerException {
        return socialSecurityTypeSer.maps(dto);
    }

    @Override
    public SocialSecurityTypeBO getById(String id) throws SerException {
        return socialSecurityTypeSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return socialSecurityTypeSer.getTotal();
    }
}