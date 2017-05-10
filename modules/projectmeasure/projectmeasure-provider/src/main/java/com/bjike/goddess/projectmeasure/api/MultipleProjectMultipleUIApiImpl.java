package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.service.MultipleProjectMultipleUISer;
import com.bjike.goddess.projectmeasure.to.MultipleProjectMultipleUITO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 多项目多个界面业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("multipleProjectMultipleUIApiImpl")
public class MultipleProjectMultipleUIApiImpl implements MultipleProjectMultipleUIAPI {

    @Autowired
    private MultipleProjectMultipleUISer multipleProjectMultipleUISer;

    /**
     * 分页查询多项目多个界面
     *
     * @return class MultipleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    public List<MultipleProjectMultipleUIBO> list(MultipleProjectMultipleUIDTO dto) throws SerException {
        return multipleProjectMultipleUISer.list(dto);
    }

    /**
     * 保存多项目多个界面
     *
     * @param to 多项目多个界面to
     * @return class MultipleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    public MultipleProjectMultipleUIBO save(MultipleProjectMultipleUITO to) throws SerException {
        return multipleProjectMultipleUISer.save(to);
    }

    /**
     * 根据id删除多项目多个界面
     *
     * @param id 多项目多个界面唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        multipleProjectMultipleUISer.remove(id);
    }

    /**
     * 更新多项目多个界面
     *
     * @param to 多项目多个界面to
     * @throws SerException
     */
    @Override
    public void update(MultipleProjectMultipleUITO to) throws SerException {
        multipleProjectMultipleUISer.update(to);
    }
}