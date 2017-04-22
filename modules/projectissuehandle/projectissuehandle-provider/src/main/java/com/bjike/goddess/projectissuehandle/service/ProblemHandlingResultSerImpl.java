package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemHandlingResultDTO;
import com.bjike.goddess.projectissuehandle.entity.ProblemHandlingResult;
import com.bjike.goddess.projectissuehandle.to.ProblemHandlingResultTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 确认问题处理结果业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:30 ]
 * @Description: [ 确认问题处理结果业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectissuehandleSerCache")
@Service
public class ProblemHandlingResultSerImpl extends ServiceImpl<ProblemHandlingResult, ProblemHandlingResultDTO> implements ProblemHandlingResultSer {
    @Override
    public Long countProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        problemHandlingResultDTO.getSorts().add("createTime=desc");
        Long counts = super.count(problemHandlingResultDTO);
        return counts;
    }

    @Override
    public List<ProblemHandlingResultBO> findListProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        problemHandlingResultDTO.getSorts().add("createTime=desc");
        List<ProblemHandlingResult> problemHandlingResults = super.findByCis(problemHandlingResultDTO, true);
        List<ProblemHandlingResultBO> problemHandlingResultBOS = BeanTransform.copyProperties(problemHandlingResults, ProblemHandlingResultBO.class);
        return problemHandlingResultBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemHandlingResultBO insertProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
        ProblemHandlingResult problemHandlingResult = BeanTransform.copyProperties(problemHandlingResultTO, ProblemHandlingResult.class, true);
        problemHandlingResult.setCreateTime(LocalDateTime.now());
        super.save(problemHandlingResult);
        return BeanTransform.copyProperties(problemHandlingResult, ProblemHandlingResultBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemHandlingResultBO editProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
        ProblemHandlingResult problemHandlingResult = super.findById(problemHandlingResultTO.getId());
        BeanTransform.copyProperties(problemHandlingResultTO, problemHandlingResult, true);
        problemHandlingResult.setModifyTime(LocalDateTime.now());
        super.update(problemHandlingResult);
        return BeanTransform.copyProperties(problemHandlingResultTO, ProblemHandlingResultBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeProblemHandlingResult(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public String exportExcel(String internalProjectName, String projectType) throws SerException {
        //TODO: xiazhili 2017-03-25 未做导出
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<ProblemHandlingResultBO> searchProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        /**
         * 内部项目名称
         */
        if(StringUtils.isNotBlank(problemHandlingResultDTO.getInternalProjectName())){
            problemHandlingResultDTO.getConditions().add(Restrict.eq("internalProjectName", problemHandlingResultDTO.getInternalProjectName()));
        }
        /**
         * 工程类型
         */
        if(StringUtils.isNotBlank(problemHandlingResultDTO.getProjectType())){
            problemHandlingResultDTO.getConditions().add(Restrict.eq("projectType",problemHandlingResultDTO.getProjectType()));
        }
        /**
         * 问题对象
         */
        if(problemHandlingResultDTO.getProblemObject()!=null){
            problemHandlingResultDTO.getConditions().add(Restrict.eq("problemObject", problemHandlingResultDTO.getProblemObject()));
        }
        List<ProblemHandlingResult> problemHandlingResultList = super.findByCis(problemHandlingResultDTO,true);
        List<ProblemHandlingResultBO> problemHandlingResultBOList = BeanTransform.copyProperties(problemHandlingResultList,ProblemHandlingResultBO.class);
        return problemHandlingResultBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void upload() throws SerException {
        //TODO: xiazhili 2017-03-25 未做上传
        return;

    }

}