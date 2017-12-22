package com.bjike.goddess.businessprojectmanage.api;

import com.bjike.goddess.businessprojectmanage.bo.*;
import com.bjike.goddess.businessprojectmanage.dto.BusinessContractProDTO;
import com.bjike.goddess.businessprojectmanage.service.BusinessContractProSer;
import com.bjike.goddess.businessprojectmanage.to.BusinessContractProTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目合同基本信息业务接口实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-11 10:03 ]
 * @Description: [ 项目合同基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessContractProApiImpl")
public class BusinessContractProApiImpl implements BusinessContractProAPI {

    @Autowired
    BusinessContractProSer businessContractProSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return businessContractProSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return businessContractProSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<BusinessContractProBO> listBusinessContactProgress(BusinessContractProDTO dto) throws SerException {
        return businessContractProSer.listBusinessContactProgress(dto);
    }

    @Override
    public Long countBusinessContactProgress(BusinessContractProDTO dto) throws SerException {
        return businessContractProSer.countBusinessContactProgress(dto);
    }

    @Override
    public void addBusinessContactProgress(BusinessContractProTO to) throws SerException {
        businessContractProSer.addBusinessContactProgress(to);
    }

    @Override
    public void updateBusinessContactProgress(BusinessContractProTO to) throws SerException {
        businessContractProSer.updateBusinessContactProgress(to);
    }

    @Override
    public void importExcel(List<BusinessContractProTO> to) throws SerException {
        businessContractProSer.importExcel(to);
    }

    @Override
    public byte[] exportExcel(BusinessContractProDTO dto) throws SerException {
        return businessContractProSer.exportExcel(dto);
    }

    @Override
    public List<BusinessContractCollectBO> dayCollect(String time) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.dayCollect(time);

        return businessContractProSer.dayCollect(time);
    }

    @Override
    public List<BusinessContractCollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return businessContractProSer.weekCollect(year, month, week);
    }

    @Override
    public List<BusinessContractCollectBO> monthCollect(Integer year, Integer month) throws SerException {
        return businessContractProSer.monthCollect(year, month);
    }

    @Override
    public List<BusinessContractCollectBO> quarterCollect(Integer year, Integer quarter) throws SerException {
        return businessContractProSer.quarterCollect(year, quarter);
    }

    @Override
    public List<BusinessContractCollectBO> yearCollect(Integer year) throws SerException {
        return businessContractProSer.yearCollect(year);
    }

    @Override
    public List<BusinessContractCollectBO> totalCollect(String time) throws SerException {
        return businessContractProSer.totalCollect(time);
    }

    @Override
    public BCCollectEchartBO dayProgressCollect(String time) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.dayCollect(time);

        return getBCProgressEchart(list, "项目实施进度完工情况管理日汇总");
    }

    @Override
    public BCCollectEchartBO weekProgressCollect(Integer year, Integer month, Integer week) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.weekCollect(year, month, week);
        return getBCProgressEchart(list, "项目实施进度完工情况管理周汇总");
    }

    @Override
    public BCCollectEchartBO monthProgressCollect(Integer year, Integer month) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.monthCollect(year, month);
        return getBCProgressEchart(list, "项目实施进度完工情况管理月汇总");
    }

    @Override
    public BCCollectEchartBO quarterProgressCollect(Integer year, Integer quarter) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.quarterCollect(year, quarter);
        return getBCProgressEchart(list, "项目实施进度完工情况管理季度汇总");
    }

    @Override
    public BCCollectEchartBO yearProgressCollect(Integer year) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.yearCollect(year);
        return getBCProgressEchart(list, "项目实施进度完工情况管理年汇总");
    }

    @Override
    public BCCollectEchartBO totalProgressCollect(String time) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.totalCollect(time);
        return getBCProgressEchart(list, "项目实施进度完工情况管理累计汇总");
    }

    @Override
    public BCCollectEchartBO dayMoneyCollect(String time) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.dayCollect(time);

        return getBCMoneyEchart(list, "项目实施进度管理金额情况日汇总");
    }

    @Override
    public BCCollectEchartBO weekMoneyCollect(Integer year, Integer month, Integer week) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.weekCollect(year, month, week);
        return getBCMoneyEchart(list, "项目实施进度管理金额情况周汇总");
    }

    @Override
    public BCCollectEchartBO monthMoneyCollect(Integer year, Integer month) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.monthCollect(year, month);
        return getBCMoneyEchart(list, "项目实施进度管理金额情况月汇总");
    }

    @Override
    public BCCollectEchartBO quarterMoneyCollect(Integer year, Integer quarter) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.quarterCollect(year, quarter);
        return getBCMoneyEchart(list, "项目实施进度管理金额情况季度汇总");
    }

    @Override
    public BCCollectEchartBO yearMoneyCollect(Integer year) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.yearCollect(year);
        return getBCMoneyEchart(list, "项目实施进度管理金额情况年汇总");
    }

    @Override
    public BCCollectEchartBO totalMoneyCollect(String time) throws SerException {
        List<BusinessContractCollectBO> list = businessContractProSer.totalCollect(time);
        return getBCMoneyEchart(list, "项目实施进度管理金额情况累计汇总");
    }


    /**
     * 将项目实施进度完工情况数据转化为Echart数据
     *
     * @param
     * @return class
     * @version v1
     */
    private BCCollectEchartBO getBCProgressEchart(List<BusinessContractCollectBO> list, String titleText) {
        String[] xText = new String[list.size()];
        SeriesBO[] series = new SeriesBO[5];
        String[] data1 = new String[list.size()];
        String[] data2 = new String[list.size()];
        String[] data3 = new String[list.size()];
        String[] data4 = new String[list.size()];
        String[] data5 = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            xText[i] = list.get(i).getProjectGroup();

            String scaleContract = list.get(i).getScaleContract() == null ? "" : list.get(i).getScaleContract();
            String makeContractAmount = list.get(i).getMakeContractAmout() == null ? "" : list.get(i).getMakeContractAmout();
            String forecastContractAmount = list.get(i).getForecastContractAmount() == null ? "" : list.get(i).getForecastContractAmount();
            String complete = list.get(i).getComplete() == null ? "" : list.get(i).getComplete();
            String notCompleteContract = list.get(i).getNotCompleteContract() == null ? "" : list.get(i).getNotCompleteContract();

            data1[i] = scaleContract;
            data2[i] = makeContractAmount;
            data3[i] = forecastContractAmount;
            data4[i] = complete;
            data5[i] = notCompleteContract;


        }
        String[] data = new String[5];

        SeriesBO seriesBO1 = new SeriesBO("项目总规模", "bar", data1);
        SeriesBO seriesBO2 = new SeriesBO("已立项合同规模", "bar", data2);
        SeriesBO seriesBO3 = new SeriesBO("预立项合同规模", "bar", data3);
        SeriesBO seriesBO4 = new SeriesBO("已完工规模", "bar", data4);
        SeriesBO seriesBO5 = new SeriesBO("未完工规模", "bar", data5);

        series[0] = seriesBO1;
        series[1] = seriesBO2;
        series[2] = seriesBO3;
        series[3] = seriesBO4;
        series[4] = seriesBO5;

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(titleText);

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        legendBO.setData(new String[]{"项目总规模", "已立项合同规模", "预立项合同规模", "已完工规模", "未完工规模",});
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setData(xText);
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(true);

        BCCollectEchartBO bcCollectEchartBO = new BCCollectEchartBO();
        bcCollectEchartBO.setTitle(titleBO);
        bcCollectEchartBO.setTooltipBO(tooltipBO);
        bcCollectEchartBO.setLegend(legendBO);
        bcCollectEchartBO.setxAxis(xAxisBO);
        bcCollectEchartBO.setyAxis(yAxisBO);
        bcCollectEchartBO.setSeries(series);
        return bcCollectEchartBO;
    }

    /**
     * 将项目实施进度管理金额情况数据转化为Echart数据
     *
     * @param
     * @return class
     * @version v1
     */
    private BCCollectEchartBO getBCMoneyEchart(List<BusinessContractCollectBO> list, String titleText) {
        String[] xText = new String[list.size()];
        SeriesBO[] series = new SeriesBO[6];
        String[] data1 = new String[list.size()];
        String[] data2 = new String[list.size()];
        String[] data3 = new String[list.size()];
        String[] data4 = new String[list.size()];
        String[] data5 = new String[list.size()];
        String[] data6 = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            xText[i] = list.get(i).getProjectGroup();

            String totalMoney = list.get(i).getTotalMoney() + "";
            String contractMoney = list.get(i).getContractMoney() == null ? "" : list.get(i).getContractMoney();
            String makeMoney = list.get(i).getMakeMoney().intValue() + "";
            String forecastMoney = list.get(i).getForecastMoney().intValue() + "";
            String completeMoney = list.get(i).getCompleteMoney().intValue() + "";
            String notCompleteMoney = list.get(i).getNotCompleteMoney().intValue() + "";

            data1[i] = totalMoney;
            data2[i] = contractMoney;
            data3[i] = makeMoney;
            data4[i] = forecastMoney;
            data5[i] = completeMoney;
            data6[i] = notCompleteMoney;


        }
        String[] data = new String[6];

        SeriesBO seriesBO1 = new SeriesBO("项目总金额", "bar", data1);
        SeriesBO seriesBO2 = new SeriesBO("新增合同金额", "bar", data2);
        SeriesBO seriesBO3 = new SeriesBO("已立项合同金额", "bar", data3);
        SeriesBO seriesBO4 = new SeriesBO("预立项合同金额", "bar", data4);
        SeriesBO seriesBO5 = new SeriesBO("已完工金额", "bar", data5);
        SeriesBO seriesBO6 = new SeriesBO("未完工金额", "bar", data6);

        series[0] = seriesBO1;
        series[1] = seriesBO2;
        series[2] = seriesBO3;
        series[3] = seriesBO4;
        series[4] = seriesBO5;
        series[5] = seriesBO6;

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(titleText);

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        legendBO.setData(new String[]{"项目总金额", "新增合同金额", "已立项合同金额", "预立项合同金额", "已完工金额", "未完工金额"});
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setData(xText);
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(true);

        BCCollectEchartBO bcCollectEchartBO = new BCCollectEchartBO();
        bcCollectEchartBO.setTitle(titleBO);
        bcCollectEchartBO.setTooltipBO(tooltipBO);
        bcCollectEchartBO.setLegend(legendBO);
        bcCollectEchartBO.setxAxis(xAxisBO);
        bcCollectEchartBO.setyAxis(yAxisBO);
        bcCollectEchartBO.setSeries(series);
        return bcCollectEchartBO;
    }
}

