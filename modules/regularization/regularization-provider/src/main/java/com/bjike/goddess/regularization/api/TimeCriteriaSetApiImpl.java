package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.TimeCriteriaSetBO;
import com.bjike.goddess.regularization.dto.TimeCriteriaSetDTO;
import com.bjike.goddess.regularization.entity.TimeCriteriaSet;
import com.bjike.goddess.regularization.service.TimeCriteriaSetSer;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.TimeCriteriaSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 时间条件设置业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("timeCriteriaSetApiImpl")
public class TimeCriteriaSetApiImpl implements TimeCriteriaSetAPI {

    @Autowired
    private TimeCriteriaSetSer timeCriteriaSetSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return timeCriteriaSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return timeCriteriaSetSer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询时间条件设置
     *
     * @param id 时间条件设置唯一标识
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    @Override
    public TimeCriteriaSetBO findById(String id) throws SerException {
        TimeCriteriaSet model = timeCriteriaSetSer.findById(id);
        return BeanTransform.copyProperties(model, TimeCriteriaSetBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 时间条件设置dto
     * @throws SerException
     */
    @Override
    public Long count(TimeCriteriaSetDTO dto) throws SerException {
        return timeCriteriaSetSer.count(dto);
    }

    /**
     * 分页查询时间条件设置
     *
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    @Override
    public List<TimeCriteriaSetBO> list(TimeCriteriaSetDTO dto) throws SerException {
        return timeCriteriaSetSer.list(dto);
    }

    /**
     * 保存时间条件设置
     *
     * @param to 时间条件设置to
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    @Override
    public TimeCriteriaSetBO save(TimeCriteriaSetTO to) throws SerException {
        return timeCriteriaSetSer.save(to);
    }

    /**
     * 根据id删除时间条件设置
     *
     * @param id 时间条件设置唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        timeCriteriaSetSer.remove(id);
    }

    /**
     * 更新时间条件设置
     *
     * @param to 时间条件设置to
     * @throws SerException
     */
    @Override
    public void update(TimeCriteriaSetTO to) throws SerException {
        timeCriteriaSetSer.update(to);
    }
}