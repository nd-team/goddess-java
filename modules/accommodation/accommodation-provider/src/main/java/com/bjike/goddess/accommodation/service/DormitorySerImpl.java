package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.DormitoryBO;
import com.bjike.goddess.accommodation.dto.DormitoryDTO;
import com.bjike.goddess.accommodation.entity.Dormitory;
import com.bjike.goddess.accommodation.to.DormitoryTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 宿舍信息 业务实现
 *
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [宿舍信息 业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "accommodationSerCache")
@Service
public class DormitorySerImpl extends ServiceImpl<Dormitory,DormitoryDTO> implements DormitorySer{

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DormitoryBO insertDormitory(DormitoryTO dormitoryTO) throws SerException {
        Dormitory dormitory = BeanTransform.copyProperties(dormitoryTO,Dormitory.class,true);
        dormitory.setCreateTime(LocalDateTime.now());
        super.save(dormitory);
        return BeanTransform.copyProperties(dormitory, DormitoryBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public DormitoryBO editDormitory(DormitoryTO dormitoryTO) throws SerException {

        if(!StringUtils.isEmpty(dormitoryTO.getId())){
            Dormitory dormitory = super.findById(dormitoryTO.getId());
            BeanTransform.copyProperties(dormitoryTO,dormitory,true);
            dormitory.setModifyTime(LocalDateTime.now());
            super.update(dormitory);
        }else{
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(dormitoryTO,DormitoryBO.class);

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeDormitory(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }
    @Cacheable
    @Override
    public List<DormitoryBO> findListDormitory(DormitoryDTO dormitoryDTO) throws SerException {

        List<Dormitory> dormitories = super.findByCis(dormitoryDTO,true);
        return BeanTransform.copyProperties(dormitories,DormitoryBO.class);
    }

    /**
     * 导入数据
     */
    public void importFile() throws SerException {
        //TODO: xiazhili 2017-03-10 未做导入数据
    }
    /**
     * 导出宿舍信息明细
     */
    public String exportExcel(String area)throws SerException {
        //TODO: xiazhili 2017-03-10 未做导出宿舍信息明细
        return null;
    }
}
