package cn.itsource.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

import com.mysql.jdbc.Driver;
import until.JDBCUtil2;

/**
 *	1. 通过jdbc连接mysql
	贾：加载驱动【创建驱动对象，以及注册驱动】
 *  连：通过驱动管理类DriverManager获取连接对象
 *  欲：通过连接对象获取数据库语句对象Statement(静态语句对象)/PreparedStatement(动态语句对象)
 *  执：执行SQL语句
 *  事：释放资源（关闭连接、关闭语句对象、关闭结果集）
	1. 步骤：【掌握】
		1. 在项目下创建一个lib文件夹，将mysql-connector-java-5.1.26-bin.jar包复制到lib下
		2. build path,生成小奶瓶
		3. 在测试类中，使用jdbc，操作数据库【重点】
		4. 写代码：
 *			4.1	DriverManager注册驱动
 *				方式1：不用
 *					// 创建一个mysql驱动对象（不同的数据库驱动是不同的）
					Driver driver = new com.mysql.jdbc.Driver();
 *					
					// 通过驱动管理器对象DriverManager，注册驱动
					DriverManager.registerDriver(driver);
					
 *				方式2：建议用
 *					通过反射获取驱动Driver	注册mysql驱动
					Class.forName("com.mysql.jdbc.Driver");
					
 *				方式3：建议用	
 *					DriverManager注册驱动	从jdk6开始，驱动都是自动注册的，直接获取连接即可
 *
 *			4.2	DriverManager获取连接对象Connection： DriverManager.getConnection(url,userName,password);
 				
 *			4.3	通过连接对象Connection获取 语句对象Statement（静态语句对象）/PreparedStatement（懂态语句对象）
 				Statement st = conn.createStatement();// 获取静态语句对象
 				PreparedStatement prst = conn.prepareStatement();// 获取动态语句对象
 				
 *				语句对象是专门用来做数据库增删改查操作的（DDL、DML、DQL）
				  1. boolean execute(String sql)  
				  		一般执行DDL语句（创建表等）
				  
				  2. ResultSet executeQuery(String sql) 
				  	执行给定的DQL（查询）语句，该语句返回结果装到 ResultSet对象。  
				  		一般执行的是查询语句，返回的是所有的记录，记录装到一个ResultSet的对象中。
				  		如果想要取出数据，想要遍历结合，用迭代器
				  		hasNext()
				  
				  3. int executeUpdate(String sql)  
				   		一般执行DML(增删改)操作
 
 *			4.4  准备一个String类型的sql语句
 *			4.5  通过语句对象，调用方法执行SQL语句：
 					1. boolean execute(String sql)  一般执行DDL语句（创建表等）
 					2. ResultSet executeQuery(String sql) 执行给定的DQL（查询）语句
 					3. int executeUpdate(String sql)  一般执行DML(增删改)操作
 					
 *			4.6  释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】
 				
 
 *		注意：statement执行一次执行一句SQL语句
 */
public class JDBCTest3 {
	
	@Test
	public void testJDBC1() throws Exception {

		JDBCUtil2 util2 = JDBCUtil2.getInstance();
		try(
				Connection conn = util2.getConnection();
				Statement stmt = conn.createStatement();
		) {
			// 4. 准备一个String类型的sql语句
			String dropSql = "DROP TABLE IF EXISTS `user2`;";
			String createSql = "CREATE TABLE `user2` ( " + // 注意：SQL中如果使用到了""，必须替换为'',或者转义一下
							"`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
							"`name` varchar(255) NOT NULL COMMENT '姓名', " +
							"`pwd` varchar(255) NOT NULL COMMENT '密码', " +
							"`money` double(255,0) DEFAULT NULL COMMENT '余额'," +
							"PRIMARY KEY (`id`)"  +
							") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";

			stmt.execute(dropSql);// 【注意，一次只能执行一条SQL语句】
			stmt.execute(createSql);// 【注意，一次只能执行一条SQL语句】
			// 6.  释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】自动释放资源
		}
	}
}
