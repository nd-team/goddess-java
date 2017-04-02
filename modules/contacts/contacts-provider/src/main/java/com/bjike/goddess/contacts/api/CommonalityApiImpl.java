package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.service.CommonalitySer;
import com.bjike.goddess.contacts.to.CommonalityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公共邮箱管理业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("commonalityApiImpl")
public class CommonalityApiImpl implements CommonalityAPI {

    @Autowired
    private CommonalitySer commonalitySer;

    @Override
    public CommonalityBO save(CommonalityTO to) throws SerException {
        return commonalitySer.save(to);
    }

    @Override
    public CommonalityBO update(CommonalityTO to) throws SerException {
        return commonalitySer.update(to);
    }

    @Override
    public CommonalityBO delete(CommonalityTO to) throws SerException {
        return commonalitySer.delete(to);
    }

    @Override
    public CommonalityBO congeal(CommonalityTO to) throws SerException {
        return commonalitySer.congeal(to);
    }

    @Override
    public CommonalityBO thaw(CommonalityTO to) throws SerException {
        return commonalitySer.thaw(to);
    }

    @Override
    public List<CommonalityBO> findThaw() throws SerException {
        return commonalitySer.findThaw();
    }

    @Override
    public List<CommonalityBO> maps(CommonalityDTO dto) throws SerException {
        return commonalitySer.maps(dto);
    }

    @Override
    public CommonalityBO findByDepartment(String department) throws SerException {
        return commonalitySer.findByDepartment(department);
    }
}