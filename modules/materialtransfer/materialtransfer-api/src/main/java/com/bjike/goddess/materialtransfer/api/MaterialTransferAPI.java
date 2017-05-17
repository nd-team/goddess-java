package com.bjike.goddess.materialtransfer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialtransfer.bo.MaterialTransferBO;
import com.bjike.goddess.materialtransfer.dto.MaterialTransferDTO;
import com.bjike.goddess.materialtransfer.to.MaterialTransferTO;
import com.bjike.goddess.materialtransfer.type.AuditState;

import java.util.List;

/**
 * 物资调动业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialTransferAPI {

    /**
     * 根据id查询物资调动
     *
     * @param id 物资调动唯一标识
     * @return class MaterialTransferBO
     * @throws SerException
     */
    MaterialTransferBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 物资调动dto
     * @throws SerException
     */
    Long count(MaterialTransferDTO dto) throws SerException;

    /**
     * 分页查询物资调动
     *
     * @return class MaterialTransferBO
     * @throws SerException
     */
    List<MaterialTransferBO> list(MaterialTransferDTO dto) throws SerException;

    /**
     * 保存物资调动
     *
     * @param to 物资调动to
     * @return class MaterialTransferBO
     * @throws SerException
     */
    MaterialTransferBO save(MaterialTransferTO to) throws SerException;

    /**
     * 根据id删除物资调动
     *
     * @param id 物资调动唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资调动
     *
     * @param to 物资调动to
     * @throws SerException
     */
    void update(MaterialTransferTO to) throws SerException;

    /**
     * 项目经理审核
     *
     * @param id           物资调动唯一标识
     * @param pmAuditState 项目经理审核状态
     * @throws SerException
     */
    void pmAudit(String id, AuditState pmAuditState) throws SerException;

    /**
     * 福利模块负责人审核
     *
     * @param id           物资调动唯一标识
     * @param welfareState 福利模块负责人审核状态
     * @throws SerException
     */
    void wealModAudit(String id, AuditState welfareState) throws SerException;

    /**
     * 福利模块负责人确认调配成功
     *
     * @param id               物资调动唯一标识
     * @param recipient        领用人
     * @param confirmDeploy    福利模块负责人确认调配成功
     * @param finishDeployTime 调配成功
     * @throws SerException
     */
    void wealModConfirm(String id, String recipient, Boolean confirmDeploy, String finishDeployTime) throws SerException;

}