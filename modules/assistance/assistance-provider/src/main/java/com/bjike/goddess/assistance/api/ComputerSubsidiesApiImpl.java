package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.ComputerSubsidiesBO;
import com.bjike.goddess.assistance.dto.ComputerSubsidiesDTO;
import com.bjike.goddess.assistance.service.ComputerSubsidiesSer;
import com.bjike.goddess.assistance.to.ComputerSubsidiesAddTO;
import com.bjike.goddess.assistance.to.ComputerSubsidiesExcelTO;
import com.bjike.goddess.assistance.to.ComputerSubsidiesTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电脑补助业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:59 ]
 * @Description: [ 电脑补助业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("computerSubsidiesApiImpl")
public class ComputerSubsidiesApiImpl implements ComputerSubsidiesAPI {
    @Autowired
    private ComputerSubsidiesSer computerSubsidiesSer;

    @Override
    public Long countComputer(ComputerSubsidiesDTO computerSubsidiesDTO) throws SerException {
        return computerSubsidiesSer.countComputer(computerSubsidiesDTO);
    }

    @Override
    public ComputerSubsidiesBO getOneById(String id) throws SerException {
        return computerSubsidiesSer.getOneById(id);
    }

    @Override
    public List<ComputerSubsidiesBO> listComputer(ComputerSubsidiesDTO computerSubsidiesDTO) throws SerException {
        return computerSubsidiesSer.listComputer(computerSubsidiesDTO);
    }

    @Override
    public void saveComputer(ComputerSubsidiesAddTO computerSubsidiesAddTO) throws SerException {
        computerSubsidiesSer.saveComputer(computerSubsidiesAddTO);
    }

    @Override
    public void editComputer(ComputerSubsidiesTO computerSubsidiesTO) throws SerException {
        computerSubsidiesSer.editComputer(computerSubsidiesTO);
    }

    @Override
    public void deleteTemp(String id) throws SerException {
        computerSubsidiesSer.deleteTemp(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return computerSubsidiesSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return computerSubsidiesSer.templateExport();
    }

    @Override
    public void importExcel(List<ComputerSubsidiesExcelTO> temperatureSubsidiesExcelTOS) throws SerException {
        computerSubsidiesSer.importExcel(temperatureSubsidiesExcelTOS);
    }

    @Override
    public void remindingConfirm(String id) throws SerException {
        computerSubsidiesSer.remindingConfirm(id);
    }

    @Override
    public void confirm(String id, Boolean confirm) throws SerException {
        computerSubsidiesSer.confirm(id, confirm);
    }

    @Override
    public ComputerSubsidiesBO findByComputer(String payStartTime, String payEndTime) throws SerException {
        return computerSubsidiesSer.findByComputer(payStartTime,payEndTime);
    }
}