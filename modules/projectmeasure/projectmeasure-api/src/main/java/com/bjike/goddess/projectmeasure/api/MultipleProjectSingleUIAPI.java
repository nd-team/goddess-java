package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUITO;

import java.util.List;

/**
 * 多项目单个界面业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MultipleProjectSingleUIAPI {

    /**
     * 根据id查询多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    MultipleProjectSingleUIBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 多项目当个界面dto
     * @throws SerException
     */
    Long count(MultipleProjectSingleUIDTO dto) throws SerException;


    /**
     * 分页查询多项目单个界面
     *
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    List<MultipleProjectSingleUIBO> list(MultipleProjectSingleUIDTO dto) throws SerException;

    /**
     * 保存多项目单个界面
     *
     * @param to 多项目多个界面to
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    MultipleProjectSingleUIBO save(MultipleProjectSingleUITO to) throws SerException;

    /**
     * 根据id删除多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新多项目单个界面
     *
     * @param to 多项目单个界面to
     * @throws SerException
     */
    void update(MultipleProjectSingleUITO to) throws SerException;

}