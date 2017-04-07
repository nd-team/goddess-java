package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.bo.GiftStandardBO;
import com.bjike.goddess.festival.dto.GiftStandardDTO;
import com.bjike.goddess.festival.entity.GiftStandard;
import com.bjike.goddess.festival.to.GiftStandardTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 节假日礼品标准业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:02 ]
 * @Description: [ 节假日礼品标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "festivalSerCache")
@Service
public class GiftStandardSerImpl extends ServiceImpl<GiftStandard, GiftStandardDTO> implements GiftStandardSer {

    @Override
    public Long countGiftStandard(GiftStandardDTO giftStandardDTO) throws SerException {
        return super.count( giftStandardDTO );
    }

    @Override
    public List<GiftStandardBO> listGiftStandard(GiftStandardDTO giftStandardDTO) throws SerException {

        giftStandardDTO.getSorts().add("createTime=desc");
        List<GiftStandard> list = super.findByCis(giftStandardDTO);

        return BeanTransform.copyProperties(list, GiftStandardBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public GiftStandardBO addGiftStandard(GiftStandardTO giftStandardTO) throws SerException {
        GiftStandard giftStandard = BeanTransform.copyProperties(giftStandardTO,GiftStandard.class,true);
        giftStandard.setCreateTime(LocalDateTime.now());
        super.save( giftStandard );
        return BeanTransform.copyProperties(giftStandard, GiftStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public GiftStandardBO editGiftStandard(GiftStandardTO giftStandardTO) throws SerException {
        GiftStandard giftStandard = BeanTransform.copyProperties(giftStandardTO,GiftStandard.class,true);
        GiftStandard temp = super.findById( giftStandardTO.getId() );

        temp.setName( giftStandard.getName() );
        temp.setDescribeDetail( giftStandard.getDescribeDetail() );
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );
        return BeanTransform.copyProperties(giftStandard, GiftStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteGiftStandard(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<String> getGiftByFestivalName() throws SerException {
        String[] fields = new String[]{"name"};
        List<GiftStandardBO> giftStandardBOList = super.findBySql(
                "select name ,1 from festival_giftstandard where name  order by createTime desc ", GiftStandardBO.class, fields);

        List<String> list = giftStandardBOList.stream().map(GiftStandardBO::getName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return list;
    }
}