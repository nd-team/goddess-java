package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.carinfo.bo.*;
import com.bjike.goddess.carinfo.dto.DriverRecruitDTO;
import com.bjike.goddess.carinfo.entity.DriverInfo;
import com.bjike.goddess.carinfo.entity.DriverRecruit;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.carinfo.dto.CollectCarinfoDTO;
import com.bjike.goddess.carinfo.entity.CollectCarinfo;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.rentcar.bo.DriverInfoBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* 司机信息管理汇总业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-10 05:56 ]
* @Description:	[ 司机信息管理汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="carinfoSerCache")
@Service
public class CollectCarinfoSerImpl extends ServiceImpl<CollectCarinfo, CollectCarinfoDTO> implements CollectCarinfoSer {

    @Autowired
    private DriverInfoSer driverInfoSer;

    @Autowired
    private DriverRecruitSer driverRecruitSer;

    @Override
    public List<AreaBO> dayCollect(String day) throws SerException {
        LocalDate startDay = LocalDate.now();
        if (day !=null){
            startDay = DateUtil.parseDate(day);
        }
        String[] fields2 = new String[]{"area","department"};
        String[] fields = new String[]{"department"};
        StringBuilder sql = new StringBuilder("select area,department ");
        sql.append("from carinfo_driverinfo");
        List<DriverInfo> driverInfo = driverInfoSer.findBySql(sql.toString(),DriverInfo.class,fields2);
        Set<String> areas = driverInfo.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<AreaBO> areaBOS = new ArrayList<>();
        for (String area : areas ){
            AreaBO areaBO = new AreaBO();
            StringBuilder sql2 = new StringBuilder("select department ");
            sql2.append("from carinfo_driverinfo");
            sql2.append("where area= '"+area+"'");
            List<DriverInfo> driverInfos = driverInfoSer.findBySql(sql2.toString(),DriverInfo.class,fields);
            Set<String> departments = driverInfos.stream().map(p -> p.getDepartment()).collect(Collectors.toSet());
            List<DepartmentBO> departmentBOS = new ArrayList<>();
            for (String department : departments){
                DepartmentBO departmentBO = new DepartmentBO();
                CollectCarinfoBO collectCarinfoBO = new CollectCarinfoBO();
                Integer businessDemand = 0;
                Integer projectDemand = 0;
                Integer availalbeDriver = 0;
                Integer needDriver = 0;
                Integer waitSignDealNumber = 0;
                Integer driverInformationCollect = 0;
                Integer driverContactNumber = 0;
                Integer waitDriverContactNumber = 0;
                //todo 商务需求
                //todo 项目需求
                StringBuilder sql3 = new StringBuilder("select department ");
                sql3.append("from rentcar_driverinfo");
                sql3.append("where area= '"+area+"'");
                sql3.append("and department= '"+department+"' ");
                sql3.append("and agreementStatus = '0'");
                List<DriverInfoBO> driverInfos1 = driverInfoSer.findBySql(sql3.toString(),DriverInfoBO.class,fields);
                availalbeDriver = driverInfos1.size();
                needDriver = availalbeDriver + businessDemand + projectDemand;

                StringBuilder sql4 = new StringBuilder("select department ");
                sql4.append("from carinfo_driverrecruit");
                sql4.append("and area= '"+area+"'");
                sql4.append("and department= '"+department+"' ");
                sql4.append("and enSureAgreement = '0'");
                List<DriverRecruit> driverInfos2 = driverRecruitSer.findBySql(sql4.toString(),DriverRecruit.class,fields);
                waitSignDealNumber = driverInfos2.size();

                StringBuilder sql5 = new StringBuilder("select department ");
                sql5.append("from carinfo_driverrecruit");
                sql5.append("and area= '"+area+"'");
                sql5.append("and department= '"+department+"' ");
                sql5.append("and informatioCollectionTime = '"+startDay+"' ");
                List<DriverRecruit> driverInfos3 = driverRecruitSer.findBySql(sql5.toString(),DriverRecruit.class,fields);
                driverInformationCollect = driverInfos3.size();

                sql5.append("and contact= '0' ");
                List<DriverRecruit> driverInfos4 = driverRecruitSer.findBySql(sql5.toString(),DriverRecruit.class,fields);
                driverContactNumber = driverInfos4.size();


                StringBuilder sql6 = new StringBuilder("select department ");
                sql6.append("from carinfo_driverrecruit");
                sql6.append("and area= '"+area+"'");
                sql6.append("and department= '"+department+"' ");
                sql6.append("and informatioCollectionTime = '"+startDay+"' ");
                sql6.append("and contact= '1' ");
                List<DriverRecruit> driverInfos5 = driverRecruitSer.findBySql(sql6.toString(),DriverRecruit.class,fields);
                waitDriverContactNumber = driverInfos5.size();

                collectCarinfoBO.setAvailalbeDriver(availalbeDriver);
                collectCarinfoBO.setBusinessDemand(businessDemand);
                collectCarinfoBO.setDriverContactNumber(driverContactNumber);
                collectCarinfoBO.setDriverInformationCollect(driverInformationCollect);
                collectCarinfoBO.setNeedDriver(needDriver);
                collectCarinfoBO.setWaitSignDealNumber(waitSignDealNumber);
                collectCarinfoBO.setProjectDemand(projectDemand);
                collectCarinfoBO.setWaitDriverContactNumber(waitDriverContactNumber);

                departmentBO.setCollectCarinfo(collectCarinfoBO);
                departmentBOS.add(departmentBO);
            }
            areaBO.setArea(area);
            areaBOS.add(areaBO);
        }
        return areaBOS;
    }

    @Override
    public List<AreaBO> weekCollect(Integer year,Integer month,Integer week) throws SerException {
        LocalDate[] startWeek = null;
        if (year != null && month != null && week != null ){
            startWeek = DateUtil.getWeekTimes(year,month,week);
        }else{
            LocalDate startDate = DateUtil.getStartWeek();
            LocalDate endDate = DateUtil.getEndWeek();
            startWeek = new LocalDate[]{startDate,endDate};
        }
        return collect(startWeek);
    }

    @Override
    public List<AreaBO> monthCollect(Integer year, Integer month) throws SerException {
        LocalDate[] startMonth = null;
        if (year != null && month != null){
            LocalDate startDate = DateUtil.getStartDayOfMonth(year,month);
            LocalDate endDate = DateUtil.getEndDaYOfMonth(year,month);
            startMonth = new LocalDate[]{startDate,endDate};
        }else{
            LocalDate startDate = DateUtil.getStartMonth();
            LocalDate endDate = DateUtil.getEndMonth();
            startMonth = new LocalDate[]{startDate,endDate};
        }
        return collect(startMonth);
    }

    @Override
    public List<AreaBO> allCollect(Integer year) throws SerException {
        LocalDate[] startYear = null;
        if (year != null){
            LocalDate startDate = DateUtil.parseDate(year+"-01-01");
            LocalDate endDate = DateUtil.parseDate(year+"-12-31");
            startYear = new LocalDate[]{startDate,endDate};
        }else{
            LocalDate startDate = DateUtil.getStartYear();
            LocalDate endDate = DateUtil.getEndYear();
            startYear = new LocalDate[]{startDate,endDate};
        }
        return collect(startYear);
    }


    private List<AreaBO> collect(LocalDate[] time) throws SerException{
        String[] fields2 = new String[]{"area","department"};
        String[] fields = new String[]{"department"};
        StringBuilder sql = new StringBuilder("select area,department ");
        sql.append("from carinfo_driverinfo");
        List<DriverInfo> driverInfo = driverInfoSer.findBySql(sql.toString(),DriverInfo.class,fields2);
        Set<String> areas = driverInfo.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<AreaBO> areaBOS = new ArrayList<>();
        for (String area : areas ){
            AreaBO areaBO = new AreaBO();
            StringBuilder sql2 = new StringBuilder("select department ");
            sql2.append("from carinfo_driverinfo");
            sql2.append("where area= '"+area+"'");
            List<DriverInfo> driverInfos = driverInfoSer.findBySql(sql2.toString(),DriverInfo.class,fields);
            Set<String> departments = driverInfos.stream().map(p -> p.getDepartment()).collect(Collectors.toSet());
            List<DepartmentBO> departmentBOS = new ArrayList<>();
            for (String department : departments){
                DepartmentBO departmentBO = new DepartmentBO();
                CollectCarinfoBO collectCarinfoBO = new CollectCarinfoBO();
                Integer businessDemand = 0;
                Integer projectDemand = 0;
                Integer availalbeDriver = 0;
                Integer needDriver = 0;
                Integer waitSignDealNumber = 0;
                Integer driverInformationCollect = 0;
                Integer driverContactNumber = 0;
                Integer waitDriverContactNumber = 0;
                //todo 商务需求
                //todo 项目需求
                StringBuilder sql3 = new StringBuilder("select department ");
                sql3.append("from rentcar_driverinfo");
                sql3.append("where area= '"+area+"'");
                sql3.append("and department= '"+department+"' ");
                sql3.append("and agreementStatus = '0'");
                List<DriverInfoBO> driverInfos1 = driverInfoSer.findBySql(sql3.toString(),DriverInfoBO.class,fields);
                availalbeDriver = driverInfos1.size();
                needDriver = availalbeDriver + businessDemand + projectDemand;

                StringBuilder sql4 = new StringBuilder("select department ");
                sql4.append("from carinfo_driverrecruit");
                sql4.append("and area= '"+area+"'");
                sql4.append("and department= '"+department+"' ");
                sql4.append("and enSureAgreement = '0'");
                List<DriverRecruit> driverInfos2 = driverRecruitSer.findBySql(sql4.toString(),DriverRecruit.class,fields);
                waitSignDealNumber = driverInfos2.size();

                DriverRecruitDTO driverRecruitDTO = new DriverRecruitDTO();
                driverRecruitDTO.getConditions().add(Restrict.between("informatioCollectionTime",time));
                driverRecruitDTO.getConditions().add(Restrict.eq("area",area));
                driverRecruitDTO.getConditions().add(Restrict.eq("department",department));
                List<DriverRecruit> driverInfos3 = driverRecruitSer.findByCis(driverRecruitDTO);
                driverInformationCollect = driverInfos3.size();

                driverRecruitDTO.getConditions().add(Restrict.eq("contact",true));
                List<DriverRecruit> driverInfos4 = driverRecruitSer.findByCis(driverRecruitDTO);
                driverContactNumber = driverInfos4.size();


                DriverRecruitDTO driverRecruitDTO1 = new DriverRecruitDTO();
                driverRecruitDTO1.getConditions().add(Restrict.eq("area",area));
                driverRecruitDTO1.getConditions().add(Restrict.eq("department",department));
                driverRecruitDTO1.getConditions().add(Restrict.between("informatioCollectionTime",time));
                driverRecruitDTO1.getConditions().add(Restrict.eq("contact",false));
                List<DriverRecruit> driverInfos5 = driverRecruitSer.findByCis(driverRecruitDTO1);
                waitDriverContactNumber = driverInfos5.size();

                collectCarinfoBO.setAvailalbeDriver(availalbeDriver);
                collectCarinfoBO.setBusinessDemand(businessDemand);
                collectCarinfoBO.setDriverContactNumber(driverContactNumber);
                collectCarinfoBO.setDriverInformationCollect(driverInformationCollect);
                collectCarinfoBO.setNeedDriver(needDriver);
                collectCarinfoBO.setWaitSignDealNumber(waitSignDealNumber);
                collectCarinfoBO.setProjectDemand(projectDemand);
                collectCarinfoBO.setWaitDriverContactNumber(waitDriverContactNumber);

                departmentBO.setCollectCarinfo(collectCarinfoBO);
                departmentBOS.add(departmentBO);
            }
            areaBO.setArea(area);
            areaBOS.add(areaBO);
        }
        return areaBOS;
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
        String text_1 = "司机信息管理日汇总("+day+")";
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
        String text_1 = "司机信息管理周汇总("+startDate[0]+"-"+startDate[1]+")";
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
        String text_1 = "司机信息管理月汇总("+month+"月)";
        return totalShowMethod(startTime,text_1);
    }

    @Override
    public OptionBO figrureShowTotal(String day) throws SerException {
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
        String text_1 = "司机管理累计汇总"+"(累计)";
        return totalShowMethod(startTime,text_1);
    }

    private OptionBO totalShowMethod(LocalDate[] startTime,String text_1) throws SerException{
            String[] fields = new String[]{"department"};
            StringBuilder sql2 = new StringBuilder("select department ");
            sql2.append("from carinfo_driverinfo");
            List<DriverInfo> driverInfos = driverInfoSer.findBySql(sql2.toString(),DriverInfo.class,fields);
            Set<String> departments = driverInfos.stream().map(p -> p.getDepartment()).collect(Collectors.toSet());
            List<DepartmentBO> departmentBOS = new ArrayList<>();
            for (String department : departments){
                DepartmentBO departmentBO = new DepartmentBO();
                CollectCarinfoBO collectCarinfoBO = new CollectCarinfoBO();
                Integer businessDemand = 0;
                Integer projectDemand = 0;
                Integer availalbeDriver = 0;
                Integer needDriver = 0;
                Integer waitSignDealNumber = 0;
                Integer driverInformationCollect = 0;
                Integer driverContactNumber = 0;
                Integer waitDriverContactNumber = 0;
                //todo 商务需求
                //todo 项目需求
                StringBuilder sql3 = new StringBuilder("select department ");
                sql3.append("from rentcar_driverinfo");
                sql3.append("and department= '"+department+"' ");
                sql3.append("and agreementStatus = '0'");
                List<DriverInfoBO> driverInfos1 = driverInfoSer.findBySql(sql3.toString(),DriverInfoBO.class,fields);
                availalbeDriver = driverInfos1.size();
                needDriver = availalbeDriver + businessDemand + projectDemand;

                StringBuilder sql4 = new StringBuilder("select department ");
                sql4.append("from carinfo_driverrecruit");
                sql4.append("and department= '"+department+"' ");
                sql4.append("and enSureAgreement = '0'");
                List<DriverRecruit> driverInfos2 = driverRecruitSer.findBySql(sql4.toString(),DriverRecruit.class,fields);
                waitSignDealNumber = driverInfos2.size();

                DriverRecruitDTO driverRecruitDTO = new DriverRecruitDTO();
                driverRecruitDTO.getConditions().add(Restrict.between("informatioCollectionTime",startTime));
                driverRecruitDTO.getConditions().add(Restrict.eq("department",department));
                List<DriverRecruit> driverInfos3 = driverRecruitSer.findByCis(driverRecruitDTO);
                driverInformationCollect = driverInfos3.size();

                driverRecruitDTO.getConditions().add(Restrict.eq("contact",true));
                List<DriverRecruit> driverInfos4 = driverRecruitSer.findByCis(driverRecruitDTO);
                driverContactNumber = driverInfos4.size();


                DriverRecruitDTO driverRecruitDTO1 = new DriverRecruitDTO();
                driverRecruitDTO1.getConditions().add(Restrict.eq("department",department));
                driverRecruitDTO1.getConditions().add(Restrict.between("informatioCollectionTime",startTime));
                driverRecruitDTO1.getConditions().add(Restrict.eq("contact",false));
                List<DriverRecruit> driverInfos5 = driverRecruitSer.findByCis(driverRecruitDTO1);
                waitDriverContactNumber = driverInfos5.size();

                collectCarinfoBO.setAvailalbeDriver(availalbeDriver);
                collectCarinfoBO.setBusinessDemand(businessDemand);
                collectCarinfoBO.setDriverContactNumber(driverContactNumber);
                collectCarinfoBO.setDriverInformationCollect(driverInformationCollect);
                collectCarinfoBO.setNeedDriver(needDriver);
                collectCarinfoBO.setWaitSignDealNumber(waitSignDealNumber);
                collectCarinfoBO.setProjectDemand(projectDemand);
                collectCarinfoBO.setWaitDriverContactNumber(waitDriverContactNumber);

                departmentBO.setCollectCarinfo(collectCarinfoBO);
                departmentBOS.add(departmentBO);
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
        String[] text_3 = new String[]{"商务需求","项目需求","可用司机","还需司机数","待签订协议数","司机信息收集数","司机已联系数","待联系司机数"};
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (departmentBOS != null && departmentBOS.size() > 0){
            for (DepartmentBO departmentBO : departmentBOS){
                text_list2.add(departmentBO.getDepartment());

                //柱状图数据
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(departmentBO.getDepartment());
                seriesBO.setType("bar");

                Integer[] number = new Integer[]{departmentBO.getCollectCarinfo().getBusinessDemand()
                        ,departmentBO.getCollectCarinfo().getProjectDemand()
                        ,departmentBO.getCollectCarinfo().getAvailalbeDriver()
                        ,departmentBO.getCollectCarinfo().getNeedDriver()
                        ,departmentBO.getCollectCarinfo().getWaitSignDealNumber()
                        ,departmentBO.getCollectCarinfo().getDriverInformationCollect()
                        ,departmentBO.getCollectCarinfo().getDriverContactNumber()
                        ,departmentBO.getCollectCarinfo().getWaitDriverContactNumber()
                };
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