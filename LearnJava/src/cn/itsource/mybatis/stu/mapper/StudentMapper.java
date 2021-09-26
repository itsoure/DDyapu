package cn.itsource.mybatis.stu.mapper;

import java.util.List;

import cn.itsource.mybatis.domain.Student;

/**
 * 这是DAO层接口，只不过我们要用MyBatis就必须按照这个命名
 * StudentMapper
 */
public interface StudentMapper {
	boolean add(Student u) throws Exception;
	boolean update(Student u) throws Exception;
	boolean delete(Long id) throws Exception;
	Student searchById(Long id) throws Exception;
	List<Student> searchAll() throws Exception;
	Student searchByName(String name) throws Exception;
}
