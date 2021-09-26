package cn.itsource.mybatis;

import org.junit.Test;
import until.JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCtest2 {
    @Test
    public void testJDBC1() throws Exception{
//        Class.forName("com.mysql.jdbc.Driver");
//        Driver driver = new com.mysql.jdbc.Driver();
//        DriverManager.registerDriver(driver);
        JDBCUtil util = JDBCUtil.getInstance();
        try (
                Connection conn = util.getConnection();
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8","root","123456");
//        System.out.println(conn);
                Statement stmt = conn.createStatement();){

            String dropSql="DROP TABLE IF EXISTS user2";
            String createSql = " CREATE TABLE `user2` ( " +
                    "`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                    "`name` varchar(255) NOT NULL COMMENT '姓名', " +
                    "`pwd` varchar(255) NOT NULL COMMENT '密码', " +
                    "`money` double(255,0) DEFAULT NULL COMMENT '余额'," +
                    "PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
            stmt.execute(dropSql);
            stmt.execute(createSql);

        }


    }

}
