package com.zzgs.springbootdemo.Bean;


import lombok.Data;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 项目角色实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class ProjectUserRole {

  /** 项目下用户的角色id */
  private Integer projectUserRoleId;
  /** 项目下用户的角色名称 */
  private String projectUserRoleName;
}
