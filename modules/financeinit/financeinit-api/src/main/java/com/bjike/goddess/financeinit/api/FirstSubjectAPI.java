package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.FirstSubjectBO;
import com.bjike.goddess.financeinit.dto.FirstSubjectDTO;
import com.bjike.goddess.financeinit.excel.SonPermissionObject;
import com.bjike.goddess.financeinit.to.FirstSubjectTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;

import java.util.List;

/**
 * 一级科目业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 一级科目业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FirstSubjectAPI {


    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }


    /**
     * 一级科目列表总条数
     */
    default Long countFirstSubject(FirstSubjectDTO firstSubjectDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取一级科目列表
     *
     * @return class FirstSubjectBO
     */
    default FirstSubjectBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 一级科目列表
     *
     * @return class FirstSubjectBO
     */
    default List<FirstSubjectBO> listFirstSubject(FirstSubjectDTO firstSubjectDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param firstSubjectTO 一级科目信息
     * @return class FirstSubjectBO
     */
    default FirstSubjectBO addFirstSubject(FirstSubjectTO firstSubjectTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param firstSubjectTO 一级科目信息
     * @return class FirstSubjectBO
     */
    default FirstSubjectBO editFirstSubject(FirstSubjectTO firstSubjectTO) throws SerException {
        return null;
    }

    /**
     * 删除级别
     *
     * @param id id
     */
    default void deleteFirstSubject(String id) throws SerException {
        return;
    }

    ;

    /**
     * 根据一级科目名称查找一级科目
     *
     * @param firstSubjectName 一级科目信息
     * @return class FirstSubjectBO
     */
    default FirstSubjectBO getFirstSubject(String firstSubjectName) throws SerException {
        return null;
    }

    /**
     * 获取所有一级科目列表
     */
    default List<String> listAllFirst() throws SerException {
        return null;
    }


    /**
     * 导入
     *
     * @param firstSubjectTO 一级科目信息
     * @return class FirstSubjectBO
     */
    default FirstSubjectBO importExcel(List<FirstSubjectTO> firstSubjectTO) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(FirstSubjectDTO dto) throws SerException;

    /**
     * 导出Excel导入模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;


    /**
     * 获取带有科目编号的一级科目
     *
     * @return
     * @throws SerException
     */
    default List<String> listAllFirstAndCode() throws SerException {
        return null;
    }
}