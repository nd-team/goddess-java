package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.entity.Project;
import com.bjike.goddess.taskallotment.to.GuidePermissionTO;
import com.bjike.goddess.taskallotment.to.ProjectTO;
import com.bjike.goddess.taskallotment.to.TableTO;
import com.bjike.goddess.taskallotment.vo.SonPermissionObject;

import java.util.List;
import java.util.Set;

/**
 * 项目列表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:55 ]
 * @Description: [ 项目列表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectSer extends Ser<Project, ProjectDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProjectBO> list(ProjectDTO dto) throws SerException;

    /**
     * 列表1
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProjectBO> list1(ProjectDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    void save(ProjectTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ProjectTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    ProjectBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ProjectDTO dto) throws SerException;

    /**
     * 编辑表
     *
     * @param tableTO
     * @throws SerException
     */
    void editTable(TableTO tableTO) throws SerException;

    /**
     * 获取所有地区
     *
     * @return
     * @throws SerException
     */
    Set<String> areas() throws SerException;

    /**
     * 根据地区获取部门
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Set<String> departs(ProjectDTO dto) throws SerException;

    /**
     * 根据部门获取项目名称
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProjectBO> projects(ProjectDTO dto) throws SerException;

    /**
     * 根据表id获取项目表
     * @param id
     * @return
     * @throws SerException
     */
    TableBO table(String id) throws SerException;

    /**
     * 所有项目名称
     * @return
     * @throws SerException
     */
    List<ProjectBO> projects() throws SerException;

    /**
     * 根据项目获取项目表
     * @param projectId
     * @return
     * @throws SerException
     */
    List<TableBO> tables(String projectId) throws SerException;

    /**
     * 根据项目表获取任务名
     * @param tableId
     * @return
     * @throws SerException
     */
    Set<String> taskNames(String tableId) throws SerException;
}