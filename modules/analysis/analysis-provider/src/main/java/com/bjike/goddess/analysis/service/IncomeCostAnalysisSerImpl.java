package com.bjike.goddess.analysis.service;

import com.bjike.goddess.analysis.bo.CollectBO;
import com.bjike.goddess.analysis.bo.IncomeCostAnalysisBO;
import com.bjike.goddess.analysis.dto.IncomeCostAnalysisDTO;
import com.bjike.goddess.analysis.entity.IncomeCostAnalysis;
import com.bjike.goddess.analysis.enums.GuideAddrStatus;
import com.bjike.goddess.analysis.excel.SonPermissionObject;
import com.bjike.goddess.analysis.to.CollectTO;
import com.bjike.goddess.analysis.to.GuidePermissionTO;
import com.bjike.goddess.analysis.to.IncomeCostAnalysisTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.bo.DriverDispatchFeeBO;
import com.bjike.goddess.dispatchcar.bo.DriverDispatchsBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.PartBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("incomecostanalysis");
        obj.setDescribesion("收入成本分析");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

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
        incomeCostAnalysisDTO.getSorts().add("createTime=desc");
//        int month = incomeCostAnalysisDTO.getMonth();
//        //出车司机数
//        List<DriverDispatchsBO> driver = dispatchCarInfoAPI.findDispatchs(month);
//        //司机出车费
//        List<DriverDispatchFeeBO> driverFee = dispatchCarInfoAPI.findDispatchFree(month);

        String[] condis = incomeCostAnalysisDTO.getCondi();


        List<PartBO> partBOS = voucherGenerateAPI.findByCondition(condis);
        IncomeCostAnalysisBO bo = new IncomeCostAnalysisBO();
        List<IncomeCostAnalysis> incomeCostAnalyses = super.findByPage(incomeCostAnalysisDTO);
        List<IncomeCostAnalysisBO> incomeCostAnalysisBOS = BeanTransform.copyProperties(incomeCostAnalyses, IncomeCostAnalysisBO.class);
        return incomeCostAnalysisBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IncomeCostAnalysisBO insertIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
        IncomeCostAnalysis incomeCostAnalysis =
                BeanTransform.copyProperties(incomeCostAnalysisTO, IncomeCostAnalysis.class, true);

        //合计（司机出车费+油卡充值+房租+社保+员工工资+办公费+市场费+税金）
        Double total = incomeCostAnalysisTO.getDriverFee() + incomeCostAnalysisTO.getOilRecharge() +
                incomeCostAnalysisTO.getRent() + incomeCostAnalysisTO.getSocialSecurity() +
                incomeCostAnalysisTO.getStaffWage() + incomeCostAnalysisTO.getOffice() +
                incomeCostAnalysisTO.getMarketCost() + incomeCostAnalysisTO.getTax();
        incomeCostAnalysis.setTotal(total);
        //差额（税后余额收入-合计）
        Double balance = incomeCostAnalysisTO.getIncomeAfterTax() - total;
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
        Double total = incomeCostAnalysisTO.getDriverFee() + incomeCostAnalysisTO.getOilRecharge() +
                incomeCostAnalysisTO.getRent() + incomeCostAnalysisTO.getSocialSecurity() +
                incomeCostAnalysisTO.getStaffWage() + incomeCostAnalysisTO.getOffice() +
                incomeCostAnalysisTO.getMarketCost() + incomeCostAnalysisTO.getTax();
        incomeCostAnalysis.setTotal(total);
        //差额（税后余额收入-合计）
        Double balance = incomeCostAnalysisTO.getIncomeAfterTax() - total;
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

//    public List<CollectAreaBO> collectArea(String[] areas) throws SerException {
//        String[] areasTemp = new String[areas.length];
//        for (int i = 0; i < areas.length; i++) {
//            areasTemp[i] = "'" + areas[i] + "'";
//        }
//        String areaStr = StringUtils.join(areasTemp, ",");
//        StringBuilder sb = new StringBuilder();
//        sb.append(" SELECT * FROM ");
//        sb.append(" (SELECT area,year AS year,month as month,department as department,sum(carNum)AS carNum, ");
//        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
//        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
//        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
//        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
//        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax ");
//        sb.append(" FROM analysis_incomecostanalysis ");
//        sb.append(" WHERE area IN (%s) GROUP BY year,month,department,area ORDER BY area)A ");
//        sb.append(" UNION ");
//        sb.append(" SELECT '合计' as area,NULL as year,NULL AS month,NULL AS department,sum(carNum)AS carNum, ");
//        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
//        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
//        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
//        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
//        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax FROM ");
//        sb.append(" (SELECT area,year AS year,month as month,department as department,sum(carNum)AS carNum, ");
//        sb.append(" sum(driverFee)AS driverFee,sum(oilRecharge) AS oilRecharge, ");
//        sb.append(" sum(rent)AS rent,sum(socialSecurity) AS socialSecurity, ");
//        sb.append(" sum(staffWage)AS staffWage,sum(office)AS office,sum(marketCost)AS marketCost, ");
//        sb.append(" sum(tax) AS tax,sum(total)AS total,sum(staffNum)AS staffNum, ");
//        sb.append(" sum(perCapitaWage)AS perCapitaWage,sum(incomeAfterTax)AS incomeAfterTax ");
//        sb.append(" FROM analysis_incomecostanalysis ");
//        sb.append(" WHERE area IN (%s) GROUP BY year,month,department,area ORDER BY area)A ");
//        String sql = sb.toString();
//        sql = String.format(sql, areaStr, areaStr);
//        String[] fields = new String[]{"area", "year", "month", "department", "carNum", "driverFee", "oilRecharge",
//                "rent", "socialSecurity", "staffWage", "office", "marketCost", "tax", "total", "staffNum", "perCapitaWage",
//                "incomeAfterTax"};
//        List<CollectAreaBO> collectAreaBOS = super.findBySql(sql, CollectAreaBO.class, fields);
//        return collectAreaBOS;
//    }

    @Override
    public List<CollectBO> collect(CollectTO to) throws SerException {
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        IncomeCostAnalysisDTO dto = new IncomeCostAnalysisDTO();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            String[] condi = new String[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("date", condi));
        }
        if (null != to.getArea()) {
            dto.getConditions().add(Restrict.in("area", to.getArea()));
        }
        if (null != to.getDepartment()) {
            dto.getConditions().add(Restrict.in("department", to.getDepartment()));
        }
        return collectAnalysis(dto);
    }

    public List<CollectBO> collectAnalysis(IncomeCostAnalysisDTO dto) throws SerException {
        List<IncomeCostAnalysis> list = super.findByCis(dto);
        List<CollectBO> collectBOS = new ArrayList<>();
        for (IncomeCostAnalysis model : list) {
            CollectBO bo = new CollectBO();
            bo.setArea(model.getArea());
            bo.setDepartment(model.getDepartment());
            bo.setDate(String.valueOf(model.getDate()));
            bo.setDriverFee(model.getDriverFee());
            bo.setOilRecharge(model.getOilRecharge());
            bo.setRent(model.getRent());
            bo.setSocialSecurity(model.getSocialSecurity());
            bo.setStaffWage(model.getStaffWage());
            bo.setOffice(model.getOffice());
            bo.setMarketCost(model.getMarketCost());
            bo.setTax(model.getTax());
            collectBOS.add(bo);
        }
        Integer carNum = 0;//出车司机数
        Double driverFee = 0.0;//司机出车费
        Double oilRecharge = 0.0;//油卡充值
        Double rent = 0.0;//房租
        Double socialSecurity = 0.0;//社保
        Double staffWage = 0.0;//员工工资
        Double office = 0.0;//办公费
        Double marketCost = 0.0;//市场费
        Double tax = 0.0;//税金
        Double total = 0.0;//合计（司机出车费+油卡充值+房租+社保+员工工资+办公费+市场费+税金）
        Integer staffNum = 0;//员工人数
        Double perCapitaWage = 0.0;//人均工资
        Double incomeAfterTax = 0.0;//税后余额收入
        if (list != null) {
            carNum = list.stream().filter(p -> p.getCarNum() != null).mapToInt(p -> p.getCarNum()).sum();
            driverFee = list.stream().filter(p -> p.getDriverFee() != null).mapToDouble(p -> p.getDriverFee()).sum();
            oilRecharge = list.stream().filter(p -> p.getOilRecharge() != null).mapToDouble(p -> p.getOilRecharge()).sum();
            rent = list.stream().filter(p -> p.getRent() != null).mapToDouble(p -> p.getRent()).sum();
            socialSecurity = list.stream().filter(p -> p.getSocialSecurity() != null).mapToDouble(p -> p.getSocialSecurity()).sum();
            staffWage = list.stream().filter(p -> p.getStaffWage() != null).mapToDouble(p -> p.getStaffWage()).sum();
            office = list.stream().filter(p -> p.getDriverFee() != null).mapToDouble(p -> p.getOffice()).sum();
            marketCost = list.stream().filter(p -> p.getMarketCost() != null).mapToDouble(p -> p.getMarketCost()).sum();
            tax = list.stream().filter(p -> p.getTax() != null).mapToDouble(p -> p.getTax()).sum();
            total = driverFee + oilRecharge + rent + socialSecurity + staffWage + office + marketCost + tax;
            staffNum = list.stream().filter(p -> p.getStaffNum() != null).mapToInt(p -> p.getStaffNum()).sum();
            perCapitaWage = list.stream().filter(p -> p.getPerCapitaWage() != null).mapToDouble(p -> p.getPerCapitaWage()).sum();
            incomeAfterTax = list.stream().filter(p -> p.getIncomeAfterTax() != null).mapToDouble(p -> p.getIncomeAfterTax()).sum();

            CollectBO totalBO = new CollectBO("合计", "", "", carNum, driverFee, oilRecharge, rent, socialSecurity,
                    staffWage, office, marketCost, tax, total, staffNum, perCapitaWage, incomeAfterTax);
            collectBOS.add(totalBO);
        } else {
            CollectBO totalBO = new CollectBO("合计", "", "", carNum, driverFee, oilRecharge, rent, socialSecurity,
                    staffWage, office, marketCost, tax, total, staffNum, perCapitaWage, incomeAfterTax);
            collectBOS.add(totalBO);
        }


        return collectBOS;
    }


    @Override
    public List<String> getArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<IncomeCostAnalysisBO> incomeCostAnalysisBOS = super.findBySql("select distinct area from analysis_incomecostanalysis group by area order by area asc ", IncomeCostAnalysisBO.class, fields);

        List<String> areaList = incomeCostAnalysisBOS.stream().map(IncomeCostAnalysisBO::getArea)
                .filter(area -> (StringUtils.isNotBlank(area))).distinct().collect(Collectors.toList());


        return areaList;
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