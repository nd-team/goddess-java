package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.service.MultipleProjectSingleUISer;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUITO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 多项目单个界面业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("multipleProjectSingleUIApiImpl")
public class MultipleProjectSingleUIApiImpl implements MultipleProjectSingleUIAPI {

    @Autowired
    private MultipleProjectSingleUISer multipleProjectSingleUISer;

    /**
     * 分页查询多项目单个界面
     *
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public List<MultipleProjectSingleUIBO> list(MultipleProjectSingleUIDTO dto) throws SerException {
        return multipleProjectSingleUISer.list(dto);
    }

    /**
     * 保存多项目单个界面
     *
     * @param to 多项目多个界面to
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public MultipleProjectSingleUIBO save(MultipleProjectSingleUITO to) throws SerException {
        return multipleProjectSingleUISer.save(to);
    }

    /**
     * 根据id删除多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        multipleProjectSingleUISer.remove(id);
    }

    /**
     * 更新多项目单个界面
     *
     * @param to 多项目单个界面to
     * @throws SerException
     */
    @Override
    public void update(MultipleProjectSingleUITO to) throws SerException {
        multipleProjectSingleUISer.update(to);
    }
}