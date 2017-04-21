package com.bjike.goddess.projectissuehandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemAcceptDTO;
import com.bjike.goddess.projectissuehandle.service.ProblemAcceptSer;
import com.bjike.goddess.projectissuehandle.to.ProblemAcceptTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目执行中的问题受理业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:16 ]
 * @Description: [ 项目执行中的问题受理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("problemAcceptApiImpl")
public class ProblemAcceptApiImpl implements ProblemAcceptAPI {
    @Autowired
    private ProblemAcceptSer problemAcceptSer;
    @Override
    public Long countProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        return problemAcceptSer.countProblemAccept(problemAcceptDTO);
    }

    @Override
    public List<ProblemAcceptBO> findListProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        return problemAcceptSer.findListProblemAccept(problemAcceptDTO);
    }

    @Override
    public ProblemAcceptBO insertProblemAccept(ProblemAcceptTO problemAcceptTO) throws SerException {
        return problemAcceptSer.insertProblemAccept(problemAcceptTO);
    }

    @Override
    public ProblemAcceptBO editProblemAccept(ProblemAcceptTO problemAcceptTO) throws SerException {
        return problemAcceptSer.editProblemAccept(problemAcceptTO);
    }

    @Override
    public void removeProblemAccept(String id) throws SerException {
        problemAcceptSer.removeProblemAccept(id);
    }

    @Override
    public String exportExcel(String internalProjectName, String projectType) throws SerException {
        return problemAcceptSer.exportExcel(internalProjectName, projectType);
    }

    @Override
    public ProblemAcceptBO searchProblemAccept(String internalProjectName, String projectType) throws SerException {
        return problemAcceptSer.searchProblemAccept(internalProjectName, projectType);
    }

    @Override
    public void upload() throws SerException {
        problemAcceptSer.upload();

    }

}