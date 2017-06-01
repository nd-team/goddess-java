package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.FailPhoneReasonBO;
import com.bjike.goddess.recruit.dto.FailPhoneReasonDTO;
import com.bjike.goddess.recruit.entity.FailPhoneReason;
import com.bjike.goddess.recruit.to.FailPhoneReasonTO;

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
public interface FailPhoneReasonSer extends Ser<FailPhoneReason, FailPhoneReasonDTO> {

    /**
     * 分页查询未成功通话原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FailPhoneReasonBO> list(FailPhoneReasonDTO dto) throws SerException;

    /**
     * 保存未成功通话原因
     *
     * @param failPhoneReasonTO
     * @return
     * @throws SerException
     */
    FailPhoneReasonBO save(FailPhoneReasonTO failPhoneReasonTO) throws SerException;

    /**
     * 根据id删除未成功通话原因
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新未成功通话原因
     *
     * @param failPhoneReasonTO
     * @throws SerException
     */
    void update(FailPhoneReasonTO failPhoneReasonTO) throws SerException;

}
