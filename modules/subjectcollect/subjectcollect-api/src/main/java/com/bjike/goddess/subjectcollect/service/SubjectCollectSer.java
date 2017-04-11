package com.bjike.goddess.subjectcollect.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.entity.SubjectCollect;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectDTO;
import com.bjike.goddess.subjectcollect.to.SubjectCollectTO;

import java.util.List;

/**
 * 科目汇总表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SubjectCollectSer extends Ser<SubjectCollect, SubjectCollectDTO> {

    /**
     * 导出
     *
     * @throws SerException
     */
    default String exportExcel() throws SerException {
        return null;
    }
    /**
     * 根据id删除科目汇总表
     *
     * @param id
     * @throws SerException
     */
    default void removeSubjectCollect(String id) throws SerException {

    }

    /**
     * 汇总
     *
     * @param firstSubject firstSubject
     * @return class marketInfoBO
     * @throws SerException
     */
    default SubjectCollectBO collectSubjectCollect(String[] firstSubject) throws SerException {
        return null;
    }

    /**
     * 获取一级科目
     *
     * @return class String
     */
    default List<String> getSubjectCollectFirstSubject() throws SerException {
        return null;
    }


    /**
     * 地区汇总
     *
     * @param area area
     * @return class marketInfoBO
     * @throws SerException
     */
    default SubjectCollectBO collectArea(String[] area) throws SerException {
        return null;
    }

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getSubjectCollectArea() throws SerException {
        return null;
    }
    /**
     * 项目名称汇总
     *
     * @param projectName projectName
     * @return class marketInfoBO
     * @throws SerException
     */
    default SubjectCollectBO collectProjectName(String[] projectName) throws SerException {
        return null;
    }

    /**
     * 获取项目名称
     *
     * @return class String
     */
    default List<String> getSubjectCollectProjectName() throws SerException {
        return null;
    }
    /**
     * 项目组汇总
     *
     * @param projectGroup projectGroup
     * @return class marketInfoBO
     * @throws SerException
     */
    default SubjectCollectBO collectProjectGroup(String[] projectGroup) throws SerException {
        return null;
    }

    /**
     * 获取项目组
     *
     * @return class String
     */
    default List<String> getSubjectCollectProjectGroup() throws SerException {
        return null;
    }
    /**
     * 对比汇总
     *
     * @param subjectCollectTO 对比汇总数据to
     * @return class subjectCollectBO
     * @throws SerException
     */
    default List<SubjectCollectBO> collectCompare(SubjectCollectTO subjectCollectTO) throws SerException {
        return null;
    }
    default List<String> getAreas() throws SerException{
        return null;
    }
    default List<String> getProjectName() throws SerException{
        return null;
    }
    default List<String> getProjectGroup() throws SerException{
        return null;
    }


}