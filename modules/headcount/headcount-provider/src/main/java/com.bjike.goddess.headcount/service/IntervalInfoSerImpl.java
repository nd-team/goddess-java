package com.bjike.goddess.headcount.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.headcount.bo.IntervalInfoBO;
import com.bjike.goddess.headcount.dto.IntervalInfoDTO;
import com.bjike.goddess.headcount.entity.plancost.IntervalInfo;
import com.bjike.goddess.headcount.to.IntervalInfoTO;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-09 11:26 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class IntervalInfoSerImpl extends ServiceImpl<IntervalInfo,IntervalInfoDTO> implements IntervalInfoSer {

    @Override
    @Transactional(rollbackFor = SerException.class)
    public IntervalInfoBO saveByTO(TransactionContext txContext, IntervalInfoTO intervalInfoTO ) throws SerException{
        IntervalInfo intervalInfo = BeanTransform.copyProperties(intervalInfoTO,IntervalInfo.class,true);
        super.save(intervalInfo);
        intervalInfoTO.setId(intervalInfo.getId());
        return BeanTransform.copyProperties(intervalInfoTO,IntervalInfoBO.class);
    }

    @Override
    public List<IntervalInfoBO> list()throws SerException{
        List<IntervalInfo> intervalInfos = super.findAll();
        List<IntervalInfoBO> intervalInfoBOs = BeanTransform.copyProperties(intervalInfos,IntervalInfoBO.class);
        return intervalInfoBOs;
    }
    @Override
    public void update(IntervalInfoTO intervalInfoTO)throws SerException{
       IntervalInfo intervalInfo = super.findById(intervalInfoTO.getId());
        BeanTransform.copyProperties(intervalInfoTO,intervalInfo,true);
        intervalInfo.setModifyTime(LocalDateTime.now());
        super.update(intervalInfo);

    }
}
