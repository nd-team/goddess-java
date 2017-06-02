package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FailPhoneReasonBO;
import com.bjike.goddess.recruit.dto.FailPhoneReasonDTO;
import com.bjike.goddess.recruit.entity.FailPhoneReason;
import com.bjike.goddess.recruit.service.FailPhoneReasonSer;
import com.bjike.goddess.recruit.to.FailPhoneReasonTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未成功通话原因
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("failPhoneReasonApiImpl")
public class FailPhoneReasonApiImpl implements FailPhoneReasonAPI {

    @Autowired
    private FailPhoneReasonSer failPhoneReasonSer;

    /**
     * 根据id查询未成功通话原因
     *
     * @param id 未成功通话原因唯一标识
     * @return class FailPhoneReasonBO
     * @throws SerException
     */
    @Override
    public FailPhoneReasonBO findById(String id) throws SerException {
        FailPhoneReason model = failPhoneReasonSer.findById(id);
        return BeanTransform.copyProperties(model, FailPhoneReasonBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 未成功通话原因dto
     * @throws SerException
     */
    @Override
    public Long count(FailPhoneReasonDTO dto) throws SerException {
        return failPhoneReasonSer.count(dto);
    }

    /**
     * 分页查询未成功通话原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<FailPhoneReasonBO> list(FailPhoneReasonDTO dto) throws SerException {
        return failPhoneReasonSer.list(dto);
    }

    /**
     * 保存未成功通话原因
     *
     * @param failPhoneReasonTO
     * @return
     * @throws SerException
     */
    @Override
    public FailPhoneReasonBO save(FailPhoneReasonTO failPhoneReasonTO) throws SerException {
        return failPhoneReasonSer.save(failPhoneReasonTO);
    }

    /**
     * 根据id删除未成功通话原因
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        failPhoneReasonSer.remove(id);
    }

    /**
     * 更新未成功通话原因
     *
     * @param failPhoneReasonTO
     * @throws SerException
     */
    @Override
    public void update(FailPhoneReasonTO failPhoneReasonTO) throws SerException {
        failPhoneReasonSer.update(failPhoneReasonTO);
    }
}
