package until;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *	代码重构，通过工具类JDBCUtil将获取连接对象的代码抽取出来
 *	工具类实现有2种方式：
 *		1. 全静态方法：简单，但是占用资源多
 *		2. 单例模式+实例方法，复杂，但是节约资源
 *	我们用单例模式+实例方法完成该工具类设计
 */
public class JDBCUtil2 {
	/** private static final 声明一个JDBCUtil常量对象 */
	private static final JDBCUtil2 INSTANCE = new JDBCUtil2();
	
	/** 声明private static修饰Properties对象p，不赋值，为了在静态代码中赋值 */
	private static Properties p;
	
	/** 私有化构造方法 */
	private JDBCUtil2() {}
	
	static {// 为了预加载Properties配置文件，所以在静态代码块中完成创建工作【注意：避免注册驱动的时候，还没有Driver类】
		p = new Properties();
		// 获取当前线程的类加载器：
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (
			// 通过类加载器获取字节流对象：注意资源文件夹从包名路径开始写
			InputStream is = loader.getResourceAsStream("cn/itsource/jdbc/mysql.properties");
		) {
			// 通过 Properties对象p加载资源文件夹中配置文件：mysql.properties
			p.load(is);
//			System.out.println(p);
		} catch (IOException e) {
			System.out.println("解析资源文件失败！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 提供一个public static JDBCUtil getInstance()静态方法获取一个JDBCUtil对象
	 * @return
	 */
	public static JDBCUtil2 getInstance() {
		return INSTANCE;
	}
	
	/**
	 * 获取连接对象方法： public Connection getConnection()
	 *  注意：
	 *  	1. 这里返回值类型Connection是JDK原生接口类型，不是MySQL中的，为了获取任意类型数据库，而不仅仅是MySQL
	 *  	2. 这里不能try() {} 因为连接对象需要在执行SQL后关闭，现在关闭就会出错。导致执行SQL失败。
	 *  
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		// 获取mysql.properties文件中的url
		String url = p.getProperty("url");
		// 获取mysql.properties文件中的username
		String username = p.getProperty("username");
		// 获取mysql.properties文件中的password
		String password = p.getProperty("password");
		// 获取mysql.properties文件中的driverClassName
		String driverClassName = p.getProperty("driverClassName");
		// DriverManager调用获取连接方法:getConnection(...);
		Connection conn = DriverManager.getConnection(url, username, password);
		// 通过反射获取驱动Driver	注册mysql驱动，为了可以随时跟换连接其他非MySQL数据
		Class.forName(driverClassName);
		return conn;
	}
}	
