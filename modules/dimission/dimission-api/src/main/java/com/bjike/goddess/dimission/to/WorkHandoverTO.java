package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 工作交接
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-17 02:24 ]
* @Description:	[ 工作交接 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class WorkHandoverTO extends BaseTO { 

/**
* 交接人姓名
*/
 private String  handover; 

/**
* 被交接人姓名
*/
 private String  transferred; 

/**
* 交接信息明细
*/
 private String  handoverInfo; 

/**
* 交接情况
*/
 private String  situation; 

/**
* 交接人确认
*/
 private String  confirm; 

/**
* 福利模块负责人
*/
 private String  director; 

/**
* 福利模块负责人确认
*/
 private String  directorConfirm; 



 public String getHandover () { 
 return handover;
 } 
 public void setHandover (String handover ) { 
 this.handover = handover ; 
 } 
 public String getTransferred () { 
 return transferred;
 } 
 public void setTransferred (String transferred ) { 
 this.transferred = transferred ; 
 } 
 public String getHandoverInfo () { 
 return handoverInfo;
 } 
 public void setHandoverInfo (String handoverInfo ) { 
 this.handoverInfo = handoverInfo ; 
 } 
 public String getSituation () { 
 return situation;
 } 
 public void setSituation (String situation ) { 
 this.situation = situation ; 
 } 
 public String getConfirm () { 
 return confirm;
 } 
 public void setConfirm (String confirm ) { 
 this.confirm = confirm ; 
 } 
 public String getDirector () { 
 return director;
 } 
 public void setDirector (String director ) { 
 this.director = director ; 
 } 
 public String getDirectorConfirm () { 
 return directorConfirm;
 } 
 public void setDirectorConfirm (String directorConfirm ) { 
 this.directorConfirm = directorConfirm ; 
 } 
 }