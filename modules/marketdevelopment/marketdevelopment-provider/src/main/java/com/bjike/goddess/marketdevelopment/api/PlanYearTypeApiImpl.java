package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.PlanYearBO;
import com.bjike.goddess.marketdevelopment.bo.PlanYearUpdateBO;
import com.bjike.goddess.marketdevelopment.dto.PlanYearTypeDTO;
import com.bjike.goddess.marketdevelopment.excel.PlanYearImportExcel;
import com.bjike.goddess.marketdevelopment.service.PlanYearTypeSer;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearUpdateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 年计划业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-07 05:32 ]
* @Description:	[ 年计划业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("planYearTypeApiImpl")
public class PlanYearTypeApiImpl implements PlanYearTypeAPI  {
    @Autowired
    private PlanYearTypeSer planYearTypeSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return planYearTypeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<PlanYearBO> maps(PlanYearTypeDTO dto) throws SerException {
        return planYearTypeSer.maps(dto);
    }

    @Override
    public void save(PlanYearTO to) throws SerException {
        planYearTypeSer.save(to);
    }

    @Override
    public void update(PlanYearUpdateTO to) throws SerException {
        planYearTypeSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        planYearTypeSer.delete(id);
    }

    @Override
    public PlanYearUpdateBO getById(String id) throws SerException {
        return planYearTypeSer.getById(id);
    }

    @Override
    public Long getTotal(PlanYearTypeDTO dto) throws SerException {
        return planYearTypeSer.getTotal(dto);
    }

    @Override
    public void congeal(String id) throws SerException {
        planYearTypeSer.congeal(id);
    }

    @Override
    public void thaw(String id) throws SerException {
        planYearTypeSer.thaw(id);
    }

    @Override
    public void importExcel(List<PlanYearImportExcel> tos) throws SerException {
        planYearTypeSer.importExcel(tos);
    }

    @Override
    public byte[] exportTempExcel() throws SerException {
        return planYearTypeSer.exportTempExcel();
    }

    @Override
    public byte[] export() throws SerException {
        return planYearTypeSer.export();
    }
}