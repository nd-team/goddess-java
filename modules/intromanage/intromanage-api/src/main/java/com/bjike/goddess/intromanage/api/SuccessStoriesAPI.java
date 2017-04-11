package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.SuccessStoriesBO;
import com.bjike.goddess.intromanage.dto.SuccessStoriesDTO;
import com.bjike.goddess.intromanage.to.SuccessStoriesTO;

import java.util.List;

/**
 * 成功案例业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:49 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SuccessStoriesAPI {

    /**
     * 分页查询成功案例
     *
     * @return class SuccessStoriesBO
     * @throws SerException
     */
    List<SuccessStoriesBO> list(SuccessStoriesDTO dto) throws SerException;

    /**
     * 保存成功案例
     *
     * @param to 成功案例to
     * @return class SuccessStoriesBO
     * @throws SerException
     */
    SuccessStoriesBO save(SuccessStoriesTO to) throws SerException;

    /**
     * 根据id删除成功案例
     *
     * @param id 成功案例唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新成功案例
     *
     * @param to 成功案例to
     * @throws SerException
     */
    void update(SuccessStoriesTO to) throws SerException;

}