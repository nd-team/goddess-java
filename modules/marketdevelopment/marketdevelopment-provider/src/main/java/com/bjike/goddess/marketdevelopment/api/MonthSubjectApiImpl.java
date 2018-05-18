package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.MonthMonthMoneyBO;
import com.bjike.goddess.marketdevelopment.bo.MonthSubjectBO;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectDTO;
import com.bjike.goddess.marketdevelopment.service.MonthSubjectSer;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectUpdateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 月计划的业务科目业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-06 05:04 ]
* @Description:	[ 月计划的业务科目业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("monthSubjectApiImpl")
public class MonthSubjectApiImpl implements MonthSubjectAPI  {
    @Autowired
    private MonthSubjectSer monthSubjectSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return monthSubjectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<MonthMonthMoneyBO> maps(MonthSubjectDTO dto) throws SerException {
        return monthSubjectSer.maps(dto);
    }

    @Override
    public void save(MonthSubjectTO to) throws SerException {
        monthSubjectSer.save(to);
    }

    @Override
    public void update(MonthSubjectUpdateTO to) throws SerException {
        monthSubjectSer.update(to);
    }

    @Override
    public void delete(String monthSubjectId) throws SerException {
        monthSubjectSer.delete(monthSubjectId);
    }

    @Override
    public MonthSubjectBO getById(String monthSubjectId) throws SerException {
        return monthSubjectSer.getById(monthSubjectId);
    }

    @Override
    public Long getTotal(MonthSubjectDTO dto) throws SerException {
        return monthSubjectSer.getTotal(dto);
    }

    @Override
    public void congeal(String monthSubjectId) throws SerException {
        monthSubjectSer.congeal(monthSubjectId);
    }

    @Override
    public void thaw(String monthSubjectId) throws SerException {
        monthSubjectSer.thaw(monthSubjectId);
    }
}