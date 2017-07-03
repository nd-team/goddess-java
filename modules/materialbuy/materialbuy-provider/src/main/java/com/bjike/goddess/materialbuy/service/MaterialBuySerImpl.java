package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.bo.AreaBuyStatusDayCollectBO;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.materialbuy.entity.DeviceType;
import com.bjike.goddess.materialbuy.entity.MaterialBuy;
import com.bjike.goddess.materialbuy.to.MaterialBuyTO;
import com.bjike.goddess.materialbuy.vo.MaterialBuyVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.OpinionBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    private DeviceTypeSer deviceTypeSer;

    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

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
    @Transactional(rollbackFor = SerException.class)
    public MaterialBuyBO save(MaterialBuyTO to) throws SerException {
        MaterialBuy entity = BeanTransform.copyProperties(to, MaterialBuy.class, true);
        entity.setIfPayment(Boolean.FALSE);
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
    @Transactional(rollbackFor = SerException.class)
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
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialBuyTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
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
     * @param to 物资购买to
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
    @Transactional(rollbackFor = SerException.class)
    public void areaPrincipalAudit(MaterialBuyTO to) throws SerException {
        update(to);
    }

    /**
     * 查询等待付款
     *
     * @param dto 物资购买dto
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    public List<MaterialBuyBO> findWaitPay(MaterialBuyDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("ifPayment", Boolean.TRUE));
        List<MaterialBuy> list = super.findByCis(dto);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        List<MaterialBuyBO> listBO = BeanTransform.copyProperties(list, MaterialBuyBO.class);
        return listBO;
    }

    /**
     * 地区购买情况汇总
     *
     * @return class AreaBuyStatusDayCollectBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusDayCollectBO> areaBuyStatusDaySum() throws SerException {
        //查询所有部门
        List<OpinionBO> listBO = departmentDetailAPI.findAllOpinion();
        if (CollectionUtils.isEmpty(listBO)) {
            return null;
        }
        List<String> listDepartments = new ArrayList<>(0);
        for (OpinionBO opinionBO : listBO) {
            if (StringUtils.isNotBlank(opinionBO.getValue())) {
                listDepartments.add(opinionBO.getValue());
            }
        }
        List<String> listDeviceTypes = deviceTypeSer.findAllDeviceNames();
        List<AreaBuyStatusDayCollectBO> areaCollectBO = new ArrayList<>(0);
        for (String department : listDepartments) {
            for (String deviceType : listDeviceTypes) {
                MaterialBuyDTO dto = new MaterialBuyDTO();
                dto.getConditions().add(Restrict.eq("subscribeDate", LocalDate.now()));
                dto.getConditions().add(Restrict.eq("projectTeam", department));
                dto.getConditions().add(Restrict.eq("deviceType", deviceType));
                dto.getSorts().add("subscribeDate=desc");
                List<MaterialBuy> list = super.findByCis(dto);
                String area = null;
                Integer totalQty = 0;//总数量
                Double totalAmount = 0d;//总金额
                for (MaterialBuy materialBuy : list) {
                    Integer quantity = materialBuy.getQuantity() == null?0:(materialBuy.getQuantity());
                    Double unitPrice = materialBuy.getUnitPrice() == null?0:(materialBuy.getUnitPrice());
                    Double amount = quantity * unitPrice;
                    totalQty += quantity;
                    totalAmount += amount;
                }

            }

        }

        return null;
    }
}