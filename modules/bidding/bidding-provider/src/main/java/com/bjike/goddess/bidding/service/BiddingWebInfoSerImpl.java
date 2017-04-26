package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingWebInfoBO;
import com.bjike.goddess.bidding.to.BiddingWebInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.bidding.dto.BiddingWebInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingWebInfo;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
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
@CacheConfig(cacheNames = "biddingSerCache")
@Service
public class BiddingWebInfoSerImpl extends ServiceImpl<BiddingWebInfo, BiddingWebInfoDTO> implements BiddingWebInfoSer {
    @Override
    public Long countBiddingWebInfo(BiddingWebInfoDTO biddingWebInfoDTO) throws SerException {
        biddingWebInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(biddingWebInfoDTO);
        return count;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<BiddingWebInfoBO> findListBiddingWebInfo(BiddingWebInfoDTO biddingWebInfoDTO) throws SerException {
        biddingWebInfoDTO.getSorts().add("createTime=desc");
        List<BiddingWebInfo> biddingWebInfos = super.findByCis(biddingWebInfoDTO,true);
        List<BiddingWebInfoBO> biddingWebInfoBOS = BeanTransform.copyProperties(biddingWebInfos,BiddingWebInfoBO.class);
        return biddingWebInfoBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingWebInfoBO insertBiddingWebInfo(BiddingWebInfoTO biddingWebInfoTO) throws SerException {
        BiddingWebInfo biddingWebInfo = BeanTransform.copyProperties(biddingWebInfoTO, BiddingWebInfo.class, true);
        biddingWebInfo.setId(biddingWebInfoTO.getId());
        super.save(biddingWebInfo);
        return BeanTransform.copyProperties(biddingWebInfo, BiddingWebInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingWebInfoBO editBiddingWebInfo(BiddingWebInfoTO biddingWebInfoTO) throws SerException {
        BiddingWebInfo biddingWebInfo = super.findById(biddingWebInfoTO.getId());
        BeanTransform.copyProperties(biddingWebInfoTO, BiddingWebInfo.class, true);
        biddingWebInfo.setModifyTime(LocalDateTime.now());
        super.update(biddingWebInfo);
        return BeanTransform.copyProperties(biddingWebInfoTO, BiddingWebInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBiddingWebInfo(String id) throws SerException {
        if(StringUtils.isNotBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

}