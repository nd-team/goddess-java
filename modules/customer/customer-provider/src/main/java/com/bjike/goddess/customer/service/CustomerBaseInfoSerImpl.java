package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.DataType;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.entity.CusFamilyMember;
import com.bjike.goddess.customer.entity.CustomerBaseInfo;
import com.bjike.goddess.customer.entity.CustomerDetail;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.enums.CustomerSex;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import com.bjike.goddess.customer.to.CustomerLevelTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 客户基本信息业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.065 ]
 * @Description: [ 客户基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CustomerBaseInfoSerImpl extends ServiceImpl<CustomerBaseInfo, CustomerBaseInfoDTO> implements CustomerBaseInfoSer {

    @Autowired
    private CustomerLevelSer customerLevelAPI;
    @Autowired
    private CusFamilyMemberSer cusFamilyMemberAPI;
    @Autowired
    private CustomerDetailSer customerDetailAPI;
    @Autowired
    private UserAPI userAPI;

    @Override
    public CustomerBaseInfoBO generateCustomerNum() throws SerException {
        String[] fields = new String[]{"customerNum", "customerName"};
        List<CustomerBaseInfoBO> customerBaseInfoBOS =super.findBySql("select customernum as customerNum,customername as customerName from customer_customerbaseinfo", CustomerBaseInfoBO.class, fields);

        Set<String> cusNumberSet = customerBaseInfoBOS.stream().filter(cus -> cus.getCustomerNum().length() > 5).map(CustomerBaseInfoBO::getCustomerNum).collect(Collectors.toSet());
        List<Integer> cusNumberList = new ArrayList<>(0);
        for (String num : cusNumberSet) {
            if (NumberUtils.isNumber(num.substring(5))) {
                cusNumberList.add(Integer.valueOf(num.substring(5)));
            }
        }
        Integer maxInt =(( cusNumberList != null && cusNumberList.size()>0)?Collections.max(cusNumberList):0) + 0001;
        String customerNumber = "CS000" + maxInt;

        CustomerBaseInfoBO cbo = new CustomerBaseInfoBO();
        cbo.setCustomerNum( customerNumber);
        return cbo;
    }

    @Cacheable
    @Override
    public List<CustomerBaseInfoBO> listCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        List<CustomerBaseInfo> list = super.findByCis(customerBaseInfoDTO, true);

        return BeanTransform.copyProperties(list, CustomerBaseInfoBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerBaseInfoBO addCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        String levelName =  customerBaseInfoTO.getCustomerLevelTO().getName();
        CustomerLevelBO customerLevelBO = customerLevelAPI.getCustomerLevelByName( levelName );
        CustomerLevel customerLevel = BeanTransform.copyProperties( customerLevelBO,CustomerLevel.class,true);

        performance(customerBaseInfoTO);
        CustomerBaseInfo customerBaseInfo =  BeanTransform.copyProperties( customerBaseInfoTO , CustomerBaseInfo.class,true);
        customerBaseInfo.setCreateTime(LocalDateTime.now());
        customerBaseInfo.setModifyPersion( userAPI.currentUser().getUsername());
        customerBaseInfo.setCustomerLevel(customerLevel);

        super.save( customerBaseInfo );
        return BeanTransform.copyProperties( customerBaseInfoTO ,CustomerBaseInfoBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerBaseInfoBO editCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        performance(customerBaseInfoTO);
        CustomerBaseInfo customerBaseInfo =  BeanTransform.copyProperties( customerBaseInfoTO , CustomerBaseInfo.class,true);

        customerBaseInfo.setModifyTime( LocalDateTime.now());
        customerBaseInfo.setModifyPersion( userAPI.currentUser().getUsername());
        super.update( customerBaseInfo);
        return BeanTransform.copyProperties( customerBaseInfo ,CustomerBaseInfoBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCustomerBaseInfo(String id) throws SerException {
        CustomerBaseInfo customerBaseInfo = super.findById( id );
        CustomerDetail detail = customerBaseInfo.getCustomerDetail();
        if( detail != null ){
            CusFamilyMemberDTO familyMemberDTO = new CusFamilyMemberDTO();
            familyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id", detail.getId()));
            List<CusFamilyMember> cusFamilyMemberList = cusFamilyMemberAPI.findByCis(familyMemberDTO );
            if( cusFamilyMemberList!= null && cusFamilyMemberList.size()>0 ){
                //删除家庭信息
                cusFamilyMemberAPI.remove( cusFamilyMemberList );
            }
            //删除详细信息
            customerDetailAPI.remove( detail );
            super.remove( customerBaseInfo );
        }else{
            super.remove( customerBaseInfo );
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCustomerBaseInfo(String id) throws SerException {
        CustomerBaseInfo customerBaseInfo = super.findById( id );
        customerBaseInfo.setModifyPersion( userAPI.currentUser().getUsername());
        customerBaseInfo.setModifyTime( LocalDateTime.now() );
        customerBaseInfo.setStatus(Status.CONGEAL);

        super.update( customerBaseInfo );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCustomerBaseInfo(String id) throws SerException {
        CustomerBaseInfo customerBaseInfo = super.findById( id );
        customerBaseInfo.setModifyPersion( userAPI.currentUser().getUsername());
        customerBaseInfo.setModifyTime( LocalDateTime.now() );
        customerBaseInfo.setStatus(Status.THAW);

        super.update( customerBaseInfo );
    }


    @Cacheable
    @Override
    public List<String> getCustomerBaseInfoArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<CustomerBaseInfoBO> customerBaseInfoBOS =super.findBySql("select area,1 from customer_customerbaseinfo order by area asc ", CustomerBaseInfoBO.class, fields);

        List<String> areaList  = customerBaseInfoBOS.stream().map(CustomerBaseInfoBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim())) ).distinct().collect(Collectors.toList());


        return areaList;
    }

    @Cacheable
    @Override
    public List<String> getCustomerBaseInfoName() throws SerException {
        String[] fields = new String[]{"customerName"};
        List<CustomerBaseInfoBO> customerBaseInfoBOS =super.findBySql("select customername ,1 from customer_customerbaseinfo", CustomerBaseInfoBO.class, fields);

        List<String> customerNameList  = customerBaseInfoBOS.stream().map(CustomerBaseInfoBO::getCustomerName)
                .filter(name -> (name != null || !"".equals(name.trim())) ).distinct().collect(Collectors.toList());


        return customerNameList;
    }

    /**
     * 客户信息详细完成度
     *
     * @param customerBaseInfoTO
     */
    private void performance(CustomerBaseInfoTO customerBaseInfoTO) {
        double sum = 0, now = 0;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getCustomerNum())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getArea())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getCustomerName())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(String.valueOf(customerBaseInfoTO.getCustomerSex().getCode()))) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(String.valueOf(customerBaseInfoTO.getCustomerType().getCode()))) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(String.valueOf(customerBaseInfoTO.getCustomerStatus().getCode()))) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(String.valueOf(customerBaseInfoTO.getRelation()))) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getCustomerLevelTO().getName())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getOrigin())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getIntroducer())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getCusEmail())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getTel())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getPhone())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getWeChart())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getQq())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getWorkProfession())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getOriganizion())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getOriganizationSize())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getWorkPosition())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getWorkLevel())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getWorkRight())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getLifeArea())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getGrouthArea())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getOldWorkPlace())) {
            now++;
        }
        sum++;

        double completeness = (now / sum) * 100;
        customerBaseInfoTO.setInfoComplet(new BigDecimal(completeness).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "%");
    }


    @Override
    public CustomerBaseInfoBO addMarketCustomerInfo(@NotBlank String customerName,String origanizion) throws SerException {
        if( StringUtils.isNotBlank(customerName) ){
            return null;
        }else{
            /**
             * 生成客户编号
             */
            CustomerBaseInfoBO cBO = generateCustomerNum();

            CustomerBaseInfo cbaseInfo = new CustomerBaseInfo();
            cbaseInfo.setCustomerName( customerName );
            cbaseInfo.setOrigin(origanizion);
            cbaseInfo.setCustomerNum( cBO.getCustomerNum() );
            cbaseInfo.setCustomerSex(CustomerSex.NONE );
            cbaseInfo.setCreateTime( LocalDateTime.now());
            cbaseInfo.setModifyPersion( userAPI.currentUser().getUsername());

            super.save( cbaseInfo );
            return BeanTransform.copyProperties(cbaseInfo , CustomerBaseInfoBO.class);
        }
    }

    @Override
    public CustomerBaseInfoBO getCustomerInfoByNum(String customerNum) throws SerException {
        CustomerBaseInfoDTO dto = new CustomerBaseInfoDTO();
        dto.getConditions().add(Restrict.eq("customerNum",customerNum));
        CustomerBaseInfo customerBaseInfo = super.findOne( dto );
        return BeanTransform.copyProperties( customerBaseInfo,CustomerBaseInfoBO.class);
    }
}