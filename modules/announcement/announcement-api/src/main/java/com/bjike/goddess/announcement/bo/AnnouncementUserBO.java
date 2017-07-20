package com.bjike.goddess.announcement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 公告对应的发布对象业务传输对象
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-12 10:34 ]
* @Description:	[ 公告对应的发布对象业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AnnouncementUserBO extends BaseBO { 

/**
* 是否已经读取
*/
 private Boolean  haveRead; 



 public Boolean getHaveRead () { 
 return haveRead;
 } 
 public void setHaveRead (Boolean haveRead ) { 
 this.haveRead = haveRead ; 
 } 
 }