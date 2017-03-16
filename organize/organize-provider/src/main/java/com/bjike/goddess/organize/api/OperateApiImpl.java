package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.OperateBO;
import com.bjike.goddess.organize.dto.OperateDTO;
import com.bjike.goddess.organize.entity.Operate;
import com.bjike.goddess.organize.service.OperateSer;
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
}
