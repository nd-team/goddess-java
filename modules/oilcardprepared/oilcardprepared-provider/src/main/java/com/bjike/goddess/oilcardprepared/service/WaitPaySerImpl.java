package com.bjike.goddess.oilcardprepared.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.bo.DispatchCarInfoBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardRechargeBO;
import com.bjike.goddess.oilcardprepared.bo.ContrastBO;
import com.bjike.goddess.oilcardprepared.bo.WaitPayBO;
import com.bjike.goddess.oilcardprepared.dto.WaitPayDTO;
import com.bjike.goddess.oilcardprepared.entity.WaitPay;
import com.bjike.goddess.oilcardprepared.to.WaitPayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 等待付款业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-12 11:05 ]
 * @Description: [ 等待付款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "oilcardpreparedSerCache")
@Service
public class WaitPaySerImpl extends ServiceImpl<WaitPay, WaitPayDTO> implements WaitPaySer {
    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;

    @Override
    public WaitPayBO findByID(String id) throws SerException {
        WaitPay waitPay = super.findById(id);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Override
    public void pay(WaitPayTO to) throws SerException {
        WaitPay waitPay = super.findById(to.getId());
        waitPay.setPay(to.getPay());
        super.update(waitPay);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<WaitPayBO> list(WaitPayDTO dto) throws SerException {
        List<DispatchCarInfoBO> list = dispatchCarInfoAPI.allWaitPay();
        List<WaitPay> waitPays = super.findAll();
        if (list != null) {
            for (DispatchCarInfoBO v : list) {
                if (waitPays.size() == 0) {
                    WaitPay waitPay = new WaitPay();
                    waitPay.setWaitId(v.getId());
                    waitPay.setOilCardCode(v.getOilCardNumber());
                    waitPay.setOilCardBalance(v.getOilCardBalance());
                    OilCardBasicBO oilCardBasicBO = findOilCardBasic(v.getOilCardNumber());
                    waitPay.setOilCardNumber(oilCardBasicBO.getOilCardNumber());
                    OilCardRechargeBO oilCardRechargeBO = findOilCardRecharge(oilCardBasicBO.getId());
                    waitPay.setRechargeDate(DateUtil.parseDateTime(oilCardRechargeBO.getRechargeDate()));
                    waitPay.setMonth(DateUtil.parseDateTime(oilCardRechargeBO.getRechargeDate()).getMonthValue());
                    waitPay.setYear(DateUtil.parseDateTime(oilCardRechargeBO.getRechargeDate()).getYear());
                    waitPay.setRechargeMoney(oilCardRechargeBO.getRechargeMoney());
                    waitPay.setRechargeUser(oilCardRechargeBO.getRechargeUser());
                    waitPay.setRechargeWay(oilCardRechargeBO.getRechargeWay());
                    super.save(waitPay);
                } else {
                    boolean b1 = true;
                    for (WaitPay p : waitPays) {
                        if (p.getWaitId().equals(v.getId())) {
                            b1 = false;
                        }
                    }
                    if (b1) {
                        WaitPay waitPay = new WaitPay();
                        waitPay.setWaitId(v.getId());
                        waitPay.setOilCardCode(v.getOilCardNumber());
                        waitPay.setOilCardBalance(v.getOilCardBalance());
                        OilCardBasicBO oilCardBasicBO = findOilCardBasic(v.getOilCardNumber());
                        waitPay.setOilCardNumber(oilCardBasicBO.getOilCardNumber());
                        OilCardRechargeBO oilCardRechargeBO = findOilCardRecharge(oilCardBasicBO.getId());
                        waitPay.setRechargeDate(DateUtil.parseDateTime(oilCardRechargeBO.getRechargeDate()));
                        waitPay.setMonth(DateUtil.parseDateTime(oilCardRechargeBO.getRechargeDate()).getMonthValue());
                        waitPay.setYear(DateUtil.parseDateTime(oilCardRechargeBO.getRechargeDate()).getYear());
                        waitPay.setRechargeMoney(oilCardRechargeBO.getRechargeMoney());
                        waitPay.setRechargeUser(oilCardRechargeBO.getRechargeUser());
                        waitPay.setRechargeWay(oilCardRechargeBO.getRechargeWay());
                        super.save(waitPay);
                    }
                }
            }
        }
        for (WaitPay p : super.findAll()) {
            DispatchCarInfoBO v = dispatchCarInfoAPI.findById(p.getWaitId());
            if (v == null || v.getPay()) {
                super.remove(p.getId());
            }
        }
        List<WaitPay> l = super.findByCis(dto, true);
        return BeanTransform.copyProperties(l, WaitPayBO.class);
    }

    @Override
    public List<WaitPayBO> count(String startTime, String endTime) throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> all = new ArrayList<WaitPay>();
        WaitPayDTO dto = new WaitPayDTO();
        List<WaitPayBO> boList = new ArrayList<WaitPayBO>();
        LocalDate[] time = null;
        Double sum = 0.00;
        try {
            LocalDate s = DateUtil.parseDate(startTime);
            LocalDate e = DateUtil.parseDate(endTime);
            time = new LocalDate[]{s, e};
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        dto.getConditions().add(Restrict.between("rechargeDate", time));
        for (WaitPay w : super.findByCis(dto)) {
            if (w.getPay()) {
                all.add(w);
            }
        }
        for (String oilCardCode : allOilCardCodes()) {
//            for (String oilCardNumber : allOilCardNumbers()) {
//                for (String rechargeUser : allRechargeUsers()) {
//                    for (String rechargeWay : allRechargeWays()) {
//                        for (Double oilCardBalance : allOilCardBalances()) {
            for (WaitPay w : all) {
                boolean b1 = oilCardCode.equals(w.getOilCardCode());
//                                boolean b2 = oilCardNumber.equals(w.getOilCardNumber());
//                                boolean b3 = rechargeUser.equals(w.getRechargeUser());
//                                boolean b4 = rechargeWay.equals(w.getRechargeWay());
//                                boolean b5 = oilCardBalance.equals(w.getOilCardBalance());
                if (b1) {
                    sum += w.getRechargeMoney();
                    WaitPayBO bo = new WaitPayBO();
                    bo.setOilCardCode(oilCardCode);
                    bo.setOilCardNumber(w.getOilCardNumber());
                    bo.setRechargeDate(DateUtil.dateToString(w.getRechargeDate()));
                    bo.setRechargeUser(w.getRechargeUser());
                    bo.setRechargeWay(w.getRechargeWay());
                    bo.setOilCardBalance(w.getOilCardBalance());
                    bo.setPay(true);
                    boList.add(bo);
                }
            }
            if (sum != 0) {
                WaitPayBO bo = new WaitPayBO();
                bo.setRechargeMoneySum(sum);
                boList.add(bo);
                sum = 0.00;    //置为0
            }
//                        }
//                    }
//                }
//            }
        }
        return boList;
    }

    @Override
    public List<ContrastBO> contrast(Integer month) throws SerException {
        if (month < 1 || month > 12) {
            throw new SerException("月份不合法");
        }
        List<WaitPayBO> all = allPay();
        List<ContrastBO> boList = new ArrayList<ContrastBO>();
        Double currentSum = 0.00;
        Double lastSum = 0.00;
        if (month != 1) {
            Integer m = month - 1;
            Integer year = LocalDate.now().getYear();
            for (String oilCardNumber : allOilCardNumbers()) {
                for (WaitPayBO w : all) {
                    if (oilCardNumber.equals(w.getOilCardNumber()) && year.equals(w.getYear()) && month.equals(w.getMonth())) {
                        currentSum += w.getRechargeMoney();
                    }
                    if (oilCardNumber.equals(w.getOilCardNumber()) && year.equals(w.getYear()) && m.equals(w.getMonth())) {
                        lastSum += w.getRechargeMoney();
                    }
                }
                if ((currentSum != 0) || (lastSum != 0)) {
                    ContrastBO bo = new ContrastBO();
                    bo.setMonth(month);
                    bo.setOilCardNumber(oilCardNumber);
                    bo.setCurrentSum(currentSum);
                    bo.setLastSum(lastSum);
                    bo.setDifferences(currentSum - lastSum);
                    if (lastSum != 0) {
                        bo.setGrowthRate((currentSum - lastSum) / lastSum);
                    } else {
                        bo.setGrowthRate(1.00);
                    }
                    boList.add(bo);
                    currentSum = 0.00;
                    lastSum = 0.00;
                }
            }
            return boList;
        } else {
            Integer year = LocalDate.now().getYear();
            for (String oilCardNumber : allOilCardNumbers()) {
                for (WaitPayBO w : all) {
                    if (oilCardNumber.equals(w.getOilCardNumber()) && year.equals(w.getYear()) && month.equals(w.getMonth())) {
                        currentSum += w.getRechargeMoney();
                    }
                    if (oilCardNumber.equals(w.getOilCardNumber()) && ((year - 1) == w.getYear()) && (w.getMonth() == 12)) {
                        lastSum += w.getRechargeMoney();
                    }
                }
                if ((currentSum != 0) || (lastSum != 0)) {
                    ContrastBO bo = new ContrastBO();
                    bo.setMonth(month);
                    bo.setOilCardNumber(oilCardNumber);
                    bo.setCurrentSum(currentSum);
                    bo.setLastSum(lastSum);
                    bo.setDifferences(currentSum - lastSum);
                    if (lastSum != 0) {
                        bo.setGrowthRate((currentSum - lastSum) / lastSum);
                    } else {
                        bo.setGrowthRate(1.00);
                    }
                    boList.add(bo);
                    currentSum = 0.00;
                    lastSum = 0.00;
                }
            }
            return boList;
        }
    }

    /**
     * 通过油卡编号查找油卡信息
     *
     * @param oilCardCode 油卡编号
     * @return class OilCardBasicBO
     * @throws SerException
     */
    private OilCardBasicBO findOilCardBasic(String oilCardCode) throws SerException {
        String[] oilCardCodes = new String[]{oilCardCode};
        List<OilCardBasicBO> list = null;
        for (String s : oilCardCodes) {
            String[] fields = new String[]{"id", "oilCardNumber"};
            String sql = "SELECT id,oilCardNumber\n" +
                    "FROM oilcardmanage_basic\n" +
                    "where oilCardCode='" + s + "'";
            list = super.findBySql(sql, OilCardBasicBO.class, fields);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过油卡信息Id查找油卡充值记录
     *
     * @param oilCardBasicId 油卡信息Id
     * @return class OilCardRechargeBO
     * @throws SerException
     */
    private OilCardRechargeBO findOilCardRecharge(String oilCardBasicId) throws SerException {
        String[] oilCardBasicIds = new String[]{oilCardBasicId};
        List<OilCardRechargeBO> list = null;
        for (String s : oilCardBasicIds) {
            String[] fields = new String[]{"rechargeDate", "rechargeMoney", "rechargeUser", "rechargeWay"};
            String sql = "SELECT rechargeDate,rechargeMoney,rechargeUser,rechargeWay\n" +
                    "from oilcardmanage_recharge\n" +
                    "WHERE oilCardBasicId='" + s + "'";
            list = super.findBySql(sql, OilCardRechargeBO.class, fields);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<WaitPayBO> allPay() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        List<WaitPay> l = new ArrayList<WaitPay>();
        for (WaitPay w : list) {
            if (w.getPay()) {
                l.add(w);
            }
        }
        return BeanTransform.copyProperties(l, WaitPayBO.class);
    }

    /**
     * 查找所有油卡编号
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allOilCardCodes() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getOilCardCode());
        }
        return set;
    }

    /**
     * 查找所有卡号
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allOilCardNumbers() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getOilCardNumber());
        }
        return set;
    }

    /**
     * 查找所有充值人
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allRechargeUsers() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getRechargeUser());
        }
        return set;
    }

    /**
     * 查找所有充值方式
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allRechargeWays() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getRechargeWay());
        }
        return set;
    }

    /**
     * 查找所有初始余额
     *
     * @return class Double
     * @throws SerException
     */
    private Set<Double> allOilCardBalances() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<Double> set = new HashSet<Double>();
        for (WaitPay w : list) {
            set.add(w.getOilCardBalance());
        }
        return set;
    }

    /**
     * 查找所有年份
     *
     * @return class Integer
     * @throws SerException
     */
    private Set<Integer> allYears() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (WaitPay w : list) {
            set.add(w.getYear());
        }
        return set;
    }

    /**
     * 查找所有月份
     *
     * @return class Integer
     * @throws SerException
     */
    private Set<Integer> allMonths() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (WaitPay w : list) {
            set.add(w.getMonth());
        }
        return set;
    }
}