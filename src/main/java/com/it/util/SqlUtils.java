package com.it.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/*
SqlSessionFactory工厂类进行重复创建
就相当于每次买手机都需要重新创建一个手机生产工厂来给你制造一个手机一样，资源消耗非
常大但性能却非常低。所以这么做是不允许的。
那如何来优化呢？
代码重复可以抽取工具类
对指定代码只需要执行一次可以使用静态代码块
 */
public class SqlUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        //静态代码块会随着类的加载而自动执行，且只执行一次
        try {
            String resource="mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream(resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);//随着静态代码块的执行而赋值
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
}
