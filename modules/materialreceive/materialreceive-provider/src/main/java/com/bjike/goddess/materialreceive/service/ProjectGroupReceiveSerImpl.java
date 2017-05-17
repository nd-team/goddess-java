package com.bjike.goddess.materialreceive.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.entity.MaterialInStock;
import com.bjike.goddess.materialinstock.service.MaterialInStockSer;
import com.bjike.goddess.materialinstock.type.UseState;
import com.bjike.goddess.materialreceive.bo.ProjectGroupReceiveBO;
import com.bjike.goddess.materialreceive.dto.ProjectGroupReceiveDTO;
import com.bjike.goddess.materialreceive.entity.ProjectGroupReceive;
import com.bjike.goddess.materialreceive.to.ProjectGroupReceiveTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目组领用归还业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:52 ]
 * @Description: [ 项目组领用归还业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialreceiveSerCache")
@Service
public class ProjectGroupReceiveSerImpl extends ServiceImpl<ProjectGroupReceive, ProjectGroupReceiveDTO> implements ProjectGroupReceiveSer {

    @Autowired
    private MaterialInStockSer materialInStockSer;

    /**
     * 分页查询项目组领用归还
     *
     * @return class ProjectGroupReceiveBO
     * @throws SerException
     */
    @Override
    public List<ProjectGroupReceiveBO> list(ProjectGroupReceiveDTO dto) throws SerException {
        List<ProjectGroupReceive> list = super.findByPage(dto);
        List<ProjectGroupReceiveBO> listBO = BeanTransform.copyProperties(list, ProjectGroupReceiveBO.class);
        return listBO;
    }

    /**
     * 保存项目组领用归还
     *
     * @param to 项目组领用归还to
     * @return class ProjectGroupReceiveBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectGroupReceiveBO save(ProjectGroupReceiveTO to) throws SerException {
        String[] materialNum = checkMaterialNum(to);//检查待领用的物资编号不能为空
        materialInStockSer.updateUseState(materialNum, UseState.RECEIVE);//设置物资领用情况为已领用
        Integer quantity = materialNum.length;
        StringBuilder materialNoSb = getMaterialNoSb(materialNum);
        ProjectGroupReceiveBO bo = saveModel(to, quantity, materialNoSb);
        return bo;
    }

    /**
     * 校验领用物资是否为空
     *
     * @param to
     * @return
     */
    private String[] checkMaterialNum(ProjectGroupReceiveTO to) throws SerException {
        String[] materialNum = to.getMaterialNum();
        Boolean materialNumNotEmpty = (materialNum != null) && (materialNum.length > 0);
        if (!materialNumNotEmpty)
            throw new SerException("物资入库编码不能为空,无法领用");

        return materialNum;
    }


    /**
     * 更新物资使用状态
     *
     * @param materialNum 物资编号
     */
    private void updateUseState(String[] materialNum) throws SerException {
        MaterialInStockDTO dto = new MaterialInStockDTO();
        dto.getConditions().add(Restrict.in("stockEncoding", materialNum));
        List<MaterialInStock> list = materialInStockSer.findByCis(dto);
        for (MaterialInStock model : list) {
            model.setUseState(UseState.RECEIVE);
        }

        materialInStockSer.update(list);
    }


    /**
     * 保存对象
     *
     * @param to           项目组领用归还to
     * @param quantity     领用物品数量
     * @param materialNoSb 领用物品编号
     * @return
     * @throws SerException
     */
    private ProjectGroupReceiveBO saveModel(ProjectGroupReceiveTO to, Integer quantity, StringBuilder materialNoSb) throws SerException {
        ProjectGroupReceive entity = BeanTransform.copyProperties(to, ProjectGroupReceive.class, true);
        entity = super.save(entity);
        entity.setQuantity(quantity);
        entity.setMaterialNo(materialNoSb.toString());
        return BeanTransform.copyProperties(entity, ProjectGroupReceiveBO.class);
    }

    /**
     * 获取物资编号
     *
     * @param materialNum 物资编号数组
     * @return
     */
    private StringBuilder getMaterialNoSb(String[] materialNum) {
        StringBuilder materialNoSb = new StringBuilder();
        for (int i = 0; i < materialNum.length; i++) {
            if (i < (materialNum.length - 1)) {
                materialNoSb.append(materialNum[i]).append(",");
            } else {
                materialNoSb.append(materialNum[i]);
            }
        }
        return materialNoSb;
    }

    /**
     * 根据id删除项目组领用归还
     *
     * @param id 项目组领用归还唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 归还物资
     *
     * @param to 项目组领用归还to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void returnMaterial(ProjectGroupReceiveTO to) throws SerException {
        ProjectGroupReceive model = update(to);//更新项目组领用归还
        instockMaterials(model);               //归还物资
    }

    /**
     * 归还物资
     *
     * @param model
     * @throws SerException
     */
    private void instockMaterials(ProjectGroupReceive model) throws SerException {
        String materialNo = model.getMaterialNo();//获取物资编号
        String[] materialNum = materialNo.split(",");
        materialInStockSer.updateUseState(materialNum, UseState.INSTOCK);
    }

    /**
     * 更新更新项目组领用归还
     *
     * @param to 项目组领用归还to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectGroupReceive update(ProjectGroupReceiveTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            ProjectGroupReceive model = super.findById(to.getId());
            if (model != null) {
                updateProjectGroupReceive(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
            return model;
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新项目组领用归还
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateProjectGroupReceive(ProjectGroupReceiveTO to, ProjectGroupReceive model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

}