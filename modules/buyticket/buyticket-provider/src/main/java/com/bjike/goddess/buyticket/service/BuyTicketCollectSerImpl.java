package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.BuyTicketCollectBO;
import com.bjike.goddess.buyticket.dto.BuyTicketCollectDTO;
import com.bjike.goddess.buyticket.entity.BuyTicketCollect;
import com.bjike.goddess.buyticket.enums.CollectCycle;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 车票购买汇总业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:13 ]
 * @Description: [ 车票购买汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class BuyTicketCollectSerImpl extends ServiceImpl<BuyTicketCollect, BuyTicketCollectDTO> implements BuyTicketCollectSer {
    @Override
    public List<BuyTicketCollectBO> collectByCollectCycle(BuyTicketCollectDTO buyTicketCollectDTO) throws SerException {
        String[] fields = new String[]{"area", "department", "collectType", "collectTypeData", "collectCycle", "collectStartTime", "collectEndTime", "presentType"};
        String sql = "";
        List<BuyTicketCollect> list = new ArrayList<>();
        if (buyTicketCollectDTO.getCollectCycle().equals(CollectCycle.WEEK)) {
            sql = "select area,department,collectType,collectTypeData, " +
                    " collectCycle,collectStartTime,collectEndTime,presentType " +
                    " from buyticket_buyticketcollect where collectCycle = '" + buyTicketCollectDTO.getCollectCycle() + "'";
            list = super.findBySql(sql, BuyTicketCollect.class, fields);
        }
        if (buyTicketCollectDTO.getCollectCycle().equals(CollectCycle.MONTH)) {
            sql = "select area,department,collectType,collectTypeData, " +
                    " collectCycle,collectStartTime,collectEndTime,presentType " +
                    " from buyticket_buyticketcollect where collectCycle = '" + buyTicketCollectDTO.getCollectCycle() + "'";
            list = super.findBySql(sql, BuyTicketCollect.class, fields);
        }
        if (buyTicketCollectDTO.getCollectCycle().equals(CollectCycle.YEAR)) {
            sql = "select area,department,collectType,collectTypeData, " +
                    " collectCycle,collectStartTime,collectEndTime,presentType " +
                    " from buyticket_buyticketcollect where collectCycle = '" + buyTicketCollectDTO.getCollectCycle() + "'";
            list = super.findBySql(sql, BuyTicketCollect.class, fields);
        }

        return BeanTransform.copyProperties(list, BuyTicketCollectBO.class);
    }
}