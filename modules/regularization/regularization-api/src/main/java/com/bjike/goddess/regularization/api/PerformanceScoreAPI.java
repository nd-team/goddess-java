package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regularization.bo.PerformanceScoreBO;
import com.bjike.goddess.regularization.dto.PerformanceScoreDTO;
import com.bjike.goddess.regularization.to.PerformanceScoreTO;

import java.util.List;

/**
 * 工作表现评分业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PerformanceScoreAPI {

    /**
     * 根据id查询工作表现评分
     *
     * @param id 工作表现评分唯一标识
     * @return class PerformanceScoreBO
     * @throws SerException
     */
    PerformanceScoreBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 工作表现评分dto
     * @throws SerException
     */
    Long count(PerformanceScoreDTO dto) throws SerException;

    /**
     * 分页查询工作表现评分
     *
     * @return class PerformanceScoreBO
     * @throws SerException
     */
    List<PerformanceScoreBO> list(PerformanceScoreDTO dto) throws SerException;

    /**
     * 保存工作表现评分
     *
     * @param to 工作表现评分to
     * @return class PerformanceScoreBO
     * @throws SerException
     */
    PerformanceScoreBO save(PerformanceScoreTO to) throws SerException;

    /**
     * 根据id删除工作表现评分
     *
     * @param id 工作表现评分唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新工作表现评分
     *
     * @param to 工作表现评分to
     * @throws SerException
     */
    void update(PerformanceScoreTO to) throws SerException;

}