package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.BuyTicketApplyBO;
import com.bjike.goddess.buyticket.dto.BuyTicketApplyDTO;
import com.bjike.goddess.buyticket.entity.BuyTicketApply;
import com.bjike.goddess.buyticket.enums.AuditorType;
import com.bjike.goddess.buyticket.to.BuyTicketApplyTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 车票购买申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:32 ]
 * @Description: [ 车票购买申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class BuyTicketApplySerImpl extends ServiceImpl<BuyTicketApply, BuyTicketApplyDTO> implements BuyTicketApplySer {

    @Override
    public Long countBuyTicketApply(BuyTicketApplyDTO buyTicketApplyDTO) throws SerException {
        buyTicketApplyDTO.getSorts().add("createTime=desc");
        Long count = super.count(buyTicketApplyDTO);
        return count;
    }
    @Override
    public BuyTicketApplyBO getOne(String id) throws SerException {
        BuyTicketApply buyTicketApply = super.findById(id);
        return BeanTransform.copyProperties(buyTicketApply,BuyTicketApplyBO.class,true);
    }

    @Override
    public List<BuyTicketApplyBO> findListBuyTicketApply(BuyTicketApplyDTO buyTicketApplyDTO) throws SerException {
        List<BuyTicketApply> buyTicketApplies = super.findByCis(buyTicketApplyDTO,true);
        List<BuyTicketApplyBO> buyTicketApplyBOS = BeanTransform.copyProperties(buyTicketApplies,BuyTicketApplyBO.class,true);
        return buyTicketApplyBOS;
    }

    @Override
    public BuyTicketApplyBO insertBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        BuyTicketApply buyTicketApply = BeanTransform.copyProperties(buyTicketApplyTO,BuyTicketApply.class,true);
        buyTicketApply.setCreateTime(LocalDateTime.now());
        super.save(buyTicketApply);
        return BeanTransform.copyProperties(buyTicketApply,BuyTicketApplyBO.class);
    }

    @Override
    public BuyTicketApplyBO editBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        BuyTicketApply buyTicketApply = super.findById(buyTicketApplyTO.getId());
        BeanTransform.copyProperties(buyTicketApplyTO,buyTicketApply,true);
        buyTicketApply.setModifyTime(LocalDateTime.now());
        super.update(buyTicketApply);
        return BeanTransform.copyProperties(buyTicketApply,BuyTicketApplyBO.class);
    }

    @Override
    public void removeBuyTicketApply(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public BuyTicketApplyBO auditBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        BuyTicketApplyDTO dto = new BuyTicketApplyDTO();
        BuyTicketApply buyTicketApply = BeanTransform.copyProperties(buyTicketApplyTO,BuyTicketApply.class,true);

        if(buyTicketApply.getAuditor().equals(AuditorType.PLANMODULE)){
            dto.getConditions().add(Restrict.eq("planmodule",AuditorType.PLANMODULE));
            buyTicketApply.setAuditOpinion(buyTicketApplyTO.getAuditOpinion());
        }
        if(buyTicketApply.getAuditor().equals(AuditorType.WELFAREMODULE)){
            dto.getConditions().add(Restrict.eq("welfaremodule",AuditorType.WELFAREMODULE));
            buyTicketApply.setAuditOpinion(buyTicketApplyTO.getAuditOpinion());
        }
        buyTicketApply.setModifyTime(LocalDateTime.now());
        super.save(buyTicketApply);
        return BeanTransform.copyProperties(buyTicketApply,BuyTicketApplyBO.class,true);
    }

    @Override
    public BuyTicketApplyBO sendBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        //todo:未做发送邮件
        return null;
    }
}