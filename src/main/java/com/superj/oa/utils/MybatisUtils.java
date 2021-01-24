package com.superj.oa.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

/**
 * Mybatis工具类
 */
public class MybatisUtils {
    /**
     * 优化：创建sql自动获取sqlsession，使用完毕后自动关闭，避免忘记关闭造成程序崩溃
     */
    //利用static(静态)属于类不属于对象,且全局唯一
    private static SqlSessionFactory sqlSessionFactory = null;
    //利用静态块在初始化类时实例化sqlSessionFactory
    static{
        Reader reader = null;
        try{
            reader = Resources.getResourceAsReader("mybatis-config.xml");//读取配置文件
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);//构建sqlSessionFactory
        }catch(IOException e){
            //初始化错误时,通过抛出异常ExceptionInInitializerError通知调用者
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 执行SELECT查询SQL
     * @param func 要执行查询语句的代码块
     * @return 查询结果
     */
    public static Object executeQuery(Function<SqlSession,Object> func){//function函数接口，有一个参数SqlSession且有返回值Object
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Object obj = func.apply(sqlSession);
            return obj;
        }finally {
            sqlSession.close();
        }
        //catch块可以不写
    }

    /**
     * 执行INSERT/UPDATE/DELETE写操作SQL
     * @param func 要执行的写操作代码块
     * @return 写操作后返回的结果
     */
    public static Object executeUpdate(Function<SqlSession,Object> func){
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        try {
            Object obj = func.apply(sqlSession);
            sqlSession.commit();
            return obj;
        }catch (RuntimeException e){
            sqlSession.rollback();
            throw e;
        }finally{
            sqlSession.close();
        }
    }
}
