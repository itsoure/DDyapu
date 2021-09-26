package cn.itsource.mybatis.stu.mapper.impl;

import cn.itsource.mybatis.domain.Student;
import cn.itsource.mybatis.stu.mapper.StudentMapper;

import java.util.List;

public class StudentMapperImpl implements StudentMapper {
    @Override
    public boolean add(Student u) throws Exception {
        return false;
    }

    @Override
    public boolean update(Student u) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }

    @Override
    public Student searchById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Student> searchAll() throws Exception {
        return null;
    }

    @Override
    public Student searchByName(String name) throws Exception {
        return null;
    }
}
