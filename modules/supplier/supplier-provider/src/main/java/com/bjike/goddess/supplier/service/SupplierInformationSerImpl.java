package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.SupplierInformationBO;
import com.bjike.goddess.supplier.dto.SupplierInformationDTO;
import com.bjike.goddess.supplier.entity.SupplierInformation;
import com.bjike.goddess.supplier.to.SupplierInformationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 供应商基本信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.062 ]
 * @Description: [ 供应商基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class SupplierInformationSerImpl extends ServiceImpl<SupplierInformation, SupplierInformationDTO> implements SupplierInformationSer {

    @Autowired
    private ContactSituationSer contactSituationSer;
    @Autowired
    private CooperationSituationSer cooperationSituationSer;
    @Autowired
    private RewardSituationSer rewardSituationSer;
    @Autowired
    private EnterpriseQualificationSer enterpriseQualificationSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierInformationBO save(SupplierInformationTO to) throws SerException {
        SupplierInformation entity = BeanTransform.copyProperties(to, SupplierInformation.class);
        entity.setExecution(this.countExecution(entity));
        entity.setSerialNumber(this.createNumber());
        super.save(entity);
        return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
    }

    /**
     * 创建供应商编号
     *
     * @return
     * @throws SerException
     */
    private String createNumber() throws SerException {
        StringBuilder number = new StringBuilder("QS-");
        Integer size = super.findAll().size();
        char[] chars = size.toString().toCharArray();
        for (int i = 6, lent = chars.length; i >= 0; i--) {
            if (i >= lent)
                number.append("0");
            else
                number.append(String.valueOf(chars[i]));
        }
        return number.toString();
    }

    /**
     * 计算供应商信息完成度
     */
    private Double countExecution(SupplierInformation entity) {
        entity.setExecution(0d);
        entity.setId("");
        entity.setCreateTime(LocalDateTime.now());
        entity.setModifyTime(LocalDateTime.now());
        double sum = 0d;
        Class aClass = SupplierInformation.class;
        Method[] methods = aClass.getMethods();
        Set<Object> objects = new HashSet<>(0);
        for (Method method : methods)
            if (method.getName().substring(0, 3).equals("get")) {
                try {
                    objects.add(method.invoke(entity, null));
                    sum++;
                } catch (Exception e) {
                }
            }
        entity.setId(null);
        //供应商基本信息实体中有5个字段不纳入计算当中分别是id,创建时间,修改时间,编号和完成度;
        return new BigDecimal((objects.size() - 5) / (sum - 5)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierInformationBO update(SupplierInformationTO to) throws SerException {
        SupplierInformation entity = super.findById(to.getId());
        String id = entity.getId();
        LocalDateTime createTime = entity.getCreateTime();
        entity.setName(to.getName());
        entity.setArea(to.getArea());
        entity.setType(to.getType());
        entity.setScope(to.getScope());
        entity.setProduct(to.getProduct());
        entity.setContacts(to.getContacts());
        entity.setDuties(to.getDuties());
        entity.setTelephone(to.getTelephone());
        entity.setEmail(to.getEmail());
        entity.setFax(to.getFax());
        entity.setExecution(this.countExecution(entity));
        entity.setCreateTime(createTime);
        entity.setId(id);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierInformationBO updateDetail(SupplierInformationTO to) throws SerException {
        SupplierInformation entity = BeanTransform.copyProperties(to, SupplierInformation.class), information = super.findById(to.getId());
        entity.setSerialNumber(information.getSerialNumber());
        entity.setExecution(this.countExecution(entity));
        entity.setId(information.getId());
        entity.setCreateTime(information.getCreateTime());
        super.update(entity);
        return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
    }

    @Override
    public List<SupplierInformationBO> findOrderName() throws SerException {
        SupplierInformationDTO dto = new SupplierInformationDTO();
        dto.getSorts().add("name");
        List<SupplierInformation> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, SupplierInformationBO.class);
    }
}