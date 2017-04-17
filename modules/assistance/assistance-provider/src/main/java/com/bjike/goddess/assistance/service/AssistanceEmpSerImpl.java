package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AssistanceEmpBO;
import com.bjike.goddess.assistance.dto.AssistanceEmpDTO;
import com.bjike.goddess.assistance.entity.AssistanceEmp;
import com.bjike.goddess.assistance.to.AssistanceEmpTO;
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
 * 补助员工名单业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:12 ]
 * @Description: [ 补助员工名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class AssistanceEmpSerImpl extends ServiceImpl<AssistanceEmp, AssistanceEmpDTO> implements AssistanceEmpSer {


    @Override
    public Long countAssistanceEmp(AssistanceEmpDTO assistanceEmpDTO) throws SerException {

        assistanceEmpDTO.getSorts().add("createTime=desc");
        Long count = super.count(assistanceEmpDTO);
        return count;
    }

    @Override
    public List<AssistanceEmpBO> listAssistanceEmp(AssistanceEmpDTO assistanceEmpDTO) throws SerException {

        assistanceEmpDTO.getSorts().add("createTime=desc");
        List<AssistanceEmp> list = super.findByCis(assistanceEmpDTO, true);

        return BeanTransform.copyProperties(list, AssistanceEmpBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceEmpBO addAssistanceEmp(AssistanceEmpTO assistanceEmpTO) throws SerException {

        AssistanceEmp assistanceEmp = BeanTransform.copyProperties(assistanceEmpTO, AssistanceEmp.class, true);

        assistanceEmp.setCreateTime(LocalDateTime.now());
        super.save(assistanceEmp);
        return BeanTransform.copyProperties(assistanceEmp, AssistanceEmpBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceEmpBO editAssistanceEmp(AssistanceEmpTO assistanceEmpTO) throws SerException {
        if (StringUtils.isBlank(assistanceEmpTO.getId())) {
            throw new SerException("id不能为空");
        }
        AssistanceEmp assistanceEmp = BeanTransform.copyProperties(assistanceEmpTO, AssistanceEmp.class, true);
        AssistanceEmp rs = super.findById(assistanceEmpTO.getId());

        rs.setEmpName(assistanceEmp.getEmpName());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AssistanceEmpBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAssistanceEmp(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

}