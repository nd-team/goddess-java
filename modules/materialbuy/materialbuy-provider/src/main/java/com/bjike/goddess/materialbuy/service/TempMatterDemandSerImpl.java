package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.bo.TempMatterDemandBO;
import com.bjike.goddess.materialbuy.dto.TempMatterDemandDTO;
import com.bjike.goddess.materialbuy.entity.MaterialBuy;
import com.bjike.goddess.materialbuy.entity.TempMatterDemand;
import com.bjike.goddess.materialbuy.to.TempMatterDemandTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 临时物资需求业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:08 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialbuySerCache")
@Service
public class TempMatterDemandSerImpl extends ServiceImpl<TempMatterDemand, TempMatterDemandDTO> implements TempMatterDemandSer {

    /**
     * 分页查询临时物资需求
     *
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    @Override
    public List<TempMatterDemandBO> list(TempMatterDemandDTO dto) throws SerException {
        List<TempMatterDemand> list = super.findByPage(dto);
        List<TempMatterDemandBO> listBO = BeanTransform.copyProperties(list, TempMatterDemandBO.class);
        return listBO;
    }

    /**
     * 保存临时物资需求
     *
     * @param to 临时物资需求to
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    @Override
    public TempMatterDemandBO save(TempMatterDemandTO to) throws SerException {
        TempMatterDemand entity = BeanTransform.copyProperties(to, TempMatterDemand.class, true);
        entity = super.save(entity);
        TempMatterDemandBO bo = BeanTransform.copyProperties(entity, TempMatterDemandBO.class);
        return bo;
    }

    /**
     * 根据id删除临时物资需求
     *
     * @param id 临时物资需求唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新临时物资需求
     *
     * @param to 临时物资需求to
     * @throws SerException
     */
    @Override
    public void update(TempMatterDemandTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            TempMatterDemand model = super.findById(to.getId());
            if (model != null) {
                updateTempMatterDemand(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新临时物资需求
     *
     * @param to 临时物资需求to
     * @param model 临时物资需求
     * @throws SerException
     */
    private void updateTempMatterDemand(TempMatterDemandTO to, TempMatterDemand model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 审核
     *
     * @param to 临时物资需求to
     * @throws SerException
     */
    @Override
    public void audit(TempMatterDemandTO to) throws SerException {
        update(to);
    }

    /**
     * 查看详情
     *
     * @param id 临时物资需求唯一标识
     * @return class TempMatterDemandBO
     * @throws SerException
     */
    @Override
    public TempMatterDemandBO checkDetail(String id) throws SerException {
        TempMatterDemand model = super.findById(id);
        return BeanTransform.copyProperties(model, TempMatterDemandBO.class);
    }
}