package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.TemperatureSubsidiesBO;
import com.bjike.goddess.assistance.dto.TemperatureSubsidiesDTO;
import com.bjike.goddess.assistance.service.TemperatureSubsidiesSer;
import com.bjike.goddess.assistance.to.TemperatureSubsidiesExcelTO;
import com.bjike.goddess.assistance.to.TemperatureSubsidiesTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 高温补助业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:38 ]
 * @Description: [ 高温补助业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("temperatureSubsidiesApiImpl")
public class TemperatureSubsidiesApiImpl implements TemperatureSubsidiesAPI {
    @Autowired
    private TemperatureSubsidiesSer temperatureSubsidiesSer;

    @Override
    public Long countTempera(TemperatureSubsidiesDTO temperatureSubsidiesDTO) throws SerException {
        return temperatureSubsidiesSer.countTempera(temperatureSubsidiesDTO);
    }

    @Override
    public TemperatureSubsidiesBO getOneById(String id) throws SerException {
        return temperatureSubsidiesSer.getOneById(id);
    }

    @Override
    public List<TemperatureSubsidiesBO> listTempera(TemperatureSubsidiesDTO temperatureSubsidiesDTO) throws SerException {
        return temperatureSubsidiesSer.listTempera(temperatureSubsidiesDTO);
    }

    @Override
    public void saveTempera(TemperatureSubsidiesTO temperatureSubsidiesTO) throws SerException {
        temperatureSubsidiesSer.saveTempera(temperatureSubsidiesTO);
    }

    @Override
    public void editTempera(TemperatureSubsidiesTO temperatureSubsidiesTO) throws SerException {
        temperatureSubsidiesSer.editTempera(temperatureSubsidiesTO);
    }

    @Override
    public void deleteTemp(String id) throws SerException {
        temperatureSubsidiesSer.deleteTemp(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return temperatureSubsidiesSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return temperatureSubsidiesSer.templateExport();
    }

    @Override
    public void importExcel(List<TemperatureSubsidiesExcelTO> temperatureSubsidiesExcelTOS) throws SerException {
        temperatureSubsidiesSer.importExcel(temperatureSubsidiesExcelTOS);
    }

    @Override
    public void remindingConfirm(String id) throws SerException {
        temperatureSubsidiesSer.remindingConfirm(id);
    }

    @Override
    public void confirm(String id, Boolean confirm) throws SerException {
        temperatureSubsidiesSer.confirm(id, confirm);
    }

    @Override
    public TemperatureSubsidiesBO findTemperature(String paytStartTime, String payEndTime) throws SerException {
        return temperatureSubsidiesSer.findTemperature(paytStartTime,payEndTime);
    }
}