package cn.itsource.mybatis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;
import until.JDBCUtil2;


/**
 * 测试通过JDBCUtil2工具类获取连接后的CRUD操作
 */
public class CRUDTest {
	
	@Test // 创建表 
	public void testDDL() throws Exception {
		// 获取JDBCUtil2对象，通过静态方法: JDBCUtil2.getInstance();
		JDBCUtil2 util = JDBCUtil2.getInstance();
		// 为了自动关闭连接对象和语句对象，所以，写自动关流语法。【连接对象和语句对象实现了AutoCloseable接口】
		try(
			// 通过util对象获取连接对象
			Connection conn = util.getConnection();
			// 3. 通过连接对象Connection获取 语句对象Statement（静态语句对象）
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
			/* 
			 * 5. 通过语句对象stmt执行数据库增删改查操作的（DDL、DML、DQL）
					  1. boolean execute(String sql)  
					  		一般执行DDL语句（创建表等）
			 */
			stmt.execute(dropSql);// 【注意，一次只能执行一条SQL语句】
			stmt.execute(createSql);// 【注意，一次只能执行一条SQL语句】
			// 6.  释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】自动释放资源
		}
	}
	
	
	@Test // 添加数据
	public void testAdd() throws Exception {
		// 获取JDBCUtil2对象，通过静态方法: JDBCUtil2.getInstance();
		JDBCUtil2 util = JDBCUtil2.getInstance();
		// 为了自动关闭连接对象和语句对象，所以，写自动关流语法。【连接对象和语句对象实现了AutoCloseable接口】
		try(
			// 通过util对象获取连接对象
			Connection conn = util.getConnection();
			// 3. 通过连接对象Connection获取 语句对象Statement（静态语句对象）
			Statement stmt = conn.createStatement();
		) {
			// 4. 准备一个String类型的addSql语句
			String addSql = "INSERT INTO user2 (name, pwd, money) VALUES ('王老师', '6969', 500.00);";
			/* 
			 * 5. 通过语句对象stmt执行数据库增删改查操作的DML
					  1. int executeUpdate(String addSql) 一般执行DDL语句（增删改 表等）
			 */
			int executeUpdate = stmt.executeUpdate(addSql);// 返回值表示影响的记录数
			if (executeUpdate > 0) {
				System.out.println("添加成功！");
			}
			// 6.  释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】自动释放资源
		}
	}
	
	
	@Test // 修改数据
	public void testUpdate() throws Exception {
		// 获取JDBCUtil2对象，通过静态方法: JDBCUtil2.getInstance();
		JDBCUtil2 util = JDBCUtil2.getInstance();
		// 为了自动关闭连接对象和语句对象，所以，写自动关流语法。【连接对象和语句对象实现了AutoCloseable接口】
		try(
			// 通过util对象获取连接对象
			Connection conn = util.getConnection();
			// 3. 通过连接对象Connection获取 语句对象Statement（静态语句对象）
			Statement stmt = conn.createStatement();
		) {
			// 4. 准备一个String类型的updateSql语句
			String updateSql = "UPDATE user2 SET name = '王老师2号', pwd = '996' WHERE id = 1;";
			/* 
			 * 5. 通过语句对象stmt执行数据库增删改查操作的DML
					 1. int executeUpdate(String updateSql) 一般执行DDL语句（增删改 表等）
			 */
			int executeUpdate = stmt.executeUpdate(updateSql);// 返回值表示影响的记录数
			if (executeUpdate > 0) {
				System.out.println("修改成功！");
			}
			// 6.  释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】自动释放资源
		}
	}
	
	
	@Test // 删除数据
	public void testDelete() throws Exception {
		// 获取JDBCUtil2对象，通过静态方法: JDBCUtil2.getInstance();
		JDBCUtil2 util = JDBCUtil2.getInstance();
		// 为了自动关闭连接对象和语句对象，所以，写自动关流语法。【连接对象和语句对象实现了AutoCloseable接口】
		try(
				// 通过util对象获取连接对象
				Connection conn = util.getConnection();
				// 3. 通过连接对象Connection获取 语句对象Statement（静态语句对象）
				Statement stmt = conn.createStatement();
				) {
			// 4. 准备一个String类型的deleteSql语句
			String deleteSql = "DELETE FROM user2 WHERE id = 1;";
			/* 
			 * 5. 通过语句对象stmt执行数据库增删改查操作的DML
					  1. int executeUpdate(String deleteSql) 一般执行DDL语句（增删改 表等）
			 */
			int executeUpdate = stmt.executeUpdate(deleteSql);// 返回值表示影响的记录数
			if (executeUpdate > 0) {
				System.out.println("删除成功！");
			}
			// 6.  释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】自动释放资源
		}
	}
	
	
	@Test // 查询数据
	public void testSearch() throws Exception {
		// 获取JDBCUtil2对象，通过静态方法: JDBCUtil2.getInstance();
		JDBCUtil2 util = JDBCUtil2.getInstance();
		// 4. 准备一个String类型的searchSql语句
		String searchSql = "SELECT * FROM user2;";
		// 为了自动关闭连接对象和语句对象，所以，写自动关流语法。【连接对象和语句对象实现了AutoCloseable接口】
		try(
			// 通过util对象获取连接对象
			Connection conn = util.getConnection();
			// 3. 通过连接对象Connection获取 语句对象Statement（静态语句对象）
			Statement stmt = conn.createStatement();
			/* 
			 * 5. 通过语句对象stmt执行数据库增删改查操作的DML
				 1. ResultSet executeQuery(String searchSql) 一般执行DQL语句（查询 表操作）
				执行给定的DQL（查询）语句，该语句返回结果装到 ResultSet对象。  
			 * 	一般执行的是查询语句，返回的是所有的记录，记录装到一个ResultSet的对象中。
			 * 	如果想要取出数据，想要遍历结合，用迭代器
			 * 	next()  判断是否有下一条记录
			 * 	set.getLong("id");// 获取bigInt类型id字段的值
			 * 	set.getString("name");// 获取varchar类型name字段的值
			 * 	set.getObject("字段名");// 获取任意类型字段的值
			 */
			ResultSet set = stmt.executeQuery(searchSql);
		) {
			// 遍历上面的结果集ResultSet对象set，不知道多少条记录用while循环
			while (set.next()) {// 判断是否有下一条记录
				// 通过set对象中的方法取值：
				long id = set.getLong("id");// 获取bigInt类型id字段的值
				String name = set.getString("name");// 获取varchar类型name字段的值
				String pwd = set.getString("pwd");// 获取varchar类型pwd字段的值
				double money = set.getDouble("money");// 获取double类型money字段的值
				System.out.println(id + ", " + name + ", " + pwd + ", " + money);
			}
			
			// 6.  释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】自动释放资源
		}
	}
}
