package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmeasure.bo.SingleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.service.SingleProjectMultipleUISer;
import com.bjike.goddess.projectmeasure.to.SingleProjectMultipleUITO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单个项目多个界面业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("singleProjectMultipleUIApiImpl")
public class SingleProjectMultipleUIApiImpl implements SingleProjectMultipleUIAPI {

    @Autowired
    private SingleProjectMultipleUISer singleProjectMultipleUISer;

    /**
     * 分页查询单个项目多个界面
     *
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    public List<SingleProjectMultipleUIBO> list(SingleProjectMultipleUIDTO dto) throws SerException {
        return singleProjectMultipleUISer.list(dto);
    }

    /**
     * 保存单个项目多个界面
     *
     * @param to 单个项目多个界面to
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    public SingleProjectMultipleUIBO save(SingleProjectMultipleUITO to) throws SerException {
        return singleProjectMultipleUISer.save(to);
    }

    /**
     * 根据id删除单个项目多个界面
     *
     * @param id 单个项目多个界面唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        singleProjectMultipleUISer.remove(id);
    }

    /**
     * 更新单个项目多个界面
     *
     * @param to 单个项目多个界面to
     * @throws SerException
     */
    @Override
    public void update(SingleProjectMultipleUITO to) throws SerException {
        singleProjectMultipleUISer.update(to);
    }
}