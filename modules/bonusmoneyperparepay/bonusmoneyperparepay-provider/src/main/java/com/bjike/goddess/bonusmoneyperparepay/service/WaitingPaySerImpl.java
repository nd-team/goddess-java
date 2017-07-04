package com.bjike.goddess.bonusmoneyperparepay.service;

import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingPayBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.WaitingPayDTO;
import com.bjike.goddess.bonusmoneyperparepay.entity.MoneyPerpare;
import com.bjike.goddess.bonusmoneyperparepay.entity.WaitingPay;
import com.bjike.goddess.bonusmoneyperparepay.excel.WaitingPayExcel;
import com.bjike.goddess.bonusmoneyperparepay.to.WaitingPayTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Override
    public Long countWaiting(WaitingPayDTO waitingPayDTO) throws SerException {
        searchCondition(waitingPayDTO);
        Long count = super.count(waitingPayDTO);
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
        searchCondition(waitingPayDTO);
        waitingPayDTO.getConditions().add(Restrict.eq("payStatus","等待付款"));
        List<WaitingPay> list = super.findByPage(waitingPayDTO);
        List<WaitingPayBO> waitingPayBOList = BeanTransform.copyProperties(list, WaitingPayBO.class);
        return waitingPayBOList;
    }

    @Override
    public List<WaitingPayBO> list(WaitingPayDTO waitingPayDTO) throws SerException {
        searchCondition(waitingPayDTO);
        waitingPayDTO.getConditions().add(Restrict.eq("payStatus","已付款"));
        List<WaitingPay> list = super.findByPage(waitingPayDTO);
        List<WaitingPayBO> waitingPayBOList = BeanTransform.copyProperties(list, WaitingPayBO.class);
        return waitingPayBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteWaiting(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    public void searchCondition(WaitingPayDTO waitingPayDTO) throws SerException {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(waitingPayDTO.getStartDifference())) {
            start = DateUtil.parseDate(waitingPayDTO.getStartDifference());
        }
        if (StringUtils.isNotBlank(waitingPayDTO.getEndDifference())) {
            end = DateUtil.parseDate(waitingPayDTO.getEndDifference());
        }
        LocalDate[] diffe = new LocalDate[]{start,end};
        if (StringUtils.isNotBlank(waitingPayDTO.getStartDifference()) || StringUtils.isNotBlank(waitingPayDTO.getEndDifference())) {
            waitingPayDTO.getConditions().add(Restrict.between("difference",diffe));
        }
        if(StringUtils.isNotBlank(waitingPayDTO.getProjectGroup())){
            waitingPayDTO.getConditions().add(Restrict.eq("projectGroup",waitingPayDTO.getProjectGroup()));
        }
    }

    @Override
    public void payMoney(String id,Double payMoney) throws SerException {
        UserBO userbo = userAPI.currentUser();
        WaitingPay model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setPayStatus("已付款");
        model.setTurntable("是");
        model.setPayAuthor(userbo.getUsername());
        model.setPayMoney(payMoney);
        super.update(model);
    }


    @Override
    public List<WaitingBO> projectCompare(Integer month, String[] projectGroup) throws SerException {
        List<WaitingBO> list = new ArrayList<>();
        if (projectGroup == null || projectGroup.length == 0) {
            throw new SerException("项目组不能为空");
        } else {
            for (String project : projectGroup) {
                WaitingPayDTO dto = new WaitingPayDTO();
                if (month != null) {
                    dto.getConditions().add(Restrict.eq("month", month));
                }
                dto.getConditions().add(Restrict.eq("projectGroup", projectGroup));
                List<WaitingPay> waitingPays = super.findByCis(dto);
                if (waitingPays != null && waitingPays.size() > 0) {
                    Double payMoney = 0d;
                    Double reserve = 0d;
                    for (WaitingPay waitingPay : waitingPays) {
                        WaitingBO waitingBO = new WaitingBO();
                        waitingBO.setYearsMonth(waitingPay.getYears() + "-" + (waitingPay.getMonth() > 9 ? waitingPay.getMonth() : "0" + waitingPay.getMonth()));//时间
                        waitingBO.setProjectGroup(project);//项目组
                        waitingBO.setPayStatus(waitingPay.getPayStatus());//付款状态
                        waitingBO.setSubjects(waitingPay.getSubjects());//科目
                        waitingBO.setReserve(waitingPay.getReserve());//准备金
                        waitingBO.setPayMoney(waitingPay.getPayMoney());//支付金额
                        waitingBO.setDifference(waitingPay.getDifference().toString());//付款时间

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
        List<WaitingBO> list = new ArrayList<>();
        WaitingPayDTO dto = new WaitingPayDTO();
        if (years != null) {
            dto.getConditions().add(Restrict.eq("years", years));
        }else{
            dto.getConditions().add(Restrict.eq("years", LocalDateTime.now().getYear()));
        }
        List<WaitingPay> waitingPays = super.findByCis(dto);
        if (waitingPays != null && waitingPays.size() > 0) {
            Double payMoney = 0d;
            Double reserve = 0d;
            for (WaitingPay waitingPay : waitingPays) {
                WaitingBO waitingBO = new WaitingBO();
                waitingBO.setYearsMonth(waitingPay.getYears() + "-" + (waitingPay.getMonth() > 9 ? waitingPay.getMonth() : "0" + waitingPay.getMonth()));//时间
                waitingBO.setProjectGroup(waitingPay.getProjectGroup());//项目组
                waitingBO.setPayStatus(waitingPay.getPayStatus());//付款状态
                waitingBO.setSubjects(waitingPay.getSubjects());//科目
                waitingBO.setReserve(waitingPay.getReserve());//准备金
                waitingBO.setPayMoney(waitingPay.getPayMoney());//支付金额
                waitingBO.setDifference(waitingPay.getDifference().toString());//付款时间

                payMoney += waitingPay.getPayMoney();
                reserve += waitingPay.getReserve();
                list.add(waitingBO);
            }

            WaitingBO waitingBO = new WaitingBO();
            waitingBO.setYearsMonth("合计");//时间
            waitingBO.setPayMoney(payMoney);//支付金额
            waitingBO.setReserve(reserve);//准备金额

            list.add(waitingBO);;
        }
        return list;
    }
    @Override
    public List<WaitingBO> monthCompare(Integer month) throws SerException {
        List<WaitingBO> list = new ArrayList<>();
        WaitingPayDTO dto = new WaitingPayDTO();
        if (month != null) {
            dto.getConditions().add(Restrict.eq("month", month));
        }else{
            dto.getConditions().add(Restrict.eq("month", LocalDateTime.now().getMonthValue()));
        }
        List<WaitingPay> waitingPays = super.findByCis(dto);
        if (waitingPays != null && waitingPays.size() > 0) {
            Double payMoney = 0d;
            Double reserve = 0d;
            for (WaitingPay waitingPay : waitingPays) {
                WaitingBO waitingBO = new WaitingBO();
                waitingBO.setYearsMonth(waitingPay.getYears() + "-" + (waitingPay.getMonth() > 9 ? waitingPay.getMonth() : "0" + waitingPay.getMonth()));//时间
                waitingBO.setProjectGroup(waitingPay.getProjectGroup());//项目组
                waitingBO.setPayStatus(waitingPay.getPayStatus());//付款状态
                waitingBO.setSubjects(waitingPay.getSubjects());//科目
                waitingBO.setReserve(waitingPay.getReserve());//准备金
                waitingBO.setPayMoney(waitingPay.getPayMoney());//支付金额
                waitingBO.setDifference(waitingPay.getDifference().toString());//付款时间

                payMoney += waitingPay.getPayMoney();
                reserve += waitingPay.getReserve();
                list.add(waitingBO);
            }

            WaitingBO waitingBO = new WaitingBO();
            waitingBO.setYearsMonth("合计");//时间
            waitingBO.setPayMoney(payMoney);//支付金额
            waitingBO.setReserve(reserve);//准备金额

            list.add(waitingBO);;
        }
        return list;
    }

    @Override
    public byte[] exportExcel(Integer startMonth, Integer endMonth) throws SerException {

        Integer start = LocalDate.now().getMonthValue();
        Integer end = LocalDate.now().getMonthValue();
        if(start!=null){
            start = startMonth;
        }
        if(end !=null){
            end = endMonth;
        }

        WaitingPayDTO waitingPayDTO = new WaitingPayDTO();
        if (start!=null || end !=null) {
            waitingPayDTO.getConditions().add(Restrict.between("month", new Integer[]{start,end}));
        }
        List<WaitingPay> list = super.findByCis(waitingPayDTO);

        List<WaitingPayExcel> waitingPayExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            WaitingPayExcel excel = BeanTransform.copyProperties(str, WaitingPayExcel.class);
            waitingPayExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(waitingPayExcels, excel);
        return bytes;
    }

    @Override
    public byte[] exportArealdyExcel(Integer startMonth, Integer endMonth) throws SerException {

        Integer start = LocalDate.now().getMonthValue();
        Integer end = LocalDate.now().getMonthValue();
        if(start!=null){
            start = startMonth;
        }
        if(end !=null){
            end = endMonth;
        }

        WaitingPayDTO waitingPayDTO = new WaitingPayDTO();
        if (start!=null || end !=null) {
            waitingPayDTO.getConditions().add(Restrict.between("month", new Integer[]{start,end}));
        }
        List<WaitingPay> list = super.findByCis(waitingPayDTO);

        List<WaitingPayExcel> waitingPayExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            WaitingPayExcel excel = BeanTransform.copyProperties(str, WaitingPayExcel.class);
            waitingPayExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(waitingPayExcels, excel);
        return bytes;
    }
}