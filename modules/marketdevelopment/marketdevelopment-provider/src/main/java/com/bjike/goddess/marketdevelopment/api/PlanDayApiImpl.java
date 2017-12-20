package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.MarkProblemAcceBO;
import com.bjike.goddess.marketdevelopment.bo.PlanDayBO;
import com.bjike.goddess.marketdevelopment.dto.PlanDayDTO;
import com.bjike.goddess.marketdevelopment.excel.PlanDayImportExcel;
import com.bjike.goddess.marketdevelopment.service.PlanDaySer;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.PlanDayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 日计划业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-29 03:55 ]
* @Description:	[ 日计划业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("planDayApiImpl")
public class PlanDayApiImpl implements PlanDayAPI  {
    @Autowired
    private PlanDaySer planDaySer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return planDaySer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<PlanDayBO> maps(PlanDayDTO dto) throws SerException {
        return planDaySer.maps(dto);
    }

    @Override
    public void save(PlanDayTO to) throws SerException {
        planDaySer.save(to);
    }

    @Override
    public void update(PlanDayTO to) throws SerException {
        planDaySer.update(to);
    }

    @Override
    public void congeal(PlanDayTO to) throws SerException {
        planDaySer.congeal(to);
    }

    @Override
    public void thaw(PlanDayTO to) throws SerException {
        planDaySer.thaw(to);
    }

    @Override
    public void delete(PlanDayTO to) throws SerException {
        planDaySer.delete(to);
    }

    @Override
    public PlanDayBO getById(String id) throws SerException {
        return planDaySer.getById(id);
    }

    @Override
    public Long getTotal(PlanDayDTO dto) throws SerException {
        return planDaySer.count(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return planDaySer.templateExcel();
    }

    @Override
    public byte[] exportExcel(PlanDayDTO dto) throws SerException {
        return planDaySer.exportExcel(dto);
    }

    @Override
    public void upload(List<PlanDayImportExcel> tos) throws SerException {
        planDaySer.upload(tos);
    }

    @Override
    public List<String> findBusinessType() throws SerException {
        return planDaySer.findBusinessType();
    }

    @Override
    public List<String> findBusinessSub() throws SerException {
        return planDaySer.findBusinessSub();
    }

    @Override
    public List<String> findInterCode() throws SerException {
        return planDaySer.findInterCode();
    }

    @Override
    public MarkProblemAcceBO findProblemAcce(String interCode) throws SerException {
        return planDaySer.findProblemAcce(interCode);
    }

    @Override
    public List<String> findMarkCode() throws SerException {
        return planDaySer.findMarkCode();
    }

    @Override
    public String findInnerProject(String marketNum) throws SerException {
        return planDaySer.findInnerProject(marketNum);
    }
}