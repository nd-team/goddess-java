package com.bjike.goddess.materialtransfer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.entity.MaterialInStock;
import com.bjike.goddess.materialinstock.service.MaterialInStockSer;
import com.bjike.goddess.materialinstock.type.UseState;
import com.bjike.goddess.materialtransfer.bo.MaterialTransferBO;
import com.bjike.goddess.materialtransfer.dto.MaterialTransferDTO;
import com.bjike.goddess.materialtransfer.entity.MaterialTransfer;
import com.bjike.goddess.materialtransfer.to.MaterialTransferTO;
import com.bjike.goddess.materialtransfer.type.AuditState;
import com.bjike.goddess.materialtransfer.type.MaterialState;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private MaterialInStockAPI materialInStockAPI;

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
        if (to == null) {
            return null;
        }
        MaterialTransferBO bo = BeanTransform.copyProperties(to, MaterialTransferBO.class);
        bo = setAttributes(bo);//设置物资属性
        MaterialTransfer entity = BeanTransform.copyProperties(bo, MaterialTransfer.class);
        entity = super.save(entity);
        MaterialTransferBO transferBO = BeanTransform.copyProperties(entity, MaterialTransferBO.class);
        return transferBO;
    }

    /**
     * 设置属性
     *
     * @param bo 物资调动
     * @return
     */
    private MaterialTransferBO setAttributes(MaterialTransferBO bo) throws SerException {
        MaterialInStockBO inStockBO = checkMaterialInStock(bo);//检验是否为空
        String curUsername = userAPI.currentUser().getUsername();
        updateInStock(bo, inStockBO, curUsername);  //更新物资入库信息
        return setTransferProperties(bo, inStockBO, curUsername);
    }

    /**
     * 更新物资入库信息
     *
     * @param transferBO 物资调动信息bo
     * @param inStockBO 物资入库bo
     * @param curUsername 当前用户姓名
     * @throws SerException
     */
    private MaterialInStockBO updateInStock(MaterialTransferBO transferBO, MaterialInStockBO inStockBO, String curUsername) throws SerException {
        String lendArea = transferBO.getTransferredArea(); //调入地区
        inStockBO.setLender(curUsername);            //设置外借人
        inStockBO.setLendArea(lendArea);             //设置外借地区
        inStockBO.setUseState(UseState.TRANSFER);    //设置使用状态为外借
        materialInStockAPI.updateSingleBO(inStockBO);//更新物资入库
        return inStockBO;
    }

    /**
     * 设置物资调动信息
     *
     * @param bo 物资调动
     * @param inStockBO 物资入库bo
     * @param curUsername 当前用户姓名
     * @return
     */
    private MaterialTransferBO setTransferProperties(MaterialTransferBO bo, MaterialInStockBO inStockBO, String curUsername) {
        String materialType = inStockBO.getMaterialType();//物资类型
        String materialName = inStockBO.getMaterialName();//物资名称
        String materialModel = inStockBO.getMaterialModel();//物资型号
        Integer quantity = inStockBO.getQuantity();         //数量
        String unit = inStockBO.getUnit();                  //单位
        String storageArea = inStockBO.getStorageArea();    //存储地区
        bo.setMaterialType(materialType);           //设置物资状态
        bo.setMaterialName(materialName);           //设置物资名称
        bo.setModel(materialModel);                 //设置型号
        bo.setQuantity(quantity);                   //设置数量
        bo.setUnit(unit);                           //设置单位
        bo.setArchSaveArea(storageArea);            //设置原存储地区
        bo.setMaterialState(MaterialState.INTACT);  //设置物资状态为完好
        bo.setApplyDate(LocalDate.now().toString());//设置申请日期
        bo.setHandler(curUsername);//经手人
        bo.setPmAuditState(AuditState.NONE);//项目经历审核位未审核
        bo.setWelfareState(AuditState.NONE);//福利模块负责人审核状态为未审核
        bo.setConfirmDeploy(Boolean.FALSE); //设置福利模块负责人确认调配成功为未确认

        return bo;
    }

    /**
     * 检查物资入库
     *
     * @param bo 物资入库实体
     * @return class MaterialInStockBO
     * @throws SerException
     */
    private MaterialInStockBO checkMaterialInStock(MaterialTransferBO bo) throws SerException {
        String stockEncoding = bo.getInstockCode();
        MaterialInStockDTO dto = new MaterialInStockDTO();
        dto.getConditions().add(Restrict.eq("stockEncoding", stockEncoding));
        MaterialInStockBO model = materialInStockAPI.findOne(dto);
        if (model == null) {
            throw new SerException("该物资不存在,无法进行调动.");
        }
        return model;
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