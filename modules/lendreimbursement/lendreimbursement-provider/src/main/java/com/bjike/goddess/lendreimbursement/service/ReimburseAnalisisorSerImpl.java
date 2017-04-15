package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAnalisisorBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseAnalisisorDTO;
import com.bjike.goddess.lendreimbursement.entity.ReimburseAnalisisor;
import com.bjike.goddess.lendreimbursement.to.ReimburseAnalisisorTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.RollbackException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 报销分析人员表业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:51 ]
 * @Description: [ 报销分析人员表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class ReimburseAnalisisorSerImpl extends ServiceImpl<ReimburseAnalisisor, ReimburseAnalisisorDTO> implements ReimburseAnalisisorSer {


    @Override
    public Long countReimburseAnalisisor(ReimburseAnalisisorDTO reimburseAnalisisorDTO) throws SerException {
        reimburseAnalisisorDTO.getSorts().add("createTime=desc");
        Long count = super.count(reimburseAnalisisorDTO);
        return count;
    }

    @Override
    public List<ReimburseAnalisisorBO> listReimburseAnalisisor(ReimburseAnalisisorDTO reimburseAnalisisorDTO) throws SerException {
        reimburseAnalisisorDTO.getSorts().add("createTime=desc");
        List<ReimburseAnalisisor> list = super.findByCis(reimburseAnalisisorDTO,true);
        List<ReimburseAnalisisorBO> analisisorBOList = BeanTransform.copyProperties(list,ReimburseAnalisisorBO.class);
        return analisisorBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseAnalisisorBO addReimburseAnalisisor(ReimburseAnalisisorTO reimburseAnalisisorTO) throws SerException {
        ReimburseAnalisisor reimburseAnalisisor = BeanTransform.copyProperties(reimburseAnalisisorTO,ReimburseAnalisisor.class,true);
        reimburseAnalisisor.setCreateTime(LocalDateTime.now());

        super.save( reimburseAnalisisor );

        return BeanTransform.copyProperties(reimburseAnalisisor,ReimburseAnalisisorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseAnalisisorBO editReimburseAnalisisor(ReimburseAnalisisorTO reimburseAnalisisorTO) throws SerException {
        if(StringUtils.isBlank(reimburseAnalisisorTO.getId())){
            throw new SerException("id不能为空");
        }
        ReimburseAnalisisor temp = super.findById( reimburseAnalisisorTO.getId() );
        ReimburseAnalisisor reimburseAnalisisor = BeanTransform.copyProperties(reimburseAnalisisorTO,ReimburseAnalisisor.class,true);

        BeanUtils.copyProperties(reimburseAnalisisor,temp,"id","createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(reimburseAnalisisor);
        return BeanTransform.copyProperties(reimburseAnalisisor,ReimburseAnalisisorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteReimburseAnalisisor(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}