package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AssistanceStandardBO;
import com.bjike.goddess.assistance.dto.AssistanceStandardDTO;
import com.bjike.goddess.assistance.entity.AssistanceStandard;
import com.bjike.goddess.assistance.to.AssistanceStandardTO;
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
 * 补助标准业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:29 ]
 * @Description: [ 补助标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class AssistanceStandardSerImpl extends ServiceImpl<AssistanceStandard, AssistanceStandardDTO> implements AssistanceStandardSer {


    @Override
    public Long countAssistanceStandard(AssistanceStandardDTO assistanceStandardDTO) throws SerException {
        if( StringUtils.isNotBlank(assistanceStandardDTO.getName() )){
            assistanceStandardDTO.getConditions().add(Restrict.like("name",assistanceStandardDTO.getName() ));
        }
        assistanceStandardDTO.getSorts().add("createTime=desc");
        Long count = super.count(assistanceStandardDTO);
        return count;
    }

    @Override
    public AssistanceStandardBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        AssistanceStandard  list = super.findById(id);

        return BeanTransform.copyProperties(list, AssistanceStandardBO.class );
    }

    @Override
    public List<AssistanceStandardBO> listAssistanceStandard(AssistanceStandardDTO assistanceStandardDTO) throws SerException {
        if( StringUtils.isNotBlank(assistanceStandardDTO.getName() )){
            assistanceStandardDTO.getConditions().add(Restrict.like("name",assistanceStandardDTO.getName() ));
        }
        assistanceStandardDTO.getSorts().add("createTime=desc");
        List<AssistanceStandard> list = super.findByCis(assistanceStandardDTO,true);

        return BeanTransform.copyProperties(list, AssistanceStandardBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceStandardBO addAssistanceStandard(AssistanceStandardTO assistanceStandardTO) throws SerException {
        AssistanceStandard assistanceStandard = BeanTransform.copyProperties(assistanceStandardTO,AssistanceStandard.class,true);
        assistanceStandard.setCreateTime(LocalDateTime.now());
        super.save( assistanceStandard );
        return BeanTransform.copyProperties(assistanceStandard, AssistanceStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceStandardBO editAssistanceStandard(AssistanceStandardTO assistanceStandardTO) throws SerException {
        AssistanceStandard assistanceStandard = BeanTransform.copyProperties(assistanceStandardTO,AssistanceStandard.class,true);
        AssistanceStandard rs = super.findById( assistanceStandardTO.getId() );

        rs.setName( assistanceStandard.getName() );
        rs.setRemark( assistanceStandard.getRemark() );
        rs.setStandardForm( assistanceStandard.getStandardForm() );
        rs.setModifyTime(LocalDateTime.now());
        super.update( rs );
        return BeanTransform.copyProperties(rs, AssistanceStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAssistanceStandard(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        AssistanceStandard assistanceStandard = super.findById( id );
        if(assistanceStandard != null ){
            super.remove( id );
        }else{
            throw new SerException("删除的对象不存在");
        }
    }

    @Override
    public List<AssistanceStandardBO> getAgeStands() throws SerException {
        AssistanceStandardDTO assistanceStandardDTO = new AssistanceStandardDTO();
        assistanceStandardDTO.getConditions().add(Restrict.like("name","工龄补助"));
        List<AssistanceStandard> list = super.findByCis( assistanceStandardDTO );
        return BeanTransform.copyProperties(list,AssistanceStandardBO.class);
    }
}