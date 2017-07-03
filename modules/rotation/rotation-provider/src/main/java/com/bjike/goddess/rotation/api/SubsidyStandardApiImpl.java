package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.SubsidyStandardBO;
import com.bjike.goddess.rotation.dto.SubsidyStandardDTO;
import com.bjike.goddess.rotation.service.SubsidyStandardSer;
import com.bjike.goddess.rotation.to.SubsidyStandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位补贴标准业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:06 ]
 * @Description: [ 岗位补贴标准业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("subsidyStandardApiImpl")
public class SubsidyStandardApiImpl implements SubsidyStandardAPI {

    @Autowired
    private SubsidyStandardSer subsidyStandardSer;

    @Override
    public SubsidyStandardBO save(SubsidyStandardTO to) throws SerException {
        return subsidyStandardSer.save(to);
    }

    @Override
    public SubsidyStandardBO update(SubsidyStandardTO to) throws SerException {
        return subsidyStandardSer.update(to);
    }

    @Override
    public SubsidyStandardBO delete(String id) throws SerException {
        return subsidyStandardSer.delete(id);
    }

    @Override
    public SubsidyStandardBO congeal(String id) throws SerException {
        return subsidyStandardSer.congeal(id);
    }

    @Override
    public SubsidyStandardBO thaw(String id) throws SerException {
        return subsidyStandardSer.thaw(id);
    }

    @Override
    public SubsidyStandardBO getById(String id) throws SerException {
        return subsidyStandardSer.getById(id);
    }

    @Override
    public List<SubsidyStandardBO> maps(SubsidyStandardDTO dto) throws SerException {
        return subsidyStandardSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return subsidyStandardSer.getTotal();
    }

    @Override
    public SubsidyStandardBO findByArrangement(String arrangement) throws SerException {
        return subsidyStandardSer.findByArrangement(arrangement);
    }

    @Override
    public List<SubsidyStandardBO> findThaw() throws SerException {
        return subsidyStandardSer.findThaw();
    }
}