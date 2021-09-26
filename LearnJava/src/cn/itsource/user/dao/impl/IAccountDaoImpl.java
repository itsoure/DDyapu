package cn.itsource.user.dao.impl;

import cn.itsource.user.dao.IAccountDao;
import cn.itsource.user.domain.Account;
import until.JDBCUtil2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IAccountDaoImpl implements IAccountDao {
//    根据工具类获取连接
    JDBCUtil2 util = JDBCUtil2.getInstance();
    private ResultSet set;
    @Override
    public boolean transfer(Account in,Account out,double money)  {
        if(in==null||out ==null||money<=0){
            return false;
        }
        String inSql = "UPDATE account SET money = ? WHERE acc_name =?";
        String outSql = "UPDATE account SET money = ? WHERE acc_name =?";
        Connection conn=null;
        PreparedStatement ps =null;
        PreparedStatement ps2 =null;
        try
         {
            conn = util.getConnection(); //通过util获取连接对象
            ps = conn.prepareStatement(inSql);//通过连接对象获取动态语句对象
            ps2 = conn.prepareStatement(outSql);//通过连接对象获取动态语句对象
            conn.setAutoCommit(false);//通过连接对象关闭自动提交事务
            ps.setString(2,in.getAccName());
            ps.setDouble(1,in.getMoney()+money);//账户金额+转账金额
            int executeUpdate = ps.executeUpdate();// 返回值表示影响的记录数

             //测试回滚事务
//             System.out.println(1/0);
            ps2.setString(2,out.getAccName());
            ps2.setDouble(1,out.getMoney()-money);//原账户金额-转账金额
            int executeUpdate2 = ps2.executeUpdate();// 返回值表示影响的记录数
            if (executeUpdate > 0&&executeUpdate2>0) {
                conn.commit();
                return true;
            }

        } catch (Exception e) {
            //一旦发生异常回滚事务
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 释放资源（关闭连接、关闭语句对象、关闭结果集）先开后关【调用close方法】自动释放资源
            try {
                if (ps2 != null){
                    ps2.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    if (ps != null){
                        ps.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    try {
                        if (conn != null){
                            conn.close();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }

        }
        return false;
    }


    @Override
    public Account searchByAccName(String accName) throws ClassNotFoundException, SQLException {
        String searchSql ="SELECT * FROM account WHERE acc_name =?;";
        try (
                Connection conn = util.getConnection();
                PreparedStatement ps = conn.prepareStatement(searchSql)
        ){
            ps.setString(1,accName);
            set = ps.executeQuery();
            while (set.next()){
                Account acc = new Account(set.getLong("id"),set.getString("acc_name"),set.getDouble("money"));
                return  acc;
            }
        }finally {
            if(set != null){
                set.close();
            }
        }
        return null;
    }
}
