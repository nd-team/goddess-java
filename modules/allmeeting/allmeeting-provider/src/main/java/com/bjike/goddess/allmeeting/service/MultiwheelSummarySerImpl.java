package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.MultiwheelSummaryBO;
import com.bjike.goddess.allmeeting.dto.MultiwheelSummaryDTO;
import com.bjike.goddess.allmeeting.entity.MultiwheelSummary;
import com.bjike.goddess.allmeeting.to.MultiwheelSummaryTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 简洁交流讨论纪要业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:44 ]
 * @Description: [ 简洁交流讨论纪要业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class MultiwheelSummarySerImpl extends ServiceImpl<MultiwheelSummary, MultiwheelSummaryDTO> implements MultiwheelSummarySer {

    @Override
    public MultiwheelSummaryBO updateModel(MultiwheelSummaryTO to) throws SerException {
        MultiwheelSummary model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, MultiwheelSummaryBO.class);
    }

    @Override
    public void freeze(String id) throws SerException {
        MultiwheelSummary model = super.findById(id);
        if (model != null) {
            model.setModifyTime(LocalDateTime.now());
            model.setStatus(Status.CONGEAL);
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    public List<MultiwheelSummaryBO> pageList(MultiwheelSummaryDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), MultiwheelSummaryBO.class);
    }
}