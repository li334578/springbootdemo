package com.zzgs.springbootdemo.Dto;

import com.zzgs.springbootdemo.Bean.User;
import lombok.Data;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-06-01 22:45:49
 */
@Data
public class UserDto extends User {
    private String departName;
}
