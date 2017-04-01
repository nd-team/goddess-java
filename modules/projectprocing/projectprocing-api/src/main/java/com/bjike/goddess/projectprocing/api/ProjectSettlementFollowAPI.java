package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.ProjectSettlementFollowBO;
import com.bjike.goddess.projectprocing.dto.ProjectSettlementFollowDTO;
import com.bjike.goddess.projectprocing.to.ProjectSettlementFollowTO;
import com.bjike.goddess.projectprocing.utils.CollectData;

import java.util.List;

/**
 * 项目结算跟进业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:49 ]
 * @Description: [ 项目结算跟进业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectSettlementFollowAPI {

    /**
     * 项目结算跟进列表总条数
     *
     */
    default Long countProjectSettlementFollow(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {
        return null;
    }
    /**
     * 项目结算跟进列表
     * @return class ProjectSettlementFollowBO
     */
    default List<ProjectSettlementFollowBO> listProjectSettlementFollow(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {return null;}
    /**
     *  添加
     * @param projectSettlementFollowTO 项目结算跟进信息
     * @return class ProjectSettlementFollowBO
     */
    default ProjectSettlementFollowBO addProjectSettlementFollow(ProjectSettlementFollowTO projectSettlementFollowTO) throws SerException { return null;}

    /**
     *  编辑
     * @param projectSettlementFollowTO 项目结算跟进信息
     * @return class ProjectSettlementFollowBO
     */
    default ProjectSettlementFollowBO editProjectSettlementFollow(ProjectSettlementFollowTO projectSettlementFollowTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteProjectSettlementFollow(String id ) throws SerException {return;};

    /**
     * 搜索查询
     * @return class ProjectSettlementFollowBO
     */
    default List<ProjectSettlementFollowBO> searchListProjectSettlementFollow(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {return null;}


    /**
     * 项目结算汇总表
     * @return class CollectData
     */
    default List<CollectData> collect(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {return null;}


}