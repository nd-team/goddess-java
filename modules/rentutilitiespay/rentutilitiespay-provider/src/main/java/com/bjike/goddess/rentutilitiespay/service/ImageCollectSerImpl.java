package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.rentutilitiespay.bo.*;
import com.bjike.goddess.rentutilitiespay.dto.ImageCollectDTO;
import com.bjike.goddess.rentutilitiespay.dto.RentPayDTO;
import com.bjike.goddess.rentutilitiespay.dto.StayUtilitiesDTO;
import com.bjike.goddess.rentutilitiespay.entity.ImageCollect;
import com.bjike.goddess.rentutilitiespay.entity.RentPay;
import com.bjike.goddess.rentutilitiespay.entity.StayUtilities;
import com.bjike.goddess.rentutilitiespay.enums.StaffVerify;
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
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-23 16:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames ="rentutilitiespaySerCache")
@Service
public class ImageCollectSerImpl extends ServiceImpl<ImageCollect,ImageCollectDTO> implements ImageCollectSer {
    @Autowired
    private ImageCollectSer imageCollectSer;

    @Autowired
    private RentPaySer rentPaySer;

    @Autowired
    private StayUtilitiesSer stayUtilitiesSer;

    @Override
    public OptionBO firgureShowMonth(Integer year, Integer month) throws SerException {
        LocalDate[] startTime = null;
        if (year != null && month != null){
            LocalDate startDate = DateUtil.getStartDayOfMonth(year,month);
            LocalDate endDate = DateUtil.getEndDaYOfMonth(year,month);
            startTime = new LocalDate[]{startDate,endDate};
        }else {
            LocalDate startDate = DateUtil.getStartMonth();
            LocalDate endDate = DateUtil.getEndMonth();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);
    }

    @Override
    public OptionBO figrueShowYear(Integer year) throws SerException {
        LocalDate[] startTime = null;
        if (year != null){
            LocalDate startDate = DateUtil.parseDate(year+"-01-01");
            LocalDate endDate = DateUtil.parseDate(year+"-12-31");
            startTime = new LocalDate[]{startDate,endDate};
        }else {
            LocalDate startDate = DateUtil.getStartYear();
            LocalDate endDate = DateUtil.getEndYear();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);
    }

    @Override
    public OptionBO figureShowTotal(String day) throws SerException {
        LocalDate[] startTime = null;
        if (StringUtils.isNotBlank(day)){
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = DateUtil.parseDate(day);
            startTime = new LocalDate[]{startDate,endDate};
        }else {
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = LocalDate.now();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);
    }

    private OptionBO collect(LocalDate[] startTime) throws SerException {
        RentPayDTO rentPayDTO = new RentPayDTO();
        List<RentPay> rentPays1 = rentPaySer.findByCis(rentPayDTO);
        Set<String> departments = rentPays1.stream().map(p -> p.getProjectGroup()).collect(Collectors.toSet());
        List<ImageCollectBO> imageCollectBOS = new ArrayList<>();
        for (String department : departments) {
            DepartmentCollectBO departmentCollectBO = new DepartmentCollectBO();
            RentPayDTO rentPayDTO1 = new RentPayDTO();
            rentPayDTO1.getConditions().add(Restrict.eq("projectGroup", department));
            List<RentPay> rentPays2 = rentPaySer.findByCis(rentPayDTO1);
            Set<String> locations = rentPays2.stream().map(p -> p.getAddress()).collect(Collectors.toSet());
            for (String address : locations) {
                ImageCollectBO imageCollectBO = new ImageCollectBO();
                //房租缴付总金额
                Double rent = 0.0;
                //水费缴付总金额
                Double waterPayMoney = 0.0;
                //电费缴付总金额
                Double energyPayMoney = 0.0;
                //燃气费缴付总金额
                Double gasRechargeLines = 0.0;
                //员工应缴金额
                Double staffPayCollect = 0.0;
                RentPayDTO rentPayDTO2 = new RentPayDTO();
                rentPayDTO2.getConditions().add(Restrict.eq("projectGroup", department));
                rentPayDTO2.getConditions().add(Restrict.eq("address", address));
                rentPayDTO2.getConditions().add(Restrict.between("payDate", startTime));
                List<RentPay> rentPays3 = rentPaySer.findByCis(rentPayDTO2);
                if (rentPays3 != null && rentPays3.size() > 0) {
                    rent = rentPays3.stream().filter(p -> p.getRent() != null).mapToDouble(p -> p.getRent()).sum();
                    waterPayMoney = rentPays3.stream().filter(p -> p.getWaterPayMoney() != null).mapToDouble(p -> p.getWaterPayMoney()).sum();
                    energyPayMoney = rentPays3.stream().filter(p -> p.getEnergyPayMoney() != null).mapToDouble(p -> p.getEnergyPayMoney()).sum();
                    gasRechargeLines = rentPays3.stream().filter(p -> p.getGasRechargeLines() != null).mapToDouble(p -> p.getGasRechargeLines()).sum();
                }
                Double staffPayCollect1 = 0.0;
                StayUtilitiesDTO stayUtilitiesDTO = new StayUtilitiesDTO();
                stayUtilitiesDTO.getConditions().add(Restrict.eq("projectGroup",department));
                stayUtilitiesDTO.getConditions().add(Restrict.eq("address",address));
                List<StayUtilities> stayUtilities = stayUtilitiesSer.findByCis(stayUtilitiesDTO);
                if (stayUtilities !=null && stayUtilities.size() > 0){
                    staffPayCollect1 = stayUtilities.stream().filter(p -> p.getStaffPayCollect() != null).mapToDouble(p -> p.getStaffPayCollect()).sum();
                }
                Double staffPayCollect2 = 0.0;
                String[] fields = new String[]{"waterStaffPay","energyStaffPay","gasStaffPay","waterStaffPrepay","energyStaffPrepay","gasStaffPrepay","staffPayCollect"};
                StringBuilder sql = new StringBuilder("SELECT waterStaffPay,energyStaffPay,gasStaffPay,waterStaffPrepay,energyStaffPrepay ,gasStaffPrepay,staffPayCollect FROM rentutilitiespay_stayutilities ");
                sql.append("WHERE projectGroup='"+department+"' ");
                sql.append("and address='"+address+"' ");
                sql.append("and stayEndTime < '"+startTime[0]+"'");
                List<StayUtilities> stayUtilities1 = stayUtilitiesSer.findBySql(sql.toString(),StayUtilities.class,fields);
                if (stayUtilities1 != null && stayUtilities1.size() > 0){
                    staffPayCollect2 = stayUtilities1.stream().filter(p -> p.getStaffPayCollect() != null).mapToDouble(p -> p.getStaffPayCollect()).sum();
                }

                Double staffPayCollect3 = 0.0;

                StringBuilder sql2 = new StringBuilder("SELECT waterStaffPay,energyStaffPay,gasStaffPay,waterStaffPrepay,energyStaffPrepay ,gasStaffPrepay,staffPayCollect FROM rentutilitiespay_stayutilities ");
                sql2.append("WHERE projectGroup='"+department+"' ");
                sql2.append("and address='"+address+"' ");
                sql2.append("and stayStartTime > '"+startTime[1]+"'");
                List<StayUtilities> stayUtilities2 = stayUtilitiesSer.findBySql(sql.toString(),StayUtilities.class,fields);
                if (stayUtilities1 != null && stayUtilities1.size() > 0){
                    staffPayCollect3 = stayUtilities2.stream().filter(p -> p.getStaffPayCollect() != null).mapToDouble(p -> p.getStaffPayCollect()).sum();
                }
                staffPayCollect = staffPayCollect1 - staffPayCollect2 - staffPayCollect3;
                imageCollectBO.setStaffPayCollect(staffPayCollect);
                imageCollectBO.setDepartment(department);
                imageCollectBO.setRent(rent);
                imageCollectBO.setWaterPayMoney(waterPayMoney);
                imageCollectBO.setEnergyPayMoney(energyPayMoney);
                imageCollectBO.setGasRechargeLines(gasRechargeLines);
                imageCollectBOS.add(imageCollectBO);
            }
        }
        //标题
        TitleBO titleBO = new TitleBO();


        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list2 = new ArrayList<>();


        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_3 = new String[]{"房租缴付总金额","水费缴付总金额","电费缴付总金额","燃气费缴付总金额","待解除协议数"};
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (imageCollectBOS != null && imageCollectBOS.size() > 0){
            for (ImageCollectBO imageCollectBO : imageCollectBOS){
                text_list2.add(imageCollectBO.getDepartment());

                //柱状图数据
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(imageCollectBO.getDepartment());
                seriesBO.setType("bar");

                Double[] number = new Double[]{imageCollectBO.getRent(),imageCollectBO.getWaterPayMoney(),imageCollectBO.getEnergyPayMoney()
                        ,imageCollectBO.getGasRechargeLines(),imageCollectBO.getStaffPayCollect()
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
        return  optionBO;
    }


}
