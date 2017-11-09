package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.entity.Project;
import com.bjike.goddess.taskallotment.excel.ProjectExcel;
import com.bjike.goddess.taskallotment.excel.TableExcel;
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
public interface ProjectAPI {
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

    /**
     * 添加表
     * @param to
     * @throws SerException
     */
    void addTable(TableTO to) throws SerException;

    /**
     * 导出项目excel
     * @param dto
     * @return
     * @throws SerException
     */
    byte[] exportProjectExcel(ProjectDTO dto) throws SerException;

    /**
     * 导入项目excel
     * @param toList
     * @throws SerException
     */
    void leadProjectExcel(List<ProjectExcel> toList) throws SerException;

    /**
     * 导出项目表excel
     * @param dto
     * @return
     * @throws SerException
     */
    byte[] exportTableExcel(TableDTO dto) throws SerException;

    /**
     * 导入项目表excel
     * @param toList
     * @param projectId
     * @throws SerException
     */
    void leadTableExcel(List<TableExcel> toList, String projectId) throws SerException;

    /**
     * 获取所有地区
     * @return
     * @throws SerException
     */
    List<String> areass() throws SerException;

    /**
     * 根据地区获取部门
     * @param area
     * @return
     * @throws SerException
     */
    List<String> departs(String area) throws SerException;

    /**
     * 根据地区和部门获取立项情况
     * @param area
     * @param depart
     * @return
     * @throws SerException
     */
    List<String> makeProjects(String area,String depart) throws SerException;

    /**
     * 根据地区,部门和立项情况获取项目
     * @param area
     * @param depart
     * @param makeProject
     * @return
     * @throws SerException
     */
    List<ProjectBO> projects(String area,String depart,String makeProject) throws SerException;
}