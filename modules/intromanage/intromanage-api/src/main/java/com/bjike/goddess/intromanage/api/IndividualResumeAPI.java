package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.IndividualResumeBO;
import com.bjike.goddess.intromanage.dto.IndividualResumeDTO;
import com.bjike.goddess.intromanage.to.IndividualResumeTO;

import java.util.List;

/**
 * 个人简介业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:19 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IndividualResumeAPI {

    /**
     * 分页查询个人简介
     *
     * @return class IndividualResumeBO
     * @throws SerException
     */
    List<IndividualResumeBO> list(IndividualResumeDTO dto) throws SerException;

    /**
     * 保存个人简介
     *
     * @param to 个人简介to
     * @return class IndividualResumeBO
     * @throws SerException
     */
    IndividualResumeBO save(IndividualResumeTO to) throws SerException;

    /**
     * 根据id删除个人简介
     *
     * @param id 个人简介唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新个人简介
     *
     * @param to 个人简介to
     * @throws SerException
     */
    void update(IndividualResumeTO to) throws SerException;

}