package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.RightSetBO;
import com.bjike.goddess.assistance.dto.RightSetDTO;
import com.bjike.goddess.assistance.entity.RightSet;
import com.bjike.goddess.assistance.to.RightSetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:14 ]
 * @Description: [ 权限设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class RightSetSerImpl extends ServiceImpl<RightSet, RightSetDTO> implements RightSetSer {

    @Override
    public Long countRightSet(RightSetDTO rightSetDTO) throws SerException {
        if( StringUtils.isNotBlank(rightSetDTO.getEmpName() )){
            rightSetDTO.getConditions().add(Restrict.like("empName",rightSetDTO.getEmpName() ));
        }
        rightSetDTO.getSorts().add("empRight=desc");
        Long count = super.count(rightSetDTO);
        return count;
    }

    @Override
    public List<RightSetBO> listRightSet(RightSetDTO rightSetDTO) throws SerException {
        if( StringUtils.isNotBlank(rightSetDTO.getEmpName() )){
            rightSetDTO.getConditions().add(Restrict.like("empName",rightSetDTO.getEmpName() ));
        }
        rightSetDTO.getSorts().add("empRight=desc");
        List<RightSet> list = super.findByCis(rightSetDTO,true);

        return BeanTransform.copyProperties(list, RightSetBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RightSetBO addRightSet(RightSetTO rightSetTO) throws SerException {
        RightSet rightSet = BeanTransform.copyProperties(rightSetTO,RightSet.class,true);
        rightSet.setCreateTime(LocalDateTime.now());
        super.save( rightSet );
        return BeanTransform.copyProperties(rightSet, RightSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RightSetBO editRightSet(RightSetTO rightSetTO) throws SerException {
        RightSet rightSet = BeanTransform.copyProperties(rightSetTO,RightSet.class,true);
        RightSet rs = super.findById( rightSetTO.getId() );

        rs.setEmpName( rightSet.getEmpName() );
        rs.setRemark( rightSet.getRemark() );
        rs.setEmpRight( rightSet.getEmpRight() );
        rs.setModifyTime(LocalDateTime.now());
        super.update( rs );
        return BeanTransform.copyProperties(rs, RightSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteRightSet(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove( id );
    }

}