package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectMultipleUI;
import com.bjike.goddess.projectmeasure.service.MultipleProjectMultipleUISer;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
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
     * 根据id查询多项目多个界面
     *
     * @param id 多项目多个界面唯一标识
     * @return class MultipleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    public MultipleProjectMultipleUIBO getOne(String id) throws SerException {
        return multipleProjectMultipleUISer.getOne(id);
    }

    /**
     * 计算总条数
     *
     * @param dto 多项目多个界面dto
     * @throws SerException
     */
    @Override
    public Long count(MultipleProjectMultipleUIDTO dto) throws SerException {
        return multipleProjectMultipleUISer.count(dto);
    }

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
    public void save(MultipleProjectMultipleUITO to) throws SerException {
         multipleProjectMultipleUISer.save(to);
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

    @Override
    public Boolean sonPermission() throws SerException {
        return multipleProjectMultipleUISer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return multipleProjectMultipleUISer.guidePermission(guidePermissionTO);
    }

}