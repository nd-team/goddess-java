package com.bjike.goddess.materialreceive.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialreceive.bo.MaterialReceiveBO;
import com.bjike.goddess.materialreceive.dto.MaterialReceiveDTO;
import com.bjike.goddess.materialreceive.to.MaterialReceiveTO;
import com.bjike.goddess.materialreceive.type.AuditState;

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
public interface MaterialReceiveAPI {

    /**
     * 根据id查询物资领用
     *
     * @param id 物资领用唯一标识
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    MaterialReceiveBO findById(String id) throws SerException;

    /**
     * 查询物资领用记录条数
     *
     * @param dto 物资领用dto
     * @throws SerException
     */
    Long count(MaterialReceiveDTO dto) throws SerException;
    
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
     * @param id 物资领用唯一标识
     * @param auditState 审核状态
     * @param auditOpinion 审核意见
     * @throws SerException
     */
    void audit(String id, AuditState auditState, String auditOpinion) throws SerException;

    /**
     * 领用完成
     *
     * @param to 物资领用to
     * @throws SerException
     */
    void receiveOver(MaterialReceiveTO to) throws SerException;

    /**
     * 物资领用后归还
     *
     * @param to 物资领用to
     * @throws SerException
     */
    void materialReturn(MaterialReceiveTO to) throws SerException;

}