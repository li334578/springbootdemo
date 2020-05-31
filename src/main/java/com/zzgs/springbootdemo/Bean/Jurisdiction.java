package com.zzgs.springbootdemo.Bean;


import lombok.Data;

import java.io.Serializable;

/**
 * @author : LiWenBo
 * @program : ITmanager
 * @description : 资源实体类
 * @date : 2020-05-02 13:58:36
 */
@Data
public class Jurisdiction implements Serializable {

  /** 资源id */
  private Integer jurisdictionId;
  /** 资源路径 */
  private String jurisdictionResources;
  /** 资源访问值 */
  private String jurisdictionValue;
  /** 资源权重值 */
  private Integer jurisdictionWeight;
}
