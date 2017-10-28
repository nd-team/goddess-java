package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.carinfo.bo.ImagingBO;
import com.bjike.goddess.carinfo.bo.ImagingTopBO;
import com.bjike.goddess.carinfo.bo.ImagingXBO;
import com.bjike.goddess.carinfo.dto.DriverInfoDTO;
import com.bjike.goddess.carinfo.dto.DriverRecruitDTO;
import com.bjike.goddess.carinfo.entity.DriverInfo;
import com.bjike.goddess.carinfo.entity.DriverRecruit;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.carinfo.dto.ImagingDTO;
import com.bjike.goddess.carinfo.entity.Imaging;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.rentcar.api.DriverInfoAPI;
import com.bjike.goddess.rentcar.enums.AgreementStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* 图形化业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-12 02:30 ]
* @Description:	[ 图形化业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="carinfoSerCache")
@Service
public class ImagingSerImpl extends ServiceImpl<Imaging, ImagingDTO> implements ImagingSer {

    @Autowired
    private DriverInfoSer driverInfoSer;

    @Autowired
    private DriverRecruitSer driverRecruitSer;

    @Autowired
    private DriverInfoAPI driverInfoAPI;
    @Override
    public ImagingTopBO dayCollect(String day) throws SerException {
        LocalDate startDay = null;
        if (day != null){
            startDay = DateUtil.parseDate(day);
        }else{
            startDay = LocalDate.now();
        }
        List<DriverInfo> driverInfos = driverInfoSer.findAll();
        Set<String> departments = driverInfos.stream().map(p -> p.getDepartment()).collect(Collectors.toSet());
        ImagingTopBO imagingTopBO = new ImagingTopBO();
        ImagingXBO imagingXBO = new ImagingXBO();
        List<ImagingXBO> imagingXBOS = new ArrayList<>();
        List<ImagingBO> imagingBOS = new ArrayList<>();
        for (String department : departments){
            ImagingBO imagingBO = new ImagingBO();
            Integer businessDemand = 0;
            Integer projectDemand = 0;
            Integer availalbeDriver = 0;
            Integer needDriver = 0;
            Integer waitSignDealNumber = 0;
            Integer driverInformationCollect = 0;
            Integer driverContactNumber = 0;
            Integer waitDriverContactNumber = 0;
            //todo　商务需求
            //todo 项目需求

            //todo 还需司机数
            com.bjike.goddess.rentcar.dto.DriverInfoDTO driverInfoDTO = new com.bjike.goddess.rentcar.dto.DriverInfoDTO();
            driverInfoDTO.getConditions().add(Restrict.eq("department",department));
            driverInfoDTO.getConditions().add(Restrict.eq("agreementStatus",AgreementStatus.INLEASE));
            List<com.bjike.goddess.rentcar.bo.DriverInfoBO> driverInfoBOS = driverInfoAPI.pageList(driverInfoDTO);
            availalbeDriver = driverInfoBOS.size();

            needDriver = businessDemand + projectDemand + availalbeDriver;

            DriverRecruitDTO driverRecruitDTO = new DriverRecruitDTO();
            driverRecruitDTO.getConditions().add(Restrict.eq("department",department));
            driverRecruitDTO.getConditions().add(Restrict.eq("enSureAgreement", true));
            List<DriverRecruit> driverRecruits = driverRecruitSer.findByCis(driverRecruitDTO);
            waitSignDealNumber = driverRecruits.size();

            DriverRecruitDTO driverRecruitDTO1 = new DriverRecruitDTO();
            driverRecruitDTO1.getConditions().add(Restrict.eq("department",department));
            driverRecruitDTO1.getConditions().add(Restrict.eq("informatioCollectionTime",startDay));
            List<DriverRecruit> driverRecruits1 = driverRecruitSer.findByCis(driverRecruitDTO1);
            driverInformationCollect = driverRecruits1.size();

            driverRecruitDTO1.getConditions().add(Restrict.eq("contact",true));

            List<DriverRecruit> driverRecruits2 = driverRecruitSer.findByCis(driverRecruitDTO1);
            driverContactNumber = driverRecruits2.size();

            DriverRecruitDTO driverRecruitDTO2 = new DriverRecruitDTO();
            driverRecruitDTO2.getConditions().add(Restrict.eq("department",department));
            driverRecruitDTO2.getConditions().add(Restrict.eq("informatioCollectionTime",startDay));
            driverRecruitDTO2.getConditions().add(Restrict.eq("contact",false));
            List<DriverRecruit> driverRecruits3 = driverRecruitSer.findByCis(driverRecruitDTO2);

            waitDriverContactNumber = driverRecruits3.size();

            imagingBO.setAvailalbeDriver(availalbeDriver);
            imagingBO.setBusinessDemand(businessDemand);
            imagingBO.setProjectDemand(projectDemand);
            imagingBO.setNeedDriver(needDriver);
            imagingBO.setWaitDriverContactNumber(waitDriverContactNumber);
            imagingBO.setWaitSignDealNumber(waitSignDealNumber);
            imagingBO.setDriverContactNumber(driverContactNumber);
            imagingBO.setDriverInformationCollect(driverInformationCollect);

            imagingBOS.add(imagingBO);
            imagingXBO.setName(department);
        }
        imagingXBO.setImagingList(imagingBOS);
        imagingXBOS.add(imagingXBO);
        imagingTopBO.setDepartmentList(new ArrayList<>(departments));
        imagingTopBO.setImagingXList(imagingXBOS);
        return imagingTopBO;
    }

    @Override
    public ImagingTopBO weekCollect(Integer year, Integer month, Integer week) throws SerException {
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
    public ImagingTopBO monthCollect(Integer year, Integer month) throws SerException {
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
    public ImagingTopBO allCollect(Integer year) throws SerException {
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


    private ImagingTopBO collect(LocalDate[] time) throws SerException{
        List<DriverInfo> driverInfos = driverInfoSer.findAll();
        Set<String> departments = driverInfos.stream().map(p -> p.getDepartment()).collect(Collectors.toSet());
        ImagingTopBO imagingTopBO = new ImagingTopBO();
        ImagingXBO imagingXBO = new ImagingXBO();
        List<ImagingXBO> imagingXBOS = new ArrayList<>();
        List<ImagingBO> imagingBOS = new ArrayList<>();
        for (String department : departments){
            ImagingBO imagingBO = new ImagingBO();
            Integer businessDemand = 0;
            Integer projectDemand = 0;
            Integer availalbeDriver = 0;
            Integer needDriver = 0;
            Integer waitSignDealNumber = 0;
            Integer driverInformationCollect = 0;
            Integer driverContactNumber = 0;
            Integer waitDriverContactNumber = 0;
            //todo　商务需求
            //todo 项目需求

            //todo 还需司机数
            com.bjike.goddess.rentcar.dto.DriverInfoDTO driverInfoDTO = new com.bjike.goddess.rentcar.dto.DriverInfoDTO();
            driverInfoDTO.getConditions().add(Restrict.eq("department",department));
            driverInfoDTO.getConditions().add(Restrict.eq("agreementStatus",AgreementStatus.INLEASE));
            List<com.bjike.goddess.rentcar.bo.DriverInfoBO> driverInfoBOS = driverInfoAPI.pageList(driverInfoDTO);
            availalbeDriver = driverInfoBOS.size();

            needDriver = businessDemand + projectDemand + availalbeDriver;

            DriverRecruitDTO driverRecruitDTO = new DriverRecruitDTO();
            driverRecruitDTO.getConditions().add(Restrict.eq("department",department));
            driverRecruitDTO.getConditions().add(Restrict.eq("enSureAgreement", true));
            List<DriverRecruit> driverRecruits = driverRecruitSer.findByCis(driverRecruitDTO);
            waitSignDealNumber = driverRecruits.size();

            DriverRecruitDTO driverRecruitDTO1 = new DriverRecruitDTO();
            driverRecruitDTO1.getConditions().add(Restrict.eq("department",department));
            driverRecruitDTO1.getConditions().add(Restrict.between("informatioCollectionTime",time));
            List<DriverRecruit> driverRecruits1 = driverRecruitSer.findByCis(driverRecruitDTO1);
            driverInformationCollect = driverRecruits1.size();

            driverRecruitDTO1.getConditions().add(Restrict.eq("contact",true));

            List<DriverRecruit> driverRecruits2 = driverRecruitSer.findByCis(driverRecruitDTO1);
            driverContactNumber = driverRecruits2.size();

            DriverRecruitDTO driverRecruitDTO2 = new DriverRecruitDTO();
            driverRecruitDTO2.getConditions().add(Restrict.eq("department",department));
            driverRecruitDTO2.getConditions().add(Restrict.between("informatioCollectionTime",time));
            driverRecruitDTO2.getConditions().add(Restrict.eq("contact",false));
            List<DriverRecruit> driverRecruits3 = driverRecruitSer.findByCis(driverRecruitDTO2);

            waitDriverContactNumber = driverRecruits3.size();

            imagingBO.setAvailalbeDriver(availalbeDriver);
            imagingBO.setBusinessDemand(businessDemand);
            imagingBO.setProjectDemand(projectDemand);
            imagingBO.setNeedDriver(needDriver);
            imagingBO.setWaitDriverContactNumber(waitDriverContactNumber);
            imagingBO.setWaitSignDealNumber(waitSignDealNumber);
            imagingBO.setDriverContactNumber(driverContactNumber);
            imagingBO.setDriverInformationCollect(driverInformationCollect);

            imagingBOS.add(imagingBO);
            imagingXBO.setName(department);
        }
        imagingXBO.setImagingList(imagingBOS);
        imagingXBOS.add(imagingXBO);
        imagingTopBO.setDepartmentList(new ArrayList<>(departments));
        imagingTopBO.setImagingXList(imagingXBOS);
        return imagingTopBO;
    }
}