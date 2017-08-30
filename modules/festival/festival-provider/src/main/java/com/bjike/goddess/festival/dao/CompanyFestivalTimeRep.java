package com.bjike.goddess.festival.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.festival.dto.CompanyFestivalTimeDTO;
import com.bjike.goddess.festival.entity.CompanyFestivalTime;

/**
 * 公司放假时间安排持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 08:10 ]
 * @Description: [ 公司放假时间安排持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompanyFestivalTimeRep extends JpaRep<CompanyFestivalTime, CompanyFestivalTimeDTO> {

}