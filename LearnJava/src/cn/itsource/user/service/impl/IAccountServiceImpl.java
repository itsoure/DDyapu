package cn.itsource.user.service.impl;

import cn.itsource.user.dao.IAccountDao;
import cn.itsource.user.dao.impl.IAccountDaoImpl;
import cn.itsource.user.domain.Account;
import cn.itsource.user.service.IAccountService;

import java.sql.SQLException;

public class IAccountServiceImpl implements IAccountService {
    private IAccountDao dao = new IAccountDaoImpl();
    @Override
    public boolean transfer(Account in, Account out, double money) throws SQLException, ClassNotFoundException {
        if(in==null||out ==null||money<=0){
            return false;
        }
        Account acc = dao.searchByAccName(out.getAccName());
        return acc.getMoney() >= money?dao.transfer(in,out,money):false;
    }

    @Override
    public Account searchByAccName(String accName) throws ClassNotFoundException, SQLException {
        return null;
    }
}
