package com.bjike.goddess.equipmentprepared.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.equipmentprepared.bo.PayCountBO;
import com.bjike.goddess.equipmentprepared.bo.WaitPayBO;
import com.bjike.goddess.equipmentprepared.dto.WaitPayDTO;
import com.bjike.goddess.equipmentprepared.entity.WaitPay;
import com.bjike.goddess.equipmentprepared.to.WaitPayTO;
import com.bjike.goddess.materialbuy.api.MaterialBuyAPI;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 等待付款业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-13 02:27 ]
 * @Description: [ 等待付款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "equipmentpreparedSerCache")
@Service
public class WaitPaySerImpl extends ServiceImpl<WaitPay, WaitPayDTO> implements WaitPaySer {
    @Autowired
    private MaterialBuyAPI materialBuyAPI;
    @Autowired
    private UserAPI userAPI;

    @Override
    public List<PayCountBO> departmentCount() throws SerException {
        list(new WaitPayDTO());
        Integer quantitySum = 0;
        Double sum = 0.00;
        List<PayCountBO> boList = new ArrayList<PayCountBO>();
        List<WaitPayBO> all = allPays();
        Set<String> departments = allDepartments();
        for (String depatment : departments) {
            for (WaitPayBO w : all) {
                if (depatment.equals(w.getProjectTeam())) {
                    PayCountBO bo = new PayCountBO();
                    BeanUtils.copyProperties(w, bo);
                    quantitySum += w.getQuantity();
                    sum += w.getTotalSum();
                    boList.add(bo);
                }
            }
            if ((quantitySum != 0) || (sum != 0)) {
                PayCountBO bo = new PayCountBO();
                bo.setQuantitySum(quantitySum);
                bo.setSum(sum);
                boList.add(bo);
                quantitySum = 0;
                sum = 0.00;
            }
        }
        return boList;
    }

    @Override
    public List<PayCountBO> areaCount() throws SerException {
        list(new WaitPayDTO());
        Integer quantitySum = 0;
        Double sum = 0.00;
        List<PayCountBO> boList = new ArrayList<PayCountBO>();
        List<WaitPayBO> all = allPays();
        Set<String> areas = allAreas();
        for (String area : areas) {
            for (WaitPayBO w : all) {
                if (area.equals(w.getArea())) {
                    PayCountBO bo = new PayCountBO();
                    BeanUtils.copyProperties(w, bo);
                    quantitySum += w.getQuantity();
                    sum += w.getTotalSum();
                    boList.add(bo);
                }
            }
            if ((quantitySum != 0) || (sum != 0)) {
                PayCountBO bo = new PayCountBO();
                bo.setQuantitySum(quantitySum);
                bo.setSum(sum);
                boList.add(bo);
                quantitySum = 0;
                sum = 0.00;
            }
        }
        return boList;
    }

    @Override
    public List<PayCountBO> deviceNameCount() throws SerException {
        list(new WaitPayDTO());
        Integer quantitySum = 0;
        Double sum = 0.00;
        List<PayCountBO> boList = new ArrayList<PayCountBO>();
        List<WaitPayBO> all = allPays();
        Set<String> deviceNames = allDeviceNames();
        for (String deviceName : deviceNames) {
            for (WaitPayBO w : all) {
                if (deviceName.equals(w.getDeviceName())) {
                    PayCountBO bo = new PayCountBO();
                    BeanUtils.copyProperties(w, bo);
                    quantitySum += w.getQuantity();
                    sum += w.getTotalSum();
                    boList.add(bo);
                }
            }
            if ((quantitySum != 0) || (sum != 0)) {
                PayCountBO bo = new PayCountBO();
                bo.setQuantitySum(quantitySum);
                bo.setSum(sum);
                boList.add(bo);
                quantitySum = 0;
                sum = 0.00;
            }
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<WaitPayBO> list(WaitPayDTO dto) throws SerException {
        List<MaterialBuyBO> list = materialBuyAPI.findWaitPay(new MaterialBuyDTO());
        List<WaitPay> waitPays = super.findAll();
        if (list != null) {
            for (MaterialBuyBO v : list) {
                if (waitPays.size() == 0) {
                    WaitPay waitPay = new WaitPay();
                    BeanUtils.copyProperties(v, waitPay);
                    waitPay.setSubscribeDate(DateUtil.parseDate(v.getSubscribeDate()));
                    LocalDate l = DateUtil.parseDate(v.getSubscribeDate());
                    waitPay.setYear(l.getYear());
                    waitPay.setMonth(l.getMonthValue());
                    waitPay.setWaitId(v.getId());
                    waitPay.setIfPayment(v.getIfPayment());
                    super.save(waitPay);
                } else {
                    boolean b1 = true;
                    for (WaitPay p : waitPays) {
                        if (p.getWaitId().equals(v.getId())) {
                            LocalDateTime a = p.getCreateTime();
                            String id = p.getId();
                            boolean b2 = p.getIfPayment();
                            BeanUtils.copyProperties(v, p);
                            p.setCreateTime(a);
                            p.setModifyTime(LocalDateTime.now());
                            p.setSubscribeDate(DateUtil.parseDate(v.getSubscribeDate()));
                            p.setYear(DateUtil.parseDate(v.getSubscribeDate()).getYear());
                            p.setMonth(DateUtil.parseDate(v.getSubscribeDate()).getMonthValue());
                            p.setWaitId(v.getId());
                            p.setId(id);
                            p.setIfPayment(b2);
                            super.update(p);
                            b1 = false;
                        }
                    }
                    if (b1) {
                        WaitPay waitPay = new WaitPay();
                        BeanUtils.copyProperties(v, waitPay);
                        waitPay.setSubscribeDate(DateUtil.parseDate(v.getSubscribeDate()));
                        LocalDate l = DateUtil.parseDate(v.getSubscribeDate());
                        waitPay.setYear(l.getYear());
                        waitPay.setMonth(l.getMonthValue());
                        waitPay.setWaitId(v.getId());
                        waitPay.setIfPayment(v.getIfPayment());
                        super.save(waitPay);
                    }
                }
            }
        }
        for (WaitPay p : super.findAll()) {
            MaterialBuyBO v = materialBuyAPI.findById(p.getWaitId());
            if (v == null || v.getIfPayment()) {
                super.remove(p.getId());
            }
        }
        dto.getConditions().add(Restrict.eq("ifPayment", Boolean.TRUE));
        List<WaitPay> l = super.findByCis(dto, true);
        return BeanTransform.copyProperties(l, WaitPayBO.class);
    }

    @Override
    public List<WaitPayBO> pays(WaitPayDTO dto) throws SerException {
        list(new WaitPayDTO());
        dto.getConditions().add(Restrict.eq("ifPayment", Boolean.FALSE));
        List<WaitPay> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, WaitPayBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void confirmPay(WaitPayTO to) throws SerException {
        String name=userAPI.currentUser().getUsername();
        WaitPay waitPay = super.findById(to.getId());
        try {
            waitPay.setPayTime(DateUtil.parseDateTime(to.getPayTime()));
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        waitPay.setPayMan(name);
        waitPay.setIfPayment(true);
        super.update(waitPay);
    }

    @Override
    public List<WaitPayBO> export(Integer year, Integer month) throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = new ArrayList<WaitPay>();
        WaitPayDTO dto = new WaitPayDTO();
        String r = "^20\\d{2}$";
        String r1 = "^[1-9][0-2]?$";
        if (!(String.valueOf(year).matches(r))) {
            throw new SerException("年份格式错误");
        }
        if (!(String.valueOf(month).matches(r1))) {
            throw new SerException("月份格式错误");
        }
        dto.getConditions().add(Restrict.eq("year", year));
        dto.getConditions().add(Restrict.eq("month", month));
        List<WaitPay> l = super.findByCis(dto);
        for (WaitPay w : l) {
            if (!w.getIfPayment()) {
                list.add(w);
            }
        }
        return BeanTransform.copyProperties(list, WaitPayBO.class);
    }

    @Override
    public WaitPayBO findByID(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), WaitPayBO.class);
    }

    /**
     * 获取所有地区
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allAreas() throws SerException {
        Set<String> set = new HashSet<String>();
        List<WaitPayBO> list = allPays();
        for (WaitPayBO w : list) {
            set.add(w.getArea());
        }
        return set;
    }

    /**
     * 获取所有设备名称
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allDeviceNames() throws SerException {
        Set<String> set = new HashSet<String>();
        List<WaitPayBO> list = allPays();
        for (WaitPayBO w : list) {
            set.add(w.getDeviceName());
        }
        return set;
    }

    /**
     * 获取所有部门
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allDepartments() throws SerException {
        Set<String> set = new HashSet<String>();
        List<WaitPayBO> list = allPays();
        for (WaitPayBO w : list) {
            set.add(w.getProjectTeam());
        }
        return set;
    }

    /**
     * 查找所有已付款
     *
     * @return
     * @throws SerException
     */
    private List<WaitPayBO> allPays() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> all = super.findAll();
        List<WaitPay> list = new ArrayList<WaitPay>();
        for (WaitPay w : all) {
            if (w.getIfPayment()) {
                list.add(w);
            }
        }
        return BeanTransform.copyProperties(list, WaitPayBO.class);
    }

    @Override
    public Long waitCountSum(WaitPayDTO dto) throws SerException{
        list(new WaitPayDTO());
        dto.getConditions().add(Restrict.eq("ifPayment", Boolean.TRUE));
        return super.count(dto);
    }

    @Override
    public Long payCountSum(WaitPayDTO dto) throws SerException{
        list(new WaitPayDTO());
        dto.getConditions().add(Restrict.eq("ifPayment", Boolean.FALSE));
        return super.count(dto);
    }
}