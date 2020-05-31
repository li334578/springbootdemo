package com.zzgs.springbootdemo.Mapper;

import com.zzgs.springbootdemo.Bean.Jurisdiction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author:   Tang
 * Date:     2019/12/31 13:41
 * Description: 资源mapper
 */
@Mapper
public interface JurisdictionDao {
    /**
     * 按照权重升序查询所有的资源访问权限
     * @return 资源列表
     */
    @Select("SELECT * FROM jurisdiction ORDER BY jurisdiction_weight ASC")
    List<Jurisdiction> findAll();
}
