package com.zzgs.springbootdemo.Bean;


import lombok.Data;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 任务履历的实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class TaskHistory {

  /** 任务履历id */
  private Integer taskHistoryId;
  /** 任务履历内容 */
  private String taskHistoryContent;
  /** 任务履历操作时间 */
  private String taskHistoryTime;
  /** 任务id */
  private Integer taskId;
  /** 用户id */
  private Integer userId;
}
