package com.bjike.goddess.materialtransfer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialtransfer.bo.MaterialTransferBO;
import com.bjike.goddess.materialtransfer.dto.MaterialTransferDTO;
import com.bjike.goddess.materialtransfer.entity.MaterialTransfer;
import com.bjike.goddess.materialtransfer.service.MaterialTransferSer;
import com.bjike.goddess.materialtransfer.to.MaterialTransferTO;
import com.bjike.goddess.materialtransfer.type.AuditState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资调动业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialTransferApiImpl")
public class MaterialTransferApiImpl implements MaterialTransferAPI {

    @Autowired
    private MaterialTransferSer materialTransferSer;

    /**
     * 根据id查询物资调动
     *
     * @param id 物资调动唯一标识
     * @return class MaterialTransferBO
     * @throws SerException
     */
    @Override
    public MaterialTransferBO findById(String id) throws SerException {
        MaterialTransfer model = materialTransferSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialTransferBO.class);
    }

    /**
     * 查询总条数
     * @param dto 物资调动dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialTransferDTO dto) throws SerException {
        return materialTransferSer.count(dto);
    }

    /**
     * 分页查询物资调动
     *
     * @return class MaterialTransferBO
     * @throws SerException
     */
    @Override
    public List<MaterialTransferBO> list(MaterialTransferDTO dto) throws SerException {
        return materialTransferSer.list(dto);
    }

    /**
     * 保存物资调动
     *
     * @param to 物资调动to
     * @return class MaterialTransferBO
     * @throws SerException
     */
    @Override
    public MaterialTransferBO save(MaterialTransferTO to) throws SerException {
        return materialTransferSer.save(to);
    }

    /**
     * 根据id删除物资调动
     *
     * @param id 物资调动唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialTransferSer.remove(id);
    }

    /**
     * 更新物资调动
     *
     * @param to 物资调动to
     * @throws SerException
     */
    @Override
    public void update(MaterialTransferTO to) throws SerException {
        materialTransferSer.update(to);
    }

    /**
     * 项目经理审核
     *
     * @param id 物资调动唯一标识
     * @param pmAuditState 项目经理审核状态
     * @throws SerException
     */
    @Override
    public void pmAudit(String id, AuditState pmAuditState) throws SerException {
        materialTransferSer.pmAudit(id, pmAuditState);
    }

    /**
     * 福利模块负责人审核
     *
     * @param id 物资调动唯一标识
     * @param welfareState 福利模块负责人审核状态
     * @throws SerException
     */
    @Override
    public void wealModAudit(String id, AuditState welfareState) throws SerException {
        materialTransferSer.wealModAudit(id, welfareState);
    }

    /**
     * 福利模块负责人确认调配成功
     *
     * @param id 物资调动唯一标识
     * @param recipient 领用人
     * @param confirmDeploy 福利模块负责人确认调配成功
     * @param finishDeployTime 调配成功
     * @throws SerException
     */
    @Override
    public void wealModConfirm(String id, String recipient, Boolean confirmDeploy, String finishDeployTime) throws SerException {
        materialTransferSer.wealModConfirm(id, recipient, confirmDeploy, finishDeployTime);
    }
}