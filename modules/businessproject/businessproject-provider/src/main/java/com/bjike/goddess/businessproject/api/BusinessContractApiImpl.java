package com.bjike.goddess.businessproject.api;

import com.bjike.goddess.businessproject.bo.*;
import com.bjike.goddess.businessproject.dto.BusinessContractDTO;
import com.bjike.goddess.businessproject.service.BusinessContractSer;
import com.bjike.goddess.businessproject.to.BusinessContractTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.Timer;

/**
 * 商务项目合同业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessContractApiImpl")
public class BusinessContractApiImpl implements BusinessContractAPI {
    @Autowired
    private BusinessContractSer businessContractSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return businessContractSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return businessContractSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(BusinessContractDTO dto) throws SerException {
        return businessContractSer.count(dto);
    }
    @Override
    public BusinessContractBO getOneById(String id) throws SerException {
        return businessContractSer.getOneById(id);
    }

    @Override
    public List<BusinessContractBO> list(BusinessContractDTO dto) throws SerException {
        return businessContractSer.list(dto);
    }

    @Override
    public BusinessContractBO add(BusinessContractTO to) throws SerException {
        return businessContractSer.add(to);
    }

    @Override
    public BusinessContractBO edit(BusinessContractTO to) throws SerException {
        return businessContractSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        businessContractSer.delete(id);
    }
    @Override
    public Set<String> areas() throws SerException {
        return businessContractSer.areas();
    }
    @Override
    public BusinessContractBO managerIdea(BusinessContractTO to) throws SerException {
        return businessContractSer.managerIdea(to);
    }

    @Override
    public BusinessContractBO planIdea(BusinessContractTO to) throws SerException {
        return businessContractSer.planIdea(to);
    }

    @Override
    public BusinessContractBO budgetIdea(BusinessContractTO to) throws SerException {
        return businessContractSer.budgetIdea(to);
    }
    @Override
    public BusinessContractBO hadContract(BusinessContractTO to) throws SerException {
        return businessContractSer.hadContract(to);
    }
    @Override
    public BusinessContractBO advance(BusinessContractTO to) throws SerException {
        return businessContractSer.advance(to);
    }

    @Override
    public BusinessContractBO changes(BusinessContractTO to) throws SerException {
        return businessContractSer.changes(to);
    }

    @Override
    public BusinessContractBO notification(BusinessContractTO to) throws SerException {
        return businessContractSer.notification(to);
    }
    @Override
    public List<BusinessContractADetailBO> collect(String[] areas) throws SerException {
        return businessContractSer.collect(areas);
    }
    @Override
    public List<BusinessContractProgressBO> dayCollect(String time) throws SerException {
        return businessContractSer.dayCollect(time);
    }

    @Override
    public List<BusinessContractProgressBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractSer.weekCollect(year, month, week);
    }

    @Override
    public List<BusinessContractProgressBO> monthCollect(Integer year, Integer month) throws SerException {
        return businessContractSer.monthCollect(year, month);
    }

    @Override
    public List<BusinessContractProgressBO> quarterCollect(Integer year, Integer quarter) throws SerException {
        return businessContractSer.quarterCollect(year, quarter);
    }

    @Override
    public List<BusinessContractProgressBO> yearCollect(Integer year) throws SerException {
        return businessContractSer.yearCollect(year);
    }

    @Override
    public List<BusinessContractProgressBO> totalCollect(String time) throws SerException {
        return businessContractSer.totalCollect(time);
    }

    @Override
    public OptionBO dayAreaScaleFigureCollect(String time) throws SerException {
        return businessContractSer.dayAreaScaleFigureCollect(time);
    }

    @Override
    public OptionBO weekAreaScaleFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractSer.weekAreaScaleFigureCollect(year, month, week);
    }

    @Override
    public OptionBO monthAreaScaleFigureCollect(Integer year, Integer month) throws SerException {
        return businessContractSer.monthAreaScaleFigureCollect(year,month);
    }

    @Override
    public OptionBO quarterAreaScaleFigureCollect(Integer year, Integer quarter) throws SerException {
        return businessContractSer.quarterAreaScaleFigureCollect(year, quarter);
    }

    @Override
    public OptionBO yearAreaScaleFigureCollect(Integer year) throws SerException {
        return businessContractSer.yearAreaScaleFigureCollect(year);
    }

    @Override
    public OptionBO totalAreaScaleFigureCollect(String time) throws SerException {
        return businessContractSer.totalAreaScaleFigureCollect(time);
    }
    @Override
    public OptionBO dayProjectGroupScaleFigureCollect(String time) throws SerException {
        return businessContractSer.dayProjectGroupScaleFigureCollect(time);
    }

    @Override
    public OptionBO weekProjectGroupScaleFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractSer.weekProjectGroupScaleFigureCollect(year, month, week);
    }

    @Override
    public OptionBO monthProjectGroupScaleFigureCollect(Integer year, Integer month) throws SerException {
        return businessContractSer.monthProjectGroupScaleFigureCollect(year, month);
    }

    @Override
    public OptionBO quarterProjectGroupScaleFigureCollect(Integer year, Integer quarter) throws SerException {
        return businessContractSer.quarterProjectGroupScaleFigureCollect(year, quarter);
    }

    @Override
    public OptionBO yearProjectGroupScaleFigureCollect(Integer year) throws SerException {
        return businessContractSer.yearProjectGroupScaleFigureCollect(year);
    }

    @Override
    public OptionBO totalProjectGroupScaleFigureCollect(String time) throws SerException {
        return businessContractSer.totalProjectGroupScaleFigureCollect(time);
    }

    @Override
    public OptionBO dayMajorScaleFigureCollect(String time) throws SerException {
        return businessContractSer.dayMajorScaleFigureCollect(time);
    }

    @Override
    public OptionBO weekMajorScaleFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractSer.weekMajorScaleFigureCollect(year, month, week);
    }

    @Override
    public OptionBO monthMajorScaleFigureCollect(Integer year, Integer month) throws SerException {
        return businessContractSer.monthMajorScaleFigureCollect(year, month);
    }

    @Override
    public OptionBO quarterMajorScaleFigureCollect(Integer year, Integer quarter) throws SerException {
        return businessContractSer.quarterMajorScaleFigureCollect(year, quarter);
    }

    @Override
    public OptionBO yearMajorScaleFigureCollect(Integer year) throws SerException {
        return businessContractSer.yearMajorScaleFigureCollect(year);
    }

    @Override
    public OptionBO totalMajorScaleFigureCollect(String time) throws SerException {
        return businessContractSer.totalMajorScaleFigureCollect(time);
    }
    @Override
    public OptionMakeBO dayAreaMakeFigureCollect(String time) throws SerException {
        return businessContractSer.dayAreaMakeFigureCollect(time);
    }

    @Override
    public OptionMakeBO weekAreaMakeFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractSer.weekAreaMakeFigureCollect(year, month, week);
    }

    @Override
    public OptionMakeBO monthAreaMakeFigureCollect(Integer year, Integer month) throws SerException {
        return businessContractSer.monthAreaMakeFigureCollect(year, month);
    }

    @Override
    public OptionMakeBO quarterAreaMakeFigureCollect(Integer year, Integer quarter) throws SerException {
        return businessContractSer.quarterAreaMakeFigureCollect(year, quarter);
    }

    @Override
    public OptionMakeBO yearAreaMakeFigureCollect(Integer year) throws SerException {
        return businessContractSer.yearAreaMakeFigureCollect(year);
    }

    @Override
    public OptionMakeBO totalAreaMakeFigureCollect(String time) throws SerException {
        return businessContractSer.totalAreaMakeFigureCollect(time);
    }
    @Override
    public OptionMakeBO dayProjectGroupMakeFigureCollect(String time) throws SerException {
        return businessContractSer.dayProjectGroupMakeFigureCollect(time);
    }

    @Override
    public OptionMakeBO weekProjectGroupMakeFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractSer.weekProjectGroupMakeFigureCollect(year, month, week);
    }

    @Override
    public OptionMakeBO monthProjectGroupMakeFigureCollect(Integer year, Integer month) throws SerException {
        return businessContractSer.monthProjectGroupMakeFigureCollect(year, month);
    }

    @Override
    public OptionMakeBO quarterProjectGroupMakeFigureCollect(Integer year, Integer quarter) throws SerException {
        return businessContractSer.quarterProjectGroupMakeFigureCollect(year, quarter);
    }

    @Override
    public OptionMakeBO yearProjectGroupMakeFigureCollect(Integer year) throws SerException {
        return businessContractSer.yearProjectGroupMakeFigureCollect(year);
    }

    @Override
    public OptionMakeBO totalProjectGroupMakeFigureCollect(String time) throws SerException {
        return businessContractSer.totalProjectGroupMakeFigureCollect(time);
    }

    @Override
    public OptionMakeBO dayMajorMakeFigureCollect(String time) throws SerException {
        return businessContractSer.dayMajorMakeFigureCollect(time);
    }

    @Override
    public OptionMakeBO weekMajorMakeFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractSer.weekMajorMakeFigureCollect(year, month, week);
    }

    @Override
    public OptionMakeBO monthMajorMakeFigureCollect(Integer year, Integer month) throws SerException {
        return businessContractSer.monthMajorMakeFigureCollect(year, month);
    }

    @Override
    public OptionMakeBO quarterMajorMakeFigureCollect(Integer year, Integer quarter) throws SerException {
        return businessContractSer.quarterMajorMakeFigureCollect(year, quarter);
    }

    @Override
    public OptionMakeBO yearMajorMakeFigureCollect(Integer year) throws SerException {
        return businessContractSer.yearMajorMakeFigureCollect(year);
    }

    @Override
    public OptionMakeBO totalMajorMakeFigureCollect(String time) throws SerException {
        return businessContractSer.totalMajorMakeFigureCollect(time);
    }
    @Override
    public OptionBO dayAreaMakeCaseFigureCollect(String time) throws SerException {
        return businessContractSer.dayAreaMakeCaseFigureCollect(time);
    }

    @Override
    public OptionBO weekAreaMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractSer.weekAreaMakeCaseFigureCollect(year, month, week);
    }

    @Override
    public OptionBO monthAreaMakeCaseFigureCollect(Integer year, Integer month) throws SerException {
        return businessContractSer.monthAreaMakeCaseFigureCollect(year, month);
    }

    @Override
    public OptionBO quarterAreaMakeCaseFigureCollect(Integer year, Integer quarter) throws SerException {
        return businessContractSer.quarterAreaMakeCaseFigureCollect(year, quarter);
    }

    @Override
    public OptionBO yearAreaMakeCaseFigureCollect(Integer year) throws SerException {
        return businessContractSer.yearAreaMakeCaseFigureCollect(year);
    }

    @Override
    public OptionBO totalAreaMakeCaseFigureCollect(String time) throws SerException {
        return businessContractSer.totalAreaMakeCaseFigureCollect(time);
    }

    @Override
    public OptionBO dayProjectGroupMakeCaseFigureCollect(String time) throws SerException {
        return businessContractSer.dayProjectGroupMakeCaseFigureCollect(time);
    }

    @Override
    public OptionBO weekProjectGroupMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractSer.weekProjectGroupMakeCaseFigureCollect(year, month, week);
    }

    @Override
    public OptionBO monthProjectGroupMakeCaseFigureCollect(Integer year, Integer month) throws SerException {
        return businessContractSer.monthProjectGroupMakeCaseFigureCollect(year, month);
    }

    @Override
    public OptionBO quarterProjectGroupMakeCaseFigureCollect(Integer year, Integer quarter) throws SerException {
        return businessContractSer.quarterProjectGroupMakeCaseFigureCollect(year, quarter);
    }

    @Override
    public OptionBO yearProjectGroupMakeCaseFigureCollect(Integer year) throws SerException {
        return businessContractSer.yearProjectGroupMakeCaseFigureCollect(year);
    }

    @Override
    public OptionBO totalProjectGroupMakeCaseFigureCollect(String time) throws SerException {
        return businessContractSer.totalProjectGroupMakeCaseFigureCollect(time);
    }

    @Override
    public OptionBO dayMajorMakeCaseFigureCollect(String time) throws SerException {
        return businessContractSer.dayMajorMakeCaseFigureCollect(time);
    }

    @Override
    public OptionBO weekMajorMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractSer.weekMajorMakeCaseFigureCollect(year, month, week);
    }

    @Override
    public OptionBO monthMajorMakeCaseFigureCollect(Integer year, Integer month) throws SerException {
        return businessContractSer.monthMajorMakeCaseFigureCollect(year, month);
    }

    @Override
    public OptionBO quarterMajorMakeCaseFigureCollect(Integer year, Integer quarter) throws SerException {
        return businessContractSer.quarterMajorMakeCaseFigureCollect(year, quarter);
    }

    @Override
    public OptionBO yearMajorMakeCaseFigureCollect(Integer year) throws SerException {
        return businessContractSer.yearMajorMakeCaseFigureCollect(year);
    }

    @Override
    public OptionBO totalMajorMakeCaseFigureCollect(String time) throws SerException {
        return businessContractSer.totalMajorMakeCaseFigureCollect(time);
    }
    @Override
    public BrokenOptionBO scaleNum(Integer year) throws SerException {
        return businessContractSer.scaleNum(year);
    }

    @Override
    public BrokenOptionMakeBO makeMoney(Integer year) throws SerException {
        return businessContractSer.makeMoney(year);
    }

    @Override
    public BrokenOptionBO makeCase(Integer year) throws SerException {
        return businessContractSer.makeCase(year);
    }
    @Override
    public BusinessContractBO importExcel(List<BusinessContractTO> contractTOS) throws SerException {
        return businessContractSer.importExcel(contractTOS);
    }

    @Override
    public byte[] exportExcel(BusinessContractDTO dto) throws SerException {
        return businessContractSer.exportExcel(dto);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return businessContractSer.templateExport();
    }

}