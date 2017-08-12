package com.bjike.goddess.system.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.system.bo.PlatformClassifyBO;
import com.bjike.goddess.system.dto.PlatformClassifyDTO;
import com.bjike.goddess.system.entity.PlatformClassify;
import com.bjike.goddess.system.excel.PlatformClassifyExport;
import com.bjike.goddess.system.excel.PlatformClassifyTemplateExcel;
import com.bjike.goddess.system.to.PlatformClassifyTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 平台分类业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 01:44 ]
 * @Description: [ 平台分类业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "systemSerCache")
@Service
public class PlatformClassifySerImpl extends ServiceImpl<PlatformClassify, PlatformClassifyDTO> implements PlatformClassifySer {
    @Override
    public Long count(PlatformClassifyDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public PlatformClassifyBO getOne(String id) throws SerException {
        PlatformClassify platformClassify = super.findById(id);
        return BeanTransform.copyProperties(platformClassify, PlatformClassifyBO.class);
    }

    @Override
    public List<PlatformClassifyBO> list(PlatformClassifyDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<PlatformClassify> platformClassifies = super.findByCis(dto);
        List<PlatformClassifyBO> platformClassifyBOS = BeanTransform.copyProperties(platformClassifies, PlatformClassifyBO.class);
        return platformClassifyBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PlatformClassifyBO insert(PlatformClassifyTO to) throws SerException {
        PlatformClassify entity = BeanTransform.copyProperties(to, PlatformClassify.class, true);
        entity.setCreateTime(LocalDateTime.now());
        verify(to);
        super.save(entity);
        return BeanTransform.copyProperties(entity, PlatformClassifyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PlatformClassifyBO edit(PlatformClassifyTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            PlatformClassify entity = super.findById(to.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            verify(to);
            super.update(entity);
            return BeanTransform.copyProperties(entity, PlatformClassifyBO.class);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public PlatformClassifyBO importExcel(List<PlatformClassifyTO> platformClassifyTOS) throws SerException {
        List<PlatformClassify> platformClassifies = new ArrayList<>(platformClassifyTOS.size());
        for (PlatformClassifyTO to : platformClassifyTOS) {
            PlatformClassify platformClassify = BeanTransform.copyProperties(to, PlatformClassify.class, true);
            platformClassifies.add(platformClassify);
        }
        super.save(platformClassifies);
        PlatformClassifyBO platformClassifyBOS = BeanTransform.copyProperties(new PlatformClassify(), PlatformClassifyBO.class);
        return platformClassifyBOS;
    }

    @Override
    public byte[] exportExcel(PlatformClassifyDTO dto) throws SerException {
        if (null != dto.getPlatformName()) {
            dto.getConditions().add(Restrict.in("platformName", dto.getPlatformName()));
        }
        List<PlatformClassify> list = super.findByCis(dto);
        List<PlatformClassifyExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            PlatformClassifyExport export = BeanTransform.copyProperties(str, PlatformClassifyExport.class);
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<PlatformClassifyTemplateExcel> templateExcels = new ArrayList<>();
        PlatformClassifyTemplateExcel templateExcel = new PlatformClassifyTemplateExcel();
        templateExcel.setPlatformName("test");
        templateExcel.setPlatformLocalize("test");
        templateExcel.setPlatformPurpose("test");
        templateExcel.setPlatformFeatures("test");
        templateExcel.setPlatformDemand("test");
        templateExcel.setContainsFunction("test");
        templateExcel.setUpdateTime(LocalDate.now());
        templateExcel.setProjectNum("test");
        templateExcel.setProjectName("test");
        templateExcel.setNewNodeUpdateTime(LocalDate.now());
        templateExcel.setNewNodeNum("test");
        templateExcel.setNewNode("test");
        templateExcel.setFieldUpdateTime(LocalDate.now());
        templateExcel.setNewFieldNum("test");
        templateExcel.setNewField("test");
        templateExcel.setPlatformInternalDemand("test");
        templateExcel.setInternalData(0);
        templateExcel.setPlatformExternalDemand("test");
        templateExcel.setExternalData(0);
        templateExcel.setRegisterDemand("test");
        templateExcel.setRegisterData(0);
        templateExcel.setUnRegisterDemand("test");
        templateExcel.setUnRegisterData(0);
        templateExcel.setPlatformLink("test");
        templateExcel.setRemark("test");
        templateExcels.add(templateExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels, excel);
        return bytes;
    }

    private PlatformClassifyBO verify(PlatformClassifyTO to) throws SerException {
        PlatformClassify entity = BeanTransform.copyProperties(to, PlatformClassify.class, true);
        if (null == to.getInternalData() || to.getInternalData().intValue() == 1) {
            entity.setInternalData(to.getInternalData());
        } else {
            throw new SerException("这里只能填1或者不填哦");
        }
        if (null == to.getExternalData() || to.getExternalData().intValue() == 1) {
            entity.setExternalData(to.getExternalData());
        } else {
            throw new SerException("这里只能填1或者不填哦");
        }
        if (null == to.getRegisterData() || to.getRegisterData().intValue() == 1) {
            entity.setRegisterData(to.getRegisterData());
        } else {
            throw new SerException("这里只能填1或者不填哦");
        }
        if (null == to.getUnRegisterData() || to.getUnRegisterData().intValue() == 1) {
            entity.setUnRegisterData(to.getUnRegisterData());
        } else {
            throw new SerException("这里只能填1或者不填哦");
        }
        return BeanTransform.copyProperties(entity, PlatformClassifyBO.class);
    }
}