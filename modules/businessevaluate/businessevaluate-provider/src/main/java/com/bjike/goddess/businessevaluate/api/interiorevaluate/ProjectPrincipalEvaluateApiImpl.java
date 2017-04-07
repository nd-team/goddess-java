package com.bjike.goddess.businessevaluate.api.interiorevaluate;

import com.bjike.goddess.businessevaluate.bo.interiorevaluate.ProjectPrincipalEvaluateBO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.ProjectPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.service.interiorevaluate.ProjectPrincipalEvaluateSer;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.ProjectPrincipalEvaluateTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务负责人评价业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 01:55 ]
 * @Description: [ 商务负责人评价业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectPrincipalEvaluateApiImpl")
public class ProjectPrincipalEvaluateApiImpl implements ProjectPrincipalEvaluateAPI {

    @Autowired
    private ProjectPrincipalEvaluateSer projectPrincipalEvaluateSer;

    @Override
    public ProjectPrincipalEvaluateBO addModel(ProjectPrincipalEvaluateTO to) throws SerException {
        return projectPrincipalEvaluateSer.insertModel(to);
    }

    @Override
    public ProjectPrincipalEvaluateBO editModel(ProjectPrincipalEvaluateTO to) throws SerException {
        return projectPrincipalEvaluateSer.editModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        projectPrincipalEvaluateSer.remove(id);
    }

    @Override
    public List<ProjectPrincipalEvaluateBO> pageList(ProjectPrincipalEvaluateDTO dto) throws SerException {
        return projectPrincipalEvaluateSer.pageList(dto);
    }
}