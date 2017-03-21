package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.TenderInfoBO;
import com.bjike.goddess.bidding.to.TenderInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.bidding.dto.TenderInfoDTO;
import com.bjike.goddess.bidding.entity.TenderInfo;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
@CacheConfig(cacheNames = "tenderInfoSerCache")
@Service
public class TenderInfoSerImpl extends ServiceImpl<TenderInfo, TenderInfoDTO> implements TenderInfoSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TenderInfoBO insertTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        TenderInfo tenderInfo = BeanTransform.copyProperties(tenderInfoTO, TenderInfo.class, true);
        try {
            tenderInfo.setCreateTime(LocalDateTime.now());
            super.save(tenderInfo);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(tenderInfo, TenderInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TenderInfoBO editTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        TenderInfo tenderInfo = BeanTransform.copyProperties(tenderInfoTO, TenderInfo.class, true);
        try {
            tenderInfo.setModifyTime(LocalDateTime.now());
            super.update(tenderInfo);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(tenderInfo, TenderInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeTenderInfo(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<TenderInfo> findListTenderInfo(TenderInfoDTO tenderInfoDTO) throws SerException {
        //TODO: xiazhili 2017-03-17 未做根据 biddingWebInfoDTO 分页查询所有
        List<TenderInfo> tenderInfo = super.findByPage(tenderInfoDTO);
        return tenderInfo;
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