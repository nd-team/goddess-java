package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.DateDataBO;
import com.bjike.goddess.marketdevelopment.bo.MonthBusinessDataBO;
import com.bjike.goddess.marketdevelopment.bo.MonthMoneyBO;
import com.bjike.goddess.marketdevelopment.dto.DateDataDTO;
import com.bjike.goddess.marketdevelopment.service.DateDataSer;
import com.bjike.goddess.marketdevelopment.to.DateDataTO;
import com.bjike.goddess.marketdevelopment.to.DateDataUpdateTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日期数据业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dateDataApiImpl")
public class DateDataApiImpl implements DateDataAPI {
    @Autowired
    private DateDataSer dateDataSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return dateDataSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<MonthMoneyBO> maps(DateDataDTO dto) throws SerException {
        return dateDataSer.maps(dto);
    }

    @Override
    public void save(DateDataTO to) throws SerException {
        dateDataSer.save(to);
    }

    @Override
    public void update(DateDataUpdateTO to) throws SerException {
        dateDataSer.update(to);
    }

    @Override
    public void delete(String dateDataId) throws SerException {
        dateDataSer.delete(dateDataId);
    }

    @Override
    public Long getTotal(DateDataDTO dto) throws SerException {
        return dateDataSer.getTotal(dto);
    }

    @Override
    public DateDataBO getById(String dateDataId) throws SerException {
        return dateDataSer.getById(dateDataId);
    }

    @Override
    public MonthMoneyBO findMoneyData(String year, String month) throws SerException {
        return dateDataSer.findMoneyData(year, month);
    }

    @Override
    public MonthBusinessDataBO findBusinessData(String year, String month, String businessType) throws SerException {
        return dateDataSer.findBusinessData(year, month, businessType);
    }

    @Override
    public List<String> findDate(String year, String month, String cycle) throws SerException {
        return dateDataSer.findDate(year, month, cycle);
    }
}