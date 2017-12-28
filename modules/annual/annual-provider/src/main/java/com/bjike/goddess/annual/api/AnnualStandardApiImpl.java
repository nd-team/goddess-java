package com.bjike.goddess.annual.api;

import com.bjike.goddess.annual.bo.AnnualStandardBO;
import com.bjike.goddess.annual.dto.AnnualStandardDTO;
import com.bjike.goddess.annual.excel.SonPermissionObject;
import com.bjike.goddess.annual.service.AnnualStandardSer;
import com.bjike.goddess.annual.to.AnnualStandardTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 年假标准业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:26 ]
 * @Description: [ 年假标准业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("annualStandardApiImpl")
public class AnnualStandardApiImpl implements AnnualStandardAPI {

    @Autowired
    private AnnualStandardSer annualStandardSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return annualStandardSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return annualStandardSer.guidePermission(guidePermissionTO);
    }

    @Override
    public AnnualStandardBO save(AnnualStandardTO to) throws SerException {
        return annualStandardSer.save(to);
    }

    @Override
    public AnnualStandardBO update(AnnualStandardTO to) throws SerException {
        return annualStandardSer.update(to);
    }

    @Override
    public AnnualStandardBO delete(AnnualStandardTO to) throws SerException {
        return annualStandardSer.delete(to);
    }

    @Override
    public AnnualStandardBO congeal(AnnualStandardTO to) throws SerException {
        return annualStandardSer.congeal(to);
    }

    @Override
    public AnnualStandardBO thaw(AnnualStandardTO to) throws SerException {
        return annualStandardSer.thaw(to);
    }

    @Override
    public List<AnnualStandardBO> findThaw() throws SerException {
        return annualStandardSer.findThaw();
    }

    @Override
    public AnnualStandardBO findBySeniority(Integer seniority) throws SerException {
        return annualStandardSer.findBySeniority(seniority);
    }

    @Override
    public List<AnnualStandardBO> maps(AnnualStandardDTO dto) throws SerException {
        return annualStandardSer.maps(dto);
    }

    @Override
    public AnnualStandardBO getById(String id) throws SerException {
        return annualStandardSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return annualStandardSer.getTotal();
    }
}