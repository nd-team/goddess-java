package com.bjike.goddess.materialreceive.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialreceive.bo.MaterialReceiveBO;
import com.bjike.goddess.materialreceive.dto.MaterialReceiveDTO;
import com.bjike.goddess.materialreceive.entity.MaterialReceive;
import com.bjike.goddess.materialreceive.to.MaterialReceiveTO;
import com.bjike.goddess.materialreceive.to.MaterialReturnTO;
import com.bjike.goddess.materialreceive.type.AuditState;

import java.util.List;

/**
 * 物资领用归还登记业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:41 ]
 * @Description: [ 物资领用归还登记业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialReceiveSer extends Ser<MaterialReceive, MaterialReceiveDTO> {

    /**
     * 分页查询物资领用归还登记
     *
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    List<MaterialReceiveBO> list(MaterialReceiveDTO dto) throws SerException;

    /**
     * 保存物资领用归还登记
     *
     * @param to 物资领用归还登记to
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    MaterialReceiveBO save(MaterialReceiveTO to) throws SerException;

    /**
     * 根据id删除物资领用归还登记
     *
     * @param id 物资领用归还登记唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资领用归还登记
     *
     * @param to 物资领用归还登记to
     * @throws SerException
     */
    void update(MaterialReceiveTO to) throws SerException;

    /**
     * 审核
     *
     * @param id 物资领用归还登记唯一标识
     * @param auditState 审核状态
     * @param auditOpinion 审核意见
     * @throws SerException
     */
    void audit(String id, AuditState auditState, String auditOpinion) throws SerException;

    /**
     * 领用完成
     *
     * @param to 物资领用归还登记to
     * @throws SerException
     */
    void receiveOver(MaterialReceiveTO to) throws SerException;

    /**
     * 物资归还
     *
     * @param to 物资归还to
     * @throws SerException
     */
    void materialReturn(MaterialReturnTO to) throws SerException;

}