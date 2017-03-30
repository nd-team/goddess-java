package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.DesignNumberInfoBO;
import com.bjike.goddess.organize.dto.DesignNumberInfoDTO;
import com.bjike.goddess.organize.entity.DesignNumberInfo;
import com.bjike.goddess.organize.to.DesignNumberInfoTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 编号设计信息业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:26]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class DesignNumberInfoSerImpl extends ServiceImpl<DesignNumberInfo, DesignNumberInfoDTO> implements DesignNumberInfoSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DesignNumberInfoBO save(DesignNumberInfoTO to) throws SerException {
        DesignNumberInfo designNumberInfo = BeanTransform.copyProperties(to, DesignNumberInfo.class);
        designNumberInfo.setCreateTime(LocalDateTime.now());
        super.save(designNumberInfo);
        return BeanTransform.copyProperties(designNumberInfo, DesignNumberInfoBO.class, true);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DesignNumberInfoBO update(DesignNumberInfoTO to) throws SerException {
        DesignNumberInfo designNumberInfo = super.findById(to.getId());
        designNumberInfo.setSerialNumber(to.getSerialNumber());
        designNumberInfo.setName(to.getName());
        designNumberInfo.setDescription(to.getDescription());
        designNumberInfo.setClassify(to.getClassify());
        super.update(designNumberInfo);
        return BeanTransform.copyProperties(designNumberInfo, DesignNumberInfoBO.class, true);
    }
}
