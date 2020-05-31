package com.zzgs.springbootdemo.Bean;

import lombok.Data;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 项目中任务的负责人的实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class TaskPersonLiable {

  /** 任务id */
  private Integer taskId;
  /** 用户id */
  private Integer userId;
  /** 任务下的人员类型 */
  private Integer taskPersonLiableType;
}
