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
    public ProblemHandlingResultBO searchProblemHandlingResult(String internalProjectName, String projectType, String problemObject) throws SerException {
        ProblemHandlingResultDTO dto = new ProblemHandlingResultDTO();
        dto.getConditions().add(Restrict.eq("internalProjectName", internalProjectName));
        dto.getConditions().add(Restrict.eq("projectType", projectType));
        dto.getConditions().add(Restrict.eq("problemObject", problemObject));
        ProblemHandlingResultBO problemHandlingResultBO = BeanTransform.copyProperties(super.findOne(dto), ProblemHandlingResultBO.class);
        return problemHandlingResultBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void upload() throws SerException {
        //TODO: xiazhili 2017-03-25 未做上传
        return;

    }

}