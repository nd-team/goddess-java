package com.bjike.goddess.materialreceive.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialreceive.bo.MaterialReceiveBO;
import com.bjike.goddess.materialreceive.dto.MaterialReceiveDTO;
import com.bjike.goddess.materialreceive.entity.MaterialReceive;
import com.bjike.goddess.materialreceive.to.MaterialReceiveTO;

import java.util.List;

/**
 * 物资领用业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:41 ]
 * @Description: [ 物资领用业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialReceiveSer extends Ser<MaterialReceive, MaterialReceiveDTO> {

    /**
     * 分页查询物资领用
     *
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    List<MaterialReceiveBO> list(MaterialReceiveDTO dto) throws SerException;

    /**
     * 保存物资领用
     *
     * @param to 物资领用to
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    MaterialReceiveBO save(MaterialReceiveTO to) throws SerException;

    /**
     * 根据id删除物资领用
     *
     * @param id 物资领用唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资领用
     *
     * @param to 物资领用to
     * @throws SerException
     */
    void update(MaterialReceiveTO to) throws SerException;

    /**
     * 审核
     *
     * @param to 物资领用to
     * @throws SerException
     */
    void audit(MaterialReceiveTO to) throws SerException;

    /**
     * 领用完成
     *
     * @param to 物资领用to
     * @throws SerException
     */
    void receiveOver(MaterialReceiveTO to) throws SerException;

    /**
     * 物资归还
     *
     * @param to 物资领用to
     * @throws SerException
     */
    void materialReturn(MaterialReceiveTO to) throws SerException;

}