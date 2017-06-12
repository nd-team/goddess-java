package com.bjike.goddess.materialreceive.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialreceive.bo.MaterialReceiveBO;
import com.bjike.goddess.materialreceive.dto.MaterialReceiveDTO;
import com.bjike.goddess.materialreceive.entity.MaterialReceive;
import com.bjike.goddess.materialreceive.service.MaterialReceiveSer;
import com.bjike.goddess.materialreceive.to.MaterialReceiveTO;
import com.bjike.goddess.materialreceive.to.MaterialReturnTO;
import com.bjike.goddess.materialreceive.type.AuditState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资领用归还登记业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:41 ]
 * @Description: [ 物资领用归还登记业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialReceiveApiImpl")
public class MaterialReceiveApiImpl implements MaterialReceiveAPI {

    @Autowired
    private MaterialReceiveSer materialReceiveSer;

    /**
     * 根据id查询物资领用归还登记
     *
     * @param id 物资领用归还登记唯一标识
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    @Override
    public MaterialReceiveBO findById(String id) throws SerException {
        MaterialReceive model = materialReceiveSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialReceiveBO.class);
    }

    /**
     * 查询物资领用归还登记记录条数
     *
     * @param dto 物资领用归还登记dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialReceiveDTO dto) throws SerException {
        return materialReceiveSer.count(dto);
    }

    /**
     * 分页查询物资领用归还登记
     *
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    @Override
    public List<MaterialReceiveBO> list(MaterialReceiveDTO dto) throws SerException {
        return materialReceiveSer.list(dto);
    }

    /**
     * 保存物资领用归还登记
     *
     * @param to 物资领用归还登记to
     * @return class MaterialReceiveBO
     * @throws SerException
     */
    @Override
    public MaterialReceiveBO save(MaterialReceiveTO to) throws SerException {
        return materialReceiveSer.save(to);
    }

    /**
     * 根据id删除物资领用归还登记
     *
     * @param id 物资领用归还登记唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialReceiveSer.remove(id);
    }

    /**
     * 更新物资领用归还登记
     *
     * @param to 物资领用归还登记to
     * @throws SerException
     */
    @Override
    public void update(MaterialReceiveTO to) throws SerException {
        materialReceiveSer.update(to);
    }

    /**
     * 审核
     *
     * @param id 物资领用归还登记唯一标识
     * @param auditState 审核状态
     * @param auditOpinion 审核意见
     * @throws SerException
     */
    @Override
    public void audit(String id, AuditState auditState, String auditOpinion) throws SerException {
        materialReceiveSer.audit(id, auditState, auditOpinion);
    }

    /**
     * 领用完成
     *
     * @param to 物资领用归还登记to
     * @throws SerException
     */
    @Override
    public void receiveOver(MaterialReceiveTO to) throws SerException {
        materialReceiveSer.receiveOver(to);
    }

    /**
     * 物资归还
     *
     * @param to 物资归还to
     * @throws SerException
     */
    @Override
    public void materialReturn(MaterialReturnTO to) throws SerException {
        materialReceiveSer.materialReturn(to);
    }
}