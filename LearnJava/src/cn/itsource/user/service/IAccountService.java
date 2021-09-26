package cn.itsource.user.service;

import cn.itsource.user.domain.Account;
import java.sql.SQLException;

public interface IAccountService {
    public boolean transfer(Account in, Account out, double money) throws SQLException, ClassNotFoundException;
    Account searchByAccName(String accName)throws ClassNotFoundException, SQLException;
}
