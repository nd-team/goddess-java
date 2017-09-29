package com.bjike.goddess.task.bo.custmize;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.util.List;

/**固定表头
 * @Author: [liguiqin]
 * @Date: [2017-09-28 15:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FixedField {
   private String tableName; //表名
   @ExcelHeader(name = "任务名称")
   private String taskName; //任务名称
   @ExcelHeader(name = "人员")
   private String username; //人员
   @ExcelHeader(name = "计划执行时间")
   private String planTime; //计划执行时间
   @ExcelHeader(name = "任务类型")
   private String taskType; //任务类型
   @ExcelHeader(name = "工作内容")
   private String content; //工作内容
   @ExcelHeader(name = "计划工作量")
   private String planNum; //计划工作量
   @ExcelHeader(name = "所需时长")
   private String needTime; //所需时长
   @ExcelHeader(name = "备注")
   private String remark; //备注

   List<CustomField> customFields;

   public String getTaskName() {
      return taskName;
   }

   public void setTaskName(String taskName) {
      this.taskName = taskName;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPlanTime() {
      return planTime;
   }

   public void setPlanTime(String planTime) {
      this.planTime = planTime;
   }

   public String getTaskType() {
      return taskType;
   }

   public void setTaskType(String taskType) {
      this.taskType = taskType;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getPlanNum() {
      return planNum;
   }

   public void setPlanNum(String planNum) {
      this.planNum = planNum;
   }

   public String getNeedTime() {
      return needTime;
   }

   public void setNeedTime(String needTime) {
      this.needTime = needTime;
   }

   public String getRemark() {
      return remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public List<CustomField> getCustomFields() {
      return customFields;
   }

   public void setCustomFields(List<CustomField> customFields) {
      this.customFields = customFields;
   }

   public String getTableName() {
      return tableName;
   }

   public void setTableName(String tableName) {
      this.tableName = tableName;
   }
}


