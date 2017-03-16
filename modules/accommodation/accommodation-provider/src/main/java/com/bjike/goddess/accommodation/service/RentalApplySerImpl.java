package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.RentalApplyBO;
import com.bjike.goddess.accommodation.dto.RentalApplyDTO;
import com.bjike.goddess.accommodation.entity.RentalApply;
import com.bjike.goddess.accommodation.to.RentalApplyTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 租房申请 业务实现
 *
 * @Author: [xiazhili]
 * @Date: [2017-3-10 10:16]
 * @Description: [租房申请 业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "rentalApplySerCache")
@Service
public class RentalApplySerImpl extends ServiceImpl<RentalApply,RentalApplyDTO> implements RentalApplySer{
    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalApplyBO insertApply(RentalApplyTO applyTO) throws SerException {
        RentalApply apply = BeanTransform.copyProperties(applyTO,RentalApply.class,true);
        try {
            apply.setCreateTime(LocalDateTime.now());
            super.save(apply);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(apply, RentalApplyBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalApplyBO editApply(RentalApplyTO applyTO) throws SerException {

        RentalApply apply = BeanTransform.copyProperties(applyTO, RentalApply.class, true);
        try {
            apply.setModifyTime(LocalDateTime.now());
            super.update(apply);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

        return BeanTransform.copyProperties(apply, RentalApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeApply(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }
    @Cacheable
    @Override
    public List<RentalApply> listRentalApply(RentalApplyDTO rentalApplyDTO) throws SerException {

        //TODO: xiazhili 2017-03-10 未做根据 rentalApplyDTO 分页查询所有
        List<RentalApply> rentalApplies = super.findByPage(rentalApplyDTO);
        return rentalApplies;
    }

    /**
     * 审核
     * @param applyTO
     * @throws SerException
     */
    public void audit(RentalApplyTO applyTO) throws SerException{
        //TODO: xiazhili 2017-03-14 未做审核
        return ;
    }

    /**
     *租房申请导出明细
     */
    public String exportExcel (String startTime, String endTime)throws SerException{
        //TODO: xiazhili 2017-03-10 未做导出明细
        return null;
    }
    /**
     *自动生成记账凭证
     */
    public String generateCredentials ()throws SerException{
        //TODO: xiazhili 2017-03-10 未做自动生成记账凭证
        return null;
    }
    /**
     *租房申请汇总到租房信息中
     */
    public RentalApplyBO summary (RentalApplyTO rentalApplyTO)throws SerException{
        //TODO: xiazhili 2017-03-10 未做租房申请汇总到租房信息中
        return null;
    }

}
