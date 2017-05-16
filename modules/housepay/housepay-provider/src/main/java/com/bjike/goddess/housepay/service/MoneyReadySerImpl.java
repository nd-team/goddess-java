package com.bjike.goddess.housepay.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.housepay.bo.CollectCompareBO;
import com.bjike.goddess.housepay.bo.MoneyReadyBO;
import com.bjike.goddess.housepay.dto.MoneyReadyDTO;
import com.bjike.goddess.housepay.entity.MoneyReady;
import com.bjike.goddess.housepay.to.CollectCompareTO;
import com.bjike.goddess.housepay.to.MoneyReadyTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资金准备审核表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:32 ]
 * @Description: [ 资金准备审核表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "housepaySerCache")
@Service
public class MoneyReadySerImpl extends ServiceImpl<MoneyReady, MoneyReadyDTO> implements MoneyReadySer {
    @Override
    public Long countMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        Long count = super.count(moneyReadyDTO);
        return count;
    }

    @Override
    public MoneyReadyBO getOne(String id) throws SerException {
        MoneyReady moneyReady = super.findById(id);
        return BeanTransform.copyProperties(moneyReady,MoneyReadyBO.class);
    }

    @Override
    public List<MoneyReadyBO> findListMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        List<MoneyReady> moneyReadies = super.findByPage(moneyReadyDTO);
        List<MoneyReadyBO> moneyReadyBOS = BeanTransform.copyProperties(moneyReadies,MoneyReadyBO.class);
        return moneyReadyBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyReadyBO insertMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        MoneyReady moneyReady = BeanTransform.copyProperties(moneyReadyTO,MoneyReady.class,true);
        moneyReady.setCreateTime(LocalDateTime.now());
        super.save(moneyReady);
        return BeanTransform.copyProperties(moneyReady,MoneyReadyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyReadyBO editMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        MoneyReady moneyReady = super.findById(moneyReadyTO.getId());
        BeanTransform.copyProperties(moneyReadyTO,moneyReady,true);
        moneyReady.setModifyTime(LocalDateTime.now());
        super.update(moneyReady);
        return BeanTransform.copyProperties(moneyReady,MoneyReadyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeMoneyReady(String id) throws SerException {
        super.remove(id);
    }
//    public List<CollectCompareBO> time(String startMonth,String endMonth) throws SerException {
//        MoneyReadyDTO dto = new MoneyReadyDTO();
//        LocalDate[] dates = new LocalDate[0];
//        try {
//            dates = new LocalDate[]{LocalDate.parse(startMonth), LocalDate.parse(endMonth)};
//        } catch (Exception e) {
//            throw new SerException("时间格式错误(例:2010-12-31)");
//        }
//        dto.getConditions().add(Restrict.between("time", dates));
//        return super.findByCis(dto);
//    }
    @Override
    public List<CollectCompareBO> collectCompare(String startMonth, String endMonth) throws SerException {
        CollectCompareBO collectCompareBO = new CollectCompareBO();
        List<CollectCompareBO> collectCompareBOList = new ArrayList<>();

        /*DateFormat format = new SimpleDateFormat("yyyy-MM");
        try {
            Date startDate =  format.parse(startMonth);
          if(startDate.getMonth() == 0){
              startDate.setYear(startDate.getYear()+1900-1);
              startDate.setMonth(12);
            }else{
                startDate.setMonth(startDate.getMonth()-1);
            }
        } catch (ParseException e) {
           e.printStackTrace();
       }      */


        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = (LocalDate) format.parse(startMonth);
        //startDate.minusMonths(1);
//        String last = String.valueOf(startDate);
//        last = last.substring(0,7);
        String month = startMonth.substring(startMonth.indexOf("-"))+1;
        if(month.lastIndexOf("0") == 0){
            month = month.substring(1);
        }
        int thisMonth = Integer.parseInt(month);
        if(thisMonth == 1){
            month = String.valueOf(startDate.minusMonths(1));
        }

        //for(CollectCompareBO compareBO : collectCompareBO){}
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT dates ,projectGroup ,sum(reserves) as reserves FROM ");
        sb.append(" (SELECT date_format(time,'%Y-%m') as dates ,projectGroup,reserves from ");
        sb.append(" housepay_moneyready where time BETWEEN '"+ startMonth +"' AND '"+ endMonth +"')A GROUP BY dates,projectGroup ");
        return null;
    }

    public static void main(String[] args) {
        //DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        /*LocalDate startDate = LocalDate.parse("2017-11-11",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate startDate1 = startDate.minusMonths(1);
        String date = String.valueOf(startDate1);
        date = date.substring(0,7);
        System.out.println(date);*/

        LocalDate now = LocalDate.now().withYear(2017).withMonth(11).withDayOfMonth(1);
        System.out.println(now.minusMonths(1));
        System.out.println(now.getMonthValue()-1);

    }
}
