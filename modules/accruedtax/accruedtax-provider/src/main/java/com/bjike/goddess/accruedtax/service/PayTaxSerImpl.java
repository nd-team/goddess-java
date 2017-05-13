package com.bjike.goddess.accruedtax.service;

import com.bjike.goddess.accruedtax.bo.PayTaxBO;
import com.bjike.goddess.accruedtax.dto.ProjectTaxDTO;
import com.bjike.goddess.accruedtax.entity.ProjectTax;
import com.bjike.goddess.accruedtax.to.PayTaxTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.accruedtax.dto.PayTaxDTO;
import com.bjike.goddess.accruedtax.entity.PayTax;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.api.TaxManagementAPI;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 应交税金业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:17 ]
 * @Description: [ 应交税金业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "accruedtaxSerCache")
@Service
public class PayTaxSerImpl extends ServiceImpl<PayTax, PayTaxDTO> implements PayTaxSer {

    @Autowired
    private TaxManagementAPI taxManagementAPI;
    @Autowired
    private ProjectTaxSer projectTaxSer;

    @Override
    public Long countPayTax(PayTaxDTO payTaxDTO) throws SerException {
        Long count = super.count(payTaxDTO);
        return count;
    }

    @Override
    public List<PayTaxBO> listPayTax(PayTaxDTO payTaxDTO) throws SerException {
        payTaxDTO.getSorts().add("createTime=desc");
        List<PayTax> list = super.findByCis(payTaxDTO,true);

        return BeanTransform.copyProperties(list, PayTaxBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PayTaxBO addPayTax(PayTaxTO payTaxTO) throws SerException {
        LocalDate time = LocalDate.parse(payTaxTO.getTaxDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String month = time.getMonthValue() < 10 ? "0"+time.getMonthValue():time.getMonthValue()+"";
        String startTime = time.getYear()+"-"+month+"-01";
        String endTime = time.with(TemporalAdjusters.lastDayOfMonth())+"";
        List<TaxManagementBO> taxManagementBOList = taxManagementAPI.listByCompany(payTaxTO.getCompany() ,startTime , endTime );
        Double actualTax  = 0d;
        if( taxManagementBOList!= null && taxManagementBOList.size()>0 ){
            actualTax = taxManagementBOList.stream().filter(str->str.getTax()!=null).mapToDouble(TaxManagementBO::getTax).sum();
        }
        Double tempTax = 0d;
        if( actualTax != null && payTaxTO.getActualTax() != actualTax ){
            tempTax = actualTax;
        }else{
            tempTax = payTaxTO.getActualTax();
        }
        PayTax payTax = BeanTransform.copyProperties(payTaxTO,PayTax.class,true);
        payTax.setActualTax( tempTax );
        payTax.setRate( payTax.getActualTax()/payTax.getPlanTax());
        payTax.setBalance( payTax.getActualTax() - payTax.getPlanTax() );
        payTax.setCreateTime(LocalDateTime.now());
        super.save( payTax );
        return BeanTransform.copyProperties(payTax, PayTaxBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PayTaxBO editPayTax(PayTaxTO payTaxTO) throws SerException {
        PayTax payTax = BeanTransform.copyProperties(payTaxTO,PayTax.class,true);
        PayTax cusLevel = super.findById( payTaxTO.getId() );

        BeanUtils.copyProperties( payTax , cusLevel ,"id","createTime");
        cusLevel.setRate( cusLevel.getActualTax()/cusLevel.getPlanTax());
        cusLevel.setBalance( cusLevel.getActualTax() - cusLevel.getPlanTax() );
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, PayTaxBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deletePayTax(String id) throws SerException {
        if(StringUtils.isBlank( id)){
            throw  new SerException("id不能为空");
        }
        ProjectTaxDTO projectTaxDTO = new ProjectTaxDTO();
        projectTaxDTO.getConditions().add(Restrict.eq("payTaxId",id));
        List<ProjectTax> list = projectTaxSer.findByCis( projectTaxDTO );
        if( list!= null && list.size()>0 ){
            projectTaxSer.remove( list );
        }
        super.remove( id );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PayTaxBO splitTax(PayTaxTO payTaxTO) throws SerException {
        if(StringUtils.isBlank( payTaxTO.getId())){
            throw  new SerException("id不能为空");
        }
        Double splitRate = payTaxTO.getSplitRate();

        PayTax payTax = super.findById( payTaxTO.getId() );

        ProjectTax projectTax = new ProjectTax();
        projectTax.setProject( payTaxTO.getProject());
        projectTax.setTaxDate( payTax.getTaxDate() );
        projectTax.setTaxType( payTax.getTaxType() );
        projectTax.setTaxRate( payTax.getTaxRate() );
        projectTax.setTargetTax( payTax.getTargetTax()*splitRate );
        projectTax.setPlanTax( payTax.getPlanTax()* splitRate );
        projectTax.setActualTax( payTax.getActualTax()*splitRate );
        projectTax.setCreateTime(LocalDateTime.now());
        projectTaxSer.save( projectTax );
        return BeanTransform.copyProperties(payTaxTO , PayTaxBO.class);
    }

    @Override
    public List<PayTaxBO> collectCompany(PayTaxDTO payTaxDTO) throws SerException {
        String company = payTaxDTO.getCompany();
        String[] field = new String[]{"company","targetTax","planTax","actualTax","rate","balance"};
        String sql = "select company , sum(targetTax) as targetTax, sum(planTax) as planTax, sum(actualTax) as actualTax " +
                " ,(sum(actualTax)/sum(planTax)) as rate , (sum(actualTax)-sum(planTax)) as balance from accruedtax_paytax where 1=1  ";
        if( StringUtils.isNotBlank(company)){
            field = new String[]{"company","targetTax","planTax","actualTax","rate","balance","taxDate"};
            sql = " select company  , targetTax , planTax , actualTax , rate , balance, taxDate from accruedtax_paytax where 1=1 and company ='"+company+"' ";
        }
        if( StringUtils.isNotBlank(payTaxDTO.getStartTime()) && StringUtils.isNotBlank(payTaxDTO.getEndTime()) ){
            LocalDate start = LocalDate.parse(payTaxDTO.getStartTime());
            LocalDate end = LocalDate.parse(payTaxDTO.getEndTime());
            sql = sql +" and taxDate between '"+start+"' and '"+end+"' ";
        }
        if( StringUtils.isBlank(company)){
            sql = sql + " group by company ";
        }
        List<PayTax> list = super.findBySql(sql , PayTax.class, field);

        List<PayTaxBO> listBO = new ArrayList<>();
        if( list != null && list.size()>0  ){
            listBO = BeanTransform.copyProperties(list,PayTaxBO.class);
            if(StringUtils.isBlank(company)){
                listBO.stream().forEach(str->{
                    str.setTaxDate("无");
                });
            }
        }else{
            PayTaxBO bo = new PayTaxBO();
            listBO.add(bo);
        }
        return listBO;
    }

    @Override
    public List<PayTaxBO> collectTaxType(PayTaxDTO payTaxDTO) throws SerException {
        String taxType = payTaxDTO.getTaxType();
        String[] field = new String[]{"taxType","targetTax","planTax","actualTax","rate","balance"};
        String sql = "select taxType , sum(targetTax) as targetTax, sum(planTax) as planTax, sum(actualTax) as actualTax " +
                " ,(sum(actualTax)/sum(planTax)) as rate , (sum(actualTax)-sum(planTax)) as balance from accruedtax_paytax where 1=1  ";
        if( StringUtils.isNotBlank(taxType)){
            field = new String[]{"taxType","taxDate","targetTax","planTax","actualTax","rate","balance"};
            sql = " select taxType , taxDate , targetTax , planTax , actualTax , rate , balance from accruedtax_paytax where 1=1 and taxType ='"+taxType+"' ";
        }
        if( StringUtils.isNotBlank(payTaxDTO.getStartTime()) && StringUtils.isNotBlank(payTaxDTO.getEndTime()) ){
            LocalDate start = LocalDate.parse(payTaxDTO.getStartTime());
            LocalDate end = LocalDate.parse(payTaxDTO.getEndTime());
            sql = sql +" and taxDate between '"+start+"' and '"+end+"' ";
        }
        if( StringUtils.isBlank(taxType)){
            sql = sql + " group by taxType ";
        }
        List<PayTax> list = super.findBySql(sql , PayTax.class, field);

        List<PayTaxBO> listBO = new ArrayList<>();
        if( list != null && list.size()>0  ){
            listBO = BeanTransform.copyProperties(list,PayTaxBO.class);
            if(StringUtils.isBlank(taxType)){
                listBO.stream().forEach(str->{
                    str.setTaxDate("无");
                });
            }
        }else{
            PayTaxBO bo = new PayTaxBO();
            listBO.add(bo);
        }
        return listBO;
    }

    @Override
    public PayTaxBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        PayTax payTax = super.findById(id);
        return BeanTransform.copyProperties(payTax, PayTaxBO.class);
    }

    @Override
    public List<String> listCompany() throws SerException {
        String [] field = new String[]{"company"};
        String sql = "select company from accruedtax_paytax group by company ";
        List<PayTax> list = super.findBySql( sql , PayTax.class, field );
        List<String> companyList = list.stream().map(PayTax::getCompany).collect(Collectors.toList());
        return companyList;
    }

    @Override
    public List<String> listTaxType() throws SerException {
        String [] field = new String[]{"taxType"};
        String sql = "select taxType from accruedtax_paytax group by taxType ";
        List<PayTax> list = super.findBySql( sql , PayTax.class, field );
        List<String> companyList = list.stream().map(PayTax::getTaxType).collect(Collectors.toList());
        return companyList;
    }
}