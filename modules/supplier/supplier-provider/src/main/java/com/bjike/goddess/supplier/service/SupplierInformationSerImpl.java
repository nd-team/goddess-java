package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.EnterpriseQualificationBO;
import com.bjike.goddess.supplier.bo.SupplierInfoCollectBO;
import com.bjike.goddess.supplier.bo.SupplierInfoCollectTitleBO;
import com.bjike.goddess.supplier.bo.SupplierInformationBO;
import com.bjike.goddess.supplier.dto.SupplierInformationDTO;
import com.bjike.goddess.supplier.entity.SupplierInformation;
import com.bjike.goddess.supplier.to.SupplierInformationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private SupPermissionSer supPermissionSer;

    private static final String idFlag = "supplier-01";

    private static final String format = "%s供应商数量";

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierInformationBO save(SupplierInformationTO to) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
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
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                SupplierInformation entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setExecution(this.countExecution(entity));
                entity.setId(to.getId());
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierInformationBO updateDetail(SupplierInformationTO to) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        return this.update(to);
    }

    @Override
    public List<SupplierInformationBO> findOrderName() throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        SupplierInformationDTO dto = new SupplierInformationDTO();
        dto.getSorts().add("name");
        List<SupplierInformation> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, SupplierInformationBO.class);
    }

    @Override
    public SupplierInformationBO delete(String id) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        SupplierInformation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        String message = "存在依赖关系,无法删除";
        if (contactSituationSer.findByInformation(id).size() > 0)
            throw new SerException(message);
        if (cooperationSituationSer.findByInformation(id).size() > 0)
            throw new SerException(message);
        if (enterpriseQualificationSer.findByInformation(id).size() > 0)
            throw new SerException(message);
        if (rewardSituationSer.findByInformation(id).size() > 0)
            throw new SerException(message);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
    }

    @Override
    public List<SupplierInformationBO> maps(SupplierInformationDTO dto) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        dto.getSorts().add("serialNumber=desc");
        List<SupplierInformation> list = super.findByPage(dto);
        if (null != list && list.size() > 0)
            return BeanTransform.copyProperties(list, SupplierInformationBO.class);
        else
            return new ArrayList<SupplierInformationBO>(0);
    }

    @Override
    public SupplierInformationBO getById(String id) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        SupplierInformation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
    }

    @Override
    public void changeEnclosure(String id) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        SupplierInformation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        entity.isEnclosure(Boolean.TRUE);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<SupplierInfoCollectBO> collect(String... area) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        List<SupplierInfoCollectBO> collectBOs = new ArrayList<>(0);
        SupplierInformationDTO dto = new SupplierInformationDTO();
        if (area != null && area.length != 0) {
            dto.getConditions().add(Restrict.in("area", area));
        }
        dto.getSorts().add("area=asc");
        List<SupplierInformation> list = super.findByCis(dto);
        List<EnterpriseQualificationBO> qualificationBOs = new ArrayList<>(0);
        List<SupplierInfoCollectTitleBO> totalList = new ArrayList<>(0);
        List<String> typeList = new ArrayList<>(0), qualificationList = new ArrayList<>(0);
        String tempArea = "", qualification = "";
        for (SupplierInformation entity : list) {
            qualificationBOs.addAll(enterpriseQualificationSer.findByInformation(entity.getId()));
            if (typeList.stream().filter(s -> s.equals(entity.getType())).count() == 0)
                typeList.add(entity.getType());
            if (!tempArea.equals(entity.getArea())) {
                SupplierInfoCollectBO bo = new SupplierInfoCollectBO();
                bo.setArea(entity.getArea());
                bo.setTitleBOs(new HashSet<>(0));
                collectBOs.add(bo);
            }
        }
        for (EnterpriseQualificationBO bo : qualificationBOs.stream()
                .sorted(Comparator.comparing(EnterpriseQualificationBO::getAptitude))
                .collect(Collectors.toList())) {
            if (!qualification.equals(bo.getAptitude()))
                qualificationList.add(bo.getAptitude());
        }
        for (SupplierInfoCollectBO bo : collectBOs) {
            this.countCollect(bo,
                    enterpriseQualificationSer.findByInformationIds(list.stream()
                            .filter(s -> s.getArea().equals(bo.getArea()))
                            .map(SupplierInformation::getId).collect(Collectors.toList()).toArray(new String[0])),
                    list.stream().filter(s -> s.getArea().equals(bo.getArea())).collect(Collectors.toList()),
                    typeList, qualificationList);
            totalList.addAll(bo.getTitleBOs());
        }
        SupplierInfoCollectBO bo = new SupplierInfoCollectBO();
        bo.setArea("合计");
        bo.setTitleBOs(new HashSet<>(0));
        this.countCollect(bo, qualificationBOs, list, typeList, qualificationList);
        collectBOs.add(bo);
        return collectBOs;
    }

    private void countCollect(SupplierInfoCollectBO bo, List<EnterpriseQualificationBO> count, List<SupplierInformation> list,
                              List<String> typeList, List<String> qualificationList) throws SerException {
        for (String type : typeList) {
            Long number = list.stream().filter(s -> s.getType().equals(type)).count();
            bo.getTitleBOs().add(new SupplierInfoCollectTitleBO(number.intValue(), String.format(format, type)));
        }
        for (String q : qualificationList) {
            Long number = count.stream().filter(e -> e.getAptitude().equals(q)).count();
            bo.getTitleBOs().add(new SupplierInfoCollectTitleBO(number.intValue(), String.format(format, q)));
        }
    }
}