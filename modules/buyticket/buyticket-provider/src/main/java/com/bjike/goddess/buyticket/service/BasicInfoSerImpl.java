package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.BasicInfoBO;
import com.bjike.goddess.buyticket.dto.BasicInfoDTO;
import com.bjike.goddess.buyticket.entity.BasicInfo;
import com.bjike.goddess.buyticket.to.BasicInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 基本信息设置业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:14 ]
 * @Description: [ 基本信息设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class BasicInfoSerImpl extends ServiceImpl<BasicInfo, BasicInfoDTO> implements BasicInfoSer {

    @Override
    public Long countBasicInfo(BasicInfoDTO basicInfoDTO) throws SerException {
        basicInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(basicInfoDTO);
        return count;
    }
    @Override
    public BasicInfoBO getOne(String id) throws SerException {
        BasicInfo basicInfo = super.findById(id);
        return BeanTransform.copyProperties(basicInfo,BasicInfoBO.class,true);
    }

    @Override
    public List<BasicInfoBO> findListBasicInfo(BasicInfoDTO basicInfoDTO) throws SerException {
        basicInfoDTO.getSorts().add("createTime=desc");
        List<BasicInfo> basicInfos = super.findByCis(basicInfoDTO,true);
        List<BasicInfoBO> basicInfoBOS = BeanTransform.copyProperties(basicInfos,BasicInfoBO.class,true);
        return basicInfoBOS;
    }

    @Override
    public BasicInfoBO insertBasicInfo(BasicInfoTO basicInfoTO) throws SerException {
        BasicInfo basicInfo = BeanTransform.copyProperties(basicInfoTO,BasicInfo.class,true);
        basicInfo.setCreateTime(LocalDateTime.now());
        super.save(basicInfo);
        return BeanTransform.copyProperties(basicInfo,BasicInfoBO.class);
    }

    @Override
    public BasicInfoBO editBasicInfo(BasicInfoTO basicInfoTO) throws SerException {
        BasicInfo basicInfo = super.findById(basicInfoTO.getId());
        BeanTransform.copyProperties(basicInfoTO,basicInfo,true);
        basicInfo.setModifyTime(LocalDateTime.now());
        super.update(basicInfo);
        return BeanTransform.copyProperties(basicInfoTO,BasicInfoBO.class);
    }

    @Override
    public void removeBasicInfo(String id) throws SerException {
        super.remove(id);

    }
}