package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.dto.ManagementScoreDTO;
import com.bjike.goddess.regularization.to.ManagementScoreTO;

import java.util.List;

/**
 * 管理层评分业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-17 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ManagementScoreAPI {

    /**
     * 根据id查询管理层评分
     *
     * @param id 管理层评分唯一标识
     * @return class ManagementScoreBO
     * @throws SerException
     */
    ManagementScoreBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 管理层评分dto
     * @throws SerException
     */
    Long count(ManagementScoreDTO dto) throws SerException;

    /**
     * 分页查询管理层评分
     *
     * @return class ManagementScoreBO
     * @throws SerException
     */
    List<ManagementScoreBO> list(ManagementScoreDTO dto) throws SerException;

    /**
     * 保存管理层评分
     *
     * @param to 管理层评分to
     * @return class ManagementScoreBO
     * @throws SerException
     */
    ManagementScoreBO save(ManagementScoreTO to) throws SerException;

    /**
     * 根据id删除管理层评分
     *
     * @param id 管理层评分唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新管理层评分
     *
     * @param to 管理层评分to
     * @throws SerException
     */
    void update(ManagementScoreTO to) throws SerException;

}