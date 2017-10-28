package com.bjike.goddess.lendreimbursement.service.reimshape;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimCompanyShapeDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimburseShapeConDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimburseShapeDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimburseTrendShapeDTO;
import com.bjike.goddess.lendreimbursement.entity.ReimburseRecord;
import com.bjike.goddess.lendreimbursement.service.ReimburseRecordSer;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.*;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 报销记录业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
//@CacheConfig(cacheNames = "lendreimbursementSerCache")
//@Service
public class ReimburseShapeSerImpl implements ReimburseRecordSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ReimburseRecordSer reimburseRecordSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 处理dto添加用户名和时间范围
     *
     * @param reimburseRecordDTO
     * @param userName
     * @param startTime
     * @param endTime
     * @return
     */
    private ReimburseRecordDTO addQueryCon(ReimburseRecordDTO reimburseRecordDTO, String userName, LocalDateTime startTime, LocalDateTime endTime) {
        reimburseRecordDTO = new ReimburseRecordDTO();
        reimburseRecordDTO.getConditions().add(Restrict.eq("reimer", userName));
        reimburseRecordDTO.getConditions().add(Restrict.between("createTime", new LocalDateTime[]{startTime, endTime}));
        return reimburseRecordDTO;
    }

    /**
     * 处理dto添加待审核报销记录
     *
     * @param reimburseRecordDTO
     * @return
     */
    private ReimburseRecordDTO addWaitAuditQueryCon(ReimburseRecordDTO reimburseRecordDTO) {
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{5, 1, 0}));
        reimburseRecordDTO.getConditions().add(Restrict.eq("analisisIsAll", false));
        return reimburseRecordDTO;
    }

    /**
     * 处理dto添加待支付报销记录
     *
     * @param reimburseRecordDTO
     * @return
     */
    private ReimburseRecordDTO addWaitPayQueryCon(ReimburseRecordDTO reimburseRecordDTO) {
        reimburseRecordDTO.getConditions().add(Restrict.eq("reimStatus", 1));
        reimburseRecordDTO.getConditions().add(Restrict.eq("analisisIsAll", true));
        reimburseRecordDTO.getConditions().add(Restrict.ne("payCondition", "是"));
        reimburseRecordDTO.getConditions().add(Restrict.isNull("accountCheckPassOr"));
        return reimburseRecordDTO;
    }

    /**
     * 处理dto添加已支付报销记录
     *
     * @param reimburseRecordDTO
     * @return
     */
    private ReimburseRecordDTO addHasPayQueryCon(ReimburseRecordDTO reimburseRecordDTO) {
        reimburseRecordDTO.getConditions().add(Restrict.eq("payCondition", "是"));
        return reimburseRecordDTO;
    }

    /**
     * 饼型图制作
     *
     * @param seriesDataVOList
     * @return
     */
    private ReimShapeVO echartPieObject(List<ReimShapeSeriesDataVO> seriesDataVOList, ReimShapeTitleVO titleVO) {
        ReimShapeVO reimShapeVO = new ReimShapeVO();
        //echart饼状图形数据封装
        //title
        ReimShapeTitleVO reimShapeTitleVO = titleVO;
        titleVO.setX("center");
        reimShapeVO.setTitleVO(reimShapeTitleVO);
        //tootltip
        ReimShapeToolTipVO toolTipVO = new ReimShapeToolTipVO();
        toolTipVO.setTrigger("item");
        toolTipVO.setFormatter("{a} <br/>{b} : {c} ({d}%)");
        reimShapeVO.setToolTipVO(toolTipVO);
        //legend
        ReimShapeLegendVO legendVO = new ReimShapeLegendVO();
        legendVO.setOrient("vertical");
        legendVO.setLeft("left");
        legendVO.setData(Arrays.asList("申报报销记录", "待审核", "待支付", "已支付"));
        reimShapeVO.setRlegendVO(legendVO);
        //series
        ReimShapeSeriesVO seriesVO = new ReimShapeSeriesVO();
        seriesVO.setName("无");
        seriesVO.setType("pie");
        seriesVO.setRadius("20%");
        seriesVO.setSeriesDataVOList(seriesDataVOList);
        reimShapeVO.setSeriesVO(seriesVO);

        return reimShapeVO;
    }

    /**
     * 柱状图制作
     *
     * @param userName
     * @param start
     * @param end
     * @param seriesDataVOList
     * @return
     */
    private ReimShapeBarVO echartBarObject(String userName, LocalDateTime start, LocalDateTime end, List<Double> seriesDataVOList) {
        ReimShapeBarVO shapeBarVO = new ReimShapeBarVO();
        //echart饼状图形数据封装
        //title
        ReimShapeTitleVO titleVO = new ReimShapeTitleVO();
        titleVO.setText(userName + "报销指标金额统计");
        titleVO.setSubtext("时间: " + start + "至" + end);
        //color
        shapeBarVO.setColorList(Arrays.asList("#3398DB"));
        //tootltip
        ReimShapeBarToolTipVO toolTipVO = new ReimShapeBarToolTipVO();
        toolTipVO.setTrigger("axis");
        toolTipVO.setAxisPointerBarVO(new AxisPointerBarVO("shadow"));
        shapeBarVO.setToolTipVO(toolTipVO);
        //grid
        ReimShapeBarGridVO gridVO = new ReimShapeBarGridVO();
        gridVO.setLeft("30%");
        gridVO.setRight("40%");
        gridVO.setBottom("70%");
        gridVO.setContainLabel(true);
        shapeBarVO.setBarGridVO(gridVO);
        //xAxis
        ReimShapeXaxisVO xAxisVO = new ReimShapeXaxisVO();
        xAxisVO.setName("状态");
        xAxisVO.setType("category");
        xAxisVO.setData(Arrays.asList("申报报销记录", "待审核", "待支付", "已支付"));
        xAxisVO.setAxisTickBarVO(new AxisTickBarVO(true));
        shapeBarVO.setXaxisVO(xAxisVO);
        //yAxis
        ReimShapeYaxisVO yAxisVO = new ReimShapeYaxisVO();
        yAxisVO.setName("金额");
        yAxisVO.setType("value");
        shapeBarVO.setXaxisVO(xAxisVO);
        //series
        ReimShapeBarSeriesVO seriesVO = new ReimShapeBarSeriesVO();
        seriesVO.setName("金额");
        seriesVO.setType("bar");
        seriesVO.setBarWidth("60%");
        seriesVO.setSeriesDataVOList(seriesDataVOList);
        shapeBarVO.setSeriesVO(seriesVO);

        return shapeBarVO;
    }

    private ReimShapeAllVO caculate(ReimburseShapeConDTO reimburseShapeConDTO) throws SerException {
        ReimShapeAllVO allVO = new ReimShapeAllVO();
        //用来存饼型累计的数据
        ReimShapeVO reimShapeVO = new ReimShapeVO();
        List<ReimShapeSeriesDataVO> seriesDataVOList = new ArrayList<>();
        //用来存饼型金额的数据
        ReimShapeVO reimShape2VO = new ReimShapeVO();
        List<ReimShapeSeriesDataVO> seriesDataVO2List = new ArrayList<>();
        //用来存条形金额的数据
        ReimShapeBarVO reimShapeBarVO = new ReimShapeBarVO();
        List<Double> seriesDataVOBarList = new ArrayList<>();

        LocalDateTime startTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getStartTime()) + " 00:00:00");
        LocalDateTime endTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getEndTime()) + " 23:59:59");
        String userName = reimburseShapeConDTO.getUserName();

        //计算某用户在这个时间段内的申报报销记录个数
        ReimShapeSeriesDataVO seriesDataVO = new ReimShapeSeriesDataVO();
        ReimShapeSeriesDataVO seriesData2VO = new ReimShapeSeriesDataVO();
        ReimburseRecordDTO reimburseRecordDTO = new ReimburseRecordDTO();
        reimburseRecordDTO = addQueryCon(reimburseRecordDTO, userName, startTime, endTime);
        List<ReimburseRecord> listReim = reimburseRecordSer.findByCis(reimburseRecordDTO);
        seriesDataVO.setName("申报报销记录");
        seriesData2VO.setName("申报报销记录");
        Double tempReimMoney = 0d;
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            tempReimMoney = listReim.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
            seriesData2VO.setValue(String.valueOf(tempReimMoney));
            seriesDataVOBarList.add(tempReimMoney == null || tempReimMoney.equals(0d) ? 0d : tempReimMoney);

        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);
        //计算某用户在这个时间段内的待审核报销记录个数(包括负责人未审核/申请冻结/分析人员未分析完)
        reimburseRecordDTO = addQueryCon(reimburseRecordDTO, userName, startTime, endTime);
        reimburseRecordDTO = addWaitAuditQueryCon(reimburseRecordDTO);
        listReim = reimburseRecordSer.findByCis(reimburseRecordDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("待审核");
        seriesData2VO.setName("待审核");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
            seriesData2VO.setValue(String.valueOf(reimMoneySum));
            seriesDataVOBarList.add(reimMoneySum == null || reimMoneySum.equals(0d) ? 0d : reimMoneySum);
        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);


        //计算某用户在这个时间段内的待支付报销记录个数
        reimburseRecordDTO = addQueryCon(reimburseRecordDTO, userName, startTime, endTime);
        reimburseRecordDTO = addWaitPayQueryCon(reimburseRecordDTO);
        listReim = reimburseRecordSer.findByCis(reimburseRecordDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("待支付");
        seriesData2VO.setName("待支付");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
            seriesData2VO.setValue(String.valueOf(reimMoneySum));
            seriesDataVOBarList.add(reimMoneySum == null || reimMoneySum.equals(0d) ? 0d : reimMoneySum);
        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);
        //计算某用户在这个时间段内的已支付报销记录个数
        reimburseRecordDTO = addQueryCon(reimburseRecordDTO, userName, startTime, endTime);
        reimburseRecordDTO = addHasPayQueryCon(reimburseRecordDTO);
        listReim = reimburseRecordSer.findByCis(reimburseRecordDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("已支付");
        seriesData2VO.setName("已支付");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
            seriesData2VO.setValue(String.valueOf(reimMoneySum));
            seriesDataVOBarList.add(reimMoneySum == null || reimMoneySum.equals(0d) ? 0d : reimMoneySum);
        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);

        //echart饼状图形数据封装
        ReimShapeTitleVO titlePieVO = new ReimShapeTitleVO();
        titlePieVO.setText(reimburseShapeConDTO.getUserName() + "报销情况统计");
        titlePieVO.setSubtext("时间: " + startTime + "至" + endTime + "累计记录");
        reimShapeVO = echartPieObject(seriesDataVOList, titlePieVO);
        //echart饼状图形数据封装
        ReimShapeTitleVO titlePie2VO = new ReimShapeTitleVO();
        titlePieVO.setText(reimburseShapeConDTO.getUserName() + "报销金额指标统计");
        titlePieVO.setSubtext("时间: " + startTime + "至" + endTime + "报销金额");
        reimShapeVO = echartPieObject(seriesDataVO2List, titlePie2VO);
        //echart柱状图形数据封装
        reimShapeBarVO = echartBarObject(reimburseShapeConDTO.getUserName(), startTime, endTime, seriesDataVOBarList);

        allVO.setReimConIndexPercent(reimShapeVO);
        allVO.setReimMoneyIndexPercent(reimShape2VO);
        allVO.setReimShapeBarVO(reimShapeBarVO);
        return allVO;
    }


    @Override
    public ReimShapeAllVO collectSelfShape(ReimburseShapeDTO reimburseShapeDTO) throws SerException {
        ReimShapeAllVO reimShapeAllVO = new ReimShapeAllVO();

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(reimburseShapeDTO, ReimburseShapeConDTO.class);
        if (null == reimburseShapeDTO.getReimburseShapeStatus()) {
            throw new SerException("汇总状态类型不能为空");
        }
        //根据选择的日周月年和用户名
        if (StringUtils.isBlank(reimburseShapeDTO.getUserName())) {
            throw new SerException("用户名不能为空");
        }
        switch (reimburseShapeDTO.getReimburseShapeStatus()) {
            case YEAR:
                if (StringUtils.isBlank("" + reimburseShapeDTO.getYear())) {
                    throw new SerException("年份不能为空");
                }
                reimburseShapeConDTO.setStartTime(DateUtil.parseDate(reimburseShapeDTO.getYear() + "-01-01"));
                reimburseShapeConDTO.setEndTime(DateUtil.parseDate(reimburseShapeDTO.getYear() + "-12-31"));

                break;
            case MONTH:
                if (StringUtils.isBlank("" + reimburseShapeDTO.getYear()) || StringUtils.isBlank("" + reimburseShapeDTO.getMonth())) {
                    throw new SerException("年份或月份不能为空");
                }
                reimburseShapeConDTO.setStartTime(DateUtil.getStartDayOfMonth(reimburseShapeDTO.getYear(), reimburseShapeDTO.getMonth()));
                reimburseShapeConDTO.setEndTime(DateUtil.getEndDaYOfMonth(reimburseShapeDTO.getYear(), reimburseShapeDTO.getMonth()));
                break;
            case WEEK:
                if (StringUtils.isBlank("" + reimburseShapeDTO.getYear()) || StringUtils.isBlank("" + reimburseShapeDTO.getMonth()) || StringUtils.isBlank("" + reimburseShapeDTO.getWeek())) {
                    throw new SerException("年份或月份或周数不能为空");
                }
                LocalDate[] dateDuring = DateUtil.getWeekTimes(reimburseShapeDTO.getYear(), reimburseShapeDTO.getMonth(), reimburseShapeDTO.getWeek());
                reimburseShapeConDTO.setStartTime(dateDuring[0]);
                reimburseShapeConDTO.setEndTime(dateDuring[1]);
                break;
            case DAY:
                if (StringUtils.isBlank(reimburseShapeDTO.getTime())) {
                    throw new SerException("日期不能为空");
                }
                reimburseShapeConDTO.setStartTime(DateUtil.parseDate(reimburseShapeDTO.getTime()));
                reimburseShapeConDTO.setEndTime(DateUtil.parseDate(reimburseShapeDTO.getTime()));
                break;
            default:
                return null;
        }
        if (null == reimburseShapeConDTO.getStartTime() || null == reimburseShapeConDTO.getEndTime()) {
            reimShapeAllVO = null;
        } else {
            reimShapeAllVO = caculate(reimburseShapeConDTO);
        }

        return reimShapeAllVO;
    }


    @Override
    public ReimShapeMixVO collectSelfTrend(ReimburseTrendShapeDTO reimburseTrendShapeDTO) throws SerException {
        ReimShapeMixVO reimShapeMixVO = new ReimShapeMixVO();
        List<ReimShapeMixSeriesVO> mixSeriesVOList = new ArrayList<>(1);


        if (StringUtils.isBlank(reimburseTrendShapeDTO.getUserName())) {
            throw new SerException("用户名不能为空");
        }
        if (StringUtils.isBlank(reimburseTrendShapeDTO.getStartTime())) {
            throw new SerException("开始时间不能为空,格式为2017-09");
        }
        if (StringUtils.isBlank(reimburseTrendShapeDTO.getEndTime())) {
            throw new SerException("结束时间不能为空,格式为2017-09");
        }

        //用户
        String userName = reimburseTrendShapeDTO.getUserName();
        //处理第一个时间
        LocalDateTime startMonStart = DateUtil.parseDateTime(reimburseTrendShapeDTO.getStartTime() + "-01 00:00:00");
        LocalDateTime startMonEnd = startMonStart.with(TemporalAdjusters.lastDayOfMonth());
        //处理第二个时间
        LocalDateTime endMonStart = DateUtil.parseDateTime(reimburseTrendShapeDTO.getStartTime() + "-01 00:00:00");
        LocalDateTime endMonEnd = endMonStart.with(TemporalAdjusters.lastDayOfMonth());
        Double reimMoney = 0d;
        ReimShapeMixSeriesVO mixSeriesVO = new ReimShapeMixSeriesVO();
        List<Double> seriesDataList = new ArrayList<>();

        ReimburseRecordDTO dto = new ReimburseRecordDTO();
        //查询第一个时间所有申报报销记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        List<ReimburseRecord> list = reimburseRecordSer.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第一个时间所有待审核报销记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addWaitAuditQueryCon(dto);
        list = reimburseRecordSer.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第一个时间所有待支付报销记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addWaitPayQueryCon(dto);
        list = reimburseRecordSer.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);

        //查询第一个时间所有已支付报销记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addHasPayQueryCon(dto);
        list = reimburseRecordSer.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //封装返回数据
        mixSeriesVO.setName(reimburseTrendShapeDTO.getStartTime());
        mixSeriesVO.setType("bar");
        mixSeriesVO.setBarWidth("40%");
        mixSeriesVO.setSeriesDataVOList(seriesDataList);
        mixSeriesVOList.add(mixSeriesVO);

        //处理第二个数据
        seriesDataList = new ArrayList<>();
        ReimburseRecordDTO dto2 = new ReimburseRecordDTO();
        //查询第二个时间所有申报报销记录
        dto2 = addQueryCon(dto2, userName, startMonStart, startMonEnd);
        List<ReimburseRecord> list2 = reimburseRecordSer.findByCis(dto2);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第二个时间所有待审核报销记录
        dto2 = addQueryCon(dto2, userName, startMonStart, startMonEnd);
        dto2 = addWaitAuditQueryCon(dto2);
        list2 = reimburseRecordSer.findByCis(dto2);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第二个时间所有待支付报销记录
        dto2 = addQueryCon(dto2, userName, startMonStart, startMonEnd);
        dto2 = addWaitPayQueryCon(dto2);
        list2 = reimburseRecordSer.findByCis(dto2);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第二个时间所有已支付报销记录
        dto2 = addQueryCon(dto2, userName, startMonStart, startMonEnd);
        dto2 = addHasPayQueryCon(dto2);
        list2 = reimburseRecordSer.findByCis(dto2);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //封装返回数据
        mixSeriesVO.setName(reimburseTrendShapeDTO.getEndTime());
        mixSeriesVO.setType("line");
        mixSeriesVO.setSeriesDataVOList(seriesDataList);
        mixSeriesVOList.add(mixSeriesVO);

        ReimShapeXaxisVO xaxisVO = new ReimShapeXaxisVO();
        xaxisVO.setName("状态");
        xaxisVO.setType("category");
        xaxisVO.setData(Arrays.asList("申报报销记录", "待审核", "待支付", "已支付"));
        xaxisVO.setAxisTickBarVO(new AxisTickBarVO(true));
        xaxisVO.setAxisPointerBarVO(new AxisPointerBarVO("shadow"));
        reimShapeMixVO.setXaxisVO(xaxisVO);
        reimShapeMixVO.setTitleVO(new ReimShapeTitleVO(userName + "的报销情况变化趋势图", reimburseTrendShapeDTO.getStartTime() + "至" + reimburseTrendShapeDTO.getEndTime(), "center", "45%"));
        reimShapeMixVO.setToolTipVO(new ReimShapeMixToolTipVO("axis", new AxisPointerBarVO("cross")));
        reimShapeMixVO.setGridVO(new ReimShapeBarGridVO("20%", "30%", "60%", true));
        reimShapeMixVO.setRlegendVO(new ReimShapeLegendVO("x", "center", Arrays.asList(reimburseTrendShapeDTO.getStartTime(), reimburseTrendShapeDTO.getEndTime())));
        reimShapeMixVO.setYaxisVO(new ReimShapeYaxisVO("value", "金额(元)", 50, new AxisLabelVO("{value}")));
        reimShapeMixVO.setSeriesVO(mixSeriesVOList);
        return reimShapeMixVO;
    }


    @Override
    public ReimCompanyShapeBarVO collectGroupBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        ReimCompanyShapeBarVO reimCompanyShapeBarVO = new ReimCompanyShapeBarVO();

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(reimCompanyShapeDTO, ReimburseShapeConDTO.class);
        reimburseShapeConDTO.setReimburseShapeStatus(reimCompanyShapeDTO.getShapeStatus());
        if (null == reimCompanyShapeDTO.getShapeStatus()) {
            throw new SerException("汇总类型不能为空");
        }
        //根据选择的日周月年
        switch (reimCompanyShapeDTO.getShapeStatus()) {
            case YEAR:
                if (StringUtils.isBlank("" + reimCompanyShapeDTO.getYear())) {
                    throw new SerException("年份不能为空");
                }
                reimburseShapeConDTO.setStartTime(DateUtil.parseDate(reimCompanyShapeDTO.getYear() + "-01-01"));
                reimburseShapeConDTO.setEndTime(DateUtil.parseDate(reimCompanyShapeDTO.getYear() + "-12-31"));

                break;
            case MONTH:
                if (StringUtils.isBlank("" + reimCompanyShapeDTO.getYear()) || StringUtils.isBlank("" + reimCompanyShapeDTO.getMonth())) {
                    throw new SerException("年份或月份不能为空");
                }
                reimburseShapeConDTO.setStartTime(DateUtil.getStartDayOfMonth(reimCompanyShapeDTO.getYear(), reimCompanyShapeDTO.getMonth()));
                reimburseShapeConDTO.setEndTime(DateUtil.getEndDaYOfMonth(reimCompanyShapeDTO.getYear(), reimCompanyShapeDTO.getMonth()));
                break;
            case WEEK:
                if (StringUtils.isBlank("" + reimCompanyShapeDTO.getYear()) || StringUtils.isBlank("" + reimCompanyShapeDTO.getMonth()) || StringUtils.isBlank("" + reimburseShapeDTO.getWeek())) {
                    throw new SerException("年份或月份或周数不能为空");
                }
                LocalDate[] dateDuring = DateUtil.getWeekTimes(reimCompanyShapeDTO.getYear(), reimCompanyShapeDTO.getMonth(), reimCompanyShapeDTO.getWeek());
                reimburseShapeConDTO.setStartTime(dateDuring[0]);
                reimburseShapeConDTO.setEndTime(dateDuring[1]);
                break;
            default:
                return null;
        }
        if (null == reimburseShapeConDTO.getStartTime() || null == reimburseShapeConDTO.getEndTime()) {
            return null;
        }

        //查询公司所有的项目组
        List<String> groupList = positionDetailUserAPI.getAllDepartment();
        //查询所有项目组的申报报销记录
        //查询所有项目组的待审核
        //查询所有项目组的待支付
        //查询所有项目组的已支付


        //查询他们在申报报销记录和待审核和待支付和已支付里面的钱

//        reimCompanyShapeBarVO = caculate(reimburseShapeConDTO);

        return reimCompanyShapeBarVO;
    }
}