package com.bjike.goddess.materialcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialcheck.bo.MaterialInventoryBO;
import com.bjike.goddess.materialcheck.dto.MaterialInventoryDTO;
import com.bjike.goddess.materialcheck.to.MaterialInventoryTO;

import java.util.List;

/**
 * 物资盘点业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:00 ]
 * @Description: [ 物资盘点业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialInventoryAPI {

    /**
     * 根据id查询物资盘点
     *
     * @param id 物资盘点唯一标识
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    MaterialInventoryBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 物资盘点dto
     * @throws SerException
     */
    Long count(MaterialInventoryDTO dto) throws SerException;

    /**
     * 分页查询物资盘点
     *
     * @param dto 物资盘点dto
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    List<MaterialInventoryBO> list(MaterialInventoryDTO dto) throws SerException;

    /**
     * 保存物资盘点
     *
     * @param to 物资盘点to
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    MaterialInventoryBO save(MaterialInventoryTO to) throws SerException;

    /**
     * 根据id删除物资盘点
     *
     * @param id 物资盘点唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资盘点
     *
     * @param to 物资盘点to
     * @throws SerException
     */
    void update(MaterialInventoryTO to) throws SerException;

    /**
     * 经办人确认情况
     *
     * @param id             物资盘点唯一标识
     * @param operatorStatus 经办人确认情况
     * @throws SerException
     */
    void operatorConfirm(String id, String operatorStatus) throws SerException;

    /**
     * 账务模块核实
     *
     * @param id            物资盘点唯一标识
     * @param accountStatus 账务模块核实情况
     * @throws SerException
     */
    void accountModuleConfirm(String id, String accountStatus) throws SerException;

    /**
     * 总经办审核
     *
     * @param id        物资盘点唯一标识
     * @param zjbStatus 总经办审核意见
     * @throws SerException
     */
    void zjbConfirm(String id, String zjbStatus) throws SerException;

}