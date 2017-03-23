package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingWebInfoBO;
import com.bjike.goddess.bidding.to.BiddingWebInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.bidding.dto.BiddingWebInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingWebInfo;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 招投标网站信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T10:15:43.331 ]
 * @Description: [ 招投标网站信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "biddingWebInfoSerCache")
@Service
public class BiddingWebInfoSerImpl extends ServiceImpl<BiddingWebInfo, BiddingWebInfoDTO> implements BiddingWebInfoSer {
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingWebInfoBO insertBiddingWebInfo(BiddingWebInfoTO biddingWebInfoTO) throws SerException {
        BiddingWebInfo biddingWebInfo = BeanTransform.copyProperties(biddingWebInfoTO, BiddingWebInfo.class, true);
        try {
            biddingWebInfo.setCreateTime(LocalDateTime.now());
            super.save(biddingWebInfo);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(biddingWebInfo, BiddingWebInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingWebInfoBO editBiddingWebInfo(BiddingWebInfoTO biddingWebInfoTO) throws SerException {
        BiddingWebInfo biddingWebInfo = BeanTransform.copyProperties(biddingWebInfoTO, BiddingWebInfo.class, true);
        try {
            biddingWebInfo.setModifyTime(LocalDateTime.now());
            super.update(biddingWebInfo);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(biddingWebInfo, BiddingWebInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBiddingWebInfo(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<BiddingWebInfoBO> findListBiddingWebInfo(BiddingWebInfoDTO biddingWebInfoDTO) throws SerException {
        List<BiddingWebInfo> biddingWebInfos = super.findByCis(biddingWebInfoDTO,true);
        return BeanTransform.copyProperties(biddingWebInfos,BiddingWebInfoBO.class);
    }

}