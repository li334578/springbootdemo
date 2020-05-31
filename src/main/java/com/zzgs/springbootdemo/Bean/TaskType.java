package com.zzgs.springbootdemo.Bean;

import lombok.Data;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 项目中任务分类的实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class TaskType {

  /** 任务分类id */
  private Integer taskTypeId;
  /** 任务分类名称 */
  private String taskTypeName;
  /** 任务分类创建时间 */
  private String taskTypeCreateTime;
  /** 任务分类删除标记 */
  private Integer taskTypeDelFlag;
}
