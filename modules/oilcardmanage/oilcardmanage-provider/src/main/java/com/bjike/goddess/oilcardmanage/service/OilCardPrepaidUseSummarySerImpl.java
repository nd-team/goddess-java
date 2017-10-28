package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.bo.DispatchCarInfoBO;
import com.bjike.goddess.oilcardmanage.bo.*;
import com.bjike.goddess.oilcardmanage.dto.OilCardBasicDTO;
import com.bjike.goddess.oilcardmanage.dto.OilCardPrepaidUseSummaryDTO;
import com.bjike.goddess.oilcardmanage.dto.OilCardReceiveDTO;
import com.bjike.goddess.oilcardmanage.dto.OilCardRechargeDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.entity.OilCardPrepaidUseSummary;
import com.bjike.goddess.oilcardmanage.entity.OilCardReceive;
import com.bjike.goddess.oilcardmanage.entity.OilCardRecharge;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* 图形化业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-16 09:21 ]
* @Description:	[ 图形化业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="oilcardmanageSerCache")
@Service
public class OilCardPrepaidUseSummarySerImpl extends ServiceImpl<OilCardPrepaidUseSummary, OilCardPrepaidUseSummaryDTO> implements OilCardPrepaidUseSummarySer {


    @Autowired
    private OilCardBasicSer oilCardBasicSer;

    @Autowired
    private OilCardReceiveSer oilCardReceiveSer;

    @Autowired
    private OilCardRechargeSer oilCardRechargeSer;
    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;

    @Autowired
    private ModuleAPI moduleAPI;

    @Override
    public List<OilCardPrepaidAreaBO> dayCollect(String day) throws SerException {
        LocalDate[] startTime = null;
        if (StringUtils.isNotBlank(day)){
            LocalDate startDate = DateUtil.parseDate(day);
            LocalDate endDate = DateUtil.parseDate(day);
            startTime = new LocalDate[]{startDate,endDate};
        }else {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = LocalDate.now();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);
    }

    @Override
    public List<OilCardPrepaidAreaBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        LocalDate[] startDate = null;
        if (year !=null && month != null && week != null){
            startDate = DateUtil.getWeekTimes(year,month,week);
        }else {
            LocalDate starDay = DateUtil.getStartWeek();
            LocalDate endDay = DateUtil.getEndWeek();
            startDate = new LocalDate[]{starDay,endDay};
        }
        return collect(startDate);
    }

    @Override
    public List<OilCardPrepaidAreaBO> monthCollect(Integer year, Integer month) throws SerException {
        LocalDate[] startTime = null;
        if (year != null && month != null){
            LocalDate startDate = DateUtil.getStartDayOfMonth(year,month);
            LocalDate endDate = DateUtil.getEndDaYOfMonth(year,month);
            startTime = new LocalDate[]{startDate,endDate};
        }else{
            LocalDate startDate = DateUtil.getStartMonth();
            LocalDate endDate = DateUtil.getEndMonth();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);
    }

    @Override
    public List<OilCardPrepaidAreaBO> allCollect(String day) throws SerException {
        LocalDate[] startTime = null;
        if (StringUtils.isNotBlank(day)){
            LocalDate startDate = DateUtil.parseDate("1902-01-01");
            LocalDate endDate = DateUtil.parseDate(day);
            startTime = new LocalDate[]{startDate,endDate};
        }else {
            LocalDate startDate = DateUtil.parseDate("1902-01-01");
            LocalDate endDate = LocalDate.now();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);
    }

    private List<OilCardPrepaidAreaBO> collect(LocalDate[] startDate) throws SerException{
        List<OilCardBasic> oilCardBasics = oilCardBasicSer.findAll();
        Set<String> areas = oilCardBasics.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<OilCardPrepaidAreaBO> oilCardPrepaidAreaBOS = new ArrayList<>();
        for(String area : areas){
            OilCardPrepaidAreaBO oilCardPrepaidAreaBO = new OilCardPrepaidAreaBO();
            OilCardBasicDTO oilCardBasicDTO = new OilCardBasicDTO();
            oilCardBasicDTO.getConditions().add(Restrict.eq("area",area));
            List<OilCardBasic> oilCardBasics1 = oilCardBasicSer.findByCis(oilCardBasicDTO);
            Set<String> departments = oilCardBasics1.stream().map(p -> p.getDepartment()).collect(Collectors.toSet());
            List<OilCardPrepaidDepartmentBO> oilCardPrepaidDepartmentBOS = new ArrayList<>();
            for(String department : departments){
                OilCardPrepaidDepartmentBO oilCardPrepaidDepartmentBO = new OilCardPrepaidDepartmentBO();
                OilCardPrepaidUseSummaryBO oilCardPrepaidUseSummaryBO = new OilCardPrepaidUseSummaryBO();
                //油卡数
                Integer oilCardNumber = 0;
                //更新油卡余额信息次数
                Integer updateOilcardBalanceNumber = 0;
                //充值次数
                Integer prepaidNumber = 0;
                //充值总金额
                Double prepaidTotalMoney = 0.0;
                //加油总金额
                Double addOilTotalMoney = 0.0;
                //余额
                Double balance = 0.0;
                OilCardReceiveDTO oilCardReceiveDTO1 = new OilCardReceiveDTO();
                oilCardReceiveDTO1.getConditions().add(Restrict.eq("area",area));
                oilCardReceiveDTO1.getConditions().add(Restrict.eq("department",department));
                List<OilCardReceive> oilCardReceives2 = oilCardReceiveSer.findByCis(oilCardReceiveDTO1);
                oilCardNumber = oilCardReceives2.size();


                LocalDateTime startTime = DateUtil.parseDateTime(startDate[0]+"00:00:00");
                LocalDateTime endTime = DateUtil.parseDateTime(startDate[1]+"23:59:59");
                LocalDateTime[] startTime2 = new LocalDateTime[]{startTime,endTime};
                OilCardRechargeDTO oilCardRechargeDTO = new OilCardRechargeDTO();
                oilCardRechargeDTO.getConditions().add(Restrict.eq("area",area));
                oilCardRechargeDTO.getConditions().add(Restrict.eq("department",department));
                oilCardRechargeDTO.getConditions().add(Restrict.between("modifyTime",startTime2));
                List<OilCardRecharge> oilCardRecharges = oilCardRechargeSer.findByCis(oilCardRechargeDTO);
                updateOilcardBalanceNumber = oilCardRecharges.size();

                OilCardRechargeDTO oilCardRechargeDTO1 = new OilCardRechargeDTO();
                oilCardRechargeDTO1.getConditions().add(Restrict.eq("area",area));
                oilCardRechargeDTO1.getConditions().add(Restrict.eq("department",department));
                oilCardRechargeDTO1.getConditions().add(Restrict.between("rechargeDate",startDate));
                List<OilCardRecharge> oilCardRechargeList = oilCardRechargeSer.findByCis(oilCardRechargeDTO);
                prepaidNumber = oilCardRechargeList.size();

                prepaidTotalMoney = oilCardRechargeList.stream().filter(p -> p.getRechargeMoney() != null).mapToDouble(p -> p.getRechargeMoney()).sum();
                List<DispatchCarInfoBO> dispatchCarInfoBOS = new ArrayList<>();
                if (moduleAPI.isCheck("dispatchcar")) {
                    dispatchCarInfoBOS = dispatchCarInfoAPI.findInformation(area, department, startDate);
                }else {
                    throw new SerException("请去模块关联设置出车记录的关联");
                }
                addOilTotalMoney = dispatchCarInfoBOS.stream().filter(p -> p.getAddOilAmountMoney() != null).mapToDouble(p -> p.getAddOilAmountMoney()).sum();

                Set<String> cardNumbers = oilCardRechargeList.stream().map(p -> p.getOilCardBasic().getOilCardNumber()).collect(Collectors.toSet());
                List<OilCardBasic> oilCardBasics2 = new ArrayList<>();
                for(String cardNumber : cardNumbers){
                    OilCardRechargeDTO oilCardRechargeDTO2 = new OilCardRechargeDTO();
                    oilCardRechargeDTO2.getConditions().add(Restrict.eq("oilCardBasic.oilCardCode",cardNumber));
                    oilCardRechargeDTO2.getSorts().add("createTime=desc");
                    List<OilCardRecharge> oilCardRecharges1 = oilCardRechargeSer.findByCis(oilCardRechargeDTO2);
                    OilCardBasic oilCardBasic = new OilCardBasic();
                    oilCardBasic.setBalance(oilCardRecharges1.get(0).getOilCardBasic().getBalance());
                    oilCardBasics2.add(oilCardBasic);
                }
                balance = oilCardBasics2.stream().filter(p -> p.getBalance() != null).mapToDouble(p -> p.getBalance()).sum();


                oilCardPrepaidUseSummaryBO.setOilCardNumber(oilCardNumber);
                oilCardPrepaidUseSummaryBO.setAddOilTotalMoney(addOilTotalMoney);
                oilCardPrepaidUseSummaryBO.setBalance(balance);
                oilCardPrepaidUseSummaryBO.setPrepaidNumber(prepaidNumber);
                oilCardPrepaidUseSummaryBO.setPrepaidTotalMoney(prepaidTotalMoney);
                oilCardPrepaidUseSummaryBO.setUpdateOilcardBalanceNumber(updateOilcardBalanceNumber);

                oilCardPrepaidDepartmentBO.setDepartment(department);
                oilCardPrepaidDepartmentBO.setOilCardPrepaidUseSummary(oilCardPrepaidUseSummaryBO);
                oilCardPrepaidDepartmentBOS.add(oilCardPrepaidDepartmentBO);
            }
            oilCardPrepaidAreaBO.setArea(area);
            oilCardPrepaidAreaBO.setOilCardPrepaidDepartmentList(oilCardPrepaidDepartmentBOS);
            oilCardPrepaidAreaBOS.add(oilCardPrepaidAreaBO);
        }
        return oilCardPrepaidAreaBOS;
    }

    @Override
    public OptionBO figureShowDay(String day) throws SerException {
        LocalDate[] startTime = null;
        if (StringUtils.isNotBlank(day)){
            LocalDate startDate = DateUtil.parseDate(day);
            LocalDate endDate = DateUtil.parseDate(day);
            startTime = new LocalDate[]{startDate,endDate};
        }else {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = LocalDate.now();
            startTime = new LocalDate[]{startDate,endDate};
        }
        String text_1 = "油卡信息管理日汇总("+day+")";
        return totalShowMethod(startTime,text_1);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        LocalDate[] startDate = null;
        if (year !=null && month != null && week != null){
            startDate = DateUtil.getWeekTimes(year,month,week);
        }else {
            LocalDate starDay = DateUtil.getStartWeek();
            LocalDate endDay = DateUtil.getEndWeek();
            startDate = new LocalDate[]{starDay,endDay};
        }
        String text_1 = "油卡信息管理周汇总("+startDate[0]+"-"+startDate[1]+")";
        return totalShowMethod(startDate,text_1);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        LocalDate[] startTime = null;
        if (year != null && month != null){
            LocalDate startDate = DateUtil.getStartDayOfMonth(year,month);
            LocalDate endDate = DateUtil.getEndDaYOfMonth(year,month);
            startTime = new LocalDate[]{startDate,endDate};
        }else{
            LocalDate startDate = DateUtil.getStartMonth();
            LocalDate endDate = DateUtil.getEndMonth();
            startTime = new LocalDate[]{startDate,endDate};
        }
        String text_1 = "油卡信息管理月汇总("+month+"月)";
        return totalShowMethod(startTime,text_1);
    }

    @Override
    public OptionBO figrueShowTotal(String day) throws SerException {
        LocalDate[] startTime = null;
        if (StringUtils.isNotBlank(day)){
            LocalDate startDate = DateUtil.parseDate("1902-01-01");
            LocalDate endDate = DateUtil.parseDate(day);
            startTime = new LocalDate[]{startDate,endDate};
        }else {
            LocalDate startDate = DateUtil.parseDate("1902-01-01");
            LocalDate endDate = LocalDate.now();
            startTime = new LocalDate[]{startDate,endDate};
        }
        String text_1 = "油卡管理累计汇总"+"(累计)";
        return totalShowMethod(startTime,text_1);
    }


    private OptionBO totalShowMethod(LocalDate[] startDate,String text_1) throws SerException{
        List<OilCardBasic> oilCardBasics = oilCardBasicSer.findAll();
        Set<String> departments = oilCardBasics.stream().map(p -> p.getDepartment()).collect(Collectors.toSet());
        List<OilCardPrepaidDepartmentBO> oilCardPrepaidDepartmentBOS = new ArrayList<>();
        for (String department : departments) {
            OilCardPrepaidDepartmentBO oilCardPrepaidDepartmentBO = new OilCardPrepaidDepartmentBO();
            OilCardPrepaidUseSummaryBO oilCardPrepaidUseSummaryBO = new OilCardPrepaidUseSummaryBO();
            //油卡数
            Integer oilCardNumber = 0;
            //更新油卡余额信息次数
            Integer updateOilcardBalanceNumber = 0;
            //充值次数
            Integer prepaidNumber = 0;
            //充值总金额
            Double prepaidTotalMoney = 0.0;
            //加油总金额
            Double addOilTotalMoney = 0.0;
            //余额
            Double balance = 0.0;
            OilCardReceiveDTO oilCardReceiveDTO1 = new OilCardReceiveDTO();
            oilCardReceiveDTO1.getConditions().add(Restrict.eq("department", department));
            List<OilCardReceive> oilCardReceives2 = oilCardReceiveSer.findByCis(oilCardReceiveDTO1);
            oilCardNumber = oilCardReceives2.size();


            LocalDateTime startTime = DateUtil.parseDateTime(startDate[0] + "00:00:00");
            LocalDateTime endTime = DateUtil.parseDateTime(startDate[1] + "23:59:59");
            LocalDateTime[] startTime2 = new LocalDateTime[]{startTime, endTime};
            OilCardRechargeDTO oilCardRechargeDTO = new OilCardRechargeDTO();
            oilCardRechargeDTO.getConditions().add(Restrict.eq("department", department));
            oilCardRechargeDTO.getConditions().add(Restrict.between("modifyTime", startTime2));
            List<OilCardRecharge> oilCardRecharges = oilCardRechargeSer.findByCis(oilCardRechargeDTO);
            updateOilcardBalanceNumber = oilCardRecharges.size();

            OilCardRechargeDTO oilCardRechargeDTO1 = new OilCardRechargeDTO();
            oilCardRechargeDTO1.getConditions().add(Restrict.eq("department", department));
            oilCardRechargeDTO1.getConditions().add(Restrict.between("rechargeDate", startDate));
            List<OilCardRecharge> oilCardRechargeList = oilCardRechargeSer.findByCis(oilCardRechargeDTO);
            prepaidNumber = oilCardRechargeList.size();

            prepaidTotalMoney = oilCardRechargeList.stream().filter(p -> p.getRechargeMoney() != null).mapToDouble(p -> p.getRechargeMoney()).sum();
            List<DispatchCarInfoBO> dispatchCarInfoBOS = new ArrayList<>();
            if (moduleAPI.isCheck("dispatchcar")) {
                dispatchCarInfoBOS = dispatchCarInfoAPI.findInformation(department, startDate);
            } else {
                throw new SerException("请去模块关联设置出车记录的关联");
            }
            addOilTotalMoney = dispatchCarInfoBOS.stream().filter(p -> p.getAddOilAmountMoney() != null).mapToDouble(p -> p.getAddOilAmountMoney()).sum();

            Set<String> cardNumbers = oilCardRechargeList.stream().map(p -> p.getOilCardBasic().getOilCardNumber()).collect(Collectors.toSet());
            List<OilCardBasic> oilCardBasics2 = new ArrayList<>();
            for (String cardNumber : cardNumbers) {
                OilCardRechargeDTO oilCardRechargeDTO2 = new OilCardRechargeDTO();
                oilCardRechargeDTO2.getConditions().add(Restrict.eq("oilCardBasic.oilCardCode", cardNumber));
                oilCardRechargeDTO2.getSorts().add("createTime=desc");
                List<OilCardRecharge> oilCardRecharges1 = oilCardRechargeSer.findByCis(oilCardRechargeDTO2);
                OilCardBasic oilCardBasic = new OilCardBasic();
                oilCardBasic.setBalance(oilCardRecharges1.get(0).getOilCardBasic().getBalance());
                oilCardBasics2.add(oilCardBasic);
            }
            balance = oilCardBasics2.stream().filter(p -> p.getBalance() != null).mapToDouble(p -> p.getBalance()).sum();


            oilCardPrepaidUseSummaryBO.setOilCardNumber(oilCardNumber);
            oilCardPrepaidUseSummaryBO.setAddOilTotalMoney(addOilTotalMoney);
            oilCardPrepaidUseSummaryBO.setBalance(balance);
            oilCardPrepaidUseSummaryBO.setPrepaidNumber(prepaidNumber);
            oilCardPrepaidUseSummaryBO.setPrepaidTotalMoney(prepaidTotalMoney);
            oilCardPrepaidUseSummaryBO.setUpdateOilcardBalanceNumber(updateOilcardBalanceNumber);

            oilCardPrepaidDepartmentBO.setDepartment(department);
            oilCardPrepaidDepartmentBO.setOilCardPrepaidUseSummary(oilCardPrepaidUseSummaryBO);
            oilCardPrepaidDepartmentBOS.add(oilCardPrepaidDepartmentBO);
        }

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list2 = new ArrayList<>();

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_3 = new String[]{"油卡数","更新油卡余额信息次数","充值次数","充值总金额","加油总金额","余额"};
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (oilCardPrepaidDepartmentBOS != null && oilCardPrepaidDepartmentBOS.size() > 0){
            for (OilCardPrepaidDepartmentBO oilCardPrepaidDepartmentBO : oilCardPrepaidDepartmentBOS){
                text_list2.add(oilCardPrepaidDepartmentBO.getDepartment());

                //柱状图数据
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(oilCardPrepaidDepartmentBO.getDepartment());
                seriesBO.setType("bar");

                Double[] number = new Double[]{(double)oilCardPrepaidDepartmentBO.getOilCardPrepaidUseSummary().getOilCardNumber()
                        ,(double)oilCardPrepaidDepartmentBO.getOilCardPrepaidUseSummary().getUpdateOilcardBalanceNumber()
                ,(double)oilCardPrepaidDepartmentBO.getOilCardPrepaidUseSummary().getPrepaidNumber()
                        , oilCardPrepaidDepartmentBO.getOilCardPrepaidUseSummary().getPrepaidTotalMoney()
                ,oilCardPrepaidDepartmentBO.getOilCardPrepaidUseSummary().getAddOilTotalMoney()
                        ,oilCardPrepaidDepartmentBO.getOilCardPrepaidUseSummary().getBalance()};
                seriesBO.setData(number);
                seriesBOList.add(seriesBO);
            }
        }

        String[] text_2 = new String[text_list2.size()];
        text_2 = text_list2.toArray(text_2);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

}