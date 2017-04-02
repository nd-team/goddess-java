package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.dto.HierarchyDTO;
import com.bjike.goddess.organize.entity.Hierarchy;
import com.bjike.goddess.organize.to.HierarchyTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 体系业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:30]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class HierarchySerImpl extends ServiceImpl<Hierarchy, HierarchyDTO> implements HierarchySer {

    @Override
    public List<HierarchyBO> findStatus() throws SerException {
        HierarchyDTO dto = new HierarchyDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Hierarchy> list = super.findByCis(dto);
        List<HierarchyBO> bos = BeanTransform.copyProperties(list, HierarchyBO.class);
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HierarchyBO save(HierarchyTO to) throws SerException {
        Hierarchy hierarchy = BeanTransform.copyProperties(to, Hierarchy.class);
        hierarchy.setCreateTime(LocalDateTime.now());
        hierarchy.setStatus(Status.THAW);
        super.save(hierarchy);
        return BeanTransform.copyProperties(hierarchy, HierarchyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HierarchyBO update(HierarchyTO to) throws SerException {
        Hierarchy hierarchy = super.findById(to.getId());
        hierarchy.setDescription(to.getDescription());
        hierarchy.setHierarchy(to.getHierarchy());
        hierarchy.setSerialNumber(to.getSerialNumber());
        super.save(hierarchy);
        return BeanTransform.copyProperties(hierarchy, HierarchyBO.class);
    }


}
