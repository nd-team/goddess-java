package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.YearIndexSetBO;
import com.bjike.goddess.balancecard.dto.YearIndexSetDTO;
import com.bjike.goddess.balancecard.excel.SonPermissionObject;
import com.bjike.goddess.balancecard.service.YearIndexSetSer;
import com.bjike.goddess.balancecard.to.*;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 年度指标设置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 年度指标设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("yearIndexSetApiImpl")
public class YearIndexSetApiImpl implements YearIndexSetAPI {


    @Autowired
    private YearIndexSetSer yearIndexSetSer;

    @Override
    public Long countYearIndexSet(YearIndexSetDTO yearIndexSetDTO) throws SerException {
        return yearIndexSetSer.countYearIndexSet( yearIndexSetDTO);
    }

    @Override
    public YearIndexSetBO getOneById(String id) throws SerException {
        return yearIndexSetSer.getOneById(id);
    }

    @Override
    public List<YearIndexSetBO> listYearIndexSet(YearIndexSetDTO yearIndexSetDTO) throws SerException {
        return yearIndexSetSer.listYearIndexSet(yearIndexSetDTO);
    }

    @Override
    public YearIndexSetBO addYearIndexSet(YearIndexSetTO yearIndexSetTO) throws SerException {
        return yearIndexSetSer.addYearIndexSet(yearIndexSetTO);
    }

    @Override
    public YearIndexSetBO editYearIndexSet(YearIndexSetTO yearIndexSetTO) throws SerException {
        return yearIndexSetSer.editYearIndexSet(yearIndexSetTO);
    }

    @Override
    public void deleteYearIndexSet(String id) throws SerException {
        yearIndexSetSer.deleteYearIndexSet(id);
    }

    @Override
    public YearIndexSetBO seperateDepartYear(YearIndexSetTO yearIndexSetTO) throws SerException {
        return yearIndexSetSer.seperateDepartYear(yearIndexSetTO);
    }

    @Override
    public List<String> yearList() throws SerException {
        return yearIndexSetSer.yearList();
    }

    @Override
    public void leadExcel(List<YearIndexSetTO> toList) throws SerException {
        yearIndexSetSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(ExportExcelYearTO to) throws SerException {
        return yearIndexSetSer.exportExcel(to);
    }

    @Override
    public byte[] exportYearExcel(ExportExcelYearTO to) throws SerException {
        return yearIndexSetSer.exportYearExcel(to);
    }

    @Override
    public byte[] exportYearDeExcel(ExportExcelYearTO to) throws SerException {
        return yearIndexSetSer.exportYearDeExcel(to);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return yearIndexSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return yearIndexSetSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<YearIndexSetBO> dendrogram(YearIndexSetDTO yearIndexSetDTO) throws SerException {
        return yearIndexSetSer.dendrogram(yearIndexSetDTO);
    }
}