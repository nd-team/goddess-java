package com.bjike.goddess.materialtransfer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.materialtransfer.bo.MaterialTransferBO;
import com.bjike.goddess.materialtransfer.dto.MaterialTransferDTO;
import com.bjike.goddess.materialtransfer.entity.MaterialTransfer;
import com.bjike.goddess.materialtransfer.to.MaterialTransferTO;
import com.bjike.goddess.materialtransfer.type.AuditState;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 物资调动业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialtransferSerCache")
@Service
public class MaterialTransferSerImpl extends ServiceImpl<MaterialTransfer, MaterialTransferDTO> implements MaterialTransferSer {

    @Autowired
    private UserAPI userAPI;

    /**
     * 分页查询物资调动
     *
     * @return class MaterialTransferBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialTransferBO> list(MaterialTransferDTO dto) throws SerException {
        List<MaterialTransfer> list = super.findByPage(dto);
        List<MaterialTransferBO> listBO = BeanTransform.copyProperties(list, MaterialTransferBO.class);
        return listBO;
    }

    /**
     * 保存物资调动
     *
     * @param to 物资调动to
     * @return class MaterialTransferBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialTransferBO save(MaterialTransferTO to) throws SerException {
        MaterialTransfer entity = BeanTransform.copyProperties(to, MaterialTransfer.class, true);
        entity = setAttributes(entity);
        entity = super.save(entity);
        MaterialTransferBO bo = BeanTransform.copyProperties(entity, MaterialTransferBO.class);
        return bo;
    }

    /**
     * 设置属性
     *
     * @param entity 物资调动
     * @return
     */
    private MaterialTransfer setAttributes(MaterialTransfer entity) throws SerException {
        String curUsername = userAPI.currentUser().getUsername();
        entity.setApplyDate(LocalDate.now());//设置申请日期
        entity.setHandler(curUsername);//经手人
        entity.setPmAuditState(AuditState.NONE);//项目经历审核位未审核
        entity.setWelfareState(AuditState.NONE);//福利模块负责人审核状态为未审核
        entity.setConfirmDeploy(Boolean.FALSE); //设置福利模块负责人确认调配成功为未确认
        return entity;
    }

    /**
     * 根据id删除物资调动
     *
     * @param id 物资调动唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新物资调动
     *
     * @param to 物资调动to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialTransferTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            MaterialTransfer model = super.findById(to.getId());
            if (model != null) {
                updateMaterialTransfer(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资调动
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialTransfer(MaterialTransferTO to, MaterialTransfer model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 项目经理审核
     *
     * @param id 物资调动唯一标识
     * @param pmAuditState 项目经理审核状态
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void pmAudit(String id, AuditState pmAuditState) throws SerException {
        String curUsername = userAPI.currentUser().getUsername();
        MaterialTransfer model = super.findById(id);
        String pm = model.getOriginalPM();
        if (curUsername.equals(pm)) {
            model.setPmAuditState(pmAuditState);
            super.update(model);
        } else {
            throw new SerException("您好,您不是项目经理,无权审核!");
        }

    }

    /**
     * 福利模块负责人审核
     *
     * @param id 物资调动唯一标识
     * @param welfareState 物资调动to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void wealModAudit(String id, AuditState welfareState) throws SerException {
        String curUsername = userAPI.currentUser().getUsername();
        MaterialTransfer model = super.findById(id);
        if (curUsername.equals(model.getWelfareModule())) {
            model.setWelfareState(welfareState);
            super.update(model);
        } else {
            throw new SerException("您好,您不是福利模块负责人,无权审核.");
        }

    }

    /**
     * 福利模块负责人确认调配成功
     *
     * @param id 物资调动唯一标识
     * @param recipient        领用人
     * @param confirmDeploy    福利模块负责人确认调配成功
     * @param finishDeployTime 调配成功
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void wealModConfirm(String id, String recipient, Boolean confirmDeploy, String finishDeployTime) throws SerException {
        String curUsername = userAPI.currentUser().getUsername();
        MaterialTransfer model = super.findById(id);
        if (curUsername.equals(model.getWelfareModule())) {
            model.setRecipient(recipient);
            model.setConfirmDeploy(confirmDeploy);
            model.setFinishDeployTime(DateUtil.parseDateTime(finishDeployTime));
            super.update(model);
        } else {
            throw new SerException("您好,您不是福利模块负责人,无权审核.");
        }
    }
}