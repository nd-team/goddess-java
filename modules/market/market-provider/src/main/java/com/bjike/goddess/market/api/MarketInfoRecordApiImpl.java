package com.bjike.goddess.market.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerNameNumBO;
import com.bjike.goddess.market.bo.MarketInfoRecordBO;
import com.bjike.goddess.market.bo.OptionBO;
import com.bjike.goddess.market.bo.SummationAreaBO;
import com.bjike.goddess.market.bo.SummationBO;
import com.bjike.goddess.market.dto.MarketInfoRecordDTO;
import com.bjike.goddess.market.excel.SonPermissionObject;
import com.bjike.goddess.market.service.MarketInfoRecordSer;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoRecordTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市场信息记录业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 11:50 ]
 * @Description: [ 市场信息记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketInfoRecordApiImpl")
public class MarketInfoRecordApiImpl implements MarketInfoRecordAPI {
    @Autowired
    private MarketInfoRecordSer marketInfoRecordSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return marketInfoRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return marketInfoRecordSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countRecord(MarketInfoRecordDTO marketInfoRecordDTO) throws SerException {
        return marketInfoRecordSer.countRecord(marketInfoRecordDTO);
    }

    @Override
    public MarketInfoRecordBO getOne(String id) throws SerException {
        return marketInfoRecordSer.getOne(id);
    }

    @Override
    public List<MarketInfoRecordBO> findListRecord(MarketInfoRecordDTO marketInfoRecordDTO) throws SerException {
        return marketInfoRecordSer.findListRecord(marketInfoRecordDTO);
    }

    @Override
    public MarketInfoRecordBO insertRecord(MarketInfoRecordTO marketInfoRecordTO) throws SerException {
        return marketInfoRecordSer.insertRecord(marketInfoRecordTO);
    }

    @Override
    public MarketInfoRecordBO editRecord(MarketInfoRecordTO marketInfoRecordTO) throws SerException {
        return marketInfoRecordSer.editRecord(marketInfoRecordTO);
    }

    @Override
    public void removeRecord(String id) throws SerException {
        marketInfoRecordSer.removeRecord(id);
    }

    @Override
    public List<String> findallUser() throws SerException {
        return marketInfoRecordSer.findallUser();
    }

//    @Override
//    public List<CustomerNameNumBO> getNameNum() throws SerException {
//        return marketInfoRecordSer.getNameNum();
//    }

    @Override
    public List<String> getCompetName() throws SerException {
        return marketInfoRecordSer.getCompetName();
    }

    @Override
    public List<String> findBussType() throws SerException {
        return marketInfoRecordSer.findBussType();
    }

    @Override
    public List<String> findProjectName() throws SerException {
        return marketInfoRecordSer.findProjectName();
    }

    @Override
    public List<String> findAreaByBussType(String bussType) throws SerException {
        return marketInfoRecordSer.findAreaByBussType(bussType);
    }

    @Override
    public List<SummationBO> summaDay(String summDate) throws SerException {
        return marketInfoRecordSer.summaDay(summDate);
    }

    @Override
    public List<SummationAreaBO> summaDayByArea(String summDate) throws SerException {
        return marketInfoRecordSer.summaDayByArea(summDate);
    }

    @Override
    public List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return marketInfoRecordSer.summaWeek(year,month,week);
    }

    @Override
    public List<SummationAreaBO> summaWeekByArea(Integer year, Integer month, Integer week) throws SerException {
        return marketInfoRecordSer.summaWeekByArea(year,month,week);
    }

    @Override
    public List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        return marketInfoRecordSer.summaMonth(year,month);
    }

    @Override
    public List<SummationAreaBO> summaMonthByArea(Integer year, Integer month) throws SerException {
        return marketInfoRecordSer.summaMonthByArea(year,month);
    }

    @Override
    public List<SummationBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        return marketInfoRecordSer.summaQuarter(year,quarter);
    }

    @Override
    public List<SummationAreaBO> summaQuarterByArea(Integer year, Integer quarter) throws SerException {
        return marketInfoRecordSer.summaQuarterByArea(year,quarter);
    }

    @Override
    public List<SummationBO> summaYear(Integer year) throws SerException {
        return marketInfoRecordSer.summaYear(year);
    }

    @Override
    public List<SummationAreaBO> summaYearByArea(Integer year) throws SerException {
        return marketInfoRecordSer.summaYearByArea(year);
    }

    @Override
    public List<SummationBO> summaTotal(String endDate) throws SerException {
        return marketInfoRecordSer.summaTotal(endDate);
    }

    @Override
    public List<SummationAreaBO> summaTotalByArea(String endDate) throws SerException {
        return marketInfoRecordSer.summaTotalByArea(endDate);
    }

    @Override
    public OptionBO figureShowDay(String summDate) throws SerException {
        return marketInfoRecordSer.figureShowDay(summDate);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return marketInfoRecordSer.figureShowWeek(year,month,week);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return marketInfoRecordSer.figureShowMonth(year,month);
    }

    @Override
    public OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
        return marketInfoRecordSer.figureShowQuarter(year,quarter);
    }

    @Override
    public OptionBO figureShowYear(Integer year) throws SerException {
        return marketInfoRecordSer.figureShowYear(year);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        return marketInfoRecordSer.figureShowTotal(endDate);
    }
}