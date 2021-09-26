package until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static final JDBCUtil INSTANCE = new JDBCUtil();
    private JDBCUtil(){};
    public static JDBCUtil getInstance(){
        return INSTANCE;
    }
    public Connection getConnection() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8","root","123456");
        return conn;
    }

}
