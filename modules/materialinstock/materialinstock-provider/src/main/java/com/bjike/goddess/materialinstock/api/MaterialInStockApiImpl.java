package com.bjike.goddess.materialinstock.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.entity.MaterialInStock;
import com.bjike.goddess.materialinstock.service.MaterialInStockSer;
import com.bjike.goddess.materialinstock.to.MaterialInStockTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 物资入库业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 04:55 ]
 * @Description: [ 物资入库业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialInStockApiImpl")
public class MaterialInStockApiImpl implements MaterialInStockAPI {

    @Autowired
    private MaterialInStockSer materialInStockSer;

    /**
     * 根据id查询物资入库
     *
     * @param id 物资入库唯一标识
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    public MaterialInStockBO findById(String id) throws SerException {
        MaterialInStock model = materialInStockSer.findById(id);
        return BeanTransform.copyProperties(model,MaterialInStockBO.class);
    }

    /**
     * 分页查询物资入库
     *
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    public List<MaterialInStockBO> list(MaterialInStockDTO dto) throws SerException {
        return materialInStockSer.list(dto);
    }

    /**
     * 保存物资入库
     *
     * @param to 物资入库to
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    public MaterialInStockBO save(MaterialInStockTO to) throws SerException {
        return materialInStockSer.save(to);
    }

    /**
     * 根据id删除物资入库
     *
     * @param id 物资入库唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialInStockSer.remove(id);
    }

    /**
     * 更新物资入库
     *
     * @param to 物资入库to
     * @throws SerException
     */
    @Override
    public void update(MaterialInStockTO to) throws SerException {
        materialInStockSer.update(to);
    }

    /**
     * 文件上传
     *
     * @param maps  文件名，byte 文件字节
     * @param path 上传路径
     */
    @Override
    public void upload(Map<String, byte[]> maps, String path) throws SerException {
        materialInStockSer.upload(maps, path);
    }
}