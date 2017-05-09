package com.bjike.goddess.assemble.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.assemble.dto.TicketDTO;
import com.bjike.goddess.assemble.entity.Ticket;


public interface TicketRep extends JpaRep<Ticket, TicketDTO> {
	
    /**
     * 此处使用的是spring-data-jpa接口,不需要对接口进行实现,jpa可根据命名自动进行数据的查询
     * jpa接口规范：http://docs.spring.io/spring-data/jpa/docs/1.11.0.M1/reference/html/
     * <p>
     * /**
     * 通过用户名和密码查找用户
     * @param username 用户名
     * @param password 密码
     * @return
     */
	
}
