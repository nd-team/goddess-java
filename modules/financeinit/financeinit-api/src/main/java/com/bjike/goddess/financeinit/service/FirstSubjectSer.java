package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.financeinit.bo.FirstSubjectBO;
import com.bjike.goddess.financeinit.entity.FirstSubject;
import com.bjike.goddess.financeinit.dto.FirstSubjectDTO;
import com.bjike.goddess.financeinit.to.FirstSubjectTO;

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
public interface FirstSubjectSer extends Ser<FirstSubject, FirstSubjectDTO> {


    /**
     * 一级科目列表
     * @return class FirstSubjectBO
     */
    default List<FirstSubjectBO> listFirstSubject(FirstSubjectDTO firstSubjectDTO) throws SerException {return null;}
    /**
     *  添加
     * @param firstSubjectTO 一级科目信息
     * @return class FirstSubjectBO
     */
    default FirstSubjectBO addFirstSubject(FirstSubjectTO firstSubjectTO) throws SerException { return null;}

    /**
     *  编辑
     * @param firstSubjectTO 一级科目信息
     * @return class FirstSubjectBO
     */
    default FirstSubjectBO editFirstSubject(FirstSubjectTO firstSubjectTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteFirstSubject(String id ) throws SerException {return;};

    /**
     *  根据一级科目名称查找一级科目
     * @param firstSubjectName 一级科目信息
     * @return class FirstSubjectBO
     */
    default FirstSubjectBO getFirstSubject(String firstSubjectName) throws SerException { return null;}


}