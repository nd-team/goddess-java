package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.materialbuy.entity.MaterialBuy;
import com.bjike.goddess.materialbuy.to.MaterialBuyTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 物资购买业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialbuySerCache")
@Service
public class MaterialBuySerImpl extends ServiceImpl<MaterialBuy, MaterialBuyDTO> implements MaterialBuySer {

    /**
     * 分页查询物资购买
     *
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    public List<MaterialBuyBO> list(MaterialBuyDTO dto) throws SerException {
        List<MaterialBuy> list = super.findByPage(dto);
        List<MaterialBuyBO> listBO = BeanTransform.copyProperties(list, MaterialBuyBO.class);
        return listBO;
    }

    /**
     * 保存物资购买
     *
     * @param to 物资购买to
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    public MaterialBuyBO save(MaterialBuyTO to) throws SerException {
        MaterialBuy entity = BeanTransform.copyProperties(to, MaterialBuy.class, true);
        entity = super.save(entity);
        MaterialBuyBO bo = BeanTransform.copyProperties(entity, MaterialBuyBO.class);
        return bo;
    }

    /**
     * 根据id删除物资购买
     *
     * @param id 物资购买唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新物资购买
     *
     * @param to 物资购买to
     * @throws SerException
     */
    @Override
    public void update(MaterialBuyTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialBuy model = super.findById(to.getId());
            if (model != null) {
                updateMaterialBuy(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资购买
     *
     * @param to    物资购买to
     * @param model 物资购买
     * @throws SerException
     */
    private void updateMaterialBuy(MaterialBuyTO to, MaterialBuy model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 文件上传
     *
     * @param maps 文件名，byte 文件字节
     * @param path 上传路径
     */
    @Override
    public void upload(Map<String, byte[]> maps, String path) throws SerException {
        // TODO: 17-4-20
    }

    /**
     * 查看详情
     *
     * @param id 物资购买唯一标识
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    public MaterialBuyBO checkDetail(String id) throws SerException {
        MaterialBuy model = super.findById(id);
        return BeanTransform.copyProperties(model, MaterialBuyBO.class);
    }

    /**
     * 地区负责人审核
     *
     * @param to 物资购买to
     * @throws SerException
     */
    @Override
    public void areaPrincipalAudit(MaterialBuyTO to) throws SerException {
        update(to);
    }

    /**
     * chenjunhao
     * 查找所有未付款的信息
     *
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    public List<MaterialBuyBO> allWaits() throws SerException {
        List<MaterialBuy> list = super.findAll();
        List<MaterialBuy> l = new ArrayList<MaterialBuy>();
        for (MaterialBuy m : list) {
            if (!m.getIfPayment()) {
                l.add(m);
            }
        }
        return BeanTransform.copyProperties(l, MaterialBuyBO.class);
    }
}