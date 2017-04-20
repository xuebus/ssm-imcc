package com.zhaopin.core.common;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;

/**
 * CommonDao
 * 为解决mybatis-spring-1.2.0.jar
 * 及以上版本默认不再注入SqlSessionFactory问题
 *
 * Created by SYJ on 2017/4/13.
 */
public class CommonDao extends SqlSessionDaoSupport {
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
