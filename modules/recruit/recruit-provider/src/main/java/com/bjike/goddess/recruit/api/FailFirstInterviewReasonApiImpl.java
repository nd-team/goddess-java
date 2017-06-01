package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FailFirstInterviewReasonBO;
import com.bjike.goddess.recruit.dto.FailFirstInterviewReasonDTO;
import com.bjike.goddess.recruit.entity.FailFirstInterviewReason;
import com.bjike.goddess.recruit.service.FailFirstInterviewReasonSer;
import com.bjike.goddess.recruit.to.FailFirstInterviewReasonTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未应约初试原因
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("failFirstInterviewReasonApiImpl")
public class FailFirstInterviewReasonApiImpl implements FailFirstInterviewReasonAPI {

    @Autowired
    private FailFirstInterviewReasonSer failFirstInterviewReasonSer;

    /**
     * 根据id查询未应约初试原因
     *
     * @param id 未应约初试原因唯一标识
     * @return class FailFirstInterviewReasonBO
     * @throws SerException
     */
    @Override
    public FailFirstInterviewReasonBO findById(String id) throws SerException {
        FailFirstInterviewReason model = failFirstInterviewReasonSer.findById(id);
        return BeanTransform.copyProperties(model, FailFirstInterviewReasonBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 未应约初试原因dto
     * @throws SerException
     */
    @Override
    public Long count(FailFirstInterviewReasonDTO dto) throws SerException {
        return failFirstInterviewReasonSer.count(dto);
    }

    /**
     * 分页查询未应约初试原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<FailFirstInterviewReasonBO> list(FailFirstInterviewReasonDTO dto) throws SerException {
        return failFirstInterviewReasonSer.list(dto);
    }

    /**
     * 保存未应约初试原因
     *
     * @param failFirstInterviewReasonTO
     * @return
     * @throws SerException
     */
    @Override
    public FailFirstInterviewReasonBO save(FailFirstInterviewReasonTO failFirstInterviewReasonTO) throws SerException {
        return failFirstInterviewReasonSer.save(failFirstInterviewReasonTO);
    }

    /**
     * 根据id删除未应约初试原因
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        failFirstInterviewReasonSer.remove(id);
    }

    /**
     * 更新未应约初试原因
     *
     * @param failFirstInterviewReasonTO
     * @throws SerException
     */
    @Override
    public void update(FailFirstInterviewReasonTO failFirstInterviewReasonTO) throws SerException {
        failFirstInterviewReasonSer.update(failFirstInterviewReasonTO);
    }
}
