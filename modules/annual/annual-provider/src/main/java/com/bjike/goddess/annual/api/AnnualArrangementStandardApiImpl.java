package com.bjike.goddess.annual.api;

import com.bjike.goddess.annual.bo.AnnualArrangementStandardBO;
import com.bjike.goddess.annual.dto.AnnualArrangementStandardDTO;
import com.bjike.goddess.annual.service.AnnualArrangementStandardSer;
import com.bjike.goddess.annual.to.AnnualArrangementStandardTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 年假层级标准业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:33 ]
 * @Description: [ 年假层级标准业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("annualArrangementStandardApiImpl")
public class AnnualArrangementStandardApiImpl implements AnnualArrangementStandardAPI {

    @Autowired
    private AnnualArrangementStandardSer annualArrangementStandardSer;


    @Override
    public AnnualArrangementStandardBO update(AnnualArrangementStandardTO to) throws SerException {
        return annualArrangementStandardSer.update(to);
    }

    @Override
    public List<AnnualArrangementStandardBO> findByStandard(String standard_id) throws SerException {
        return annualArrangementStandardSer.findByStandard(standard_id);
    }

    @Override
    public List<AnnualArrangementStandardBO> maps(AnnualArrangementStandardDTO dto) throws SerException {
        return annualArrangementStandardSer.maps(dto);
    }

    @Override
    public AnnualArrangementStandardBO findByArrangementStandard(String standard_id, String arrangement_id) throws SerException {
        return annualArrangementStandardSer.findByArrangementStandard(standard_id, arrangement_id);
    }

    @Override
    public AnnualArrangementStandardBO getById(String id) throws SerException {
        return annualArrangementStandardSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return annualArrangementStandardSer.getTotal();
    }
}