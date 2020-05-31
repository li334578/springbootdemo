package com.zzgs.springbootdemo.Bean;


import lombok.Data;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 部门实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class Department {

  /** 部门id */
  private Integer departmentId;
  /** 部门名称 */
  private String departmentName;
  /** 部门创建时间 */
  private String departmentCreateTime;
  /** 部门删除标记 */
  private Integer departmentDelFlag;
}
