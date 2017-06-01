package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmeasure.bo.SingleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.to.SingleProjectSingleUITO;

import java.util.List;

/**
 * 单个项目单个界面业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:48 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SingleProjectSingleUIAPI {

    /**
     * 根据id查询单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    SingleProjectSingleUIBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 单个项目单个界面dto
     * @throws SerException
     */
    Long count(SingleProjectSingleUIDTO dto) throws SerException;

    /**
     * 分页查询单个项目单个界面
     *
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    List<SingleProjectSingleUIBO> list(SingleProjectSingleUIDTO dto) throws SerException;

    /**
     * 保存单个项目单个界面
     *
     * @param to 单个项目单个界面to
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    SingleProjectSingleUIBO save(SingleProjectSingleUITO to) throws SerException;

    /**
     * 根据id删除单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新单个项目单个界面
     *
     * @param to 单个项目单个界面to
     * @throws SerException
     */
    void update(SingleProjectSingleUITO to) throws SerException;

}