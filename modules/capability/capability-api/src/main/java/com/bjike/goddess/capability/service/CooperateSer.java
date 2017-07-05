package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CooperateBO;
import com.bjike.goddess.capability.dto.CooperateDTO;
import com.bjike.goddess.capability.entity.Cooperate;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

/**
 * 公司合作对象业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:03 ]
 * @Description: [ 公司合作对象业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CooperateSer extends Ser<Cooperate, CooperateDTO> {

    /**
     * 添加
     *
     * @param Cooperates 公司参与项目数信息
     * @param companyId    公司id
     * @return class CooperateBO
     */
    default CooperateBO addCooperate(String[] Cooperates, String companyId) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param Cooperates 公司参与项目数信息
     * @param companyId    公司id
     * @return class CooperateBO
     */
    default CooperateBO editCooperate(String[] Cooperates, String companyId) throws SerException {
        return null;
    }

    /**
     * 删除公司参与项目数
     *
     * @param id id
     */
    default void deleteCooperate(String id) throws SerException {
        return;
    }


}