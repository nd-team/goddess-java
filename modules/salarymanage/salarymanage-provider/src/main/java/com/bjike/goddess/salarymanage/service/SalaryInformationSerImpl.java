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
import org.springframework.beans.BeanUtils;
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
        //todo 还没有添加权限 规划人能查看所有的 还能根据名称来查找,默认是查找所有的
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

    //校验字段是否存在
    private void isExist(SalaryInformationTO to, Integer row) throws SerException {
        if(StringUtils.isBlank( to.getPayStarTime() )){
            throw new SerException("第" + row + "行的计薪周期开始时间不能为空" );
        }
        if(StringUtils.isBlank( to.getPayEndTime() )){
            throw new SerException("第" + row + "行的计薪周期结束时间不能为空" );
        }
        if(null== to.getArea()){
            throw new SerException("第" + row + "行的地区不能为空" );
        }
        if ( null== to.getEmployeeId()) {
            throw new SerException("第" + row + "行的员工编号不能为空");
        }
        if(null == to.getEmployeeName()){
            throw new SerException("第" + row + "行的姓名不能为空");
        }
        if(null== to.getSystem()){
            throw new SerException("第" + row + "行的体系不能为空" );
        }
        if ( null== to.getSection()) {
            throw new SerException("第" + row + "行的部门项目组不能为空");
        }
        if(null == to.getStation()){
            throw new SerException("第" + row + "行的岗位不能为空");
        }
        if(null== to.getStationLevel()){
            throw new SerException("第" + row + "行的岗位层级不能为空" );
        }
        if ( null== to.getManageLevel()) {
            throw new SerException("第" + row + "行的管理层级不能为空");
        }
        if(null == to.getSkill()){
            throw new SerException("第" + row + "行的技能项不能为空");
        }
        if(null== to.getProSkills()){
            throw new SerException("第" + row + "行的技能专业不能为空" );
        }
        if ( null== to.getSkillLevel()) {
            throw new SerException("第" + row + "行的技能级别不能为空");
        }
        if(null == to.getHiredate()){
            throw new SerException("第" + row + "行的入职时间不能为空");
        }

        if(null == to.getWorkingTime()){
            throw new SerException("第" + row + "行的在职时间不能为空");
        }
        if(null== to.getBasicSalary()){
            throw new SerException("第" + row + "行的基本工资不能为空" );
        }
        if ( null== to.getPostSalary()) {
            throw new SerException("第" + row + "行的岗位工资不能为空");
        }
        if(null == to.getSkillPay()){
            throw new SerException("第" + row + "行的技能工资不能为空");
        }
        if(null== to.getManagePay()){
            throw new SerException("第" + row + "行的管理工资不能为空" );
        }
        if ( null== to.getSkillSubsidies()) {
            throw new SerException("第" + row + "行的技能职衔补助不能为空");
        }
        if(null == to.getManageSubsidies()){
            throw new SerException("第" + row + "行的管理等级职衔补助不能为空");
        }
        if(null== to.getSeniorityLevSubsidies()){
            throw new SerException("第" + row + "行的工龄职衔补助不能为空" );
        }
        if ( null== to.getAllSubsidies()) {
            throw new SerException("第" + row + "行的职衔补助总额不能为空");
        }
        if(null == to.getProjectBenefits()){
            throw new SerException("第" + row + "行的项目津贴不能为空");
        }

        if ( null== to.getWage()) {
            throw new SerException("第" + row + "行的定薪不能为空");
        }
        if(null == to.getSalary()){
            throw new SerException("第" + row + "行的岗位不能为空");
        }
        if(null== to.getStationLevel()){
            throw new SerException("第" + row + "行的工资不能为空" );
        }
        if ( null== to.getComputerSubsidies()) {
            throw new SerException("第" + row + "行的电脑补助不能为空");
        }
        if(null == to.getAccommodationSubsidies()){
            throw new SerException("第" + row + "行的住宿补助不能为空");
        }
        if(null== to.getSenioritySubsidies()){
            throw new SerException("第" + row + "行的工龄补助不能为空" );
        }
        if ( null== to.getHyperthermiaSubsidies()) {
            throw new SerException("第" + row + "行的高温补助不能为空");
        }
        if(null == to.getAllSalary()){
            throw new SerException("第" + row + "行的工资总额不能为空");
        }

        if(null == to.getJinpoCost()){
            throw new SerException("第" + row + "行的扣社保不能为空");
        }
        if(null== to.getJinpoSubsidies()){
            throw new SerException("第" + row + "行的社保补助不能为空" );
        }
        if ( null== to.getUtilities()) {
            throw new SerException("第" + row + "行的水电费不能为空");
        }
        if(null == to.getPersonTax()){
            throw new SerException("第" + row + "行的个税不能为空");
        }
        if(null== to.getAllRewardScore()){
            throw new SerException("第" + row + "行的奖励处罚得分汇总不能为空" );
        }
        if ( null== to.getAllRewardCost()) {
            throw new SerException("第" + row + "行的奖励处罚费用汇总不能为空");
        }
        if(null == to.getAttendanceDay()){
            throw new SerException("第" + row + "行的出勤天数不能为空");
        }
        if(null == to.getVacateDay()){
            throw new SerException("第" + row + "行的请假天数不能为空");
        }
        if(null== to.getAbsenteeismDay()){
            throw new SerException("第" + row + "行的旷工天数不能为空" );
        }
        if ( null== to.getUnfinishedTime()) {
            throw new SerException("第" + row + "行的未完成任务工时不能为空");
        }
        if(null == to.getNormalOvertimeDay()){
            throw new SerException("第" + row + "行的正常工作日加班天数不能为空");
        }
        if(null== to.getLegalRestDay()){
            throw new SerException("第" + row + "行的法定节假日实际休息天数不能为空" );
        }
        if ( null== to.getLegalOvertimeDay()) {
            throw new SerException("第" + row + "行的法定节假日加班天数不能为空");
        }
        if(null == to.getNormalRestDay()){
            throw new SerException("第" + row + "行正常休息天数实际休息天数不能为空");
        }

        if(null == to.getNormalOvertimeDay()){
            throw new SerException("第" + row + "行的正常休息天数加班天数不能为空");
        }
        if(null == to.getSurplusOvertimeDay()){
            throw new SerException("第" + row + "行的剩余加班天数不能为空");
        }
        if(null== to.getOffsetOvertime()){
            throw new SerException("第" + row + "行的加班抵事假和其他假的天数不能为空" );
        }
        if ( null== to.getEffectiveOvertime()) {
            throw new SerException("第" + row + "行的抵扣事假和其他假后剩余加班天数不能为空");
        }
        if(null == to.getWeekDays()){
            throw new SerException("第" + row + "行的月工作日不能为空");
        }
        if(null== to.getPaidDay()){
            throw new SerException("第" + row + "行的可享受带薪天数不能为空" );
        }

    }

    @Override
    public void leadExcel(List<SalaryInformationTO> toList) throws SerException {
        //todo 要加上导出权限
        UserBO userBO = userAPI.currentUser();
        for(int i = 1; i<= toList.size();i++){
            isExist(toList.get(i-1),i);
        }
        List<SalaryInformation> list = BeanTransform.copyProperties(toList,SalaryInformation.class,true);
        list.stream().forEach(str->{
            str.setModifyTime(LocalDateTime.now());
            str.setCreateTime(LocalDateTime.now());
        });
        super.save(list);

    }

    @Override
    public byte[] exportExcel(ExportSalaryInformationTO to) throws SerException {
        //todo 暂时不知道是否一定要输入导出条件
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
        excel.setArea("地区");
        excel.setEmployeeId("员工编号");
        excel.setEmployeeName("姓名");
        excel.setSystem("体系");
        excel.setSection("部门/项目组");
        excel.setStation("岗位");
        excel.setStationLevel("岗位层级");
        excel.setManageLevel("管理层级");
        salaryBasicSetExcels.add(excel);

        Excel exce = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(salaryBasicSetExcels,exce);
        return bytes;
        return new byte[0];
    }
}