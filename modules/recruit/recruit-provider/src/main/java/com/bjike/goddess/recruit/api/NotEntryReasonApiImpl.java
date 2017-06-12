package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.NotEntryReasonBO;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.entity.NotEntryReason;
import com.bjike.goddess.recruit.service.NotEntryReasonSer;
import com.bjike.goddess.recruit.to.NotEntryReasonTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未入职原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("notEntryReasonApiImpl")
public class NotEntryReasonApiImpl implements NotEntryReasonAPI {

    @Autowired
    private NotEntryReasonSer notEntryReasonSer;

    /**
     * 根据id查询未入职原因
     *
     * @param id 未入职原因唯一标识
     * @return class NotEntryReasonBO
     * @throws SerException
     */
    @Override
    public NotEntryReasonBO findById(String id) throws SerException {
        NotEntryReason model = notEntryReasonSer.findById(id);
        return BeanTransform.copyProperties(model, NotEntryReasonBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 未入职原因dto
     * @throws SerException
     */
    @Override
    public Long count(NotEntryReasonDTO dto) throws SerException {
        return notEntryReasonSer.count(dto);
    }

    /**
     * 分页查询未入职原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<NotEntryReasonBO> list(NotEntryReasonDTO dto) throws SerException {
        return notEntryReasonSer.list(dto);
    }

    /**
     * 保存未入职原因
     *
     * @param notEntryReasonTO
     * @return
     * @throws SerException
     */
    @Override
    public NotEntryReasonBO save(NotEntryReasonTO notEntryReasonTO) throws SerException {
        return notEntryReasonSer.save(notEntryReasonTO);
    }

    /**
     * 根据id删除未入职原因
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        notEntryReasonSer.remove(id);
    }

    /**
     * 更新未入职原因
     *
     * @param notEntryReasonTO
     * @throws SerException
     */
    @Override
    public void update(NotEntryReasonTO notEntryReasonTO) throws SerException {
        notEntryReasonSer.update(notEntryReasonTO);
    }
}
