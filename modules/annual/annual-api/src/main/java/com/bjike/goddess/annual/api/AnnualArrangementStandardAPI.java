package com.bjike.goddess.annual.api;

import com.bjike.goddess.annual.bo.AnnualArrangementStandardBO;
import com.bjike.goddess.annual.dto.AnnualArrangementStandardDTO;
import com.bjike.goddess.annual.to.AnnualArrangementStandardTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 年假层级标准业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:33 ]
 * @Description: [ 年假层级标准业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnnualArrangementStandardAPI {

    /**
     * 更新年假层级标准实体数据
     *
     * @param to 年假层级标准传输对象
     * @return
     * @throws SerException
     */
    default AnnualArrangementStandardBO update(AnnualArrangementStandardTO to) throws SerException {
        return null;
    }

    /**
     * 根据年假标准查询年假层级标准
     *
     * @param standardId 年假标准id
     * @return
     * @throws SerException
     */
    default List<AnnualArrangementStandardBO> findByStandard(String standardId) throws SerException {
        return null;
    }

    /**
     * 年假层级标准列表
     *
     * @param dto 年假层级标准数据传输对象
     * @return
     * @throws SerException
     */
    default List<AnnualArrangementStandardBO> maps(AnnualArrangementStandardDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据标准和岗位层级查询年假层级标准
     *
     * @param standardId    年假标准ID
     * @param arrangementId 岗位层级ID
     * @return
     * @throws SerException
     */
    default AnnualArrangementStandardBO findByArrangementStandard(String standardId, String arrangementId) throws SerException {
        return null;
    }

    /**
     * 根据id获取年假层级标准数据
     *
     * @param id 年假层级标准数据id
     * @return
     * @throws SerException
     */
    default AnnualArrangementStandardBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }
}