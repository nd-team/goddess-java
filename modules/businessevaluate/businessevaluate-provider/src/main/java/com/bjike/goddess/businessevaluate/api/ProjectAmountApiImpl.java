package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.ProjectAmountBO;
import com.bjike.goddess.businessevaluate.bo.ProjectAmountInfoBO;
import com.bjike.goddess.businessevaluate.dto.ProjectAmountDTO;
import com.bjike.goddess.businessevaluate.service.ProjectAmountSer;
import com.bjike.goddess.businessevaluate.to.ProjectAmountTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目金额业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 项目金额业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectAmountApiImpl")
public class ProjectAmountApiImpl implements ProjectAmountAPI {

    @Autowired
    private ProjectAmountSer projectAmountSer;


    @Override
    public ProjectAmountInfoBO findInfoById(String id) throws SerException {
        return projectAmountSer.findInfoById(id);
    }

    @Override
    public ProjectAmountBO addModel(ProjectAmountTO to) throws SerException {
        return projectAmountSer.insertModel(to);
    }

    @Override
    public ProjectAmountBO editModel(ProjectAmountTO to) throws SerException {
        return projectAmountSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        projectAmountSer.remove(id);
    }

    @Override
    public List<ProjectAmountBO> pageList(ProjectAmountDTO dto) throws SerException {
        return projectAmountSer.pageList(dto);
    }
}