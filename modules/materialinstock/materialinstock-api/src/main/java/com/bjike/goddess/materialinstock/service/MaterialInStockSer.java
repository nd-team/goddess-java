package com.bjike.goddess.materialinstock.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialinstock.bo.AttributeBO;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.entity.MaterialInStock;
import com.bjike.goddess.materialinstock.to.GuidePermissionTO;
import com.bjike.goddess.materialinstock.to.MaterialInStockTO;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialinstock.type.UseState;

import java.util.List;
import java.util.Set;

/**
 * 物资入库业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 04:55 ]
 * @Description: [ 物资入库业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialInStockSer extends Ser<MaterialInStock, MaterialInStockDTO> {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 分页查询物资入库
     *
     * @return class MaterialInStockBO
     * @throws SerException
     */
    List<MaterialInStockBO> list(MaterialInStockDTO dto) throws SerException;

    /**
     * 根据物资状态和物资使用状态查询物资入库
     *
     * @param materialState 物资状态
     * @param useState      物资使用状态
     * @param dto           物资入库dto
     * @return
     * @throws SerException
     */
    List<MaterialInStockBO> findByState(MaterialState materialState, UseState useState, MaterialInStockDTO dto) throws SerException;

    /**
     * 根据物资编号查询物资入库
     *
     * @param materialCoding 物资编号
     * @return class MaterialInStockBO
     * @throws SerException
     */
    MaterialInStockBO findByMaterialCoding(String materialCoding) throws SerException;

    /**
     * 更新物资使用状态
     *
     * @param materialNum 物资编号集合
     * @param useState    使用状态
     * @throws SerException
     */
    void updateUseState(String[] materialNum, UseState useState) throws SerException;

    /**
     * 根据物资编号查询物资
     *
     * @param materialNum 物资编号
     * @return 物资入库集合
     * @throws SerException
     */
    List<MaterialInStock> getMaterialInStocks(String[] materialNum) throws SerException;

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
     * cjh
     * 查找所有入库编号
     *
     * @return
     * @throws SerException
     */
    Set<String> allstockEncoding() throws SerException;

    /**
     *
     * 设备维修中需要修改入库信息
     *
     * @return
     * @throws SerException
     */
    void updateLijuntao(MaterialInStockTO to) throws SerException;

    /**
     * 获取所有组织结构中的部门
     *
     * @return
     * @throws SerException
     */
    default List<String> findAddAllDetails() throws SerException {
        return null;
    }

    /**
     * 获取所有用户
     *
     * @return
     * @throws SerException
     */
    default List<String> findallUser() throws SerException {
        return null;
    }
}