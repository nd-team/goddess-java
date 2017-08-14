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
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
//    @Autowired
//    private DispatchCarInfoAPI dispatchCarInfoAPI;
//       @Autowired
//    private VoucherGenerateAPI voucherGenerateAPI;


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
    public List<IncomeCostAnalysisBO> findListIncomeCostAnalysis(IncomeCostAnalysisDTO dto) throws SerException {
        List<IncomeCostAnalysis> incomeCostAnalyses = super.findByCis(dto);
        List<IncomeCostAnalysisBO> incomeCostAnalysisBOS = BeanTransform.copyProperties(incomeCostAnalyses, IncomeCostAnalysisBO.class);
        return incomeCostAnalysisBOS;
//        Integer year = dto.getYear();
//        Integer month = dto.getMonth();
//        String area = dto.getArea();
//        String projectGroup = dto.getProjectGroup();
//        if (year != null) {
//            dto.getConditions().add(Restrict.eq("year", year));
//        }
//        if (month != null) {
//            dto.getConditions().add(Restrict.eq("month", month));
//        }
//        if (area != null) {
//            dto.getConditions().add(Restrict.eq("area", area));
//        }
//        if (projectGroup != null) {
//            dto.getConditions().add(Restrict.eq("department", projectGroup));
//        }
//        List<IncomeCostAnalysis> incomeCostAnalysis = super.findByCis(dto);
//        if (incomeCostAnalysis == null) {
//            IncomeCostAnalysis entity = new IncomeCostAnalysis();
//            entity.setArea(area);
//            entity.setDepartment(projectGroup);
//            entity.setYear(year);
//            entity.setMonth(month);
//            super.save(entity);
//        }
//        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
//        BeanUtils.copyProperties(dto, voucherGenerateDTO);
//        List<IncomeCostAnalysis> incomeCostAnalysisS = super.findByCis(dto);
//        List<PartBO> partBOS = voucherGenerateAPI.findByMoney(voucherGenerateDTO);
//        List<IncomeCostAnalysisBO> incomeCostAnalysisBOS = BeanTransform.copyProperties(partBOS, IncomeCostAnalysisBO.class);
//        //出车司机数
//        List<DriverDispatchsBO> driver = dispatchCarInfoAPI.findDispatchs(voucherGenerateDTO.getArea(), voucherGenerateDTO.getProjectGroup(), voucherGenerateDTO.getYear(), voucherGenerateDTO.getMonth());
//        //司机出车费
//        List<DriverDispatchFeeBO> driverFee = dispatchCarInfoAPI.findDispatchFree(voucherGenerateDTO.getArea(), voucherGenerateDTO.getProjectGroup(), voucherGenerateDTO.getYear(), voucherGenerateDTO.getMonth());
//        List<IncomeCostAnalysisBO> boList = new ArrayList<>();
////        for (IncomeCostAnalysisBO incomeCostAnalysisBO : incomeCostAnalysisBOS) {
//        if (incomeCostAnalysisBOS != null && !incomeCostAnalysisBOS.isEmpty()) {
//            IncomeCostAnalysisBO incomeCostAnalysisBO = BeanTransform.copyProperties(incomeCostAnalysisS.get(0), IncomeCostAnalysisBO.class);
//            if (!driver.isEmpty()) {
//                incomeCostAnalysisBO.setCarNum(driver.get(0).getSum());
//            } else {
//                incomeCostAnalysisBO.setCarNum(0);
//            }
//            if (!driverFee.isEmpty()) {
//                incomeCostAnalysisBO.setDriverFee(driverFee.get(0).getFee());
//            } else {
//                incomeCostAnalysisBO.setDriverFee(0.0);
//            }
//            IncomeCostAnalysisBO bo = incomeCostAnalysisBOS.get(0);
//            incomeCostAnalysisBO.setOilRecharge(bo.getOilRecharge());
//            incomeCostAnalysisBO.setRent(bo.getRent());
//            incomeCostAnalysisBO.setSocialSecurity(bo.getSocialSecurity());
//            incomeCostAnalysisBO.setStaffWage(bo.getStaffWage());
//            incomeCostAnalysisBO.setOffice(bo.getOffice());
//            incomeCostAnalysisBO.setMarketCost(bo.getMarketCost());
//            incomeCostAnalysisBO.setTax(bo.getTax());
//            double total = incomeCostAnalysisBO.getDriverFee() + bo.getOilRecharge()
//                    + bo.getRent() + bo.getSocialSecurity() + bo.getStaffWage()
//                    + bo.getOffice() + bo.getMarketCost() + bo.getTax();
//            incomeCostAnalysisBO.setTotal(total);
//
//            if (null != incomeCostAnalysisBO.getIncomeAfterTax()) {
//                incomeCostAnalysisBO.setBalance(incomeCostAnalysisBO.getIncomeAfterTax() - total);
//            }
//            boList.add(incomeCostAnalysisBO);
//        }
//        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void insertIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
        IncomeCostAnalysis incomeCostAnalysis =
                BeanTransform.copyProperties(incomeCostAnalysisTO, IncomeCostAnalysis.class, true);
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
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void editIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
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
        super.update(incomeCostAnalysis);
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
    public List<CollectBO> collect(CollectTO to) throws SerException {
        IncomeCostAnalysisDTO dto = new IncomeCostAnalysisDTO();
        if (null != to.getArea()) {
            dto.setArea(to.getArea());
        }
        if (null != to.getDepartment()) {
            dto.setProjectGroup(to.getDepartment());
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
            bo.setDate(String.valueOf(model.getYear() + "-" + model.getMonth()));
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