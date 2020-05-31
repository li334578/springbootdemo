package com.zzgs.springbootdemo.Bean;

import lombok.Data;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 任务标签的实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class TaskTag {

  /** 任务标签id */
  private Integer taskTagId;
  /** 任务标签名称 */
  private String taskTagName;
  /** 任务标签创建时间 */
  private String taskTagCreateTime;
  /** 任务标签删除标记 */
  private Integer taskTagDelFlag;
}
