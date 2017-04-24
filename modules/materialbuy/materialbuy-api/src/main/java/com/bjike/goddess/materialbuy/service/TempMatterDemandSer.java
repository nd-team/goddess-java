package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialbuy.bo.TempMatterDemandBO;
import com.bjike.goddess.materialbuy.dto.TempMatterDemandDTO;
import com.bjike.goddess.materialbuy.entity.TempMatterDemand;
import com.bjike.goddess.materialbuy.to.TempMatterDemandTO;

import java.util.List;

/**
 * 临时物资需求业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:08 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TempMatterDemandSer extends Ser<TempMatterDemand, TempMatterDemandDTO> {

    /**
     * 分页查询临时物资需求
     *
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    List<TempMatterDemandBO> list(TempMatterDemandDTO dto) throws SerException;

    /**
     * 保存临时物资需求
     *
     * @param to 临时物资需求to
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    TempMatterDemandBO save(TempMatterDemandTO to) throws SerException;

    /**
     * 根据id删除临时物资需求
     *
     * @param id 临时物资需求唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新临时物资需求
     *
     * @param to 临时物资需求to
     * @throws SerException
     */
    void update(TempMatterDemandTO to) throws SerException;

    /**
     * 审核
     *
     * @param to 临时物资需求
     * @throws SerException
     */
    void audit(TempMatterDemandTO to) throws SerException;

    /**
     * 查看详情
     *
     * @param id 临时物资需求唯一标识
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    TempMatterDemandBO checkDetail(String id) throws SerException;

}