package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CusFamilyMemberBO;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.CustomerDetailBO;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.dto.CustomerDetailDTO;
import com.bjike.goddess.customer.entity.CusFamilyMember;
import com.bjike.goddess.customer.entity.CustomerBaseInfo;
import com.bjike.goddess.customer.entity.CustomerDetail;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.to.CusFamilyMemberTO;
import com.bjike.goddess.customer.to.CustomerDetailTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户详细信息业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:41:13.476 ]
 * @Description: [ 客户详细信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CustomerDetailSerImpl extends ServiceImpl<CustomerDetail, CustomerDetailDTO> implements CustomerDetailSer {

    @Autowired
    private CustomerBaseInfoSer customerBaseInfoAPI;
    @Autowired
    private CusFamilyMemberSer cusFamilyMemberAPI;

    @Override
    public Long countCustomerDetail(CustomerDetailDTO customerDetailDTO) throws SerException {
        Long count = super.count( customerDetailDTO );
        return count;
    }

    @Override
    public List<CustomerDetailBO> listCustomerDetail(CustomerDetailDTO customerDetailDTO) throws SerException {
        List<CustomerDetail> list = super.findByCis(customerDetailDTO, true);
        List<CustomerDetailBO> customerDetailBOArrayList = new ArrayList<>();
        list.stream().forEach(str->{
            CustomerLevelBO customerLevelBO = BeanTransform.copyProperties(str.getCustomerBaseInfo().getCustomerLevel(), CustomerLevelBO.class);
            CustomerBaseInfoBO customerBaseInfoBO = BeanTransform.copyProperties(str.getCustomerBaseInfo(), CustomerBaseInfoBO.class);
            customerBaseInfoBO.setCustomerLevelBO( customerLevelBO );
            CustomerDetailBO customerDetailBO = BeanTransform.copyProperties(str, CustomerDetailBO.class);
            customerDetailBO.setCustomerBaseInfoBO(customerBaseInfoBO);
            customerDetailBOArrayList.add(customerDetailBO);
        });
        return BeanTransform.copyProperties(customerDetailBOArrayList, CustomerDetailBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerDetailBO addCustomerDetail(CustomerDetailTO customerDetailTO) throws SerException {
        String baseInfoNum = customerDetailTO.getCustomerNum();
        CustomerBaseInfoDTO baseInfoDTO = new CustomerBaseInfoDTO();
        baseInfoDTO.getConditions().add( Restrict.eq( "customerNum",baseInfoNum) );
        CustomerBaseInfo baseInfo = customerBaseInfoAPI.findOne(baseInfoDTO);

        CustomerDetail customerDetail = BeanTransform.copyProperties(customerDetailTO,CustomerDetail.class,true);
        customerDetail.setCreateTime(LocalDateTime.now());
        customerDetail.setCustomerBaseInfo( baseInfo );
        customerDetail = super.save( customerDetail );

        //添加家庭信息4条家庭信息
        if( customerDetailTO.getCusFamilyMemberTOList() != null && customerDetailTO.getCusFamilyMemberTOList().size()>0){
            CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
            cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerNum",baseInfoNum));
            Long countFamily =  cusFamilyMemberAPI.count( cusFamilyMemberDTO );

            if( countFamily < 4 ){
                List<CusFamilyMemberTO> familyMemberTOList = customerDetailTO.getCusFamilyMemberTOList();
                List<CusFamilyMember> cusFamilyMemberList = BeanTransform.copyProperties(familyMemberTOList,CusFamilyMember.class,true);
                for( CusFamilyMember temp : cusFamilyMemberList ) {
                    temp.setCreateTime(LocalDateTime.now());
                    temp.setCustomerDetail(customerDetail);
                }
                cusFamilyMemberAPI.save( cusFamilyMemberList );
            }
        }

        return BeanTransform.copyProperties(customerDetail, CustomerDetailBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerDetailBO editCustomerDetail(CustomerDetailTO customerDetailTO) throws SerException {

        //先查一遍详细
        CustomerDetailDTO dto = new CustomerDetailDTO();
        dto.getConditions().add(Restrict.eq("customerNum",customerDetailTO.getCustomerNum()));
        CustomerDetail cusDetail = super.findOne( dto );

        CustomerDetail customerDetail = BeanTransform.copyProperties(customerDetailTO,CustomerDetail.class,true);
        BeanUtils.copyProperties(customerDetail,cusDetail,"customerNum","id","createTime","customerBaseInfo");
        cusDetail.setModifyTime(LocalDateTime.now());

        super.update( cusDetail );

        //修改家庭信息  先删除再重新添加
        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id",cusDetail.getId()));
        List<CusFamilyMember> cfamilyList = cusFamilyMemberAPI.findByCis( cusFamilyMemberDTO );
        if( cfamilyList!= null && cfamilyList.size()>0 ){
            cusFamilyMemberAPI.remove( cfamilyList );
        }
        //重新添加家庭信息
        List<CusFamilyMemberTO> familyMemberTOList = customerDetailTO.getCusFamilyMemberTOList();
        if( familyMemberTOList != null && familyMemberTOList.size()>0){
            List<CusFamilyMember> cusFamilyMemberList = BeanTransform.copyProperties(familyMemberTOList,CusFamilyMember.class,true);
            for( CusFamilyMember temp : cusFamilyMemberList ) {
                temp.setCreateTime(LocalDateTime.now());
                temp.setCustomerDetail(cusDetail);
            }
            cusFamilyMemberAPI.save( cusFamilyMemberList );
        }


        return BeanTransform.copyProperties(cusDetail, CustomerDetailBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCustomerDetail(String id) throws SerException {
        CustomerDetail customerDetail = super.findById( id );

        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id",customerDetail.getId()));
        List<CusFamilyMember> cfamilyList = cusFamilyMemberAPI.findByCis( cusFamilyMemberDTO );
        if( cfamilyList!= null && cfamilyList.size()>0 ){
            //先删除家庭成员
            cusFamilyMemberAPI.remove( cfamilyList );
//            super.remove( customerDetail );
        }
        try {
            super.remove(customerDetail );
        } catch (SerException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public CustomerDetailBO getCustomerDetailById(String id) throws SerException {
        CustomerDetail customerDetail = super.findById( id );

        CustomerBaseInfo customerBaseInfo = customerDetail.getCustomerBaseInfo();
        CustomerLevelBO customerLevelBO = BeanTransform.copyProperties(customerBaseInfo.getCustomerLevel(),CustomerLevelBO.class);
        CustomerBaseInfoBO customerBaseInfoBO = BeanTransform.copyProperties(customerDetail.getCustomerBaseInfo(),CustomerBaseInfoBO.class);
        customerBaseInfoBO.setCustomerLevelBO( customerLevelBO );
        CustomerDetailBO customerDetailBO = BeanTransform.copyProperties(customerDetail , CustomerDetailBO.class  );
        customerDetailBO.setCustomerBaseInfoBO(customerBaseInfoBO);
        //查找家庭信息
        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id",id));
        List<CusFamilyMember> cfamilyList = cusFamilyMemberAPI.findByCis( cusFamilyMemberDTO );
        List<CusFamilyMemberBO> cusFamilyMemberBOList = BeanTransform.copyProperties(cfamilyList,CusFamilyMemberBO.class  );

        customerDetailBO.setCusFamilyMemberBOList( cusFamilyMemberBOList );

        return  customerDetailBO ;
    }


    
    @Override
    public CustomerDetailBO getCustomerDetailByNum(String customerNum) throws SerException {
        CustomerBaseInfoDTO cBaseInfoDTO = new CustomerBaseInfoDTO();
        cBaseInfoDTO.getConditions().add(Restrict.eq("customerNum",customerNum));
        CustomerBaseInfo customerBaseInfo = customerBaseInfoAPI.findOne(cBaseInfoDTO );
        CustomerBaseInfoBO customerBaseInfoBO = BeanTransform.copyProperties( customerBaseInfo,CustomerBaseInfoBO.class );
        CustomerLevelBO customerLevelBO = BeanTransform.copyProperties(customerBaseInfo.getCustomerLevel(),CustomerLevelBO.class);
        customerBaseInfoBO.setCustomerLevelBO(customerLevelBO );

        CustomerDetailDTO cdDTO = new CustomerDetailDTO();
        cdDTO.getConditions().add( Restrict.eq("customerNum",customerNum));
        CustomerDetail customerDetail = super.findOne( cdDTO );
        CustomerDetailBO customerDetailBO = BeanTransform.copyProperties( customerDetail,CustomerDetailBO.class );
        //查找家庭信息
        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id",customerDetail.getId()));
        List<CusFamilyMember> cfamilyList = cusFamilyMemberAPI.findByCis( cusFamilyMemberDTO );
        List<CusFamilyMemberBO> cusFamilyMemberBOList = BeanTransform.copyProperties(cfamilyList,CusFamilyMemberBO.class );

        customerDetailBO.setCustomerBaseInfoBO( customerBaseInfoBO );
        customerDetailBO.setCusFamilyMemberBOList( cusFamilyMemberBOList );

        return customerDetailBO;
    }
}