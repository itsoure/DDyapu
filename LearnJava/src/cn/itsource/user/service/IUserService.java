package cn.itsource.user.service;

import cn.itsource.user.domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * IUserService层接口
 *	MVC写代码的顺序，正好反过来
 step1： 先设计数据库表
 step2： 写domain实体类
 step3： 写DAO层
 1. 先写DAO层接口
 2. 再写DAO层Impl实现类
 step4： 写Service层
 1. 先写Service层接口
 2. 再写Service层Impl实现类
 step5： 写Controller层【今天通过JUnit4测试类模拟】
 step6： 写View（今天没有）
 */
public interface IUserService {
    boolean add(User u) throws SQLException, ClassNotFoundException;

    boolean update(User u) throws SQLException, ClassNotFoundException;

    boolean deleteById(long id) throws SQLException, ClassNotFoundException;

    User searchById(long id) throws SQLException, ClassNotFoundException;

    List<User> searchAll() throws SQLException, ClassNotFoundException;

    User searchByName(String name);

    String login(User u) throws SQLException, ClassNotFoundException;

}