package com.bjike.goddess.materialinstock.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialinstock.bo.AttributeBO;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.entity.MaterialInStock;
import com.bjike.goddess.materialinstock.to.MaterialInStockTO;
import com.bjike.goddess.materialinstock.type.UseState;

import java.util.List;

/**
 * 物资入库业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 04:55 ]
 * @Description: [ 物资入库业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialInStockAPI {

    /**
     * 根据id查询物资入库
     *
     * @param id 物资入库唯一标识
     * @return class MaterialInStockBO
     * @throws SerException
     */
    MaterialInStockBO findById(String id) throws SerException;

    /**
     * 查询物资入库记录条数
     *
     * @param dto 物资入库dto
     * @throws SerException
     */
    Long count(MaterialInStockDTO dto) throws SerException;

    /**
     * 分页查询物资入库
     *
     * @return class MaterialInStockBO
     * @throws SerException
     */
    List<MaterialInStockBO> list(MaterialInStockDTO dto) throws SerException;

    /**
     * 更新物资使用状态
     *
     * @param materialNum 物资编号集合
     * @param useState    使用状态
     * @throws SerException
     */
    void updateUseState(String[] materialNum, UseState useState) throws SerException;

    /**
     * 保存物资入库
     *
     * @param to 物资入库to
     * @return class MaterialInStockBO
     * @throws SerException
     */
    MaterialInStockBO save(MaterialInStockTO to) throws SerException;

    /**
     * 根据id删除物资入库
     *
     * @param id 物资入库唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资入库
     *
     * @param to 物资入库to
     * @throws SerException
     */
    void update(MaterialInStockTO to) throws SerException;

    /**
     * 根据dto查询物资入库
     *
     * @param dto 物资入库dto
     * @return class MaterialInStockBO
     * @throws SerException
     */
    List<MaterialInStockBO> findBOByCis(MaterialInStockDTO dto) throws SerException;

    /**
     * 更新物资入库集合
     *
     * @param listBO 物资入库bo集合
     * @throws SerException
     */
    void updateBO(List<MaterialInStockBO> listBO) throws SerException;

    /**
     * 更新单个物资入库
     *
     * @param bo 物资入库bo
     * @throws SerException
     */
    void updateSingleBO(MaterialInStockBO bo) throws SerException;

    /**
     * 查询单个物资入库
     *
     * @param dto 查询物资入库dto
     * @return class MaterialInStockBO
     * @throws SerException
     */
    MaterialInStockBO findOne(MaterialInStockDTO dto) throws SerException;

    /**
     * 根据物资编号查询物资入库
     *
     * @param materialCoding 物资编号
     * @return class MaterialInStockBO
     * @throws SerException
     */
    MaterialInStockBO findByMaterialCoding(String materialCoding) throws SerException;

    /**
     * 查询所有相同类型的物资入库
     *
     * @return
     * @throws SerException
     */
    List<AttributeBO> findAllKindsType() throws SerException;

    /**
     * 根据属性查找物资入库
     *
     * @param bo 属性bo
     * @return class MaterialInStock
     * @throws SerException
     */
    List<MaterialInStockBO> findByAttribute(AttributeBO bo) throws SerException;

    /**
     * 查询所有的物资入库
     *
     * @return class MaterialInStockBO
     * @throws SerException
     */
    List<MaterialInStockBO> findAll() throws SerException;

}