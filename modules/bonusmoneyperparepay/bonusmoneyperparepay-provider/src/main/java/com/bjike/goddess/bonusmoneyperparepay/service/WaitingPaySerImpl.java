package com.bjike.goddess.bonusmoneyperparepay.service;

import com.bjike.goddess.bonusmoneyperparepay.bo.PerpareActualDifferencesBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingPayBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.WaitingPayDTO;
import com.bjike.goddess.bonusmoneyperparepay.entity.WaitingPay;
import com.bjike.goddess.bonusmoneyperparepay.excel.AlreadyPayExcel;
import com.bjike.goddess.bonusmoneyperparepay.excel.SonPermissionObject;
import com.bjike.goddess.bonusmoneyperparepay.excel.WaitingPayExcel;
import com.bjike.goddess.bonusmoneyperparepay.to.GuidePermissionTO;
import com.bjike.goddess.bonusmoneyperparepay.type.GuideAddrStatus;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 等待付款业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 等待付款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bonusmoneyperparepaySerCache")
@Service
public class WaitingPaySerImpl extends ServiceImpl<WaitingPay, WaitingPayDTO> implements WaitingPaySer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private MoneyPerpareSer moneyPerpareSer;

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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagMoneyPerpare = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("moneyperpare");
        obj.setDescribesion("奖金资金准备");
        if (flagMoneyPerpare) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagWaitPay = moneyPerpareSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("waitingpay");
        obj.setDescribesion("等待付款");
        if (flagWaitPay) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAlreadyPay = moneyPerpareSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("alreadypay");
        obj.setDescribesion("已付款");
        if (flagAlreadyPay) {
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
    public Long countWaiting(WaitingPayDTO waitingPayDTO) throws SerException {
        waitingPayDTO.getConditions().add(Restrict.eq("turntable", "否"));
        List<WaitingPay> waitingPays = super.findByCis(waitingPayDTO);
        Long count = (long) waitingPays.size();
        return count;
    }

    @Override
    public Long countAlready(WaitingPayDTO waitingPayDTO) throws SerException {
        searchCondition(waitingPayDTO);
        waitingPayDTO.getConditions().add(Restrict.eq("turntable", "是"));
        List<WaitingPay> waitingPays = super.findByCis(waitingPayDTO);
        Long count = (long) waitingPays.size();
        return count;
    }

    @Override
    public WaitingPayBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        WaitingPay waitingPay = super.findById(id);
        return BeanTransform.copyProperties(waitingPay, WaitingPayBO.class);
    }

    @Override
    public List<WaitingPayBO> listWaiting(WaitingPayDTO waitingPayDTO) throws SerException {
        checkPermission();
        waitingPayDTO.getConditions().add(Restrict.eq("payStatus", "等待付款"));
        searchCondition(waitingPayDTO);
        List<WaitingPay> list = super.findByPage(waitingPayDTO);
        List<WaitingPayBO> waitingPayBOList = BeanTransform.copyProperties(list, WaitingPayBO.class);
        return waitingPayBOList;
    }

    @Override
    public List<WaitingPayBO> list(WaitingPayDTO waitingPayDTO) throws SerException {
        checkPermission();
        searchCondition(waitingPayDTO);
        waitingPayDTO.getConditions().add(Restrict.eq("payStatus", "已付款"));
        List<WaitingPay> list = super.findByPage(waitingPayDTO);
        List<WaitingPayBO> waitingPayBOList = BeanTransform.copyProperties(list, WaitingPayBO.class);
        return waitingPayBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteWaiting(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    public void searchCondition(WaitingPayDTO waitingPayDTO) throws SerException {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        if (StringUtils.isNotBlank(waitingPayDTO.getStartDifference())) {
            start = DateUtil.parseDateTime(waitingPayDTO.getStartDifference());
        }
        if (StringUtils.isNotBlank(waitingPayDTO.getEndDifference())) {
            end = DateUtil.parseDateTime(waitingPayDTO.getEndDifference());
        }
        LocalDateTime[] diffe = new LocalDateTime[]{start, end};
        if (StringUtils.isNotBlank(waitingPayDTO.getStartDifference()) || StringUtils.isNotBlank(waitingPayDTO.getEndDifference())) {
            waitingPayDTO.getConditions().add(Restrict.between("differenceTime", diffe));
        }
        if (StringUtils.isNotBlank(waitingPayDTO.getProjectGroup())) {
            waitingPayDTO.getConditions().add(Restrict.eq("projectGroup", waitingPayDTO.getProjectGroup()));
        }
    }

    @Override
    public void payMoney(String id, Double payMoney) throws SerException {
        checkPermission();
        UserBO userbo = userAPI.currentUser();
        WaitingPay model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setPayStatus("已付款");
        model.setTurntable("是");
        model.setDifferenceTime(LocalDateTime.now());
        model.setPayAuthor(userbo.getUsername());
        model.setPayMoney(payMoney);
        super.update(model);
    }


    @Override
    public List<WaitingBO> projectCompare(Integer years, Integer month, String[] projectGroup) throws SerException {
        checkPermission();
        List<WaitingBO> list = new ArrayList<>();
        if (projectGroup == null || projectGroup.length == 0) {
            throw new SerException("项目组不能为空");
        } else {
            for (String project : projectGroup) {
                WaitingPayDTO dto = new WaitingPayDTO();
                Integer year = LocalDateTime.now().getYear();
                if (years != null) {
                    year = years;
                }
                if (month != null) {
                    dto.getConditions().add(Restrict.eq("years", year));
                    dto.getConditions().add(Restrict.eq("month", month));
                }
                dto.getConditions().add(Restrict.eq("projectGroup", project));
                dto.getConditions().add(Restrict.eq("turntable", "是"));
                List<WaitingPay> waitingPays = super.findByCis(dto);
                if (waitingPays != null && waitingPays.size() > 0) {
                    Double payMoney = 0d;
                    Double reserve = 0d;
                    for (WaitingPay waitingPay : waitingPays) {
                        WaitingBO waitingBO = new WaitingBO();
                        waitingBO.setYearsMonth(waitingPay.getYears() + "-" + (waitingPay.getMonth() > 10 ? waitingPay.getMonth() : "0" + waitingPay.getMonth()));//时间
                        waitingBO.setProjectGroup(project);//项目组
                        waitingBO.setPayStatus(waitingPay.getPayStatus());//付款状态
                        waitingBO.setSubjects(waitingPay.getSubjects());//科目
                        waitingBO.setReserve(waitingPay.getReserve());//准备金
                        waitingBO.setPayMoney(waitingPay.getPayMoney());//支付金额
                        waitingBO.setDifference(waitingPay.getDifferenceTime().toString());//付款时间

                        payMoney += waitingPay.getPayMoney();
                        reserve += waitingPay.getReserve();
                        list.add(waitingBO);
                    }

                    WaitingBO waitingBO = new WaitingBO();
                    waitingBO.setYearsMonth("合计");//时间
                    waitingBO.setPayMoney(payMoney);//支付金额
                    waitingBO.setReserve(reserve);//准备金额

                    list.add(waitingBO);
                }
            }
        }
        return list;
    }

    @Override
    public List<WaitingBO> yearsCompare(Integer years) throws SerException {
        checkPermission();
        List<WaitingBO> list = new ArrayList<>();
        WaitingPayDTO dto = new WaitingPayDTO();
        if (years != null) {
            dto.getConditions().add(Restrict.eq("years", years));
        } else {
            dto.getConditions().add(Restrict.eq("years", LocalDateTime.now().getYear()));
        }
        dto.getConditions().add(Restrict.eq("turntable", "是"));
        List<WaitingPay> waitingPays = super.findByCis(dto);
        if (waitingPays != null && waitingPays.size() > 0) {
            Double payMoney = 0d;
            Double reserve = 0d;
            for (WaitingPay waitingPay : waitingPays) {
                WaitingBO waitingBO = new WaitingBO();
                waitingBO.setYearsMonth(waitingPay.getYears() + "-" + (waitingPay.getMonth() > 10 ? waitingPay.getMonth() : "0" + waitingPay.getMonth()));//时间
                waitingBO.setProjectGroup(waitingPay.getProjectGroup());//项目组
                waitingBO.setPayStatus(waitingPay.getPayStatus());//付款状态
                waitingBO.setSubjects(waitingPay.getSubjects());//科目
                waitingBO.setReserve(waitingPay.getReserve());//准备金
                waitingBO.setPayMoney(waitingPay.getPayMoney());//支付金额
                waitingBO.setDifference(waitingPay.getDifferenceTime().toString());//付款时间

                payMoney += waitingPay.getPayMoney();
                reserve += waitingPay.getReserve();
                list.add(waitingBO);
            }

            WaitingBO waitingBO = new WaitingBO();
            waitingBO.setYearsMonth("合计");//时间
            waitingBO.setPayMoney(payMoney);//支付金额
            waitingBO.setReserve(reserve);//准备金额

            list.add(waitingBO);
            ;
        }
        return list;
    }

    @Override
    public List<WaitingBO> monthCompare(Integer years, Integer month) throws SerException {
        checkPermission();
        List<WaitingBO> list = new ArrayList<>();
        WaitingPayDTO dto = new WaitingPayDTO();
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
        dto.getConditions().add(Restrict.eq("turntable", "是"));
        List<WaitingPay> waitingPays = super.findByCis(dto);
        if (waitingPays != null && waitingPays.size() > 0) {
            Double payMoney = 0d;
            Double reserve = 0d;
            for (WaitingPay waitingPay : waitingPays) {
                WaitingBO waitingBO = new WaitingBO();
                waitingBO.setYearsMonth(waitingPay.getYears() + "-" + (waitingPay.getMonth() > 10 ? waitingPay.getMonth() : "0" + waitingPay.getMonth()));//时间
                waitingBO.setProjectGroup(waitingPay.getProjectGroup());//项目组
                waitingBO.setPayStatus(waitingPay.getPayStatus());//付款状态
                waitingBO.setSubjects(waitingPay.getSubjects());//科目
                waitingBO.setReserve(waitingPay.getReserve());//准备金
                waitingBO.setPayMoney(waitingPay.getPayMoney());//支付金额
                waitingBO.setDifference(waitingPay.getDifferenceTime().toString());//付款时间

                payMoney += waitingPay.getPayMoney();
                reserve += waitingPay.getReserve();
                list.add(waitingBO);
            }

            WaitingBO waitingBO = new WaitingBO();
            waitingBO.setYearsMonth("合计");//时间
            waitingBO.setPayMoney(payMoney);//支付金额
            waitingBO.setReserve(reserve);//准备金额

            list.add(waitingBO);
            ;
        }
        return list;
    }

    @Override
    public byte[] exportExcel(Integer years,Integer startMonth, Integer endMonth) throws SerException {

        Integer start = LocalDate.now().getMonthValue();
        Integer end = LocalDate.now().getMonthValue();
        if(years == null){
            years = LocalDateTime.now().getYear();
        }
        if (startMonth != null) {
            start = startMonth;
        }
        if (endMonth != null) {
            end = endMonth;
        }

        WaitingPayDTO waitingPayDTO = new WaitingPayDTO();
        if (startMonth != null || endMonth != null) {
            waitingPayDTO.getConditions().add(Restrict.eq("years", years));
            waitingPayDTO.getConditions().add(Restrict.between("month", new Integer[]{start, end}));
        }
        waitingPayDTO.getConditions().add(Restrict.eq("turntable", "否"));
        List<WaitingPay> list = super.findByCis(waitingPayDTO);

        List<WaitingPayExcel> waitingPayExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            WaitingPayExcel excel = BeanTransform.copyProperties(str, WaitingPayExcel.class);
            excel.setYearsMonth(str.getYears() + "-" + (str.getMonth() > 10 ? str.getMonth() : "0" + str.getMonth()));
            waitingPayExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(waitingPayExcels, excel);
        return bytes;
    }

    @Override
    public byte[] exportArealdyExcel(Integer years,Integer startMonth, Integer endMonth) throws SerException {

        Integer start = LocalDate.now().getMonthValue();
        Integer end = LocalDate.now().getMonthValue();
        if (startMonth != null) {
            start = startMonth;
        }
        if (endMonth != null) {
            end = endMonth;
        }
        if(years == null){
            years = LocalDateTime.now().getYear();
        }

        WaitingPayDTO waitingPayDTO = new WaitingPayDTO();
        if (startMonth != null || endMonth != null) {
            waitingPayDTO.getConditions().add(Restrict.eq("years", years));
            waitingPayDTO.getConditions().add(Restrict.between("month", new Integer[]{start, end}));
        }
        waitingPayDTO.getConditions().add(Restrict.eq("turntable", "是"));
        List<WaitingPay> list = super.findByCis(waitingPayDTO);

        List<AlreadyPayExcel> alreadyPayExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            AlreadyPayExcel excel = BeanTransform.copyProperties(str, AlreadyPayExcel.class);
            excel.setYearsMonth(str.getYears() + "-" + (str.getMonth() > 10 ? str.getMonth() : "0" + str.getMonth()));
            alreadyPayExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(alreadyPayExcels, excel);
        return bytes;
    }

    @Override
    public List<PerpareActualDifferencesBO> differencesCompare(Integer years, Integer month) throws SerException {
        if (years == null) {
            years = LocalDateTime.now().getYear();
        }
        List<PerpareActualDifferencesBO> moneyPerpareContrastBOList = new ArrayList<>();
        List<String> projectGroup = findAllProject();
        WaitingPayDTO dto = new WaitingPayDTO();
        dto.getConditions().add(Restrict.eq("turntable", "是"));
        List<WaitingPay> waitingPays = super.findByCis(dto);
        Double reserveSum = 0d;
        Double payMoneySum = 0d;
        for (String project : projectGroup) {
            for (WaitingPay waitingPay : waitingPays) {
                if (waitingPay.getProjectGroup().equals(project) && waitingPay.getYears().equals(years) && waitingPay.getMonth().equals(month)) {
                    reserveSum += waitingPay.getReserve();
                    payMoneySum += waitingPay.getPayMoney();
                }
            }

            if ((reserveSum != 0) || (payMoneySum != 0)) {
                PerpareActualDifferencesBO perpareActualDifferencesBO = new PerpareActualDifferencesBO();
                perpareActualDifferencesBO.setYears(years);
                perpareActualDifferencesBO.setMonth(month);
                perpareActualDifferencesBO.setProjectGroup(project);
                perpareActualDifferencesBO.setSubjects("奖金");
                perpareActualDifferencesBO.setThisMonthReserve(reserveSum);
                perpareActualDifferencesBO.setActualPay(payMoneySum);
                perpareActualDifferencesBO.setDifference(reserveSum - payMoneySum);
                if (payMoneySum == 0) {
                    perpareActualDifferencesBO.setGrowthRate(0d);
                } else {
                    perpareActualDifferencesBO.setGrowthRate(((reserveSum - payMoneySum) / payMoneySum * 100));
                }

                moneyPerpareContrastBOList.add(perpareActualDifferencesBO);

                reserveSum = 0d;
                payMoneySum = 0d;
            }

        }
        return moneyPerpareContrastBOList;
    }

    @Override
    public List<String> findAllProject() throws SerException {
        WaitingPayDTO dto = new WaitingPayDTO();
        dto.getConditions().add(Restrict.eq("turntable", "是"));
        List<WaitingPay> list = super.findByCis(dto);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (WaitingPay model : list) {
            String project = model.getProjectGroup();
            if (StringUtils.isNotBlank(model.getProjectGroup())) {
                set.add(project);
            }
        }
        return new ArrayList<>(set);
    }
}