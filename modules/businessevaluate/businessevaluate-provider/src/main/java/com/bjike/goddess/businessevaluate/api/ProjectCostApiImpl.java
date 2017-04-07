package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.ProjectCostBO;
import com.bjike.goddess.businessevaluate.dto.ProjectCostDTO;
import com.bjike.goddess.businessevaluate.service.ProjectCostSer;
import com.bjike.goddess.businessevaluate.to.ProjectCostTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目费用业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 02:17 ]
 * @Description: [ 项目费用业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectCostApiImpl")
public class ProjectCostApiImpl implements ProjectCostAPI {

    @Autowired
    private ProjectCostSer projectCostSer;

    @Override
    public ProjectCostBO addModel(ProjectCostTO to) throws SerException {
        return projectCostSer.insertModel(to);
    }

    @Override
    public ProjectCostBO editModel(ProjectCostTO to) throws SerException {
        return projectCostSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        projectCostSer.remove(id);
    }

    @Override
    public List<ProjectCostBO> pageList(ProjectCostDTO dto) throws SerException {
        return projectCostSer.pageList(dto);
    }
}