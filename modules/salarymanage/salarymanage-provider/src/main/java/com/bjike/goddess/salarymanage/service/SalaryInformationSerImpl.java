package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.entity.SalaryInformation;
import com.bjike.goddess.salarymanage.excel.SalaryInformationSetExcel;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* 薪资管理业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 01:45 ]
* @Description:	[ 薪资管理业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="salarymanageSerCache")
@Service
public class SalaryInformationSerImpl extends ServiceImpl<SalaryInformation, SalaryInformationDTO> implements SalaryInformationSer {
    @Autowired
    private UserAPI userAPI;
    @Override
    public List<SalaryInformationBO> pageList(SalaryInformationDTO dto) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        if(dto.getPayStarTime() != null && dto.getPayEndTime() != null){
            LocalDate[] localDates = new LocalDate[]{dto.getPayStarTime(),dto.getPayEndTime()};
            dto.getConditions().add(Restrict.between("payStarTime",localDates));
            dto.getConditions().add(Restrict.between("payEndTime",localDates));
        }
        if(dto.getPayStarTime() != null && dto.getPayEndTime() == null){
            LocalDate endTime = LocalDate.now();
            LocalDate[] localDates = new LocalDate[]{dto.getPayStarTime(),endTime};
            dto.getConditions().add(Restrict.between("payStarTime",localDates));
            dto.getConditions().add(Restrict.between("payEndTime",localDates));
        }
        if(dto.getPayStarTime() == null && dto.getPayEndTime() != null){
            dto.getConditions().add(Restrict.lt_eq("payEndTime",dto.getPayEndTime()));
        }
        dto.getConditions().add(Restrict.eq("employeeId",userBO.getEmployeeNumber()));
        List<SalaryInformation> list = super.findByCis(dto);
        List<SalaryInformationBO> salaryInformationBOS = BeanTransform.copyProperties(list,SalaryInformationBO.class);
        return salaryInformationBOS;
    }

    @Override
    public SalaryInformationBO addSalaryInformation(SalaryInformationTO to) throws SerException {
        SalaryInformation salaryInformation = BeanTransform.copyProperties(to,SalaryInformation.class);
        salaryInformation.setCreateTime(LocalDateTime.now());
        super.save(salaryInformation);
        SalaryInformationBO salaryInformationBO = BeanTransform.copyProperties(salaryInformation,SalaryInformationBO.class);
        return salaryInformationBO;
    }

    @Override
    public SalaryInformationBO editSalaryInformation(SalaryInformationTO to) throws SerException {
        SalaryInformation temp = super.findById(to.getId());
        try {
            DateUtil.parseDate(to.getPayStarTime());
            DateUtil.parseDate(to.getPayEndTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式不对");
        }
        SalaryInformation salaryInformation = BeanTransform.copyProperties(to,SalaryInformation.class);
        BeanUtils.copyProperties(salaryInformation,temp,"id","createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        SalaryInformationBO salaryInformationBO = BeanTransform.copyProperties(temp,SalaryInformationBO.class);
        return salaryInformationBO;
    }

    @Override
    public void deleteSalaryInformation(String id) throws SerException {
        super.remove(id);
    }


    @Override
    public void leadExcel(List<SalaryInformationTO> toList) throws SerException {
        //todo 要加上导出权限
        UserBO userBO = userAPI.currentUser();
        List<SalaryInformation> list = BeanTransform.copyProperties(toList,SalaryInformation.class,true);
        list.stream().forEach(str->{
            str.setModifyTime(LocalDateTime.now());
            str.setCreateTime(LocalDateTime.now());
        });
        super.save(list);

    }

    @Override
    public byte[] exportExcel(ExportSalaryInformationTO to) throws SerException {
        SalaryInformationDTO dto = new SalaryInformationDTO();
        //根据计薪开始时间和计薪结束时间来导出excel
        if(StringUtils.isNotBlank(to.getPayStarTime()) && StringUtils.isNotBlank(to.getPayEndTime())){
            LocalDate[] localDates = new LocalDate[]{dto.getPayStarTime(),dto.getPayEndTime()};
            dto.getConditions().add(Restrict.between("payStarTime",localDates));
            dto.getConditions().add(Restrict.between("payEndTime",localDates));
        }

        List<SalaryInformation> list = super.findByCis(dto);
        List<SalaryInformationSetExcel> toList = new ArrayList<SalaryInformationSetExcel>();
        for(SalaryInformation model : list) {
            SalaryInformationSetExcel excel = new SalaryInformationSetExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList,excel);
        return bytes;
    }

    @Override
    public List<String> findTime() throws SerException {
        SalaryInformationDTO dto = new SalaryInformationDTO();
        List<SalaryInformationBO> list = this.pageList(dto);
        List<String> timeList = new ArrayList<>();
        for(SalaryInformationBO salaryInformationBO : list){
            timeList.add(salaryInformationBO.getPayStarTime());
            timeList.add(salaryInformationBO.getPayEndTime());
        }
        return timeList;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<SalaryInformationSetExcel> salaryInformationSetExcels = new ArrayList<>();

        SalaryInformationSetExcel excel = new SalaryInformationSetExcel();

        excel.setPayStarTime("计薪周期开始时间");
        excel.setPayEndTime("计薪周期结束时间");
        excel.setArea("地区");
        excel.setEmployeeId("员工编号");
        excel.setEmployeeName("姓名");
        excel.setSystem("体系");
        excel.setSection("部门/项目组");
        excel.setStation("岗位");
        excel.setStationLevel("岗位层级");
        excel.setManageLevel("管理层级");
        excel.setSkill("技能项");
        excel.setPayEndTime("技能专业");
        excel.setSkillLevel("技能级别");
        excel.setHiredate("入职时间");
        excel.setPositiveTime("转正时间");
        excel.setWorkingTime("在职时间");
        excel.setBasicSalary(10d);
        excel.setPostSalary(10d);
        excel.setManagePay(10d);
        excel.setSkillSubsidies(10d);
        excel.setManageSubsidies(10d);
        excel.setSeniorityLevSubsidies(10d);
        excel.setAllSubsidies(10d);
        excel.setProjectBenefits(10d);
        excel.setWage(10d);
        excel.setSalary(10d);
        excel.setComputerSubsidies(10d);
        excel.setAccommodationSubsidies(10d);
        excel.setSenioritySubsidies(10d);
        excel.setHyperthermiaSubsidies(10d);
        excel.setAllSalary(10d);
        excel.setJinpoCost(10d);
        excel.setJinpoSubsidies(10d);
        excel.setUtilities(10d);
        excel.setPersonTax(10d);
        excel.setAllRewardScore(10d);
        excel.setAllRewardCost(10d);
        excel.setAttendanceDay(10d);
        excel.setVacateDay(10d);
        excel.setAbsenteeismDay(10d);
        excel.setUnfinishedTime(10d);
        excel.setNormalOvertimeDay(10d);
        excel.setLegalRestDay(10d);
        excel.setLegalOvertimeDay(10d);
        excel.setNormalRestDay(10d);
        excel.setNormalOvertimeDay(10d);
        excel.setSurplusOvertimeDay(10d);
        excel.setOffsetOvertime(10d);
        excel.setEffectiveOvertime(10d);
        excel.setWeekDays(10d);
        excel.setPaidDay(10d);
        excel.setRemark("备注");
        salaryInformationSetExcels.add(excel);

        Excel exce = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(salaryInformationSetExcels,exce);
        return bytes;
    }
}