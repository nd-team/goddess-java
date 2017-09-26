package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.CasualtyPurchasingListBO;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingListDTO;
import com.bjike.goddess.businsurance.entity.CasualtyPurchasingList;
import com.bjike.goddess.businsurance.excel.CasualtyPurchasingListImport;
import com.bjike.goddess.businsurance.to.CasualtyPurchasingListTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 团体意外险购买名单业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:24 ]
 * @Description: [ 团体意外险购买名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class CasualtyPurchasingListSerImpl extends ServiceImpl<CasualtyPurchasingList, CasualtyPurchasingListDTO> implements CasualtyPurchasingListSer {
    @Override
    public Long countCasualty(CasualtyPurchasingListDTO casualtyPurchasingListDTO) throws SerException {
        Long count = super.count(casualtyPurchasingListDTO);
        return count;
    }

    @Override
    public CasualtyPurchasingListBO getOneCasualty(String id) throws SerException {
        CasualtyPurchasingList casualtyPurchasingList = super.findById(id);
        return BeanTransform.copyProperties(casualtyPurchasingList,CasualtyPurchasingListBO.class);
    }

    @Override
    public List<CasualtyPurchasingListBO> listCasualty(CasualtyPurchasingListDTO casualtyPurchasingListDTO) throws SerException {
        casualtyPurchasingListDTO.getSorts().add("createTime=desc");
        List<CasualtyPurchasingList> list = super.findByCis(casualtyPurchasingListDTO,true);
        return BeanTransform.copyProperties(list, CasualtyPurchasingListBO.class );
    }

    @Override
    public CasualtyPurchasingListBO addCasualty(CasualtyPurchasingListTO casualtyPurchasingListTO) throws SerException {
        CasualtyPurchasingList casualtyPurchasingList = BeanTransform.copyProperties(casualtyPurchasingListTO,CasualtyPurchasingList.class,true);
        casualtyPurchasingList.setCreateTime(LocalDateTime.now());
        super.save(casualtyPurchasingList);
        return BeanTransform.copyProperties(casualtyPurchasingList,CasualtyPurchasingListBO.class);
    }

    @Override
    public CasualtyPurchasingListBO editCasualty(CasualtyPurchasingListTO casualtyPurchasingListTO) throws SerException {
        CasualtyPurchasingList casualtyPurchasingList = super.findById(casualtyPurchasingListTO.getId());
        LocalDateTime date = casualtyPurchasingList.getCreateTime();
        casualtyPurchasingList = BeanTransform.copyProperties(casualtyPurchasingListTO,CasualtyPurchasingList.class,true);
        casualtyPurchasingList.setCreateTime(date);
        casualtyPurchasingList.setModifyTime(LocalDateTime.now());
        super.update(casualtyPurchasingList);
        return BeanTransform.copyProperties(casualtyPurchasingList,CasualtyPurchasingListBO.class);
    }

    @Override
    public void deleteCasualty(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        List<CasualtyPurchasingList> list = super.findAll();
        List<CasualtyPurchasingListImport> casualtyPurchasingListImports = new ArrayList<>();
        list.stream().forEach(str -> {
            CasualtyPurchasingListImport excel = BeanTransform.copyProperties(str, CasualtyPurchasingListImport.class);
            casualtyPurchasingListImports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(casualtyPurchasingListImports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<CasualtyPurchasingListImport> casualtyPurchasingListImports = new ArrayList<>();
        CasualtyPurchasingListImport casualtyPurchasingListImport = new CasualtyPurchasingListImport();
        casualtyPurchasingListImport.setInsurancePolicyNo("695465552654456225");
        casualtyPurchasingListImport.setUnitNo("2342");
        casualtyPurchasingListImport.setSingleNo("321856954685468");
        casualtyPurchasingListImport.setSingleStatus("已分单");
        casualtyPurchasingListImport.setEffectiveDate("2017-12-12");
        casualtyPurchasingListImport.setSurrInsurApplyDate("2018-03-12");
        casualtyPurchasingListImport.setSecurityLeve("一级");
        casualtyPurchasingListImport.setClientPhone("15596598745");
        casualtyPurchasingListImport.setBeApplicantName("小朋友");
        casualtyPurchasingListImport.setDocumentsType("身份证");
        casualtyPurchasingListImport.setDocumentsPhone("6958469255369875462");
        casualtyPurchasingListImport.setGender("女");
        casualtyPurchasingListImport.setBirthDate("1987-12-12");
        casualtyPurchasingListImport.setInsuredAge(20);
        casualtyPurchasingListImport.setBeApplicantType("人");
        casualtyPurchasingListImport.setMainWithBeAppliRela("test");
        casualtyPurchasingListImport.setMainBeAppliName("小小朋友");
        casualtyPurchasingListImport.setOccupationCode("012");
        casualtyPurchasingListImport.setOccupationName("程序员");
        casualtyPurchasingListImport.setDepartment("研发部");
        casualtyPurchasingListImport.setSalaryMonth("100万");
        casualtyPurchasingListImport.setEmail("xiaopengyou_aj@163.com");
        casualtyPurchasingListImport.setMobilePhone("13698756482");
        casualtyPurchasingListImport.setContactPhone("02296584652");
        casualtyPurchasingListImport.setContactAddress("广州天河区");
        casualtyPurchasingListImport.setContactZipcode("023695");
        casualtyPurchasingListImport.setSocialSecurityArea("北京");
        casualtyPurchasingListImport.setSocialSecurityCard("156985236989");
        casualtyPurchasingListImport.setInsuranceCard("36957456698456625");
        casualtyPurchasingListImport.setBankAccount("存钱");
        casualtyPurchasingListImport.setOpenAccountName("小朋友");
        casualtyPurchasingListImport.setOpenIdType("身份证");
        casualtyPurchasingListImport.setOpenIdNo("695465552654456225");
        casualtyPurchasingListImport.setOpenBankCode("6597");
        casualtyPurchasingListImport.setOpenFullName("太平洋保险");
        casualtyPurchasingListImport.setOpenBankAccountNum("695465552654456225");
        casualtyPurchasingListImports.add(casualtyPurchasingListImport);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(casualtyPurchasingListImports, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<CasualtyPurchasingListTO> casualtyPurchasingListTOS) throws SerException {
        List<CasualtyPurchasingList> casualtyPurchasingLists = BeanTransform.copyProperties(casualtyPurchasingListTOS, CasualtyPurchasingList.class, true);
        casualtyPurchasingLists.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(casualtyPurchasingLists);
    }
}