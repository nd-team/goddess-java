package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectMultipleUI;
import com.bjike.goddess.projectmeasure.to.MultipleProjectMultipleUITO;

import java.util.List;

/**
 * 多项目多个界面业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MultipleProjectMultipleUISer extends Ser<MultipleProjectMultipleUI, MultipleProjectMultipleUIDTO> {

    /**
     * 分页查询多项目多个界面
     *
     * @return class MultipleProjectMultipleUIBO
     * @throws SerException
     */
    List<MultipleProjectMultipleUIBO> list(MultipleProjectMultipleUIDTO dto) throws SerException;

    /**
     * 保存多项目多个界面
     *
     * @param to 多项目多个界面to
     * @return class MultipleProjectMultipleUIBO
     * @throws SerException
     */
    MultipleProjectMultipleUIBO save(MultipleProjectMultipleUITO to) throws SerException;

    /**
     * 根据id删除多项目多个界面
     *
     * @param id 多项目多个界面唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新多项目多个界面
     *
     * @param to 多项目多个界面to
     * @throws SerException
     */
    void update(MultipleProjectMultipleUITO to) throws SerException;

}