package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.SuccessStoriesBO;
import com.bjike.goddess.intromanage.dto.SuccessStoriesDTO;
import com.bjike.goddess.intromanage.service.SuccessStoriesSer;
import com.bjike.goddess.intromanage.to.SuccessStoriesTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 成功案例业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:49 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("successStoriesApiImpl")
public class SuccessStoriesApiImpl implements SuccessStoriesAPI {

    @Autowired
    private SuccessStoriesSer successStoriesSer;

    /**
     * 分页查询成功案例
     *
     * @return class SuccessStoriesBO
     * @throws SerException
     */
    @Override
    public List<SuccessStoriesBO> list(SuccessStoriesDTO dto) throws SerException {
        return successStoriesSer.list(dto);
    }

    /**
     * 保存成功案例
     *
     * @param to 成功案例to
     * @return class SuccessStoriesBO
     * @throws SerException
     */
    @Override
    public SuccessStoriesBO save(SuccessStoriesTO to) throws SerException {
        return successStoriesSer.save(to);
    }

    /**
     * 根据id删除成功案例
     *
     * @param id 成功案例唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        successStoriesSer.remove(id);
    }

    /**
     * 更新成功案例
     *
     * @param to 成功案例to
     * @throws SerException
     */
    @Override
    public void update(SuccessStoriesTO to) throws SerException {
        successStoriesSer.update(to);
    }
}