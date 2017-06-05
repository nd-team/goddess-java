package com.bjike.goddess.salaryconfirm.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.salaryconfirm.bo.AnalyzeBO;
import com.bjike.goddess.salaryconfirm.bo.InvoiceSubmitBO;
import com.bjike.goddess.salaryconfirm.bo.SalaryconfirmBO;
import com.bjike.goddess.salaryconfirm.dto.SalaryconfirmDTO;
import com.bjike.goddess.salaryconfirm.entity.Salaryconfirm;
import com.bjike.goddess.salaryconfirm.enums.FindType;
import com.bjike.goddess.salaryconfirm.enums.Ratepaying;
import com.bjike.goddess.salaryconfirm.excel.SalaryconfirmExcel;
import com.bjike.goddess.salaryconfirm.to.ConditionTO;
import com.bjike.goddess.salaryconfirm.to.SalaryconfirmTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 薪资核算确认业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 薪资核算确认业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "salaryconfirmSerCache")
@Service
public class SalaryconfirmSerImpl extends ServiceImpl<Salaryconfirm, SalaryconfirmDTO> implements SalaryconfirmSer {

    @Autowired
    private InvoiceSubmitSer invoiceSubmitSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SalaryconfirmBO insertModel(SalaryconfirmTO to) throws SerException {
        Salaryconfirm model = BeanTransform.copyProperties(to, Salaryconfirm.class, true);

        //工资总额 = 员工工资 + **补助
        Double totalSalary = model.getSalary() + model.getCpSubsidy() + model.getDormitorySubsidy()
                + model.getYearSubsidy() + model.getHotSubsidy()
                + model.getSocialSubsidy() + model.getAnotherSubsidy();

        model.setTotalSalary(totalSalary);

        int days = model.getSalaryEnd().getDayOfYear() - model.getSalaryStart().getDayOfYear();
        //日工资
        Double avgSalary = totalSalary / days;
        //加班费 = 工资总额 / 自然月天数 * 加班天数 * 3
        model.setHolidaySalary(avgSalary * model.getHolidayWorkDays() * 3);
        //旷工扣款 = 工资总额 / 自然月天数 * 旷工天数 * 3
        model.setAbsenteeismConsume(avgSalary * model.getAbsenteeismDays() * 3);

        //奖励处罚扣款 = 扣分合计 * 5
        model.setPunishConsume(model.getDeduction() * 5.0);
        //事病假扣款 = 工资总额 / 自然月天数 * 抵扣后请假
        if (model.getOverWorkDays() < model.getVacationDays()) {
            model.setVacationConsume(avgSalary * (model.getVacationDays() - model.getOverWorkDays()));
        } else {
            //不兑换工资，如果剩余加班天数大于本月请假天数，继续累积下去
            model.setVacationConsume(0.0);
        }

        //应税工资 = 工资总额 + 假期加班费 - 社保扣款 - 水电费扣款 - 奖励处罚扣款 - 事病假扣款 - 旷工扣款
        Double taxableSalary = totalSalary + model.getHolidaySalary() - model.getSocialConsume()
                - model.getDormitoryConsume() - model.getPunishConsume()
                - model.getVacationConsume() - model.getAbsenteeismConsume();
        model.setTaxableSalary(taxableSalary);

        setProperties(model);

        verifyData(model);

        model.setFindType(FindType.WAIT);
        super.save(model);
        return BeanTransform.copyProperties(model, SalaryconfirmBO.class);
    }

    //计算个税，具体计算规则请参照流程图
    public Double calculateTax(Double taxableSalary) {
        Double taxs = 0.0;
        Double calculate = taxableSalary - 3500;
        if (calculate <= 1500) {
            taxs = calculate * 0.03;
        } else if (calculate > 1500 && calculate <= 4500) {
            taxs = calculate * 0.1 - 105;
        } else if (calculate > 4500 && calculate <= 9000) {
            taxs = calculate * 0.2 - 555;
        } else if (calculate > 9000 && calculate <= 35000) {
            taxs = calculate * 0.25 - 1005;
        } else if (calculate > 35000 && calculate <= 55000) {
            taxs = calculate * 0.3 - 2755;
        } else if (calculate > 55000 && calculate <= 80000) {
            taxs = calculate * 0.35 - 5505;
        } else if (calculate > 80000) {
            taxs = calculate * 0.45 - 13505;
        }
        return taxs;
    }

    //同一个人不能在一个计薪周期存在两条或两条以上的数据
    public void verifyData(Salaryconfirm model) throws SerException {
        String sql = " select salary.id from salaryconfirm as salary where " +
                " (salary.employeeNumber = '" + model.getEmployeeNumber() + "'" +
                " and salary.year = " + model.getYear() + "" +
                " and salary.month = " + model.getMonth() + ") " +
                " or " +
                " (salary.employeeNumber = '" + model.getEmployeeNumber() + "'" +
                " and ( cast( salary.salaryStart as char ) = '" + model.getSalaryStart() + "'" +
                " and ( cast( salary.salaryEnd as char ) = '" + model.getSalaryEnd() + "' )))";
        List<Salaryconfirm> list = super.findBySql(sql, Salaryconfirm.class, new String[]{"id"});
        if (list != null && !list.isEmpty()) {
            throw new SerException("该用户的薪资核算确认记录在本计薪周期内已经存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SalaryconfirmBO editModel(SalaryconfirmTO to) throws SerException {

        Salaryconfirm model = auditId(to.getId());
        BeanTransform.copyProperties(to, model, true);

        //工资总额 = 员工工资 + **补助
        Double totalSalary = model.getSalary() + model.getCpSubsidy() + model.getDormitorySubsidy()
                + model.getYearSubsidy() + model.getHotSubsidy()
                + model.getSocialSubsidy() + model.getAnotherSubsidy();

        model.setTotalSalary(totalSalary);

        int days = model.getSalaryEnd().getDayOfYear() - model.getSalaryStart().getDayOfYear();
        //日工资
        Double avgSalary = totalSalary / days;
        //加班费 = 工资总额 / 自然月天数 * 加班天数 * 3
        model.setHolidaySalary(avgSalary * model.getHolidayWorkDays() * 3);
        //旷工扣款 = 工资总额 / 自然月天数 * 旷工天数 * 3
        model.setAbsenteeismConsume(avgSalary * model.getAbsenteeismDays() * 3);

        //奖励处罚扣款 = 扣分合计 * 5
        model.setPunishConsume(model.getDeduction() * 5.0);
        //事病假扣款 = 工资总额 / 自然月天数 * 抵扣后请假
        if (model.getOverWorkDays() < model.getVacationDays()) {
            model.setVacationConsume(avgSalary * (model.getVacationDays() - model.getOverWorkDays()));
        } else {
            model.setVacationConsume(0.0);
        }

        //应税工资 = 工资总额 + 假期加班费 - 社保扣款 - 水电费扣款 - 奖励处罚扣款 - 事病假扣款 - 旷工扣款
        Double taxableSalary = totalSalary + model.getHolidaySalary() - model.getSocialConsume()
                - model.getDormitoryConsume() - model.getPunishConsume()
                - model.getVacationConsume() - model.getAbsenteeismConsume();
        model.setTaxableSalary(taxableSalary);

        setProperties(model);

        super.update(model);
        return BeanTransform.copyProperties(model, SalaryconfirmBO.class);
    }

    public void setProperties(Salaryconfirm model) throws SerException {
        Double taxableSalary = model.getTaxableSalary();
        if (taxableSalary > 3500) {

            if (model.getRatepaying() == Ratepaying.NOT) {
                //不纳税情况下： 实发工资 = 应税工资
                model.setActualSalary(taxableSalary);
                //本月第二次发工资取整计算
                String totalStr = String.valueOf(taxableSalary);
                int pointNum = totalStr.lastIndexOf(".");
                String front = totalStr.substring(pointNum - 2, pointNum);
                String after = "0" + totalStr.substring(pointNum);
                //由于取整需要将实发工资及第一次发工资取整并且避免Double损失精度
                Double firstPrefix = 3400.0 + Integer.parseInt(front);
                Double firstSalary = 3400.0 + Integer.parseInt(front) + Double.parseDouble(after);
                model.setFirstSalary(firstSalary);
                model.setSecondSalary(taxableSalary.intValue() - firstPrefix);
                //本月需缴发票金额
                model.setNeedRatepaying((model.getSecondSalary() / 2));

            } else {
                //纳税情况下： 实发工资 = 应税工资 - 个税扣款
                Double actualSalary = taxableSalary - calculateTax(taxableSalary);
                model.setActualSalary(actualSalary);
                model.setTaxConsume(calculateTax(taxableSalary));
                model.setFirstSalary(actualSalary);
            }
        } else {
            if (model.getRatepaying() == Ratepaying.YES) {
                throw new SerException("工资超过3500，无需缴纳个人所得税!");
            }
        }
    }

    @Override
    public List<SalaryconfirmBO> pageList(SalaryconfirmDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<SalaryconfirmBO> boList = BeanTransform.copyProperties(super.findByPage(dto), SalaryconfirmBO.class);

        if (boList != null && !boList.isEmpty()) {
            for (SalaryconfirmBO bo : boList) {
                List<InvoiceSubmitBO> submitBOList = invoiceSubmitSer.findByCondition(bo.getEmployeeNumber(), bo.getYear(), bo.getMonth());
                if (submitBOList != null && !submitBOList.isEmpty()) {
                    Double account = submitBOList.stream().filter(p -> p != null && p.getAmount() != null).mapToDouble(p -> p.getAmount()).sum();
                    bo.setTotalSubmit(account);
                }
                bo.setTotalRatepayed(findBeforeRatepayed(bo.getEmployeeNumber(), DateUtil.parseDate(bo.getSalaryStart())));
            }
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void confirm(String id) throws SerException {
        Salaryconfirm model = auditId(id);
        if (model.getFindType() == FindType.CONFIRM) {
            throw new SerException("该记录已经为已确认状态，无需重复确认!");
        }
        model.setFindType(FindType.CONFIRM);
        super.update(model);
    }

    public Double findBeforeRatepayed(String employeeNumber, LocalDate salaryStart) throws SerException {
        SalaryconfirmDTO dto = new SalaryconfirmDTO();
        dto.getConditions().add(Restrict.eq("name", employeeNumber));
        dto.getConditions().add(Restrict.lt("salaryStart", salaryStart));
        List<Salaryconfirm> list = super.findByCis(dto);
        Double before = 0.0;
        if (list != null && !list.isEmpty()) {
            before = list.stream().filter(p -> p != null && p.getNeedRatepaying() != null).mapToDouble(p -> p.getNeedRatepaying()).sum();
        }
        return before;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void firstPay(String id) throws SerException {
        Salaryconfirm model = auditId(id);
        if (model.getFirstPayedTime() != null) {
            throw new SerException("'第二次是否发放'已经确认，无需重复确认!");
        }
        model.setFirstPayedTime(LocalDateTime.now());
        super.update(model);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void secondPay(String id) throws SerException {
        Salaryconfirm model = auditId(id);
        if (model.getFirstPayedTime() != null) {
            model.setSecondPayedTime(LocalDateTime.now());
            model.setFindType(FindType.PAYED);
            super.update(model);
        } else {
            throw new SerException("第一次尚未付款!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void firstConfirm(String id) throws SerException {
        Salaryconfirm model = auditId(id);
        if (model.getFirstConfirm() != null && model.getFirstConfirm()) {
            throw new SerException("'第二次是否发放'已经确认，无需重复确认!");
        }
        model.setFirstConfirm(Boolean.TRUE);
        model.setFirstConfirmTime(LocalDateTime.now());
        super.update(model);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void secondConfirm(String id) throws SerException {
        Salaryconfirm model = auditId(id);
        if (model.getSecondConfirm() != null && model.getSecondConfirm()) {
            throw new SerException("'第二次是否发放'已经确认，无需重复确认!");
        }
        model.setSecondConfirm(Boolean.TRUE);
        model.setSecondConfirmTime(LocalDateTime.now());
        model.setFindType(FindType.PAYED);
        super.update(model);
    }

    //检查id是否非法数据
    public Salaryconfirm auditId(String id) throws SerException {
        Salaryconfirm model = super.findById(id);
        if (model != null) {
            return model;
        } else {
            throw new SerException("非法ID,对象不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<SalaryconfirmBO> collectByCondition(ConditionTO to) throws SerException {
        SalaryconfirmDTO dto = new SalaryconfirmDTO();
        dto.getConditions().add(Restrict.eq("year", to.getYear()));
        dto.getConditions().add(Restrict.eq("month", to.getMonth()));
        if (!StringUtils.isEmpty(to.getArea())) {
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        } else if (!StringUtils.isEmpty(to.getDepartment())) {
            dto.getConditions().add(Restrict.eq("department", to.getDepartment()));
        } else if (!StringUtils.isEmpty(to.getUserName())) {
            dto.getConditions().add(Restrict.eq("name", to.getUserName()));
        }
        List<SalaryconfirmBO> bolist = BeanTransform.copyProperties(super.findByCis(dto), SalaryconfirmBO.class);

        if (bolist == null || bolist.isEmpty()) {
            return null;
        }

        Double salaries = bolist.stream().filter(p -> p != null && p.getSalary() != null).mapToDouble(SalaryconfirmBO::getSalary).sum();
        Double cpSubsidies = bolist.stream().filter(p -> p != null && p.getCpSubsidy() != null).mapToDouble(SalaryconfirmBO::getCpSubsidy).sum();
        Double dormitorySubsidies = bolist.stream().filter(p -> p != null && p.getSalary() != null).mapToDouble(SalaryconfirmBO::getDormitorySubsidy).sum();
        Double yearSubsidies = bolist.stream().filter(p -> p != null && p.getYearSubsidy() != null).mapToDouble(SalaryconfirmBO::getYearSubsidy).sum();
        Double hotSubsidies = bolist.stream().filter(p -> p != null && p.getHotSubsidy() != null).mapToDouble(SalaryconfirmBO::getHotSubsidy).sum();
        Double socialSubsidies = bolist.stream().filter(p -> p != null && p.getSocialSubsidy() != null).mapToDouble(SalaryconfirmBO::getSocialSubsidy).sum();
        Double anotherSubsidies = bolist.stream().filter(p -> p != null && p.getAnotherSubsidy() != null).mapToDouble(SalaryconfirmBO::getAnotherSubsidy).sum();
        Double totalSalaries = bolist.stream().filter(p -> p != null && p.getTotalSalary() != null).mapToDouble(SalaryconfirmBO::getTotalSalary).sum();
        Double holidaySalaries = bolist.stream().filter(p -> p != null && p.getHolidaySalary() != null).mapToDouble(SalaryconfirmBO::getHolidaySalary).sum();
        Double socialConsume = bolist.stream().filter(p -> p != null && p.getSocialConsume() != null).mapToDouble(SalaryconfirmBO::getSocialConsume).sum();
        Double dormitoryConsume = bolist.stream().filter(p -> p != null && p.getDormitoryConsume() != null).mapToDouble(SalaryconfirmBO::getDormitoryConsume).sum();
        Double punishConsume = bolist.stream().filter(p -> p != null && p.getPunishConsume() != null).mapToDouble(SalaryconfirmBO::getPunishConsume).sum();
        Double taxConsume = bolist.stream().filter(p -> p != null && p.getTaxConsume() != null).mapToDouble(SalaryconfirmBO::getTaxConsume).sum();
        Double vacationConsume = bolist.stream().filter(p -> p != null && p.getVacationConsume() != null).mapToDouble(SalaryconfirmBO::getVacationConsume).sum();
        Double absenteeismConsume = bolist.stream().filter(p -> p != null && p.getAbsenteeismConsume() != null).mapToDouble(SalaryconfirmBO::getAbsenteeismConsume).sum();
        Double actualSalaries = bolist.stream().filter(p -> p != null && p.getActualSalary() != null).mapToDouble(SalaryconfirmBO::getActualSalary).sum();

        SalaryconfirmBO totalBO = null;
        if (!StringUtils.isEmpty(to.getArea())) {
            totalBO = new SalaryconfirmBO("area", to.getYear(), to.getMonth(), salaries,
                    cpSubsidies, dormitorySubsidies, yearSubsidies,
                    hotSubsidies, socialSubsidies, anotherSubsidies,
                    totalSalaries, holidaySalaries, socialConsume,
                    dormitoryConsume, punishConsume, taxConsume,
                    vacationConsume, absenteeismConsume, actualSalaries);
        } else if (!StringUtils.isEmpty(to.getDepartment())) {
            totalBO = new SalaryconfirmBO("department", to.getYear(), to.getMonth(), salaries,
                    cpSubsidies, dormitorySubsidies, yearSubsidies,
                    hotSubsidies, socialSubsidies, anotherSubsidies,
                    totalSalaries, holidaySalaries, socialConsume,
                    dormitoryConsume, punishConsume, taxConsume,
                    vacationConsume, absenteeismConsume, actualSalaries);
        } else if (!StringUtils.isEmpty(to.getUserName())) {
            totalBO = new SalaryconfirmBO("name", to.getYear(), to.getMonth(), salaries,
                    cpSubsidies, dormitorySubsidies, yearSubsidies,
                    hotSubsidies, socialSubsidies, anotherSubsidies,
                    totalSalaries, holidaySalaries, socialConsume,
                    dormitoryConsume, punishConsume, taxConsume,
                    vacationConsume, absenteeismConsume, actualSalaries);
        }

        bolist.add(totalBO);

        return bolist;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AnalyzeBO> analyzeByCondition(ConditionTO to, String type) throws SerException {

        StringBuilder sql = new StringBuilder(" SELECT ");
        StringBuilder basicStr = new StringBuilder(" sum(s.actualSalary) as salary from salaryconfirm s where 0 = 0 ");
        basicStr.append(" and s.year = " + to.getYear());
        basicStr.append(" and s.month = " + to.getMonth());

        StringBuilder lastSql = new StringBuilder(" SELECT ");
        StringBuilder lastBasicStr = new StringBuilder(" sum(s.actualSalary) as salary from salaryconfirm s where 0 = 0 ");
        if (to.getMonth() == 12) {
            lastBasicStr.append(" and s.year = " + (to.getYear() - 1));
            lastBasicStr.append(" and s.month = 1 ");
        } else {
            lastBasicStr.append(" and s.year = " + to.getYear());
            lastBasicStr.append(" and s.month = " + (to.getMonth() - 1));
        }

        String[] field = new String[2];

        if (!StringUtils.isEmpty(to.getArea()) || type.equals("area")) {
            field[0] = "area";

            sql.append("area ,");
            sql.append(basicStr);

            lastSql.append("area ,");
            lastSql.append(lastBasicStr);

            if (!StringUtils.isEmpty(to.getArea())) {
                sql.append(" and s.area = '" + to.getArea() + "'");
                lastSql.append(" and s.area = '" + to.getArea() + "'");
            }

            sql.append(" GROUP BY s.area ");
            lastSql.append(" GROUP BY s.area ");

        } else if (!StringUtils.isEmpty(to.getDepartment()) || type.equals("department")) {
            field[0] = "department";

            sql.append("department ,");
            sql.append(basicStr);

            lastSql.append("department ,");
            lastSql.append(lastBasicStr);

            if (!StringUtils.isEmpty(to.getDepartment())) {
                sql.append(" and s.department = '" + to.getDepartment() + "'");
                lastSql.append(" and s.department = '" + to.getDepartment() + "'");
            }

            sql.append(" GROUP BY s.department ");
            lastSql.append(" GROUP BY s.department ");

        } else if (!StringUtils.isEmpty(to.getUserName()) || type.equals("name")) {

            field[0] = "name";

            sql.append("name ,");
            sql.append(basicStr);

            lastSql.append("name ,");
            lastSql.append(lastBasicStr);

            if (!StringUtils.isEmpty(to.getUserName())) {
                sql.append(" and s.name = '" + to.getUserName() + "'");
                lastSql.append(" and s.name = '" + to.getUserName() + "'");
            }

            sql.append(" GROUP BY s.name ");
            lastSql.append(" GROUP BY s.name ");
        }
        field[1] = "salary";

        List<AnalyzeBO> boList = super.findBySql(sql.toString(), AnalyzeBO.class, field);
        List<AnalyzeBO> lastBOList = super.findBySql(lastSql.toString(), AnalyzeBO.class, field);

        return combination(boList, lastBOList, to);
    }

    @Override
    public void importExcel(List<SalaryconfirmTO> toList) throws SerException {
        for (SalaryconfirmTO to : toList) {
            insertModel(to);
        }
    }

    @Override
    public byte[] exportExcel(Integer year, Integer month) throws SerException {
        SalaryconfirmDTO dto = new SalaryconfirmDTO();
        dto.getConditions().add(Restrict.eq("year", year));
        dto.getConditions().add(Restrict.eq("month", month));
        List<Salaryconfirm> list = super.findByCis(dto);
        List<SalaryconfirmExcel> toList = new ArrayList<SalaryconfirmExcel>();
        for (Salaryconfirm model : list) {
            SalaryconfirmExcel excel = new SalaryconfirmExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    //组合分析数据
    public List<AnalyzeBO> combination(List<AnalyzeBO> boList, List<AnalyzeBO> lastBOList, ConditionTO to) throws SerException {

        for (AnalyzeBO bo : boList) {
            for (AnalyzeBO lastBO : lastBOList) {
                if (!StringUtils.isEmpty(to.getArea())) {
                    if (bo.getArea().equals(lastBO.getArea())) {
                        setProperties(bo, lastBO);
                    }

                } else if (!StringUtils.isEmpty(to.getDepartment())) {
                    if (bo.getDepartment().equals(lastBO.getDepartment())) {
                        setProperties(bo, lastBO);
                    }

                } else if (!StringUtils.isEmpty(to.getUserName())) {
                    if (bo.getName().equals(lastBO.getName())) {
                        setProperties(bo, lastBO);
                    }
                }
            }
        }
        return boList;
    }

    public void setProperties(AnalyzeBO bo, AnalyzeBO lastBO) {

        bo.setBalance(bo.getSalary() - lastBO.getSalary());
        bo.setLastSalary(lastBO.getSalary());
        Double growUpRate = ((bo.getSalary() - lastBO.getSalary()) / lastBO.getSalary() * 100);
        DecimalFormat df = new DecimalFormat("#.00");
        bo.setGrowUpRate(df.format(growUpRate) + "%");
    }
}