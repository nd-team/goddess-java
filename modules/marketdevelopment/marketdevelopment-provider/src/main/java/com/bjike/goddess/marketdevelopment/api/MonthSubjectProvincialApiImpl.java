package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.MonthMoneyProvincialBO;
import com.bjike.goddess.marketdevelopment.bo.MonthSubjectProvincialUpdateBO;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectProvincialDTO;
import com.bjike.goddess.marketdevelopment.service.MonthSubjectProvincialSer;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectProvincialADDTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectProvincialUpdateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 月计划省市方向业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:29 ]
 * @Description: [ 月计划省市方向业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("monthSubjectProvincialApiImpl")
public class MonthSubjectProvincialApiImpl implements MonthSubjectProvincialAPI {
    @Autowired
    private MonthSubjectProvincialSer monthSubjectProvincialSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return monthSubjectProvincialSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<MonthMoneyProvincialBO> maps(MonthSubjectProvincialDTO dto) throws SerException {
        return monthSubjectProvincialSer.maps(dto);
    }

    @Override
    public void save(MonthSubjectProvincialADDTO to) throws SerException {
        monthSubjectProvincialSer.save(to);
    }

    @Override
    public void update(MonthSubjectProvincialUpdateTO to) throws SerException {
        monthSubjectProvincialSer.update(to);
    }

    @Override
    public void delete(String provincialAreaId) throws SerException {
        monthSubjectProvincialSer.delete(provincialAreaId);
    }

    @Override
    public Long getTotal(MonthSubjectProvincialDTO dto) throws SerException {
        return monthSubjectProvincialSer.getTotal(dto);
    }

    @Override
    public MonthSubjectProvincialUpdateBO getById(String provincialAreaId) throws SerException {
        return monthSubjectProvincialSer.getById(provincialAreaId);
    }

    @Override
    public void congeal(String monthmoneyId) throws SerException {
        monthSubjectProvincialSer.congeal(monthmoneyId);
    }

    @Override
    public void thaw(String monthmoneyId) throws SerException {
        monthSubjectProvincialSer.thaw(monthmoneyId);
    }

    @Override
    public Double findActualMoney(String year, String month) throws SerException {
        return monthSubjectProvincialSer.findActualMoney(year, month);
    }

    @Override
    public Double findActualMoney1(String year, String month, String businessType) throws SerException {
        return monthSubjectProvincialSer.findActualMoney1(year, month, businessType);
    }
}