package com.zzgs.springbootdemo.Bean;

import lombok.Data;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 任务实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class Task {

  /** 任务id */
  private Integer taskId;
  /** 任务内容 */
  private String taskContent;
  /** 任务创建时间 */
  private String taskCreateTime;
  /** 任务完成时间 */
  private String taskFinishTime;
  /** 任务状态 */
  private Integer taskStatus;
  /** 任务所属用户id */
  private Integer userId;
  /** 任务类型id */
  private Integer taskTypeId;
}
