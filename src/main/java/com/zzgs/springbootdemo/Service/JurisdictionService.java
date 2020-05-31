package com.zzgs.springbootdemo.Service;


import com.zzgs.springbootdemo.Bean.Jurisdiction;

import java.util.List;

/**
 * Author:   Tang
 * Date:     2019/12/31 13:41
 * Description:
 */

public interface JurisdictionService {
    /**
     * 查询所有资源的访问权限设置
     * @return 资源列表
     */
    List<Jurisdiction> findAll();
}
