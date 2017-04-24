package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.DesignNumberInfoBO;
import com.bjike.goddess.organize.dto.DesignNumberInfoDTO;
import com.bjike.goddess.organize.entity.DesignNumberInfo;
import com.bjike.goddess.organize.to.DesignNumberInfoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
        return BeanTransform.copyProperties(designNumberInfo, DesignNumberInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DesignNumberInfoBO update(DesignNumberInfoTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        DesignNumberInfo entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, DesignNumberInfoBO.class);
    }

    @Override
    public DesignNumberInfoBO delete(String id) throws SerException {
        DesignNumberInfo entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, DesignNumberInfoBO.class);
    }

    @Override
    public List<DesignNumberInfoBO> maps(DesignNumberInfoDTO dto) throws SerException {
        dto.getSorts().add("serialNumber=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), DesignNumberInfoBO.class);
    }
}
