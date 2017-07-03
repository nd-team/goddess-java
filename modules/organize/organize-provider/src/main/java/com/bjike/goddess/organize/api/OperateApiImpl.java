package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.OperateBO;
import com.bjike.goddess.organize.dto.OperateDTO;
import com.bjike.goddess.organize.service.OperateSer;
import com.bjike.goddess.organize.to.OperateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作类型业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service("operateApiImpl")
public class OperateApiImpl implements OperateAPI {

    @Autowired
    private OperateSer operateSer;

    @Override
    public List<OperateBO> findStatus() throws SerException {
        return operateSer.findStatus();
    }

    @Override
    public OperateBO save(OperateTO to) throws SerException {
        return operateSer.save(to);
    }

    @Override
    public OperateBO update(OperateTO to) throws SerException {
        return operateSer.update(to);
    }

    @Override
    public OperateBO delete(String id) throws SerException {
        return operateSer.delete(id);
    }

    @Override
    public OperateBO close(String id) throws SerException {
        return operateSer.close(id);
    }

    @Override
    public OperateBO open(String id) throws SerException {
        return operateSer.open(id);
    }

    @Override
    public List<OperateBO> maps(OperateDTO dto) throws SerException {
        return operateSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        OperateDTO dto = new OperateDTO();
        return operateSer.count(dto);
    }

    @Override
    public OperateBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(operateSer.findById(id), OperateBO.class);
    }
}
