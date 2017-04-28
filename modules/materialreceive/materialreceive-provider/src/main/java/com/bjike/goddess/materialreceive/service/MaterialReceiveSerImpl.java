package com.bjike.goddess.materialreceive.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialreceive.bo.MaterialReceiveBO;
import com.bjike.goddess.materialreceive.dto.MaterialReceiveDTO;
import com.bjike.goddess.materialreceive.entity.MaterialReceive;
import com.bjike.goddess.materialreceive.to.MaterialReceiveTO;
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
    @Transactional
    public MaterialReceiveBO save(MaterialReceiveTO to) throws SerException {
        MaterialReceive entity = BeanTransform.copyProperties(to, MaterialReceive.class, true);
        entity.setAuditState(AuditState.UNAUDITED);
        entity = super.save(entity);
        MaterialReceiveBO bo = BeanTransform.copyProperties(entity, MaterialReceiveBO.class);
        return bo;
    }

    /**
     * 根据id删除物资领用
     *
     * @param id 物资领用唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
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
    @Transactional
    public void update(MaterialReceiveTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialReceive model = super.findById(to.getId());
            if (model != null) {
                updateMaterialReceive(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
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
     * @param to 物资领用to
     * @throws SerException
     */
    @Override
    @Transactional
    public void audit(MaterialReceiveTO to) throws SerException {
        String curUsername = userAPI.currentUser().getUsername();
        if (StringUtils.isNotBlank(to.getId())) {
            MaterialReceive model = super.findById(to.getId());
            boolean auditorIsNotEmpty = (model != null) && (StringUtils.isNotEmpty(model.getAuditor()));
            if (auditorIsNotEmpty && (model.getAuditor().equals(curUsername))) {
                update(to);
            } else {
                throw new SerException("审核人与当前用户不符,无法进行审核.");
            }
        } else {
            throw new SerException("更新id不能为空");
        }
    }

    /**
     * 领用完成
     *
     * @param to 物资领用to
     * @throws SerException
     */
    @Override
    @Transactional
    public void receiveOver(MaterialReceiveTO to) throws SerException {
        update(to);
    }

    /**
     * 物资归还
     *
     * @param to 物资领用to
     * @throws SerException
     */
    @Override
    @Transactional
    public void materialReturn(MaterialReceiveTO to) throws SerException {
        update(to);
    }
}