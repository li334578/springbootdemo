package com.zzgs.springbootdemo.Bean;


import lombok.Data;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 项目中人员的实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class ProjectUser {

  /** 项目id */
  private Integer projectId;
  /** 用户id */
  private Integer userId;
  /** 项目下用户的角色id */
  private Integer projectUserRoleId;
}
