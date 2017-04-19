package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.regularization.bo.TimeCriteriaSetBO;
import com.bjike.goddess.regularization.dto.TimeCriteriaSetDTO;
import com.bjike.goddess.regularization.entity.TimeCriteriaSet;
import com.bjike.goddess.regularization.to.TimeCriteriaSetTO;

import java.util.List;

/**
 * 时间条件设置业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TimeCriteriaSetSer extends Ser<TimeCriteriaSet, TimeCriteriaSetDTO> {

    /**
     * 分页查询时间条件设置
     *
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    List<TimeCriteriaSetBO> list(TimeCriteriaSetDTO dto) throws SerException;

    /**
     * 保存时间条件设置
     *
     * @param to 时间条件设置to
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    TimeCriteriaSetBO save(TimeCriteriaSetTO to) throws SerException;

    /**
     * 根据id删除时间条件设置
     *
     * @param id 时间条件设置唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新时间条件设置
     *
     * @param to 时间条件设置to
     * @throws SerException
     */
    void update(TimeCriteriaSetTO to) throws SerException;

}