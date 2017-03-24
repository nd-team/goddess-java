package com.bjike.goddess.customer;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.entity.CustomerBaseInfo;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.enums.CustomerSex;
import com.bjike.goddess.customer.enums.CustomerStatus;
import com.bjike.goddess.customer.enums.CustomerType;
import com.bjike.goddess.customer.service.CustomerBaseInfoSer;
import com.bjike.goddess.customer.service.CustomerLevelSer;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import com.bjike.goddess.customer.to.CustomerLevelTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 客户基本信息测试
 * @Author: [tanghaixiang]
 * @Date: [2017-03-18 14:12]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CustomerBaseInfoTest {

    @Autowired
    private CustomerBaseInfoSer customerBaseInfoAPI;
    @Autowired
    private CustomerLevelSer customerLevelAPI;


    @Test
    public void add() throws SerException {
        CustomerBaseInfoBO cBaseInfoBO = customerBaseInfoAPI.generateCustomerNum();

        CustomerBaseInfoTO customerBaseInfo = new CustomerBaseInfoTO();
        customerBaseInfo.setCreateTime( DateUtil.dateToString(LocalDateTime.now()) );
        customerBaseInfo.setArea("广州");
        customerBaseInfo.setCusEmail("88888@qq.com");
        customerBaseInfo.setCustomerName( "北京艾佳");
        customerBaseInfo.setCustomerNum( cBaseInfoBO.getCustomerNum() );

        customerBaseInfo.setCustomerSex(CustomerSex.MAN);
        customerBaseInfo.setCustomerStatus(CustomerStatus.POTENTIAL);
        customerBaseInfo.setCustomerType(CustomerType.COOPERATOR);
        customerBaseInfo.setGrouthArea("成长地区");
        customerBaseInfo.setIntroducer("介绍人");
        customerBaseInfo.setLifeArea("生活地区");
        customerBaseInfo.setMarketReceptTime(DateUtil.dateToString(LocalDateTime.now()));
        customerBaseInfo.setOldWorkPlace("以往工作地区");
        customerBaseInfo.setOriganizion("组织机构名称");
        customerBaseInfo.setOriganizationSize("组织机构规模");
        customerBaseInfo.setOrigin("客户来源");
        customerBaseInfo.setPhone("5467357");
        customerBaseInfo.setQq("66666");
        customerBaseInfo.setRelation(60.0);
        customerBaseInfo.setTel("13727454445");
        customerBaseInfo.setWeChart("微信");
        customerBaseInfo.setWorkLevel("职级");
        customerBaseInfo.setWorkPosition("岗位");
        customerBaseInfo.setWorkProfession("电信行业");
        customerBaseInfo.setWorkRight("职权");


        CustomerLevelBO clevel  = customerLevelAPI.getCustomerLevelByName("1星级");
        customerBaseInfo.setCustomerLevelTO(BeanTransform.copyProperties(clevel,CustomerLevelTO.class) );

        CustomerBaseInfoBO returnBO = customerBaseInfoAPI.addCustomerBaseInfo( customerBaseInfo );
        System.out.println("");
    }


    /**
     * 测试自动生成客户编号
     * @throws SerException
     */
    @Cacheable
    @Test
    public void generNum() throws SerException {
        CustomerBaseInfoBO customerBaseInfoBO = customerBaseInfoAPI.generateCustomerNum();
        System.out.println( customerBaseInfoBO.getCustomerNum());
    }

    @Test
    public void testEnums()throws SerException {
        CustomerBaseInfoTO to = new CustomerBaseInfoTO();
        to.setCustomerStatus( CustomerStatus.POTENTIAL );

        CustomerBaseInfo c = BeanTransform.copyProperties(to, CustomerBaseInfo.class,true);
        System.out.println("");
    }

}
