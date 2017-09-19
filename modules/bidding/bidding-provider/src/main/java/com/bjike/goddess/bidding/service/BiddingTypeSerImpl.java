package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingTypeBO;
import com.bjike.goddess.bidding.dto.BiddingTypeDTO;
import com.bjike.goddess.bidding.entity.BiddingType;
import com.bjike.goddess.bidding.to.BiddingTypeTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.enums.RangeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 招投标类型业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:24 ]
 * @Description: [ 招投标类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "biddingSerCache")
@Service
public class BiddingTypeSerImpl extends ServiceImpl<BiddingType, BiddingTypeDTO> implements BiddingTypeSer {

    @Override
    public Boolean sonPermission() throws SerException {
        return null;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    @Override
    public Long count(BiddingTypeDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public BiddingTypeBO getOne(String id) throws SerException {
        BiddingType biddingType = super.findById(id);
        return BeanTransform.copyProperties(biddingType, BiddingTypeBO.class);
    }

    @Override
    public List<BiddingTypeBO> list(BiddingTypeDTO dto) throws SerException {
        List<BiddingType> biddingTypes = super.findByCis(dto);
        List<BiddingTypeBO> biddingTypeBOS = BeanTransform.copyProperties(biddingTypes, BiddingTypeBO.class);
        return biddingTypeBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingTypeBO save(BiddingTypeTO to) throws SerException {
        BiddingType biddingType = BeanTransform.copyProperties(to, BiddingType.class, true);
        biddingType.setCreateTime(LocalDateTime.now());
        super.save(biddingType);
        BiddingTypeBO bo = BeanTransform.copyProperties(biddingType, BiddingTypeBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingTypeBO edit(BiddingTypeTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            BiddingType biddingType = super.findById(to.getId());
            BeanTransform.copyProperties(to,biddingType,true);
            biddingType.setModifyTime(LocalDateTime.now());
            super.update(biddingType);
            BiddingTypeBO bo = BeanTransform.copyProperties(biddingType, BiddingTypeBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }

    }

    @Override
    public List<String> getType() throws SerException {
        String[] fields = new String[]{"biddingType"};
        String sql = " SELECT  biddingType AS biddingType FROM bidding_biddingtype GROUP BY biddingType ";
        List<BiddingType> biddingTypes = super.findBySql(sql,BiddingType.class,fields);
        List<String> typeList = biddingTypes.stream().map(BiddingType::getBiddingType).collect(Collectors.toList());
        return typeList;
    }
}