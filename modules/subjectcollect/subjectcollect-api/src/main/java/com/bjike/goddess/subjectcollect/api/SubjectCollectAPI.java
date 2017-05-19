package com.bjike.goddess.subjectcollect.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.subjectcollect.bo.CompareCollectBO;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
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
public interface SubjectCollectAPI {
    /**
     * 科目汇总表列表总条数
     */
    default Long countSubjectCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return null;
    }

    /**
     * 一个科目汇总表
     *
     * @return class SubjectCollectBO
     */
    default SubjectCollectBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 科目汇总表
     *
     * @param subjectCollectDTO 科目汇总表dto
     * @return class SubjectCollectBO
     * @throws SerException
     */
    default List<SubjectCollectBO> findListSubjectCollect(SubjectCollectDTO subjectCollectDTO) throws SerException {
        return null;
    }

    /**
     * 添加科目汇总表
     *
     * @param subjectCollectTO 科目汇总表数据to
     * @return class SubjectCollectBO
     * @throws SerException
     */
    default SubjectCollectBO insertSubjectCollect(SubjectCollectTO subjectCollectTO) throws SerException {
        return null;
    }

    /**
     * 编辑科目汇总表
     *
     * @param subjectCollectTO 科目汇总表数据to
     * @return class SubjectCollectBO
     * @throws SerException
     */
    default SubjectCollectBO editSubjectCollect(SubjectCollectTO subjectCollectTO) throws SerException {
        return null;
    }

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
     * 汇总对比
     *
     * @return class CompareCollectBO
     * @throws SerException
     */
    default List<CompareCollectBO> collectCompare(Integer [] months) throws SerException {
        return null;
    }
}