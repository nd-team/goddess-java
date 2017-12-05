package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.marketdevelopment.bo.OutBillBO;
import com.bjike.goddess.marketdevelopment.dto.OutBillDTO;
import com.bjike.goddess.marketdevelopment.entity.OutBill;
import com.bjike.goddess.marketdevelopment.to.OutBillTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 外出单业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 11:12 ]
 * @Description: [ 外出单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class OutBillSerImpl extends ServiceImpl<OutBill, OutBillDTO> implements OutBillSer {

    @Override
    public List<OutBillBO> maps(OutBillDTO dto) throws SerException {
        dto.getSorts().add("num=asc");
        dto.getSorts().add("billNum=asc");
        List<OutBill> outBills = super.findByPage(dto);
        List<OutBillBO> bos = BeanTransform.copyProperties(outBills, OutBillBO.class, false);
        return bos;
    }

    @Override
    public void save(OutBillTO to) throws SerException {
        OutBill entity = BeanTransform.copyProperties(to, OutBill.class, true);
        List<OutBill> outBills = super.findAll();
        int num = outBills.size();
        entity.setNum(num);
        String time = DateUtil.dateToString(LocalDate.now());
        String billTime = time.substring(0,4) + "年-" + time.substring(time.indexOf("-") + 1,time.lastIndexOf("-")) + "月-";
        entity.setBillNum("swbwcd-" + billTime + to.getBillNum());
        super.save(entity);
    }

    @Override
    public void update(OutBillTO to) throws SerException {
        OutBill entity = super.findById(to.getId());
        if(null == entity){
            throw new SerException("目标数据不能为空");
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void delete(OutBillTO to) throws SerException {
        OutBill entity = super.findById(to.getId());
        if(null == entity){
            throw new SerException("目标数据不能为空");
        }
        super.remove(entity);
    }

    @Override
    public OutBillBO getById(String id) throws SerException {
        OutBill entity = super.findById(id);
        if(null == entity){
            throw new SerException("目标数据不能为空");
        }
        OutBillBO bo = BeanTransform.copyProperties(entity, OutBillBO.class, false);
        return bo;
    }

    @Override
    public Long getTotal(OutBillDTO dto) throws SerException {
        return super.count(dto);
    }

    private void searchCondition(OutBillDTO dto) throws SerException{

    }
}