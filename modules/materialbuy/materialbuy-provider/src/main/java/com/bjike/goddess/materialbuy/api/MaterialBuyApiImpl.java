package com.bjike.goddess.materialbuy.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.materialbuy.entity.MaterialBuy;
import com.bjike.goddess.materialbuy.service.MaterialBuySer;
import com.bjike.goddess.materialbuy.to.MaterialBuyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 物资购买业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialBuyApiImpl")
public class MaterialBuyApiImpl implements MaterialBuyAPI {

    @Autowired
    private MaterialBuySer materialBuySer;

    /**
     * 根据id查询物资购买
     *
     * @param id 物资购买唯一标识
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    public MaterialBuyBO findById(String id) throws SerException {
        MaterialBuy model = materialBuySer.findById(id);
        return BeanTransform.copyProperties(model, MaterialBuyBO.class);
    }

    /**
     * 分页查询物资购买
     *
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    public List<MaterialBuyBO> list(MaterialBuyDTO dto) throws SerException {
        return materialBuySer.list(dto);
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
        return materialBuySer.save(to);
    }

    /**
     * 根据id删除物资购买
     *
     * @param id 物资购买唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialBuySer.remove(id);
    }

    /**
     * 更新物资购买
     *
     * @param to 物资购买to
     * @throws SerException
     */
    @Override
    public void update(MaterialBuyTO to) throws SerException {
        materialBuySer.update(to);
    }

    /**
     * 文件上传
     *
     * @param maps  文件名，byte 文件字节
     * @param path 上传路径
     */
    @Override
    public void upload(Map<String, byte[]> maps, String path) throws SerException {
        materialBuySer.upload(maps, path);
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
        return materialBuySer.checkDetail(id);
    }

    /**
     * 地区负责人审核
     *
     * @param to 物资购买to
     * @throws SerException
     */
    @Override
    public void areaPrincipalAudit(MaterialBuyTO to) throws SerException {
        materialBuySer.areaPrincipalAudit(to);
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
        return materialBuySer.allWaits();
    }
}