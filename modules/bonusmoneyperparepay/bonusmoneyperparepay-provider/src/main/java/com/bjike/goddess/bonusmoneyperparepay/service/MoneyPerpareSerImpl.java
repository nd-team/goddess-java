package com.bjike.goddess.bonusmoneyperparepay.service;

import com.bjike.goddess.bonusmoneyperparepay.bo.MoneyPerpareBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.MoneyPerpareContrastBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.PerpareBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.MoneyPerpareDTO;
import com.bjike.goddess.bonusmoneyperparepay.dto.WaitingPayDTO;
import com.bjike.goddess.bonusmoneyperparepay.entity.MoneyPerpare;
import com.bjike.goddess.bonusmoneyperparepay.entity.WaitingPay;
import com.bjike.goddess.bonusmoneyperparepay.to.GuidePermissionTO;
import com.bjike.goddess.bonusmoneyperparepay.to.MoneyPerpareTO;
import com.bjike.goddess.bonusmoneyperparepay.type.GuideAddrStatus;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 奖金资金准备与支付业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 04:44 ]
 * @Description: [ 奖金资金准备与支付业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bonusmoneyperparepaySerCache")
@Service
public class MoneyPerpareSerImpl extends ServiceImpl<MoneyPerpare, MoneyPerpareDTO> implements MoneyPerpareSer {

    @Autowired
    private WaitingPaySer waitingPaySer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countMoney(MoneyPerpareDTO moneyPerpareDTO) throws SerException {
        searchCondition(moneyPerpareDTO);
        Long count = super.count(moneyPerpareDTO);
        return count;
    }

    @Override
    public MoneyPerpareBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        MoneyPerpare moneyPerpare = super.findById(id);
        return BeanTransform.copyProperties(moneyPerpare, MoneyPerpareBO.class);
    }

    @Override
    public List<MoneyPerpareBO> listMoneyPerpare(MoneyPerpareDTO moneyPerpareDTO) throws SerException {
        checkPermission();
        searchCondition(moneyPerpareDTO);
        List<MoneyPerpare> list = super.findByPage(moneyPerpareDTO);
        List<MoneyPerpareBO> baseInfoManageBOList = BeanTransform.copyProperties(list, MoneyPerpareBO.class);
        return baseInfoManageBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyPerpareBO addMoneyPerpare(MoneyPerpareTO moneyPerpareTO) throws SerException {
        checkPermission();
        MoneyPerpare moneyPerpare = BeanTransform.copyProperties(moneyPerpareTO, MoneyPerpare.class, true);
        moneyPerpare.setCreateTime(LocalDateTime.now());
        moneyPerpare = super.save(moneyPerpare);
        WaitingPay waitingPay = new WaitingPay();
        BeanUtils.copyProperties(moneyPerpare, waitingPay);
        waitingPay.setCreateTime(LocalDateTime.now());
        waitingPay.setPayStatus("等待付款");
        waitingPay.setTurntable("否");
        waitingPay.setPerpareId(moneyPerpare.getId());
        waitingPaySer.save(waitingPay);
        return BeanTransform.copyProperties(moneyPerpare, MoneyPerpareBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyPerpareBO editMoneyPerpare(MoneyPerpareTO moneyPerpareTO) throws SerException {
        checkPermission();
        MoneyPerpare moneyPerpare = super.findById(moneyPerpareTO.getId());
        BeanTransform.copyProperties(moneyPerpareTO, moneyPerpare, true);
        moneyPerpare.setModifyTime(LocalDateTime.now());
        super.update(moneyPerpare);
        WaitingPayDTO dto = new WaitingPayDTO();
        dto.getConditions().add(Restrict.eq("perpareId", moneyPerpare.getId()));
        List<WaitingPay> waitingPays = waitingPaySer.findByCis(dto);
        WaitingPay waitingPay = waitingPays.get(0);
        if (waitingPay.getPayStatus().equals("等待付款")) {
            BeanTransform.copyProperties(moneyPerpareTO, waitingPay, true, "id");
            waitingPaySer.update(waitingPay);
        }
        return BeanTransform.copyProperties(moneyPerpare, MoneyPerpareBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteMoneyPerpare(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    public void searchCondition(MoneyPerpareDTO moneyPerpareDTO) throws SerException {
        Integer start = LocalDate.now().getMonthValue();
        Integer end = LocalDate.now().getMonthValue();
        if (moneyPerpareDTO.getStartMonth() != null) {
            start = moneyPerpareDTO.getStartMonth();
        }
        if (moneyPerpareDTO.getEndMonth() != null) {
            end = moneyPerpareDTO.getEndMonth();
        }
        if (moneyPerpareDTO.getStartMonth() != null || moneyPerpareDTO.getStartMonth() != null) {
            moneyPerpareDTO.getConditions().add(Restrict.between("month", new Integer[]{start, end}));
        }
    }

    @Override
    public List<PerpareBO> projectCompare(Integer years, Integer month, String[] projectGroup) throws SerException {
        checkPermission();
        List<PerpareBO> list = new ArrayList<>();
        if (projectGroup == null || projectGroup.length == 0) {
            throw new SerException("项目组不能为空");
        } else {
            for (String project : projectGroup) {
                MoneyPerpareDTO dto = new MoneyPerpareDTO();
                Integer year = LocalDateTime.now().getYear();
                if (years != null) {
                    year = years;
                }
                if (month != null) {
                    dto.getConditions().add(Restrict.eq("years", year));
                    dto.getConditions().add(Restrict.eq("month", month));
                }
                dto.getConditions().add(Restrict.eq("projectGroup", projectGroup));
                List<MoneyPerpare> moneyPerpares = super.findByCis(dto);
                if (moneyPerpares != null && moneyPerpares.size() > 0) {
                    Double totalReserve = 0d;
                    Double reserve = 0d;
                    for (MoneyPerpare moneyPerpare : moneyPerpares) {
                        PerpareBO perpareBO = new PerpareBO();
                        perpareBO.setYearsMoth(moneyPerpare.getYears() + "-" + (moneyPerpare.getMonth() > 10 ? moneyPerpare.getMonth() : "0" + moneyPerpare.getMonth()));//时间
                        perpareBO.setProjectGroup(project);//项目组
                        perpareBO.setSubjects(moneyPerpare.getSubjects());//科目
                        perpareBO.setTotalReserve(moneyPerpare.getTotalReserve());//总准备金
                        perpareBO.setReserve(moneyPerpare.getReserve());//准备金

                        totalReserve += moneyPerpare.getTotalReserve();
                        reserve += moneyPerpare.getReserve();
                        list.add(perpareBO);
                    }

                    PerpareBO perpareBO = new PerpareBO();
                    perpareBO.setYearsMoth("合计");//时间
                    perpareBO.setTotalReserve(totalReserve);
                    perpareBO.setReserve(reserve);
                    list.add(perpareBO);
                }
            }
        }
        return list;
    }

    @Override
    public List<PerpareBO> monthCompare(Integer years, Integer month) throws SerException {
        checkPermission();
        List<PerpareBO> list = new ArrayList<>();
        MoneyPerpareDTO dto = new MoneyPerpareDTO();
        Integer year = LocalDateTime.now().getYear();
        if (years != null) {
            year = years;
        }
        if (month == null) {
            month = LocalDateTime.now().getMonthValue();
        }
        if (month != null) {
            dto.getConditions().add(Restrict.eq("years", year));
            dto.getConditions().add(Restrict.eq("month", month));
        }
        List<MoneyPerpare> moneyPerpares = super.findByCis(dto);
        if (moneyPerpares != null && moneyPerpares.size() > 0) {
            Double totalReserve = 0d;
            Double reserve = 0d;
            for (MoneyPerpare moneyPerpare : moneyPerpares) {
                PerpareBO perpareBO = new PerpareBO();
                perpareBO.setYearsMoth(moneyPerpare.getYears() + "-" + (moneyPerpare.getMonth() > 10 ? moneyPerpare.getMonth() : "0" + moneyPerpare.getMonth()));//时间
                perpareBO.setProjectGroup(moneyPerpare.getProjectGroup());//项目组
                perpareBO.setSubjects(moneyPerpare.getSubjects());//科目
                perpareBO.setTotalReserve(moneyPerpare.getTotalReserve());//总准备金
                perpareBO.setReserve(moneyPerpare.getReserve());//准备金

                totalReserve += moneyPerpare.getTotalReserve();
                reserve += moneyPerpare.getReserve();
                list.add(perpareBO);
            }

            PerpareBO perpareBO = new PerpareBO();
            perpareBO.setYearsMoth("合计");//时间
            perpareBO.setTotalReserve(totalReserve);
            perpareBO.setReserve(reserve);
            list.add(perpareBO);
        }
        return list;
    }

    @Override
    public List<PerpareBO> yearsCompare(Integer years) throws SerException {
        checkPermission();
        List<PerpareBO> list = new ArrayList<>();
        MoneyPerpareDTO dto = new MoneyPerpareDTO();
        if (years != null) {
            dto.getConditions().add(Restrict.eq("years", years));
        } else {
            dto.getConditions().add(Restrict.eq("years", LocalDateTime.now().getYear()));
        }
        List<MoneyPerpare> moneyPerpares = super.findByCis(dto);
        if (moneyPerpares != null && moneyPerpares.size() > 0) {
            Double totalReserve = 0d;
            Double reserve = 0d;
            for (MoneyPerpare moneyPerpare : moneyPerpares) {
                PerpareBO perpareBO = new PerpareBO();
                perpareBO.setYearsMoth(moneyPerpare.getYears() + "-" + (moneyPerpare.getMonth() > 10 ? moneyPerpare.getMonth() : "0" + moneyPerpare.getMonth()));//时间
                perpareBO.setProjectGroup(moneyPerpare.getProjectGroup());//项目组
                perpareBO.setSubjects(moneyPerpare.getSubjects());//科目
                perpareBO.setTotalReserve(moneyPerpare.getTotalReserve());//总准备金
                perpareBO.setReserve(moneyPerpare.getReserve());//准备金

                totalReserve += moneyPerpare.getTotalReserve();
                reserve += moneyPerpare.getReserve();
                list.add(perpareBO);
            }

            PerpareBO perpareBO = new PerpareBO();
            perpareBO.setYearsMoth("合计");//时间
            perpareBO.setTotalReserve(totalReserve);
            perpareBO.setReserve(reserve);
            list.add(perpareBO);
        }
        return list;
    }

    @Override
    public List<String> findAllProject() throws SerException {
        List<MoneyPerpare> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MoneyPerpare model : list) {
            String project = model.getProjectGroup();
            if (StringUtils.isNotBlank(model.getProjectGroup())) {
                set.add(project);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<MoneyPerpareContrastBO> contrastCompare(Integer years, Integer month) throws SerException {
        checkPermission();
        if (years == null) {
            years = LocalDateTime.now().getYear();
        }
        List<MoneyPerpareContrastBO> moneyPerpareContrastBOList = new ArrayList<>();
        List<String> projectGroup = findAllProject();
        MoneyPerpareDTO dto = new MoneyPerpareDTO();
        List<MoneyPerpare> moneyPerpareList = super.findByCis(dto);
        Double reserveSum = 0d;
        Double lastReserveSum = 0d;

        if (month != 1) {
            for (String project : projectGroup) {
                for (MoneyPerpare perpare : moneyPerpareList) {
                    if (perpare.getProjectGroup().equals(project) && perpare.getYears().equals(years) && perpare.getMonth().equals(month)) {
                        reserveSum += perpare.getReserve();
                    }
                    if (perpare.getProjectGroup().equals(project) && perpare.getYears().equals(years) && perpare.getMonth().equals(month - 1)) {
                        lastReserveSum += perpare.getReserve();
                    }
                }

                if ((reserveSum != 0) || (lastReserveSum != 0)) {
                    MoneyPerpareContrastBO moneyPerpareContrastBO = new MoneyPerpareContrastBO();
                    moneyPerpareContrastBO.setYears(LocalDateTime.now().getYear());
                    moneyPerpareContrastBO.setMonth(month);
                    moneyPerpareContrastBO.setProjectGroup(project);
                    moneyPerpareContrastBO.setSubjects("奖金");
                    moneyPerpareContrastBO.setThisMonthReserve(reserveSum);
                    moneyPerpareContrastBO.setLastMonthReserve(lastReserveSum);
                    moneyPerpareContrastBO.setDifference(reserveSum - lastReserveSum);
                    if (lastReserveSum == 0) {
                        moneyPerpareContrastBO.setGrowthRate(0d);
                    } else {
                        moneyPerpareContrastBO.setGrowthRate(((reserveSum - lastReserveSum) / lastReserveSum * 100));
                    }

                    moneyPerpareContrastBOList.add(moneyPerpareContrastBO);

                    reserveSum = 0d;
                    lastReserveSum = 0d;
                }

            }
        } else {
            for (String project : projectGroup) {
                for (MoneyPerpare perpare : moneyPerpareList) {
                    if (perpare.getProjectGroup().equals(project) && perpare.getYears().equals(years) && perpare.getMonth().equals(month)) {
                        reserveSum += perpare.getReserve();
                    }
                    if (perpare.getProjectGroup().equals(project) && perpare.getYears().equals(years - 1) && perpare.getMonth().equals(12)) {
                        lastReserveSum += perpare.getReserve();
                    }
                }
                if ((reserveSum != 0) || (lastReserveSum != 0)) {
                    MoneyPerpareContrastBO moneyPerpareContrastBO = new MoneyPerpareContrastBO();
                    moneyPerpareContrastBO.setYears(LocalDateTime.now().getYear());
                    moneyPerpareContrastBO.setMonth(month);
                    moneyPerpareContrastBO.setProjectGroup(project);
                    moneyPerpareContrastBO.setSubjects("奖金");
                    moneyPerpareContrastBO.setThisMonthReserve(reserveSum);
                    moneyPerpareContrastBO.setLastMonthReserve(lastReserveSum);
                    moneyPerpareContrastBO.setDifference(reserveSum - lastReserveSum);
                    if (lastReserveSum == 0) {
                        moneyPerpareContrastBO.setGrowthRate(0d);
                    } else {
                        moneyPerpareContrastBO.setGrowthRate(((reserveSum - lastReserveSum) / lastReserveSum * 100));
                    }

                    moneyPerpareContrastBOList.add(moneyPerpareContrastBO);

                    reserveSum = 0d;
                    lastReserveSum = 0d;
                }

            }

        }

        return moneyPerpareContrastBOList;
    }


}