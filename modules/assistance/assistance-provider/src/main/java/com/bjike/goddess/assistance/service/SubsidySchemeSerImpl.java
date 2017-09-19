package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.SubsidySchemeBO;
import com.bjike.goddess.assistance.dto.SubsidySchemeDTO;
import com.bjike.goddess.assistance.entity.SubsidyScheme;
import com.bjike.goddess.assistance.to.SubsidySchemeTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公司补助方案业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 10:30 ]
 * @Description: [ 公司补助方案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class SubsidySchemeSerImpl extends ServiceImpl<SubsidyScheme, SubsidySchemeDTO> implements SubsidySchemeSer {
    @Override
    public Long countSubsidy(SubsidySchemeDTO subsidySchemeDTO) throws SerException {
        Long count = super.count(subsidySchemeDTO);
        return count;
    }

    @Override
    public SubsidySchemeBO getOneById(String id) throws SerException {
        SubsidyScheme subsidyScheme = super.findById(id);
        return BeanTransform.copyProperties(subsidyScheme, SubsidySchemeBO.class);
    }

    @Override
    public List<SubsidySchemeBO> listSubsidy(SubsidySchemeDTO subsidySchemeDTO) throws SerException {
        search(subsidySchemeDTO);
        subsidySchemeDTO.getSorts().add("schemeSeq=asc");
        List<SubsidyScheme> subsidySchemes = super.findByCis(subsidySchemeDTO);
        return BeanTransform.copyProperties(subsidySchemes, SubsidySchemeBO.class);
    }

    public void search(SubsidySchemeDTO subsidySchemeDTO) throws SerException {
        if (StringUtils.isNotBlank(subsidySchemeDTO.getSchemeName())) {
            subsidySchemeDTO.getConditions().add(Restrict.eq("schemeName", subsidySchemeDTO.getSchemeName()));
        }
        if (StringUtils.isNotBlank(subsidySchemeDTO.getSubsidyName())) {
            subsidySchemeDTO.getConditions().add(Restrict.eq("subsidyName", subsidySchemeDTO.getSubsidyName()));
        }
    }

    @Override
    public SubsidySchemeBO addSubsidy(SubsidySchemeTO subsidySchemeTO) throws SerException {
        SubsidyScheme subsidyScheme = BeanTransform.copyProperties(subsidySchemeTO, SubsidyScheme.class, true);
        subsidyScheme.setCreateTime(LocalDateTime.now());
        super.save(subsidyScheme);
        return BeanTransform.copyProperties(subsidyScheme, SubsidySchemeBO.class);
    }

    @Override
    public SubsidySchemeBO editSubsidy(SubsidySchemeTO subsidySchemeTO) throws SerException {
        SubsidyScheme subsidyScheme = super.findById(subsidySchemeTO.getId());
        if (!subsidyScheme.getImplement()) {
            LocalDateTime date = subsidyScheme.getCreateTime();
            subsidyScheme = BeanTransform.copyProperties(subsidySchemeTO, SubsidyScheme.class, true);
            subsidyScheme.setCreateTime(date);
            subsidyScheme.setModifyTime(LocalDateTime.now());
            super.update(subsidyScheme);
        } else {
            throw new SerException("该方案已被确定可以被实施,不能被修改");
        }
        return BeanTransform.copyProperties(subsidyScheme, SubsidySchemeBO.class);
    }

    @Override
    public void deleteSubsidy(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void manageAudit(SubsidySchemeTO subsidySchemeTO) throws SerException {
        SubsidyScheme subsidyScheme = super.findById(subsidySchemeTO.getId());
        if (!subsidyScheme.getImplement()){
            subsidyScheme.setManageOpinion(subsidySchemeTO.getManageOpinion());
            subsidyScheme.setImplement(subsidySchemeTO.getImplement());
            super.update(subsidyScheme);
        }else{
            throw new SerException("该方案已被确定可以被实施,审核过了");
        }
    }
}