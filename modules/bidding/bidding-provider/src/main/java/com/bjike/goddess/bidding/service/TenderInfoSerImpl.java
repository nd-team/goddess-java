package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.TenderInfoBO;
import com.bjike.goddess.bidding.dto.BidOpeningInfoDTO;
import com.bjike.goddess.bidding.to.TenderInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.bidding.dto.TenderInfoDTO;
import com.bjike.goddess.bidding.entity.TenderInfo;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 标书资料业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.338 ]
 * @Description: [ 标书资料业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "biddingSerCache")
@Service
public class TenderInfoSerImpl extends ServiceImpl<TenderInfo, TenderInfoDTO> implements TenderInfoSer {

    @Override
    public Long countTenderInfo(TenderInfoDTO tenderInfoDTO) throws SerException {
        tenderInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(tenderInfoDTO);
        return count;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<TenderInfoBO> findListTenderInfo(TenderInfoDTO tenderInfoDTO) throws SerException {
        tenderInfoDTO.getSorts().add("createTime=desc");
        List<TenderInfo> tenderInfo = super.findByCis(tenderInfoDTO,true);
        List<TenderInfoBO> tenderInfoBOS = BeanTransform.copyProperties(tenderInfo,TenderInfoBO.class);
        return tenderInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TenderInfoBO insertTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        TenderInfo tenderInfo = BeanTransform.copyProperties(tenderInfoTO, TenderInfo.class, true);
        tenderInfo.setModifyTime(LocalDateTime.now());
        super.save(tenderInfo);
        return BeanTransform.copyProperties(tenderInfo, TenderInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TenderInfoBO editTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        TenderInfo tenderInfo = super.findById(tenderInfoTO.getId());
        BeanTransform.copyProperties(tenderInfoTO, tenderInfo, true);
        tenderInfo.setModifyTime(LocalDateTime.now());
        super.update(tenderInfo);
        return BeanTransform.copyProperties(tenderInfoTO, TenderInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeTenderInfo(String id) throws SerException {
        if(StringUtils.isNotBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public String exportExcel(String projectName) throws SerException {
        //TODO: xiazhili 2017-03-17 未做导出
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void upload() throws SerException {
        //TODO: xiazhili 2017-03-17 未做上传
        return;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void uploadAttachments() throws SerException {
        //TODO: xiazhili 2017-03-17 未做上传附件
        return;
    }
}