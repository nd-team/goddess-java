package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.AreaTransferDaySumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferDaySumDTO;
import com.bjike.goddess.materialsummary.to.AreaTransferDaySumTO;

import java.util.List;

/**
 * 地区调动日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:50 ]
 * @Description: [ 地区调动日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaTransferDaySumAPI {

    /**
     * 根据id查询地区调动日汇总
     *
     * @param id 地区调动日汇总唯一标识
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    AreaTransferDaySumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 地区调动日汇总dto
     * @throws SerException
     */
    Long count(AreaTransferDaySumDTO dto) throws SerException;

    /**
     * 分页查询地区调动日汇总
     *
     * @param dto 地区调动日汇总dto
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    List<AreaTransferDaySumBO> list(AreaTransferDaySumDTO dto) throws SerException;

    /**
     * 保存地区调动日汇总
     *
     * @param to 地区调动日汇总to
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    AreaTransferDaySumBO save(AreaTransferDaySumTO to) throws SerException;

    /**
     * 根据id删除地区调动日汇总
     *
     * @param id 地区调动日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区调动日汇总
     *
     * @param to 地区调动日汇总to
     * @throws SerException
     */
    void update(AreaTransferDaySumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    List<AreaTransferDaySumBO> summary() throws SerException;

}