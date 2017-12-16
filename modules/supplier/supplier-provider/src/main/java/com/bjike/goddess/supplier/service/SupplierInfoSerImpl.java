package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.supplier.bo.SupplierInfoBO;
import com.bjike.goddess.supplier.dto.SupplierInfoDTO;
import com.bjike.goddess.supplier.entity.SupplierInfo;
import com.bjike.goddess.supplier.excel.SupplierInfoImport;
import com.bjike.goddess.supplier.excel.SupplierInfoImportTemple;
import com.bjike.goddess.supplier.to.SupplierInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 供应商信息管理业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class SupplierInfoSerImpl extends ServiceImpl<SupplierInfo, SupplierInfoDTO> implements SupplierInfoSer {

    @Override
    public Long countSupplierInfo(SupplierInfoDTO supplierInfoDTO) throws SerException {
        seachCondi(supplierInfoDTO);
        Long count = super.count(supplierInfoDTO);
        return count;
    }

    @Override
    public SupplierInfoBO getOneById(String id) throws SerException {
        SupplierInfo supplierInfo = super.findById(id);
        return BeanTransform.copyProperties(supplierInfo,SupplierInfoBO.class);
    }

    @Override
    public List<SupplierInfoBO> listSupplierInfo(SupplierInfoDTO supplierInfoDTO) throws SerException {
        seachCondi(supplierInfoDTO);
        List<SupplierInfo> supplierInfoList = super.findByCis(supplierInfoDTO,true);
        return BeanTransform.copyProperties(supplierInfoList,SupplierInfoBO.class);
    }

    private void seachCondi(SupplierInfoDTO supplierInfoDTO) throws SerException{
        if(StringUtils.isNotBlank(supplierInfoDTO.getSupplierName())){
            supplierInfoDTO.getConditions().add(Restrict.eq("supplierName",supplierInfoDTO.getSupplierName()));
        }
    }

    @Override
    public SupplierInfoBO addSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        SupplierInfo supplierInfo = BeanTransform.copyProperties(supplierInfoTO,SupplierInfo.class,true);
        supplierInfo.setCreateTime(LocalDateTime.now());
        super.save(supplierInfo);
        return BeanTransform.copyProperties(supplierInfo,SupplierInfoBO.class);
    }

    @Override
    public SupplierInfoBO editSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        SupplierInfo supplierInfo = super.findById(supplierInfoTO.getId());
        LocalDateTime dateTime = supplierInfo.getCreateTime();
        supplierInfo = BeanTransform.copyProperties(supplierInfoTO,SupplierInfo.class,true);
        supplierInfo.setModifyTime(LocalDateTime.now());
        supplierInfo.setCreateTime(dateTime);
        super.update(supplierInfo);
        return BeanTransform.copyProperties(supplierInfo,SupplierInfoBO.class);
    }

    @Override
    public void deleteSupplierInfo(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        List<SupplierInfo> list = super.findAll();
        List<SupplierInfoImport> supplierInfoImports = new ArrayList<>();
        for (SupplierInfo supplierInfo : list){
            SupplierInfoImport excel = BeanTransform.copyProperties(supplierInfo, SupplierInfoImport.class,
                    "uploadBusinLicense","uploadQualifition","deterCooper","payComplete","infoPerfecting");
            excel.setUploadBusinLicense(boolToString(supplierInfo.getUploadBusinLicense()));
            excel.setUploadQualifition(boolToString(supplierInfo.getUploadQualifition()));
            excel.setDeterCooper(boolToString(supplierInfo.getDeterCooper()));
            excel.setPayComplete(boolToString(supplierInfo.getPayComplete()));
            excel.setInfoPerfecting(boolToString(supplierInfo.getInfoPerfecting()));
            supplierInfoImports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(supplierInfoImports, excel);
        return bytes;
    }

    private String boolToString(Boolean bool) throws SerException{
        String str = "";
        if(bool!=null){
            if(bool){

                str = "是";
            }
            else{

                str = "否";
            }
        }
        return str;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<SupplierInfoImportTemple> supplierInfoImportTemples = new ArrayList<>();
        SupplierInfoImportTemple excel = new SupplierInfoImportTemple();
        excel.setInfoCollectDate("text");
        excel.setInfoSource("text");
        excel.setMarketNum("text");
        excel.setBussType("text");
        excel.setSupplierNum("text");
        excel.setSupplierArea("text");
        excel.setSupplierName("text");
        excel.setSupplierType("text");
        excel.setBussScope("text");
        excel.setMajorProducts("text");
        excel.setBussLiaison("text");
        excel.setDuty("text");
        excel.setContactNum("text");
        excel.setMailBox("text");
        excel.setFax("text");
        excel.setInfoCompletion("text");
        excel.setUploadBusinLicense("text");
        excel.setUploadQualifition("text");
        excel.setLevelQualifition("text");
        excel.setDeterCooper("text");
        excel.setNewSigningDate("text");
        excel.setNewCutoffDate("text");
        excel.setPayComplete("text");
        excel.setSettlementCompleteDate("text");
        excel.setStatus("text");
        excel.setCooperationNum(100);
        excel.setInfoPerfecting("text");
        supplierInfoImportTemples.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(supplierInfoImportTemples, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<SupplierInfoTO> supplierInfoTOList) throws SerException {
        List<SupplierInfo> supplierInfoList = BeanTransform.copyProperties(supplierInfoTOList, SupplierInfo.class, true);
        supplierInfoList.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(supplierInfoList);
    }
}