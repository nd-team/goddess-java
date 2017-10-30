package com.bjike.goddess.outcarfare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.bo.DispatchCarInfoBO;
import com.bjike.goddess.dispatchcar.enums.FindType;
import com.bjike.goddess.outcarfare.bo.ArrivalCountBO;
import com.bjike.goddess.outcarfare.bo.CarUserCountBO;
import com.bjike.goddess.outcarfare.bo.DriverCountBO;
import com.bjike.goddess.outcarfare.bo.WaitPayBO;
import com.bjike.goddess.outcarfare.dto.WaitPayDTO;
import com.bjike.goddess.outcarfare.entity.WaitPay;
import com.bjike.goddess.outcarfare.enums.GuideAddrStatus;
import com.bjike.goddess.outcarfare.to.GuidePermissionTO;
import com.bjike.goddess.outcarfare.to.WaitPayTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
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
            case pay:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WaitPayBO save(WaitPayTO to) throws SerException {
        checkAddIdentity();
        WaitPay waitPay = BeanTransform.copyProperties(to, WaitPay.class, true);
        waitPay.setIsDel(false);
        super.save(waitPay);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WaitPayBO edit(WaitPayTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            WaitPay waitPay = super.findById(to.getId());
            BeanTransform.copyProperties(to, waitPay, true);
            waitPay.setIsDel(false);
            waitPay.setModifyTime(LocalDateTime.now());
            super.update(waitPay);
            return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
        } else {
            throw new SerException("id不能为空");
        }

    }

    @Override
    public void pay(WaitPayTO to) throws SerException {
        checkAddIdentity();
        WaitPay waitPay = super.findById(to.getId());
        waitPay.setIsPay(to.getIsPay());
        super.update(waitPay);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            WaitPay waitPay = super.findById(id);
            waitPay.setIsDel(true);
            waitPay.setDelTime(LocalDate.now());
            waitPay.setModifyTime(LocalDateTime.now());
            super.update(waitPay);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<WaitPayBO> delList(WaitPayDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("delTime=asc");
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isDel", Boolean.FALSE));
        List<WaitPay> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, WaitPayBO.class);
    }

    @Override
    public void reback(String id) throws SerException {
        checkAddIdentity();
        WaitPay entity = super.findById(id);
        entity.setDelTime(null);
        entity.setIsDel(false);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public Long delCount(WaitPayDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isDel", Boolean.FALSE));
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void quartz() throws SerException {
        WaitPayDTO dto = new WaitPayDTO();
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isDel", Boolean.FALSE));
        List<WaitPay> list = super.findByCis(dto);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long now = simpleDateFormat.parse(DateUtil.dateToString(LocalDate.now())).getTime();
            for (WaitPay m : list) {
                LocalDate a = m.getDelTime().plusDays(30);
                long delTime = simpleDateFormat.parse(DateUtil.dateToString(a)).getTime();
                if (now - delTime >= 0) {
                    super.remove(m);
                }
            }
        } catch (ParseException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<WaitPayBO> list(WaitPayDTO dto) throws SerException {
        checkSeeIdentity();
        String userToken = RpcTransmit.getUserToken();
        List<DispatchCarInfoBO> list = dispatchCarInfoAPI.allWaitPay();
        List<WaitPay> waitPays = super.findAll();
        if (list != null) {
            for (DispatchCarInfoBO v : list) {
                if (waitPays.size() == 0) {
                    WaitPay waitPay = new WaitPay();
                    BeanUtils.copyProperties(v, waitPay);
                    waitPay.setDriverName(v.getDriver());
                    waitPay.setCarDate(v.getDispatchDate());
                    waitPay.setNumber(v.getNumber());
                    waitPay.setArrival(v.getArea());
//                    waitPay.setCarPrice(v.getCarRentalCost());
                    waitPay.setAcctype(v.getAcctype());
                    waitPay.setOvertimeHour((double) v.getOverWorkTime());
                    waitPay.setOvertimeFee(v.getOverWorkCost());
                    waitPay.setAllowance(v.getMealCost());
//                    waitPay.setOvertimePrice(v.getCarRentalCost() / 8);
                    waitPay.setParkFee(v.getParkCost() + v.getRoadCost());
                    waitPay.setAmount(v.getCost());
                    waitPay.setDispatchCarInfoId(v.getId());
                    waitPay.setIsDel(false);
                    waitPay.setIsPay(false);
                    super.save(waitPay);
                } else {
                    boolean b1 = true;
                    for (WaitPay p : waitPays) {
                        if (null != p.getDispatchCarInfoId()) {
                            if (p.getDispatchCarInfoId().equals(v.getId())) {
                                LocalDateTime a = p.getCreateTime();
                                String id = p.getId();
                                BeanUtils.copyProperties(v, p);
                                p.setId(id);
                                p.setCreateTime(a);
                                p.setModifyTime(LocalDateTime.now());
                                p.setDriverName(v.getDriver());
                                p.setCarDate(v.getDispatchDate());
                                p.setNumber(v.getNumber());
                                p.setArrival(v.getArea());
                                p.setAcctype(v.getAcctype());
//                                p.setCarPrice(v.getCarRentalCost());
                                p.setOvertimeHour((double) v.getOverWorkTime());
                                p.setOvertimeFee(v.getOverWorkCost());
                                p.setAllowance(v.getMealCost());
                                p.setParkFee(v.getParkCost() + v.getRoadCost());
                                p.setAmount(v.getCost());
//                                p.setOvertimePrice(v.getCarRentalCost() / 8);
                                p.setDispatchCarInfoId(v.getId());
                                p.setDispatchCarInfoId(v.getId());
                                super.update(p);
                                b1 = false;
                            }
                        }

                    }
                    if (b1) {
                        WaitPay waitPay = new WaitPay();
                        BeanUtils.copyProperties(v, waitPay);
                        waitPay.setDispatchCarInfoId(v.getId());
                        waitPay.setDriverName(v.getDriver());
                        waitPay.setCarDate(v.getDispatchDate());
                        waitPay.setNumber(v.getNumber());
                        waitPay.setArrival(v.getArea());
                        waitPay.setAcctype(v.getAcctype());
//                        waitPay.setCarPrice(v.getCarRentalCost());
                        waitPay.setOvertimeHour((double) v.getOverWorkTime());
                        waitPay.setOvertimeFee(v.getOverWorkCost());
                        waitPay.setAllowance(v.getMealCost());
                        waitPay.setParkFee(v.getParkCost() + v.getRoadCost());
                        waitPay.setAmount(v.getCost());
//                        waitPay.setOvertimePrice(v.getCarRentalCost() / 8);
                        waitPay.setDispatchCarInfoId(v.getId());
                        waitPay.setIsDel(false);
                        waitPay.setIsPay(false);
                        super.save(waitPay);
                    }
                }
            }
        }
        for (WaitPay p : super.findAll()) {
            if (null != p.getDispatchCarInfoId()) {
                DispatchCarInfoBO v = dispatchCarInfoAPI.findById(p.getDispatchCarInfoId());
//                if (v == null || (!FindType.WAITPAY.equals(v.getFindType())) || v.getPay()) {
//                    super.remove(p.getId());
//                }
            }
        }
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        List<WaitPay> l = super.findByCis(dto, true);
        RpcTransmit.transmitUserToken(userToken);
        return BeanTransform.copyProperties(l, WaitPayBO.class);
    }

    @Override
    public WaitPayBO findByID(String id) throws SerException {
        WaitPay waitPay = super.findById(id);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Override
    public List<DriverCountBO> driverCount(WaitPayDTO dto) throws SerException {
//        list(dto);
        String[] drivers = dto.getDrivers();
        dto.getConditions().add(Restrict.in("driverName", drivers));
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.FALSE));
        List<WaitPay> list = super.findByCis(dto);
        List<DriverCountBO> boList = new ArrayList<>();
        double overtimeFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
        double allowanceSum = list.stream().mapToDouble((WaitPay w) -> w.getAllowance()).sum();
        double parkFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getParkFee()).sum();
        double amountSum = list.stream().mapToDouble((WaitPay w) -> w.getAmount()).sum();
        for (String driverName : drivers) {
            double overtimeFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
            double allowance = list.stream().filter(waitPay -> driverName.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
            double parkFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
            double amount = list.stream().filter(waitPay -> driverName.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
            DriverCountBO bo = new DriverCountBO();
            bo.setDriverName(driverName);
            bo.setOvertimeFeeSum(overtimeFee);
            bo.setAllowanceSum(allowance);
            bo.setParkFeeSum(parkFee);
            bo.setAmountSum(amount);
            boList.add(bo);
        }
        DriverCountBO bo = new DriverCountBO();
        bo.setDriverName("合计");
        bo.setOvertimeFeeSum(overtimeFeeSum);
        bo.setAllowanceSum(allowanceSum);
        bo.setParkFeeSum(parkFeeSum);
        bo.setAmountSum(amountSum);
        boList.add(bo);
        return boList;
    }

    @Override
    public List<ArrivalCountBO> arrivalCount(WaitPayDTO dto) throws SerException {
//        list(dto);
        String[] drivers = dto.getArrivals();
        dto.getConditions().add(Restrict.in("arrival", drivers));
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.FALSE));
        List<WaitPay> list = super.findByCis(dto);
        List<ArrivalCountBO> boList = new ArrayList<>();
        double overtimeFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
        double allowanceSum = list.stream().mapToDouble((WaitPay w) -> w.getAllowance()).sum();
        double parkFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getParkFee()).sum();
        double amountSum = list.stream().mapToDouble((WaitPay w) -> w.getAmount()).sum();
        for (String driverName : drivers) {
            double overtimeFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
            double allowance = list.stream().filter(waitPay -> driverName.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
            double parkFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
            double amount = list.stream().filter(waitPay -> driverName.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
            ArrivalCountBO bo = new ArrivalCountBO();
            bo.setArrival(driverName);
            bo.setOvertimeFeeSum(overtimeFee);
            bo.setAllowanceSum(allowance);
            bo.setParkFeeSum(parkFee);
            bo.setAmountSum(amount);
            boList.add(bo);
        }
        ArrivalCountBO bo = new ArrivalCountBO();
        bo.setArrival("合计");
        bo.setOvertimeFeeSum(overtimeFeeSum);
        bo.setAllowanceSum(allowanceSum);
        bo.setParkFeeSum(parkFeeSum);
        bo.setAmountSum(amountSum);
        boList.add(bo);
        return boList;
    }

    @Override
    public List<CarUserCountBO> carUserCount(WaitPayDTO dto) throws SerException {
//        list(dto);
        String[] drivers = dto.getCarUsers();
        dto.getConditions().add(Restrict.in("carUser", drivers));
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.FALSE));
        List<WaitPay> list = super.findByCis(dto);
        List<CarUserCountBO> boList = new ArrayList<>();
        double overtimeFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
        double allowanceSum = list.stream().mapToDouble((WaitPay w) -> w.getAllowance()).sum();
        double parkFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getParkFee()).sum();
        double amountSum = list.stream().mapToDouble((WaitPay w) -> w.getAmount()).sum();
        for (String driverName : drivers) {
            double overtimeFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
            double allowance = list.stream().filter(waitPay -> driverName.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
            double parkFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
            double amount = list.stream().filter(waitPay -> driverName.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
            CarUserCountBO bo = new CarUserCountBO();
            bo.setCarUser(driverName);
            bo.setOvertimeFeeSum(overtimeFee);
            bo.setAllowanceSum(allowance);
            bo.setParkFeeSum(parkFee);
            bo.setAmountSum(amount);
            boList.add(bo);
        }
        CarUserCountBO bo = new CarUserCountBO();
        bo.setCarUser("合计");
        bo.setOvertimeFeeSum(overtimeFeeSum);
        bo.setAllowanceSum(allowanceSum);
        bo.setParkFeeSum(parkFeeSum);
        bo.setAmountSum(amountSum);
        boList.add(bo);
        return boList;
    }

    @Override
    public List<DriverCountBO> waitDriverCount(WaitPayDTO dto) throws SerException {
        list(dto);
        String[] drivers = dto.getDrivers();
        dto.getConditions().add(Restrict.in("driverName", drivers));
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        List<WaitPay> list = super.findByCis(dto);
        List<DriverCountBO> boList = new ArrayList<>();
        double overtimeFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
        double allowanceSum = list.stream().mapToDouble((WaitPay w) -> w.getAllowance()).sum();
        double parkFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getParkFee()).sum();
        double amountSum = list.stream().mapToDouble((WaitPay w) -> w.getAmount()).sum();
        for (String driverName : drivers) {
            double overtimeFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
            double allowance = list.stream().filter(waitPay -> driverName.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
            double parkFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
            double amount = list.stream().filter(waitPay -> driverName.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
            DriverCountBO bo = new DriverCountBO();
            bo.setDriverName(driverName);
            bo.setOvertimeFeeSum(overtimeFee);
            bo.setAllowanceSum(allowance);
            bo.setParkFeeSum(parkFee);
            bo.setAmountSum(amount);
            boList.add(bo);
        }
        DriverCountBO bo = new DriverCountBO();
        bo.setDriverName("合计");
        bo.setOvertimeFeeSum(overtimeFeeSum);
        bo.setAllowanceSum(allowanceSum);
        bo.setParkFeeSum(parkFeeSum);
        bo.setAmountSum(amountSum);
        boList.add(bo);
        return boList;
    }

    @Override
    public List<ArrivalCountBO> waitArrivalCount(WaitPayDTO dto) throws SerException {
        list(dto);
        String[] drivers = dto.getArrivals();
        dto.getConditions().add(Restrict.in("arrival", drivers));
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        List<WaitPay> list = super.findByCis(dto);
        List<ArrivalCountBO> boList = new ArrayList<>();
        double overtimeFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
        double allowanceSum = list.stream().mapToDouble((WaitPay w) -> w.getAllowance()).sum();
        double parkFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getParkFee()).sum();
        double amountSum = list.stream().mapToDouble((WaitPay w) -> w.getAmount()).sum();
        for (String driverName : drivers) {
            double overtimeFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
            double allowance = list.stream().filter(waitPay -> driverName.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
            double parkFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
            double amount = list.stream().filter(waitPay -> driverName.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
            ArrivalCountBO bo = new ArrivalCountBO();
            bo.setArrival(driverName);
            bo.setOvertimeFeeSum(overtimeFee);
            bo.setAllowanceSum(allowance);
            bo.setParkFeeSum(parkFee);
            bo.setAmountSum(amount);
            boList.add(bo);
        }
        ArrivalCountBO bo = new ArrivalCountBO();
        bo.setArrival("合计");
        bo.setOvertimeFeeSum(overtimeFeeSum);
        bo.setAllowanceSum(allowanceSum);
        bo.setParkFeeSum(parkFeeSum);
        bo.setAmountSum(amountSum);
        boList.add(bo);
        return boList;
    }

    @Override
    public List<CarUserCountBO> waitCarUserCount(WaitPayDTO dto) throws SerException {
        list(dto);
        String[] drivers = dto.getCarUsers();
        dto.getConditions().add(Restrict.in("carUser", drivers));
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        List<WaitPay> list = super.findByCis(dto);
        List<CarUserCountBO> boList = new ArrayList<>();
        double overtimeFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
        double allowanceSum = list.stream().mapToDouble((WaitPay w) -> w.getAllowance()).sum();
        double parkFeeSum = list.stream().mapToDouble((WaitPay w) -> w.getParkFee()).sum();
        double amountSum = list.stream().mapToDouble((WaitPay w) -> w.getAmount()).sum();
        for (String driverName : drivers) {
            double overtimeFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
            double allowance = list.stream().filter(waitPay -> driverName.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
            double parkFee = list.stream().filter(waitPay -> driverName.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
            double amount = list.stream().filter(waitPay -> driverName.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
            CarUserCountBO bo = new CarUserCountBO();
            bo.setCarUser(driverName);
            bo.setOvertimeFeeSum(overtimeFee);
            bo.setAllowanceSum(allowance);
            bo.setParkFeeSum(parkFee);
            bo.setAmountSum(amount);
            boList.add(bo);
        }
        CarUserCountBO bo = new CarUserCountBO();
        bo.setCarUser("合计");
        bo.setOvertimeFeeSum(overtimeFeeSum);
        bo.setAllowanceSum(allowanceSum);
        bo.setParkFeeSum(parkFeeSum);
        bo.setAmountSum(amountSum);
        boList.add(bo);
        return boList;
    }

    @Override
    public List<WaitPayBO> waitDetails(WaitPayDTO dto) throws SerException {
        list(dto);
        String[] drivers = dto.getDrivers();
        String[] arrivals = dto.getArrivals();
        String[] carUsers = dto.getCarUsers();
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        List<WaitPayBO> boList = new LinkedList<>();
        if (null != drivers) {
            dto.getSorts().add("driverName=asc");
            dto.getConditions().add(Restrict.in("driverName", drivers));
            List<WaitPay> list = super.findByCis(dto);
            if (!list.isEmpty()) {
                boList = BeanTransform.copyProperties(list, WaitPayBO.class);
            }
            for (String driver : drivers) {
                double overtimeFee = list.stream().filter(waitPay -> driver.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
                double allowance = list.stream().filter(waitPay -> driver.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
                double parkFee = list.stream().filter(waitPay -> driver.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
                double amount = list.stream().filter(waitPay -> driver.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
                ListIterator<WaitPayBO> iterator = boList.listIterator();
                boolean b = false;
                int num = 0;
                while (iterator.hasNext()) {
                    if (driver.equals(iterator.next().getDriverName())) {
                        b = true;
                        num = iterator.nextIndex();
                    }
                }
                if (b) {
                    WaitPayBO bo = new WaitPayBO();
                    bo.setDriverName(driver);
                    bo.setNumber("合计");
                    bo.setOvertimeFee(overtimeFee);
                    bo.setAllowance(allowance);
                    bo.setParkFee(parkFee);
                    bo.setAmount(amount);
                    boList.add(num, bo);
                }
            }
        } else if (null != arrivals) {
            dto.getSorts().add("arrival=asc");
            dto.getConditions().add(Restrict.in("arrival", arrivals));
            List<WaitPay> list = super.findByCis(dto);
            if (!list.isEmpty()) {
                boList = BeanTransform.copyProperties(list, WaitPayBO.class);
            }
            for (String driver : arrivals) {
                double overtimeFee = list.stream().filter(waitPay -> driver.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
                double allowance = list.stream().filter(waitPay -> driver.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
                double parkFee = list.stream().filter(waitPay -> driver.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
                double amount = list.stream().filter(waitPay -> driver.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
                ListIterator<WaitPayBO> iterator = boList.listIterator();
                boolean b = false;
                int num = 0;
                while (iterator.hasNext()) {
                    if (driver.equals(iterator.next().getArrival())) {
                        b = true;
                        num = iterator.nextIndex();
                    }
                }
                if (b) {
                    WaitPayBO bo = new WaitPayBO();
                    bo.setArrival(driver);
                    bo.setNumber("合计");
                    bo.setOvertimeFee(overtimeFee);
                    bo.setAllowance(allowance);
                    bo.setParkFee(parkFee);
                    bo.setAmount(amount);
                    boList.add(num, bo);
                }
            }
        } else if (null != carUsers) {
            dto.getSorts().add("carUser=asc");
            dto.getConditions().add(Restrict.in("carUser", carUsers));
            List<WaitPay> list = super.findByCis(dto);
            if (!list.isEmpty()) {
                boList = BeanTransform.copyProperties(list, WaitPayBO.class);
            }
            for (String driver : carUsers) {
                double overtimeFee = list.stream().filter(waitPay -> driver.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
                double allowance = list.stream().filter(waitPay -> driver.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
                double parkFee = list.stream().filter(waitPay -> driver.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
                double amount = list.stream().filter(waitPay -> driver.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
                ListIterator<WaitPayBO> iterator = boList.listIterator();
                boolean b = false;
                int num = 0;
                while (iterator.hasNext()) {
                    if (driver.equals(iterator.next().getCarUser())) {
                        b = true;
                        num = iterator.nextIndex();
                    }
                }
                if (b) {
                    WaitPayBO bo = new WaitPayBO();
                    bo.setCarUser(driver);
                    bo.setNumber("合计");
                    bo.setOvertimeFee(overtimeFee);
                    bo.setAllowance(allowance);
                    bo.setParkFee(parkFee);
                    bo.setAmount(amount);
                    boList.add(num, bo);
                }
            }
        }
        return boList;
    }

    @Override
    public List<WaitPayBO> details(WaitPayDTO dto) throws SerException {
//        list(dto);
        String[] drivers = dto.getDrivers();
        String[] arrivals = dto.getArrivals();
        String[] carUsers = dto.getCarUsers();
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.FALSE));
        List<WaitPayBO> boList = new LinkedList<>();
        if (null != drivers) {
            dto.getSorts().add("driverName=asc");
            dto.getConditions().add(Restrict.in("driverName", drivers));
            List<WaitPay> list = super.findByCis(dto);
            if (!list.isEmpty()) {
                boList = BeanTransform.copyProperties(list, WaitPayBO.class);
            }
            for (String driver : drivers) {
                double overtimeFee = list.stream().filter(waitPay -> driver.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
                double allowance = list.stream().filter(waitPay -> driver.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
                double parkFee = list.stream().filter(waitPay -> driver.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
                double amount = list.stream().filter(waitPay -> driver.equals(waitPay.getDriverName())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
                ListIterator<WaitPayBO> iterator = boList.listIterator();
                boolean b = false;
                int num = 0;
                while (iterator.hasNext()) {
                    if (driver.equals(iterator.next().getDriverName())) {
                        b = true;
                        num = iterator.nextIndex();
                    }
                }
                if (b) {
                    WaitPayBO bo = new WaitPayBO();
                    bo.setDriverName(driver);
                    bo.setNumber("合计");
                    bo.setOvertimeFee(overtimeFee);
                    bo.setAllowance(allowance);
                    bo.setParkFee(parkFee);
                    bo.setAmount(amount);
                    boList.add(num, bo);
                }
            }
        } else if (null != arrivals) {
            dto.getSorts().add("arrival=asc");
            dto.getConditions().add(Restrict.in("arrival", arrivals));
            List<WaitPay> list = super.findByCis(dto);
            if (!list.isEmpty()) {
                boList = BeanTransform.copyProperties(list, WaitPayBO.class);
            }
            for (String driver : arrivals) {
                double overtimeFee = list.stream().filter(waitPay -> driver.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
                double allowance = list.stream().filter(waitPay -> driver.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
                double parkFee = list.stream().filter(waitPay -> driver.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
                double amount = list.stream().filter(waitPay -> driver.equals(waitPay.getArrival())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
                ListIterator<WaitPayBO> iterator = boList.listIterator();
                boolean b = false;
                int num = 0;
                while (iterator.hasNext()) {
                    if (driver.equals(iterator.next().getArrival())) {
                        b = true;
                        num = iterator.nextIndex();
                    }
                }
                if (b) {
                    WaitPayBO bo = new WaitPayBO();
                    bo.setArrival(driver);
                    bo.setNumber("合计");
                    bo.setOvertimeFee(overtimeFee);
                    bo.setAllowance(allowance);
                    bo.setParkFee(parkFee);
                    bo.setAmount(amount);
                    boList.add(num, bo);
                }
            }
        } else if (null != carUsers) {
            dto.getSorts().add("carUser=asc");
            dto.getConditions().add(Restrict.in("carUser", carUsers));
            List<WaitPay> list = super.findByCis(dto);
            if (!list.isEmpty()) {
                boList = BeanTransform.copyProperties(list, WaitPayBO.class);
            }
            for (String driver : carUsers) {
                double overtimeFee = list.stream().filter(waitPay -> driver.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getOvertimeFee()).sum();
                double allowance = list.stream().filter(waitPay -> driver.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getAllowance()).sum();
                double parkFee = list.stream().filter(waitPay -> driver.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getParkFee()).sum();
                double amount = list.stream().filter(waitPay -> driver.equals(waitPay.getCarUser())).mapToDouble((WaitPay w) -> w.getAmount()).sum();
                ListIterator<WaitPayBO> iterator = boList.listIterator();
                boolean b = false;
                int num = 0;
                while (iterator.hasNext()) {
                    if (driver.equals(iterator.next().getCarUser())) {
                        b = true;
                        num = iterator.nextIndex();
                    }
                }
                if (b) {
                    WaitPayBO bo = new WaitPayBO();
                    bo.setCarUser(driver);
                    bo.setNumber("合计");
                    bo.setOvertimeFee(overtimeFee);
                    bo.setAllowance(allowance);
                    bo.setParkFee(parkFee);
                    bo.setAmount(amount);
                    boList.add(num, bo);
                }
            }
        }
        return boList;
    }

    @Override
    public List<WaitPayBO> pays(WaitPayDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.FALSE));
        List<WaitPay> l = super.findByCis(dto, true);
        return BeanTransform.copyProperties(l, WaitPayBO.class);
    }

    @Override
    public Long waitCountSum(WaitPayDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        return super.count(dto);
    }

    @Override
    public Long payCountSum(WaitPayDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("isDel", Boolean.TRUE));
        dto.getConditions().add(Restrict.eq("isPay", Boolean.FALSE));
        return super.count(dto);
    }

    /**
     * 查找所有司机
     *
     * @return class String
     * @throws SerException
     */
    @Override
    public Set<String> findAllDrivers() throws SerException {
        list(new WaitPayDTO());
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
    @Override
    public Set<String> findAllArrivals() throws SerException {
        list(new WaitPayDTO());
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
    @Override
    public Set<String> findAllCarUsers() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getCarUser());
        }
        return set;
    }

}