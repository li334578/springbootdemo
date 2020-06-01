package com.zzgs.springbootdemo.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 用户的实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  /** 用户id */
  private Integer userId;
  /** 用户姓名 */
  private String name;
  /** 用户名 */
  private String userName;
  /** 用户密码 */
  private String userPassword;
  /** 用户邮箱 */
  private String userEmail;
  /** 用户手机号 */
  private String userPhone;
  /** 用户工号 */
  private String userJobNumber;
  /** 用户入职时间 */
  private String userEntryTime;
  /** 用户删除标记 */
  private Integer userDelFlag;
  /** 用户部门id */
  private Integer departmentId;
}
