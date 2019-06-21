package com.cheer.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    //保证系统中有且只有一个facatory实例
    private static SqlSessionFactory factory;

    //静态代码块。在静态变量初始化完成之后执行，并且只执行一次
    static {
        String resource = "mybatis_config.xml";
        InputStream inputStream = null;

        try {
            //导入 mybatis的配置文件,地址“mybatis_config.xml”
            inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream == null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static SqlSession getSession() {
        return factory.openSession();
    }

    public static void close(SqlSession session){
        try{
            session.commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
