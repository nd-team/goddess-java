package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.ProjectInfoBO;
import com.bjike.goddess.progressmanage.bo.ProjectListForNodeBO;
import com.bjike.goddess.progressmanage.dto.ProjectInfoDTO;
import com.bjike.goddess.progressmanage.to.ProjectInfoTO;

import java.util.List;

/**
 * 项目信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:41 ]
 * @Description: [ 项目信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectInfoAPI {

    ProjectInfoBO add(ProjectInfoTO to) throws SerException;

    ProjectInfoBO edit(ProjectInfoTO to) throws SerException;

    void freeze(String id) throws SerException;

    void unfreeze(String id) throws SerException;

    void delete(String id) throws SerException;

    List<ProjectInfoBO> pageList(ProjectInfoDTO dto) throws SerException;

    List<ProjectListForNodeBO> projects() throws SerException;
}