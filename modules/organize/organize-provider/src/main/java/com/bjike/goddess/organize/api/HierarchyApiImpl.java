package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.dto.HierarchyDTO;
import com.bjike.goddess.organize.service.HierarchySer;
import com.bjike.goddess.organize.to.HierarchyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("hierarchyApiImpl")
public class HierarchyApiImpl implements HierarchyAPI {

    @Autowired
    private HierarchySer hierarchySer;

    @Override
    public List<HierarchyBO> findStatus() throws SerException {
        return hierarchySer.findStatus();
    }

    @Override
    public HierarchyBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(hierarchySer.findById(id), HierarchyBO.class);
    }

    @Override
    public HierarchyBO save(HierarchyTO to) throws SerException {
        return hierarchySer.save(to);
    }

    @Override
    public HierarchyBO update(HierarchyTO to) throws SerException {
        return hierarchySer.update(to);
    }

    @Override
    public HierarchyBO delete(String id) throws SerException {
        return hierarchySer.delete(id);
    }

    @Override
    public HierarchyBO congeal(String id) throws SerException {
        return hierarchySer.congeal(id);
    }

    @Override
    public HierarchyBO thaw(String id) throws SerException {
        return hierarchySer.thaw(id);
    }

    @Override
    public List<HierarchyBO> maps(HierarchyDTO dto) throws SerException {
        return hierarchySer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        HierarchyDTO dto = new HierarchyDTO();
        return hierarchySer.count(dto);
    }
}
