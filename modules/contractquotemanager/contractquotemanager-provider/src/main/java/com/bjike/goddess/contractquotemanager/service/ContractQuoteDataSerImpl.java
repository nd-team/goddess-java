package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dao.ContractQuoteDataRep;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractQuoteData;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 合同单价资料信息业务实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:01:53.323 ]
 * @Description: [ 合同单价资料信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractQuoteDataSerCache")
@Service
public class ContractQuoteDataSerImpl extends ServiceImpl<ContractQuoteData, ContractQuoteDataDTO> implements ContractQuoteDataSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractQuoteDataBO save(ContractQuoteDataTO contractQuoteDataTO) throws SerException {
        ContractQuoteData contractQuoteData = BeanTransform.copyProperties(contractQuoteDataTO, ContractQuoteData.class, true);
        super.save(contractQuoteData);
        contractQuoteDataTO.setId(contractQuoteData.getId());
        return BeanTransform.copyProperties(contractQuoteDataTO, ContractQuoteDataBO.class, true);
    }
    @Override
    public List<ContractQuoteDataBO> list(ContractQuoteDataDTO contractQuoteDataDTO) throws SerException {
        List<ContractQuoteData> contractQuoteDatas = super.findByCis(contractQuoteDataDTO);
        List<ContractQuoteDataBO> contractQuoteDataBOs = BeanTransform.copyProperties(contractQuoteDatas, ContractQuoteDataBO.class);
        return contractQuoteDataBOs;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(ContractQuoteDataTO contractQuoteDataTO) throws SerException {
        ContractQuoteData contractQuoteData = super.findById(contractQuoteDataTO.getId());
        BeanTransform.copyProperties(contractQuoteDataTO, contractQuoteData, true);
        contractQuoteData.setModifyTime(LocalDateTime.now());
        super.update(contractQuoteData);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealStatus(String id) throws SerException {
        ContractQuoteData contractQuoteData = super.findById(id);
        contractQuoteData.setStatus(Status.CONGEAL);
        contractQuoteData.setModifyTime(LocalDateTime.now());
        super.update(contractQuoteData);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawStatus(String id) throws SerException {
        ContractQuoteData contractQuoteData = super.findById(id);
        contractQuoteData.setStatus(Status.THAW);
        contractQuoteData.setModifyTime(LocalDateTime.now());
        super.update(contractQuoteData);
    }


    @Override
    public List<ContractQuoteDataBO> collect(ContractQuoteDataDTO dto) throws SerException {
        if(dto ==null){
            throw new SerException("您好!查询条件为空,无法进行查询!");
        }
        if((dto.getArea()==null) || (dto.getArea().length == 0)){
            throw new SerException("您好!地区列表为空,无法进行查询!");

        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] areas = dto.getArea();
        String customerName = dto.getCustomerName();
        String startDate = dto.getStartDate();
        String endDate = dto.getEndDate();
        List<ContractQuoteDataBO> list = new ArrayList<>(0);
        for(String area : areas){
           ContractQuoteDataDTO contractQuoteDateDTO = new ContractQuoteDataDTO();
            contractQuoteDateDTO.getConditions().add(Restrict.eq("area",area));
            contractQuoteDateDTO.getConditions().add(Restrict.eq("customerName",customerName));
            ContractQuoteDataBO bo = new ContractQuoteDataBO();
            bo.setArea(area);
            bo.setCustomerName(customerName);
            bo.setSuitableDateStart(startDate);
            bo.setSuitableDateEnd(endDate);
            list.add(bo);
        }


        return BeanTransform.copyProperties(super.findByCis(dto), ContractQuoteDataBO.class);
    }
    @Override
    public List<ContractQuoteDataBO> searchs(ContractQuoteDataBO bo) throws SerException {
        ContractQuoteDataDTO dto = new ContractQuoteDataDTO();
        if (bo.getArea() != null && !bo.getArea().equals("")) {
            dto.getConditions().add(Restrict.eq("area", bo.getArea()));
        }
        if (bo.getProject() != null && !bo.getProject().equals("")) {
            dto.getConditions().add(Restrict.eq("project", bo.getProject()));
        }

        return BeanTransform.copyProperties(super.findByCis(dto), ContractQuoteDataBO.class);
    }

}