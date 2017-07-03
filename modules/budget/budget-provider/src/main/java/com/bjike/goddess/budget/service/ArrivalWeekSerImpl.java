package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.ArrivalWeekBO;
import com.bjike.goddess.budget.bo.ArrivalWeekCountBO;
import com.bjike.goddess.budget.dto.ArrivalWeekDTO;
import com.bjike.goddess.budget.entity.ArrivalWeek;
import com.bjike.goddess.budget.enums.GuideAddrStatus;
import com.bjike.goddess.budget.to.ArrivalMonthTO;
import com.bjike.goddess.budget.to.ArrivalWeekTO;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.vo.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 地区收入周业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:03 ]
 * @Description: [ 地区收入周业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "budgetSerCache")
@Service
public class ArrivalWeekSerImpl extends ServiceImpl<ArrivalWeek, ArrivalWeekDTO> implements ArrivalWeekSer {
    @Autowired
    private ArrivalMonthSer arrivalMonthSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ProjectMonthSer projectMonthSer;
    @Autowired
    private ProjectWeekSer projectWeekSer;
    @Autowired
    private WarnSer warnSer;

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
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("arrivalweek");
        obj.setDescribesion("地区收入周");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = arrivalMonthSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("arrivalmonth");
        obj.setDescribesion("地区收入月");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = projectMonthSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectmonth");
        obj.setDescribesion("项目收入月");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = projectWeekSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectweek");
        obj.setDescribesion("项目收入周");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase = warnSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("warn");
        obj.setDescribesion("预警");
        if (flagSeeBase) {
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
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case DETAIL:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ArrivalWeekBO save(ArrivalWeekTO to) throws SerException {
        checkAddIdentity();
        ArrivalWeek arrivalWeek = BeanTransform.copyProperties(to, ArrivalWeek.class, true);
        super.save(arrivalWeek);
        arrivalMonthSer.deleteAll();
        List<ArrivalWeekCountBO> list = count();
        for (ArrivalWeekCountBO bo : list) {
            ArrivalMonthTO arrivalMonthTO = new ArrivalMonthTO();
            arrivalMonthTO.setArrival(bo.getArrival());
            arrivalMonthTO.setYear(bo.getYear());
            arrivalMonthTO.setMonth(bo.getMonth());
            arrivalMonthTO.setTargetWork(bo.getTargetWorkSum());
            arrivalMonthTO.setActualWork(bo.getActualWorkSum());
            arrivalMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            arrivalMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            arrivalMonthTO.setPlanIncome(bo.getPlanIncome());
            arrivalMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            Double planIncome = bo.getPlanIncome();
            Double targetIncome = bo.getTargetIncomeSum();
            Double scale = planIncome / targetIncome;
            arrivalMonthTO.setScale(scale);
            arrivalMonthSer.save(arrivalMonthTO);
        }
        return BeanTransform.copyProperties(arrivalWeek, ArrivalWeekBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ArrivalWeekTO to) throws SerException {
        checkAddIdentity();
        ArrivalWeek arrivalWeek = super.findById(to.getId());
        LocalDateTime a = arrivalWeek.getCreateTime();
        LocalDateTime b = arrivalWeek.getModifyTime();
        arrivalWeek = BeanTransform.copyProperties(to, ArrivalWeek.class, true);
        arrivalWeek.setCreateTime(a);
        arrivalWeek.setModifyTime(b);
        super.update(arrivalWeek);
        arrivalMonthSer.deleteAll();
        List<ArrivalWeekCountBO> list = count();
        for (ArrivalWeekCountBO bo : list) {
            ArrivalMonthTO arrivalMonthTO = new ArrivalMonthTO();
            arrivalMonthTO.setArrival(bo.getArrival());
            arrivalMonthTO.setYear(bo.getYear());
            arrivalMonthTO.setMonth(bo.getMonth());
            arrivalMonthTO.setTargetWork(bo.getTargetWorkSum());
            arrivalMonthTO.setActualWork(bo.getActualWorkSum());
            arrivalMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            arrivalMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            arrivalMonthTO.setPlanIncome(bo.getPlanIncome());
            arrivalMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            Double planIncome = bo.getPlanIncome();
            Double targetIncome = bo.getTargetIncomeSum();
            Double scale = planIncome / targetIncome;
            arrivalMonthTO.setScale(scale);
            arrivalMonthSer.save(arrivalMonthTO);
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        arrivalMonthSer.deleteAll();
        List<ArrivalWeekCountBO> list = count();
        for (ArrivalWeekCountBO bo : list) {
            ArrivalMonthTO arrivalMonthTO = new ArrivalMonthTO();
            arrivalMonthTO.setArrival(bo.getArrival());
            arrivalMonthTO.setYear(bo.getYear());
            arrivalMonthTO.setMonth(bo.getMonth());
            arrivalMonthTO.setTargetWork(bo.getTargetWorkSum());
            arrivalMonthTO.setActualWork(bo.getActualWorkSum());
            arrivalMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            arrivalMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            arrivalMonthTO.setPlanIncome(bo.getPlanIncome());
            arrivalMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            Double planIncome = bo.getPlanIncome();
            Double targetIncome = bo.getTargetIncomeSum();
            Double scale = planIncome / targetIncome;
            arrivalMonthTO.setScale(scale);
            arrivalMonthSer.save(arrivalMonthTO);
        }
    }

    @Override
    public List<ArrivalWeekBO> list(ArrivalWeekDTO dto) throws SerException {
        checkSeeIdentity();
        List<ArrivalWeek> list = super.findByCis(dto, true);
        List<ArrivalWeekBO> boList = new ArrayList<ArrivalWeekBO>();
        for (ArrivalWeek a : list) {
            ArrivalWeekBO bo = BeanTransform.copyProperties(a, ArrivalWeekBO.class);
            bo.setWorkDifferences(a.getActualWork() - a.getTargetWork());
            bo.setIncomeDifferences(a.getPlanIncome() - a.getTargetIncome());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public ArrivalWeekBO findByID(String id) throws SerException {
        ArrivalWeek arrivalWeek = super.findById(id);
        ArrivalWeekBO bo = BeanTransform.copyProperties(arrivalWeek, ArrivalWeekBO.class);
        bo.setWorkDifferences(arrivalWeek.getActualWork() - arrivalWeek.getTargetWork());
        bo.setIncomeDifferences(arrivalWeek.getPlanIncome() - arrivalWeek.getTargetIncome());
        return bo;
    }

    @Override
    public List<ArrivalWeekCountBO> count() throws SerException {
        checkSeeIdentity();
        List<ArrivalWeekCountBO> boList = new ArrayList<ArrivalWeekCountBO>();
        ArrivalWeekDTO dto = new ArrivalWeekDTO();
        List<ArrivalWeek> list = super.findByCis(dto);
        List<String> arrivals = findAllArrivals();
        List<Integer> years = findAllYears();
        List<Integer> months = findAllMonths();
        List<Double> prices = findAllPrices();
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        for (String arrival : arrivals) {
            for (Integer year : years) {
                for (Integer month : months) {
                    //     for (Double price : prices) {
                    for (ArrivalWeek a : list) {
                        boolean b1 = a.getArrival().equals(arrival);
                        int year1 = a.getYear();
                        boolean b2 = year1 == year;
                        boolean b3 = a.getMonth() == month;
                        //           Double price1 = a.getPrice();
//                        int aa = price1.compareTo(price);
//                        boolean b4 = aa == 0 ? true : false;
                        if (b1 && b2 && b3/* && b4*/) {
                            targetIncomeSum += a.getTargetIncome();
                            planIncomeSum += a.getPlanIncome();
                            double incomeDifference = a.getPlanIncome() - a.getTargetIncome();
                            incomeDifferencesSum += incomeDifference;
                            targetWorkSum += a.getTargetWork();
                            actualWorkSum += a.getActualWork();
                            int workDifference = a.getActualWork() - a.getTargetWork();
                            workDifferencesSum += workDifference;
                        }
                    }
                    if (targetWorkSum != 0) {
                        ArrivalWeekCountBO arrivalWeekCountBO = new ArrivalWeekCountBO();
                        arrivalWeekCountBO.setArrival(arrival);
                        arrivalWeekCountBO.setYear(year);
                        arrivalWeekCountBO.setMonth(month);
                        //       arrivalWeekCountBO.setPrice(price);
                        arrivalWeekCountBO.setTargetWorkSum(targetWorkSum);
                        arrivalWeekCountBO.setActualWorkSum(actualWorkSum);
                        arrivalWeekCountBO.setWorkDifferencesSum(workDifferencesSum);
                        arrivalWeekCountBO.setTargetIncomeSum(targetIncomeSum);
                        arrivalWeekCountBO.setPlanIncome(planIncomeSum);
                        arrivalWeekCountBO.setIncomeDifferencesSum(incomeDifferencesSum);
                        boList.add(arrivalWeekCountBO);
                        targetWorkSum = 0;
                        actualWorkSum = 0;
                        workDifferencesSum = 0;
                        targetIncomeSum = 0.00;
                        planIncomeSum = 0.00;
                        incomeDifferencesSum = 0.00;     //置为0
                    }
                }
                //   }
            }
        }
        return boList;
    }

    @Override
    public List<ArrivalWeekCountBO> conditionsCount(ArrivalWeekDTO dto1) throws SerException {
        checkSeeIdentity();
        List<ArrivalWeekCountBO> boList = new ArrayList<ArrivalWeekCountBO>();
        List<Integer> years = findAllYears();
        List<Integer> months = findAllMonths();
        List<Double> prices = findAllPrices();
        List<ArrivalWeek> list = null;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        String[] arrivals = dto1.getArrivals();
        if (arrivals != null) {
            for (String arrival : arrivals) {
                ArrivalWeekDTO dto = new ArrivalWeekDTO();
                dto.getConditions().add(Restrict.eq("arrival", arrival));
                list = super.findByCis(dto);
                //    for (Double price : prices) {
                for (Integer year : years) {
                    for (Integer month : months) {
                        for (ArrivalWeek a : list) {
//                            Double price1 = a.getPrice();
//                            int aa = price1.compareTo(price);
//                            boolean b = aa == 0 ? true : false;
                            if (/*b &&*/ a.getYear().equals(year) && a.getMonth().equals(month)) {
                                targetIncomeSum += a.getTargetIncome();
                                planIncomeSum += a.getPlanIncome();
                                double incomeDifference = a.getPlanIncome() - a.getTargetIncome();
                                incomeDifferencesSum += incomeDifference;
                                targetWorkSum += a.getTargetWork();
                                actualWorkSum += a.getActualWork();
                                int workDifference = a.getActualWork() - a.getTargetWork();
                                workDifferencesSum += workDifference;
                            }
                        }
                        if (targetWorkSum != 0) {
                            ArrivalWeekCountBO arrivalWeekCountBO = new ArrivalWeekCountBO();
                            arrivalWeekCountBO.setArrival(arrival);
                            //        arrivalWeekCountBO.setPrice(price);
                            arrivalWeekCountBO.setYear(year);
                            arrivalWeekCountBO.setMonth(month);
                            arrivalWeekCountBO.setTargetWorkSum(targetWorkSum);
                            arrivalWeekCountBO.setActualWorkSum(actualWorkSum);
                            arrivalWeekCountBO.setWorkDifferencesSum(workDifferencesSum);
                            arrivalWeekCountBO.setTargetIncomeSum(targetIncomeSum);
                            arrivalWeekCountBO.setPlanIncome(planIncomeSum);
                            arrivalWeekCountBO.setIncomeDifferencesSum(incomeDifferencesSum);
                            boList.add(arrivalWeekCountBO);
                            targetWorkSum = 0;
                            actualWorkSum = 0;
                            workDifferencesSum = 0;
                            targetIncomeSum = 0.00;
                            planIncomeSum = 0.00;
                            incomeDifferencesSum = 0.00;     //置为0
                        }
                    }
                }
                //}
            }
        }
        return boList;
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    @Override
    public List<String> findAllArrivals() throws SerException {
        List<ArrivalWeek> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ArrivalWeek a : list) {
            set.add(a.getArrival());
        }
        List<String> l = new ArrayList<String>(set);
        return l;
    }

    /**
     * 查找所有年份
     *
     * @return class Integer
     * @throws SerException
     */
    private List<Integer> findAllYears() throws SerException {
        List<ArrivalWeek> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (ArrivalWeek a : list) {
            set.add(a.getYear());
        }
        List<Integer> l = new ArrayList<Integer>(set);
        return l;
    }

    /**
     * 查找所有月份
     *
     * @return class Integer
     * @throws SerException
     */
    private List<Integer> findAllMonths() throws SerException {
        List<ArrivalWeek> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (ArrivalWeek a : list) {
            set.add(a.getMonth());
        }
        List<Integer> l = new ArrayList<Integer>(set);
        return l;
    }

    /**
     * 查找所有的单价
     *
     * @return class Double
     * @throws SerException
     */
    private List<Double> findAllPrices() throws SerException {
        List<ArrivalWeek> list = super.findAll();
        Set<Double> set = new HashSet<Double>();
        for (ArrivalWeek a : list) {
            set.add(a.getPrice());
        }
        List<Double> l = new ArrayList<Double>(set);
        return l;
    }

    @Override
    public Long countNum(ArrivalWeekDTO dto) throws SerException {
        return super.count(dto);
    }
}