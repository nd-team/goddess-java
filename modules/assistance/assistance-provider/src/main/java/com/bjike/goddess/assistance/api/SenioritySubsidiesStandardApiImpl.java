package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.SenioritySubsidiesStandardBO;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesStandardDTO;
import com.bjike.goddess.assistance.entity.SenioritySubsidiesStandard;
import com.bjike.goddess.assistance.service.SenioritySubsidiesStandardSer;
import com.bjike.goddess.assistance.to.SenioritySubsidiesStandardTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工龄补助标准业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:07 ]
 * @Description: [ 工龄补助标准业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("senioritySubsidiesStandardApiImpl")
public class SenioritySubsidiesStandardApiImpl implements SenioritySubsidiesStandardAPI {
    private SenioritySubsidiesStandardSer senioritySubsidiesStandardSer;
    @Override
    public Long countSenior(SenioritySubsidiesStandardDTO senioritySubsidiesStandardDTO) throws SerException {
        return senioritySubsidiesStandardSer.countSenior(senioritySubsidiesStandardDTO);
    }

    @Override
    public SenioritySubsidiesStandardBO getOneById(String id) throws SerException {
        return senioritySubsidiesStandardSer.getOneById(id);
    }

    @Override
    public List<SenioritySubsidiesStandardBO> listSenior(SenioritySubsidiesStandardDTO senioritySubsidiesStandardDTO) throws SerException {
        return senioritySubsidiesStandardSer.listSenior(senioritySubsidiesStandardDTO);
    }

    @Override
    public SenioritySubsidiesStandardBO addSenior(SenioritySubsidiesStandardTO senioritySubsidiesStandardTO) throws SerException {
        return senioritySubsidiesStandardSer.addSenior(senioritySubsidiesStandardTO);
    }

    @Override
    public SenioritySubsidiesStandardBO editSenior(SenioritySubsidiesStandardTO senioritySubsidiesStandardTO) throws SerException {
        return senioritySubsidiesStandardSer.editSenior(senioritySubsidiesStandardTO);
    }

    @Override
    public void deleteSubsidy(String id) throws SerException {
        senioritySubsidiesStandardSer.deleteSubsidy(id);
    }
}