package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.BuyTicketStandardBO;
import com.bjike.goddess.buyticket.dto.BuyTicketStandardDTO;
import com.bjike.goddess.buyticket.entity.BuyTicketStandard;
import com.bjike.goddess.buyticket.to.BuyTicketStandardTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.transform.TupleSubsetResultTransformer;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 购票标准业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:40 ]
 * @Description: [ 购票标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class BuyTicketStandardSerImpl extends ServiceImpl<BuyTicketStandard, BuyTicketStandardDTO> implements BuyTicketStandardSer {

    @Override
    public Long countBuyTicketStandard(BuyTicketStandardDTO buyTicketStandardDTO) throws SerException {
        buyTicketStandardDTO.getSorts().add("createTime=desc");
        Long count = super.count(buyTicketStandardDTO);
        return count;
    }
    @Override
    public BuyTicketStandardBO getOne(String id) throws SerException {
        BuyTicketStandard buyTicketStandard = super.findById(id);
        return BeanTransform.copyProperties(buyTicketStandard,BuyTicketStandardBO.class,true);
    }

    @Override
    public List<BuyTicketStandardBO> findListBuyTicketStandard(BuyTicketStandardDTO buyTicketStandardDTO) throws SerException {
        List<BuyTicketStandard> buyTicketStandards = super.findByCis(buyTicketStandardDTO,true);
        List<BuyTicketStandardBO> buyTicketStandardBOS = BeanTransform.copyProperties(buyTicketStandards,BuyTicketStandardBO.class);
        return buyTicketStandardBOS;
    }

    @Override
    public BuyTicketStandardBO insertBuyTicketStandard(BuyTicketStandardTO buyTicketStandardTO) throws SerException {
        BuyTicketStandard buyTicketStandard = BeanTransform.copyProperties(buyTicketStandardTO,BuyTicketStandard.class,true);
        buyTicketStandard.setModifyTime(LocalDateTime.now());
        super.save(buyTicketStandard);
        return BeanTransform.copyProperties(buyTicketStandard,BuyTicketStandardBO.class);
    }

    @Override
    public BuyTicketStandardBO editBuyTicketStandard(BuyTicketStandardTO buyTicketStandardTO) throws SerException {
        BuyTicketStandard buyTicketStandard = super.findById(buyTicketStandardTO.getId());
        BeanTransform.copyProperties(buyTicketStandardTO,buyTicketStandard, true);
        buyTicketStandard.setModifyTime(LocalDateTime.now());
        super.update(buyTicketStandard);
        return BeanTransform.copyProperties(buyTicketStandardTO,BuyTicketStandardBO.class);
    }

    @Override
    public void removeBuyTicketStandard(String id) throws SerException {
        super.remove(id);
    }
}