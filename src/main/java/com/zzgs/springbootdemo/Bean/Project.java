package com.zzgs.springbootdemo.Bean;


import lombok.Data;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 项目实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class Project {

  /** 项目id */
  private Integer projectId;
  /** 项目名称 */
  private String projectName;
  /** 项目内容 */
  private String projectContent;
  /** 项目状态 */
  private Integer projectStatus;
}
