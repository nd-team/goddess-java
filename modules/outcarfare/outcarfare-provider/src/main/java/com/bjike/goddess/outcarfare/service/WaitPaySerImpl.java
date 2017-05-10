package com.bjike.goddess.outcarfare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.outcarfare.bo.*;
import com.bjike.goddess.outcarfare.dto.WaitPayDTO;
import com.bjike.goddess.outcarfare.entity.WaitPay;
import com.bjike.goddess.outcarfare.to.WaitPayTO;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 等待付款业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 03:11 ]
 * @Description: [ 等待付款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "outcarfareSerCache")
@Service
public class WaitPaySerImpl extends ServiceImpl<WaitPay, WaitPayDTO> implements WaitPaySer {
    @Override
    public WaitPayBO save(WaitPayTO to) throws SerException {
        //TODO:查找所有等待付款的人
        WaitPay waitPay = BeanTransform.copyProperties(to, WaitPay.class, true);
        super.save(waitPay);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Override
    public void pay(WaitPayTO to) throws SerException {
        WaitPay waitPay=super.findById(to.getId());
        waitPay.setIsPay(to.getIsPay());
        super.update(waitPay);
    }

    @Override
    public void delete(String id) throws SerException {

    }

    @Override
    public List<WaitPayBO> list(WaitPayDTO dto) throws SerException {
        List<WaitPay> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, WaitPayBO.class);
    }

    @Override
    public WaitPayBO findByID(String id) throws SerException {
        WaitPay waitPay = super.findById(id);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Override
    public List<DriverCountBO> driverCount() throws SerException {
        WaitPayDTO dto = new WaitPayDTO();
        List<PayBO> list=findAlreadyPays();
        Set<String> drivers = findAllDrivers();
        Set<Double> prices = findAllPrices();
        List<DriverCountBO> boList = new ArrayList<DriverCountBO>();
        Double overtimeFeeSum = 0.00;
        Double allowanceSum = 0.00;
        Double parkFeeSum = 0.00;
        Double amountSum = 0.00;
        for (String driver : drivers) {
            for (Double price : prices) {
                for (PayBO w : list) {
                    if (w.getDriverName().equals(driver) && w.getCarPrice().equals(price)) {
                        overtimeFeeSum += w.getOvertimeFee();
                        allowanceSum += w.getAllowance();
                        parkFeeSum += w.getParkFee();
                        amountSum += w.getAmount();
                    }
                }
                if (amountSum != 0) {
                    DriverCountBO bo = new DriverCountBO();
                    bo.setDriverName(driver);
                    bo.setCarPrice(price);
                    bo.setOvertimeFeeSum(overtimeFeeSum);
                    bo.setAllowanceSum(allowanceSum);
                    bo.setParkFeeSum(parkFeeSum);
                    bo.setAmountSum(amountSum);
                    boList.add(bo);
                    overtimeFeeSum = 0.00;
                    allowanceSum = 0.00;
                    parkFeeSum = 0.00;
                    amountSum = 0.00;      //置为0
                }
            }
        }
        return boList;
    }

    @Override
    public List<ArrivalCountBO> arrivalCount() throws SerException {
        WaitPayDTO dto = new WaitPayDTO();
        List<PayBO> list=findAlreadyPays();
        Set<String> arrivals = findAllArrivals();
        Set<Double> prices = findAllPrices();
        List<ArrivalCountBO> boList = new ArrayList<ArrivalCountBO>();
        Double overtimeFeeSum = 0.00;
        Double allowanceSum = 0.00;
        Double parkFeeSum = 0.00;
        Double amountSum = 0.00;
        for (String arrival : arrivals) {
            for (Double price : prices) {
                for (PayBO w : list) {
                    if (w.getArrival().equals(arrival) && w.getCarPrice().equals(price)) {
                        overtimeFeeSum += w.getOvertimeFee();
                        allowanceSum += w.getAllowance();
                        parkFeeSum += w.getParkFee();
                        amountSum += w.getAmount();
                    }
                }
                if (amountSum != 0) {
                    ArrivalCountBO bo = new ArrivalCountBO();
                    bo.setArrival(arrival);
                    bo.setCarPrice(price);
                    bo.setOvertimeFeeSum(overtimeFeeSum);
                    bo.setAllowanceSum(allowanceSum);
                    bo.setParkFeeSum(parkFeeSum);
                    bo.setAmountSum(amountSum);
                    boList.add(bo);
                    overtimeFeeSum = 0.00;
                    allowanceSum = 0.00;
                    parkFeeSum = 0.00;
                    amountSum = 0.00;      //置为0
                }
            }
        }
        return boList;
    }

    @Override
    public List<CarUserCountBO> carUserCount() throws SerException {
        WaitPayDTO dto = new WaitPayDTO();
        List<PayBO> list=findAlreadyPays();
        Set<String> carUsers = findAllCarUsers();
        Set<Double> prices = findAllPrices();
        List<CarUserCountBO> boList = new ArrayList<CarUserCountBO>();
        Double overtimeFeeSum = 0.00;
        Double allowanceSum = 0.00;
        Double parkFeeSum = 0.00;
        Double amountSum = 0.00;
        for (String carUser : carUsers) {
            for (Double price : prices) {
                for (PayBO w : list) {
                    if (w.getCarUser().equals(carUser) && w.getCarPrice().equals(price)) {
                        overtimeFeeSum += w.getOvertimeFee();
                        allowanceSum += w.getAllowance();
                        parkFeeSum += w.getParkFee();
                        amountSum += w.getAmount();
                    }
                }
                if (amountSum != 0) {
                    CarUserCountBO bo = new CarUserCountBO();
                    bo.setCarUser(carUser);
                    bo.setCarPrice(price);
                    bo.setOvertimeFeeSum(overtimeFeeSum);
                    bo.setAllowanceSum(allowanceSum);
                    bo.setParkFeeSum(parkFeeSum);
                    bo.setAmountSum(amountSum);
                    boList.add(bo);
                    overtimeFeeSum = 0.00;
                    allowanceSum = 0.00;
                    parkFeeSum = 0.00;
                    amountSum = 0.00;      //置为0
                }
            }
        }
        return boList;
    }

    @Override
    public List<PayBO> findAlreadyPays() throws SerException {
        List<WaitPay> list = super.findAll();
        List<PayBO> boList = new ArrayList<PayBO>();
        for (WaitPay w : list) {
            if (w.getIsPay()) {
                PayBO bo = new PayBO();
                BeanUtils.copyProperties(w, bo);
                bo.setHavePay(true);
                boList.add(bo);
            }
        }
        return boList;
    }

    /**
     * 查找所有司机
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllDrivers() throws SerException {
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getDriverName());
        }
        return set;
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllArrivals() throws SerException {
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getArrival());
        }
        return set;
    }

    /**
     * 查找所有用车人
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllCarUsers() throws SerException {
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getCarUser());
        }
        return set;
    }

    /**
     * 查找所有租车单价
     *
     * @return class Double
     * @throws SerException
     */
    private Set<Double> findAllPrices() throws SerException {
        List<WaitPay> list = super.findAll();
        Set<Double> set = new HashSet<Double>();
        for (WaitPay w : list) {
            set.add(w.getCarPrice());
        }
        return set;
    }
}