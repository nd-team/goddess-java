package com.bjike.goddess.materialreceive.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialinstock.type.UseState;
import com.bjike.goddess.materialreceive.bo.MaterialReceiveBO;
import com.bjike.goddess.materialreceive.dto.MaterialReceiveDTO;
import com.bjike.goddess.materialreceive.entity.MaterialReceive;
import com.bjike.goddess.materialreceive.to.MaterialReceiveTO;
import com.bjike.goddess.materialreceive.to.MaterialReturnTO;
import com.bjike.goddess.materialreceive.type.AuditState;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资领用业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:41 ]
 * @Description: [ 物资领用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialreceiveSerCache")
@Service
public class MaterialReceiveSerImpl extends ServiceImpl<MaterialReceive, MaterialReceiveDTO> implements MaterialReceiveSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private MaterialInStockAPI materialInStockAPI;


    /**
     * 分页查询物资领用
     *
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    @Override
    public List<MaterialReceiveBO> list(MaterialReceiveDTO dto) throws SerException {
        List<MaterialReceive> list = super.findByPage(dto);
        List<MaterialReceiveBO> listBO = BeanTransform.copyProperties(list, MaterialReceiveBO.class);
        return listBO;
    }

    /**
     * 保存物资领用
     *
     * @param to 物资领用to
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialReceiveBO save(MaterialReceiveTO to) throws SerException {
        Integer quantity = setReceive(to);//设置领用数量和领用编号
        return saveModel(to, quantity);
    }

    /**
     * 保存物资领用
     *
     * @param to
     * @param quantity
     * @return
     * @throws SerException
     */
    private MaterialReceiveBO saveModel(MaterialReceiveTO to, Integer quantity) throws SerException {
        MaterialReceive entity = BeanTransform.copyProperties(to, MaterialReceive.class, true);
        entity.setAuditState(AuditState.UNAUDITED);
        entity.setQuantity(quantity);//设置领用数量
        entity = super.save(entity);
        MaterialReceiveBO bo = BeanTransform.copyProperties(entity, MaterialReceiveBO.class);
        return bo;
    }

    /**
     * 设置领用编号
     *
     * @param to
     * @return
     * @throws SerException
     */
    private Integer setReceive(MaterialReceiveTO to) throws SerException {
        String[] materialNum = checkMaterialNum(to);//校验领用物资编号是否为空
        Integer quantity = setMaterialNo(to, materialNum);//设置领用编号
        materialInStockAPI.updateUseState(materialNum, UseState.RECEIVE);    //更新物资使用状态
        return quantity;
    }

    /**
     * 设置物资领用中的物资编号
     *
     * @param to          物资领用to
     * @param materialNum 物资编号
     * @return
     */
    private Integer setMaterialNo(MaterialReceiveTO to, String[] materialNum) {
        Integer quantity = to.getMaterialNum().length;//领用数量
        StringBuilder materialNo = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            if (i < (quantity - 1)) {
                materialNo.append(materialNum[i]).append(",");
            } else {
                materialNo.append(materialNum[i]);
            }
        }
        to.setMaterialNo(materialNo.toString());//设置领用编号
        return quantity;
    }

    /**
     * 校验领用物资是否为空
     *
     * @param to
     * @return
     * @throws SerException
     */
    private String[] checkMaterialNum(MaterialReceiveTO to) throws SerException {
        String[] materialNum = to.getMaterialNum();//获取入库编码
        Boolean materialNumNotEmpty = (materialNum != null) && (materialNum.length > 0);
        if (!materialNumNotEmpty) {
            throw new SerException("物资入库编码为空,无法领用.");
        }
        return materialNum;
    }

    /**
     * 根据id删除物资领用
     *
     * @param id 物资领用唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新物资领用
     *
     * @param to 物资领用to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialReceiveTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialReceive model = super.findById(to.getId());
            if (model != null) {
                updateUseState(model, UseState.INSTOCK);//更新物资使用状态
                setReceive(to);
                updateMaterialReceive(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资入库状态
     *
     * @param model    物资入库
     * @param useState 使用状态
     * @throws SerException
     */
    private void updateUseState(MaterialReceive model, UseState useState) throws SerException {
        String materialNo = model.getMaterialNo();
        String[] materialNum = materialNo.split(",");
        materialInStockAPI.updateUseState(materialNum, useState);
    }

    /**
     * 更新物资领用
     *
     * @param to    物资领用to
     * @param model 物资领用
     * @throws SerException
     */
    private void updateMaterialReceive(MaterialReceiveTO to, MaterialReceive model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 审核
     *
     * @param id           物资领用唯一标识
     * @param auditState   审核状态
     * @param auditOpinion 审核意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(String id, AuditState auditState, String auditOpinion) throws SerException {
        String curUsername = userAPI.currentUser().getUsername();
        if (StringUtils.isNotBlank(id)) {
            MaterialReceive model = super.findById(id);
            boolean auditorIsNotEmpty = (model != null) && (StringUtils.isNotEmpty(model.getAuditor()));
            if (auditorIsNotEmpty && (model.getAuditor().equals(curUsername))) {
                model.setAuditState(auditState);
                model.setAuditOpinion(auditOpinion);
                super.update(model);
            } else {
                throw new SerException("审核人与当前用户不符,无法进行审核.");
            }
        } else {
            throw new SerException("更新id不能为空");
        }
    }

    /**
     * 领用成功
     *
     * @param to 物资领用to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void receiveOver(MaterialReceiveTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            MaterialReceive model = super.findById(to.getId());
            model.setModel(to.getModel());
            model.setOldStorageArea(to.getOldStorageArea());
            model.setOldPrincipal(to.getOldPrincipal());
            model.setHandler(to.getHandler());
            super.update(model);
        } else {
            throw new SerException("更新id不能为空");
        }

    }

    /**
     * 物资归还
     *
     * @param to 物资归还to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void materialReturn(MaterialReturnTO to) throws SerException {
        setUseStateToInStock(to);//更新使用状态为在库
        String id = to.getId();
        if (StringUtils.isBlank(id)) {
            throw new SerException("物资归还id为空,无法执行归还操作");
        }
        MaterialReceive model = super.findById(id);
        Boolean ifReturn = to.getIfReturn();//是否归还
        MaterialState materialState = to.getMaterialState();//物资状态
        String returnTime = to.getReturnTime();//归还时间
        AuditState auditCase = to.getAuditCase();//审核情况
        model.setIfReturn(ifReturn);
        model.setMaterialState(materialState);
        model.setReturnTime(DateUtil.parseDateTime(returnTime));
        model.setAuditCase(auditCase);
        super.update(model);
    }

    /**
     * 更新用户状态为在库
     *
     * @param to 物资领用to
     * @throws SerException
     */
    private void setUseStateToInStock(MaterialReturnTO to) throws SerException {
        String[] materialNum = to.getMaterialNum();//获取归还的物资编号
        materialInStockAPI.updateUseState(materialNum, UseState.INSTOCK);
    }

}