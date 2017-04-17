package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AssistanceRecordBO;
import com.bjike.goddess.assistance.dto.AssistanceRecordDTO;
import com.bjike.goddess.assistance.entity.AssistanceRecord;
import com.bjike.goddess.assistance.to.AssistanceRecordTO;
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
 * 公司员工补助信息记录业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:07 ]
 * @Description: [ 公司员工补助信息记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class AssistanceRecordSerImpl extends ServiceImpl<AssistanceRecord, AssistanceRecordDTO> implements AssistanceRecordSer {

    @Override
    public Long countAssistanceRecord(AssistanceRecordDTO assistanceRecordDTO) throws SerException {

        assistanceRecordDTO.getSorts().add("createTime=desc");
        Long count = super.count(assistanceRecordDTO);
        return count;
    }

    @Override
    public List<AssistanceRecordBO> listAssistanceRecord(AssistanceRecordDTO assistanceRecordDTO) throws SerException {

        assistanceRecordDTO.getSorts().add("createTime=desc");
        List<AssistanceRecord> list = super.findByCis(assistanceRecordDTO, true);

        return BeanTransform.copyProperties(list, AssistanceRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceRecordBO addAssistanceRecord(AssistanceRecordTO assistanceRecordTO) throws SerException {

        AssistanceRecord assistanceRecord = BeanTransform.copyProperties(assistanceRecordTO, AssistanceRecord.class, true);

        assistanceRecord.setCreateTime(LocalDateTime.now());
        super.save(assistanceRecord);
        return BeanTransform.copyProperties(assistanceRecord, AssistanceRecordBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceRecordBO editAssistanceRecord(AssistanceRecordTO assistanceRecordTO) throws SerException {
        if (StringUtils.isBlank(assistanceRecordTO.getId())) {
            throw new SerException("id不能为空");
        }

        AssistanceRecord assistanceRecord = BeanTransform.copyProperties(assistanceRecordTO, AssistanceRecord.class, true);
        AssistanceRecord rs = super.findById(assistanceRecordTO.getId());

        rs.setEmpName(assistanceRecord.getEmpName());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AssistanceRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAssistanceRecord(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }



}