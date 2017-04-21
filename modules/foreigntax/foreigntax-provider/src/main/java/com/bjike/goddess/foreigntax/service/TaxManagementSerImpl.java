package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.TaxCollectBO;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.entity.TaxManagement;
import com.bjike.goddess.foreigntax.enums.PaymentStatus;
import com.bjike.goddess.foreigntax.to.CollectTo;
import com.bjike.goddess.foreigntax.to.TaxManagementTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 税金管理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class TaxManagementSerImpl extends ServiceImpl<TaxManagement, TaxManagementDTO> implements TaxManagementSer {

    @Override
    public Long countTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        taxManagementDTO.getSorts().add("createTime=desc");
        Long counts = super.count(taxManagementDTO);
        return counts;
    }
    @Override
    public List<TaxManagementBO> findListTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        taxManagementDTO.getSorts().add("createTime=desc");
        List<TaxManagement> taxManagements = super.findByCis(taxManagementDTO,true);
        List<TaxManagementBO> taxManagementBOS = BeanTransform.copyProperties(taxManagements,TaxManagementBO.class,true);
        return  taxManagementBOS;
    }

    @Override
    public TaxManagementBO insertTaxManagement(TaxManagementTO taxManagementTO) throws SerException {
        TaxManagement taxManagement = BeanTransform.copyProperties(taxManagementTO,TaxManagement.class,true);
        if(taxManagement.getPaymentStatus().equals(PaymentStatus.DIDPAY)){
            taxManagement.setCompany(taxManagementTO.getCompany());
            taxManagement.setMonth(taxManagementTO.getMonth());
            taxManagement.setTaxType(taxManagementTO.getTaxType());
            taxManagement.setRate(taxManagement.getRate());
            taxManagement.setTax(taxManagement.getTax());
        }
        if(taxManagement.getPaymentStatus().equals(PaymentStatus.DUTYPAID)){
            taxManagement.setPaymentDate(LocalDate.parse(taxManagementTO.getPaymentDate()));
            taxManagement.setPaymentUnit(taxManagementTO.getPaymentUnit());
            taxManagement.setCreateTime(LocalDateTime.now());
            super.save(taxManagement);
        }
        return BeanTransform.copyProperties(taxManagement,TaxManagementBO.class);
    }

    @Override
    public TaxManagementBO editTaxManagement(TaxManagementTO taxManagementTO) throws SerException {
        TaxManagement taxManagement = super.findById(taxManagementTO.getId());
        BeanTransform.copyProperties(taxManagementTO,taxManagement,true);
        taxManagement.setModifyTime(LocalDateTime.now());
        super.update(taxManagement);
        return BeanTransform.copyProperties(taxManagementTO,TaxManagementBO.class);
    }

    @Override
    public void removeTaxManagement(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
    @Override
    public void upload() throws SerException {
        //todo 未做上传
        return;

    }
    @Override
    public TaxManagementBO viewTaxManagement(String company,String taxType,String month) throws SerException {
        TaxManagementDTO dto = new TaxManagementDTO();
        dto.getConditions().add(Restrict.eq("company",company));
        dto.getConditions().add(Restrict.eq("taxType",taxType));
        dto.getConditions().add(Restrict.eq("month",month));
        TaxManagementBO taxManagementBO = BeanTransform.copyProperties(super.findOne(dto),TaxManagementBO.class);
        return taxManagementBO;

    }
    @Override
    public List<TaxCollectBO> collectTaxManagement(CollectTo to) throws SerException {
        List<TaxManagement> list = this.getListByFilter(to).stream()
                .sorted(Comparator.comparing(TaxManagement::getCompany)
                .thenComparing(TaxManagement::getTaxType)
                .thenComparing(TaxManagement::getMonth))
                .collect(Collectors.toList());
        List<TaxCollectBO> taxCollectBOS = new ArrayList<>();
        String company = "",taxType = "",month = "";
        for(TaxManagement taxManagement:list)
            if(!taxManagement.getCompany().equals(company) || !taxManagement.getTaxType().equals(taxType)
                    || taxManagement.getMonth().equals(month)){
                company = taxManagement.getCompany();
                taxType = taxManagement.getTaxType();
                month  = taxManagement.getMonth();
                TaxCollectBO taxCollectBO = BeanTransform.copyProperties(taxManagement,TaxCollectBO.class,true);
                List<TaxManagement> taxManagements = list.stream()
                        .filter(d -> d.getCompany().equals(taxManagement.getCompany()) && d.getTaxType().equals(taxManagement.getTaxType())
                                && d.getMonth().equals(taxManagement.getMonth()))
                        .collect(Collectors.toList());
                taxCollectBO.setRate(taxManagement.getRate());
                taxCollectBO.setTax(taxManagements.stream().mapToDouble(TaxManagement::getTax).sum());
                taxCollectBO.setPaymentStatus(taxManagement.getPaymentStatus());
                taxCollectBO.setPaymentDate(String.valueOf(taxManagement.getPaymentDate()));
                taxCollectBO.setPaymentUnit(taxManagement.getPaymentUnit());
                taxCollectBOS.add(taxCollectBO);
            }


        return taxCollectBOS;

    }
    /**
     * 根据过滤条件传获取数据
     *
     * @param to 过滤条件传输对象
     * @return
     * @throws SerException
     */
    private List<TaxManagement> getListByFilter(CollectTo to) throws SerException {
        TaxManagementDTO dto = new TaxManagementDTO();
        if (StringUtils.isNotBlank(to.getCompany()))
            dto.getConditions().add(Restrict.eq("company", to.getCompany()));
        if (StringUtils.isNotBlank(to.getTaxType()))
            dto.getConditions().add(Restrict.eq("taxType", to.getTaxType()));
        if (StringUtils.isNotBlank(to.getMonth()))
            dto.getConditions().add(Restrict.eq("month", to.getMonth()));
        return super.findByCis(dto);
    }
}