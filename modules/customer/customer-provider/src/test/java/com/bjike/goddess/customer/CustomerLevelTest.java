package com.bjike.goddess.customer;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CustomerLevelDTO;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.service.CustomerLevelSer;
import com.bjike.goddess.customer.to.CustomerLevelTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-18 14:46]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CustomerLevelTest {

    @Autowired
    private CustomerLevelSer customerLevelAPI;

    @Test
    public void addLevel() throws SerException {
        CustomerLevelTO clevel = new CustomerLevelTO();
        clevel.setName("9星级");
        clevel.setRemark("这个自己想");
        clevel.setStatus(Status.THAW);
        clevel.setCreateTime(DateUtil.dateToString(LocalDateTime.now()));

        CustomerLevelBO cusbo = customerLevelAPI.addCustomerLevel( clevel );
        System.out.println(cusbo);
    }


    @Test
    public void getLevelByName () throws SerException {
        String name = "1星级";
        CustomerLevelBO clevel  = customerLevelAPI.getCustomerLevelByName(name);
        System.out.println("");
    }
}
