package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.FinanceAttendanceDTO;
import com.bjike.goddess.attendance.entity.FinanceAttendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 财务出勤表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-16 04:09 ]
* @Description:	[ 财务出勤表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface FinanceAttendanceRep extends JpaRep<FinanceAttendance ,FinanceAttendanceDTO> {
 @Transactional
 Page<FinanceAttendance> findAll(Pageable pageable);

 @Transactional
 FinanceAttendance saveAndFlush(FinanceAttendance entity);

 @Modifying
 @Query(value = "SELECT id,depart,name,time from attendance_vacate where " +
         "  name in (:names ) and " +
         "( :date BETWEEN DATE_FORMAT(startTime,'%Y-%m-%d') AND DATE_FORMAT(endTime,'%Y-%m-%d'))",nativeQuery = true)
 List<Object[]> findByName(@Param(value = "names") List<String> names, @Param(value = "date") String date);


 }