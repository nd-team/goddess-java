package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.bo.AccountBO;
import com.bjike.goddess.financeinit.bo.CompanyBasicInfoBO;
import com.bjike.goddess.financeinit.dto.CompanyBasicInfoDTO;
import com.bjike.goddess.financeinit.entity.CompanyBasicInfo;
import com.bjike.goddess.financeinit.excel.CompanyBasicInfoExport;
import com.bjike.goddess.financeinit.to.CompanyBasicInfoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司基本信息业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:08 ]
 * @Description: [ 公司基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class CompanyBasicInfoSerImpl extends ServiceImpl<CompanyBasicInfo, CompanyBasicInfoDTO> implements CompanyBasicInfoSer {
    @Override
    public Long countBasicInfo(CompanyBasicInfoDTO companyBasicInfoDTO) throws SerException {
        Long count = super.count(companyBasicInfoDTO);
        return count;
    }

    @Override
    public CompanyBasicInfoBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        CompanyBasicInfo companyBasicInfo = super.findById(id);
        return BeanTransform.copyProperties(companyBasicInfo, CompanyBasicInfoBO.class);
    }

    @Override
    public List<CompanyBasicInfoBO> listBaseInfo(CompanyBasicInfoDTO companyBasicInfoDTO) throws SerException {
        List<CompanyBasicInfo> list = super.findByCis(companyBasicInfoDTO, true);
        return BeanTransform.copyProperties(list, CompanyBasicInfoBO.class);
    }

    @Override
    public CompanyBasicInfoBO addBaseInfo(CompanyBasicInfoTO companyBasicInfoTO) throws SerException {
        CompanyBasicInfo companyBasicInfo = BeanTransform.copyProperties(companyBasicInfoTO,CompanyBasicInfo.class,true);
        companyBasicInfo.setCreateTime(LocalDateTime.now());
        super.save(companyBasicInfo);
        return BeanTransform.copyProperties(companyBasicInfo,CompanyBasicInfoBO.class);
    }

    @Override
    public CompanyBasicInfoBO editBaseInfo(CompanyBasicInfoTO companyBasicInfoTO) throws SerException {
        CompanyBasicInfo companyBasicInfo = super.findById(companyBasicInfoTO.getId());
        LocalDateTime date = companyBasicInfo.getCreateTime();
        companyBasicInfo = BeanTransform.copyProperties(companyBasicInfoTO,CompanyBasicInfo.class);
        companyBasicInfo.setCreateTime(date);
        companyBasicInfo.setModifyTime(LocalDateTime.now());
        super.update(companyBasicInfo);
        return BeanTransform.copyProperties(companyBasicInfo,CompanyBasicInfoBO.class);
    }

    @Override
    public void deleteBaseInfo(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        List<CompanyBasicInfo> list = super.findAll();
        List<CompanyBasicInfoExport> companyBasicInfoExports = new ArrayList<>();

        for (CompanyBasicInfo companyBasicInfo : list){
            CompanyBasicInfoExport excel = BeanTransform.copyProperties(companyBasicInfo, CompanyBasicInfoExport.class);
            companyBasicInfoExports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(companyBasicInfoExports, excel);
        return bytes;
    }

    @Override
    public List<String> findCompanyName() throws SerException {
        List<CompanyBasicInfo> companyBasicInfos = super.findAll();
        List<String> CompanyNames = new ArrayList<>();
        if(companyBasicInfos!=null && companyBasicInfos.size()>0){
            for (CompanyBasicInfo companyBasicInfo : companyBasicInfos){
                CompanyNames.add(companyBasicInfo.getCompanyName());
            }
        }
        return CompanyNames;
    }

    @Override
    public CompanyBasicInfoBO findByCompanyName(String companyName) throws SerException {
        CompanyBasicInfoDTO companyBasicInfoDTO = new CompanyBasicInfoDTO();
        companyBasicInfoDTO.getConditions().add(Restrict.eq("companyName",companyName));
        CompanyBasicInfo companyBasicInfo = super.findOne(companyBasicInfoDTO);
        return BeanTransform.copyProperties(companyBasicInfo,CompanyBasicInfoBO.class);
    }
}