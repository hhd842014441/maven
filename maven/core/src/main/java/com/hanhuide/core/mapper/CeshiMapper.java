package com.hanhuide.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hanhuide.core.model.SysUser;
import com.hanhuide.driver.annotation.DataSource;
import com.hanhuide.driver.dataSource.DataSourceNames;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: maven
 * @description:
 * @author: 韩惠德
 * @create: 2019-12-24 16:39
 * @version: 1.0
 **/
@Service
public interface CeshiMapper extends BaseMapper<SysUser> {
    @Select("select * from sys_user")
    List<SysUser> findAll();

    @Select("select * from sys_user")
    @DataSource(DataSourceNames.CLUSTER)
    List<SysUser> findAll2();

}
