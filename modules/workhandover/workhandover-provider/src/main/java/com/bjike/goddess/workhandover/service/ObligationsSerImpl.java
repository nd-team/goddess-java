package com.bjike.goddess.workhandover.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workhandover.bo.ObligationsBO;
import com.bjike.goddess.workhandover.dto.ObligationsDTO;
import com.bjike.goddess.workhandover.entity.Obligations;
import com.bjike.goddess.workhandover.to.ObligationsTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作交接时间规范业务实现
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-13 06:26 ]
 * @Description: [ 工作交接时间规范业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workhandoverSerCache")
@Service
public class ObligationsSerImpl extends ServiceImpl<Obligations, ObligationsDTO> implements ObligationsSer {

    @Override
    public List<ObligationsBO> findObligations(ObligationsDTO dto) throws SerException {
        dto.getSorts ().add ( "createTime=desc" );
        List<Obligations> transinfo = super.findByPage ( dto );
        List<ObligationsBO> bo = BeanTransform.copyProperties ( transinfo, ObligationsBO.class );
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ObligationsBO insertObligations(ObligationsTO to) throws SerException {
        Obligations obligations = BeanTransform.copyProperties ( to, Obligations.class, true );
        obligations.setCreateTime ( LocalDateTime.now () );
        super.save ( obligations );
        return BeanTransform.copyProperties ( obligations, ObligationsBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ObligationsBO editObligations(ObligationsTO to) throws SerException {
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeObligations(String id) throws SerException {

    }


}