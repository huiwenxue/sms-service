package com.xhw.oracle;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleTest {
    @Test
    public void test() {
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.66.166:1521:orcl";
        String username = "xhw";
        String password = "xhw";
        {
            try {
                //注册驱动
                Class.forName(driver);
                //获取连接
                Connection conn = DriverManager.getConnection(url,username,password);
                //调用存储过程
                CallableStatement callableStatement = conn.prepareCall("{call yearsal(?,?)}");
                //设置存储过程的值
                callableStatement.setInt(1,7369);
                //注册返回值的类型
                callableStatement.registerOutParameter(2, OracleTypes.NUMBER);
                //执行查询语句
                callableStatement.execute();
                //获取结果集
                Object object =  callableStatement.getObject(2);

                System.out.println(object);
                //关闭流
                conn.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
