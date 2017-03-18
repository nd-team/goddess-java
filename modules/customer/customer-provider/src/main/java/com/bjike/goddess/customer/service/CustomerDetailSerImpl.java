package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CusFamilyMemberBO;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.CustomerDetailBO;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.dto.CustomerDetailDTO;
import com.bjike.goddess.customer.entity.CusFamilyMember;
import com.bjike.goddess.customer.entity.CustomerBaseInfo;
import com.bjike.goddess.customer.entity.CustomerDetail;
import com.bjike.goddess.customer.to.CusFamilyMemberTO;
import com.bjike.goddess.customer.to.CustomerDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Cacheable
    @Override
    public List<CustomerDetailBO> listCustomerDetail(CustomerDetailDTO customerDetailDTO) throws SerException {
        List<CustomerDetail> list = super.findByCis(customerDetailDTO, true);

        return BeanTransform.copyProperties(list, CustomerDetailBO.class, true);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerDetailBO addCustomerDetail(CustomerDetailTO customerDetailTO) throws SerException {
        String baseInfoNum = customerDetailTO.getCustomerBaseInfoTO().getCustomerNum();
        CustomerBaseInfoDTO baseInfoDTO = new CustomerBaseInfoDTO();
        baseInfoDTO.getConditions().add( Restrict.eq( "customerNum",baseInfoNum) );
        CustomerBaseInfo baseInfo = customerBaseInfoAPI.findOne(baseInfoDTO);

        CustomerDetail customerDetail = BeanTransform.copyProperties(customerDetailTO,CustomerDetail.class,true);
        customerDetail.setCreateTime(LocalDateTime.now());
        customerDetail.setCustomerBaseInfo( baseInfo );
        super.save( customerDetail );

        //添加家庭信息
        List<CusFamilyMemberTO> familyMemberTOList = customerDetailTO.getCusFamilyMemberTOList();
        List<CusFamilyMember> cusFamilyMemberList = BeanTransform.copyProperties(familyMemberTOList,CusFamilyMember.class,true);
        for( CusFamilyMember temp : cusFamilyMemberList ) {
            temp.setCreateTime(LocalDateTime.now());
        }
        cusFamilyMemberAPI.save( cusFamilyMemberList );

        return BeanTransform.copyProperties(customerDetail, CustomerDetailBO.class, true);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerDetailBO editCustomerDetail(CustomerDetailTO customerDetailTO) throws SerException {
        CustomerDetail customerDetail = BeanTransform.copyProperties(customerDetailTO,CustomerDetail.class,true);
        customerDetail.setModifyTime(LocalDateTime.now());

        super.update( customerDetail );

        //修改家庭信息  先删除再重新添加
        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id",customerDetail.getId()));
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
            }
            cusFamilyMemberAPI.save( cusFamilyMemberList );
        }


        return BeanTransform.copyProperties(customerDetail, CustomerDetailBO.class, true);
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
            super.remove( id );
        }else{
            super.remove( id );
        }
    }

    @Cacheable
    @Override
    public CustomerDetailBO getCustomerDetailById(String id) throws SerException {
        CustomerDetail customerDetail = super.findById( id );

        CustomerDetailBO customerDetailBO = BeanTransform.copyProperties(customerDetail , CustomerDetailBO.class ,true);

        //查找家庭信息
        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id",id));
        List<CusFamilyMember> cfamilyList = cusFamilyMemberAPI.findByCis( cusFamilyMemberDTO );
        List<CusFamilyMemberBO> cusFamilyMemberBOList = BeanTransform.copyProperties(cfamilyList,CusFamilyMemberBO.class,true);

        customerDetailBO.setCusFamilyMemberBOList( cusFamilyMemberBOList );

        return  customerDetailBO ;
    }


    @Cacheable
    @Override
    public CustomerDetailBO getCustomerDetailByNum(String customerNum) throws SerException {
        CustomerBaseInfoDTO cBaseInfoDTO = new CustomerBaseInfoDTO();
        cBaseInfoDTO.getConditions().add(Restrict.eq("customerNum",cBaseInfoDTO));
        CustomerBaseInfo customerBaseInfo = customerBaseInfoAPI.findOne(cBaseInfoDTO );
        CustomerBaseInfoBO customerBaseInfoBO = BeanTransform.copyProperties( customerBaseInfo,CustomerBaseInfoBO.class,true);

        CustomerDetailDTO cdDTO = new CustomerDetailDTO();
        cdDTO.getConditions().add( Restrict.eq("customerNum",customerNum));
        CustomerDetail customerDetail = super.findOne( cdDTO );
        CustomerDetailBO customerDetailBO = BeanTransform.copyProperties( customerDetail,CustomerDetailBO.class,true);
        //查找家庭信息
        CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
        cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id",customerDetail.getId()));
        List<CusFamilyMember> cfamilyList = cusFamilyMemberAPI.findByCis( cusFamilyMemberDTO );
        List<CusFamilyMemberBO> cusFamilyMemberBOList = BeanTransform.copyProperties(cfamilyList,CusFamilyMemberBO.class,true);

        customerDetailBO.setCustomerBaseInfoBO( customerBaseInfoBO );
        customerDetailBO.setCusFamilyMemberBOList( cusFamilyMemberBOList );

        return customerDetailBO;
    }
}