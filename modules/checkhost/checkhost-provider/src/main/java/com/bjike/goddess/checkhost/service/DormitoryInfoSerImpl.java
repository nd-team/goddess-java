package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.DormitoryInfoBO;
import com.bjike.goddess.checkhost.to.DormitoryInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.checkhost.dto.DormitoryInfoDTO;
import com.bjike.goddess.checkhost.entity.DormitoryInfo;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 宿舍信息管理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:02 ]
 * @Description: [ 宿舍信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkhostSerCache")
@Service
public class DormitoryInfoSerImpl extends ServiceImpl<DormitoryInfo, DormitoryInfoDTO> implements DormitoryInfoSer {

    @Cacheable
    @Override
    public List<DormitoryInfoBO> findListDormitoryInfo(DormitoryInfoDTO dormitoryInfoDTO) throws SerException {
        List<DormitoryInfo> dormitoryInfos = super.findByCis(dormitoryInfoDTO,true);
        return BeanTransform.copyProperties(dormitoryInfos,DormitoryInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DormitoryInfoBO insertDormitoryInfo(DormitoryInfoTO dormitoryInfoTO) throws SerException {
        DormitoryInfo dormitoryInfo = BeanTransform.copyProperties(dormitoryInfoTO,DormitoryInfo.class,true);
        dormitoryInfo.setCreateTime(LocalDateTime.now());
        super.save(dormitoryInfo);
        return BeanTransform.copyProperties(dormitoryInfo,DormitoryInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DormitoryInfoBO editDormitoryInfo(DormitoryInfoTO dormitoryInfoTO) throws SerException {
        if(!StringUtils.isEmpty(dormitoryInfoTO.getId())){
            DormitoryInfo dormitoryInfo = super.findById(dormitoryInfoTO.getId());
            BeanTransform.copyProperties(dormitoryInfoTO,dormitoryInfo,true);
            dormitoryInfo.setModifyTime(LocalDateTime.now());
            super.update(dormitoryInfo);
        }else{
            throw new SerException("更新ID不能为空");
        }
        return BeanTransform.copyProperties(dormitoryInfoTO,DormitoryInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeDormitoryInfo(String id) throws SerException {
        super.remove(id);
    }

}