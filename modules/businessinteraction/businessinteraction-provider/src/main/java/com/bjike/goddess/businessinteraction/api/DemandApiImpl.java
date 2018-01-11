package com.bjike.goddess.businessinteraction.api;

import com.bjike.goddess.businessinteraction.bo.DemandBO;
import com.bjike.goddess.businessinteraction.bo.OptionBO;
import com.bjike.goddess.businessinteraction.bo.SummationBO;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;
import com.bjike.goddess.businessinteraction.service.DemandSer;
import com.bjike.goddess.businessinteraction.to.DemandTO;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 需求信息业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:18 ]
 * @Description: [ 需求信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("demandApiImpl")
public class DemandApiImpl implements DemandAPI {
    @Autowired
    private DemandSer demandSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return demandSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return demandSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countInter(DemandDTO demandDTO) throws SerException {
        return demandSer.countInter(demandDTO);
    }

    @Override
    public DemandBO getOneById(String id) throws SerException {
        return demandSer.getOneById(id);
    }

    @Override
    public List<DemandBO> listIntera(DemandDTO demandDTO) throws SerException {
        return demandSer.listIntera(demandDTO);
    }

    @Override
    public DemandBO addIntera(DemandTO demandTO) throws SerException {
        return demandSer.addIntera(demandTO);
    }

    @Override
    public DemandBO editIntera(DemandTO demandTO) throws SerException {
        return demandSer.editIntera(demandTO);
    }

    @Override
    public void deleteIntera(String id) throws SerException {
        demandSer.deleteIntera(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return demandSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return demandSer.templateExport();
    }

    @Override
    public void importExcel(List<DemandTO> demandTOS) throws SerException {
        demandSer.importExcel(demandTOS);
    }

    @Override
    public List<SummationBO> summaDay(String summDate) throws SerException {
        return demandSer.summaDay(summDate);
    }

    @Override
    public List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return demandSer.summaWeek(year,month,week);
    }

    @Override
    public List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        return demandSer.summaMonth(year,month);
    }

    @Override
    public List<SummationBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        return demandSer.summaQuarter(year,quarter);
    }

    @Override
    public List<SummationBO> summaYear(Integer year) throws SerException {
        return demandSer.summaYear(year);
    }

    @Override
    public List<SummationBO> summaTotal(String endDate) throws SerException {
        return demandSer.summaTotal(endDate);
    }

    @Override
    public OptionBO figureShowDay(String summDate) throws SerException {
        return demandSer.figureShowDay(summDate);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return demandSer.figureShowWeek(year,month,week);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return demandSer.figureShowMonth(year,month);
    }

    @Override
    public OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
        return demandSer.figureShowQuarter(year,quarter);
    }

    @Override
    public OptionBO figureShowYear(Integer year) throws SerException {
        return demandSer.figureShowYear(year);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        return demandSer.figureShowTotal(endDate);
    }

    @Override
    public List<String> findBussType() throws SerException {
        return demandSer.findBussType();
    }
}