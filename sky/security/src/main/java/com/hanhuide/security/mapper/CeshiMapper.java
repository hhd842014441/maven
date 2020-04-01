package com.hanhuide.security.mapper;

import com.baomidou.mybatisplus.annotations.DataSource;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hanhuide.driver.dataSource.DataSourceNames;
import com.hanhuide.driver.dataSource.TransactionNames;
import com.hanhuide.security.model.SysUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @DataSource(name = DataSourceNames.CLUSTER)
    @Transactional(value = TransactionNames.CLUSTER, rollbackFor = Exception.class)
    List<SysUser> findAll2();

}
