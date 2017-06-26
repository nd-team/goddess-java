package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalPreceptDTO;
import com.bjike.goddess.accommodation.entity.RentalPrecept;
import com.bjike.goddess.accommodation.enums.PassStatus;
import com.bjike.goddess.accommodation.to.RentalPreceptTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 租房方案 业务实现
 *
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [租房方案 业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "accommodationSerCache")
@Service
public class RentalPreceptSerImpl extends ServiceImpl<RentalPrecept, RentalPreceptDTO> implements RentalPreceptSer {

    @Autowired
    private UserAPI userAPI;
    @Override
    public Long countRentalPrecept(RentalPreceptDTO rentalPreceptDTO) throws SerException {
        Long count = super.count(rentalPreceptDTO);
        return count;
    }

    @Override
    public RentalPreceptBO getOne(String id) throws SerException {
        RentalPrecept rentalPrecept = super.findById(id);
        return BeanTransform.copyProperties(rentalPrecept, RentalPreceptBO.class);
    }

    @Override
    public List<RentalPreceptBO> findListRentalPrecept(RentalPreceptDTO rentalPreceptDTO) throws SerException {
        List<RentalPrecept> rentalPrecepts = super.findByCis(rentalPreceptDTO, true);
        List<RentalPreceptBO> rentalPreceptBOS = BeanTransform.copyProperties(rentalPrecepts, RentalPreceptBO.class);
        return rentalPreceptBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalPreceptBO insertPecept(RentalPreceptTO preceptTO) throws SerException {

        RentalPrecept precept = BeanTransform.copyProperties(preceptTO, RentalPrecept.class, true);
        precept.setCreateTime(LocalDateTime.now());
        super.save(precept);
        return BeanTransform.copyProperties(precept, RentalPreceptBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalPreceptBO editPecept(RentalPreceptTO preceptTO) throws SerException {
        if (!StringUtils.isEmpty(preceptTO.getId())) {
            RentalPrecept rentalPrecept = super.findById(preceptTO.getId());
            BeanTransform.copyProperties(preceptTO, rentalPrecept, true);
            rentalPrecept.setModifyTime(LocalDateTime.now());
            super.update(rentalPrecept);
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(preceptTO, RentalPreceptBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removePecept(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }


    @Override
    public RentalPreceptBO manageAudit(RentalPreceptTO preceptTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        RentalPrecept precept = super.findById(preceptTO.getId());

        precept.setManage(userBO.getUsername());
        precept.setManageOpinion(preceptTO.getManageOpinion());
        precept.setManagePass(preceptTO.getManagePass());
        if("是".equals(preceptTO.getManagePass())){
            precept.setManagePass(String.valueOf(PassStatus.MANAGEPASS));
        }else if("否".equals(preceptTO.getManagePass())){
            precept.setManagePass(String.valueOf(PassStatus.MANAGENOPASS));
        }
        return  null;
    }

    @Override
    public RentalPreceptBO generalAudit(RentalPreceptTO preceptTO) throws SerException {
        return null;
    }
}
