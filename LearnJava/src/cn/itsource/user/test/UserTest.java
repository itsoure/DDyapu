package cn.itsource.user.test;

import cn.itsource.user.dao.IUserDao;
import cn.itsource.user.dao.impl.IUserDaoImpl;
import cn.itsource.user.domain.User;
import cn.itsource.user.service.IUserService;
import cn.itsource.user.service.impl.IUserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

public class UserTest {
    private IUserDao dao = new IUserDaoImpl();
    private IUserService service = new IUserServiceImpl();
    @Test
    public void testSearchById() throws SQLException, ClassNotFoundException {
//        User user = dao.searchById(2);
//        System.out.println(user);
        System.out.println( service.searchById(2));

    }
    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user =new User("qw","1121",344);
        boolean add =  service.add(user);
        if(add){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }

    }
    @Test
    public void update() throws SQLException, ClassNotFoundException {
        User user=new User(2,"正在","111",200);
        boolean add =  dao.update(user);
        if(add){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }

    }
    @Test
    public void datea() throws SQLException, ClassNotFoundException {
//        System.out.println(service.deleteById(2));
        service.deleteById(5);

    }
    @Test
    public void ass() throws SQLException, ClassNotFoundException {
//        User user = dao.searchById(2);
//        System.out.println(user);
        System.out.println(service.searchAll());
    }

}
