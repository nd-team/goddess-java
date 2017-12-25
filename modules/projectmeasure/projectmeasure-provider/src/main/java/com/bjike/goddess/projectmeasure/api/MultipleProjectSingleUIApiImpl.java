package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBBO;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIBDTO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectSingleUIB;
import com.bjike.goddess.projectmeasure.service.MultipleProjectSingleUISer;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUIBTO;
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
     * 根据id查询多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public MultipleProjectSingleUIBO getOne(String id) throws SerException {
        return multipleProjectSingleUISer.getOne(id);
    }

    /**
     * 计算总条数
     *
     * @param dto 多项目单个界面dto
     * @throws SerException
     */
    @Override
    public Long count(MultipleProjectSingleUIDTO dto) throws SerException {
        return multipleProjectSingleUISer.count(dto);
    }

    /**
     * 分页查询多项目单个界面
     *
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public List<MultipleProjectSingleUIBBO> list(MultipleProjectSingleUIBDTO dto) throws SerException {
        return multipleProjectSingleUISer.list(dto);
    }

    /**
     * 保存多项目单个界面
     *
     * @param to 多项目单个界面to
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public void save(MultipleProjectSingleUIBTO to) throws SerException {
         multipleProjectSingleUISer.save(to);
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
    public void update(MultipleProjectSingleUIBTO to) throws SerException {
        multipleProjectSingleUISer.update(to);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return multipleProjectSingleUISer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return multipleProjectSingleUISer.guidePermission(guidePermissionTO);
    }
}