package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.bo.WithUnitBO;
import com.bjike.goddess.financeinit.dto.WithUnitDTO;
import com.bjike.goddess.financeinit.entity.WithUnit;
import com.bjike.goddess.financeinit.excel.WithUnitExport;
import com.bjike.goddess.financeinit.to.WithUnitTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 往来单位业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:28 ]
 * @Description: [ 往来单位业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class WithUnitSerImpl extends ServiceImpl<WithUnit, WithUnitDTO> implements WithUnitSer {
    @Override
    public Long countWith(WithUnitDTO withUnitDTO) throws SerException {
        Long count = super.count(withUnitDTO);
        return count;
    }

    @Override
    public WithUnitBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        WithUnit withUnit = super.findById(id);
        return BeanTransform.copyProperties(withUnit, WithUnitBO.class);
    }

    @Override
    public List<WithUnitBO> listWith(WithUnitDTO withUnitDTO) throws SerException {
        List<WithUnit> list = super.findByCis(withUnitDTO, true);
        return BeanTransform.copyProperties(list, WithUnitBO.class);
    }

    @Override
    public WithUnitBO addWith(WithUnitTO withUnitTO) throws SerException {
        WithUnit withUnit = BeanTransform.copyProperties(withUnitTO, WithUnit.class, true);
        withUnit.setCreateTime(LocalDateTime.now());
        super.save(withUnit);
        return BeanTransform.copyProperties(withUnit, WithUnitBO.class);
    }

    @Override
    public WithUnitBO editWith(WithUnitTO withUnitTO) throws SerException {
        WithUnit withUnit = super.findById(withUnitTO.getId());
        LocalDateTime date = withUnit.getCreateTime();
        withUnit = BeanTransform.copyProperties(withUnitTO, WithUnit.class);
        withUnit.setCreateTime(date);
        withUnit.setModifyTime(LocalDateTime.now());
        super.update(withUnit);
        return BeanTransform.copyProperties(withUnit, WithUnitBO.class);
    }

    @Override
    public void deleteWith(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        List<WithUnit> list = super.findAll();
        List<WithUnitExport> withUnitExports = new ArrayList<>();

        for (WithUnit withUnit : list){
            WithUnitExport excel = BeanTransform.copyProperties(withUnit, WithUnitExport.class);
            withUnitExports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(withUnitExports, excel);
        return bytes;
    }
}