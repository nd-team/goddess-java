package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.ExamWaySetBO;
import com.bjike.goddess.balancecard.to.ExamWaySetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.balancecard.entity.ExamWaySet;
import com.bjike.goddess.balancecard.dto.ExamWaySetDTO;

import java.util.List;

/**
 * 考核方式设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 考核方式设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ExamWaySetSer extends Ser<ExamWaySet, ExamWaySetDTO> {


    /**
     * 考核方式列表总条数
     */
    default Long countExamWaySet(ExamWaySetDTO examWaySetDTO) throws SerException {
        return null;
    }

    /**
     * 考核方式列表id
     * @return class ExamWaySetBO
     */
    default ExamWaySetBO getOneById (String id) throws SerException {return null;}


    /**
     * 考核方式列表
     *
     * @return class ExamWaySetBO
     */
    default List<ExamWaySetBO> listExamWaySet(ExamWaySetDTO examWaySetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param examWaySetTO 考核方式信息
     * @return class ExamWaySetBO
     */
    default ExamWaySetBO addExamWaySet(ExamWaySetTO examWaySetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param examWaySetTO 考核方式信息
     * @return class ExamWaySetBO
     */
    default ExamWaySetBO editExamWaySet(ExamWaySetTO examWaySetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteExamWaySet(String id) throws SerException {
        return;
    }

    ;


    /**
     * 获取所有维考核方式
     *
     */
    default List<String> listName(   ) throws SerException {
        return null;
    }


    ;
}