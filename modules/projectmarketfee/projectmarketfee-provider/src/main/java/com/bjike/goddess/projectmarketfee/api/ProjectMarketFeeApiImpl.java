package com.bjike.goddess.projectmarketfee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeBO;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeCountBO;
import com.bjike.goddess.projectmarketfee.dto.ProjectMarketFeeDTO;
import com.bjike.goddess.projectmarketfee.service.ProjectMarketFeeSer;
import com.bjike.goddess.projectmarketfee.to.ProjectMarketFeeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目前期的市场活动费业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-10 10:34 ]
 * @Description: [ 项目前期的市场活动费业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectMarketFeeApiImpl")
public class ProjectMarketFeeApiImpl implements ProjectMarketFeeAPI {
    @Autowired
    private ProjectMarketFeeSer projectMarketFeeSer;

    @Override
    public List<ProjectMarketFeeBO> list(ProjectMarketFeeDTO dto) throws SerException {
        return projectMarketFeeSer.list(dto);
    }

    @Override
    public List<ProjectMarketFeeCountBO> firstSubjectCount(String startTime, String endTime) throws SerException {
        return projectMarketFeeSer.firstSubjectCount(startTime, endTime);
    }

    @Override
    public List<ProjectMarketFeeCountBO> secondSubjectCount(String startTime, String endTime) throws SerException {
        return projectMarketFeeSer.secondSubjectCount(startTime, endTime);
    }

    @Override
    public List<ProjectMarketFeeCountBO> thirdSubjectCount(String startTime, String endTime) throws SerException {
        return projectMarketFeeSer.thirdSubjectCount(startTime, endTime);
    }

    @Override
    public List<ProjectMarketFeeCountBO> areaCount(String startTime, String endTime) throws SerException {
        return projectMarketFeeSer.areaCount(startTime, endTime);
    }

    @Override
    public List<ProjectMarketFeeCountBO> projectGroupCount(String startTime, String endTime) throws SerException {
        return projectMarketFeeSer.projectGroupCount(startTime, endTime);
    }

    @Override
    public List<ProjectMarketFeeCountBO> projectNameCount(String startTime, String endTime) throws SerException {
        return projectMarketFeeSer.projectNameCount(startTime, endTime);
    }

    @Override
    public List<ProjectMarketFeeBO> findDetail(ProjectMarketFeeTO to) throws SerException {
        return projectMarketFeeSer.findDetail(to);
    }

    @Override
    public ProjectMarketFeeCountBO count(String projectGroup, String area, Integer year, Integer month, String projectName) throws SerException {
        return projectMarketFeeSer.count(projectGroup, area, year, month, projectName);
    }
}