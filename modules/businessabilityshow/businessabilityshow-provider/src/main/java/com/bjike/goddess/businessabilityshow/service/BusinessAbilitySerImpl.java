package com.bjike.goddess.businessabilityshow.service;

import com.bjike.goddess.businessabilityshow.bo.BACollectEchartBO;
import com.bjike.goddess.businessabilityshow.bo.BusinessAbilityBO;
import com.bjike.goddess.businessabilityshow.bo.BusinessAbilityCollectBO;
import com.bjike.goddess.businessabilityshow.excel.BusinessAbilityExport;
import com.bjike.goddess.businessabilityshow.to.BusinessAbilityTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businessabilityshow.dto.BusinessAbilityDTO;
import com.bjike.goddess.businessabilityshow.entity.BusinessAbility;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目合同基本信息业务实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-16 02:50 ]
 * @Description: [ 项目合同基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessabilityshowSerCache")
@Service
public class BusinessAbilitySerImpl extends ServiceImpl<BusinessAbility, BusinessAbilityDTO> implements BusinessAbilitySer {

    @Override
    public List<BusinessAbilityBO> list(BusinessAbilityDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByCis(dto), BusinessAbilityBO.class);
    }

    @Override
    public BusinessAbilityBO getOne(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), BusinessAbilityBO.class);
    }

    @Override
    public void add(BusinessAbilityTO to) throws SerException {
        BusinessAbility entity = BeanTransform.copyProperties(to, BusinessAbility.class);
        super.save(entity);
    }

    @Override
    public void update(BusinessAbilityTO to) throws SerException {
        BusinessAbility entity = BeanTransform.copyProperties(to, BusinessAbility.class);
        super.update(entity);
    }

    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void importExcel(List<BusinessAbilityTO> tos) throws SerException {

        List<BusinessAbility> entities = new ArrayList<>(tos.size());
        for (BusinessAbilityTO to : tos) {
            BusinessAbility contract = BeanTransform.copyProperties(to, BusinessAbility.class, true);
            entities.add(contract);
        }
        super.save(entities);
    }

    @Override
    public byte[] exportExcel(BusinessAbilityDTO dto) throws SerException {
        List<BusinessAbility> list = super.findByCis(dto);

        List<BusinessAbilityExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            BusinessAbilityExport export = BeanTransform.copyProperties(str, BusinessAbilityExport.class);
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    @Override
    public List<BusinessAbilityCollectBO> businessAbilityCollect() throws SerException {
        return null;
    }

    @Override
    public List<BACollectEchartBO> businessAbilityEchartCollect() throws SerException {
        return null;
    }

    @Override
    public List<BACollectEchartBO> businessProjectEchartcollect() throws SerException {
        return null;
    }
}