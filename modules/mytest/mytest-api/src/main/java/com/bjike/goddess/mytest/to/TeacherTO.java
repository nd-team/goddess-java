package com.bjike.goddess.mytest.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
* 测试用类 教师
* @Author:			[ wanyi ]
* @Date:			[  2017-12-15 01:47 ]
* @Description:	[ 测试用类 教师 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class TeacherTO extends BaseTO { 

/**
* 姓名
*/
 private String  name; 

/**
* 科目
*/
 private String  subject;

 private List<CarTO> carTOS;

 public List<CarTO> getCarTOS() {
  return carTOS;
 }

 public void setCarTOS(List<CarTO> carTOS) {
  this.carTOS = carTOS;
 }

 public String getName () {
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public String getSubject () { 
 return subject;
 } 
 public void setSubject (String subject ) { 
 this.subject = subject ; 
 }

 @Override
 public String toString() {
  return "TeacherTO{" +
          "name='" + name + '\'' +
          ", subject='" + subject + '\'' +
          '}';
 }
}