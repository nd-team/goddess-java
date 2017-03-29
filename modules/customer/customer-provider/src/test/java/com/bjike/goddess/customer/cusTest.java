package com.bjike.goddess.customer;

import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import com.bjike.goddess.customer.to.CustomerLevelTO;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-15 18:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public class cusTest {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        CustomerLevelTO customerLevelTO =new  CustomerLevelTO();
        customerLevelTO.setCreateTime("2016-07-01 12:12:12");
        customerLevelTO.setModifyTime("2016-08-01 12:12:12");

        CustomerBaseInfoTO  to = new CustomerBaseInfoTO();
        to.setCreateTime("2015-07-01 12:12:12");
        to.setModifyTime("2015-08-01 12:12:12");
//        to.setCustomerLevelTO( customerLevelTO );


        CustomerLevelBO c = BeanTransform.copyProperties( to,CustomerLevelBO.class,true);
        System.out.println(c );
    }

}
