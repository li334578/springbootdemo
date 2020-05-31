package com.zzgs.springbootdemo.Bean;


import lombok.Data;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 部门职位实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class DepartmentPosition {

  /** 部门职位id */
  private Integer departmentPositionId;
  /** 部门职位名称 */
  private String departmentPositionName;
  /** 部门职位创建时间 */
  private String departmentPositionCreateTime;
  /** 部门职位删除标记 0为删除 1为激活 */
  private Integer departmentPositionDelFlag;
}
