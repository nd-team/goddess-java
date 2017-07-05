package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.SingleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.SingleProjectSingleUI;
import com.bjike.goddess.projectmeasure.service.SingleProjectSingleUISer;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.SingleProjectSingleUITO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单个项目单个界面业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:48 ]
 * @Description: [ 单个项目单个界面业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("singleProjectSingleUIApiImpl")
public class SingleProjectSingleUIApiImpl implements SingleProjectSingleUIAPI {

    @Autowired
    private SingleProjectSingleUISer singleProjectSingleUISer;

    /**
     * 根据id查询单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public SingleProjectSingleUIBO getOne(String id) throws SerException {
        return singleProjectSingleUISer.getOne(id);
    }

    /**
     * 计算总条数
     *
     * @param dto 单个项目单个界面dto
     * @throws SerException
     */
    @Override
    public Long count(SingleProjectSingleUIDTO dto) throws SerException {
        return singleProjectSingleUISer.count(dto);
    }

    /**
     * 分页查询单个项目单个界面
     *
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public List<SingleProjectSingleUIBO> list(SingleProjectSingleUIDTO dto) throws SerException {
        return singleProjectSingleUISer.list(dto);
    }

    /**
     * 保存单个项目单个界面
     *
     * @param to 单个项目单个界面to
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public SingleProjectSingleUIBO save(SingleProjectSingleUITO to) throws SerException {
        return singleProjectSingleUISer.save(to);
    }

    /**
     * 根据id删除单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        singleProjectSingleUISer.remove(id);
    }

    /**
     * 更新单个项目单个界面
     *
     * @param to 单个项目单个界面to
     * @throws SerException
     */
    @Override
    public void update(SingleProjectSingleUITO to) throws SerException {

    }

    @Override
    public Boolean sonPermission() throws SerException {
        return singleProjectSingleUISer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return singleProjectSingleUISer.guidePermission(guidePermissionTO);
    }
}