package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.PlanYearSubjectUpdateBO;
import com.bjike.goddess.marketdevelopment.bo.PlanYearmapsBO;
import com.bjike.goddess.marketdevelopment.dto.PlanYearSubjectDTO;
import com.bjike.goddess.marketdevelopment.dto.PlanYearTypeDTO;
import com.bjike.goddess.marketdevelopment.excel.PlanYearmapsImportExcel;
import com.bjike.goddess.marketdevelopment.service.PlanYearSubjectSer;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearSubjectUpdateTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearmapsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 年计划(科目方向)业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-08 03:34 ]
* @Description:	[ 年计划(科目方向)业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("planYearSubjectApiImpl")
public class PlanYearSubjectApiImpl implements PlanYearSubjectAPI  {
    @Autowired
    private PlanYearSubjectSer planYearSubjectSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return planYearSubjectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<PlanYearmapsBO> maps(PlanYearSubjectDTO dto) throws SerException {
        return planYearSubjectSer.maps(dto);
    }

    @Override
    public void save(PlanYearmapsTO to) throws SerException {
        planYearSubjectSer.save(to);
    }

    @Override
    public void update(PlanYearSubjectUpdateTO to) throws SerException {
        planYearSubjectSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        planYearSubjectSer.delete(id);
    }

    @Override
    public Long getTotal(PlanYearTypeDTO dto) throws SerException {
        return planYearSubjectSer.getTotal(dto);
    }

    @Override
    public PlanYearSubjectUpdateBO getById(String id) throws SerException {
        return planYearSubjectSer.getById(id);
    }

    @Override
    public void congeal(String id) throws SerException {
        planYearSubjectSer.congeal(id);
    }

    @Override
    public void thaw(String id) throws SerException {
        planYearSubjectSer.thaw(id);
    }

    @Override
    public void importExcel(List<PlanYearmapsImportExcel> tos) throws SerException {
        planYearSubjectSer.importExcel(tos);
    }

    @Override
    public byte[] exportTempExcel() throws SerException {
        return planYearSubjectSer.exportTempExcel();
    }

    @Override
    public byte[] export() throws SerException {
        return planYearSubjectSer.export();
    }

    @Override
    public Double findMoney(String year) throws SerException {
        return planYearSubjectSer.findMoney(year);
    }

    @Override
    public Double moneyByType(String businessType, String year) throws SerException {
        return planYearSubjectSer.moneyByType(businessType,year);
    }

    @Override
    public Double countYearProportion(Double workWeight, Double proportion) throws SerException {
        return planYearSubjectSer.countYearProportion(workWeight,proportion);
    }

    @Override
    public Integer findDeveBusiness(String course) throws SerException {
        return planYearSubjectSer.findDeveBusiness(course);
    }
}