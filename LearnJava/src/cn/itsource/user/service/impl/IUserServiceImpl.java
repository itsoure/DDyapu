package cn.itsource.user.service.impl;

import cn.itsource.user.dao.IUserDao;
import cn.itsource.user.dao.impl.IUserDaoImpl;
import cn.itsource.user.domain.User;
import cn.itsource.user.service.IUserService;

import java.sql.SQLException;
import java.util.List;

public class IUserServiceImpl implements IUserService {
    private IUserDao dao = new IUserDaoImpl();


    @Override
    public boolean add(User u) throws SQLException, ClassNotFoundException {
        return dao.add(u);
    }

    @Override
    public boolean update(User u) throws SQLException, ClassNotFoundException {
        if (u==null){
            return false;
        }
        User user=dao.searchById(u.getId());
        return u.equals(user) ? true:dao.update(u);
    }

    @Override
    public boolean deleteById(long id) throws SQLException, ClassNotFoundException {

        return dao.deleteById(id);
    }

    @Override
    public User searchById(long id) throws SQLException, ClassNotFoundException {
        return dao.searchById(id);
    }

    @Override
    public List<User> searchAll() throws SQLException, ClassNotFoundException {

        return dao.searchAll();
    }

    @Override
    public User searchByName(String name) {
        return null;
    }

    @Override
    public String login(User u) throws SQLException, ClassNotFoundException {
        if(u==null){
            return "用户名错误";
        }
        User user = dao.searchByName(u.getName());
        if(user==null){
            return "用户名不存在";
        }
        if(user.getPwd().equals(u.getPwd())){
            return "登录成功";
        }else {
            return "密码错误";
        }

    }
}
