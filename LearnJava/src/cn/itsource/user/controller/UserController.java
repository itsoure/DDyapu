package cn.itsource.user.controller;

import cn.itsource.user.dao.IAccountDao;
import cn.itsource.user.domain.Account;
import cn.itsource.user.domain.User;
import cn.itsource.user.service.IAccountService;
import cn.itsource.user.service.IUserService;
import cn.itsource.user.service.impl.IAccountServiceImpl;
import cn.itsource.user.service.impl.IUserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

public class UserController {
    private IUserService service = new IUserServiceImpl();
    private IAccountService service2 = new IAccountServiceImpl();
    @Test
    public void testSearchById() throws SQLException, ClassNotFoundException {
//        User user = dao.searchById(2);
//        System.out.println(user);
        System.out.println(service.searchById(8));
    }
    @Test
    public void testLogin() throws Exception{
        User u = new User("曹操1","1231");
        String longing = service.login(u);
        System.out.println(longing);
    }
    @Test
    public void testbyid() throws SQLException,ClassNotFoundException{
        User u = new User("曹操1","123",200);
        System.out.println(service.add(u));
    }
    @Test
    public void testtrans() throws SQLException, ClassNotFoundException {
        Account out = new Account("曹操",100);
        Account in = new Account("刘备",100);
        boolean ser = service2.transfer(in,out,100);
        if (ser){
            System.out.println("转账成功");
        }else {
            System.out.println("转账失败");
        }
    }
}
