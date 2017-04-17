package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AgeAssistBO;
import com.bjike.goddess.assistance.dto.AgeAssistDTO;
import com.bjike.goddess.assistance.entity.AgeAssist;
import com.bjike.goddess.assistance.to.AgeAssistTO;
import com.bjike.goddess.common.api.dto.Restrict;
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
 * 工龄补助业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:59 ]
 * @Description: [ 工龄补助业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class AgeAssistSerImpl extends ServiceImpl<AgeAssist, AgeAssistDTO> implements AgeAssistSer {

    @Override
    public Long countAgeAssist(AgeAssistDTO ageAssistDTO) throws SerException {
        if (StringUtils.isNotBlank(ageAssistDTO.getArea())) {
            ageAssistDTO.getConditions().add(Restrict.eq("area", ageAssistDTO.getArea()));
        }
        if (StringUtils.isNotBlank(ageAssistDTO.getProjectGroup())) {
            ageAssistDTO.getConditions().add(Restrict.eq("projectGroup", ageAssistDTO.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(ageAssistDTO.getEmpName())) {
            ageAssistDTO.getConditions().add(Restrict.eq("empName", ageAssistDTO.getEmpName()));
        }
        ageAssistDTO.getSorts().add("createTime=desc");
        Long count = super.count(ageAssistDTO);
        return count;
    }

    @Override
    public List<AgeAssistBO> listAgeAssist(AgeAssistDTO ageAssistDTO) throws SerException {
        if (StringUtils.isNotBlank(ageAssistDTO.getArea())) {
            ageAssistDTO.getConditions().add(Restrict.eq("area", ageAssistDTO.getArea()));
        }
        if (StringUtils.isNotBlank(ageAssistDTO.getProjectGroup())) {
            ageAssistDTO.getConditions().add(Restrict.eq("projectGroup", ageAssistDTO.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(ageAssistDTO.getEmpName())) {
            ageAssistDTO.getConditions().add(Restrict.eq("empName", ageAssistDTO.getEmpName()));
        }
        ageAssistDTO.getSorts().add("createTime=desc");
        List<AgeAssist> list = super.findByCis(ageAssistDTO, true);

        return BeanTransform.copyProperties(list, AgeAssistBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AgeAssistBO addAgeAssist(AgeAssistTO ageAssistTO) throws SerException {
        if( ageAssistTO.getJobAge()==null || ageAssistTO.getJobAge().isNaN()){
            ageAssistTO.setJobAge( 0d );
        }
        AgeAssist ageAssist = BeanTransform.copyProperties(ageAssistTO, AgeAssist.class, true);

        ageAssist.setCreateTime(LocalDateTime.now());
        super.save(ageAssist);
        return BeanTransform.copyProperties(ageAssist, AgeAssistBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public AgeAssistBO editAgeAssist(AgeAssistTO ageAssistTO) throws SerException {
        if (StringUtils.isBlank(ageAssistTO.getId())) {
            throw new SerException("id不能为空");
        }
        if( ageAssistTO.getJobAge()==null || ageAssistTO.getJobAge().isNaN()){
            ageAssistTO.setJobAge( 0d );
        }
        AgeAssist ageAssist = BeanTransform.copyProperties(ageAssistTO, AgeAssist.class, true);
        AgeAssist rs = super.findById(ageAssistTO.getId());

        rs.setEmpName(ageAssist.getEmpName());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AgeAssistBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAgeAssist(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }


}