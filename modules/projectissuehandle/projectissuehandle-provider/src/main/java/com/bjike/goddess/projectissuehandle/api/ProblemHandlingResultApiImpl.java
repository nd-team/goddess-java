package com.bjike.goddess.projectissuehandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemHandlingResultDTO;
import com.bjike.goddess.projectissuehandle.service.ProblemHandlingResultSer;
import com.bjike.goddess.projectissuehandle.to.ProblemHandlingResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 确认问题处理结果业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:30 ]
 * @Description: [ 确认问题处理结果业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("problemHandlingResultApiImpl")
public class ProblemHandlingResultApiImpl implements ProblemHandlingResultAPI {
    @Autowired
    private ProblemHandlingResultSer problemHandlingResultSer;

    @Override
    public List<ProblemHandlingResultBO> findListProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        return problemHandlingResultSer.findListProblemHandlingResult(problemHandlingResultDTO);
    }

    @Override
    public ProblemHandlingResultBO insertProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
        problemHandlingResultTO.setProblemAcceptTime(DateUtil.dateToString(LocalDate.now()));
        problemHandlingResultTO.setProblemOccurrenceTime(DateUtil.dateToString(LocalDate.now()));
        problemHandlingResultTO.setProblemSolveTime(DateUtil.dateToString(LocalDate.now()));
        return problemHandlingResultSer.insertProblemHandlingResult(problemHandlingResultTO);
    }

    @Override
    public ProblemHandlingResultBO editProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
        return problemHandlingResultSer.editProblemHandlingResult(problemHandlingResultTO);
    }

    @Override
    public void removeProblemHandlingResult(String id) throws SerException {
        problemHandlingResultSer.removeProblemHandlingResult(id);
    }

    @Override
    public String exportExcel(String internalProjectName, String projectType) throws SerException {
        return problemHandlingResultSer.exportExcel(internalProjectName, projectType);
    }

    @Override
    public ProblemHandlingResultBO searchProblemHandlingResult(String internalProjectName, String projectType, String problemObject) throws SerException {
        return problemHandlingResultSer.searchProblemHandlingResult(internalProjectName, projectType, problemObject);
    }

    public void upload() throws SerException {
        problemHandlingResultSer.upload();

    }

}