package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.bo.RecordBO;
import com.bjike.goddess.task.dto.RecordDTO;
import com.bjike.goddess.task.entity.Record;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内部协助单记录业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:30 ]
 * @Description: [ 内部协助单记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskSerCache")
@Service
public class RecordSerImpl extends ServiceImpl<Record, RecordDTO> implements RecordSer {
    @Override
    public List<RecordBO> list(RecordDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Record> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, RecordBO.class);
    }

    @Override
    public Long count(RecordDTO dto) throws SerException {
        return super.count(dto);
    }
}