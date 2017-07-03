package com.bjike.goddess.analysis.service;

import com.bjike.goddess.analysis.bo.CollectAreaBO;
import com.bjike.goddess.analysis.bo.CollectDepartmentBO;
import com.bjike.goddess.analysis.bo.CollectMonthBO;
import com.bjike.goddess.analysis.bo.IncomeCostAnalysisBO;
import com.bjike.goddess.analysis.dto.IncomeCostAnalysisDTO;
import com.bjike.goddess.analysis.entity.IncomeCostAnalysis;
import com.bjike.goddess.analysis.to.IncomeCostAnalysisTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.FirstSubjectAPI;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.PartBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收入成本分析业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-31 03:37 ]
 * @Description: [ 收入成本分析业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "analysisSerCache")
@Service
public class IncomeCostAnalysisSerImpl extends ServiceImpl<IncomeCostAnalysis, IncomeCostAnalysisDTO> implements IncomeCostAnalysisSer {
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;

    @Override
    public Long countIncomeCostAnalysis(IncomeCostAnalysisDTO incomeCostAnalysisDTO) throws SerException {
        Long count = super.count(incomeCostAnalysisDTO);
        return count;
    }

    @Override
    public IncomeCostAnalysisBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        IncomeCostAnalysis incomeCostAnalysis = super.findById(id);
        return BeanTransform.copyProperties(incomeCostAnalysis, IncomeCostAnalysisBO.class);
    }

    @Override
    public List<IncomeCostAnalysisBO> findListIncomeCostAnalysis(IncomeCostAnalysisDTO incomeCostAnalysisDTO) throws SerException {
        List<IncomeCostAnalysis> incomeCostAnalyses = super.findByPage(incomeCostAnalysisDTO);
        List<IncomeCostAnalysisBO> incomeCostAnalysisBOS = BeanTransform.copyProperties(incomeCostAnalyses, IncomeCostAnalysisBO.class);
        return incomeCostAnalysisBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IncomeCostAnalysisBO insertIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
        IncomeCostAnalysis incomeCostAnalysis =
                BeanTransform.copyProperties(incomeCostAnalysisTO, IncomeCostAnalysis.class, true);
        //todo: 出车管理（出车司机数，司机出车费）
        List<PartBO> oilRecharge = voucherGenerateAPI.findByCondition(String.valueOf(incomeCostAnalysisTO.getOilRecharge()));

//        List<PartBO> rent = voucherGenerateAPI.findByCondition(String.valueOf(incomeCostAnalysisTO.getRent()));
//        List<PartBO> socialSecurity = voucherGenerateAPI.findByCondition(String.valueOf(incomeCostAnalysisTO.getSocialSecurity()));
//        List<PartBO> staffWage = voucherGenerateAPI.findByCondition(String.valueOf(incomeCostAnalysisTO.getStaffWage()));
//        List<PartBO> office = voucherGenerateAPI.findByCondition(String.valueOf(incomeCostAnalysisTO.getOffice()));
//        List<PartBO> marketCost = voucherGenerateAPI.findByCondition(String.valueOf(incomeCostAnalysisTO.getMarketCost()));
//        List<PartBO> tax = voucherGenerateAPI.findByCondition(String.valueOf(incomeCostAnalysisTO.getTax()));
        //合计（司机出车费+油卡充值+房租+社保+员工工资+办公费+市场费+税金）
        Double total = incomeCostAnalysis.getDriverFee() + incomeCostAnalysis.getOilRecharge() +
                incomeCostAnalysis.getRent() + incomeCostAnalysis.getSocialSecurity() +
                incomeCostAnalysis.getStaffWage() + incomeCostAnalysis.getOffice() +
                incomeCostAnalysis.getMarketCost() + incomeCostAnalysis.getTax();
        incomeCostAnalysis.setTotal(total);
        //差额（税后余额收入-合计）
        Double balance = incomeCostAnalysis.getIncomeAfterTax() - total;
        incomeCostAnalysis.setBalance(balance);
        incomeCostAnalysis.setCreateTime(LocalDateTime.now());
        super.save(incomeCostAnalysis);
        return BeanTransform.copyProperties(incomeCostAnalysis, IncomeCostAnalysisBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IncomeCostAnalysisBO editIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
        if (StringUtils.isBlank(incomeCostAnalysisTO.getId())) {
            throw new SerException("id不能为空");
        }
        IncomeCostAnalysis incomeCostAnalysis = super.findById(incomeCostAnalysisTO.getId());
        BeanTransform.copyProperties(incomeCostAnalysisTO, incomeCostAnalysis, true);

        //合计（司机出车费+油卡充值+房租+社保+员工工资+办公费+市场费+税金）
        Double total = incomeCostAnalysis.getDriverFee() + incomeCostAnalysis.getOilRecharge() +
                incomeCostAnalysis.getRent() + incomeCostAnalysis.getSocialSecurity() +
                incomeCostAnalysis.getStaffWage() + incomeCostAnalysis.getOffice() +
                incomeCostAnalysis.getMarketCost() + incomeCostAnalysis.getTax();
        incomeCostAnalysis.setTotal(total);
        //差额（税后余额收入-合计）
        Double balance = incomeCostAnalysis.getIncomeAfterTax() - total;
        incomeCostAnalysis.setBalance(balance);
        incomeCostAnalysis.setModifyTime(LocalDateTime.now());
        super.update(incomeCostAnalysis);
        return BeanTransform.copyProperties(incomeCostAnalysis, IncomeCostAnalysisBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeIncomeCostAnalysis(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<CollectAreaBO> collectArea(String[] areas) throws SerException {
        String[] areasTemp = new String[areas.length];
        for (int i = 0; i < areas.length; i++) {
            areasTemp[i] = "'" + areas[i] + "'";
        }
        String areaStr = StringUtils.join(areasTemp, ",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ");
        sb.append(" (SELECT area,year AS year,month as month,department as department,sum(carNum)AS carNum, ");
        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax ");
        sb.append(" FROM analysis_incomecostanalysis ");
        sb.append(" WHERE area IN (%s) GROUP BY year,month,department,area ORDER BY area)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' as area,NULL as year,NULL AS month,NULL AS department,sum(carNum)AS carNum, ");
        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax FROM ");
        sb.append(" (SELECT area,year AS year,month as month,department as department,sum(carNum)AS carNum, ");
        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax ");
        sb.append(" FROM analysis_incomecostanalysis ");
        sb.append(" WHERE area IN (%s) GROUP BY year,month,department,area ORDER BY area)A ");
        String sql = sb.toString();
        sql = String.format(sql, areaStr, areaStr);
        String[] fields = new String[]{"area", "year", "month", "department", "carNum", "driverFee", "oilRecharge",
                "rent", "socialSecurity", "staffWage", "office", "marketCost", "tax", "total", "staffNum", "perCapitaWage",
                "incomeAfterTax"};
        List<CollectAreaBO> collectAreaBOS = super.findBySql(sql, CollectAreaBO.class, fields);
        return collectAreaBOS;
    }

    @Override
    public List<CollectMonthBO> collectMonth(String[] months) throws SerException {
        String[] monthsTemp = new String[months.length];
        for (int i = 0; i < months.length; i++) {
            monthsTemp[i] = "'" + months[i] + "'";
        }
        String monthsStr = StringUtils.join(monthsTemp, ",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ");
        sb.append(" (SELECT month,year AS year,area as area,department as department,sum(carNum)AS carNum, ");
        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax ");
        sb.append(" FROM analysis_incomecostanalysis ");
        sb.append(" WHERE month IN (%s) GROUP BY year,area,department,month ORDER BY month)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' as month,NULL as year,NULL AS area,NULL AS department,sum(carNum)AS carNum, ");
        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax FROM ");
        sb.append(" (SELECT month,year AS year,area as area,department as department,sum(carNum)AS carNum, ");
        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax ");
        sb.append(" FROM analysis_incomecostanalysis ");
        sb.append(" WHERE month IN (%s) GROUP BY year,area,department,month ORDER BY month)A ");
        String sql = sb.toString();
        sql = String.format(sql, monthsStr, monthsStr);
        String[] fields = new String[]{"month", "year", "area", "department", "carNum", "driverFee", "oilRecharge",
                "rent", "socialSecurity", "staffWage", "office", "marketCost", "tax", "total", "staffNum", "perCapitaWage",
                "incomeAfterTax"};
        List<CollectMonthBO> collectMonthBOS = super.findBySql(sql, CollectMonthBO.class, fields);
        return collectMonthBOS;
    }

    @Override
    public List<CollectDepartmentBO> collectDepartment(String[] departments) throws SerException {
        String[] departmentsTemp = new String[departments.length];
        for (int i = 0; i < departments.length; i++) {
            departmentsTemp[i] = "'" + departments[i] + "'";
        }
        String departmentsStr = StringUtils.join(departmentsTemp, ",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ");
        sb.append(" (SELECT department,year AS year,month as month,area as area,sum(carNum)AS carNum, ");
        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax ");
        sb.append(" FROM analysis_incomecostanalysis ");
        sb.append(" WHERE department IN (%s) GROUP BY year,month,area,department ORDER BY department)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' as department,NULL as year,NULL AS month,NULL AS area,sum(carNum)AS carNum, ");
        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax FROM ");
        sb.append(" (SELECT department,year AS year,month as month,area as area,sum(carNum)AS carNum, ");
        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax ");
        sb.append(" FROM analysis_incomecostanalysis ");
        sb.append(" WHERE department IN (%s) GROUP BY year,month,area,department ORDER BY department)A ");
        String sql = sb.toString();
        sql = String.format(sql, departmentsStr, departmentsStr);
        String[] fields = new String[]{"department", "year", "month", "area", "carNum", "driverFee", "oilRecharge",
                "rent", "socialSecurity", "staffWage", "office", "marketCost", "tax", "total", "staffNum", "perCapitaWage",
                "incomeAfterTax"};
        List<CollectDepartmentBO> collectDepartmentBOS = super.findBySql(sql, CollectDepartmentBO.class, fields);
        return collectDepartmentBOS;
    }

    @Override
    public List<String> getArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<IncomeCostAnalysisBO> incomeCostAnalysisBOS = super.findBySql("select distinct area from analysis_incomecostanalysis group by area order by area asc ", IncomeCostAnalysisBO.class, fields);

        List<String> areaList = incomeCostAnalysisBOS.stream().map(IncomeCostAnalysisBO::getArea)
                .filter(area -> (StringUtils.isNotBlank(area))).distinct().collect(Collectors.toList());


        return areaList;
    }

    //    @Override
//    public List<Integer> getMonth() throws SerException {
//        String[] fields = new String[]{"month"};
//        List<IncomeCostAnalysisBO> incomeCostAnalysisBOS = super.findBySql("select distinct month from analysis_incomecostanalysis group by month order by month asc ", IncomeCostAnalysisBO.class, fields);
//
//        List<Integer> monthList = new ArrayList<>();
//        incomeCostAnalysisBOS.stream().forEach(obj -> {
//            monthList.add(obj.getMonth());
//        });
//        return monthList;
//    }
    @Override
    public List<String> getMonth() throws SerException {
        String[] fields = new String[]{"month"};
        List<IncomeCostAnalysisBO> incomeCostAnalysisBOS = super.findBySql("select distinct month from analysis_incomecostanalysis group by month order by month asc ", IncomeCostAnalysisBO.class, fields);

        List<String> monthList = incomeCostAnalysisBOS.stream().map(IncomeCostAnalysisBO::getMonth)
                .filter(month -> (StringUtils.isNotBlank(month))).distinct().collect(Collectors.toList());


        return monthList;
    }

    @Override
    public List<String> getDepartment() throws SerException {
        String[] fields = new String[]{"department"};
        List<IncomeCostAnalysisBO> incomeCostAnalysisBOS = super.findBySql("select distinct department from analysis_incomecostanalysis group by department order by department asc ", IncomeCostAnalysisBO.class, fields);

        List<String> departmentList = incomeCostAnalysisBOS.stream().map(IncomeCostAnalysisBO::getDepartment)
                .filter(department -> (StringUtils.isNotBlank(department))).distinct().collect(Collectors.toList());


        return departmentList;
    }


}