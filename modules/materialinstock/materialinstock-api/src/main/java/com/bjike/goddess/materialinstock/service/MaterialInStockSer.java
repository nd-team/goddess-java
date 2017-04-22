package com.bjike.goddess.materialinstock.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.entity.MaterialInStock;
import com.bjike.goddess.materialinstock.to.MaterialInStockTO;

import java.util.List;
import java.util.Map;

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
     * 分页查询物资入库
     *
     * @return class MaterialInStockBO
     * @throws SerException
     */
    List<MaterialInStockBO> list(MaterialInStockDTO dto) throws SerException;

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
     * 文件上传
     *
     * @param maps  文件名，byte 文件字节
     * @param path 上传路径
     */
    void upload(Map<String, byte[]> maps, String path) throws SerException;

}