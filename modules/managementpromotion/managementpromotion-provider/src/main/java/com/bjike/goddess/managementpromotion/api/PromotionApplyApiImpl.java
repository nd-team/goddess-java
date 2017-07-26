package com.bjike.goddess.managementpromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managementpromotion.bo.PromotionApplyBO;
import com.bjike.goddess.managementpromotion.dto.PromotionApplyDTO;
import com.bjike.goddess.managementpromotion.service.PromotionApplySer;
import com.bjike.goddess.managementpromotion.to.GuidePermissionTO;
import com.bjike.goddess.managementpromotion.to.PromotionApplyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理等级晋升申请业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 02:04 ]
 * @Description: [ 管理等级晋升申请业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("promotionApplyApiImpl")
public class PromotionApplyApiImpl implements PromotionApplyAPI {
    @Autowired
    private PromotionApplySer promotionApplySer;

    @Override
    public PromotionApplyBO save(PromotionApplyTO to) throws SerException {
        return promotionApplySer.save(to);
    }

    @Override
    public void delete(String id) throws SerException {
        promotionApplySer.delete(id);
    }

    @Override
    public void edit(PromotionApplyTO to) throws SerException {
        promotionApplySer.edit(to);
    }

    @Override
    public List<PromotionApplyBO> find(PromotionApplyDTO dto) throws SerException {
        return promotionApplySer.find(dto);
    }

    @Override
    public PromotionApplyBO findByID(String id) throws SerException {
        return promotionApplySer.findByID(id);
    }

    @Override
    public void conform(PromotionApplyTO to) throws SerException {
        promotionApplySer.conform(to);
    }

    @Override
    public void writePromotionCriteria(PromotionApplyTO to) throws SerException {
        promotionApplySer.writePromotionCriteria(to);
    }

    @Override
    public void writeProjectManager(PromotionApplyTO to) throws SerException {
        promotionApplySer.writeProjectManager(to);
    }

    @Override
    public void writeResourceDepartment(PromotionApplyTO to) throws SerException {
        promotionApplySer.writeResourceDepartment(to);
    }

    @Override
    public void writeCommerceDepartment(PromotionApplyTO to) throws SerException {
        promotionApplySer.writeCommerceDepartment(to);
    }

    @Override
    public void writeModuler(PromotionApplyTO to) throws SerException {
        promotionApplySer.writeModuler(to);
    }

    @Override
    public void writeManager(PromotionApplyTO to) throws SerException {
        promotionApplySer.writeManager(to);
    }

    @Override
    public List<PromotionApplyBO> rank() throws SerException {
        return promotionApplySer.rank();
    }

    @Override
    public Long count(PromotionApplyDTO dto) throws SerException {
        return promotionApplySer.count(dto);
    }

    @Override
    public void send() throws SerException {
        promotionApplySer.send();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return promotionApplySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return promotionApplySer.guidePermission(guidePermissionTO);
    }
}