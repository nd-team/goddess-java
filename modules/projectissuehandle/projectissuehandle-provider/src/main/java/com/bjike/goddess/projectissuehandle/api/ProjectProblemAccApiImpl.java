package com.bjike.goddess.projectissuehandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectissuehandle.bo.OptionBO;
import com.bjike.goddess.projectissuehandle.bo.ProjectProblemAccBO;
import com.bjike.goddess.projectissuehandle.bo.ProjectSummaryBO;
import com.bjike.goddess.projectissuehandle.dto.ProjectProblemAccDTO;
import com.bjike.goddess.projectissuehandle.excel.SonPermissionObject;
import com.bjike.goddess.projectissuehandle.service.ProjectProblemAccSer;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.ProjectProblemAccTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目中问题受理和处理业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-08 03:43 ]
 * @Description: [ 项目中问题受理和处理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectProblemAccApiImpl")
public class ProjectProblemAccApiImpl implements ProjectProblemAccAPI {
    @Autowired
    private ProjectProblemAccSer projectProblemAccSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return projectProblemAccSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return projectProblemAccSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countProjectProblem(ProjectProblemAccDTO projectProblemAccDTO) throws SerException {
        return projectProblemAccSer.countProjectProblem(projectProblemAccDTO);
    }

    @Override
    public ProjectProblemAccBO getOne(String id) throws SerException {
        return projectProblemAccSer.getOne(id);
    }

    @Override
    public List<ProjectProblemAccBO> findListProjectProblem(ProjectProblemAccDTO projectProblemAccDTO) throws SerException {
        return projectProblemAccSer.findListProjectProblem(projectProblemAccDTO);
    }

    @Override
    public ProjectProblemAccBO insertProjectProblem(ProjectProblemAccTO projectProblemAccTO) throws SerException {
        return projectProblemAccSer.insertProjectProblem(projectProblemAccTO);
    }

    @Override
    public ProjectProblemAccBO editProjectProblem(ProjectProblemAccTO projectProblemAccTO) throws SerException {
        return projectProblemAccSer.editProjectProblem(projectProblemAccTO);
    }

    @Override
    public void removeProjectProblem(String id) throws SerException {
        projectProblemAccSer.removeProjectProblem(id);
    }

    @Override
    public void congeal(String id) throws SerException {
        projectProblemAccSer.congeal(id);
    }

    @Override
    public void thaw(String id) throws SerException {
        projectProblemAccSer.thaw(id);
    }

    @Override
    public void importExcel(List<ProjectProblemAccTO> projectProblemAccTOS) throws SerException {
        projectProblemAccSer.importExcel(projectProblemAccTOS);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return projectProblemAccSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return projectProblemAccSer.templateExport();
    }

    @Override
    public List<ProjectSummaryBO> summaDay(String summationDate) throws SerException {
        return projectProblemAccSer.summaDay(summationDate);
    }

    @Override
    public List<ProjectSummaryBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return projectProblemAccSer.summaWeek(year,month,week);
    }

    @Override
    public List<ProjectSummaryBO> summaMonth(Integer year, Integer month) throws SerException {
        return projectProblemAccSer.summaMonth(year,month);
    }

    @Override
    public List<ProjectSummaryBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        return projectProblemAccSer.summaQuarter(year,quarter);
    }

    @Override
    public List<ProjectSummaryBO> summaTotal(String endDate) throws SerException {
        return projectProblemAccSer.summaTotal(endDate);
    }

    @Override
    public OptionBO figureShowDay(String summDate) throws SerException {
        return projectProblemAccSer.figureShowDay(summDate);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return projectProblemAccSer.figureShowWeek(year,month,week);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return projectProblemAccSer.figureShowMonth(year,month);
    }

    @Override
    public OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
        return projectProblemAccSer.figureShowQuarter(year,quarter);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        return projectProblemAccSer.figureShowTotal(endDate);
    }
}