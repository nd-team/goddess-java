package com.bjike.goddess.projectissuehandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.projectissuehandle.bo.CollectBO;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemHandlingResultDTO;
import com.bjike.goddess.projectissuehandle.service.ProblemHandlingResultSer;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.ProblemHandlingResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.PaintEvent;
import java.time.LocalDateTime;
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
    public Boolean sonPermission() throws SerException {
        return problemHandlingResultSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return problemHandlingResultSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long countProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        return problemHandlingResultSer.countProblemHandlingResult(problemHandlingResultDTO);
    }

    @Override
    public ProblemHandlingResultBO getOne(String id) throws SerException {
        return problemHandlingResultSer.getOne(id);
    }

    @Override
    public List<ProblemHandlingResultBO> findListProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        return problemHandlingResultSer.findListProblemHandlingResult(problemHandlingResultDTO);
    }

    @Override
    public ProblemHandlingResultBO insertProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
//        problemHandlingResultTO.setProblemAcceptTime(DateUtil.dateToString(LocalDateTime.now()));
//        problemHandlingResultTO.setProblemOccurrenceTime(DateUtil.dateToString(LocalDateTime.now()));
//        problemHandlingResultTO.setProblemSolveTime(DateUtil.dateToString(LocalDateTime.now()));
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
    public List<ProblemHandlingResultBO> searchProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        return problemHandlingResultSer.searchProblemHandlingResult(problemHandlingResultDTO);
    }

    @Override
    public byte[] exportExcel(ProblemHandlingResultDTO dto ) throws SerException{
        return problemHandlingResultSer.exportExcel(dto);
    }
    @Override
    public List<String> getName() throws SerException {
        return problemHandlingResultSer.getName();
    }
    @Override
    public List<String> getType() throws SerException {
        return problemHandlingResultSer.getType();
    }
    @Override
    public List<String> getObject() throws SerException {
        return problemHandlingResultSer.getObject();
    }

    @Override
    public List<String> getProblemHandler() throws SerException {
        return problemHandlingResultSer.getProblemHandler();
    }


}