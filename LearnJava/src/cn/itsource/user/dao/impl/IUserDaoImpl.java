package cn.itsource.user.dao.impl;

import cn.itsource.user.dao.IUserDao;
import cn.itsource.user.domain.User;
import until.JDBCUtil2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class IUserDaoImpl implements IUserDao {
    JDBCUtil2 util = JDBCUtil2.getInstance();
    private ResultSet set;
    @Override
    public boolean add(User u) throws SQLException, ClassNotFoundException {
        if(u==null){
            return false;
        }
        String addSql = "INSERT INTO user2 (name, pwd, money) VALUES (?,?,?);";
        // 为了自动关闭连接对象和语句对象，所以，写自动关流语法。【连接对象和语句对象实现了AutoCloseable接口】
        try(
                // 通过util对象获取连接对象
                Connection conn = util.getConnection();
                // 3. 通过连接对象Connection获取 语句对象Statement（静态语句对象）
                PreparedStatement ps = conn.prepareStatement(addSql);
        ) {
            ps.setString(1,u.getName());
            ps.setString(2,u.getPwd());
            ps.setDouble(3,u.getMoney());
            int executeUpdate = ps.executeUpdate();// 返回值表示影响的记录数
            if (executeUpdate > 0) {
                return true;
            }
            // 6.  释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】自动释放资源
        }

        return false;
    }

    @Override
    public boolean update(User u) throws SQLException, ClassNotFoundException {
        String updateSql = "UPDATE user2 SET name =?, pwd = ? ,money = ?WHERE id=?;";
        // 为了自动关闭连接对象和语句对象，所以，写自动关流语法。【连接对象和语句对象实现了AutoCloseable接口】
        try(
                Connection conn = util.getConnection();
                PreparedStatement ps = conn.prepareStatement(updateSql);

        ) {

            ps.setString(1,u.getName());
            ps.setString(2,u.getPwd());
            ps.setDouble(3,u.getMoney());
            ps.setLong(4,u.getId());
            int executeUpdate = ps.executeUpdate();// 返回值表示影响的记录数
            if (executeUpdate > 0) {
                return true;
            }
            // 6.  释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】自动释放资源
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) throws SQLException, ClassNotFoundException {
        String deleteSql = "DELETE FROM user2 WHERE id =?;";
        // 为了自动关闭连接对象和语句对象，所以，写自动关流语法。【连接对象和语句对象实现了AutoCloseable接口】
        try(
                // 通过util对象获取连接对象
                Connection conn = util.getConnection();
                PreparedStatement ps = conn.prepareStatement(deleteSql);
        ) {
            ps.setLong(1,id);
            int executeUpdate = ps.executeUpdate();// 返回值表示影响的记录数
            if (executeUpdate > 0) {
                System.out.println("删除成功！");
            }
            // 6.  释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】自动释放资源
        }
        return false;
    }

    /**
     * 根据id查询单个对象
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public User searchById(long id) throws SQLException, ClassNotFoundException {
        // 4. 准备一个String类型的searchSql语句
        String searchSql = "SELECT * FROM user2 WHERE id = ?;";
        // 为了自动关闭连接对象和语句对象，所以，写自动关流语法。【连接对象和语句对象实现了AutoCloseable接口】
        try(
                // 通过util对象获取连接对象
                Connection conn = util.getConnection();
                // 3. 通过连接对象Connection获取 语句对象Statement（静态语句对象）
                PreparedStatement ps= conn.prepareStatement(searchSql)

        ) {
            // 遍历上面的结果集ResultSet对象set，不知道多少条记录用while循环
            ps.setLong(1,id);
            set = ps.executeQuery();
            while (set.next()) {// 判断是否有下一条记录
                // 通过set对象中的方法取值：
                String name = set.getString("name");// 获取varchar类型name字段的值
                String pwd = set.getString("pwd");// 获取varchar类型pwd字段的值
                double money = set.getDouble("money");// 获取double类型money字段的值
                User user = new User(id,name,pwd,money);
                return user;
            }
        }finally {
            if(set != null){
                set.close();
            }
        }
        return null;
    }

    @Override
    public List<User> searchAll() throws SQLException, ClassNotFoundException {
        String searchSql="SELECT * FROM user2";
        try(
                // 通过util对象获取连接对象
                Connection conn = util.getConnection();
                // 3. 通过连接对象Connection获取 语句对象Statement（静态语句对象）
                Statement stmt = conn.createStatement();
                ResultSet set = stmt.executeQuery(searchSql);
        ) {
            // 遍历上面的结果集ResultSet对象set，不知道多少条记录用while循环
            List<User>list = new ArrayList<User>();
            while (set.next()) {// 判断是否有下一条记录
                // 通过set对象中的方法取值：
                long id = set.getLong("id");
                String name = set.getString("name");// 获取varchar类型name字段的值
                String pwd = set.getString("pwd");// 获取varchar类型pwd字段的值
                double money = set.getDouble("money");// 获取double类型money字段的值
                User user = new User(id,name,pwd,money);
                list.add(user);
            }
            return  list;
        }
    }

    @Override
    public User searchByName(String name) throws SQLException, ClassNotFoundException {

//        String searchSql ="SELECT * FROM user2 WHERE name = '"+name+"';";
        String searchSql ="SELECT * FROM user2 WHERE name =?;";
        try (
                Connection conn = util.getConnection();
                PreparedStatement ps = conn.prepareStatement(searchSql)
                ){
            ps.setString(1,name);
           set = ps.executeQuery();
            while (set.next()){
                long id = set.getLong("id");
                String n =set.getString("name");
                String pwd=set.getString("pwd");
                double money = set.getDouble("money");
                User user = new User(id,n,pwd,money);
                return  user;
            }
        }finally {
            if(set != null){
                set.close();
            }
        }
        return null;
    }
}
