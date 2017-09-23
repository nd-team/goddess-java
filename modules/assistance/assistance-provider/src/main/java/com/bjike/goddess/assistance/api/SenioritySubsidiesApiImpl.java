package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.SenioritySubsidiesBO;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesDTO;
import com.bjike.goddess.assistance.entity.SenioritySubsidies;
import com.bjike.goddess.assistance.service.SenioritySubsidiesSer;
import com.bjike.goddess.assistance.to.SenioritySubsidiesTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工龄补助业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 11:34 ]
 * @Description: [ 工龄补助业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("senioritySubsidiesApiImpl")
public class SenioritySubsidiesApiImpl implements SenioritySubsidiesAPI {
    @Autowired
    private SenioritySubsidiesSer senioritySubsidiesSer;
    @Override
    public Long countSenSub(SenioritySubsidiesDTO senioritySubsidiesDTO) throws SerException {
        return senioritySubsidiesSer.countSenSub(senioritySubsidiesDTO);
    }

    @Override
    public SenioritySubsidiesBO getOneById(String id) throws SerException {
        return senioritySubsidiesSer.getOneById(id);
    }

    @Override
    public List<SenioritySubsidiesBO> listSenSub(SenioritySubsidiesDTO senioritySubsidiesDTO) throws SerException {
        return senioritySubsidiesSer.listSenSub(senioritySubsidiesDTO);
    }

    @Override
    public void saveSen(SenioritySubsidiesTO senioritySubsidiesTO) throws SerException {
        senioritySubsidiesSer.saveSen(senioritySubsidiesTO);
    }

    @Override
    public void editSen(SenioritySubsidiesTO senioritySubsidiesTO) throws SerException {
        senioritySubsidiesSer.editSen(senioritySubsidiesTO);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return senioritySubsidiesSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return senioritySubsidiesSer.templateExport();
    }

    @Override
    public void importExcel(List<SenioritySubsidiesTO> senioritySubsidiesTOS) throws SerException {
        senioritySubsidiesSer.importExcel(senioritySubsidiesTOS);
    }

    @Override
    public void checkDate() throws SerException {
        senioritySubsidiesSer.checkDate();
    }

    @Override
    public SenioritySubsidiesBO findAge(String employeeName) throws SerException {
        return senioritySubsidiesSer.findAge(employeeName);
    }
}