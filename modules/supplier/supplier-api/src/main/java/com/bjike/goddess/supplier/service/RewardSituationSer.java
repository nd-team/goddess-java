package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.supplier.bo.RewardSituationBO;
import com.bjike.goddess.supplier.dto.RewardSituationDTO;
import com.bjike.goddess.supplier.entity.RewardSituation;
import com.bjike.goddess.supplier.to.ContactSituationTO;

import java.util.List;

/**
 * 获奖情况业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:53:15.056 ]
 * @Description: [ 获奖情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RewardSituationSer extends Ser<RewardSituation, RewardSituationDTO> {


    /**
     * 根据供应商基本信息ID查询获奖情况
     *
     * @param info_id 供应商基本信息ID
     * @return
     * @throws SerException
     */
    default List<RewardSituationBO> findByInformation(String info_id) throws SerException {
        return null;
    }

    /**
     * 保存供应商获奖情况数据
     *
     * @param to 供应商获奖情况传输对象
     * @return
     * @throws SerException
     */
    default RewardSituationBO save(ContactSituationTO to) throws SerException {
        return null;
    }

    /**
     * 修改供应商获奖情况数据
     *
     * @param to 供应商获奖情况传输对象
     * @return
     * @throws SerException
     */
    default RewardSituationBO update(ContactSituationTO to) throws SerException {
        return null;
    }

    /**
     * 删除供应商获奖情况数据
     *
     * @param id 供应商获奖情况id
     * @return
     * @throws SerException
     */
    default RewardSituationBO delete(String id) throws SerException {
        return null;
    }

}