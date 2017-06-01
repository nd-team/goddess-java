package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.MaterialStatusYearSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusYearSumDTO;
import com.bjike.goddess.materialsummary.to.MaterialStatusYearSumTO;

import java.util.List;

/**
 * 物资状态年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ 物资状态年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialStatusYearSumAPI {

    /**
     * 根据id查询物资状态年汇总
     *
     * @param id 物资状态年汇总唯一标识
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    MaterialStatusYearSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 物资状态年汇总dto
     * @throws SerException
     */
    Long count(MaterialStatusYearSumDTO dto) throws SerException;

    /**
     * 分页查询物资状态年汇总
     *
     * @param dto 物资状态年汇总dto
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    List<MaterialStatusYearSumBO> list(MaterialStatusYearSumDTO dto) throws SerException;

    /**
     * 保存物资状态年汇总
     *
     * @param to 物资状态年汇总to
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    MaterialStatusYearSumBO save(MaterialStatusYearSumTO to) throws SerException;

    /**
     * 根据id删除物资状态年汇总
     *
     * @param id 物资状态年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资状态年汇总
     *
     * @param to 物资状态年汇总to
     * @throws SerException
     */
    void update(MaterialStatusYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    List<MaterialStatusYearSumBO> summary() throws SerException;

}