package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemAcceptDTO;
import com.bjike.goddess.projectissuehandle.entity.ProblemAccept;
import com.bjike.goddess.projectissuehandle.to.ProblemAcceptTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目执行中的问题受理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:16 ]
 * @Description: [ 项目执行中的问题受理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectissuehandleSerCache")
@Service
public class ProblemAcceptSerImpl extends ServiceImpl<ProblemAccept, ProblemAcceptDTO> implements ProblemAcceptSer {
    @Override
    public Long countProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        problemAcceptDTO.getSorts().add("createTime=desc");
        Long counts = super.count(problemAcceptDTO);
        return counts;
    }

    @Override
    public List<ProblemAcceptBO> findListProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        problemAcceptDTO.getSorts().add("createTime=desc");
        List<ProblemAccept> problemAccepts = super.findByCis(problemAcceptDTO, true);
        List<ProblemAcceptBO> problemAcceptBOS = BeanTransform.copyProperties(problemAccepts, ProblemAcceptBO.class);
        return problemAcceptBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemAcceptBO insertProblemAccept(ProblemAcceptTO problemAcceptTO) throws SerException {
        ProblemAccept problemAccept = BeanTransform.copyProperties(problemAcceptTO, ProblemAccept.class, true);
        problemAccept.setCreateTime(LocalDateTime.now());
        super.save(problemAccept);
        return BeanTransform.copyProperties(problemAccept, ProblemAcceptBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemAcceptBO editProblemAccept(ProblemAcceptTO problemAcceptTO) throws SerException {
        ProblemAccept problemAccept = super.findById(problemAcceptTO.getId());
        BeanTransform.copyProperties(problemAcceptTO, problemAccept, true);
        problemAccept.setModifyTime(LocalDateTime.now());
        super.update(problemAccept);
        return BeanTransform.copyProperties(problemAcceptTO, ProblemAcceptBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeProblemAccept(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public String exportExcel(String internalProjectName, String projectType) throws SerException {
        //TODO: xiazhili 2017-03-24 未做导出
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override

    public List<ProblemAcceptBO> searchProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        /**
         * 内部项目名称
         */
        if(StringUtils.isNotBlank(problemAcceptDTO.getInternalProjectName())){
            problemAcceptDTO.getConditions().add(Restrict.eq("internalProjectName", problemAcceptDTO.getInternalProjectName()));
        }
        /**
         * 工程类型
         */
        if(StringUtils.isNotBlank(problemAcceptDTO.getProjectType())){
            problemAcceptDTO.getConditions().add(Restrict.eq("projectType", problemAcceptDTO.getProjectType()));
        }
        List<ProblemAccept> problemAccepts = super.findByCis(problemAcceptDTO,true);
        List<ProblemAcceptBO> problemAcceptBOS = BeanTransform.copyProperties(problemAccepts,ProblemAcceptBO.class);
        return problemAcceptBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void upload() throws SerException {
        //TODO: xiazhili 2017-03-24 未做上传
        return;

    }

}