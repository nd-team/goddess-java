package com.bjike.goddess.managefee.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managefee.bo.ManageFeeBO;
import com.bjike.goddess.managefee.dto.ManageFeeDTO;
import com.bjike.goddess.managefee.entity.ManageFee;
import com.bjike.goddess.managefee.to.ManageFeeTO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理费业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managefeeSerCache")
@Service
public class ManageFeeSerImpl extends ServiceImpl<ManageFee, ManageFeeDTO> implements ManageFeeSer {

    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;

    @Override
    public Long countManageFee(ManageFeeDTO manageFeeDTO) throws SerException {
        Long count = super.count(manageFeeDTO);
        return count;
    }

    @Override
    public ManageFeeBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ManageFee manageFee = super.findById(id);
        return BeanTransform.copyProperties(manageFee, ManageFeeBO.class);
    }

    @Override
    public List<ManageFeeBO> listManageFee(ManageFeeDTO manageFeeDTO) throws SerException {
        manageFeeDTO.getSorts().add("createTime=desc");
        List<ManageFee> list = super.findByCis(manageFeeDTO, true);

        return BeanTransform.copyProperties(list, ManageFeeBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ManageFeeBO addManageFee(ManageFeeTO manageFeeTO) throws SerException {
        if (manageFeeTO.getTargetFee() == null) {
            throw new SerException("目标管理费不能为空");
        }
        int year = Integer.parseInt(manageFeeTO.getYear().trim());
        int month = Integer.parseInt(manageFeeTO.getMonth().trim());
        LocalDate startTime = LocalDate.of(year, month, 1);
        LocalDate endTime = startTime.with(TemporalAdjusters.lastDayOfMonth());
        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
        voucherGenerateDTO.setArea(manageFeeTO.getArea());
        voucherGenerateDTO.setProjectName(manageFeeTO.getProject());
        voucherGenerateDTO.setProjectGroup(manageFeeTO.getProjectGroup());
        voucherGenerateDTO.setStartTime(startTime + "");
        voucherGenerateDTO.setEndTime(endTime + "");
        List<VoucherGenerateBO> voucherList = voucherGenerateAPI.listStatistic(voucherGenerateDTO, "manageFee");
        //记账凭证里面的费用

        Double money = 0d;
        if( voucherList!= null && voucherList.size()>0){
            money = voucherList.stream().filter(str->str.getBorrowMoney()!=null).mapToDouble(VoucherGenerateBO::getBorrowMoney).sum();
        }

        ManageFee manageFee = BeanTransform.copyProperties(manageFeeTO, ManageFee.class, true);
        if (money == null && manageFeeTO.getActualFee() == null) {
            money = 0d;
        }
        manageFee.setActualFee(money);
        manageFee.setRate(manageFee.getActualFee() / manageFee.getTargetFee());
        manageFee.setBalance(manageFee.getActualFee() - manageFee.getTargetFee());
        manageFee.setCreateTime(LocalDateTime.now());
        super.save(manageFee);
        return BeanTransform.copyProperties(manageFee, ManageFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ManageFeeBO editManageFee(ManageFeeTO manageFeeTO) throws SerException {
        if (manageFeeTO.getTargetFee() == null) {
            throw new SerException("目标管理费不能为空");
        }
        if (manageFeeTO.getActualFee() == null) {
            throw new SerException("实际管理费不能为空");
        }

        ManageFee manageFee = BeanTransform.copyProperties(manageFeeTO, ManageFee.class, true);
        ManageFee cusLevel = super.findById(manageFeeTO.getId());

        BeanUtils.copyProperties(manageFee, cusLevel, "id", "createTime");
        cusLevel.setRate(cusLevel.getActualFee() / cusLevel.getTargetFee());
        cusLevel.setBalance(cusLevel.getActualFee() - cusLevel.getTargetFee());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, ManageFeeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteManageFee(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<ManageFeeBO> collectArea(ManageFeeDTO manageFeeDTO) throws SerException {
        String startTime = manageFeeDTO.getStartTime();
        String endTime = manageFeeDTO.getEndTime();
        LocalDate start = LocalDate.parse(startTime);
        LocalDate end = LocalDate.parse(endTime);
        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"area", "year", "targetFee", "actualFee", "rate", "balance"};
        String sql = "";
        if (StringUtils.isBlank(manageFeeDTO.getArea())) {
            sql = "select area , 1 as year , sum(targetFee) as targetFee , sum(actualFee) as actualFee ," +
                    "  (sum(actualFee)/sum(targetFee)) as rate , (sum(actualFee)-sum(targetFee)) as balance from managefee_managefee where 1= 1";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and year between '" + start.getYear() + "' and '" + end.getYear() + "' and month between '" + start.getMonthValue() + "' and '" + end.getMonthValue() + "' ";
            }
            sql = sql + " group by area  order by area desc ";
        } else {
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"area", "year", "month", "area", "project", "type", "targetFee", "actualFee", "rate", "balance"};
            sql = "select area , year , month ,projectGroup , project,type, targetFee , actualFee ," +
                    "  (actualFee/targetFee) as rate , (actualFee-targetFee) as balance from managefee_managefee where 1=1 ";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and year between '" + start.getYear() + "' and '" + end.getYear() + "' and month between '" + start.getMonthValue() + "' and '" + end.getMonthValue() + "' ";
            }
            sql = sql + " order by area desc ";
        }

        List<ManageFeeBO> list = super.findBySql(sql, ManageFeeBO.class, field);

        return list;
    }

    @Override
    public List<ManageFeeBO> collectGroup(ManageFeeDTO manageFeeDTO) throws SerException {
        String startTime = manageFeeDTO.getStartTime();
        String endTime = manageFeeDTO.getEndTime();
        LocalDate start = LocalDate.parse(startTime);
        LocalDate end = LocalDate.parse(endTime);
        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"projectGroup", "year", "targetFee", "actualFee", "rate", "balance"};
        String sql = "";
        if (StringUtils.isBlank(manageFeeDTO.getArea())) {
            sql = "select projectGroup , 1 as year , sum(targetFee) as targetFee , sum(actualFee) as actualFee ," +
                    "  (sum(actualFee)/sum(targetFee)) as rate , (sum(actualFee)-sum(targetFee)) as balance from managefee_managefee where 1= 1";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and year between '" + start.getYear() + "' and '" + end.getYear() + "' and month between '" + start.getMonthValue() + "' and '" + end.getMonthValue() + "' ";
            }
            sql = sql + " group by projectGroup  order by projectGroup desc ";
        } else {
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"projectGroup", "year", "month", "area", "project", "type", "targetFee", "actualFee", "rate", "balance"};
            sql = "select projectGroup , year , month ,area , project,type, targetFee , actualFee ," +
                    "  (actualFee/targetFee) as rate , (actualFee-targetFee) as balance from managefee_managefee where 1=1 ";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and year between '" + start.getYear() + "' and '" + end.getYear() + "' and month between '" + start.getMonthValue() + "' and '" + end.getMonthValue() + "' ";
            }
            sql = sql + " order by projectGroup desc ";
        }

        List<ManageFeeBO> list = super.findBySql(sql, ManageFeeBO.class, field);

        return list;
    }

    @Override
    public List<ManageFeeBO> collectProject(ManageFeeDTO manageFeeDTO) throws SerException {
        String startTime = manageFeeDTO.getStartTime();
        String endTime = manageFeeDTO.getEndTime();
        LocalDate start = LocalDate.parse(startTime);
        LocalDate end = LocalDate.parse(endTime);
        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"project", "year", "targetFee", "actualFee", "rate", "balance"};
        String sql = "";
        if (StringUtils.isBlank(manageFeeDTO.getArea())) {
            sql = "select project , 1 as year , sum(targetFee) as targetFee , sum(actualFee) as actualFee ," +
                    "  (sum(actualFee)/sum(targetFee)) as rate , (sum(actualFee)-sum(targetFee)) as balance from managefee_managefee where 1= 1";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and year between '" + start.getYear() + "' and '" + end.getYear() + "' and month between '" + start.getMonthValue() + "' and '" + end.getMonthValue() + "' ";
            }
            sql = sql + " group by project  order by project desc ";
        } else {
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"project", "year", "month", "area", "projectGroup", "type", "targetFee", "actualFee", "rate", "balance"};
            sql = "select project , year , month ,area , projectGroup,type, targetFee , actualFee ," +
                    "  (actualFee/targetFee) as rate , (actualFee-targetFee) as balance from managefee_managefee where 1=1 ";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and year between '" + start.getYear() + "' and '" + end.getYear() + "' and month between '" + start.getMonthValue() + "' and '" + end.getMonthValue() + "' ";
            }
            sql = sql + " order by project desc ";
        }

        List<ManageFeeBO> list = super.findBySql(sql, ManageFeeBO.class, field);

        return list;
    }


    @Override
    public List<ManageFeeBO> collectType(ManageFeeDTO manageFeeDTO) throws SerException {
        String startTime = manageFeeDTO.getStartTime();
        String endTime = manageFeeDTO.getEndTime();
        LocalDate start = LocalDate.parse(startTime);
        LocalDate end = LocalDate.parse(endTime);
        //如果没有选地区，汇总表头：（地区/日期/目标管理费/实际管理费/比例/差额）
        String[] field = new String[]{"type", "year", "targetFee", "actualFee", "rate", "balance"};
        String sql = "";
        if (StringUtils.isBlank(manageFeeDTO.getArea())) {
            sql = "select type , 1 as year , sum(targetFee) as targetFee , sum(actualFee) as actualFee ," +
                    "  (sum(actualFee)/sum(targetFee)) as rate , (sum(actualFee)-sum(targetFee)) as balance from managefee_managefee where 1= 1";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and year between '" + start.getYear() + "' and '" + end.getYear() + "' and month between '" + start.getMonthValue() + "' and '" + end.getMonthValue() + "' ";
            }
            sql = sql + " group by type  order by type desc ";
        } else {
            //如果有选地区，汇总表头：(地区/年份/月份/项目组/项目名称/类别/目标管理费/实际管理费/比例/差额)
            field = new String[]{"type", "year", "month", "area", "projectGroup", "type", "targetFee", "actualFee", "rate", "balance"};
            sql = "select type , year , month ,area , projectGroup,project, targetFee , actualFee ," +
                    "  (actualFee/targetFee) as rate , (actualFee-targetFee) as balance from managefee_managefee where 1=1 ";
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql = sql + " and year between '" + start.getYear() + "' and '" + end.getYear() + "' and month between '" + start.getMonthValue() + "' and '" + end.getMonthValue() + "' ";
            }
            sql = sql + " order by type desc ";
        }

        List<ManageFeeBO> list = super.findBySql(sql, ManageFeeBO.class, field);

        return list;
    }

    @Override
    public List<String> yearList() throws SerException {
        //获取所有年
        List<String> yearList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        int start = 1900;
        int end = now.getYear();
        String year = "";
        while (start <= end) {
            year = start + "";
            start = start + 1;
            yearList.add(year);
        }
        return yearList;
    }

    @Override
    public List<String> areaList() throws SerException {
        String[] field = new String[]{"area"};
        String sql = "select area , 1 from managefee_managefee group by area ";
        List<ManageFee> manageFeeList = super.findBySql(sql, ManageFee.class, field);
        List<String> list = manageFeeList.stream().map(ManageFee::getArea).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> groupList() throws SerException {
        String[] field = new String[]{"projectGroup"};
        String sql = "select projectGroup , 1 from managefee_managefee group by projectGroup ";
        List<ManageFee> manageFeeList = super.findBySql(sql, ManageFee.class, field);
        List<String> list = manageFeeList.stream().map(ManageFee::getProjectGroup).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> projectList() throws SerException {
        String[] field = new String[]{"project"};
        String sql = "select project , 1 from managefee_managefee group by project ";
        List<ManageFee> manageFeeList = super.findBySql(sql, ManageFee.class, field);
        List<String> list = manageFeeList.stream().map(ManageFee::getProject).collect(Collectors.toList());
        return list;
    }
}