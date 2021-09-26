package cn.itsource.user.dao;
import cn.itsource.user.domain.Account;

import java.sql.SQLException;

public interface IAccountDao {
    public boolean transfer(Account in,Account out,double money);
    Account searchByAccName(String accName)throws ClassNotFoundException,SQLException;
}
